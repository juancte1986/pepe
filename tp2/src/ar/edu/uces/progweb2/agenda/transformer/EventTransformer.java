package ar.edu.uces.progweb2.agenda.transformer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.uces.progweb2.agenda.dao.EventDao;
import ar.edu.uces.progweb2.agenda.dao.HallDao;
import ar.edu.uces.progweb2.agenda.dao.UserDao;
import ar.edu.uces.progweb2.agenda.dto.FormDragEventDTO;
import ar.edu.uces.progweb2.agenda.dto.DargEventDTO;
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
import ar.edu.uces.progweb2.agenda.utils.EventUtils;
import ar.edu.uces.progweb2.agenda.utils.JsonUtils;

@Component
public class EventTransformer {

	private UserDao userDao;
	private HallDao hallDao;
	private EventDao eventDao;

	@Autowired
	public void setEventDao(EventDao eventDao) {
		this.eventDao = eventDao;
	}

	@Autowired
	public void setHallDao(HallDao hallDao) {
		this.hallDao = hallDao;
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//list event for calendar ajax
	public DargEventDTO tranformToDragEventDTO(PrivateEvent event) {
		DargEventDTO dragEvent = new DargEventDTO();
		dragEvent.setStartTime(CalendarUtils.getTime(event.getStartTime()));
		dragEvent.setEndTime(CalendarUtils.getTime(event.getEndTime()));
		dragEvent.setName(event.getName());
		dragEvent.setTop(EventUtils.getTop(CalendarUtils.getTime(event
				.getStartTime())));
		dragEvent.setHeight(EventUtils.getHeight(
				CalendarUtils.getTime(event.getStartTime()),
				CalendarUtils.getTime(event.getEndTime())));
		dragEvent.setIsOwner(true);
		return dragEvent;
	}
	
	//list event for calendar ajax
	public DargEventDTO tranformToDragEventDTO(Meeting meeting, User user) {
		DargEventDTO dragEvent = new DargEventDTO();
		dragEvent.setStartTime(CalendarUtils.getTime(meeting.getStartTime()));
		dragEvent.setEndTime(CalendarUtils.getTime(meeting.getEndTime()));
		dragEvent.setName(meeting.getName());
		dragEvent.setTop(EventUtils.getTop(CalendarUtils.getTime(meeting
				.getStartTime())));
		dragEvent.setHeight(EventUtils.getHeight(
				CalendarUtils.getTime(meeting.getStartTime()),
				CalendarUtils.getTime(meeting.getEndTime())));
		if(meeting.getId() == user.getId()){
			dragEvent.setIsOwner(true);
		} else {
			dragEvent.setIsGuest(true);
			if((isConfirmGuest(meeting.getGuests(), user.getId()))){
				dragEvent.setIsConfirm(true);
			}
		}
		return dragEvent;
	}
	
	//save and update an event
	public Meeting tranformToMeeting(FormMeetingDTO formMeetingDTO) {
		Meeting event;
		if (formMeetingDTO.getId() != null) {
			event = (Meeting) this.eventDao.getById(formMeetingDTO.getId());
		} else{
			event = new Meeting();
		}
		event.setDate(CalendarUtils.getDate(formMeetingDTO.getDate()));
		event.setName(formMeetingDTO.getName());
		event.setOwner(formMeetingDTO.getUserLogin());
		event.setTheme(formMeetingDTO.getTheme());
		event.setGuests(this.getGuests(formMeetingDTO.getGuestsIds()));
		String start = formMeetingDTO.getDate() + " "
				+ formMeetingDTO.getStartTime();
		String end = formMeetingDTO.getDate() + " "
				+ formMeetingDTO.getEndTime();
		Date startTime = CalendarUtils.getDateTime(start);
		Date endTime = CalendarUtils.getDateTime(end);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		event.setHall(this.hallDao.getById(formMeetingDTO.getHallId()));
		return event;
	}
	
	//save and update an event
	public PrivateEvent tranformToPrivateEvent(FormPrivateEventDTO formPrivateEventDTO) {
		PrivateEvent event;  new PrivateEvent();
		if (formPrivateEventDTO.getId() != null) {
			event = (PrivateEvent) this.eventDao.getById(formPrivateEventDTO.getId());
		} else {
			event = new PrivateEvent();
			event.setOwner(formPrivateEventDTO.getUserLogin());
		}
		event.setDate(CalendarUtils.getDate(formPrivateEventDTO.getDate()));
		event.setName(formPrivateEventDTO.getName());
		event.setDescription(formPrivateEventDTO.getDescription());
		event.setAddress(formPrivateEventDTO.getAddress());
		String start = formPrivateEventDTO.getDate() + " "
				+ formPrivateEventDTO.getStartTime();
		String end = formPrivateEventDTO.getDate() + " "
				+ formPrivateEventDTO.getEndTime();
		Date startTime = CalendarUtils.getDateTime(start);
		Date endTime = CalendarUtils.getDateTime(end);
		event.setStartTime(startTime);
		event.setEndTime(endTime);

		return event;
	}
	
	// completa el form del evento privado para editar
	public FormPrivateEventDTO tranformToFormPrivateEventDTO(Event event) {
		PrivateEvent privateEvent = (PrivateEvent) event;
		FormPrivateEventDTO form = new FormPrivateEventDTO();
		form.setId(privateEvent.getId());
		form.setAddress(privateEvent.getAddress());
		form.setDescription(privateEvent.getDescription());
		form.setId(privateEvent.getId());
		form.setName(privateEvent.getName());
		form.setStartTime(CalendarUtils.getTime(privateEvent.getStartTime()));
		form.setEndTime(CalendarUtils.getTime(privateEvent.getEndTime()));
		form.setDate(CalendarUtils.convertDateToString(privateEvent.getDate()));

		return form;
	}
	
	// completa el form de la meeting para editar
	public FormMeetingDTO tranformToFormMeetingDTO(Meeting meeting, User user) {
		FormMeetingDTO form = new FormMeetingDTO();
		form.setId(meeting.getId());
		form.setName(meeting.getName());
		form.setStartTime(CalendarUtils.getTime(meeting.getStartTime()));
		form.setEndTime(CalendarUtils.getTime(meeting.getEndTime()));
		form.setDate(CalendarUtils.convertDateToString(meeting.getDate()));
		form.setHallId(meeting.getHall().getId());
		form.setTheme(meeting.getTheme());
		form.setGuestsIds(this.getGuestsIds(meeting.getGuests()));
		form.setGuestsNames(JsonUtils.serializer(this.getGuestsNames(meeting.getGuests())));
		if(meeting.getOwner().getId() == user.getId()){
			form.setIsOwner(true);
		} else {
			form.setIsGuest(true);
			if(isConfirmGuest(meeting.getGuests(), user.getId())){
				form.setIsConfirm(true);
			}
		}
		return form;
	}

	

	private boolean isConfirmGuest(Set<Guest> guests, Long id) {
		for(Guest guest : guests){
			if(guest.getUser().getId() == id){
				return guest.getConfirm();
			}
		}
		return false;
	}

	public Event tranformToEvent(FormDragEventDTO drag) {
		Event event = this.eventDao.getById(new Long(drag.getId()));
		Date startTime = CalendarUtils
				.getDate(EventUtils.getTime(drag.getTop()));
		Date endTime = CalendarUtils
				.getDate(EventUtils.getTime(drag.getFflat()));
		event.setEndTime(endTime);
		event.setStartTime(startTime);
		return event;
	}
	
	private Set<Guest> getGuests(String guestsIds) {
		String arg[] = guestsIds.split(",");
		List<Long> ids = this.getArrayLong(arg);
		Set<Guest> guests = new HashSet<Guest>();
		List<User> users = this.userDao.getUsers(ids);
		for (User user : users) {
			Guest guest = new Guest();
			guest.setUser(user);
			guests.add(guest);
		}
		return guests;
	}
	
	private List<Long> getArrayLong(String[] ids) {
		List<Long> array  = new ArrayList<Long>();
		for(int i = 0; i<ids.length ; i++){
			array.add(new Long(ids[i]));
		}
		return array;
	}

	private List<String> getGuestsNames(Set<Guest> guests) {
		List<String> names = new ArrayList<String>();
		for (Guest guest : guests) {
			User user = guest.getUser();
			names.add(user.getName() + " " + user.getSurname() + " ("
					+ user.getUser() + ")," + user.getId().toString());
		}
		return names;
	}

	private String getGuestsIds(Set<Guest> guests) {
		String cadena = "";
		int cont = 0;
		for (Guest guest : guests) {
			if (cont == (guests.size() - 1)) {
				cadena += guest.getUser().getId().toString();
			} else {
				cadena += guest.getUser().getId().toString() + ",";
			}
		}
		return cadena;
	}
	

}
