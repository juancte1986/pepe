package ar.edu.uces.progweb2.agenda.model;

public class Guest {
	
	private Long id;
	private User user;
	
	public Guest(){
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
