package android.firefly.coltfashion.cc.netcomm.bin.protocol.example;


import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;

public class PRA0001 extends ProtocolReceive {

    public String terminalCode;

    @Override
    public void buffer2Body(BufferCoder buffer) {
        terminalCode = buffer.getString();
    }
}
