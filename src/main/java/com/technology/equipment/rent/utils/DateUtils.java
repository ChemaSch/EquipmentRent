package com.technology.equipment.rent.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DateUtils {

	public static long getNumberOfDays(LocalDate startDate, LocalDate endDate) {
		return Duration.between(startDate.atStartOfDay(), endDate.atStartOfDay()).toDays();
	}

	public static LocalDate getAlternativeStartDate(LocalDate date) {
		return date.plusDays(1);
	}

	public static List<LocalDate> getDatesByRange(LocalDate startDate, LocalDate endDate) {
		List<LocalDate> dates = new ArrayList<>();
		Stream.iterate(startDate, date -> date.plusDays(1)).limit(ChronoUnit.DAYS.between(startDate, endDate) + 1)
				.forEach((f) -> {
					dates.add(f);
				});
		return dates;
	}

	public static boolean isDateBetweenOrEqual(LocalDate newStartDate, LocalDate newEndDate, LocalDate startDate,
			LocalDate endDate) {
		return ( (newStartDate.isEqual(startDate) || newStartDate.isAfter(startDate) || newStartDate.isBefore(startDate)) 
				&& 
				(newEndDate.isEqual(endDate) || newEndDate.isBefore(endDate) || newEndDate.isAfter(endDate)));
	}

	private DateUtils() {}

}
