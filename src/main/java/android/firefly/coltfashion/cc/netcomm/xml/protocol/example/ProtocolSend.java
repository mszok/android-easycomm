package android.firefly.coltfashion.cc.netcomm.xml.protocol.example;

import android.firefly.coltfashion.cc.netcomm.util.StringUtil;
import android.firefly.coltfashion.cc.netcomm.xml.XmlHandleBase;
import android.firefly.coltfashion.cc.netcomm.xml.protocol.XmlProtocolSendBase;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;


public abstract class ProtocolSend extends XmlProtocolSendBase {


    public String session = StringUtil.EMPTYSTR;
    public String msgCode = StringUtil.EMPTYSTR;
    public long time = System.currentTimeMillis();


    public void head2Xml(XmlSerializer xmlSir) throws IllegalArgumentException,
            IllegalStateException, IOException {

        XmlHandleBase.addNodeValue(xmlSir, "MsgCode", msgCode);
        XmlHandleBase.addNodeValue(xmlSir, "Time", time);
        XmlHandleBase.addNodeValue(xmlSir, "Session", session);

    }

    @Override
    public String getRootTag() {
        return "Mds";
    }

    @Override
    public String getHeadTag() {
        return "Head";
    }

    @Override
    public String getBodyTag() {
        return "Body";
    }


}
