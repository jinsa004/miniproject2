package site.metacoding.miniproject.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import site.metacoding.miniproject.config.auth.employee.EmpJwtAuthenticationFilter;
import site.metacoding.miniproject.config.auth.employee.EmpJwtAuthorizationFilter;
import site.metacoding.miniproject.domain.employee.EmployeeDao;

@Slf4j
@Profile("prod")
@Configuration
@RequiredArgsConstructor
public class EmpFilterConfig {
    private final EmployeeDao employeeDao;

    @Bean
    public FilterRegistrationBean<EmpJwtAuthenticationFilter> empJwtAuthenticationFilterRegister() {// IoC등록 서버실행시
        log.debug("디버그 : 개인회원 인증 필터 등록");
        FilterRegistrationBean<EmpJwtAuthenticationFilter> bean = new FilterRegistrationBean<>(
                new EmpJwtAuthenticationFilter(employeeDao));
        bean.addUrlPatterns("/emp/login");
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<EmpJwtAuthorizationFilter> empJwtAuthorizationFilterRegister() {// IoC등록 서버실행시
        log.debug("디버그 : 개인회원 인가 필터 등록");
        FilterRegistrationBean<EmpJwtAuthorizationFilter> bean = new FilterRegistrationBean<>(
                new EmpJwtAuthorizationFilter());
        bean.addUrlPatterns("/es/*"); // 원래 주소창 뒤엔 **로 모든 걸 포함할 수 있지만 여기서는 * 하나로 예외.
        bean.setOrder(2);
        return bean;
    }

}
