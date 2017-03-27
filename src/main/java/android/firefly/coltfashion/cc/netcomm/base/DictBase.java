package android.firefly.coltfashion.cc.netcomm.base;

import android.firefly.coltfashion.cc.netcomm.Loger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by msz on 2015/9/10.
 */
public class DictBase<T, V> extends FireflyObjectBase {

    static public final String TAG = "DictBase<T,V>";

    protected Map<T, V> mDictMap = new HashMap<>();


    public synchronized void putItem(T key, V value) {

        if (!mDictMap.containsKey(key)) {
            mDictMap.put(key, value);
        } else {
            Loger.warn(getDescripe(TAG,"putItem"), "item exist,key:" + key);
        }
    }

    public synchronized V getItem(T key) {
        return mDictMap.get(key);

    }

    public synchronized V delItem(T key) {

        return mDictMap.remove(key);
    }

    public synchronized boolean existItem(T key) {
        return mDictMap.containsKey(key);

    }


}
