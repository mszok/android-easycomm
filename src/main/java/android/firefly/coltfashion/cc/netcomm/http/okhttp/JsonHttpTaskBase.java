package android.firefly.coltfashion.cc.netcomm.http.okhttp;


import android.firefly.coltfashion.cc.netcomm.json.protocol.JsonProtocolDecodeBase;
import android.firefly.coltfashion.cc.netcomm.json.protocol.JsonProtocolReceiveBase;
import android.firefly.coltfashion.cc.netcomm.json.protocol.JsonProtocolSendBase;
import android.firefly.coltfashion.cc.netcomm.util.StringUtil;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by msz on 2015/9/25.
 */
public abstract class JsonHttpTaskBase extends HttpTaskBase {

    static public final String TAG = "JsonHttpTaskBase";

    static public final MediaType MEDIATYPE_JSON = MediaType.parse("application/json;charset=utf-8");

    private JsonProtocolSendBase mJsonSendBody;

    //static public final MediaType MEDIATYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");
    public JsonHttpTaskBase(JsonProtocolSendBase packet, String url){
        super(url);
        setJsonSendBody(packet);
    }


    public void setJsonSendBody(JsonProtocolSendBase body) {
        mJsonSendBody = body;
    }


    @Override
    public void onResponseData(Response response) {

        String responseText = response.body().toString();

        JsonProtocolReceiveBase packet = JsonProtocolDecodeBase.json2Obj(responseText);

        if (packet != null) {
            onReceivePacket(packet);
        } else {
            onError(OkHttpUtils.ERRORCODE_EXPLAINERROR, "parse reponse packet wrong");
        }
    }

    public abstract void onReceivePacket(JsonProtocolReceiveBase packet);


    @Override
    public Request getRequest() {

        if (StringUtil.isNullEmpty(mUrl)) {
            return null;
        }


        JSONObject jsonObject = new JSONObject();

        if (!mJsonSendBody.obj2Json(jsonObject)) {
            return null;
        }
        String content = jsonObject.toString();

        return new Request.Builder().url(mUrl).post(RequestBody.create(MEDIATYPE_JSON, content)).build();

    }

}
