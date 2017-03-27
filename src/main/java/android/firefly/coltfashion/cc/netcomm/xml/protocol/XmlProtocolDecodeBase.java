package android.firefly.coltfashion.cc.netcomm.xml.protocol;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.util.CommonUtil;
import android.firefly.coltfashion.cc.netcomm.util.StringUtil;
import android.firefly.coltfashion.cc.netcomm.xml.XmlHandleBase;

import org.xml.sax.Attributes;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public abstract class XmlProtocolDecodeBase extends XmlHandleBase implements IProtocolTag {

    static public final String TAG = "XmlProtocolDecodeBase";


    static private XmlProtocolDecodeBase _XmlDecoder;
    protected XmlProtocolReceiveBase mPReceiveBase;

    static public void setXmlDecoder(XmlProtocolDecodeBase decoder) {

        _XmlDecoder = decoder;

    }

    static public XmlProtocolDecodeBase getXmlDecoder() {

        return _XmlDecoder;
    }

    static public XmlProtocolReceiveBase xml2Obj(String xmlProtocolText) {

        ByteArrayInputStream inputStream = new ByteArrayInputStream(xmlProtocolText.getBytes());

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser reader = factory.newSAXParser();
            _XmlDecoder.mPReceiveBase = null;
            reader.parse(inputStream, _XmlDecoder);
            return _XmlDecoder.mPReceiveBase;

        } catch (Throwable ex) {

            Loger.error(CommonUtil.getMethodDescripe(TAG, "xml2Obj"), ex);

            return null;
        } finally {

            try {
                inputStream.close();
            } catch (Throwable e) {

            }


        }

    }

    protected String getHeadAbsPath(String subPath) {

        return String.format("%s/%s/%s", getRootTag(), getHeadTag(), subPath);


    }

    protected String getBodyAbsPath(String subPath) {

        return String.format("%s/%s/%s", getRootTag(), getBodyTag(), subPath);


    }

    protected abstract void xml2Head(String xmlPath, String nodeValue);


//    protected abstract String getHeadRootPath();
//
//    protected abstract String getBodyRootPath();

    @Override
    public void characters2(String nodeValue) {


        String headRootPath = getHeadAbsPath(StringUtil.EMPTYSTR);
        String bodyRootPath = getBodyAbsPath(StringUtil.EMPTYSTR);


        if (mPathString.contains(headRootPath)) {

            xml2Head(mPathString, nodeValue);

        } else if (mPathString.contains(bodyRootPath)) {

            if (mPReceiveBase != null)
                mPReceiveBase.xml2Body(mPathString, nodeValue);

        }


    }

    @Override
    public void startElement2(Attributes attributes) {

    }

    @Override
    public void endElement2() {

    }

}
