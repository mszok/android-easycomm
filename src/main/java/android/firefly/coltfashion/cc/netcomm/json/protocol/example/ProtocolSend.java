package android.firefly.coltfashion.cc.netcomm.json.protocol.example;

import android.firefly.coltfashion.cc.netcomm.json.protocol.JsonProtocolSendBase;
import android.firefly.coltfashion.cc.netcomm.util.StringUtil;

import org.json.JSONException;
import org.json.JSONObject;


public abstract class ProtocolSend extends JsonProtocolSendBase {


    public String session = StringUtil.EMPTYSTR;
    public String msgCode = StringUtil.EMPTYSTR;
    public long time = System.currentTimeMillis();


    public void head2Json(JSONObject jsonObject) throws JSONException {

        jsonObject.put("MsgCode", msgCode);
        jsonObject.put("Session", session);
        jsonObject.put("Time", time);

    }


    @Override
    public String getHeadTag() {
        return "Head";
    }

    @Override
    public String getBodyTag() {
        return "Body";
    }


}
