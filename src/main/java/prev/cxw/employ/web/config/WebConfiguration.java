package prev.cxw.employ.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/30
 * version:1.0
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/assets/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/assets/js/");
        super.addResourceHandlers(registry);
    }
}
