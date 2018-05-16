package com.lotus.config;

import com.lotus.service.impl.MyAuthenticationProvider;
import com.lotus.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by liuzhiqiang on 2018/5/9.
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider customAuthenticationProvider;

    /**
     * * 自定义UserDetailsService，从数据库中读取用户信息
     *
     * @return
     */
    @Bean
    public UserDetailsService customUserService() {
        return new UserServiceImpl();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
        auth.userDetailsService(customUserService()).passwordEncoder(new MyPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 所有请求均可访问
        http.authorizeRequests()
                .antMatchers("/", "/login", "/login-error", "/css/**", "/index")
                .permitAll();

        /*// 其余所有请求均需要权限
        http.authorizeRequests()
                .anyRequest()
                .authenticated();

        http.authorizeRequests()
        .antMatchers("/user/index").hasRole("role_admin")//需要权限ROLE_COMMON 才可以访问的路径   <a th:href="@{/common/test}">去test.html</a>
        .anyRequest().authenticated();

        // 配置登录页面的表单 action 必须是 '/login', 用户名和密码的参数名必须是 'username' 和 'password'，
        // 登录失败的 url 是 '/login-error'
        http.formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .failureUrl("/login-error");*/
    	/*http.authorizeRequests()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/login").failureUrl("/login?error=true").permitAll()
        .and()
        .logout().permitAll();*/
    	/*http.authorizeRequests()
         .antMatchers("/user/index").authenticated()
         .antMatchers(HttpMethod.POST,"/shop/order").authenticated()
         .anyRequest()
         .authenticated()
         .and().formLogin().loginPage("/login")
         .defaultSuccessUrl("/",true).failureUrl("/login?error").permitAll();*/
        /*http.authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/",true).failureUrl("/login?error").permitAll()
                .and().authorizeRequests().antMatchers("/user/index").hasRole("role_admin")
                .anyRequest().permitAll();*/
        /*http.authorizeRequests()
        .anyRequest().authenticated()
        .and().formLogin().loginPage("/login")
        //设置默认登录成功跳转页面
        .defaultSuccessUrl("/index").failureUrl("/login?error").permitAll()
        .and()
        //开启cookie保存用户数据
        .rememberMe()
        //设置cookie有效期
        .tokenValiditySeconds(60 * 60 * 24 * 7)
        //设置cookie的私钥
        .key("")
        .and()
        .logout()
        //默认注销行为为logout，可以通过下面的方式来修改
        .logoutUrl("/custom-logout")
        //设置注销成功后跳转页面，默认是跳转到登录页面
        .logoutSuccessUrl("")
        .permitAll();*/

        http.authorizeRequests()
                .antMatchers("/user/index").hasRole("admin")//注意角色命要 以ROLE_开头   需要权限ROLE_COMMON 才可以访问的路径   <a th:href="@{/common/test}">去test.html</a>
                .anyRequest().authenticated() // 只有具有任意的某个权限就可以访问其他访问-没有权限还是无法访问的
                .and()
                .formLogin()//对于form表单登陆
                //.passwordParameter("a").usernameParameter("b")//如果你前台登陆的form表单登录名和密码不是username,password，那么就配置本行修改你需要的名字
                .loginPage("/login")//未登陆的情况下，默认跳转的页面
                .failureUrl("/login?error").permitAll() //如果登陆失败，跳转的url
                .and().logout().permitAll(); // 允许任何请求（不管有没有权限以及拥有何种权限）登出
        http.csrf().disable();
    }
}
