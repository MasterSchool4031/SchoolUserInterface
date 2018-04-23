package school.client.commons;

import java.util.EventObject;

public class ActionModelEvent<T> extends EventObject {

	private static final long serialVersionUID = 1L;
	private T command;
	private Object param;
	
	public ActionModelEvent(Object source, T command, Object param) {
		super(source);
		this.command = command;
		this.param = param;
	}

	public T getCommand() {
		return command;
	}

	public void setCommand(T command) {
		this.command = command;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	@Override
	public String toString() {
		return "[source: "+this.getSource()+", command: "+this.getCommand()+", param: "+this.getParam()+"]";
	}


}
