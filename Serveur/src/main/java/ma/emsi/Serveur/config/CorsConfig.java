package ma.emsi.Serveur.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;

import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
public class CorsConfig {
    @Bean
    public SimpleDateFormat simpleFormatter(){
        return new SimpleDateFormat("dd/MM/yyyy");
    }

    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }

    @Bean
    public CorsConfiguration corsConfiguratio(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));
        corsConfiguration.setAllowedOrigins(List.of(
                //DEV
                "http://127.0.0.1:4200/",
                "http://localhost:4200/",
                "http://localhost:8080/"
        ));
        corsConfiguration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PUT","OPTIONS","PATCH", "DELETE"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setExposedHeaders(List.of("Authorization"));
        return corsConfiguration;
    }

}
