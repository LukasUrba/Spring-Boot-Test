package lukasurba.test.springBootTest.run;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class RunRepo {

    private final JdbcClient jdbcClient;

    public RunRepo(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
//    private final List<Run> allRuns = new ArrayList<>();

    List<Run> findAll() {
        return jdbcClient.sql("select * from run")
                .query(Run.class)
                .list();
    }

    Optional<Run> findByID(Integer id) {
        return jdbcClient.sql("SELECT id,title,started_on,finished_on, miles, location FROM Run WHERE id = :id")
                .param("id",id)
                .query(Run.class)
                .optional();
    }

    void createRun(Run run) {
        var updated = jdbcClient.sql("INSERT INTO Run(id,title,started_on,finished_on, miles, location) values(?,?,?,?,?,?")
                .params(List.of(run.id(),run.title(),run.startedOn(),run.finishedOn(),run.miles(),run.location().toString()))
                .update();
    }

//    @PostConstruct
//    private void init() {
//        allRuns.add(new Run(1,"My Run", LocalDateTime.now().minusMinutes(30),LocalDateTime.now(),4, Location.OUTDOOR));
//        allRuns.add(new Run(2,"My Run 2", LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),3, Location.OUTDOOR));
//    }
//
//    void updateRun(Run run, Integer id) {
//        Optional<Run> existingRun = findByID(id);
//        existingRun.ifPresent(value -> allRuns.set(allRuns.indexOf(value), run));
//    }
//
//    void deleteRun(Integer id) {
//        allRuns.removeIf(run -> run.id().equals(id));
//    }
}
