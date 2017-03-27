package android.firefly.coltfashion.cc.netcomm.socketcomm.example.event;

import android.os.SystemClock;

import android.firefly.coltfashion.cc.netcomm.socketcomm.example.coder.ReceivePacketBase;
import android.firefly.coltfashion.cc.netcomm.socketcomm.example.coder.SendPacketBase;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.IPacketEvent;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.SocketSession;

public abstract class PacketEventBase implements IPacketEvent {

    protected ReceivePacketBase mReceivePacket;

    protected SendPacketBase mSendPacket;

    protected long mSendPacketTime = -1;

    protected long mReceivePacketTime = -1;

    protected boolean mSendSuccessful = false;

    @Override
    public int getMsgCode() {

        if (mSendPacket != null) {

            return mSendPacket.head.msgCode;

        } else {

            if (mReceivePacket != null) {

                return mReceivePacket.head.msgCode;

            }

            return -1;
        }

    }

    @Override
    public long getSerialNo() {

        return -1;

    }

    @Override
    public boolean sendSuccessful() {

        return mSendSuccessful;

    }

    @Override
    public Object getSendPacket() {

        return mSendPacket;
    }

    @Override
    public void setSendPacket(Object sendPacket) {

        mSendPacket = (SendPacketBase) sendPacket;

    }

    @Override
    public ReceivePacketBase getReceivePacket() {

        return mReceivePacket;
    }

    @Override
    public void setReceivePacket(Object receivePacket) {

        mReceivePacket = (ReceivePacketBase) receivePacket;
        mReceivePacketTime = SystemClock.elapsedRealtime();
    }

    @Override
    public long sendPacketTime() {

        return mSendPacketTime;
    }

    @Override
    public long receivePacketTime() {

        return mReceivePacketTime;
    }

    @Override
    public boolean sendPacketDealer(SocketSession session) {

        SessionEventCollection.inst().putEventDealer(this);

        if (session.sendObj(this)) {
            mSendPacketTime = SystemClock.elapsedRealtime();
            return true;
        } else {
            SessionEventCollection.inst().pollEventDealer();
            return false;
        }

    }

    @Override
    public abstract String getTradeDescripe();

    @Override
    public abstract void mainThreadReceivePacketDealer(
            SocketSession receivePacket);

    @Override
    public abstract void receivePacketDealer(SocketSession receivePacket);

}
