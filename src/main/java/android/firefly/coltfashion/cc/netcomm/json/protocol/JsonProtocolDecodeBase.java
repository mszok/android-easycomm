package android.firefly.coltfashion.cc.netcomm.json.protocol;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.util.CommonUtil;

import org.json.JSONObject;

public abstract class JsonProtocolDecodeBase implements IProtocolTag {

    static public final String TAG = "JsonProtocolDecodeBase";


    static private JsonProtocolDecodeBase _JsonDecoder;


    static public void setJsonDecoder(JsonProtocolDecodeBase decoder) {

        _JsonDecoder = decoder;

    }

    static public JsonProtocolDecodeBase getJsonDecoder() {

        return _JsonDecoder;
    }

    static public JsonProtocolReceiveBase json2Obj(String jsonProtocolText) {

        try {

            JSONObject jsonObject = new JSONObject(jsonProtocolText);

            JSONObject jsonObject1 = jsonObject.getJSONObject(_JsonDecoder.getHeadTag());

            JsonProtocolReceiveBase protocolReceiveBase = _JsonDecoder.json2Head(jsonObject1);

            if (protocolReceiveBase != null) {
                JSONObject jsonObject2 = jsonObject.getJSONObject(_JsonDecoder.getBodyTag());

                protocolReceiveBase.json2Body(jsonObject2);
            }

            return protocolReceiveBase;

        } catch (Throwable ex) {

            Loger.error(CommonUtil.getMethodDescripe(TAG, "json2Obj"), ex);

            return null;
        }

    }


    protected abstract JsonProtocolReceiveBase json2Head(JSONObject jsonObject);


}
