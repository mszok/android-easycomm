package android.firefly.coltfashion.cc.netcomm.bin.protocol.example;

import android.firefly.coltfashion.cc.netcomm.bin.protocol.BinProtocolSendBase;
import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;
import android.firefly.coltfashion.cc.netcomm.util.StringUtil;


public abstract class ProtocolSend extends BinProtocolSendBase {


    public String msgCode = StringUtil.EMPTYSTR;
    public String session = StringUtil.EMPTYSTR;
    public long time = System.currentTimeMillis();


    @Override
    public void head2Buffer(BufferCoder buffer) {

        buffer.putString(msgCode);
        buffer.putString(session);
        buffer.putLong(time);

    }


}
