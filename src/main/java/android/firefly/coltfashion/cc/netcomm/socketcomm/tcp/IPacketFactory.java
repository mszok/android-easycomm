package android.firefly.coltfashion.cc.netcomm.socketcomm.tcp;

public interface IPacketFactory {

    IPacketHead getPacketHead();

    Object getPacket(int packid);
}
