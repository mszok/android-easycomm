package android.firefly.coltfashion.cc.netcomm.xml;


import org.xml.sax.Attributes;
import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;


//this class is example of xmlfile

public class XmlFileExample extends XmlFileHandleBase {


    public String pwd;

    public XmlFileExample(String fileName) {

        createInstance(fileName, true);

    }


    @Override
    public void characters2(String nodeValue) {

        String theString = nodeValue;
        if (mPathString.equals("/Config/Pwd")) {

            pwd = theString;

        }


    }

    @Override
    public void startElement2(Attributes attributes) {


    }

    @Override
    public void endElement2() {

    }

    @Override
    protected void saveToFile(XmlSerializer xmlSir)
            throws IllegalArgumentException, IllegalStateException, IOException {

        xmlSir.startTag(NODE_NAMESPACE, "Config");

        addNodeValue(xmlSir, "Pwd", pwd);

        xmlSir.endTag(NODE_NAMESPACE, "Config");

    }


}
