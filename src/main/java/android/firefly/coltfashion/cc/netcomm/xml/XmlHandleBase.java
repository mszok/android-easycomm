package android.firefly.coltfashion.cc.netcomm.xml;

import android.firefly.coltfashion.cc.netcomm.base.IFireflyBase;
import android.firefly.coltfashion.cc.netcomm.base.IncreaseSeed;
import android.firefly.coltfashion.cc.netcomm.util.CommonUtil;
import android.firefly.coltfashion.cc.netcomm.util.StringUtil;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlSerializer;
import java.io.IOException;

public abstract class XmlHandleBase extends DefaultHandler implements IFireflyBase {

    static public final String TAG = "XmlHandleBase";


    static public final String SPLITXML = "/";

    static public final String XML_ENCODE = StringUtil.defaultCharset().name();

    static public final String NODE_NAMESPACE = StringUtil.EMPTYSTR;
    protected String mPathString = StringUtil.EMPTYSTR;
    private boolean mNodeReading = false;
    private String mNodeValue = StringUtil.EMPTYSTR;

    static public String getNotNullString(String value) {

        return StringUtil.getNotNullString(value);
    }

    static public void addNodeValue(XmlSerializer xmlSir, String nodeName,
                                    String value) throws IllegalArgumentException,
            IllegalStateException, IOException {

        xmlSir.startTag(NODE_NAMESPACE, nodeName);
        xmlSir.text(getNotNullString(value));
        xmlSir.endTag(NODE_NAMESPACE, nodeName);

    }

    static public void addNodeValue(XmlSerializer xmlSir, String nodeName,
                                    long value) throws IllegalArgumentException,
            IllegalStateException, IOException {

        xmlSir.startTag(NODE_NAMESPACE, nodeName);
        xmlSir.text(String.valueOf(value));
        xmlSir.endTag(NODE_NAMESPACE, nodeName);

    }

    static public void addNodeValue(XmlSerializer xmlSir, String nodeName,
                                    int value) throws IllegalArgumentException,
            IllegalStateException, IOException {

        xmlSir.startTag(NODE_NAMESPACE, nodeName);
        xmlSir.text(String.valueOf(value));
        xmlSir.endTag(NODE_NAMESPACE, nodeName);

    }

    static public void addNodeValue(XmlSerializer xmlSir, String nodeName,
                                    double value) throws IllegalArgumentException,
            IllegalStateException, IOException {

        xmlSir.startTag(NODE_NAMESPACE, nodeName);
        xmlSir.text(String.valueOf(value));
        xmlSir.endTag(NODE_NAMESPACE, nodeName);

    }

    static public void addNodeValue(XmlSerializer xmlSir, String nodeName,
                                    float value) throws IllegalArgumentException,
            IllegalStateException, IOException {

        xmlSir.startTag(NODE_NAMESPACE, nodeName);
        xmlSir.text(String.valueOf(value));
        xmlSir.endTag(NODE_NAMESPACE, nodeName);

    }

    static public void addNodeValue(XmlSerializer xmlSir, String nodeName,
                                    boolean value) throws IllegalArgumentException,
            IllegalStateException, IOException {

        xmlSir.startTag(NODE_NAMESPACE, nodeName);
        xmlSir.text(String.valueOf(value));
        xmlSir.endTag(NODE_NAMESPACE, nodeName);

    }

    static public void addNodeValue(XmlSerializer xmlSir, String nodeName,
                                    byte value) throws IllegalArgumentException,
            IllegalStateException, IOException {

        xmlSir.startTag(NODE_NAMESPACE, nodeName);
        xmlSir.text(String.valueOf(value));
        xmlSir.endTag(NODE_NAMESPACE, nodeName);

    }



    private final int mObjId = IncreaseSeed.getSingleNewId();
    public int getObjId() {

        return mObjId;
    }


    public String getDescripe(String tag,String methodName) {

        return CommonUtil.getMethodDescripe(tag,methodName);

    }

    @Override
    public void startDocument() throws SAXException {

        super.startDocument();

    }

    @Override
    public void endDocument() throws SAXException {

        super.endDocument();
    }

    public abstract void characters2(String nodeValue);

    public abstract void startElement2(Attributes attributes);

    public abstract void endElement2();

    private boolean backToParentNode(String localName) {

        int index = mPathString.lastIndexOf(SPLITXML);

        if (index >= 0) {

            String localNameEx = mPathString.substring(index + 1,
                    mPathString.length());

            if (localNameEx.equals(localName)) {

                if (index == 0) {

                    mPathString = StringUtil.EMPTYSTR;

                } else {

                    mPathString = mPathString.substring(0, index);
                }

                return true;

            }
        }

        return false;
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        String theString = new String(ch, start, length);

        if (mNodeReading) {

            mNodeValue += theString;
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        mPathString += String.format("%s%s", SPLITXML, localName);

        mNodeValue = StringUtil.EMPTYSTR;

        startElement2(attributes);

        mNodeReading = true;
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        characters2(mNodeValue);

        mNodeReading = false;

        endElement2();

        if (!backToParentNode(localName)) {

            throw new SAXException("xml file format error,path:" + mPathString);
        }
    }
}
