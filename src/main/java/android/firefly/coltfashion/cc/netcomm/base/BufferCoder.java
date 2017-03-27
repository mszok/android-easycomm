package android.firefly.coltfashion.cc.netcomm.base;

import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by msz on 2015/9/10.
 */

//thread not safe, single thread use this class
//decoder and encoder bin buffer

public class BufferCoder {

    private Charset mStringCharset;

    private BufferDef mBuffer;

    private ByteOrder mByteOrderType;

    public BufferCoder(byte[] buff) {
        mByteOrderType = ByteOrder.nativeOrder();
        mStringCharset = Charset.defaultCharset();
        mBuffer = new BufferDef();
        mBuffer.buffer = buff;
        mBuffer.posIndex = 0;
    }

    public void setByteOrder(ByteOrder bType) {
        mByteOrderType = bType;
    }

    public void setCharset(Charset charset) {
        mStringCharset = charset;
    }

    public void setBuffer(byte[] buff) {

        mBuffer.buffer = buff;
        mBuffer.posIndex = 0;
    }

    public boolean getBoolean() {
        int index = mBuffer.posIndex;
        boolean re = mBuffer.buffer[index] != 0;
        mBuffer.posIndex++;
        return re;
    }

    public byte getByte() {
        int index = mBuffer.posIndex;
        byte re = mBuffer.buffer[index];
        mBuffer.posIndex++;
        return re;
    }

    public char getChar() {
        int index = mBuffer.posIndex;
        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {
            char re = (char) (((mBuffer.buffer[index + 1] & 0xFF) << 0) + ((mBuffer.buffer[index + 0]) << 8));
            mBuffer.posIndex += 2;
            return re;
        } else {
            char re = (char) (((mBuffer.buffer[index + 0] & 0xFF) << 0) + ((mBuffer.buffer[index + 1]) << 8));
            mBuffer.posIndex += 2;
            return re;
        }

    }

    public short getShort() {
        int index = mBuffer.posIndex;
        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {
            short re = (short) (((mBuffer.buffer[index + 1] & 0xFF) << 0) + ((mBuffer.buffer[index + 0]) << 8));
            mBuffer.posIndex += 2;
            return re;
        } else {
            short re = (short) (((mBuffer.buffer[index + 0] & 0xFF) << 0) + ((mBuffer.buffer[index + 1]) << 8));
            mBuffer.posIndex += 2;
            return re;
        }

    }

    public int getInt() {
        int index = mBuffer.posIndex;
        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {
            int re = ((mBuffer.buffer[index + 3] & 0xFF) << 0)
                    + ((mBuffer.buffer[index + 2] & 0xFF) << 8)
                    + ((mBuffer.buffer[index + 1] & 0xFF) << 16)
                    + ((mBuffer.buffer[index + 0] & 0xFF) << 24);
            mBuffer.posIndex += 4;
            return re;
        } else {
            int re = ((mBuffer.buffer[index + 0] & 0xFF) << 0)
                    + ((mBuffer.buffer[index + 1] & 0xFF) << 8)
                    + ((mBuffer.buffer[index + 2] & 0xFF) << 16)
                    + ((mBuffer.buffer[index + 3] & 0xFF) << 24);
            mBuffer.posIndex += 4;
            return re;
        }

    }

    public float getFloat() {
        int index = mBuffer.posIndex;
        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {
            int re = ((mBuffer.buffer[index + 3] & 0xFF) << 0)
                    + ((mBuffer.buffer[index + 2] & 0xFF) << 8)
                    + ((mBuffer.buffer[index + 1] & 0xFF) << 16)
                    + ((mBuffer.buffer[index + 0] & 0xFF) << 24);
            mBuffer.posIndex += 4;
            return Float.intBitsToFloat(re);
        } else {
            int re = ((mBuffer.buffer[index + 0] & 0xFF) << 0)
                    + ((mBuffer.buffer[index + 1] & 0xFF) << 8)
                    + ((mBuffer.buffer[index + 2] & 0xFF) << 16)
                    + ((mBuffer.buffer[index + 3] & 0xFF) << 24);
            mBuffer.posIndex += 4;
            return Float.intBitsToFloat(re);
        }
    }

