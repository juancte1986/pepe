package ar.edu.uces.progweb2.agenda.boImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.agenda.bo.EventBO;
import ar.edu.uces.progweb2.agenda.dao.EventDao;
import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.dto.FormPrivateEventDTO;
import ar.edu.uces.progweb2.agenda.model.Event;
import ar.edu.uces.progweb2.agenda.model.PrivateEvent;
import ar.edu.uces.progweb2.agenda.model.User;
import ar.edu.uces.progweb2.agenda.transformer.EventTransformer;
import ar.edu.uces.progweb2.agenda.utils.CalendarUtils;

@Component("eventBO")
public class EventBOImpl implements EventBO{
	
	private EventDao eventDao;
	private EventTransformer transformer;

	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}
	
	@Autowired
	public void setTransformer(EventTransformer transformer) {
		this.transformer = transformer;
	}

	@Override
	public List<EventDTO> getEvents(Date date, User user) {
		List<EventDTO> eventsDtos = new ArrayList<EventDTO>();
		List<Event> events = this.eventDao.getEvents(date, user);
		for(Event event : events){
			eventsDtos.add(this.transformer.tranformToEventDTO(event));
		}
		return eventsDtos;
	}

	@Override
	public void saveMeeting(FormMeetingDTO formMeetingDTO) {
		this.eventDao.save(this.transformer.tranformToMeeting(formMeetingDTO));
	}

	@Override
	public void savePrivateEvent(FormPrivateEventDTO formPrivateEventDTO) {
		PrivateEvent event = (PrivateEvent) this.transformer.tranformToPrivateEvent(formPrivateEventDTO);
		this.eventDao.save(event);
	}

	@Override
	public FormPrivateEventDTO getFormPrivateEventDTO(Long id) {
		return this.transformer.tranformToFormPrivateEventDTO(this.eventDao.getById(id));
	}

	@Override
	public FormMeetingDTO getFormMeetingDTO(Long id) {
		return this.transformer.tranformToFormMeetingDTO(this.eventDao.getById(id));
	}

	@Override
	public void updateMeeting(FormMeetingDTO formMeetingDTO) {
		this.eventDao.update(this.transformer.tranformToMeeting(formMeetingDTO));
	}

	@Override
	public void updatePrivateEvent(FormPrivateEventDTO formPrivateEventDTO) {
		this.eventDao.update(this.transformer.tranformToPrivateEvent(formPrivateEventDTO));
	}

}
