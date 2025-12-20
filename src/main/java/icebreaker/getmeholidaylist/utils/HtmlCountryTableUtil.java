package icebreaker.getmeholidaylist.utils;

//HtmlCountryTableUtil.java
import java.util.List;

import icebreaker.getmeholidaylist.dto.CountryDto;

public class HtmlCountryTableUtil {

 public static String build8ColCountryTable(List<CountryDto> countries) {
     StringBuilder sb = new StringBuilder();

     sb.append("""
     <html>
     <head>
       <style>
         body { font-family: Arial; padding: 20px; }
         table { border-collapse: collapse; width: 100%; }
         th, td { border: 1px solid #ddd; padding: 6px; vertical-align: top; }
         th { background: #f2f2f2; }
         .gap { width: 10px; background: #fff; border-left: none; border-right: none; }
         .code { width: 70px; text-align: center; font-weight: bold; }
       </style>
     </head>
     <body>
     <h2>Country Codes (ISO-3166)</h2>

     <table>
       <tr>
         <th>Country</th><th>Code</th><th class="gap"></th>
         <th>Country</th><th>Code</th><th class="gap"></th>
         <th>Country</th><th>Code</th>
       </tr>
     """);

     for (int i = 0; i < countries.size(); i += 3) {
         sb.append("<tr>");

         appendPair(sb, countries, i);
         sb.append("<td class='gap'></td>");
         appendPair(sb, countries, i + 1);
         sb.append("<td class='gap'></td>");
         appendPair(sb, countries, i + 2);

         sb.append("</tr>");
     }

     sb.append("""
     </table>
     </body>
     </html>
     """);

     return sb.toString();
 }

 private static void appendPair(StringBuilder sb, List<CountryDto> countries, int idx) {
     if (idx >= countries.size()) {
         sb.append("<td></td><td class='code'></td>");
         return;
     }
     CountryDto c = countries.get(idx);
     sb.append("<td>").append(escape(c.getCountry_name())).append("</td>");
     sb.append("<td class='code'>").append(escape(c.getIso3166())).append("</td>");
 }

 private static String escape(String s) {
     if (s == null) return "";
     return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
 }
}

