package hello.exception;

import static javax.servlet.DispatcherType.ERROR;
import static javax.servlet.DispatcherType.REQUEST;

import hello.exception.filter.LogFilter;
import hello.exception.interceptor.LogInterceptor;
import java.util.Collections;
import javax.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LogInterceptor())
        .order(1)
        .addPathPatterns("/**")
        .excludePathPatterns("/css/**", "*.ico", "error", "error-page/**"); // 오류 페이지 경로
  }

  //  @Bean
  public FilterRegistrationBean lgoFilter() {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new LogFilter());
    filterRegistrationBean.setOrder(1);
    filterRegistrationBean.setUrlPatterns(Collections.singleton("/*"));
    filterRegistrationBean.setDispatcherTypes(REQUEST, ERROR);
    return filterRegistrationBean;
  }
}
