package lukasurba.test.springBootTest.run;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

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

//    @PostConstruct
//    private void init() {
//        allRuns.add(new Run(1,"My Run", LocalDateTime.now().minusMinutes(30),LocalDateTime.now(),4, Location.OUTDOOR));
//        allRuns.add(new Run(2,"My Run 2", LocalDateTime.now(), LocalDateTime.now().plusMinutes(30),3, Location.OUTDOOR));
//    }

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
        var updated = jdbcClient.sql("INSERT INTO run(id,title,started_on,finished_on, miles, location) values(?,?,?,?,?,?)")
                .params(List.of(run.id(),run.title(),run.startedOn(),run.finishedOn(),run.miles(),run.location().toString()))
                .update();
        Assert.state(updated==1,"Failed to create run " + run.title());
    }

    void updateRun(Run run, Integer id) {
        var updated = jdbcClient.sql("UPDATE run SET title = ?, started_on = ?, finished_on = ?, miles = ?, location = ? WHERE id = ?")
                .params((List.of(run.title(),run.startedOn(),run.finishedOn(),run.miles(),run.location().toString(),id)))
                .update();
        Assert.state(updated==1,"Failed to update run "+ run.title());
    }

    void deleteRun(Integer id) {
        var updated = jdbcClient.sql("DELETE FROM Run WHERE id= :id")
                .params("id", id)
                .update();
        Assert.state(updated==1,"Failed to delete run "+ id);
    }

    public int count() {
        return jdbcClient.sql("SELECT * FROM run")
                .query()
                .listOfRows()
                .size();
    }

    public void savelAllRuns(List<Run> runs){
        runs.stream().forEach(this::createRun);
    }
    public List<Run> findByLocation(String location) {
        return jdbcClient.sql("SELECT * FROM run WHERE location = :location")
                .params("location",location)
                .query(Run.class)
                .list();
    }
}
