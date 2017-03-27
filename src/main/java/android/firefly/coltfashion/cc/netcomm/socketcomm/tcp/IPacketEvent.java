package android.firefly.coltfashion.cc.netcomm.socketcomm.tcp;

public interface IPacketEvent {

    int getMsgCode();

    long getSerialNo();

    boolean sendSuccessful();

    String getTradeDescripe();

    Object getSendPacket();

    void setSendPacket(Object sendPacket);

    Object getReceivePacket();

    void setReceivePacket(Object receivePacket);

    void mainThreadReceivePacketDealer(SocketSession session);

    void receivePacketDealer(SocketSession session);

    boolean sendPacketDealer(SocketSession session);

    long sendPacketTime();

    long receivePacketTime();

}
