package ar.edu.uces.progweb2.agenda.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormEventDTO;
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.dto.FormPrivateEventDTO;
import ar.edu.uces.progweb2.agenda.model.Hall;
import ar.edu.uces.progweb2.agenda.model.User;
import ar.edu.uces.progweb2.agenda.service.EventService;
import ar.edu.uces.progweb2.agenda.service.HallService;
import ar.edu.uces.progweb2.agenda.utils.CalendarUtils;
import ar.edu.uces.progweb2.agenda.validator.EventValidator;
import ar.edu.uces.progweb2.agenda.validator.MeetingValidator;

@SessionAttributes("user")
@Controller
public class EventController {

	private HallService hallService;
	private EventService eventService;
	private EventValidator eventValidator;
	private MeetingValidator meetingValidator;

	@Autowired
	public void setEventValidator(EventValidator eventValidator) {
		this.eventValidator = eventValidator;
	}

	@Autowired
	public void setMeetingValidator(MeetingValidator meetingValidator) {
		this.meetingValidator = meetingValidator;
	}

	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	// asincronicos
	@RequestMapping(value = "getEvents")
	public @ResponseBody
	Map<String, Object> getEvents(@RequestBody Date date) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date week[] = CalendarUtils.getWeek(date);
		List<EventDTO> eventsSunday = this.eventService.getEvents(week[0]);
		List<EventDTO> eventsMonday = this.eventService.getEvents(week[1]);
		List<EventDTO> eventsTuesday = this.eventService.getEvents(week[2]);
		List<EventDTO> eventsWednesday = this.eventService.getEvents(week[3]);
		List<EventDTO> eventsThursday = this.eventService.getEvents(week[4]);
		List<EventDTO> eventsFriday = this.eventService.getEvents(week[5]);
		List<EventDTO> eventsSaturday = this.eventService.getEvents(week[6]);
		
		map.put("eventSunday", eventsSunday);
		map.put("eventsMonday", eventsMonday);
		map.put("eventsMonday", eventsTuesday);
		map.put("eventsMonday", eventsWednesday);
		map.put("eventsMonday", eventsThursday);
		map.put("eventsMonday", eventsFriday);
		map.put("eventsMonday", eventsSaturday);
		map.put("week", week);

		return null;
	}

	@RequestMapping(value = "eventSwitchTime/{id}/")
	public @ResponseBody
	Map<String, Object> eventSwitchTime(@PathVariable("id") long id) {
		return null;
	}

	// sincronicos

	@RequestMapping(value = "/newEvent")
	public String initNewEvent(@RequestParam("event") String event,
			ModelMap model) {
		if (event.equals("meeting")) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("formMeeting", new FormMeetingDTO());
			model.addAttribute("halls", halls);
			return "/jsp/meeting.jsp";
		} else if (event.equals("privateEvent")) {
			model.addAttribute("formPrivateEvent", new FormPrivateEventDTO());
			return "/jsp/privateEvent.jsp";
		} else {
			model.addAttribute("error", true);
			return "/jsp/calendar.jsp";
		}
	}

	@RequestMapping(value = "/saveMeeting")
	public String saveMeeting(
			@ModelAttribute("formMeeting") FormEventDTO eventDTO,
			ModelMap model, BindingResult result) {
		this.eventValidator.validate(eventDTO, result);
		this.meetingValidator.validate(eventDTO, result);
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("halls", halls);
			return "/jsp/meeting.jsp";
		}
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("halls", halls);
			return "/jsp/meeting.jsp";
		}
		User user = (User) model.get("user");
		eventDTO.setOwner(user);
		eventDTO.setTipo("meeting");
		this.eventService.saveEvent(eventDTO);
		return "/jsp/calendar.jsp";
	}

	@RequestMapping(value = "/savePrivateEvent")
	public String savePrivateEvent(
			@ModelAttribute("formPrivateEvent") FormEventDTO eventDTO,
			ModelMap model, BindingResult result) {
		this.eventValidator.validate(eventDTO, result);
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("halls", halls);
			return "/jsp/meeting.jsp";
		}
		User user = (User) model.get("user");
		eventDTO.setOwner(user);
		eventDTO.setTipo("privateEvent");
		this.eventService.saveEvent(eventDTO);
		return "/jsp/calendar.jsp";
	}

	public String detailEvent() {
		return null;
	}

	public String updateMeeting() {
		return "/jsp/calendar.jsp";
	}

	public String updatePrivateEvent() {
		return "/jsp/calendar.jsp";
	}

}
