package android.firefly.coltfashion.cc.netcomm.bin.protocol;

import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;
import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;


public abstract class BinProtocolDecodeBase extends FireflyObjectBase {

    static public final String TAG = "BinProtocolDecodeBase";

    static private BinProtocolDecodeBase _BinDecoder;

    static public void setBinDecoder(BinProtocolDecodeBase decoder) {

        _BinDecoder = decoder;

    }

    static public BinProtocolDecodeBase getBinDecoder() {

        return _BinDecoder;
    }

    static public BinProtocolReceiveBase bin2Obj(byte[] binBuffer) {

            if (_BinDecoder==null) return null;

            BufferCoder buffer = new BufferCoder(binBuffer);

            BinProtocolReceiveBase binProtocolReceiveBase = _BinDecoder.bin2Head(buffer);

            if (binProtocolReceiveBase != null) {

                binProtocolReceiveBase.buffer2Body(buffer);

            }

            return binProtocolReceiveBase;

    }




    protected abstract BinProtocolReceiveBase bin2Head(BufferCoder buffer);


}
