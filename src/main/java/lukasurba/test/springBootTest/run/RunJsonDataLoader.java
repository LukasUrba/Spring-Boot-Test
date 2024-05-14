package lukasurba.test.springBootTest.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
@Component
public class RunJsonDataLoader implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final RunRepo runRepo;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(RunRepo runRepo, ObjectMapper objectMapper) {
        this.runRepo = runRepo;
        this.objectMapper = objectMapper;
    }

    @Override
    public void run(String... args) throws Exception{
        if(runRepo.count() == 0) {
            try (InputStream inputStream = TypeReference.class.getResourceAsStream("/data/runs.json")) {
                Runs allRuns = objectMapper.readValue(inputStream, Runs.class);
                log.info("Reading {} runs from JSON data and saving to a database.", allRuns.runs().size());
                runRepo.savelAllRuns(allRuns.runs());
            } catch (IOException e){
                throw new RuntimeException("Failed to read JSON data",e);
            }
        } else {
            log.info("Not loading runs from JSON since the database has data already");
        }
    }

}
