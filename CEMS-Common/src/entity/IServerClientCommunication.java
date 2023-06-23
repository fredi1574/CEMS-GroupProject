package entity;



import util.MsgHandler;



import java.io.IOException;



public interface IServerClientCommunication {



    public void sendToServer(Object msg) throws IOException;



    public MsgHandler getReturnMsg();



    public void popUpError(String msg);



    public void popUpMessage(String msg);



    public Object getUser();



    public void setUser(Object user);



}