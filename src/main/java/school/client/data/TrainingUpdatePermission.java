package school.client.data;

import java.security.Permission;

public class TrainingUpdatePermission extends Permission{


	public TrainingUpdatePermission(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	public TrainingUpdatePermission(String name, String actions) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getActions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean implies(Permission permission) {
		// TODO Auto-generated method stub
		return false;
	}

}
