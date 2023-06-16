package application.loginWindowScreen;

import util.MsgHandler;

public interface IServerClientCommunication {

    public short sendToServer(Object msg);

    public MsgHandler getServerMsg();


}
