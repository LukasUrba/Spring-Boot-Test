package lukasurba.test.springBootTest.run;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RunRepo {
    private final List<Run> allRuns = new ArrayList<>();

    List<Run> findAll() {
        return allRuns;
    }

    Optional<Run> findByID(Integer id) {
        return allRuns.stream().filter(run -> Objects.equals(run.id(), id)).findFirst();
    }

    @PostConstruct
    private void init() {
        allRuns.add(new Run(1,"My Run", LocalDateTime.now().minusMinutes(30),LocalDateTime.now(),4, Location.OUTDOOR));
        allRuns.add(new Run(2,"My Run 2", LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),3, Location.OUTDOOR));
    }
}
