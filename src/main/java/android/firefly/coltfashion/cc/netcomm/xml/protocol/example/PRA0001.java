package android.firefly.coltfashion.cc.netcomm.xml.protocol.example;


public class PRA0001 extends ProtocolReceive {

    public String terminalCode;


    @Override
    public void xml2Body(String xmlPath, String xmlValue) {

        if (xmlPath.equals(getBodyAbsPath("TerminalCode"))) {
            terminalCode = xmlValue;
        }

    }
}
