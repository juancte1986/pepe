package ar.edu.uces.progweb2.agenda.transformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.agenda.dao.HallDao;
import ar.edu.uces.progweb2.agenda.dao.UserDao;
import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.dto.FormPrivateEventDTO;
import ar.edu.uces.progweb2.agenda.model.Event;
import ar.edu.uces.progweb2.agenda.model.Guest;
import ar.edu.uces.progweb2.agenda.model.HallMeeting;
import ar.edu.uces.progweb2.agenda.model.Meeting;
import ar.edu.uces.progweb2.agenda.model.PrivateEvent;
import ar.edu.uces.progweb2.agenda.model.User;
import ar.edu.uces.progweb2.agenda.utils.CalendarUtils;

@Component
public class EventTransformer {
	
	private UserDao userDao;
	private HallDao hallDao;
	
	@Autowired
	public void setHallDao(HallDao hallDao) {
		this.hallDao = hallDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Event tranformToEvent(FormEventDTO formEventDTO, int index) {
		Event event = null; 
		if(formEventDTO.getTipo().equals("meeting")){
			event = new Meeting();
		} else if (formEventDTO.getTipo().equals("privateEvent")){
			event = new PrivateEvent();
		}
		event.setDate(new Date());
		return event;
	}

	public EventDTO tranformToEventDTO(Event event) {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setEndTime(event.getEndTime().toString());
		eventDTO.setName(event.getName());
		return eventDTO;
	}

	public Meeting tranformToMeeting(FormMeetingDTO formMeetingDTO) {
		Meeting event = new Meeting();
		if(formMeetingDTO.getId() != null ){
			event.setId(formMeetingDTO.getId());
		}
		event.setDate(CalendarUtils.getDate(formMeetingDTO.getDate()));
		event.setName(formMeetingDTO.getName());
		event.setOwner(formMeetingDTO.getOwner());
		event.setTheme(formMeetingDTO.getTheme());
		event.setGuests(this.getGuests(formMeetingDTO.getGuestsIds()));
		String start= formMeetingDTO.getDate()+ " " + formMeetingDTO.getStartTime(); 
		String end=  formMeetingDTO.getDate()+ " " + formMeetingDTO.getEndTime();
		Date startTime = CalendarUtils.getDateTime(start);
		Date endTime = CalendarUtils.getDateTime(end);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		event.setHallMeeting(new HallMeeting(this.hallDao.getById(formMeetingDTO.getHallId())));
		return event;
	}
	
	private Set<Guest> getGuests(String guestsIds){
		String args[] = guestsIds.split(",");
		Set<Guest> guests = new HashSet<Guest>();
		List<User> users = this.userDao.getUsers(args);
		for(User user : users){
			Guest guest = new Guest();
			guest.setUser(user);
			guests.add(guest);
		}
		return guests;
	}

	public PrivateEvent tranformToPrivateEvent(FormPrivateEventDTO formPrivateEventDTO) {
		PrivateEvent event = new PrivateEvent();
		if(formPrivateEventDTO.getId() != null ){
			event.setId(formPrivateEventDTO.getId());
		}	
		event.setDate(CalendarUtils.getDate(formPrivateEventDTO.getDate()));
		event.setName(formPrivateEventDTO.getName());
		event.setOwner(formPrivateEventDTO.getOwner());
		event.setDescription(formPrivateEventDTO.getDescription());
		event.setAddress(formPrivateEventDTO.getAddress());
		String start= formPrivateEventDTO.getDate()+ " " + formPrivateEventDTO.getStartTime(); 
		String end=  formPrivateEventDTO.getDate()+ " " + formPrivateEventDTO.getEndTime();
		Date startTime = CalendarUtils.getDateTime(start);
		Date endTime = CalendarUtils.getDateTime(end);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		
		return event;
	}

	public FormPrivateEventDTO tranformToFormPrivateEventDTO(Event event) {
		PrivateEvent privateEvent = (PrivateEvent) event;
		FormPrivateEventDTO form = new FormPrivateEventDTO();
		form.setId(privateEvent.getId());
		form.setAddress(privateEvent.getAddress());
		form.setDescription(privateEvent.getDescription());
		form.setId(privateEvent.getId());
		form.setName(privateEvent.getName());
		form.setOwner(privateEvent.getOwner());
		form.setStartTime(CalendarUtils.getTime(privateEvent.getStartTime()));
		form.setEndTime(CalendarUtils.getTime(privateEvent.getEndTime()));
		form.setDate(CalendarUtils.convertDateToString(privateEvent.getDate()));
		
		return form;
	}

	public FormMeetingDTO tranformToFormMeetingDTO(Event event) {
		Meeting meeting = (Meeting) event;
		FormMeetingDTO form = new FormMeetingDTO();
		form.setId(meeting.getId());
		form.setName(meeting.getName());
		form.setOwner(meeting.getOwner());
		form.setStartTime(CalendarUtils.getTime(meeting.getStartTime()));
		form.setEndTime(CalendarUtils.getTime(meeting.getEndTime()));
		form.setDate(CalendarUtils.convertDateToString(meeting.getDate()));
		form.setHallId(meeting.getHallMeeting().getHall().getId());
		form.setTheme(meeting.getTheme());
		form.setGuestsIds(this.getGuestsIds(meeting.getGuests()));
		form.setGuestsNames(this.getGuestsNames(meeting.getGuests()));
		
		return form;
	}

	private List<String> getGuestsNames(Set<Guest> guests) {
		List<String> names = new ArrayList<String>();
		for(Guest guest: guests){
			User user = guest.getUser();
			names.add(user.getName() +" "+user.getSurname()+" ("+user.getUser()+"),"+guest.getId() ); 
		}
		return names;
	}

	private String getGuestsIds(Set<Guest> guests) {
		String cadena="";
		Guest [] array = (Guest[]) guests.toArray();
		for(int i = 0; i < array.length; i++){
			if(i == (array.length - 1)){
				cadena +=array[i].getId().toString();
			} else {
				cadena +=array[i].getId().toString()+",";
			}
		}
		return cadena;
	}

}
