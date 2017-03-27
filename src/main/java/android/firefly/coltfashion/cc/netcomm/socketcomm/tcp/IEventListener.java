package android.firefly.coltfashion.cc.netcomm.socketcomm.tcp;

public interface IEventListener {

    void connected(SocketSession connectObj);

    void closed(SocketSession connectObj);

    void receive(Object packet, SocketSession connectObj);
}
