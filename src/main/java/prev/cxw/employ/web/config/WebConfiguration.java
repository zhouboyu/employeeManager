package prev.cxw.employ.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.*;
import prev.cxw.employ.web.interceptor.LogInInterceptor;

import javax.annotation.Resource;

/**
 * <p>
 * </p>
 * User:boyu
 * Time:2017/11/30
 * version:1.0
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    public LogInInterceptor getLogInInterceptor() {
        return new LogInInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(getLogInInterceptor());

        // 排除配置
        addInterceptor.excludePathPatterns("/error**");
        addInterceptor.excludePathPatterns("/doLogin**");
        addInterceptor.excludePathPatterns("/toLogin**");
        addInterceptor.excludePathPatterns("/**.jpg");

        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/error").setViewName("error.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false);
    }
}
