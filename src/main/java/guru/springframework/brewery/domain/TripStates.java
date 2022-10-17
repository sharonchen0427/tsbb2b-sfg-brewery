package guru.springframework.brewery.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Data;

@Data
@JsonPropertyOrder({"Lat", "Lon" ,"Spd", "Dist"})
public class TripStates {
    @JsonProperty("Lat")
    private String Lat;

    @JsonProperty("Lon")
    private String Lon;

    @JsonProperty("Spd")
    private String Spd;

    @JsonProperty("Dist")
    private String Dist;
}
