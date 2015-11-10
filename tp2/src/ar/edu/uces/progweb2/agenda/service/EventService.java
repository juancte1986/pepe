package ar.edu.uces.progweb2.agenda.service;

import java.util.Date;
import java.util.List;

import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.dto.FormPrivateEventDTO;

public interface EventService {

	public void saveMeeting(FormMeetingDTO formMeetingDTO);
	public void savePrivateEvent(FormPrivateEventDTO formPrivateEventDTO);
	public List<EventDTO> getEvents(Date date);
	public FormMeetingDTO getFormMeetingDTO(Long id);
	public FormPrivateEventDTO getFormPrivateEventDTO(Long id);
	public void updateMeeting(FormMeetingDTO eventDTO);
	public void updatePrivateEvent(FormPrivateEventDTO eventDTO);
	
}
