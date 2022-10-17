package guru.springframework.brewery.repositories;

import guru.springframework.brewery.services.TripStatesMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TripsRepository {

    DataSource dataSource;
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    public List<TripStatesMapper> getStates(Long tripId) {
        Map<String, Object> params = new HashMap<>();
        String sql = "select * from table_trip_states where trip_id = :tripId limit 1";
        params.put("tripId", tripId);
        return namedParameterJdbcTemplate.query(sql, params,
                (res, rowCount) -> new TripStatesMapper(res.getString("statesJson")));

    }

}
