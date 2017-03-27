package android.firefly.coltfashion.cc.netcomm.json.protocol;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class JsonProtocolSendBase extends FireflyObjectBase implements IProtocolTag {

    static public final String TAG = "JsonProtocolSendBase";

    public abstract void head2Json(JSONObject jsonObject) throws JSONException;

    public abstract void body2Json(JSONObject jsonObject) throws JSONException;

    public boolean obj2Json(JSONObject jsonObject) {

        try {

            JSONObject jsonObject1 = new JSONObject();
            head2Json(jsonObject1);

            jsonObject.put(getHeadTag(), jsonObject1);

            JSONObject jsonObject2 = new JSONObject();

            body2Json(jsonObject2);
            jsonObject.put(getBodyTag(), jsonObject2);
            return true;

        } catch (JSONException ex) {

            Loger.error(getDescripe(TAG,"obj2Json"), ex);

            return false;

        }


    }


}
