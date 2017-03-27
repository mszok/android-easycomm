package android.firefly.coltfashion.cc.netcomm.base;

/**
 * Created by msz on 2015/9/10.
 */
public interface IFactory<T, V> {

    V getEntryObj(T key);

}
