package android.firefly.coltfashion.cc.netcomm.json.protocol.example;

import org.json.JSONException;
import org.json.JSONObject;

public class PSA0001 extends ProtocolSend {

    public String name;

    public String mark;

    public String machineCode;


    public PSA0001() {
        msgCode = "A0001";
    }


    @Override
    public void body2Json(JSONObject jsonObject) throws JSONException {

        jsonObject.put("Name", name);
        jsonObject.put("Mark", mark);
        jsonObject.put("MachineCode", machineCode);

    }
}
