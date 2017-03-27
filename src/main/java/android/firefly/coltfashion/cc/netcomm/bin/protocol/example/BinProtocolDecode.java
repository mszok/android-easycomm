package android.firefly.coltfashion.cc.netcomm.bin.protocol.example;

import android.firefly.coltfashion.cc.netcomm.bin.protocol.BinProtocolDecodeBase;
import android.firefly.coltfashion.cc.netcomm.bin.protocol.BinProtocolReceiveBase;
import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;

public class BinProtocolDecode extends BinProtocolDecodeBase {

    static {

        BinProtocolDecodeBase.setBinDecoder(new BinProtocolDecode());

    }


    @Override
    protected BinProtocolReceiveBase bin2Head(BufferCoder buffer) {


            String msgCode = buffer.getString();

            ProtocolReceive receiveBase = (ProtocolReceive) ReceiveProtocolDict.inst().getEntryObj(msgCode);
            if (receiveBase != null) {

                int returnCode = buffer.getInt();
                long time = buffer.getLong();

                receiveBase.msgCode = msgCode;
                receiveBase.returnCode = returnCode;
                receiveBase.time = time;
            }
            return receiveBase;



    }

}
