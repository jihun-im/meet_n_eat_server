package com.example;

import com.example.user.User;
import com.example.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import java.util.Arrays;

@Configuration
@EnableResourceServer // API 서버 인증
@SpringBootApplication
public class DemoApplication extends ResourceServerConfigurerAdapter {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return (evt) -> Arrays.asList(
                "jhoeller@naver.com,dsyer@naver.com,pwebb@naver.com,asd@naver.com".split(","))
                .forEach(
                        a -> {
                            User user = userRepository.save(new User(a, a,
                                    "picture_url_hahaha"));
                        });
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        http.authorizeRequests()
                //.anyRequest().authenticated()
                //.antMatchers(HttpMethod.POST, "/api/v1/users").permitAll();
                //.antMatchers(HttpMethod.POST,"/api/v1/users").permitAll()
                //.anyRequest().authenticated()
                .antMatchers("api/v1/users", "api/v1/users/**").permitAll()
                .antMatchers("api/v1/requests", "api/v1/requests/**").authenticated()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/h2-console").permitAll();
        //super.configure(http);
    }

    //    @Bean
//    CommandLineRunner init(AccountRepository accountRepository,
//                           BookmarkRepository bookmarkRepository) {
//        return (evt) -> Arrays.asList(
//                "jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
//                .forEach(
//                        a -> {
//                            User user = accountRepository.save(new User(a,
//                                    "password"));
//                            bookmarkRepository.save(new Bookmark(account,
//                                    "http://bookmark.com/1/" + a, "A description"));
//                            bookmarkRepository.save(new Bookmark(account,
//                                    "http://bookmark.com/2/" + a, "A description"));
//                        });
//    }
}
