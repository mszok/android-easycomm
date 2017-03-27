package android.firefly.coltfashion.cc.netcomm.xml.protocol;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;
import android.firefly.coltfashion.cc.netcomm.xml.XmlHandleBase;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;


public abstract class XmlProtocolSendBase extends FireflyObjectBase implements IProtocolTag {

    static public final String TAG = "XmlProtocolSendBase";

//    public String session = StringUtil.EMPTYSTR;
//    public String msgCode = StringUtil.EMPTYSTR;
//    public long subTime = System.currentTimeMillis();


//        XmlHandleBase.addNodeValue(xmlSir, "MsgCode", msgCode);
//        XmlHandleBase.addNodeValue(xmlSir, "Time", subTime);
//        XmlHandleBase.addNodeValue(xmlSir, "Session", session);


    public abstract void head2Xml(XmlSerializer xmlSir) throws IllegalArgumentException,
            IllegalStateException, IOException;


    public abstract void body2Xml(XmlSerializer xmlSir) throws IllegalArgumentException,
            IllegalStateException, IOException;


    public boolean obj2Xml(XmlSerializer xmlSir) {

        try {
            xmlSir.startDocument(XmlHandleBase.XML_ENCODE, true);

            xmlSir.startTag(XmlHandleBase.NODE_NAMESPACE, getRootTag());

            xmlSir.startTag(XmlHandleBase.NODE_NAMESPACE, getHeadTag());
            head2Xml(xmlSir);
            xmlSir.endTag(XmlHandleBase.NODE_NAMESPACE, getHeadTag());


            xmlSir.startTag(XmlHandleBase.NODE_NAMESPACE, getBodyTag());
            body2Xml(xmlSir);
            xmlSir.endTag(XmlHandleBase.NODE_NAMESPACE, getBodyTag());


            xmlSir.endTag(XmlHandleBase.NODE_NAMESPACE, getRootTag());

            xmlSir.endDocument();
            return true;

        } catch (Exception ex) {

            Loger.error(getDescripe(TAG,"obj2Xml"), ex);
            return false;
        }


    }


}
