package project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.interceptor.TitleInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final TitleInterceptor titleInterceptor;

    @Autowired
    public WebMvcConfig(TitleInterceptor titleInterceptor) {
        this.titleInterceptor = titleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(titleInterceptor);
    }
}
