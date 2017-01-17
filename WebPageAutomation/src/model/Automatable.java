package model;

public interface Automatable {
		
	public Automatable create();
	public void doLogin();
	public void close();
	public String getCurrentPage();
	public void setUser(User user);
}
