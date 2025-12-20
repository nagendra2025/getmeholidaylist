package icebreaker.getmeholidaylist.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import icebreaker.getmeholidaylist.dto.CountryDto;
import icebreaker.getmeholidaylist.dto.HolidayDto;
import icebreaker.getmeholidaylist.service.CalendarificService;
import icebreaker.getmeholidaylist.service.HolidayService;
import icebreaker.getmeholidaylist.utils.HtmlCountryTableUtil;
import icebreaker.getmeholidaylist.utils.HtmlUtility;
import icebreaker.getmeholidaylist.utils.PageMode;

@RestController
@RequestMapping("/api")
public class HolidayController {

    private final HolidayService holidayService;
    private final CalendarificService calendarificService;

    public HolidayController(HolidayService holidayService,CalendarificService calendarificService) {
        this.holidayService = holidayService;
        this.calendarificService=calendarificService;
    }
    
    @GetMapping(value = "/countries", produces = "text/html")
    public ResponseEntity<String> showCountryCodes() {
        List<CountryDto> countries = calendarificService.getSupportedCountries();
        String html = HtmlCountryTableUtil.build8ColCountryTable(countries);

        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(html);
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
        String htmlTable = HtmlUtility.buildHtmlTable(nhList, cntry, yyyy, typ, PageMode.HOLIDAYS);

        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(htmlTable);
    }
   
    @GetMapping("/tdy")
    public ResponseEntity<?> getTodayInfoFromGivenCountries(
            @RequestParam String cntrys,
            @RequestParam(required = false) String typ) {

        LocalDate today = LocalDate.now();
        int yyyy = today.getYear();
        int mm   = today.getMonthValue();
        int dd   = today.getDayOfMonth();

        StringBuilder sb = new StringBuilder("<html><body>");

        // cntrys = "us,in,ca"
        for (String raw : cntrys.split(",")) {
            String code = raw.trim();
            if (code.isEmpty()) {
                continue;
            }
            
         // ✅ PER-COUNTRY VALIDATION
            if (code.length() != 2) {
                sb.append(HtmlUtility.buildErrorTable(
                        code,
                        code + " is not available. Only 2-letter ISO country codes are allowed.",
                        yyyy
                ));
                sb.append("<br><br>");
                continue;
            }

            try {
            // for each country, call existing getHolidays()
            List<HolidayDto> holidaysForCountry =
                    holidayService.getHolidays(code, yyyy, mm, dd, typ);

            // always print a table, even if holidaysForCountry is empty
            sb.append(HtmlUtility.buildHtmlTable(holidaysForCountry, code.toUpperCase(), yyyy, typ,PageMode.TODAY));
            sb.append("<br><br>"); // spacing between tables
            
            } catch (Exception ex) {
                // safety net for upstream or unexpected errors
                sb.append(HtmlUtility.buildErrorTable(
                        code,
                        "Unable to fetch holidays at this time.",
                        yyyy
                ));
            }

        sb.append("</body></html>");

      
    }
        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(sb.toString());

    }
    
  
}

