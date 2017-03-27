package android.firefly.coltfashion.cc.netcomm.http.okhttp;


import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;
import android.firefly.coltfashion.cc.netcomm.bin.protocol.BinProtocolDecodeBase;
import android.firefly.coltfashion.cc.netcomm.bin.protocol.BinProtocolReceiveBase;
import android.firefly.coltfashion.cc.netcomm.bin.protocol.BinProtocolSendBase;
import android.firefly.coltfashion.cc.netcomm.util.StringUtil;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by msz on 2015/9/25.
 */
public abstract class BinHttpTaskBase extends HttpTaskBase {

    static public final String TAG = "BinHttpTaskBase";

    static public final MediaType MEDIATYPE_BIN = MediaType.parse("application/bin;");

    protected BinProtocolSendBase mBinSendBody;

    public BinHttpTaskBase(BinProtocolSendBase packet,String url){
        super(url);
        setBinSendBody(packet);
    }


    public void setBinSendBody(BinProtocolSendBase body) {
        mBinSendBody = body;
    }


    @Override
    public void onResponseData(Response response) throws IOException {

        byte[] responseBytes = response.body().bytes();

        BinProtocolReceiveBase packet = BinProtocolDecodeBase.bin2Obj(responseBytes);

        if (packet != null) {

            onReceivePacket(packet);
        } else {
            onError(OkHttpUtils.ERRORCODE_EXPLAINERROR, "parse reponse packet wrong");
        }
    }

    public abstract void onReceivePacket(BinProtocolReceiveBase packet);

    @Override
    public Request getRequest() {

        if (StringUtil.isNullEmpty(mUrl)) {
            return null;
        }

        BufferCoder buffer = BufferCoder.newBuffer();

        if (!mBinSendBody.obj2Buffer(buffer)) {
            return null;
        }

        BufferCoder.BufferDef bufferDef = buffer.getBuffer();

        return new Request.Builder().url(mUrl).post(RequestBody.create(MEDIATYPE_BIN, bufferDef.buffer,0,bufferDef.posIndex)).build();

    }


}
