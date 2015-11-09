package ar.edu.uces.progweb2.agenda.boImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.edu.uces.progweb2.agenda.bo.EventBO;
import ar.edu.uces.progweb2.agenda.dao.EventDao;
import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;
import ar.edu.uces.progweb2.agenda.model.Event;
import ar.edu.uces.progweb2.agenda.transformer.EventTransformer;
import ar.edu.uces.progweb2.agenda.utils.CalendarUtils;

public class EventBOImpl implements EventBO{
	
	private EventDao eventDao;
	private EventTransformer transformer;
	
	
	@Override
	public void save(FormEventDTO formEventDTO) {
		Date startTime = CalendarUtils.getDate(formEventDTO.getStartTime());
		int index = this.eventDao.getIndex(startTime);
		Event event = this.transformer.tranformToEvent(formEventDTO, index);
		this.eventDao.save(event);
	}

	@Override
	public List<EventDTO> getEvents(Date date) {
		List<EventDTO> eventsDtos = new ArrayList<EventDTO>();
		List<Event> events = this.eventDao.getEvents(date);
		for(Event event : events){
			eventsDtos.add(this.transformer.tranformToEventDTO(event));
		}
		return eventsDtos;
	}

}