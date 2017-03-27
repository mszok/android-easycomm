package android.firefly.coltfashion.cc.netcomm.socketcomm.example.coder;

import android.firefly.coltfashion.cc.netcomm.base.BufferCoder;

public abstract class SendPacketBase {

    public PacketHead head = new PacketHead();

    public SendPacketBase() {

        head.headFlag = PacketHead.HEAD_FLAG;

    }

    // must override
    public abstract boolean body2Buffer(BufferCoder buffer);

    public abstract String getBodyDescripe();

    public String getPackageDescripe() {

        return String
                .format("%s %s", head.getHeadDescripe(), getBodyDescripe());

    }
}
