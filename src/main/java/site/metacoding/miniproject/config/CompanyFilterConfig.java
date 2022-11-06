package site.metacoding.miniproject.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.config.auth.company.CompanyJwtAuthenticationFilter;
import site.metacoding.miniproject.config.auth.company.CompanyJwtAuthorizationFilter;
import site.metacoding.miniproject.domain.company.CompanyDao;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CompanyFilterConfig {

  private final CompanyDao companyDao;

  // @Bean
  public FilterRegistrationBean<CompanyJwtAuthenticationFilter> coJwtAuthenticationFilterRegister() {
    log.debug("디버그 : 인증 필터 등록");
    FilterRegistrationBean<CompanyJwtAuthenticationFilter> bean = new FilterRegistrationBean<>(
        new CompanyJwtAuthenticationFilter(companyDao));
    bean.addUrlPatterns("/co/login");
    bean.setOrder(3);
    return bean;
  }

  // @Bean
  public FilterRegistrationBean<CompanyJwtAuthorizationFilter> coJwtAuthorizationFilterRegister() {
    log.debug("디버그 : 인가 필터 등록");
    FilterRegistrationBean<CompanyJwtAuthorizationFilter> bean = new FilterRegistrationBean<>(
        new CompanyJwtAuthorizationFilter());
    bean.addUrlPatterns("/cs/*");
    bean.setOrder(4);
    return bean;
  }

}
