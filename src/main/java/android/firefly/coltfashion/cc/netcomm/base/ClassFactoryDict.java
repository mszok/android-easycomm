package android.firefly.coltfashion.cc.netcomm.base;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.util.StringUtil;

/**
 * Created by msz on 2017/3/22.
 */

public class ClassFactoryDict<K,V> extends DictBase<K, String> implements IFactory<K, V> {

    static public final String TAG = "ClassFactoryDict";

    @Override
    public V getEntryObj(K key) {

        String value = getItem(key);

        if (!StringUtil.isNullEmpty(value)) {
            try {

                Class<?> c = Class.forName(value);
                return (V) c.newInstance();

            } catch (Exception ex) {

                Loger.error(getDescripe(TAG,"getEntryObj"), ex);
                return null;
            }
        }

        return null;

    }

}
