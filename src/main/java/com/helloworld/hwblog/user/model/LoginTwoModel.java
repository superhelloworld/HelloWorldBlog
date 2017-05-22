package com.helloworld.hwblog.user.model;

public class LoginTwoModel {
private String username;
private String nickname;
private String icon;
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getNickname() {
	return nickname;
}
public void setNickname(String nickname) {
	this.nickname = nickname;
}
public String getIcon() {
	return icon;
}
public void setIcon(String icon) {
	this.icon = icon;
}
public LoginTwoModel(String username, String nickname, String icon) {
	super();
	this.username = username;
	this.nickname = nickname;
	this.icon = icon;
}
public LoginTwoModel() {
	super();
	// TODO Auto-generated constructor stub
}

}
