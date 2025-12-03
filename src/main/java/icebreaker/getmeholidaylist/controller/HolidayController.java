package icebreaker.getmeholidaylist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import icebreaker.getmeholidaylist.dto.HolidayDto;
import icebreaker.getmeholidaylist.service.HolidayService;
import icebreaker.getmeholidaylist.utils.HtmlUtility;

@RestController
@RequestMapping("/api")
public class HolidayController {

    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping("/holidays")
    public ResponseEntity<?> getHolidays(
            @RequestParam String country,
            @RequestParam int year,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer day,
            @RequestParam(required = false) String type) {
        
        
        List<HolidayDto> nhList =
                holidayService.getHolidays(country, year, month, day, type);

        // Build HTML table from the NH list
        String htmlTable = HtmlUtility.buildHtmlTable(nhList, country, year, type);

        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(htmlTable);
    }
}

