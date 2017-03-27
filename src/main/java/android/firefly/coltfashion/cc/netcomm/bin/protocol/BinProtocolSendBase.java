package android.firefly.coltfashion.cc.netcomm.bin.protocol;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;
import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;


public abstract class BinProtocolSendBase extends FireflyObjectBase {

    public static final String TAG = "BinProtocolSendBase";


    public abstract void head2Buffer(BufferCoder buffer);

    public abstract void body2Buffer(BufferCoder buffer);

    public boolean obj2Buffer(BufferCoder buffer) {

        try {

            head2Buffer(buffer);

            body2Buffer(buffer);

            return true;

        } catch (Exception ex) {

            Loger.error(getDescripe(TAG,"obj2Buffer"), ex);

            return false;

        }


    }



}
