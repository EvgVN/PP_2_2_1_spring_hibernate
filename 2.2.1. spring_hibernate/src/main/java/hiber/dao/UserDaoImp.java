package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    public void getCarUser(String model, int series) {
//        Car car = sessionFactory.getCurrentSession().createQuery("FROM Car car LEFT OUTER JOIN FETCH car.user WHERE car.model = :model AND car.series = :series", Car.class).setParameter("model", model).uniqueResult();
        String hql = "FROM Car car LEFT OUTER JOIN FETCH car.user WHERE car.model = :model AND car.series = :series";
        Car car = sessionFactory.getCurrentSession()
                .createQuery(hql, Car.class)
                .setParameter("model", model)
                .setParameter("series", series)
                .uniqueResult();
        if (car != null) {
            System.out.println(car.getModel() + "-" + car.getSeries() + " user is: " + car.getUser().getFirstName() + " " + car.getUser().getLastName() + " " + car.getUser().getEmail());
        } else {
            System.out.println("No car found with model: " + model + " and series: " + series);
        }
    }

    public void deleteTables(AnnotationConfigApplicationContext context) {
        Session session = context.getBean(SessionFactory.class).openSession();
        session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS cars, users";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
