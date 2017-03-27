package android.firefly.coltfashion.cc.netcomm.bin.protocol;

import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;
import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;


public abstract class BinProtocolReceiveBase extends FireflyObjectBase {


    public abstract void buffer2Body(BufferCoder buffer);



}
