package ar.edu.uces.progweb2.agenda.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.uces.progweb2.agenda.dto.FormCalendarDTO;
import ar.edu.uces.progweb2.agenda.utils.CalendarUtils;

@Controller
public class CalendarController {

	@RequestMapping(value = "/initWeek")
	public @ResponseBody Map<String, Object> initWeek(@RequestBody FormCalendarDTO form) {
		Map<String, Object> map = new HashMap<String, Object>();
		Date date = CalendarUtils.getDate(form.getActualPage(), form.getDate());
		Date[] week = CalendarUtils.getWeek(date);
		map.put("week", week);
		return map;
	}
}
