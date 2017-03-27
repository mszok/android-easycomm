package android.firefly.coltfashion.cc.netcomm.socketcomm.example.event;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.base.DictBase;
import android.firefly.coltfashion.cc.netcomm.base.IFactory;
import android.firefly.coltfashion.cc.netcomm.socketcomm.tcp.IPacketEvent;

public class NotifyEventCreater extends DictBase<Integer, String> implements IFactory<Integer, IPacketEvent> {

    static public  final String TAG = "NotifyEventCreater";


    static private NotifyEventCreater _NotifyEventCreater;

    static {

        _NotifyEventCreater = new NotifyEventCreater();


        // to do something reg eventdealer
        //_NotifyEventCreater.putItem();
    }

    static public NotifyEventCreater inst() {
        return _NotifyEventCreater;
    }

    @Override
    public IPacketEvent getEntryObj(Integer key) {


        if (existItem(key)) {

            String className = getItem(key);

            try {
                Class<?> c = Class.forName(className);
                return (IPacketEvent) c.newInstance();
            } catch (Throwable ex) {

                Loger.error(getDescripe(TAG,"getEntryObj"), ex);

            }
        }

        return null;

    }
}
