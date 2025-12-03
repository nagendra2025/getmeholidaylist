package icebreaker.getmeholidaylist.utils;

import java.util.List;

import icebreaker.getmeholidaylist.dto.HolidayDto;


public class HtmlUtility {

    public static String buildHtmlTable(List<HolidayDto> list, String country, int year, String type) {
        StringBuilder sb = new StringBuilder();

        sb.append("<html><body>");
        sb.append("<h2>").append(type).append(" Holidays of ").append(country).append(" In The Year ").append(year).append("</h2>");
        sb.append("<table border='1' cellpadding='6' cellspacing='0' ")
                .append("style='border-collapse:collapse;font-family:Arial;'>");

        // Header row
        sb.append("<tr style='background:#f2f2f2;'>");
        sb.append("<th>Date</th>");
        sb.append("<th>Day</th>");
        sb.append("<th>Purpose</th>");
        sb.append("<th>Type</th>");
        sb.append("</tr>");

        // Data rows
        for (HolidayDto h : list) {
            sb.append("<tr>");
            sb.append("<td>").append(h.getDate()).append("</td>");
            sb.append("<td>").append(h.getName()).append("</td>");
            sb.append("<td>").append(h.getDescription()).append("</td>");
            sb.append("<td>").append(h.getTypes()).append("</td>");
            sb.append("</tr>");
        }

        sb.append("</table></body></html>");
        return sb.toString();
    }
}
