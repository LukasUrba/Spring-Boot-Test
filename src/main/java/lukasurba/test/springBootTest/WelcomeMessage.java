package lukasurba.test.springBootTest;

import org.springframework.stereotype.Component;

@Component
//Any Components should be within the same package as main to deal with Springs dependency injection & version control
public class WelcomeMessage {
    public String getWelcomeMessage() {
        return "Welcome to the Appliaction!";
    }

    @Override
    public String toString() {
        return getWelcomeMessage();
    }
}
