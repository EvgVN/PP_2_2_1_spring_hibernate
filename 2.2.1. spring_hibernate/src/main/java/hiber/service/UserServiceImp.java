package hiber.service;

import hiber.dao.UserDao;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional
   @Override
   public void getCarUser(String model){
      userDao.getCarUser(model);
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

   @Transactional
   @Override
   public void deleteTables(AnnotationConfigApplicationContext context) {
      Session session = context.getBean(SessionFactory.class).openSession();
      session.beginTransaction();

      String sql = "DROP TABLE IF EXISTS cars";
      session.createSQLQuery(sql).executeUpdate();

      sql = "DROP TABLE IF EXISTS users";
      session.createSQLQuery(sql).executeUpdate();

      session.getTransaction().commit();
      session.close();
   }

}