    public long getLong() {
        int index = mBuffer.posIndex;
        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {
            long re = ((mBuffer.buffer[index + 7] & 0xFFL) << 0)
                    + ((mBuffer.buffer[index + 6] & 0xFFL) << 8)
                    + ((mBuffer.buffer[index + 5] & 0xFFL) << 16)
                    + ((mBuffer.buffer[index + 4] & 0xFFL) << 24)
                    + ((mBuffer.buffer[index + 3] & 0xFFL) << 32)
                    + ((mBuffer.buffer[index + 2] & 0xFFL) << 40)
                    + ((mBuffer.buffer[index + 1] & 0xFFL) << 48)
                    + ((mBuffer.buffer[index + 0] & 0xFFL) << 56);
            mBuffer.posIndex += 8;
            return re;
        } else {
            long re = ((mBuffer.buffer[index + 0] & 0xFFL) << 0)
                    + ((mBuffer.buffer[index + 1] & 0xFFL) << 8)
                    + ((mBuffer.buffer[index + 2] & 0xFFL) << 16)
                    + ((mBuffer.buffer[index + 3] & 0xFFL) << 24)
                    + ((mBuffer.buffer[index + 4] & 0xFFL) << 32)
                    + ((mBuffer.buffer[index + 5] & 0xFFL) << 40)
                    + ((mBuffer.buffer[index + 6] & 0xFFL) << 48)
                    + ((mBuffer.buffer[index + 7] & 0xFFL) << 56);
            mBuffer.posIndex += 8;
            return re;
        }
    }

    public double getDouble() {
        int index = mBuffer.posIndex;
        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {
            long re = ((mBuffer.buffer[index + 7] & 0xFFL) << 0)
                    + ((mBuffer.buffer[index + 6] & 0xFFL) << 8)
                    + ((mBuffer.buffer[index + 5] & 0xFFL) << 16)
                    + ((mBuffer.buffer[index + 4] & 0xFFL) << 24)
                    + ((mBuffer.buffer[index + 3] & 0xFFL) << 32)
                    + ((mBuffer.buffer[index + 2] & 0xFFL) << 40)
                    + ((mBuffer.buffer[index + 1] & 0xFFL) << 48)
                    + ((mBuffer.buffer[index + 0] & 0xFFL) << 56);
            mBuffer.posIndex += 8;
            return Double.longBitsToDouble(re);
        } else {
            long re = ((mBuffer.buffer[index + 0] & 0xFFL) << 0)
                    + ((mBuffer.buffer[index + 1] & 0xFFL) << 8)
                    + ((mBuffer.buffer[index + 2] & 0xFFL) << 16)
                    + ((mBuffer.buffer[index + 3] & 0xFFL) << 24)
                    + ((mBuffer.buffer[index + 4] & 0xFFL) << 32)
                    + ((mBuffer.buffer[index + 5] & 0xFFL) << 40)
                    + ((mBuffer.buffer[index + 6] & 0xFFL) << 48)
                    + ((mBuffer.buffer[index + 7] & 0xFFL) << 56);
            mBuffer.posIndex += 8;
            return Double.longBitsToDouble(re);
        }
    }

    public String getString() {

        short len = getShort();

        if (len == 0)
            return "";
        if (len < 0)
            return null;


        String str = new String(mBuffer.buffer, mBuffer.posIndex,
                mBuffer.posIndex + len, mStringCharset);

        mBuffer.posIndex += len;

        return str;
    }

    public byte[] getBytes() {

        short len = getShort();

        if (len < 0)
            return null;
        else if (len == 0)
            return new byte[0];

        byte[] reBuff = Arrays.copyOfRange(mBuffer.buffer, mBuffer.posIndex,
                mBuffer.posIndex + len);

        mBuffer.posIndex += len;

        return reBuff;
    }

    public byte[] getFixBytes(int len) {
        byte[] reBuff = Arrays.copyOfRange(mBuffer.buffer, mBuffer.posIndex,
                mBuffer.posIndex + len);
        mBuffer.posIndex += len;
        return reBuff;
    }

