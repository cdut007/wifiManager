package com.thirdpart.model.entity;

import java.util.List;

public class UserInfo {
	
	private String id;
	
	private String name;

	private String realname;

	private String email;

	private String registerTime;

	private String registerIp;

	private String currentLoginTime;

	private String currentLoginIp;

	private String lastLoginTime;

	private String lastLoginIp;

	private String loginTimes;

	private List<Role> roles;
	public List<Department> departments;
	private List<Privilege> privileges;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRealname() {
		return this.realname;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getRegisterTime() {
		return this.registerTime;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	public String getRegisterIp() {
		return this.registerIp;
	}

	public void setCurrentLoginTime(String currentLoginTime) {
		this.currentLoginTime = currentLoginTime;
	}

	public String getCurrentLoginTime() {
		return this.currentLoginTime;
	}

	public void setCurrentLoginIp(String currentLoginIp) {
		this.currentLoginIp = currentLoginIp;
	}

	public String getCurrentLoginIp() {
		return this.currentLoginIp;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginTime() {
		return this.lastLoginTime;
	}

	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}

	public String getLastLoginIp() {
		return this.lastLoginIp;
	}

	public void setLoginTimes(String loginTimes) {
		this.loginTimes = loginTimes;
	}

	public String getLoginTimes() {
		return this.loginTimes;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Role> getRoles() {
		return this.roles;
	}

	public void setPrivileges(List<Privilege> privileges) {
		this.privileges = privileges;
	}

	public List<Privilege> getPrivileges() {
		return this.privileges;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}