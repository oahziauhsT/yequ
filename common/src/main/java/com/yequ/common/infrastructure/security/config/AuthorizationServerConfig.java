package com.yequ.common.infrastructure.security.config;

import com.google.common.base.Predicates;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
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
    JWTSecurityFilter jwtSecurityFilter;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/swagger-ui.html","/v2/**","/swagger-resources/**","/webjars/**","/admin/login").anonymous()
                .anyRequest().authenticated().and().addFilterBefore(jwtSecurityFilter, UsernamePasswordAuthenticationFilter.class);
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
        return new Docket(DocumentationType.SWAGGER_2).groupName("webApi").apiInfo(webApiInfo()).select().paths(Predicates.not(PathSelectors.regex("/error*"))).build().globalOperationParameters(getParameterList());
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder().title("夜曲API中心").description("spring boot 从零开始").version("1.0").build();
    }

    private List<Parameter> getParameterList(){
        ParameterBuilder clientIdTickt = new ParameterBuilder();
        List<Parameter> parameters = new ArrayList<Parameter>();
        clientIdTickt.name(CommonConstant.TOKEN_STR).description("请求令牌").modelRef(new ModelRef("String"))
                .parameterType("header").required(false).build();
        parameters.add(clientIdTickt.build());
        return parameters;
    }


}
