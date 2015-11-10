package ar.edu.uces.progweb2.agenda.dto;

import java.util.List;

import ar.edu.uces.progweb2.agenda.model.Hall;

public class FormMeetingDTO extends FormEventDTO {

	private String theme;
	private Hall hall;
	private String guestsIds;
	private List<String> guestsNames;

	public String getGuestsIds() {
		return guestsIds;
	}

	public void setGuestsIds(String guestsIds) {
		this.guestsIds = guestsIds;
	}

	public List<String> getGuestsNames() {
		return guestsNames;
	}

	public void setGuestsNames(List<String> guestsNames) {
		this.guestsNames = guestsNames;
	}

	public String getTheme() {
		return theme;
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
