package guru.springframework.injection.services;

import org.springframework.stereotype.Service;

/**
 * Created by jt on 12/27/19.
 */

public class SetterInjectedGreetingService implements GreetingService{
    @Override
    public String sayGreeting() {
        return "Hello World - Setter";
    }
}
