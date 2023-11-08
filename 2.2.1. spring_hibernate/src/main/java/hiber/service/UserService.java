package hiber.service;

import hiber.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public interface UserService {
    void add(User user);

    List<User> listUsers();

    String getCarUser(String model, int series);

    void deleteTables(AnnotationConfigApplicationContext context);
}