    public void putBoolean(boolean val) {
        mBuffer.buffer[mBuffer.posIndex] = (byte) (val ? 1 : 0);
        mBuffer.posIndex++;
    }

    public void putByte(byte val) {
        mBuffer.buffer[mBuffer.posIndex] = val;
        mBuffer.posIndex++;
    }

    public void putChar(char val) {

        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {

            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (val >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (val >>> 8);

            mBuffer.posIndex += 2;
        } else {
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (val >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (val >>> 8);

            mBuffer.posIndex += 2;
        }

    }

    public void putShort(short val) {
        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (val >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (val >>> 8);

            mBuffer.posIndex += 2;

        } else {
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (val >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (val >>> 8);

            mBuffer.posIndex += 2;
        }
    }

    public void putInt(int val) {
        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {
            mBuffer.buffer[mBuffer.posIndex + 3] = (byte) (val >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 2] = (byte) (val >>> 8);
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (val >>> 16);
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (val >>> 24);
            mBuffer.posIndex += 4;
        } else {
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (val >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (val >>> 8);
            mBuffer.buffer[mBuffer.posIndex + 2] = (byte) (val >>> 16);
            mBuffer.buffer[mBuffer.posIndex + 3] = (byte) (val >>> 24);
            mBuffer.posIndex += 4;
        }

    }

    public void putFloat(float val) {
        int bitValue = Float.floatToIntBits(val);
        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {

            mBuffer.buffer[mBuffer.posIndex + 3] = (byte) (bitValue >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 2] = (byte) (bitValue >>> 8);
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (bitValue >>> 16);
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (bitValue >>> 24);
            mBuffer.posIndex += 4;
        } else {

            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (bitValue >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (bitValue >>> 8);
            mBuffer.buffer[mBuffer.posIndex + 2] = (byte) (bitValue >>> 16);
            mBuffer.buffer[mBuffer.posIndex + 3] = (byte) (bitValue >>> 24);
            mBuffer.posIndex += 4;
        }

    }

    public void putLong(long val) {

        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {
            mBuffer.buffer[mBuffer.posIndex + 7] = (byte) (val >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 6] = (byte) (val >>> 8);
            mBuffer.buffer[mBuffer.posIndex + 5] = (byte) (val >>> 16);
            mBuffer.buffer[mBuffer.posIndex + 4] = (byte) (val >>> 24);
            mBuffer.buffer[mBuffer.posIndex + 3] = (byte) (val >>> 32);
            mBuffer.buffer[mBuffer.posIndex + 2] = (byte) (val >>> 40);
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (val >>> 48);
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (val >>> 56);
            mBuffer.posIndex += 8;
        } else {
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (val >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (val >>> 8);
            mBuffer.buffer[mBuffer.posIndex + 2] = (byte) (val >>> 16);
            mBuffer.buffer[mBuffer.posIndex + 3] = (byte) (val >>> 24);
            mBuffer.buffer[mBuffer.posIndex + 4] = (byte) (val >>> 32);
            mBuffer.buffer[mBuffer.posIndex + 5] = (byte) (val >>> 40);
            mBuffer.buffer[mBuffer.posIndex + 6] = (byte) (val >>> 48);
            mBuffer.buffer[mBuffer.posIndex + 7] = (byte) (val >>> 56);
            mBuffer.posIndex += 8;
        }

    }

    public void putDouble(double val) {
        long bitValue = Double.doubleToLongBits(val);
        if (mByteOrderType == ByteOrder.BIG_ENDIAN) {
            mBuffer.buffer[mBuffer.posIndex + 7] = (byte) (bitValue >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 6] = (byte) (bitValue >>> 8);
            mBuffer.buffer[mBuffer.posIndex + 5] = (byte) (bitValue >>> 16);
            mBuffer.buffer[mBuffer.posIndex + 4] = (byte) (bitValue >>> 24);
            mBuffer.buffer[mBuffer.posIndex + 3] = (byte) (bitValue >>> 32);
            mBuffer.buffer[mBuffer.posIndex + 2] = (byte) (bitValue >>> 40);
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (bitValue >>> 48);
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (bitValue >>> 56);
            mBuffer.posIndex += 8;
        } else {
            mBuffer.buffer[mBuffer.posIndex + 0] = (byte) (bitValue >>> 0);
            mBuffer.buffer[mBuffer.posIndex + 1] = (byte) (bitValue >>> 8);
            mBuffer.buffer[mBuffer.posIndex + 2] = (byte) (bitValue >>> 16);
            mBuffer.buffer[mBuffer.posIndex + 3] = (byte) (bitValue >>> 24);
            mBuffer.buffer[mBuffer.posIndex + 4] = (byte) (bitValue >>> 32);
            mBuffer.buffer[mBuffer.posIndex + 5] = (byte) (bitValue >>> 40);
            mBuffer.buffer[mBuffer.posIndex + 6] = (byte) (bitValue >>> 48);
            mBuffer.buffer[mBuffer.posIndex + 7] = (byte) (bitValue >>> 56);
            mBuffer.posIndex += 8;
        }

    }

    public void putString(String val) {

        short len = -1;
        byte[] buff = null;

        if ("".equals(val))
            len = 0;
        else if (val != null) {
            buff = val.getBytes(mStringCharset);
            len = (short) buff.length;
        }

        putShort(len);

        if (len > 0) {
            System.arraycopy(buff, 0, mBuffer.buffer, mBuffer.posIndex,
                    len);
            mBuffer.posIndex += len;
        }
    }

    public void putBytes(byte[] val) {
        short len = -1;
        if (val != null)
            len = (short) val.length;

        putShort(len);

        if (len > 0) {
            System.arraycopy(val, 0, mBuffer.buffer, mBuffer.posIndex,
                    len);
            mBuffer.posIndex += len;
        }
    }

    public void putBytes(byte[] val, int pos, int putlen) {
        short len = -1;
        if ((val != null) && (pos >= 0) && (val.length - pos >= putlen))
            len = (short) putlen;

        putShort(len);

        if (len > 0) {
            System.arraycopy(val, pos, mBuffer.buffer, mBuffer.posIndex, len);
            mBuffer.posIndex += len;
        }
    }

    public void putFixBytes(byte[] val) {
        int len = -1;
        if (val != null)
            len = val.length;

        if (len > 0) {
            System.arraycopy(val, 0, mBuffer.buffer, mBuffer.posIndex,
                    val.length);
            mBuffer.posIndex += len;
        }
    }

    public void putFixBytes(byte[] val, int pos, int putLen) {

        int len = -1;
        if ((val != null) && (pos >= 0) && (val.length - pos >= putLen))
            len = putLen;

        if (len > 0) {
            System.arraycopy(val, pos, mBuffer.buffer, mBuffer.posIndex, len);
            mBuffer.posIndex += len;
        }

    }

    public byte[] getEncodeBuff() {

        byte[] enCodeBuffer = new byte[mBuffer.posIndex];
        System.arraycopy(mBuffer.buffer, 0, enCodeBuffer, 0,
                enCodeBuffer.length);

        return enCodeBuffer;
    }

    public int getPosition() {

        if (mBuffer != null)
            return mBuffer.posIndex;
        else
            return -1;

    }

    public void setPosition(int pos) {

        if ((mBuffer != null) && (mBuffer.buffer != null)) {
            if (mBuffer.buffer.length > pos)
                mBuffer.posIndex = pos;
        }

    }

    public BufferDef getBuffer() {

        return mBuffer;
    }

    public void releaseBufferToPool() {

        if ((mBuffer != null) && (mBuffer.buffer != null)) {
            ByteBufferPool.releasePoolBuffer(mBuffer.buffer);
            mBuffer = null;
        }
    }

    static public BufferCoder newBuffer() {

        return newBuffer(ByteBufferPool.MAX_BUFFERSIZE);

    }

    static public BufferCoder newBuffer(int size) {

        BufferCoder buffer = new BufferCoder(ByteBufferPool.getPoolBuffer(size));
        return buffer;

    }

    static public void disposeBuffer(BufferCoder buffer) {

        if (buffer != null)
            buffer.releaseBufferToPool();


    }

    public class BufferDef {

        public byte[] buffer;
        public int posIndex;


    }


}
