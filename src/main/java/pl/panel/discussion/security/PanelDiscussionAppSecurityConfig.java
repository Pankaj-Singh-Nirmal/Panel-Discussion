package pl.panel.discussion.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class PanelDiscussionAppSecurityConfig extends WebSecurityConfigurerAdapter 
{
	@Autowired
	private DataSource dataSource; 
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	{
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception 
	{
		http
			.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/user/**").authenticated()
				
			.and()
				.formLogin()
				.loginPage("/login")
				.permitAll()
				
			.and()
				.httpBasic()
				
			.and()
				.logout().logoutUrl("/logout")
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.logoutSuccessUrl("/user/forumPostList");
	}
}
