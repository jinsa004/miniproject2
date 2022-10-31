// package site.metacoding.miniproject.config;

// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
// import site.metacoding.miniproject.config.auth.JwtAuthorizationFilter;

// @Slf4j
// @Configuration
// @RequiredArgsConstructor
// public class FilterConfig {

// // @Bean
// // public FilterRegistrationBean<JwtAuthenticationFilter>
// // jwtAuthenticationFilterRegister() {// IoC등록 서버실행시
// // log.debug("디버그 : 인증 필터 등록");
// // FilterRegistrationBean<JwtAuthenticationFilter> bean = new
// // FilterRegistrationBean<>(
// // new JwtAuthenticationFilter(userRepository));
// // bean.addUrlPatterns("/login");
// // bean.setOrder(1);// 낮은 순서대로 실행
// // return bean;
// // }

// @Bean
// public FilterRegistrationBean<JwtAuthorizationFilter>
// jwtAuthorizationFilterRegister() {// IoC등록 서버실행시
// log.debug("디버그 : 인가 필터 등록");
// FilterRegistrationBean<JwtAuthorizationFilter> bean = new
// FilterRegistrationBean<>(
// new JwtAuthorizationFilter());
// bean.addUrlPatterns("/s/*"); // 원래 주소창 뒤엔 **로 모든 걸 포함할 수 있지만 여기서는 * 하나로 예외.
// bean.setOrder(2);// 낮은 순서대로 실행
// return bean;
// }

// }
