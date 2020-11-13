package me.lee.demobootweb;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addFormatter(new PersonFormatter());
		
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new GreetingInterceptor()).order(0);
		registry.addInterceptor(new AnotherInterceptor())
		.addPathPatterns("/hi")
		.order(-1);
	}
	
	// 리소스 핸들러
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/mobile/**")
				.addResourceLocations("classpath:/mobile/")
				// 캐시기능. (캐시 전략)
				.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
	}
	
}
