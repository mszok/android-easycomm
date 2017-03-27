package android.firefly.coltfashion.cc.netcomm.socketcomm.tcp;

import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;

public interface ICodeDealer {

    void headDecoder(BufferCoder buffer, IPacketHead head);

    void exheadDecoder(BufferCoder buffer, IPacketHead head);

    Object bodyDecoder(BufferCoder buffer, IPacketHead head);

    boolean encoder(Object obj, BufferCoder buffer);

    IPacketFactory getPacketFactory();
}
