package icebreaker.getmeholidaylist.dto;

//SupportedCountriesResponse.java
import java.util.List;

public class SupportedCountriesResponse {
 private Meta meta;
 private Response response;

 public Meta getMeta() { return meta; }
 public void setMeta(Meta meta) { this.meta = meta; }

 public Response getResponse() { return response; }
 public void setResponse(Response response) { this.response = response; }

 public static class Meta {
     private int code;
     public int getCode() { return code; }
     public void setCode(int code) { this.code = code; }
 }

 public static class Response {
     private String url;
     private List<CountryDto> countries;

     public String getUrl() { return url; }
     public void setUrl(String url) { this.url = url; }

     public List<CountryDto> getCountries() { return countries; }
     public void setCountries(List<CountryDto> countries) { this.countries = countries; }
 }
}

