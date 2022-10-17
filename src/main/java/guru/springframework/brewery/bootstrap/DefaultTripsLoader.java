//package guru.springframework.brewery.bootstrap;
//
//import guru.springframework.brewery.domain.TripStates;
//import guru.springframework.brewery.repositories.*;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class DefaultTripsLoader implements CommandLineRunner {
//
//    private final TripsRepository repository;
//    public DefaultTripsLoader(TripsRepository repository) {
//        this.repository = repository;
//
//    }
//    @Override
//    public void run(String... args) throws Exception {
//        loadTripsData();
//    }
//
//    private void loadTripsData() {
//        if (repository.count() == 0) {
//            TripStates states = new TripStates();
//            states.setLat("26.67890");
//            states.setLon("34.12345");
//            states.setSpd("5");
//            states.setDist("123");
//
//            TripStates states2 = new TripStates();
//            states2.setLat("25.55555");
//            states2.setLon("37.77777");
//            states2.setSpd("8");
//            states2.setDist("563");
//
//            repository.save(states);
//            repository.save(states2);
//        }
//    }
//}