package ar.edu.uces.progweb2.agenda.service;

import java.util.Date;
import java.util.List;

import ar.edu.uces.progweb2.agenda.dto.FormDragEventDTO;
import ar.edu.uces.progweb2.agenda.dto.DargEventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.dto.FormPrivateEventDTO;
import ar.edu.uces.progweb2.agenda.exception.BackendException;
import ar.edu.uces.progweb2.agenda.model.User;

public interface EventService {

	public void saveMeeting(FormMeetingDTO formMeetingDTO);
	public void savePrivateEvent(FormPrivateEventDTO formPrivateEventDTO);
	public List<DargEventDTO> getEvents(Date date, User user);
	public FormMeetingDTO getFormMeetingDTO(Long id, User user);
	public FormPrivateEventDTO getFormPrivateEventDTO(Long id);
	public void updateMeeting(FormMeetingDTO eventDTO);
	public void updatePrivateEvent(FormPrivateEventDTO eventDTO);
	public void update(FormDragEventDTO drag) throws BackendException;
	public void delete(Long id);
	public void setConfirmGuest(FormMeetingDTO eventDTO);
	
}
