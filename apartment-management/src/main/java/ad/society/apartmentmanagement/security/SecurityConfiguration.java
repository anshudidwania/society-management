package ad.society.apartmentmanagement.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // List that holds all the User Roles other than API_DOCS_USER
    private static final List<String> listOfUserRoles = new ArrayList<>();
    private static final String USER_ROLE_SUFFIX = "_ROLE";
    private static final String API_DOCS_USER = "API_DOCS_USER_ROLE";
    @Value("${app.users}")
    private String[] APP_USERS;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        List<String[]> authUserCredentials = readUserConfigurations();

        for (String[] credentials : authUserCredentials) {
            String userRole = credentials[0] + USER_ROLE_SUFFIX;
            auth.inMemoryAuthentication()
                    .passwordEncoder(passwordEncoder())
                    .withUser(credentials[0])
                    .password(credentials[1])
                    .roles(userRole);
            if(!API_DOCS_USER.equals(userRole)) {
                listOfUserRoles.add(userRole);
            }
        }
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        //Allow Access to the MS-APIs for users other than API_DOCS_USER
        String[] rolesArray = new String[listOfUserRoles.size()];
        rolesArray = listOfUserRoles.toArray(rolesArray);
        // Disable CSRF as the service request can originate from applications other than web browsers
        //ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authReq =
        httpSecurity
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                //.antMatchers("/get*").hasAnyRole(rolesArray)
                //.antMatchers("/get*/**").hasAnyRole(rolesArray)
                //.antMatchers("/save*").hasAnyRole(rolesArray)
                //.antMatchers("/user*").hasAnyRole(rolesArray)
                .antMatchers("/society/**").hasAnyRole(rolesArray)
                .antMatchers("/v2/api-docs").hasAnyRole(rolesArray)
                .antMatchers("/actuator").hasAnyRole(rolesArray)
                //Allowing access to all Swagger URLs for all authenticated users
                //.antMatchers("/swagger-ui.html").hasRole(API_DOCS_USER)
                //.antMatchers("/webjars/**").hasRole(API_DOCS_USER)
                //.antMatchers("/swagger-resources/**").hasRole(API_DOCS_USER)
                //.antMatchers("/csrf").hasRole(API_DOCS_USER)
                //.antMatchers("/").hasRole(API_DOCS_USER)
                //Allowing public access to heartBeat URL
                .antMatchers("/heartBeat").permitAll();
                //.antMatchers("/**").denyAll()
                //.and().formLogin();

        //Default DenyAll Rule
        //authReq.antMatchers("/**").denyAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    // Read User credentials from the file
    private List<String[]> readUserConfigurations() {
        List<String[]> returnValue = new ArrayList<>();
        for (String line : APP_USERS) {
            if (StringUtils.isNotBlank(line)) {
                String[] tokens = StringUtils.split(line, ":");
                returnValue.add(tokens);
            }
        }
        return returnValue;
    }
}
