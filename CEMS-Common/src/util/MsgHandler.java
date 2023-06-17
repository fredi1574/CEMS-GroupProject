package util;

import java.io.Serializable;
import java.util.Objects;

/**
 * This class represents a message handler that associates a message type with a message object.
 *
 * @param <Object> The type of the message object.
 */
public class MsgHandler<Object> implements Serializable {

    private TypeMsg type;
    private Object msg;

    /**
     * Constructs a MsgHandler object with the specified message type and message.
     *
     * @param type The type of the message.
     * @param msg  The message object.
     */
    public MsgHandler(TypeMsg type, Object msg) {
        super();
        this.type = type;
        this.msg = msg;
    }

    /**
     * Retrieves the type of the message.
     *
     * @return The type of the message.
     */
    public TypeMsg getType() {
        return type;
    }

    /**
     * Sets the type of the message.
     *
     * @param type The type of the message.
     */
    public void setType(TypeMsg type) {
        this.type = type;
    }

    /**
     * Retrieves the message object.
     *
     * @return The message object.
     */
    public Object getMsg() {
        return msg;
    }

    /**
     * Sets the message object.
     *
     * @param msg The message object.
     */
    public void setMsg(Object msg) {
        this.msg = msg;
    }

    @Override
    public int hashCode() {
        return Objects.hash(msg, type);
    }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        MsgHandler other = (MsgHandler) obj;
        return Objects.equals(msg, other.msg) && type == other.type;
    }
}
