package android.firefly.coltfashion.cc.netcomm.socketcomm.example.event;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;
import android.firefly.coltfashion.cc.netcomm.socketcomm.example.coder.ReceivePacketBase;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.IEventListener;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.IPacketEvent;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.SocketSession;

public class ListenerHandle extends FireflyObjectBase implements IEventListener {

    static public final String TAG = "ListenerHandle";

    @Override
    public void connected(SocketSession connectObj) {

        Loger.debug(getDescripe(TAG,"connected"), "socket connect ok");


    }

    @Override
    public void closed(SocketSession connectObj) {

        Loger.warn(getDescripe(TAG,"closed"), "socket close ok");


    }

    @Override
    public void receive(Object recePacket, SocketSession connectObj) {

        if (!(recePacket instanceof ReceivePacketBase)) {

            Loger.warn(getDescripe(TAG,"receive"), "receive wrong ,packet type error");
            return;
        }

        ReceivePacketBase receivePacket = (ReceivePacketBase) recePacket;

        Loger.debug(getDescripe(TAG,"receive"),
                "receive packet:" + receivePacket.getPackageDescripe());

        if (receivePacket.head == null) {
            Loger.warn(getDescripe(TAG,"receive"), "receive wrong, head is null");
            return;
        }

        if (!receivePacket.head.checkReceiveBody()) {
            Loger.warn(getDescripe(TAG,"receive"), "receive wrong, head data error," + receivePacket.head.getHeadDescripe());

            return;
        }

        IPacketEvent pe = SessionEventCollection.inst().pollEventDealer();

        if (pe == null) {
            Loger.warn(getDescripe(TAG,"receive"), String.format(
                    "not find dealer,funFlag:%d msgCode:%d", receivePacket.head.funFlag,
                    receivePacket.head.msgCode));
            return;
        }

        pe.setReceivePacket(receivePacket);
        try {

            pe.receivePacketDealer(connectObj);

        } catch (Throwable ex) {

            Loger.error(getDescripe(TAG,"receive"), ex);
        }

    }

}
