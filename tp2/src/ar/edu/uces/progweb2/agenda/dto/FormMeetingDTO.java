package ar.edu.uces.progweb2.agenda.dto;

import ar.edu.uces.progweb2.agenda.model.Hall;

public class FormMeetingDTO extends FormEventDTO {

	private String theme;
	private Hall hall;
	private String guests;

	public String getTheme() {
		return theme;
	}

	public String getGuests() {
		return guests;
	}

	public void setGuests(String guests) {
		this.guests = guests;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

}
