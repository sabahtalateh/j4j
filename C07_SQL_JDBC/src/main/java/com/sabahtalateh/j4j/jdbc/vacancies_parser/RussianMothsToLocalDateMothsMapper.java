package com.sabahtalateh.j4j.jdbc.vacancies_parser;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

/**
 * RussianMothsToLocalDateMothsMapper.
 */
class RussianMothsToLocalDateMothsMapper {

    private Map<String, Month> map = new HashMap<>();

    /**
     * Constructor.
     */
    RussianMothsToLocalDateMothsMapper() {
        map.put("янв", Month.JANUARY);
        map.put("фев", Month.FEBRUARY);
        map.put("мар", Month.MARCH);
        map.put("апр", Month.APRIL);
        map.put("май", Month.MAY);
        map.put("июн", Month.JUNE);
        map.put("июл", Month.JULY);
        map.put("авг", Month.AUGUST);
        map.put("сен", Month.SEPTEMBER);
        map.put("окт", Month.OCTOBER);
        map.put("ноя", Month.NOVEMBER);
        map.put("дек", Month.DECEMBER);
    }

    /**
     * @param russianMonth russian month.
     * @return month.
     */
    Month map(String russianMonth) {
        return this.map.get(russianMonth);
    }
}
