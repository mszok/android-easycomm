package android.firefly.coltfashion.cc.netcomm.http.okhttp;

import android.firefly.coltfashion.cc.netcomm.base.IFireflyBase;
import android.firefly.coltfashion.cc.netcomm.base.IncreaseSeed;
import android.firefly.coltfashion.cc.netcomm.util.CommonUtil;

/**
 * Created by msz on 2017/3/24.
 */

public abstract class HttpTaskBase implements IHttpTaskBase, IFireflyBase {

    static public final String TAG = "HttpTaskBase";

    static public final int HTTPTASK_MODE_COMPLEX = 1;

    static public final int HTTPTASK_MODE_SIMPLE = 2;

    static private int _HttpMode = HTTPTASK_MODE_SIMPLE;

    protected String mUrl;

    public HttpTaskBase(String url) {

        setUrl(url);

    }

    private final int mObjId = IncreaseSeed.getSingleNewId();

    public int getObjId() {

        return mObjId;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getDescripe(String tag, String methodName) {

        return CommonUtil.getMethodDescripe(tag, methodName);

    }

    @Override
    public boolean excuteHttpTask() {
        if (_HttpMode == HTTPTASK_MODE_COMPLEX) {
            OkHttpUtils.httpPostRequest(this);
            return true;
        } else if (_HttpMode == HTTPTASK_MODE_SIMPLE) {

            if (OkHttpUtils.getRequestingCount() <= 0) {
                OkHttpUtils.httpPostRequest(this);
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    static public void setHttpMode(int mode) {
        if (mode != _HttpMode) {
            if ((HTTPTASK_MODE_COMPLEX == mode) || (HTTPTASK_MODE_SIMPLE == mode)) {
                _HttpMode = mode;
            }
        }


    }

}
