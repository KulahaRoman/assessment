package kulaha.assessment.config;

import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class BeanConfiguration {
    @Value("${github.token}")
    private String token;

    @Bean
    public GitHub getGitHub() throws IOException {
        if (token == null) {
            throw new IOException("github token is not specified");
        }
        if (token.isEmpty()) {
            throw new IOException("github token is empty");
        }

        return new GitHubBuilder()
                // explicitly specified api url to be clear
                .withEndpoint("https://api.github.com")
                .withOAuthToken(token)
                .build();
    }
}
