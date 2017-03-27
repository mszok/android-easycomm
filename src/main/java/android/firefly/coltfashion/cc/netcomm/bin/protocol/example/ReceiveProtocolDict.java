package android.firefly.coltfashion.cc.netcomm.bin.protocol.example;

import android.firefly.coltfashion.cc.netcomm.base.ClassFactoryDict;
import android.firefly.coltfashion.cc.netcomm.bin.protocol.BinProtocolReceiveBase;


public class ReceiveProtocolDict  {

    public static final String TAG = "ReceiveProtocolDict";

    static private ClassFactoryDict<String,BinProtocolReceiveBase> _ReceiveProtocolDict;

    static {

        _ReceiveProtocolDict = new ClassFactoryDict();

        _ReceiveProtocolDict.putItem("A001",
                PRA0001.class.getName());

    }

    static ClassFactoryDict<String,BinProtocolReceiveBase> inst() {
        return _ReceiveProtocolDict;
    }



}
