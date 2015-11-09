package ar.edu.uces.progweb2.agenda.dto;

import java.util.Date;

public class FormCalendarDTO {

	private Date date;
	private int actualPage; // actual page solo puede tomar valores 0, 1 y -1

	public FormCalendarDTO() {

	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getActualPage() {
		return actualPage;
	}

	public void setActualPage(int actualPage) {
		this.actualPage = actualPage;
	}

}
