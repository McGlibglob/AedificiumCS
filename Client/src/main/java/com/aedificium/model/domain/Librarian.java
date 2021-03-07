package com.aedificium.model.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="librarians")
public class Librarian extends Googler{

	private static final long serialVersionUID = -4927200532398029825L;

	public Librarian() {
		super();
	}

	public Librarian(String ldap, String badgeId, String firstName, String lastName) {
		super(ldap, badgeId, firstName, lastName); //Try to user "super()" method
	}

	@Override
	public String toString() {
		return "Librarian [getLdap()=" + getLdap() + ", getBadgeId()=" + getBadgeId() + ", getFirstName()="
				+ getFirstName() + ", getLastName()=" + getLastName() + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}
	
	

}
