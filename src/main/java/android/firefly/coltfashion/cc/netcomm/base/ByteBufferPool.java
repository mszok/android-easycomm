package android.firefly.coltfashion.cc.netcomm.base;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by msz on 2015/9/10.
 */

// thread safe, max buffer use 0xffff

public class ByteBufferPool {

    public static final int MAX_BUFFERSIZE = 0xffff;

    private static final int[] BUFFSIZE_ARRDEF = new int[]{0x100, 0x400,
            0x1000, 0x4000, 0xffff};


    static private ByteBufferPool _ByteBufferPool;
    private Map<Integer, ConcurrentLinkedQueue<byte[]>> mbufferHMap;

    public ByteBufferPool() {

        mbufferHMap = Collections
                .synchronizedMap(new HashMap<Integer, ConcurrentLinkedQueue<byte[]>>());

        for (int i = 0; i < BUFFSIZE_ARRDEF.length; i++) {
            mbufferHMap.put(BUFFSIZE_ARRDEF[i],
                    new ConcurrentLinkedQueue<byte[]>());
        }
    }

    static public synchronized byte[] getPoolBuffer(int buffSize) {

        if (_ByteBufferPool == null) {
            _ByteBufferPool = new ByteBufferPool();
        }
        return _ByteBufferPool.getBuffer(buffSize);
    }

    static public synchronized void releasePoolBuffer(byte[] buff) {

        if (_ByteBufferPool != null)
            _ByteBufferPool.releaseBuffer(buff);
    }

    private int getUsefulSize(int fitSize) {
        for (int i = 0; i < BUFFSIZE_ARRDEF.length; i++) {
            if (BUFFSIZE_ARRDEF[i] >= fitSize)
                return BUFFSIZE_ARRDEF[i];
        }

        return fitSize;

    }

    public byte[] getBuffer(int buffSize) {
        byte[] buff = null;
        int key = getUsefulSize(buffSize);
        if (mbufferHMap.containsKey(key)) {
            ConcurrentLinkedQueue<byte[]> l = mbufferHMap.get(key);
            buff = l.poll();
        }

        if (buff == null)
            buff = new byte[key];
        return buff;

    }

    public void releaseBuffer(byte[] buff) {
        int key = buff.length;

        if (mbufferHMap.containsKey(key)) {
            ConcurrentLinkedQueue<byte[]> l = mbufferHMap.get(key);
            if (l.size() < 1000)
                l.add(buff);
        }
    }


}
