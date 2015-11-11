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
import org.springframework.web.bind.annotation.RequestMethod;
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
import ar.edu.uces.progweb2.agenda.validator.MeetingValidator;
import ar.edu.uces.progweb2.agenda.validator.PrivateEventValidator;

@SessionAttributes("user")
@Controller
public class EventController {

	private HallService hallService;
	private EventService eventService;
	private PrivateEventValidator privateEventValidator;
	private MeetingValidator meetingValidator;

	@Autowired
	public void setHallService(HallService hallService) {
		this.hallService = hallService;
	}

	@Autowired
	public void setEventValidator(PrivateEventValidator privateEventValidator) {
		this.privateEventValidator = privateEventValidator;
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
	@RequestMapping(value = "/getEvents", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getEvents(@RequestBody String dateString, ModelMap model) {
		User user = (User) model.get("user");
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = CalendarUtils.getDate(dateString);
		Date week[] = CalendarUtils.getWeek(date);
		List<EventDTO> eventsSunday = this.eventService.getEvents(week[0], user);
		List<EventDTO> eventsMonday = this.eventService.getEvents(week[1], user);
		List<EventDTO> eventsTuesday = this.eventService.getEvents(week[2], user);
		List<EventDTO> eventsWednesday = this.eventService.getEvents(week[3], user);
		List<EventDTO> eventsThursday = this.eventService.getEvents(week[4], user);
		List<EventDTO> eventsFriday = this.eventService.getEvents(week[5], user);
		List<EventDTO> eventsSaturday = this.eventService.getEvents(week[6], user);
		
		map.put("eventSunday", eventsSunday);
		map.put("eventsMonday", eventsMonday);
		map.put("eventsMonday", eventsTuesday);
		map.put("eventsMonday", eventsWednesday);
		map.put("eventsMonday", eventsThursday);
		map.put("eventsMonday", eventsFriday);
		map.put("eventsMonday", eventsSaturday);
		map.put("week", week);

		return map;
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
		model.addAttribute("hours", CalendarUtils.getHours());
		return "/jsp/meeting.jsp";
	}
	
	@RequestMapping(value = "/newPrivateEvent")
	public String newPrivateEvent(ModelMap model) {
		model.addAttribute("hours", CalendarUtils.getHours());
		model.addAttribute("formPrivateEvent", new FormPrivateEventDTO());
		return "/jsp/privateEvent.jsp";
	}

	@RequestMapping(value = "/saveMeeting")
	public String saveMeeting(@ModelAttribute("formMeeting") FormMeetingDTO eventDTO,
			ModelMap model, BindingResult result) {
		this.meetingValidator.validate(eventDTO, result);
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("hours", CalendarUtils.getHours());
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
		this.privateEventValidator.validate(eventDTO, result);
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("halls", halls);
			model.addAttribute("hours", CalendarUtils.getHours());
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
		model.addAttribute("hours", CalendarUtils.getHours());
		return "/jsp/meeting.jsp";
	}
	
	@RequestMapping(value = "/detailPrivateEvent")
	public String detailPrivateEvent(@RequestParam("id") Long id, ModelMap model) {
		FormPrivateEventDTO form = this.eventService.getFormPrivateEventDTO(id);
		model.addAttribute("formPrivateEvent", form);
		model.addAttribute("hours", CalendarUtils.getHours());
		return "/jsp/privateEvent.jsp";
	}
	
	@RequestMapping(value = "/updateMeeting")
	public String updateMeeting(@ModelAttribute("formMeeting") FormMeetingDTO eventDTO,
			ModelMap model, BindingResult result) {
		this.meetingValidator.validate(eventDTO, result);
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("halls", halls);
			model.addAttribute("hours", CalendarUtils.getHours());
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
		this.privateEventValidator.validate(eventDTO, result);
		if (result.hasErrors()) {
			List<Hall> halls = this.hallService.getHalls();
			model.addAttribute("halls", halls);
			model.addAttribute("hours", CalendarUtils.getHours());
			return "/jsp/meeting.jsp";
		}
		User user = (User) model.get("user");
		eventDTO.setOwner(user);
		this.eventService.updatePrivateEvent(eventDTO);
		return "/jsp/calendar.jsp";
	}

}
