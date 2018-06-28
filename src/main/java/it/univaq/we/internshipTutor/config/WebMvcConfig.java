package it.univaq.we.internshipTutor.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${spring.file.path.images}")  String imagesPath;
    @Value("${spring.file.path.docs}") String docsPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/files/images/**")
                .addResourceLocations("file:///"+imagesPath)
                .setCachePeriod(0);

        registry.addResourceHandler("/files/docs/**")
                .addResourceLocations("file:///"+docsPath)
                .setCachePeriod(0);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}