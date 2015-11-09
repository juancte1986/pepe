package ar.edu.uces.progweb2.agenda.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.uces.progweb2.agenda.bo.EventBO;
import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;
import ar.edu.uces.progweb2.agenda.service.EventService;

@Service("eventService")
public class EventServiceImpl implements EventService {
	
	private EventBO eventBO;
	
	@Autowired
	public void setEventBO(EventBO eventBO) {
		this.eventBO = eventBO;
	}

	@Override
	public void saveEvent(FormEventDTO formEventDTO) {
		this.eventBO.save(formEventDTO);
	}

	@Override
	public List<EventDTO> getEvents(Date date) {
		return this.eventBO.getEvents(date);
	}

}
