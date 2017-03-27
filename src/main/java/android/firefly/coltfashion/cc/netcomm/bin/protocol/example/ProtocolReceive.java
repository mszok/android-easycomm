package android.firefly.coltfashion.cc.netcomm.bin.protocol.example;

import android.firefly.coltfashion.cc.netcomm.bin.protocol.BinProtocolReceiveBase;


public abstract class ProtocolReceive extends BinProtocolReceiveBase {

    //this fields is head information
    public String msgCode;
    public int returnCode;
    public long time;


}
