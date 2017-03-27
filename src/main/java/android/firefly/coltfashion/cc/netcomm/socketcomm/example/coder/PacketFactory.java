package android.firefly.coltfashion.cc.netcomm.socketcomm.example.coder;


import android.firefly.coltfashion.cc.netcomm.base.ClassFactoryDict;
import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.IPacketFactory;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.IPacketHead;

public class PacketFactory extends FireflyObjectBase implements IPacketFactory {

    static public final String TAG = "PacketFactory";

    static private PacketFactory _PacketFactory1 = new PacketFactory();

    static public PacketFactory inst(){
        return _PacketFactory1;
    }

    private ClassFactoryDict<Integer,Object> mReceiveProtocolDict = new ClassFactoryDict<>();

    public PacketFactory(){
        init();
    }

    private void init(){

//        mReceiveProtocolDict.putItem(10001,
//                PRA0001.class.getName());

    }


    @Override
    public IPacketHead getPacketHead() {

        return new PacketHead();
    }

    @Override
    public Object getPacket(int packid) {

        return mReceiveProtocolDict.getEntryObj(packid);

    }
}
