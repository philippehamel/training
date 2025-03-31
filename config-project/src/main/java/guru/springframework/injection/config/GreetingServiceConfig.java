package guru.springframework.injection.config;

import com.springframework.pets.PerServiceFactory;
import com.springframework.pets.PetService;
import guru.springframework.injection.Repository.EnglishGreetingRepository;
import guru.springframework.injection.Repository.EnglishGreetingRepositoryImpl;
import guru.springframework.injection.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class GreetingServiceConfig {

    @Bean
    PerServiceFactory perServiceFactory(){
        return new PerServiceFactory();
    }

    @Profile({"dog", "default"})
    @Bean
    PetService dogPetService(PerServiceFactory perServiceFactory){
        return perServiceFactory.getPetService("dog");
    };

    @Profile("cat")
    @Bean
    PetService catPetService(PerServiceFactory perServiceFactory){
        return perServiceFactory.getPetService("cat");
    };

    @Bean
    EnglishGreetingRepository englishGreetingRepository(){
        return new EnglishGreetingRepositoryImpl();
    };

    @Profile("EN")
    @Bean
    I18nEnglishGreetingService i18nService(EnglishGreetingRepository englishGreetingRepository){
        return new I18nEnglishGreetingService(englishGreetingRepository);
    }

    @Profile("ES")
    @Bean("i18nService")
    I18NSpanishService i18NSpanishService(){
        return new I18NSpanishService();
    }

    @Primary
    @Bean
    PrimaryGreetingService primaryGreetingService(){
        return new PrimaryGreetingService();
    }

    @Bean
    ConstructorGreetingService constructorGreetingService(){
        return new ConstructorGreetingService();
    }

    @Bean
    PropertyInjectedGreetingService propertyInjectedGreetingService(){
        return new PropertyInjectedGreetingService();
    }

    @Bean
    SetterInjectedGreetingService setterInjectedGreetingService(){
        return new SetterInjectedGreetingService();
    }
}
