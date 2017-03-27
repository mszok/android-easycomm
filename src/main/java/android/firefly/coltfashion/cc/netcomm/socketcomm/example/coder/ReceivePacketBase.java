package android.firefly.coltfashion.cc.netcomm.socketcomm.example.coder;

import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;

public abstract class ReceivePacketBase {

    public PacketHead head;

    // must override
    public abstract boolean buffer2Body(BufferCoder buffer);

    // must override
    public abstract String getBodyDescripe();

    public String getPackageDescripe() {

        if (head != null) {
            return String.format("%s %s", head.getHeadDescripe(),
                    getBodyDescripe());
        } else {
            return getBodyDescripe();
        }

    }

}
