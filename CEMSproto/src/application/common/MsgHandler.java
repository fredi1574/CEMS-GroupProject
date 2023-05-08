package application.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

@SuppressWarnings("serial")
public class MsgHandler<Object> implements  Serializable {
	
	private TypeMsg type;
	private ArrayList<Object> msg;
	
	public MsgHandler(TypeMsg type, ArrayList<Object> msg) {
		super();
		this.type = type;
		this.msg = msg;
	}
	public TypeMsg getType() {
		return type;
	}

	public void setType(TypeMsg type) {
		this.type = type;
	}

	public ArrayList<Object> getMsg() {
		return msg;
	}

	public void setMsg(ArrayList<Object> msg) {
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
