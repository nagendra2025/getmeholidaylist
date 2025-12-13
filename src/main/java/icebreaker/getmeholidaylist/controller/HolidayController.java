package icebreaker.getmeholidaylist.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import icebreaker.getmeholidaylist.dto.HolidayDto;
import icebreaker.getmeholidaylist.service.HolidayService;
import icebreaker.getmeholidaylist.utils.HtmlUtility;
import icebreaker.getmeholidaylist.utils.PageMode;

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
    
    @GetMapping(value = "/hlp", produces = "text/html")
    public ResponseEntity<String> getHelp() {

        String html = """
            <html>
            <body style="font-family: Arial; line-height: 1.6; padding: 20px;">
                <h1>GetMeHolidaysList API - Help Page</h1>

                <h2>1. Endpoint: /api/hldys</h2>

                <p><strong>Base URL:</strong> /api/hldys</p>

                <h3>Query Parameters</h3>
                <table border="1" cellpadding="6" cellspacing="0" 
                       style="border-collapse:collapse; font-family:Arial;">
                    <tr style="background:#f2f2f2;">
                        <th>Parameter</th>
                        <th>Required?</th>
                        <th>Description</th>
                    </tr>
                    <tr>
                        <td>cntry</td>
                        <td>Yes</td>
                        <td>ISO country code (e.g., CA, US, IN)</td>
                    </tr>
                    <tr>
                        <td>yyyy</td>
                        <td>Yes</td>
                        <td>Year of the holidays (2020–2100)</td>
                    </tr>
                    <tr>
                        <td>mm</td>
                        <td>No*</td>
                        <td>Month (1–12). Must be used only when <strong>day</strong> is also provided.</td>
                    </tr>
                    <tr>
                        <td>dd</td>
                        <td>No*</td>
                        <td>Day of month (1–31). Must be used only with <strong>month</strong>.</td>
                    </tr>
                    <tr>
                        <td>typ</td>
                        <td>No</td>
                        <td>Filter by holiday type (optional)</td>
                    </tr>
                </table>

                <h2>2. Usage Examples</h2>

                <h3>A) Get all holidays of a country/year</h3>
                <pre>/api/hldys?cntry=CA&yyyy=2024</pre>

                <h3>B) Get holidays for a specific date</h3>
                <pre>/api/hldys?cntry=CA&yyyy=2024&mm=1&dd=1</pre>

                <h3>C) Get only National Holidays (formatted table)</h3>
                <pre>/api/hldys?cntry=CA&yyyy=2024&typ=national</pre>

        		<h3>D) Get only Religious Holidays (formatted table)</h3>
                <pre>/api/hldys?cntry=CA&yyyy=2024&typ=religious</pre>
                
                <h3>E) Get only Observance Holidays (formatted table)</h3>
                <pre>/api/hldys?cntry=CA&yyyy=2024&typ=observance</pre>
                
                <h3>F) Get only Local Holidays (formatted table)</h3>
                <pre>/api/hldys?cntry=CA&yyyy=2024&typ=local</pre>
                
                
                <h2>3. Endpoint: /api/tdy</h2>

                <p><strong>Base URL:</strong> /api/tdy</p>

                <h3>Query Parameters</h3>
                <table border="1" cellpadding="6" cellspacing="0" 
                       style="border-collapse:collapse; font-family:Arial;">
                    <tr style="background:#f2f2f2;">
                        <th>Parameter</th>
                        <th>Required?</th>
                        <th>Description</th>
                    </tr>
                    <tr>
                        <td>cntrys</td>
                        <td>Yes</td>
                        <td>ISO country code (e.g., CA, US, IN)</td>
                    </tr>
                </table>

                <h2>4. Usage Examples</h2>

                <h3>A) Display today's holiday from the list of given countries</h3>
                <pre>/api/tdy?cntrys=ca,in,us</pre>
                
                <h3>A) Display today's holiday from the list of valid countries, for invalid countries displays the error message</h3>
                <pre>/api/tdy?cntrys=ca,inx,us</pre>
                                
                <h2>5. Rules</h2>
                <ul>
                    <li>Invalid combinations return helpful error messages</li>
                </ul>

                <hr>
                <p style="font-size: small; color: gray;">
                    GetMeHolidaysList API © 2025
                </p>
            </body>
            </html>
            """;

        return ResponseEntity.ok()
                .header("Content-Type", "text/html")
                .body(html);
    }
    
}

