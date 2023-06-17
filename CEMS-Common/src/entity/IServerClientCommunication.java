package entity;

import util.MsgHandler;

public interface IServerClientCommunication {

    public void sendToServer(Object msg);

    public MsgHandler getServerMsg();

    public Object getUser();

    public void setUser(Object user);
}
