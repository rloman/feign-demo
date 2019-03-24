package nl.qien.feigndemo.client;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import nl.qien.feigndemo.model.User;

import java.util.List;

public interface UserClient {

    @RequestLine("GET")
    List<User> findAll();

    @RequestLine("GET /{id}")
    User findById(@Param("id") long id);

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    void create(User user);


}
