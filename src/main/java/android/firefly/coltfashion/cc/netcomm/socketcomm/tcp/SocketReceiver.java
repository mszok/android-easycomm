package android.firefly.coltfashion.cc.netcomm.socketcomm.tcp;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.base.IFireflyBase;
import android.firefly.coltfashion.cc.netcomm.base.IncreaseSeed;
import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;
import android.firefly.coltfashion.cc.netcomm.util.CommonUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;


public class SocketReceiver extends Thread implements IFireflyBase {

    static public final String TAG = "SocketReceiver";

    static private final int ERRORCOUNT = -1;

    private SocketSession mClientSocket;

    private boolean mAbort = false;

    private Object mObj;

    private boolean mIsActived = false;

    private final int mObjId = IncreaseSeed.getSingleNewId();

    public SocketReceiver(SocketSession cSocket) {

        mClientSocket = cSocket;

        mObj = new Object();

    }



    @Override
    public int getObjId() {

        return mObjId;
    }



    public String getDescripe(String tag,String methodName) {

        return CommonUtil.getMethodDescripe(tag,methodName);

    }

    public boolean getActived() {
        return mIsActived;
    }

    public Object getLockObj() {

        return mObj;
    }

    public void abort() {

        mAbort = true;
    }

    @Override
    public void run() {

        IEventListener listener = mClientSocket.getListener();
        Socket sock = mClientSocket.getSocket();
        ICodeDealer codeDealer = mClientSocket.getCodeDealer();

        mIsActived = true;

        if (listener != null) {
            try {

                listener.connected(mClientSocket);

            } catch (Throwable ex) {

                Loger.error(getDescripe(TAG,"run"), ex);
            }
        }

        synchronized (mObj) {
            mObj.notifyAll();
        }

        try {

            InputStream is = sock.getInputStream();

            while (true) {

                if (mAbort)
                    break;

                if (codeDealer == null) {
                    Loger.warn(getDescripe(TAG,"run"), "codeDealer is null");
                    break;
                }

                IPacketHead head = codeDealer.getPacketFactory()
                        .getPacketHead();

                int headLength = head.getHeadLength();

                while (is.available() < headLength) {
                    Thread.sleep(50);
                    if (mAbort)
                        break;

                    if (is.available() == ERRORCOUNT) {
                        throw new IOException(
                                "server socket maybe close valid -1");
                    }

                    //Loger.debug("000000000000000000000000000", "no locked");
                }

                if (mAbort)
                    break;

                BufferCoder bdef = BufferCoder.newBuffer();
                try {

                    int readCount = is.read(bdef.getBuffer().buffer, 0, headLength);
                    Loger.debug(
                            getDescripe(TAG,"SocketReceiver.run"),
                            String.format(
                                    "-----------------socket read package head size: %d",
                                    readCount));

                    // log bin
//                    Loger.debug(
//                            getMethodDescripe("SocketReceiver.run"),
//                            "receive head data:"
//                                    + CommonUtil.bytes2HexString(bdef.buffer,
//                                    0, headLength));

                    bdef.setPosition(0);
                    codeDealer.headDecoder(bdef, head);

                } finally {
                    BufferCoder.disposeBuffer(bdef);
                }

                int exheadLength = head.getExheadLength();

                while (is.available() < exheadLength) {
                    Thread.sleep(50);
                    if (mAbort)
                        break;
                    if (is.available() == ERRORCOUNT) {
                        throw new IOException(
                                "server socket maybe close valid -1");
                    }
                }

                if (mAbort)
                    break;

                BufferCoder bdef2 = BufferCoder.newBuffer();
                try {

                    int readCount = is.read(bdef2.getBuffer().buffer, 0, exheadLength);
                    Loger.debug(
                            getDescripe(TAG,"SocketReceiver.run"),
                            String.format(
                                    "-----------------socket read package exhead size: %d",
                                    readCount));

//                    // log bin
//                    Loger.debug(
//                            getMethodDescripe("SocketReceiver.run"),
//                            "receive head extend data:"
//                                    + CommonUtil.bytes2HexString(bdef2.buffer,
//                                    0, exheadLength));
                    bdef2.setPosition(0);
                    codeDealer.exheadDecoder(bdef2, head);

                } finally {
                    BufferCoder.disposeBuffer(bdef2);

                }

                int bodylen = head.getBodyLength();
                if (bodylen < 0) {
                    Loger.warn(getDescripe(TAG,"run"), "body length wrong");
                    break;
                }

                while (is.available() < bodylen) {
                    Thread.sleep(50);
                    if (mAbort)
                        break;

                    if (is.available() == ERRORCOUNT) {
                        throw new IOException(
                                "server socket maybe close valid -1");
                    }
                }
                if (mAbort)
                    break;

                BufferCoder bdef3 = BufferCoder.newBuffer();

                try {

                    int readCount3 = is.read(bdef3.getBuffer().buffer, 0, bodylen);

                    Loger.debug(
                            getDescripe(TAG,"SocketReceiver.run"),
                            String.format(
                                    "-----------------socket read package body size: %d",
                                    readCount3));
                    // log bin
//                    Loger.debug(
//                            getMethodDescripe("SocketReceiver.run"),
//                            "receive body data:"
//                                    + CommonUtil.bytes2HexString(bdef3.buffer,
//                                    0, bodylen));

                    bdef3.setPosition(0);
                    Object obj = codeDealer.bodyDecoder(bdef3, head);

                    if ((listener != null) && (obj != null)) {

                        try {

                            listener.receive(obj, mClientSocket);

                        } catch (Throwable ex) {

                            Loger.error(getDescripe(TAG,"run"), ex);
                        }

                    }

                } finally {

                    BufferCoder.disposeBuffer(bdef3);

                }

            }

        } catch (Exception ex) {

            Loger.error(getDescripe(TAG,"run"), ex);
        }

        try {

            sock.close();

        } catch (IOException ex) {

            Loger.error(getDescripe(TAG,"run"), ex);
        }

        mIsActived = false;

        if (listener != null) {
            try {

                listener.closed(mClientSocket);

            } catch (Throwable ex) {

                Loger.error(getDescripe(TAG,"run"), ex);
            }
        }

    }






}