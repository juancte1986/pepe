package ar.edu.uces.progweb2.agenda.model;

public class Guest {
	
	private long id;
	private User user;
	
	public Guest(){
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
