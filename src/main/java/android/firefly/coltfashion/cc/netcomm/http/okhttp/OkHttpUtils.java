package android.firefly.coltfashion.cc.netcomm.http.okhttp;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by msz on 2015/9/25.
 */

public class OkHttpUtils {

    static public final String TAG = "OkHttpUtils";

    static public final int ERRORCODE_PACKETNULL = -1;

    static public final int ERRORCODE_SENDERROR = -2;

    static public final int ERRORCODE_EXPLAINERROR = -3;

    static private final OkHttpClient _OkHttpClient = new OkHttpClient();

    static private AtomicInteger _RequestingCount = new AtomicInteger(0);

    static public void httpPostRequest(final IHttpTaskBase httpTask) {

        _RequestingCount.incrementAndGet();
        Request request = httpTask.getRequest();

        if (request == null) {
            httpTask.onError(ERRORCODE_PACKETNULL, "request is null");
            _RequestingCount.decrementAndGet();

            return;
        }

        _OkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                httpTask.onError(ERRORCODE_SENDERROR, e.getMessage());
                _RequestingCount.decrementAndGet();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response != null && response.isSuccessful()) {

                    httpTask.onResponseData(response);
                } else {
                    httpTask.onError(response.code(), response.message());
                }

                _RequestingCount.decrementAndGet();
            }
        });

    }

    static public int getRequestingCount() {

        return _RequestingCount.get();

    }

}
