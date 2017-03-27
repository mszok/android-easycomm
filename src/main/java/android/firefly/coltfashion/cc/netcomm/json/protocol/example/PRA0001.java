package android.firefly.coltfashion.cc.netcomm.json.protocol.example;


import org.json.JSONException;
import org.json.JSONObject;

public class PRA0001 extends ProtocolReceive {

    public String terminalCode;

    @Override
    public void json2Body(JSONObject jsonObject) throws JSONException {

        terminalCode = jsonObject.getString("terminalCode");
    }
}
