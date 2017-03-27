package android.firefly.coltfashion.cc.netcomm.socketcomm.tcp;

import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;

public interface IPacketHead {

    int ERROR_LENGTH = -1;

    int getHeadLength();

    int getExheadLength();

    byte[] getExhead();

    void setExtHead(byte[] exhead);

    int getBodyLength();

    void decode(BufferCoder buffer);

    void decodeExhead(BufferCoder buffer);

    void encode(BufferCoder buffer);
}
