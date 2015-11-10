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
import ar.edu.uces.progweb2.agenda.dto.FormMeetingDTO;
import ar.edu.uces.progweb2.agenda.dto.FormPrivateEventDTO;
import ar.edu.uces.progweb2.agenda.model.Hall;
import ar.edu.uces.progweb2.agenda.model.User;
import ar.edu.uces.progweb2.agenda.service.EventService;
import ar.edu.uces.progweb2.agenda.service.HallService;
import ar.edu.uces.progweb2.agenda.utils.CalendarUtils;
import ar.edu.uces.progweb2.agenda.validator.PrivateEventValidator;
import ar.edu.uces.progweb2.agenda.validator.MeetingValidator;

@SessionAttributes("user")
@Controller
public class EventController {

	private HallService hallService;
	private EventService eventService;
	private PrivateEventValidator eventValidator;
	private MeetingValidator meetingValidator;

	@Autowired
	public void setEventValidator(PrivateEventValidator eventValidator) {
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

	@RequestMapping(value = "/newMeeting")
	public String newMeeting(ModelMap model) {
		List<Hall> halls = this.hallService.getHalls();
		model.addAttribute("formMeeting", new FormMeetingDTO());
		model.addAttribute("halls", halls);
		model.addAttribute("hours", this.getHours());
		return "/jsp/meeting.jsp";
	}
	
	@RequestMapping(value = "/newPrivateEvent")
	public String newPrivateEvent(ModelMap model) {
		model.addAttribute("hours", this.getHours());
		model.addAttribute("formPrivateEvent", new FormPrivateEventDTO());
		return "/jsp/privateEvent.jsp";
	}

	@RequestMapping(value = "/saveMeeting")
	public String saveMeeting(@ModelAttribute("formMeeting") FormMeetingDTO eventDTO,
			ModelMap model, BindingResult result) {
		this.meetingValidator.validate(eventDTO, result);
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("hours", this.getHours());
			model.addAttribute("halls", halls);
			return "/jsp/meeting.jsp";
		}
		User user = (User) model.get("user");
		eventDTO.setOwner(user);
		this.eventService.saveMeeting(eventDTO);
		return "/jsp/calendar.jsp";
	}

	@RequestMapping(value = "/savePrivateEvent")
	public String savePrivateEvent(
			@ModelAttribute("formPrivateEvent") FormPrivateEventDTO eventDTO,
			ModelMap model, BindingResult result) {
		this.eventValidator.validate(eventDTO, result);
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("halls", halls);
			model.addAttribute("hours", this.getHours());
			return "/jsp/meeting.jsp";
		}
		User user = (User) model.get("user");
		eventDTO.setOwner(user);
		this.eventService.savePrivateEvent(eventDTO);
		return "/jsp/calendar.jsp";
	}
	
	@RequestMapping(value = "/detailMeeting")
	public String detailMeeting(@RequestParam("id") Long id, ModelMap model) {
		FormMeetingDTO form = this.eventService.getFormMeetingDTO(id);
		List<Hall> halls = this.hallService.getHalls();
		model.addAttribute("formMeeting", form);
		model.addAttribute("halls", halls);
		model.addAttribute("hours", this.getHours());
		return "/jsp/meeting.jsp";
	}
	
	@RequestMapping(value = "/detailPrivateEvent")
	public String detailPrivateEvent(@RequestParam("id") Long id, ModelMap model) {
		FormPrivateEventDTO form = this.eventService.getFormPrivateEventDTO(id);
		model.addAttribute("formPrivateEvent", form);
		model.addAttribute("hours", this.getHours());
		return "/jsp/privateEvent.jsp";
	}
	
	@RequestMapping(value = "/updateMeeting")
	public String updateMeeting(@ModelAttribute("formMeeting") FormMeetingDTO eventDTO,
			ModelMap model, BindingResult result) {
		this.meetingValidator.validate(eventDTO, result);
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("halls", halls);
			model.addAttribute("hours", this.getHours());
			return "/jsp/meeting.jsp";
		}
		User user = (User) model.get("user");
		eventDTO.setOwner(user);
		this.eventService.updateMeeting(eventDTO);
		return "/jsp/calendar.jsp";
	}
	
	@RequestMapping(value = "/updatePrivateEvent")
	public String updatePrivateEvent(@ModelAttribute("formPrivateEvent") FormPrivateEventDTO eventDTO,
			ModelMap model, BindingResult result) {
		this.eventValidator.validate(eventDTO, result);
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("halls", halls);
			model.addAttribute("hours", this.getHours());
			return "/jsp/meeting.jsp";
		}
		User user = (User) model.get("user");
		eventDTO.setOwner(user);
		this.eventService.updatePrivateEvent(eventDTO);
		return "/jsp/calendar.jsp";
	}
	
	private Map<String, Object> getHours(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("1","00:00");
		map.put("2","00:30");
		map.put("3","01:00");
		map.put("4","01:30");
		map.put("5","02:00");
		map.put("6","02:30");
		map.put("7","03:00");
		map.put("8","03:30");
		map.put("9","04:00");
		map.put("10","04:30");
		map.put("11","05:00");
		map.put("12","05:30");
		map.put("13","06:00");
		map.put("14","06:30");
		map.put("15","07:00");
		map.put("16","07:30");
		map.put("17","08:00");
		map.put("18","08:30");
		map.put("19","09:00");
		map.put("20","09:30");
		map.put("21","10:00");
		map.put("22","10:30");
		map.put("23","11:00");
		map.put("24","11:30");
		map.put("25","12:00");
		map.put("26","12:30");
		map.put("27","13:00");
		map.put("28","13:30");
		map.put("29","14:00");
		map.put("30","14:30");
		map.put("31","15:00");
		map.put("32","15:30");
		map.put("33","16:00");
		map.put("34","16:30");
		map.put("35","17:00");
		map.put("36","17:30");
		map.put("37","18:00");
		map.put("38","18:30");
		map.put("39","19:00");
		map.put("40","19:30");
		map.put("41","20:00");
		map.put("42","20:30");
		map.put("43","21:00");
		map.put("44","21:30");
		map.put("45","22:00");
		map.put("46","22:30");
		map.put("47","23:00");
		map.put("48","23:30");
		return map;
	}

}
