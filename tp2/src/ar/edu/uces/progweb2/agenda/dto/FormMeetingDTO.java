package ar.edu.uces.progweb2.agenda.dto;

public class FormMeetingDTO extends FormEventDTO {

	private String theme;
	private Long hallId;
	private String guestsNames;
	private String guestsIds;
	private boolean isConfirm = false;
	private boolean isOwner = false;
	private boolean isGuest = false;
	
	

	public String getGuestsIds() {
		return guestsIds;
	}

	public void setGuestsIds(String guestsIds) {
		this.guestsIds = guestsIds;
	}

	public boolean getIsGuest() {
		return isGuest;
	}

	public void setIsGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}

	public boolean getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(boolean isConfirm) {
		this.isConfirm = isConfirm;
	}

	public boolean getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(boolean isOwner) {
		this.isOwner = isOwner;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public Long getHallId() {
		return hallId;
	}

	public void setHallId(Long hallId) {
		this.hallId = hallId;
	}

	public String getGuestsNames() {
		return guestsNames;
	}

	public void setGuestsNames(String guestsNames) {
		this.guestsNames = guestsNames;
	}

}
