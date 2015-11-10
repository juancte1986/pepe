package ar.edu.uces.progweb2.agenda.dao;

import java.util.Date;
import java.util.List;

import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.model.Event;
import ar.edu.uces.progweb2.agenda.model.Meeting;
import ar.edu.uces.progweb2.agenda.model.User;

public interface EventDao extends GenericDao<Event>{

	public List<Event> getEvents(Date date, User user);

}
