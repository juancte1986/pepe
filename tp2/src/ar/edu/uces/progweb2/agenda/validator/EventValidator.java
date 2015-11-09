package ar.edu.uces.progweb2.agenda.validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ar.edu.uces.progweb2.agenda.dto.EventDTO;
import ar.edu.uces.progweb2.agenda.model.User;

@Controller
public class EventValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name",
				"errors.event.name.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startTime",
				"errors.event.startTime.empty");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime",
				"errors.event.endTime.empty");
		if (errors.hasFieldErrors()) {
			EventDTO event = (EventDTO) obj;
			if (!this.isValidDate(event.getStartTime())) {
				errors.rejectValue("startTime", "errors.event.date.format");
			}
			if (!this.isValidDate(event.getStartTime())) {
				errors.rejectValue("endTime", "errors.event.date.format");
			}

			if (errors.hasFieldErrors("startTime")
					|| errors.hasFieldErrors("startTime")) {
				if (!this.isAfterDate(event.getStartTime(), event.getEndTime())) {
					errors.rejectValue("endTime", "errors.event.date.after");
				}
			}
		}

	}

	private boolean isValidDate(String fecha) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat(
					"dd/MM/yyyyHHmmss", Locale.getDefault());
			formatoFecha.setLenient(false);
			formatoFecha.parse(fecha);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	private boolean isAfterDate(String startTime, String endTime) {
		SimpleDateFormat formatoFecha = new SimpleDateFormat(
				"dd/MM/yyyyHHmmss", Locale.getDefault());
		Date start = new Date();
		Date end = new Date();
		try {
			start = formatoFecha.parse(startTime);
			end = formatoFecha.parse(endTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return end.after(start);
	}

}
