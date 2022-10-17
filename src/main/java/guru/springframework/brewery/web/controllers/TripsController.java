package guru.springframework.brewery.web.controllers;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import guru.springframework.brewery.services.TripsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class TripsController {

    private TripsService tripsService;

    public TripsController (TripsService tripsService) {
        this.tripsService = tripsService;
    }

    @GetMapping(path = {"/trips/download-csv"},produces = { "application/csv" })
    public ResponseEntity<Void> downloadCsv(@RequestParam(value = "beerId") Long beerId,
                                            HttpServletResponse httpResponse) throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        tripsService.download(beerId, httpResponse);
        return ResponseEntity.noContent().build();
    }
}
