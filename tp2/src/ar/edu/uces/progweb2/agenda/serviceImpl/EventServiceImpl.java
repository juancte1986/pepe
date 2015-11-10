package ar.edu.uces.progweb2.agenda.serviceImpl;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ar.edu.uces.progweb2.agenda.bo.EventBO;
import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.dto.FormPrivateEventDTO;
import ar.edu.uces.progweb2.agenda.service.EventService;

@Service("eventService")
public class EventServiceImpl implements EventService {
	
	private EventBO eventBO;
	
	@Autowired
	public void setEventBO(EventBO eventBO) {
		this.eventBO = eventBO;
	}

	@Override
	public void saveMeeting(FormMeetingDTO formMeetingDTO) {
		this.eventBO.saveMeeting(formMeetingDTO);
	}

	@Override
	public void savePrivateEvent(FormPrivateEventDTO formPrivateEventDTO) {
		this.eventBO.savePrivateEvent(formPrivateEventDTO);
	}

	@Override
	public FormPrivateEventDTO getFormPrivateEventDTO(Long id) {
		return this.eventBO.getFormPrivateEventDTO(id);
	}
	
	@Override
	public FormMeetingDTO getFormMeetingDTO(Long id) {
		return this.eventBO.getFormMeetingDTO(id);
	}

	@Override
	public void updateMeeting(FormMeetingDTO eventDTO) {
		this.eventBO.updateMeeting(eventDTO);
	}

	@Override
	public void updatePrivateEvent(FormPrivateEventDTO eventDTO) {
		this.eventBO.updatePrivateEvent(eventDTO);
	}

	@Override
	public List<EventDTO> getEvents(Date date) {
		return this.eventBO.getEvents(date);
	}
}
