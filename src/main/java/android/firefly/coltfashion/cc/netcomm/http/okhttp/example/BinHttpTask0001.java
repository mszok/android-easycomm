package android.firefly.coltfashion.cc.netcomm.http.okhttp.example;

import android.firefly.coltfashion.cc.netcomm.bin.protocol.BinProtocolReceiveBase;
import android.firefly.coltfashion.cc.netcomm.bin.protocol.example.PSA0001;
import android.firefly.coltfashion.cc.netcomm.http.okhttp.BinHttpTaskBase;

/**
 * Created by msz on 2017/3/24.
 * new this object and excute excuteHttpTask method
 * for example: BinHttpTask0001 task = new BinHttpTask0001(new PSA0001(),"http://www.baidu.com/api")
 *              task.excuteHttpTask();
 */

public class BinHttpTask0001 extends BinHttpTaskBase {

    public BinHttpTask0001(PSA0001 psa0001,String url){
        super(psa0001,url);
    }

    @Override
    public void onReceivePacket(BinProtocolReceiveBase packet) {
       //to do
    }

    @Override
    public void onError(int code, String errorMsg) {
       //to do
    }

}
