package android.firefly.coltfashion.cc.netcomm.bin.protocol.example;

import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;

public class PSA0001 extends ProtocolSend {

    public String name;

    public String mark;

    public String machineCode;


    public PSA0001() {
        msgCode = "A0001";
    }


    @Override
    public void body2Buffer(BufferCoder buffer) {

        buffer.putString(name);
        buffer.putString(mark);
        buffer.putString(machineCode);

    }
}
