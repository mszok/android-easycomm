package android.firefly.coltfashion.cc.netcomm.socketcomm.example.event;

import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.IPacketEvent;

public class SessionEventCollection {

    // private HashMap<Long, IPacketEvent> mSessionEventMap = new HashMap<Long,
    // IPacketEvent>();

    static private SessionEventCollection _SessionEventCollection;

    static {

        _SessionEventCollection = new SessionEventCollection();

    }

    private IPacketEvent mCurrentPacketEvent;

    static public SessionEventCollection inst() {

        return _SessionEventCollection;
    }

    public synchronized IPacketEvent pollEventDealer() {

        IPacketEvent reEvent = mCurrentPacketEvent;
        mCurrentPacketEvent = null;
        return reEvent;

    }

    public synchronized IPacketEvent getEventDealer() {

        return mCurrentPacketEvent;

    }

    public synchronized void putEventDealer(IPacketEvent eventDealer) {

        mCurrentPacketEvent = eventDealer;

    }

    public synchronized boolean existDealer() {

        return mCurrentPacketEvent != null;

    }

    public synchronized void clearSessionMap() {

        mCurrentPacketEvent = null;

    }

}
