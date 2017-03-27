package android.firefly.coltfashion.cc.netcomm.http.okhttp.example;


import android.firefly.coltfashion.cc.netcomm.http.okhttp.JsonHttpTaskBase;
import android.firefly.coltfashion.cc.netcomm.json.protocol.JsonProtocolReceiveBase;
import android.firefly.coltfashion.cc.netcomm.json.protocol.example.PSA0001;

/**
 * Created by msz on 2017/3/24.
 */

public class JsonHttpTask0001 extends JsonHttpTaskBase {

    public JsonHttpTask0001(PSA0001 psa0001, String url){
        super(psa0001,url);
    }

    @Override
    public void onReceivePacket(JsonProtocolReceiveBase packet) {

    }

    @Override
    public void onError(int code, String errorMsg) {

    }

}
