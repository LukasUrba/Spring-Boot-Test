package lukasurba.test.springBootTest.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Run(
        Integer id,
        @NotEmpty
        String title,
        LocalDateTime startedOn,
        LocalDateTime finishedOn,
        @Positive
        Integer Miles,
        Location location) {

//    Throws error that it may not be initialised?
//    TODO sort this ^
//    public Run() {
//        if (!finishedOn.isAfter(startedOn)){
//            throw new IllegalArgumentException("Completion time must be after start time.");
//        }
//    }
}
