package icebreaker.getmeholidaylist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	  @GetMapping(value = "/", produces = "text/html")
	    public ResponseEntity<String> getHelp() {

	        String html = """
	            <html>
	            <head>
				  <style>
				    body { font-family: Arial; padding: 20px; }
				    table { border-collapse: collapse; width: 100%; }
				    th, td { border: 1px solid #ddd; padding: 6px; vertical-align: top; text-align: center; }
				    th { background: #f2f2f2; }
				    .gap { width: 10px; background: #fff; border-left: none; border-right: none; }
				    .code { width: 70px; text-align: center; font-weight: bold; }
				  </style>
				</head>
	            <body style="font-family: Arial; line-height: 1.6; padding: 20px;">
	                <h1>GetMeHolidaysList API - Help Page</h1>
	        		<p> The help and usage of the two endpoints <strong>/api/hldys</strong> & <strong>/api/tdy</strong> are provided below. </p>
	        		<p><strong>Note: </strong> UI interface is yet to develop. As of now all the usage examples to be try out within the browser only </p>
	                <h2>1. Endpoint: /api/hldys</h2>

	                <p><strong>Base URL:</strong> Host URL seen in the browser, append the above endpoint and follow providing the query params as below from A to F </p>
                    <p><strong>Example :</strong> Place holder/api/hldys?cntry=CA&yyyy=2024</p>
                    <p> Replace the Place holder with the Host URL </p>
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
	                        <td>Year of the holidays (2020 to 2100)</td>
	                    </tr>
	                    <tr>
	                        <td>mm</td>
	                        <td>No*</td>
	                        <td>Month (1 to 12). Must be used only when <strong>day</strong> is also provided.</td>
	                    </tr>
	                    <tr>
	                        <td>dd</td>
	                        <td>No*</td>
	                        <td>Day of month (1 to 31). Must be used only with <strong>month</strong>.</td>
	                    </tr>
	                    <tr>
	                        <td>typ</td>
	                        <td>No</td>
	                        <td>Filter by holiday type (optional)</td>
	                    </tr>
	                </table>

	                <h3>Usage Examples</h3>
	                
	                <h3>A) Get all holidays of a country, for the current year if yyyy is not provided</h3>
	                <pre>/api/hldys?cntry=CA</pre>

	                <h3>B) Get all holidays of a country/year</h3>
	                <pre>/api/hldys?cntry=CA&yyyy=2024</pre>

	                <h3>C) Get holidays for a specific date</h3>
	                <pre>/api/hldys?cntry=CA&yyyy=2024&mm=1&dd=1</pre>

	                <h3>D) Get only National Holidays (formatted table)</h3>
	                <pre>/api/hldys?cntry=CA&yyyy=2024&typ=national</pre>

	        		<h3>E) Get only Religious Holidays (formatted table)</h3>
	                <pre>/api/hldys?cntry=CA&yyyy=2024&typ=religious</pre>
	                
	                <h3>F) Get only Observance Holidays (formatted table)</h3>
	                <pre>/api/hldys?cntry=CA&yyyy=2024&typ=observance</pre>
	                
	                <h3>G) Get only Local Holidays (formatted table)</h3>
	                <pre>/api/hldys?cntry=CA&yyyy=2024&typ=local</pre>
	                
	                
	                <h2>2. Endpoint: /api/tdy</h2>

	                <p><strong>Base URL:</strong>  Host URL seen in the browser, append the above endpoint and follow providing the query params as below from A to C </p>
	        		<p><strong>Example :</strong>  Place holder/api/tdy?cntrys=ca,in,us</p>
	        		<p> Replace the Place holder with the Host URL </p>
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

	                <h3> Usage Examples</h3>

	        		<h3>A) Display today's holiday for a single country</h3>
	                <pre>/api/tdy?cntrys=ca</pre>	                

	                <h3>B) Display today's holiday for a list of given countries, separated by ,</h3>
	                <pre>/api/tdy?cntrys=ca,in,us</pre>
	                
	                <h3>C) Display today's holiday for a list of valid countries, for invalid countries displays the error message</h3>
	                <pre>/api/tdy?cntrys=ca,inx,us</pre>	                
	                
	                <h2>3. Country Codes (ISO-3166)</h2>
	        		<p> The two letter country code listed below to try out for all the countries </p>
					<table>
					  <tr>
					    <th>Country</th><th>Code</th><th class="gap"></th>
					    <th>Country</th><th>Code</th><th class="gap"></th>
					    <th>Country</th><th>Code</th>
					  </tr>
					<tr><td>Afghanistan</td><td class='code'>AF</td><td class='gap'></td><td>Albania</td><td class='code'>AL</td><td class='gap'></td><td>Algeria</td><td class='code'>DZ</td></tr><tr><td>American Samoa</td><td class='code'>AS</td><td class='gap'></td><td>Andorra</td><td class='code'>AD</td><td class='gap'></td><td>Angola</td><td class='code'>AO</td></tr><tr><td>Anguilla</td><td class='code'>AI</td><td class='gap'></td><td>Antigua and Barbuda</td><td class='code'>AG</td><td class='gap'></td><td>Argentina</td><td class='code'>AR</td></tr><tr><td>Armenia</td><td class='code'>AM</td><td class='gap'></td><td>Aruba</td><td class='code'>AW</td><td class='gap'></td><td>Australia</td><td class='code'>AU</td></tr><tr><td>Austria</td><td class='code'>AT</td><td class='gap'></td><td>Azerbaijan</td><td class='code'>AZ</td><td class='gap'></td><td>Bahrain</td><td class='code'>BH</td></tr><tr><td>Bangladesh</td><td class='code'>BD</td><td class='gap'></td><td>Barbados</td><td class='code'>BB</td><td class='gap'></td><td>Belarus</td><td class='code'>BY</td></tr><tr><td>Belgium</td><td class='code'>BE</td><td class='gap'></td><td>Belize</td><td class='code'>BZ</td><td class='gap'></td><td>Benin</td><td class='code'>BJ</td></tr><tr><td>Bermuda</td><td class='code'>BM</td><td class='gap'></td><td>Bhutan</td><td class='code'>BT</td><td class='gap'></td><td>Bolivia</td><td class='code'>BO</td></tr><tr><td>Bosnia and Herzegovina</td><td class='code'>BA</td><td class='gap'></td><td>Botswana</td><td class='code'>BW</td><td class='gap'></td><td>Brazil</td><td class='code'>BR</td></tr><tr><td>British Virgin Islands</td><td class='code'>VG</td><td class='gap'></td><td>Brunei</td><td class='code'>BN</td><td class='gap'></td><td>Bulgaria</td><td class='code'>BG</td></tr><tr><td>Burkina Faso</td><td class='code'>BF</td><td class='gap'></td><td>Burundi</td><td class='code'>BI</td><td class='gap'></td><td>Cabo Verde</td><td class='code'>CV</td></tr><tr><td>Cambodia</td><td class='code'>KH</td><td class='gap'></td><td>Cameroon</td><td class='code'>CM</td><td class='gap'></td><td>Canada</td><td class='code'>CA</td></tr><tr><td>Cayman Islands</td><td class='code'>KY</td><td class='gap'></td><td>Central African Republic</td><td class='code'>CF</td><td class='gap'></td><td>Chad</td><td class='code'>TD</td></tr><tr><td>Chile</td><td class='code'>CL</td><td class='gap'></td><td>China</td><td class='code'>CN</td><td class='gap'></td><td>Colombia</td><td class='code'>CO</td></tr><tr><td>Comoros</td><td class='code'>KM</td><td class='gap'></td><td>Congo</td><td class='code'>CG</td><td class='gap'></td><td>Congo Democratic Republic</td><td class='code'>CD</td></tr><tr><td>Cook Islands</td><td class='code'>CK</td><td class='gap'></td><td>Costa Rica</td><td class='code'>CR</td><td class='gap'></td><td>Cote d'Ivoire</td><td class='code'>CI</td></tr><tr><td>Croatia</td><td class='code'>HR</td><td class='gap'></td><td>Cuba</td><td class='code'>CU</td><td class='gap'></td><td>CuraÃ§ao</td><td class='code'>CW</td></tr><tr><td>Cyprus</td><td class='code'>CY</td><td class='gap'></td><td>Czechia</td><td class='code'>CZ</td><td class='gap'></td><td>Denmark</td><td class='code'>DK</td></tr><tr><td>Djibouti</td><td class='code'>DJ</td><td class='gap'></td><td>Dominica</td><td class='code'>DM</td><td class='gap'></td><td>Dominican Republic</td><td class='code'>DO</td></tr><tr><td>East Timor</td><td class='code'>TL</td><td class='gap'></td><td>Ecuador</td><td class='code'>EC</td><td class='gap'></td><td>Egypt</td><td class='code'>EG</td></tr><tr><td>El Salvador</td><td class='code'>SV</td><td class='gap'></td><td>Equatorial Guinea</td><td class='code'>GQ</td><td class='gap'></td><td>Eritrea</td><td class='code'>ER</td></tr><tr><td>Estonia</td><td class='code'>EE</td><td class='gap'></td><td>Ethiopia</td><td class='code'>ET</td><td class='gap'></td><td>Falkland Islands</td><td class='code'>FK</td></tr><tr><td>Faroe Islands</td><td class='code'>FO</td><td class='gap'></td><td>Fiji</td><td class='code'>FJ</td><td class='gap'></td><td>Finland</td><td class='code'>FI</td></tr><tr><td>France</td><td class='code'>FR</td><td class='gap'></td><td>French Polynesia</td><td class='code'>PF</td><td class='gap'></td><td>Gabon</td><td class='code'>GA</td></tr><tr><td>Gambia</td><td class='code'>GM</td><td class='gap'></td><td>Georgia</td><td class='code'>GE</td><td class='gap'></td><td>Germany</td><td class='code'>DE</td></tr><tr><td>Ghana</td><td class='code'>GH</td><td class='gap'></td><td>Gibraltar</td><td class='code'>GI</td><td class='gap'></td><td>Greece</td><td class='code'>GR</td></tr><tr><td>Greenland</td><td class='code'>GL</td><td class='gap'></td><td>Grenada</td><td class='code'>GD</td><td class='gap'></td><td>Guam</td><td class='code'>GU</td></tr><tr><td>Guatemala</td><td class='code'>GT</td><td class='gap'></td><td>Guernsey</td><td class='code'>GG</td><td class='gap'></td><td>Guinea</td><td class='code'>GN</td></tr><tr><td>Guinea-Bissau</td><td class='code'>GW</td><td class='gap'></td><td>Guyana</td><td class='code'>GY</td><td class='gap'></td><td>Haiti</td><td class='code'>HT</td></tr><tr><td>Honduras</td><td class='code'>HN</td><td class='gap'></td><td>Hong Kong</td><td class='code'>HK</td><td class='gap'></td><td>Hungary</td><td class='code'>HU</td></tr><tr><td>Iceland</td><td class='code'>IS</td><td class='gap'></td><td>India</td><td class='code'>IN</td><td class='gap'></td><td>Indonesia</td><td class='code'>ID</td></tr><tr><td>Iran</td><td class='code'>IR</td><td class='gap'></td><td>Iraq</td><td class='code'>IQ</td><td class='gap'></td><td>Ireland</td><td class='code'>IE</td></tr><tr><td>Isle of Man</td><td class='code'>IM</td><td class='gap'></td><td>Israel</td><td class='code'>IL</td><td class='gap'></td><td>Italy</td><td class='code'>IT</td></tr><tr><td>Jamaica</td><td class='code'>JM</td><td class='gap'></td><td>Japan</td><td class='code'>JP</td><td class='gap'></td><td>Jersey</td><td class='code'>JE</td></tr><tr><td>Jordan</td><td class='code'>JO</td><td class='gap'></td><td>Kazakhstan</td><td class='code'>KZ</td><td class='gap'></td><td>Kenya</td><td class='code'>KE</td></tr><tr><td>Kiribati</td><td class='code'>KI</td><td class='gap'></td><td>Kosovo</td><td class='code'>XK</td><td class='gap'></td><td>Kuwait</td><td class='code'>KW</td></tr><tr><td>Kyrgyzstan</td><td class='code'>KG</td><td class='gap'></td><td>Laos</td><td class='code'>LA</td><td class='gap'></td><td>Latvia</td><td class='code'>LV</td></tr><tr><td>Lebanon</td><td class='code'>LB</td><td class='gap'></td><td>Lesotho</td><td class='code'>LS</td><td class='gap'></td><td>Liberia</td><td class='code'>LR</td></tr><tr><td>Libya</td><td class='code'>LY</td><td class='gap'></td><td>Liechtenstein</td><td class='code'>LI</td><td class='gap'></td><td>Lithuania</td><td class='code'>LT</td></tr><tr><td>Luxembourg</td><td class='code'>LU</td><td class='gap'></td><td>Macau</td><td class='code'>MO</td><td class='gap'></td><td>Madagascar</td><td class='code'>MG</td></tr><tr><td>Malawi</td><td class='code'>MW</td><td class='gap'></td><td>Malaysia</td><td class='code'>MY</td><td class='gap'></td><td>Maldives</td><td class='code'>MV</td></tr><tr><td>Mali</td><td class='code'>ML</td><td class='gap'></td><td>Malta</td><td class='code'>MT</td><td class='gap'></td><td>Marshall Islands</td><td class='code'>MH</td></tr><tr><td>Martinique</td><td class='code'>MQ</td><td class='gap'></td><td>Mauritania</td><td class='code'>MR</td><td class='gap'></td><td>Mauritius</td><td class='code'>MU</td></tr><tr><td>Mayotte</td><td class='code'>YT</td><td class='gap'></td><td>Mexico</td><td class='code'>MX</td><td class='gap'></td><td>Micronesia</td><td class='code'>FM</td></tr><tr><td>Moldova</td><td class='code'>MD</td><td class='gap'></td><td>Monaco</td><td class='code'>MC</td><td class='gap'></td><td>Mongolia</td><td class='code'>MN</td></tr><tr><td>Montenegro</td><td class='code'>ME</td><td class='gap'></td><td>Montserrat</td><td class='code'>MS</td><td class='gap'></td><td>Morocco</td><td class='code'>MA</td></tr><tr><td>Mozambique</td><td class='code'>MZ</td><td class='gap'></td><td>Myanmar</td><td class='code'>MM</td><td class='gap'></td><td>Namibia</td><td class='code'>NA</td></tr><tr><td>Nauru</td><td class='code'>NR</td><td class='gap'></td><td>Nepal</td><td class='code'>NP</td><td class='gap'></td><td>Netherlands</td><td class='code'>NL</td></tr><tr><td>New Caledonia</td><td class='code'>NC</td><td class='gap'></td><td>New Zealand</td><td class='code'>NZ</td><td class='gap'></td><td>Nicaragua</td><td class='code'>NI</td></tr><tr><td>Niger</td><td class='code'>NE</td><td class='gap'></td><td>Nigeria</td><td class='code'>NG</td><td class='gap'></td><td>North Korea</td><td class='code'>KP</td></tr><tr><td>North Macedonia</td><td class='code'>MK</td><td class='gap'></td><td>Northern Mariana Islands</td><td class='code'>MP</td><td class='gap'></td><td>Norway</td><td class='code'>NO</td></tr><tr><td>Oman</td><td class='code'>OM</td><td class='gap'></td><td>Pakistan</td><td class='code'>PK</td><td class='gap'></td><td>Palau</td><td class='code'>PW</td></tr><tr><td>Panama</td><td class='code'>PA</td><td class='gap'></td><td>Papua New Guinea</td><td class='code'>PG</td><td class='gap'></td><td>Paraguay</td><td class='code'>PY</td></tr><tr><td>Peru</td><td class='code'>PE</td><td class='gap'></td><td>Philippines</td><td class='code'>PH</td><td class='gap'></td><td>Poland</td><td class='code'>PL</td></tr><tr><td>Portugal</td><td class='code'>PT</td><td class='gap'></td><td>Puerto Rico</td><td class='code'>PR</td><td class='gap'></td><td>Qatar</td><td class='code'>QA</td></tr><tr><td>Reunion</td><td class='code'>RE</td><td class='gap'></td><td>Romania</td><td class='code'>RO</td><td class='gap'></td><td>Russia</td><td class='code'>RU</td></tr><tr><td>Rwanda</td><td class='code'>RW</td><td class='gap'></td><td>Saint Helena</td><td class='code'>SH</td><td class='gap'></td><td>Saint Kitts and Nevis</td><td class='code'>KN</td></tr><tr><td>Saint Lucia</td><td class='code'>LC</td><td class='gap'></td><td>Saint Martin</td><td class='code'>MF</td><td class='gap'></td><td>Saint Pierre and Miquelon</td><td class='code'>PM</td></tr><tr><td>Saint Vincent and the Grenadines</td><td class='code'>VC</td><td class='gap'></td><td>Samoa</td><td class='code'>WS</td><td class='gap'></td><td>San Marino</td><td class='code'>SM</td></tr><tr><td>Sao Tome and Principe</td><td class='code'>ST</td><td class='gap'></td><td>Saudi Arabia</td><td class='code'>SA</td><td class='gap'></td><td>Senegal</td><td class='code'>SN</td></tr><tr><td>Serbia</td><td class='code'>RS</td><td class='gap'></td><td>Seychelles</td><td class='code'>SC</td><td class='gap'></td><td>Sierra Leone</td><td class='code'>SL</td></tr><tr><td>Singapore</td><td class='code'>SG</td><td class='gap'></td><td>Sint Maarten</td><td class='code'>SX</td><td class='gap'></td><td>Slovakia</td><td class='code'>SK</td></tr><tr><td>Slovenia</td><td class='code'>SI</td><td class='gap'></td><td>Solomon Islands</td><td class='code'>SB</td><td class='gap'></td><td>Somalia</td><td class='code'>SO</td></tr><tr><td>South Africa</td><td class='code'>ZA</td><td class='gap'></td><td>South Korea</td><td class='code'>KR</td><td class='gap'></td><td>South Sudan</td><td class='code'>SS</td></tr><tr><td>Spain</td><td class='code'>ES</td><td class='gap'></td><td>Sri Lanka</td><td class='code'>LK</td><td class='gap'></td><td>St. Barts</td><td class='code'>BL</td></tr><tr><td>Sudan</td><td class='code'>SD</td><td class='gap'></td><td>Suriname</td><td class='code'>SR</td><td class='gap'></td><td>Sweden</td><td class='code'>SE</td></tr><tr><td>Switzerland</td><td class='code'>CH</td><td class='gap'></td><td>Syria</td><td class='code'>SY</td><td class='gap'></td><td>Taiwan</td><td class='code'>TW</td></tr><tr><td>Tajikistan</td><td class='code'>TJ</td><td class='gap'></td><td>Tanzania</td><td class='code'>TZ</td><td class='gap'></td><td>Thailand</td><td class='code'>TH</td></tr><tr><td>The Bahamas</td><td class='code'>BS</td><td class='gap'></td><td>Togo</td><td class='code'>TG</td><td class='gap'></td><td>Tonga</td><td class='code'>TO</td></tr><tr><td>Trinidad and Tobago</td><td class='code'>TT</td><td class='gap'></td><td>Tunisia</td><td class='code'>TN</td><td class='gap'></td><td>Turkey</td><td class='code'>TR</td></tr><tr><td>Turkmenistan</td><td class='code'>TM</td><td class='gap'></td><td>Turks and Caicos Islands</td><td class='code'>TC</td><td class='gap'></td><td>Tuvalu</td><td class='code'>TV</td></tr><tr><td>US Virgin Islands</td><td class='code'>VI</td><td class='gap'></td><td>Uganda</td><td class='code'>UG</td><td class='gap'></td><td>Ukraine</td><td class='code'>UA</td></tr><tr><td>United Arab Emirates</td><td class='code'>AE</td><td class='gap'></td><td>United Kingdom</td><td class='code'>GB</td><td class='gap'></td><td>United States</td><td class='code'>US</td></tr><tr><td>Uruguay</td><td class='code'>UY</td><td class='gap'></td><td>Uzbekistan</td><td class='code'>UZ</td><td class='gap'></td><td>Vanuatu</td><td class='code'>VU</td></tr><tr><td>Vatican City (Holy See)</td><td class='code'>VA</td><td class='gap'></td><td>Venezuela</td><td class='code'>VE</td><td class='gap'></td><td>Vietnam</td><td class='code'>VN</td></tr><tr><td>Wallis and Futuna</td><td class='code'>WF</td><td class='gap'></td><td>Yemen</td><td class='code'>YE</td><td class='gap'></td><td>Zambia</td><td class='code'>ZM</td></tr><tr><td>Zimbabwe</td><td class='code'>ZW</td><td class='gap'></td><td>eSwatini</td><td class='code'>SZ</td><td class='gap'></td><td></td><td class='code'></td></tr></table>

	                                
	                <h2>4. Errors</h2>
	                <p>Invalid combinations return helpful error messages</p>

	                <hr>
	                <p style="font-size: small; color: gray;">
	                    GetMeHolidaysList API © 2025 & @ 2026
	                </p>
	            </body>
	            </html>
	            """;

	        return ResponseEntity.ok()
	                .header("Content-Type", "text/html")
	                .body(html);
	    }
	    

}
