package android.firefly.coltfashion.cc.netcomm.json.protocol;

import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class JsonProtocolReceiveBase extends FireflyObjectBase {

    public abstract void json2Body(JSONObject jsonObject) throws JSONException;

}
