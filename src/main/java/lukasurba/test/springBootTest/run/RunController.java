package lukasurba.test.springBootTest.run;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepo runRepo;

//    Autowired here is implicit
    public RunController(RunRepo runRepo) {
        this.runRepo = runRepo;
    }

    @GetMapping("")
    List<Run> findAll() {
        return runRepo.findAll();
    }

    @GetMapping("/{id}")
    Run findByID(@PathVariable Integer id) {
        Optional<Run> run = runRepo.findByID(id);
        if (run.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Run Not Found");
        }
        return run.get();
    }
}
