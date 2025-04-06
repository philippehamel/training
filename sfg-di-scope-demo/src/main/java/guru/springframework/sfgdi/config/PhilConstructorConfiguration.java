package guru.springframework.sfgdi.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;


@ConstructorBinding
@ConfigurationProperties(prefix = "phil")
@AllArgsConstructor
@Getter
public class PhilConstructorConfiguration {
    private final String username;

    private final String password;

    private final String jdbcUrl;
}
