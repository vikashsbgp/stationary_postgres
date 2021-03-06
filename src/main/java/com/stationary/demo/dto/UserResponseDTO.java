package com.stationary.demo.dto;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO {

	private long id;
	private String firstName;
	private String lastName;
	private String email;
	private String mobile;
	private List<AddressResponseDTO> address = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<AddressResponseDTO> getAddress() {
		return address;
	}

	public void setAddress(List<AddressResponseDTO> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "UserResponseDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobile=" + mobile + ", address=" + address + "]";
	}


}
