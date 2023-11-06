package hiber.service;

import hiber.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public interface UserService {
    void add(User user);

    void getCarUser(String model);

    List<User> listUsers();

    void deleteTables(AnnotationConfigApplicationContext context);
}
