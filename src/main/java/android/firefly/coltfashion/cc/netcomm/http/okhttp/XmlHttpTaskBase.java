package android.firefly.coltfashion.cc.netcomm.http.okhttp;


import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.util.StringUtil;
import android.firefly.coltfashion.cc.netcomm.xml.protocol.XmlProtocolDecodeBase;
import android.firefly.coltfashion.cc.netcomm.xml.protocol.XmlProtocolReceiveBase;
import android.firefly.coltfashion.cc.netcomm.xml.protocol.XmlProtocolSendBase;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.StringWriter;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by msz on 2015/9/25.
 */
public abstract class XmlHttpTaskBase extends HttpTaskBase {

    static public final String TAG = "XmlHttpTaskBase";

    //static public final MediaType MEDIATYPE_JSON = MediaType.parse("application/json;charset=utf-8");

    static public final MediaType MEDIATYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");


    private XmlProtocolSendBase mXmlSendBody;


    public XmlHttpTaskBase(XmlProtocolSendBase packet, String url){
        super(url);
        setXmlSendBody(packet);
    }

    public void setXmlSendBody(XmlProtocolSendBase body) {
        mXmlSendBody = body;
    }


    @Override
    public void onResponseData(Response response) {

        String responseText = response.body().toString();

        XmlProtocolReceiveBase packet = XmlProtocolDecodeBase.xml2Obj(responseText);

        if (packet != null) {
            onReceivePacket(packet);
        } else {
            onError(OkHttpUtils.ERRORCODE_EXPLAINERROR, "parse reponse packet wrong");
        }
    }

    public abstract void onReceivePacket(XmlProtocolReceiveBase packet);


    @Override
    public Request getRequest() {

        if (StringUtil.isNullEmpty(mUrl)) {
            return null;
        }


        XmlSerializer xmlSir = Xml.newSerializer();
        StringWriter stringWriter = new StringWriter();

        try {
            xmlSir.setOutput(stringWriter);
        } catch (Exception ex) {

            Loger.error(getDescripe(TAG, "getRequest"), ex);
           return null;
        }

        if (!mXmlSendBody.obj2Xml(xmlSir)) {
            return null;
        }
        String content = stringWriter.toString();

        return new Request.Builder().url(mUrl).post(RequestBody.create(MEDIATYPE_MARKDOWN, content)).build();

    }

}
