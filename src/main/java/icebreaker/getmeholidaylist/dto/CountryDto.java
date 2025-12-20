package icebreaker.getmeholidaylist.dto;

//CountryDto.java
import com.fasterxml.jackson.annotation.JsonProperty;

public class CountryDto {
 private String country_name;

 @JsonProperty("iso-3166")
 private String iso3166;

 public String getCountry_name() { return country_name; }
 public void setCountry_name(String country_name) { this.country_name = country_name; }

 public String getIso3166() { return iso3166; }
 public void setIso3166(String iso3166) { this.iso3166 = iso3166; }
}

