package com.stationary.demo.entities;

public class LoginViewModel {

    private String email;
    private String password;

    public String getPassword() {
        return password;
    }

    @Override
	public String toString() {
		return "LoginViewModel [email=" + email + ", password=" + password + "]";
	}

	public void setPassword(String password) {
        this.password = password;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
