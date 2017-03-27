package android.firefly.coltfashion.cc.netcomm.base;

/**
 * Created by msz on 2017/3/12.
 */

public class IncreaseSeed {

     private int mid;

     private synchronized int getNewID() {

         mid++;
        if (mid >= Integer.MAX_VALUE)
            mid = 1;

        return mid;
    }

    private long mlargeId;

    private synchronized long getLargeNewID() {

        mlargeId++;
        if (mlargeId >= Long.MAX_VALUE)
            mlargeId = 1;

        return mlargeId;
    }

    static private final IncreaseSeed _IncreaseSeed = new IncreaseSeed();

    static public int getSingleNewId(){

       return _IncreaseSeed.getNewID();

    }

    static public long getSingleLargeNewId(){

        return _IncreaseSeed.getLargeNewID();

    }

}
