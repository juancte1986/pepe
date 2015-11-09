package ar.edu.uces.progweb2.agenda.transformer;

import java.util.Date;

import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;
import ar.edu.uces.progweb2.agenda.model.Event;
import ar.edu.uces.progweb2.agenda.model.Meeting;
import ar.edu.uces.progweb2.agenda.model.PrivateEvent;

@Component
public class EventTransformer {
	
	public Event tranformToEvent(FormEventDTO formEventDTO, int index) {
		Event event = null; 
		if(formEventDTO.getTipo().equals("meeting")){
			event = new Meeting();
		} else if (formEventDTO.getTipo().equals("privateEvent")){
			event = new PrivateEvent();
		}
		event.setDate(new Date());
		event.setIndex(index);
		return event;
	}

	public EventDTO tranformToEventDTO(Event event) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setEndTime(event.getEndTime().toString());
		eventDTO.setIndex(event.getIndex().toString());
		eventDTO.setName(event.getName());
		eventDTO.setTipo(event.getTipo());
		return eventDTO;
	}

}
