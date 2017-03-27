package android.firefly.coltfashion.cc.netcomm.socketcomm.example.coder;

import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.IPacketHead;

public class PacketHead implements IPacketHead {

    public static final byte HEAD_SIZE = 0x05;

    public static final byte HEAD_FLAG = 0x7e;

    public static final byte HEAD_FUN_QUERY = 0x00;

    public static final byte HEAD_FUN_QUERYRESPONSE = 0x01;

    public static final byte HEAD_FUN_SET = 0x03;

    public static final byte HEAD_FUN_SETRESPONSE = 0x04;

    public byte headFlag;

    public byte funFlag;

    public short packetLength;

    public byte msgCode;

    public boolean checkReceiveBody() {

        return (funFlag == HEAD_FUN_QUERYRESPONSE || funFlag == HEAD_FUN_SETRESPONSE);
    }

    public String getHeadDescripe() {

        return String.format(
                "headFlag:%d,funFlag:%d,packetLength:%d,msgCode:%d", headFlag,
                funFlag, packetLength, msgCode);

    }

    @Override
    public int getHeadLength() {

        return HEAD_SIZE;
    }

    @Override
    public int getBodyLength() {

        return packetLength;
    }

    @Override
    public void decode(BufferCoder buffer) {


        this.headFlag = buffer.getByte();
        this.funFlag = buffer.getByte();

        this.msgCode = buffer.getByte();
        this.packetLength = buffer.getShort();

    }

    @Override
    public void encode(BufferCoder buffer) {


        buffer.putByte(headFlag);

        buffer.putByte(funFlag);

        buffer.putByte(msgCode);
        buffer.putShort(packetLength);
    }

    @Override
    public int getExheadLength() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setExtHead(byte[] exhead) {
        // TODO Auto-generated method stub

    }

    @Override
    public byte[] getExhead() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void decodeExhead(BufferCoder buffer) {
        // TODO Auto-generated method stub

    }

}
