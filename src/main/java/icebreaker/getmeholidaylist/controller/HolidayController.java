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

    @GetMapping("/hldys")
    public ResponseEntity<?> getHolidays(
            @RequestParam String cntry,
            @RequestParam int yyyy,
            @RequestParam(required = false) Integer mm,
            @RequestParam(required = false) Integer dd,
            @RequestParam(required = false) String typ) {
        
        
        List<HolidayDto> nhList =
                holidayService.getHolidays(cntry, yyyy, mm, dd, typ);

        // Build HTML table from the NH list
        String htmlTable = HtmlUtility.buildHtmlTable(nhList, cntry, yyyy, typ);

        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(htmlTable);
    }
}

