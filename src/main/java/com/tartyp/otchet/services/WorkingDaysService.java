package com.tartyp.otchet.services;

import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;

@Service
public class WorkingDaysService {

    private static final Set<LocalDate> HOLIDAYS;

    static {
        List<LocalDate> dates = Arrays.asList(
                LocalDate.of(2017, 1, 2),
                LocalDate.of(2017, 1, 16),
                LocalDate.of(2017, 2, 20),
                LocalDate.of(2017, 5, 29),
                LocalDate.of(2017, 7, 4),
                LocalDate.of(2017, 9, 4),
                LocalDate.of(2017, 10, 9),
                LocalDate.of(2017, 11, 10),
                LocalDate.of(2017, 11, 23),
                LocalDate.of(2017, 12, 25)
        );
        HOLIDAYS = Collections.unmodifiableSet(new HashSet<>(dates));
    }

    public int getBusinessDays(Set<LocalDate> HOLIDAYS, LocalDate startInclusive, LocalDate endExclusive) {
        if (startInclusive.isAfter(endExclusive)) {
            return 0;
        }
        int businessDays = 0;
        LocalDate d = startInclusive;
        while (d.isBefore(endExclusive)) {
            DayOfWeek dw = d.getDayOfWeek();
            if (!HOLIDAYS.contains(d)
                    && dw != DayOfWeek.SATURDAY
                    && dw != DayOfWeek.SUNDAY) {
                businessDays++;
            }
            d = d.plusDays(1);
        }
        return businessDays;
    }
}
