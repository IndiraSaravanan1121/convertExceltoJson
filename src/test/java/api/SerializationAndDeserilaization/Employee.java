package api.SerializationAndDeserilaization;

import java.util.List;

public class Employee {
	
	private String name;
	private String email;
	private List<AddressPojoClass> address;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<AddressPojoClass> getAddress() {
		return address;
	}

	public void setAddress(List<AddressPojoClass> address) {
		this.address = address;
	}
}
