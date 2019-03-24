package nl.qien.feigndemo;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import nl.qien.feigndemo.client.UserClient;
import nl.qien.feigndemo.model.User;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        UserClient userClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(UserClient.class))
                .logLevel(Logger.Level.FULL)
                .target(UserClient.class, "http://localhost:8081/api/users");

        List<User> allUsers = userClient.findAll();

        System.out.println(allUsers);

        allUsers.forEach(user -> {
            System.out.println(user);
        });

        int actualSize = allUsers.size();

        User newUser = new User();
        newUser.setId(314);
        newUser.setName("Francien");
        newUser.setUsername("fvanrooyen");
        newUser.setEmail("francien@example.com");
        userClient.create(newUser);

        allUsers = userClient.findAll();

        System.out.println(allUsers);

        allUsers.forEach(user -> {
            System.out.println(user);
        });

        assert allUsers.size() == actualSize+1;


    }
}
