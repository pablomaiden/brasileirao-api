package br.com.phc.brasileiraoapi.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataBaseConfig {
	
	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	@Value("${spring.datasource.url}")
	private String url;

	@Value("${spring.datasource.username}")
	private String user;

	@Value("${spring.datasource.password}")
	private String password;
	
	@Bean
	public DataSource getDataSource() {
	    HikariConfig hikariConfig = new HikariConfig();
	    hikariConfig.setUsername(user);
	    hikariConfig.setPassword(password);
	    hikariConfig.setDriverClassName(driverClassName);
	    hikariConfig.setJdbcUrl(url);
	    return new HikariDataSource(hikariConfig);
	}
	
	
	
	

}
