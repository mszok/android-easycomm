package android.firefly.coltfashion.cc.netcomm.base;

import android.firefly.coltfashion.cc.netcomm.util.CommonUtil;


/**
 * Created by msz on 2015/9/17.
 */
public class FireflyObjectBase implements IFireflyBase {

    static public final String TAG = "FireflyObjectBase";

    private final int mObjId = IncreaseSeed.getSingleNewId();

    public int getObjId() {

        return mObjId;
    }


    public String getDescripe(String tag,String methodName) {

        return CommonUtil.getMethodDescripe(tag,methodName);

    }

}
