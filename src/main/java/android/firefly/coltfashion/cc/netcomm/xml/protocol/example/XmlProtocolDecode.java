package android.firefly.coltfashion.cc.netcomm.xml.protocol.example;

import android.firefly.coltfashion.cc.netcomm.Loger;
import android.firefly.coltfashion.cc.netcomm.util.TypeUtils;
import android.firefly.coltfashion.cc.netcomm.xml.protocol.XmlProtocolDecodeBase;


public class XmlProtocolDecode extends XmlProtocolDecodeBase {

    static public final String TAG = "XmlProtocolDecode";

    static {

        XmlProtocolDecodeBase.setXmlDecoder(new XmlProtocolDecode());

    }

    private String mMsgCode;
    private int mReturnCode;
    private long mTime;

    @Override
    protected void xml2Head(String xmlPath, String nodeValue) {

        if (xmlPath.equals(getHeadAbsPath("MsgCode"))) {
            mMsgCode = nodeValue;

            mPReceiveBase = ReceiveProtocolDict.inst().getEntryObj(mMsgCode);

        } else if (xmlPath.equals(getHeadAbsPath("ReturnCode"))) {
            mReturnCode = TypeUtils.toInt(nodeValue);
        } else if (xmlPath.equals(getHeadAbsPath("Time"))) {
            mTime = TypeUtils.toLong(nodeValue);
        } else {
            Loger.warn(getDescripe(TAG,"xml2Head"), "xmlpath can not explain:" + xmlPath);
        }

        if (mPReceiveBase != null) {
            ProtocolReceive protocolReceive = (ProtocolReceive) mPReceiveBase;
            protocolReceive.msgCode = mMsgCode;
            protocolReceive.returnCode = mReturnCode;
            protocolReceive.time = mTime;
        }
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
