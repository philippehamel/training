package guru.springframework.sfgdi.datasource;

import lombok.Getter;
import lombok.Setter;

public class FakeDataSource {
    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String jdbcUrl;

}
