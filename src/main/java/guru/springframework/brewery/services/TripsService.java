package guru.springframework.brewery.services;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import guru.springframework.brewery.domain.TripStates;
import guru.springframework.brewery.domain.TripStatesList;
import guru.springframework.brewery.repositories.TripsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class TripsService {
    @Autowired
    TripsRepository tripsRepository;

    @Autowired
    public TripServiceHelper tripServiceHelper;

    public void download(Long tripId, HttpServletResponse httpResponse) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        List<TripStatesMapper> rowMapper = tripsRepository.getStates(tripId);

        if(!rowMapper.isEmpty() && StringUtils.isNotBlank(rowMapper.get(0).getStatesJson())){
            String dataEncoded = rowMapper.get(0).getStatesJson();
            TripStatesList list = tripServiceHelper.decodeAndDecompose(dataEncoded);
            if(list == null) {
                httpResponse.setStatus((HttpStatus.INTERNAL_SERVER_ERROR.value()));
            } else {
                httpResponse.setStatus(HttpStatus.OK.value());
                httpResponse.addHeader("Content-Type", "application/json");
                httpResponse.addHeader("Content-Disposition", "attachment;filename="+ tripId +".csv");
                StatefulBeanToCsv<TripStates> writer = new StatefulBeanToCsvBuilder<TripStates>(
                        httpResponse.getWriter())
                        .withQuotechar('\u0000')
                        .withSeparator(',')
                        .withOrderedResults(true).build();
                writer.write(list.getTripStatesList());
            }
        } else {
            httpResponse.setStatus(HttpStatus.NOT_FOUND.value());
        }


    }
}
