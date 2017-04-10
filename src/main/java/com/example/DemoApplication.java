package com.example;

import com.example.user.User;
import com.example.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    CommandLineRunner init(UserRepository userRepository) {
        return (evt) -> Arrays.asList(
                "jhoeller@naver.com,dsyer@naver.com,pwebb@naver.com,asd@naver.com".split(","))
                .forEach(
                        a -> {
                            User user = userRepository.save(new User(a,a,a,
                                    "picture_url_hahaha"));
                        });
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
