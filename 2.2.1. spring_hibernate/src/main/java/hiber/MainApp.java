package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      Car landCruiser = new Car("Toyota Land Cruiser", 200);
      user1.setCar(landCruiser);

      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      Car nissan = new Car("Nissan 400z", 400);
      user2.setCar(nissan);

      User user3 = new User("User3", "Lastname3", "user3@mail.ru");
      Car bmw = new Car("BMW 5-series", 500);
      user3.setCar(bmw);

      User user4 = new User("User4", "Lastname4", "user4@mail.ru");
      Car mb = new Car("Mercedes-benz", 300);
      user4.setCar(mb);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      userService.getUserByCarModel("Nissan 400z");


      context.close();
   }
}
