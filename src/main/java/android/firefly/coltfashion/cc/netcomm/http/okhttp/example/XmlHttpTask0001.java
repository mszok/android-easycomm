package android.firefly.coltfashion.cc.netcomm.http.okhttp.example;


import android.firefly.coltfashion.cc.netcomm.http.okhttp.XmlHttpTaskBase;
import android.firefly.coltfashion.cc.netcomm.xml.protocol.XmlProtocolReceiveBase;
import android.firefly.coltfashion.cc.netcomm.xml.protocol.example.PSA0001;

/**
 * Created by msz on 2017/3/24.
 */

public class XmlHttpTask0001 extends XmlHttpTaskBase {

    public XmlHttpTask0001(PSA0001 psa0001, String url){
        super(psa0001,url);
    }

    @Override
    public void onReceivePacket(XmlProtocolReceiveBase packet) {

        //to do
    }

    @Override
    public void onError(int code, String errorMsg) {

        //to do
    }

}
