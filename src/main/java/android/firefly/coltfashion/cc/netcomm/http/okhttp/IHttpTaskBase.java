package android.firefly.coltfashion.cc.netcomm.http.okhttp;

import java.io.IOException;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by msz on 2017/3/23.
 */

public interface IHttpTaskBase {


     Request getRequest();

     void onResponseData(Response response) throws IOException;

     void onError(int code, String msg);

     boolean excuteHttpTask();
}
