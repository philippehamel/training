package guru.springframework.injection.Repository;

public class EnglishGreetingRepositoryImpl implements EnglishGreetingRepository{
    @Override
    public String getGreeting() {
        return "Hello World - EN";
    }
}
