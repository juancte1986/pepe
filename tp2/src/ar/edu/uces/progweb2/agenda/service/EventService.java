package ar.edu.uces.progweb2.agenda.service;

import java.util.Date;
import java.util.List;

import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;

public interface EventService {

	void saveEvent(FormEventDTO formEventDTO);

	List<EventDTO> getEvents(Date date);
	
}
