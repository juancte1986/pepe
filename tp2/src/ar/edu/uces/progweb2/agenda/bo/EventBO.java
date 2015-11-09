package ar.edu.uces.progweb2.agenda.bo;

import java.util.Date;
import java.util.List;

import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;

public interface EventBO {

	public void save(FormEventDTO formEventDTO);

	public List<EventDTO> getEvents(Date date);

}
