package com.yequ.common.infrastructure.security.config;

import com.yequ.common.infrastructure.security.filter.JWTSecurityFilter;
import com.yequ.common.utils.CommonConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: zhaost
 * @create: 2022-08-30
 **/
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AuthorizationServerConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTSecurityFilter jwtSecurityFilter;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/swagger-ui.html","/v2/**","/swagger-resources/**","/webjars/**","/admin/login","/swagger-ui/*").anonymous()
                .anyRequest().authenticated().and().addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).accessDeniedHandler(accessDeniedHandler);
    }
    //    声明加密方式
    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    //    认证管理器
    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManager();
    }

    //    注入swagger实例对象bean
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("webApi").apiInfo(webApiInfo()).select().paths(PathSelectors.regex("/error").negate()).build().globalRequestParameters(getParameterList());
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder().title("夜曲API中心").description("spring boot 从零开始").version("1.0").build();
    }

    private List<RequestParameter> getParameterList(){
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name(CommonConstant.TOKEN_STR)
                .description("请求令牌")
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }


}
