package util;

public interface IMsgHandler<Object> {
    public TypeMsg getType();

    public void setType(TypeMsg type);

    Object getMsg();

    void setMsg(Object msg);
}
