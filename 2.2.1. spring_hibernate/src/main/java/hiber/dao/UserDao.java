package hiber.dao;

import hiber.model.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> listUsers();

    void getCarUser(String model);

    void deleteTables(AnnotationConfigApplicationContext context);
}
