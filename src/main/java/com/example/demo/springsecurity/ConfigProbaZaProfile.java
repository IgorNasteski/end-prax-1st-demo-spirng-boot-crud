package com.example.demo.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Profile("local")
@Configuration
@EnableWebMvc
public class ConfigProbaZaProfile extends WebSecurityConfigurerAdapter {

	//				UKIDAMO IN MEMORY USERS
	//MORAMO OVERRIDOVATI OVAJ METOD, DESNI KLIK/SOURCE/OVERRIDE-IMPLEMENTS-METHODS
    /*@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//add our users for in memory authentication

		UserBuilder users = User.withDefaultPasswordEncoder();

		auth.inMemoryAuthentication()
			.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
			.withUser(users.username("mary").password("test123").roles("EMPLOYEE","MANAGER"))
			.withUser(users.username("susan").password("test123").roles("EMPLOYEE","ADMIN"));
	}*/
	//KRAJ	UKIDAMO IN-MEMORY USERE, KORISTIMO IZ BAZE KREIRANE



	//				KORISTIMO USERE IZ BAZE
	@Qualifier("dataSource")
	@Autowired
	DataSource dataSource;

	//Enable jdbc authentication
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//.anyRequest().authenticated()	//svaki request koji "dolazi" na app mora biti autentifikovan
				//kada je ovo bilo aktivirano, svi KOJI SU BILI AUTENTIKOVANI su mogli da pristupaju npr linku za samo managere
				//a onda sam dodao ispod OVA TRI REDA, KAKO BI OGRANICIO KO GDE SME DA PRISTUPI:
				.antMatchers("/").hasRole("EMPLOYEE")
				.antMatchers("/systems/**").hasRole("ADMIN")
				.antMatchers("/employee/**").hasRole("MANAGER")

				.antMatchers("/").hasRole("EMPLOYEE")
				.antMatchers("/users/managers/**").hasRole("MANAGER")	//morao da dodam /users pre "managers" jer se na tom "/users" kontroleru regulise ceo dalji tok managera
				.antMatchers("/users/admins/**").hasRole("ADMIN")
				.and()
				.formLogin()
				/*.loginProcessingUrl("/authenticateTheUser")
				.permitAll()*/	//dozvoljavamo svima da vide login page
				.and()
				.logout().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied");	//morao da dodam u kontroler jer gadja "access-denied" putanju
	}
}
