package ar.edu.uces.progweb2.agenda.bo;

import java.util.Date;
import java.util.List;

import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.dto.FormPrivateEventDTO;

public interface EventBO {

	public void save(FormEventDTO formEventDTO);

	public List<EventDTO> getEvents(Date date);

	public void saveMeeting(FormMeetingDTO formMeetingDTO);

	public void savePrivateEvent(FormPrivateEventDTO formPrivateEventDTO);

	public FormPrivateEventDTO getFormPrivateEventDTO(Long id);

	public FormMeetingDTO getFormMeetingDTO(Long id);

	public void updateMeeting(FormMeetingDTO eventDTO);

	public void updatePrivateEvent(FormPrivateEventDTO eventDTO);

}
