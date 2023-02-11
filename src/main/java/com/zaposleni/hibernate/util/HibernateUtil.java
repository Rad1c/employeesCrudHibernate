package com.zaposleni.hibernate.util;

import com.zaposleni.hibernate.model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

  private static final SessionFactory sessionFactory = buildSessionFactory();

  private static SessionFactory buildSessionFactory() {
    try {
      Configuration configuration = new Configuration();
      configuration.configure("hibernate.cfg.xml");
      configuration.addAnnotatedClass(Employee.class);

      return configuration.buildSessionFactory(
        new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build());
    } catch (HibernateException e) {
      throw new RuntimeException("There was an error building the factory");
    }
  }

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void shutdown() {
    getSessionFactory().close();
  }
}