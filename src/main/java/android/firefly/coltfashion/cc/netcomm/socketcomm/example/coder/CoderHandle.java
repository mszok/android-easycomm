package android.firefly.coltfashion.cc.netcomm.socketcomm.example.coder;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.ICodeDealer;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.IPacketFactory;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.IPacketHead;
import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;

public class CoderHandle extends FireflyObjectBase implements ICodeDealer {

    static public final String TAG = "CoderHandle";

    @Override
    public boolean encoder(Object obj, BufferCoder buffer) {

        if (!(obj instanceof SendPacketBase)) {
            Loger.warn(getDescripe(TAG, "encoder"), "encode class wrong");
            return false;
        }

        SendPacketBase packet = (SendPacketBase) obj;


        int beginIndex = buffer.getPosition();
        buffer.setPosition(beginIndex + packet.head.getHeadLength());
        if (packet.body2Buffer(buffer)) {

            int endIndex = buffer.getPosition();
            packet.head.packetLength = (short) buffer.getPosition();

            buffer.setPosition(beginIndex);

            packet.head.encode(buffer);

            buffer.setPosition(endIndex);

            return true;
        }

        return false;


    }

    @Override
    public void headDecoder(BufferCoder buffer, IPacketHead head) {

        head.decode(buffer);

    }

    @Override
    public Object bodyDecoder(BufferCoder buffer, IPacketHead head) {

        if (!(head instanceof PacketHead)) {
            Loger.warn(getDescripe(TAG,"bodyDecoder"), "head wrong");
            return null;
        }

        PacketHead packetHead = (PacketHead) head;

        Object obj = getPacketFactory().getPacket(packetHead.msgCode);

        if (!(obj instanceof ReceivePacketBase)) {
            Loger.warn(getDescripe(TAG,"bodyDecoder"), String.format(
                    "receive packet not register:%d,%d", packetHead.funFlag,
                    packetHead.msgCode));
            return null;
        }

        ReceivePacketBase packet = (ReceivePacketBase) obj;
        packet.head = packetHead;

        if (packet.buffer2Body(buffer)) {

            return packet;

        } else {
            Loger.warn("CoderHandle.bodyDecoder", String.format(
                    "parse packet body wrong:%d,%d", packetHead.funFlag, packetHead.msgCode));

            return null;
        }

    }

    @Override
    public IPacketFactory getPacketFactory() {

        return PacketFactory.inst();

    }

    @Override
    public void exheadDecoder(BufferCoder buffer, IPacketHead head) {

    }

}
