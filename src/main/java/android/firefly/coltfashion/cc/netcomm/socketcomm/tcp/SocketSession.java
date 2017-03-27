package android.firefly.coltfashion.cc.netcomm.socketcomm.tcp;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;
import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SocketSession extends FireflyObjectBase {

    static public  final String TAG = "SocketSession";

    private Socket mSocket;

    private SocketReceiver mSocketReceiver;

    private ICodeDealer mCodeDealer;

    private IEventListener mlisterDoEvent;

    public ICodeDealer getCodeDealer() {

        return mCodeDealer;
    }

    public void setCodeDealer(ICodeDealer coder) {

        mCodeDealer = coder;
    }

    public IEventListener getListener() {

        return mlisterDoEvent;

    }

    public void setListener(IEventListener listener) {

        mlisterDoEvent = listener;

    }

    public Socket getSocket() {

        return mSocket;
    }

    public boolean sendObj(IPacketEvent packetEvent) {

        if (packetEvent == null) {
            Loger.warn(getDescripe(TAG,"sendObj"), "packetEvent is null");
            return false;
        }

        Object sendPack = packetEvent.getSendPacket();

        if (sendPack == null) {
            Loger.warn(getDescripe(TAG,"sendObj"), "send packet is null");
            return false;
        }
        return sendObj(sendPack);

    }

    public synchronized boolean sendObj(Object obj) {

        if ((mSocketReceiver == null) || !mSocketReceiver.getActived()) {
            Loger.warn(getDescripe(TAG,"sendObj"),
                    "mSocketReceiver is not actived");
            return false;
        }

        BufferCoder bDef = BufferCoder.newBuffer();
        try {


            if (!mCodeDealer.encoder(obj, bDef)) {
                Loger.warn(getDescripe(TAG,"sendObj"), "encoder error");
                return false;
            }

            OutputStream os = mSocket.getOutputStream();

            // log bin
//            Loger.debug(
//                    getMethodDescripe("sendObj"),
//                    "send data:"
//                            + CommonUtil.bytes2HexString(bDef.buffer, 0,
//                            bDef.posIndex));

            os.write(bDef.getBuffer().buffer, 0, bDef.getPosition());
            os.flush();

            // Loger.debug(
            // "SocketSession.sendObj",
            // "发送数据2:" + CommonUtil.bytes2HexString(bDef.buffer, 0,
            // bDef.posIndex));

            return true;

        } catch (IOException ex) {

            Loger.error(ex);

            return false;

        } finally {

            BufferCoder.disposeBuffer(bDef);


        }

    }

    public synchronized boolean connect(String serverIp, int serverPort) {

        try {

            // ConnectivityManager connectivityManager = (ConnectivityManager)
            // GlobeVarDef._AppContext
            // .getSystemService(Context.CONNECTIVITY_SERVICE);
            // NetworkInfo[] nets = connectivityManager.getAllNetworkInfo();
            // NetworkInfo net = connectivityManager.getActiveNetworkInfo();
            // net =
            // connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_ETHERNET);
            // Loger.debug("", String.valueOf(nets.length) + net.getTypeName());

            if ((mSocketReceiver != null) && mSocketReceiver.getActived()) {
                Loger.warn(getDescripe(TAG,"connect"), "SocketSession already connected");
                return false;
            }

            mSocket = new Socket(serverIp, serverPort);

            mSocketReceiver = new SocketReceiver(this);

            Object obj = mSocketReceiver.getLockObj();

            synchronized (obj) {

                mSocketReceiver.start();

                obj.wait();
            }

            return true;

        } catch (Throwable ex) {

            Loger.error(getDescripe(TAG,"connect"), ex);

            return false;

        }
    }

    public synchronized void disConnect() {

        if ((mSocketReceiver != null) && mSocketReceiver.getActived()) {

            mSocketReceiver.abort();

            try {

                mSocketReceiver.join();

            } catch (Throwable ex) {

                Loger.error(getDescripe(TAG,"disConnect"), ex);

            }


        }
    }

    public synchronized boolean getActived() {

        return (mSocketReceiver != null) && mSocketReceiver.getActived();

    }

}
