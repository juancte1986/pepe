package ar.edu.uces.progweb2.agenda.dao;

import java.util.Date;
import java.util.List;

import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.model.Event;

public interface EventDao extends GenericDao<Event>{
	
	public int getIndex(Date starTime);

	public List<Event> getEvents(Date date);

}
