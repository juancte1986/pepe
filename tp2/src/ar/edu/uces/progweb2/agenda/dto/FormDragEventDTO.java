package ar.edu.uces.progweb2.agenda.dto;

import ar.edu.uces.progweb2.agenda.model.User;

public class FormDragEventDTO {
	// evento que viajan del div
	private String id;
	private User userLogin;
	private int top;
	private int event;
	private int height; // lo obtengo de div

	public User getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(User userLogin) {
		this.userLogin = userLogin;
	}

	private int fflat;

	public int getFflat() {
		return fflat;
	}

	public void setFflat(int fflat) {
		this.fflat = fflat;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}
}
