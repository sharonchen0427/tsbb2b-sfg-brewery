package guru.springframework.brewery.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TripStatesList {
    public List<TripStates> tripStatesList;

    @JsonProperty
    public List<TripStates> getTripStatesList() {return tripStatesList;}

    public void setTripStatesList(List<TripStates> tripStatesList) {
        this.tripStatesList = tripStatesList;
    }
}
