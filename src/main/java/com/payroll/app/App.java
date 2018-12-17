package com.payroll.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.payroll.controllers.TestController;
import com.payroll.rest.EmployeeRestController;
import com.payroll.services.DepartmentService;
import com.payroll.services.EmployeeService;
import com.payroll.services.LoginService;
import com.payroll.services.SkillsService;

@Configuration
@ComponentScan({"com.payroll"})
@EnableWebMvc
@EnableAutoConfiguration

@SpringBootApplication

public class App extends SpringBootServletInitializer {

	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(App.class);
	}
	
	@Bean
	public EmployeeRestController employeeRestController(){
		return new EmployeeRestController();
	}
	
	@Bean
	public TestController testController(){
		return new TestController();
	}
	
	@Bean
	public EmployeeService employeeService(){
		return new EmployeeService();
	}
	@Bean
	public DepartmentService departmentService(){
		return new DepartmentService();
	}
	@Bean
	public SkillsService skillsService(){
		return new SkillsService();
	}
	@Bean
	public LoginService loginService(){
		return new LoginService();
	}
	@Bean
	public UrlBasedViewResolver setUpViewResolver(){
		UrlBasedViewResolver resolver=new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		
		return resolver;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SpringApplication.run(App.class, args);
	}

}
