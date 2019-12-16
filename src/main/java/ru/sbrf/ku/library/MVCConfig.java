//package ru.sbrf.ku.library;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.support.ReloadableResourceBundleMessageSource;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.springframework.web.servlet.i18n.CookieLocaleResolver;
//import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import java.util.Locale;
//
//@EnableWebMvc  //<mvc:annotation-driven>
//@Configuration
//@ComponentScan
//public class MVCConfig  extends WebMvcConfigurerAdapter {
//
//    /**
//     * <mvc:resources mapping="/resources/**" location="/resources/" />
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//    }
//
//    /**
//     * bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
//     */
//    @Bean
//    public InternalResourceViewResolver jspViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setOrder(1);
//        viewResolver.setPrefix("/WEB-INF/view/");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }
//
//    /**
//     *  <mvc:interceptors>
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(getLocaleChangeInterceptor()).addPathPatterns("/*");
//    }
//
//    @Bean(name = "localeChangeInterceptor")
//    public LocaleChangeInterceptor getLocaleChangeInterceptor() {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("languageVar");
//        return localeChangeInterceptor;
//    }
//
//    /**
//     *  <bean id="localeResolver" class="org.springframework.web.servlet.i18n.CookieLocaleResolver">
//     */
//    @Bean(name = "localeResolver")
//    public CookieLocaleResolver getLocaleResolver() {
//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(new Locale("ru"));
//        cookieLocaleResolver.setCookieMaxAge(100000);
//        return cookieLocaleResolver;
//    }
//
//    /**
//     * <bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
//     */
//    @Bean(name = "messageSource")
//    public ReloadableResourceBundleMessageSource getMessageSource() {
//        ReloadableResourceBundleMessageSource resource = new ReloadableResourceBundleMessageSource();
//        resource.setBasename("classpath:/locales/messages");
//        resource.setCacheSeconds(1);
//        resource.setDefaultEncoding("UTF-8");
//        return resource;
//    }
//}
