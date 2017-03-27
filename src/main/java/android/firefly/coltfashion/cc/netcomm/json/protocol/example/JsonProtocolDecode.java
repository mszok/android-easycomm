package android.firefly.coltfashion.cc.netcomm.json.protocol.example;

import android.firefly.coltfashion.cc.netcomm.json.protocol.JsonProtocolDecodeBase;
import android.firefly.coltfashion.cc.netcomm.json.protocol.JsonProtocolReceiveBase;

import org.json.JSONObject;


public class JsonProtocolDecode extends JsonProtocolDecodeBase {


    static {

        JsonProtocolDecodeBase.setJsonDecoder(new JsonProtocolDecode());

    }


    @Override
    public String getHeadTag() {
        return "Head";
    }

    @Override
    public String getBodyTag() {
        return "Body";
    }

    @Override
    protected JsonProtocolReceiveBase json2Head(JSONObject jsonObject) {
        try {

            String msgCode = jsonObject.getString("MsgCode");

            ProtocolReceive receiveBase = (ProtocolReceive) ReceiveProtocolDict.inst().getEntryObj(msgCode);
            if (receiveBase != null) {

                int returnCode = jsonObject.getInt("ReturnCode");
                long time = jsonObject.getLong("Time");

                receiveBase.msgCode = msgCode;
                receiveBase.returnCode = returnCode;
                receiveBase.time = time;
            }
            return receiveBase;

        } catch (Exception ex) {

            return null;
        }

    }
}
