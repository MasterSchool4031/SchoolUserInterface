package school.client.gui;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import school.client.commons.ActionModelEvent;
import school.client.commons.ActionModelListener;

public class ActionModelSupport<A extends Enum> {

	private Set<ActionModelListener<A>> allAction;
	private Map<A, Set<ActionModelListener<A>>> byAction;
	private Object source;

	public ActionModelSupport(Object source) {
		super();
		if (source == null) {
			throw new NullPointerException();
		}
		this.source = source;
	}

	public synchronized void addActionModelListener(A action,
			ActionModelListener<A> listener) {
		if (action != null && listener != null) {
			Set<ActionModelListener<A>> listenerSet = this.getByAction().get(
					action);
			if (listenerSet == null) {
				listenerSet = new HashSet<ActionModelListener<A>>();
				this.getByAction().put(action, listenerSet);
			}
			listenerSet.add(listener);
		}
	}

	public synchronized void removeActionModelListener(A action,
			ActionModelListener<A> listener) {
		if (action != null && listener != null) {
			Set<ActionModelListener<A>> listenerSet = this.getByAction().get(
					action);
			if (listenerSet != null) {
				listenerSet.remove(listener);
			}
		}
	}

	public synchronized void addActionModelListener(
			ActionModelListener<A> listener) {
		if (listener != null) {
			this.allAction.add(listener);
		}
	}

	public synchronized void removeActionModelListener(
			ActionModelListener<A> listener) {
		if (listener != null) {
				this.allAction.remove(listener);
		}
	}
	
	public void fireActionModel(A command, Object param){
		this.fireActionModel(new ActionModelEvent<A>(this.source,command,param));
	}
	
	@SuppressWarnings("unchecked")
	public void fireActionModel(ActionModelEvent<A> event){
		Object[] tab;
		if (event != null) {
			Set<ActionModelListener<A>> listenerSet = this.getByAction().get(
					event.getCommand());
			if (listenerSet != null) {
				tab = listenerSet.toArray();
				for( Object li : tab){
					((ActionModelListener)li).actionModelPerformed(event);
				}
			}
			tab = this.getAllAction().toArray();
			for( Object li : tab){
				((ActionModelListener)li).actionModelPerformed(event);
			}
		}
	}

	private Set<ActionModelListener<A>> getAllAction() {
		if (this.allAction == null) {
			this.allAction = new HashSet<ActionModelListener<A>>();
		}
		return this.allAction;
	}

	private Map<A, Set<ActionModelListener<A>>> getByAction() {
		if (this.byAction == null) {
			this.byAction = new HashMap<A, Set<ActionModelListener<A>>>();
		}
		return this.byAction;
	}
}
