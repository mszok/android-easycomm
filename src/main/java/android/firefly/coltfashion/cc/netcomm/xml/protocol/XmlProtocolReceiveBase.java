package android.firefly.coltfashion.cc.netcomm.xml.protocol;

import android.firefly.coltfashion.cc.netcomm.base.FireflyObjectBase;


public abstract class XmlProtocolReceiveBase extends FireflyObjectBase {

    protected String getBodyAbsPath(String subPath) {

        return XmlProtocolDecodeBase.getXmlDecoder().getBodyAbsPath(subPath);

    }

    public abstract void xml2Body(String xmlPath, String xmlValue);


}
