package icebreaker.getmeholidaylist.service;

import java.time.DateTimeException;
import java.time.YearMonth;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import icebreaker.getmeholidaylist.client.CalendarificClient;
import icebreaker.getmeholidaylist.dto.CalendarificResponse;
import icebreaker.getmeholidaylist.dto.Holiday;
import icebreaker.getmeholidaylist.dto.HolidayDto;
import icebreaker.getmeholidaylist.exception.InvalidRequestException;

@Service
public class HolidayService {

	private final CalendarificClient calendarificClient;

	public HolidayService(CalendarificClient calendarificClient) {
		this.calendarificClient = calendarificClient;
	}

	public List<HolidayDto> getHolidays(String country, int year, Integer month, Integer day, String type) {

		validateRequest(country, year, month, day);

		CalendarificResponse response = calendarificClient.getHolidays(country, year, month, day, type);

		if (response == null || response.getResponse() == null || response.getResponse().getHolidays() == null) {
			return Collections.emptyList();
		}

		return response.getResponse().getHolidays().stream()
		        .map(this::toDto)
		        .filter(dto -> dto.getDate() != null && !dto.getDate().isBlank()) // safety
		        .collect(Collectors.toMap(
		                HolidayDto::getDate,          // key = date (yyyy-MM-dd)
		                dto -> dto,                   // value
		                (first, duplicate) -> first,  // keep first one if same date repeats
		                java.util.LinkedHashMap::new  // preserve original order
		        ))
		        .values()
		        .stream()
		        .collect(Collectors.toList());
	}
	
	private void validateRequest(String country, int year, Integer month, Integer day) {

		if (country == null || country.isBlank()) {
			throw new InvalidRequestException("Country code is required.");
		}
		if (country.length() != 2) {
			throw new InvalidRequestException("Country code must be a 2-letter ISO code, e.g. 'CA'.");
		}

		// Reasonable year range; adjust if you want
		if (year < 1900 || year > 2100) {
			throw new InvalidRequestException("Year must be between 1900 and 2100.");
		}

		if (month != null) {
			if (month < 1 || month > 12) {
				throw new InvalidRequestException("Invalid month: " + month + ". Allowed range is 1–12.");
			}

			if (day != null) {
				if (day < 1) {
					throw new InvalidRequestException("Invalid day: " + day + ". Day must be >= 1.");
				}
				// validate real calendar date, not just 1–31
				try {
					YearMonth ym = YearMonth.of(year, month);
					int maxDay = ym.lengthOfMonth();
					if (day > maxDay) {
						throw new InvalidRequestException("Invalid date: " + year + "-" + month + "-" + day
								+ ". Max day for this month is " + maxDay + ".");
					}
				} catch (DateTimeException e) {
					throw new InvalidRequestException(
							"Invalid date values: year=" + year + ", month=" + month + ", day=" + day);
				}
			}
		} else if (day != null) {
			// day provided without month – ambiguous
			throw new InvalidRequestException("Day cannot be provided without month.");
		}
	}

	private HolidayDto toDto(Holiday holiday) {
		String countryCode = holiday.getCountry() != null ? holiday.getCountry().getId() : null;
		String countryName = holiday.getCountry() != null ? holiday.getCountry().getName() : null ;
		String dateIso = holiday.getDate() != null ? holiday.getDate().getIso() : null;

		return new HolidayDto(holiday.getName(), holiday.getDescription(), countryCode, countryName, dateIso,
				holiday.getType());
	}

	
}
