package android.firefly.coltfashion.cc.netcomm.xml.protocol.example;

import android.firefly.coltfashion.cc.netcomm.xml.XmlHandleBase;

import org.xmlpull.v1.XmlSerializer;

import java.io.IOException;

public class PSA0001 extends ProtocolSend {

    public String name;

    public String mark;

    public String machineCode;


    public PSA0001() {
        msgCode = "A0001";
    }


    @Override
    public void body2Xml(XmlSerializer xmlSir) throws IllegalArgumentException,
            IllegalStateException, IOException {


        XmlHandleBase.addNodeValue(xmlSir, "Name", name);

        XmlHandleBase.addNodeValue(xmlSir, "Mark", mark);

        XmlHandleBase.addNodeValue(xmlSir, "MachineCode", machineCode);


    }


}
