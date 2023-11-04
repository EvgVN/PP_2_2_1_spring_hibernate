package hiber.service;

import hiber.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    void add(User user);


    void getUserByCarModel(String model);

    List<User> listUsers();
}
