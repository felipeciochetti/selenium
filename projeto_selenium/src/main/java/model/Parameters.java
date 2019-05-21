package model;

import java.io.Serializable;

public class Parameters implements Serializable {

	
	private static Parameters instance = new Parameters();
	
	String login;
	String password;
	String chrome;
	String path_settings;
	String path_xls;
	String log = "";
	
	
	public static Parameters getInstance(){
		return instance;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getChrome() {
		return chrome;
	}
	public void setChrome(String chrome) {
		this.chrome = chrome;
	}
	public String getPath_settings() {
		return path_settings;
	}
	public void setPath_settings(String path_settings) {
		this.path_settings = path_settings;
	}
	public static void setInstance(Parameters instance) {
		Parameters.instance = instance;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		if(getLog() == null){
			log = "";
		}
		this.log   = this.log +  log + "\n";
	}
	public String getPath_xls() {
		return path_xls;
	}
	public void setPath_xls(String path_xls) {
		this.path_xls = path_xls;
	}
	
		
	
	
}
