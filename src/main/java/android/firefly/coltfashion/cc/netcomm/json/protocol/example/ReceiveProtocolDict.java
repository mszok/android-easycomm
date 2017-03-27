package android.firefly.coltfashion.cc.netcomm.json.protocol.example;


import android.firefly.coltfashion.cc.netcomm.base.ClassFactoryDict;
import android.firefly.coltfashion.cc.netcomm.json.protocol.JsonProtocolReceiveBase;



public class ReceiveProtocolDict  {

    static public final String TAG = "ReceiveProtocolDict";

    static private ClassFactoryDict<String,JsonProtocolReceiveBase> _ReceiveProtocolDict;

    static {

        _ReceiveProtocolDict = new ClassFactoryDict();

        _ReceiveProtocolDict.putItem("A001",
                PRA0001.class.getName());


    }

    static ClassFactoryDict<String,JsonProtocolReceiveBase> inst() {
        return _ReceiveProtocolDict;
    }


}
