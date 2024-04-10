package lukasurba.test.springBootTest.run;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime finishedOn,
        Integer Miles,
        Location location) {}
