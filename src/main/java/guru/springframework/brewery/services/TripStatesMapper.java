package guru.springframework.brewery.services;

import lombok.Data;

@Data
public class TripStatesMapper {
    private String statesJson;
    public TripStatesMapper(String statesJson) {
        this.statesJson = statesJson;
    }
}
