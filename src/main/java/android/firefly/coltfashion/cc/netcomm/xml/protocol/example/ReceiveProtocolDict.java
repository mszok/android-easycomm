package android.firefly.coltfashion.cc.netcomm.xml.protocol.example;

import android.firefly.coltfashion.cc.netcomm.base.ClassFactoryDict;
import android.firefly.coltfashion.cc.netcomm.xml.protocol.XmlProtocolReceiveBase;


public class ReceiveProtocolDict  {


    static public final String TAG = "ReceiveProtocolDict";


    static private ClassFactoryDict<String,XmlProtocolReceiveBase> _ReceiveProtocolDict;

    static {

        _ReceiveProtocolDict = new ClassFactoryDict();

        _ReceiveProtocolDict.putItem("A001",
                PRA0001.class.getName());


    }

    static ClassFactoryDict<String,XmlProtocolReceiveBase> inst() {
        return _ReceiveProtocolDict;
    }


}
