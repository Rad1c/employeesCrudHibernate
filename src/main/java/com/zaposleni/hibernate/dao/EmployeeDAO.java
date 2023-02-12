package com.zaposleni.hibernate.dao;

import java.util.List;
import org.hibernate.Transaction;
import com.zaposleni.hibernate.model.Employee;
import com.zaposleni.hibernate.util.HibernateUtil;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class EmployeeDAO {

  public static List < Employee > searchEmployees(String name) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    List < Employee > employees = null;
    
    try {
      transaction = session.beginTransaction();
      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery < Employee > criteria = builder.createQuery(Employee.class);
      Root < Employee > root = criteria.from(Employee.class);
      criteria.select(root).where(builder.like(root.get("name"), "%" + name + "%"));
      
      employees = session.createQuery(criteria).getResultList();
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
    return employees;
  }

  public static void updateAddEmployee(int id, Employee updatedEmployee) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    
    try {
      transaction = session.beginTransaction();
      Employee employee = (Employee) session.get(Employee.class, id);
      
      if(employee == null){
          session.persist(updatedEmployee);
      }else{
          employee.setName(updatedEmployee.getName());
          employee.setAddress(updatedEmployee.getAddress());
          employee.setAmount(updatedEmployee.getAmount());
          employee.setAge(updatedEmployee.getAge());
          session.update(employee);
      }
      
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
  }

  public static void deleteEmployee(int id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction transaction = null;
    
    try {
      transaction = session.beginTransaction();
      Employee employee = session.load(Employee.class, id);
      session.delete(employee);
      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      session.close();
    }
  }

  public static List < Employee > getAllEmployees() {
    List < Employee > employees = null;
    Session session = null;
    Transaction transaction = null;
    
    try {
      session = HibernateUtil.getSessionFactory().openSession();
      transaction = session.beginTransaction();

      CriteriaBuilder builder = session.getCriteriaBuilder();
      CriteriaQuery < Employee > criteria = builder.createQuery(Employee.class);
      Root < Employee > root = criteria.from(Employee.class);
      criteria.select(root);
      employees = session.createQuery(criteria).getResultList();

      transaction.commit();
    } catch (HibernateException e) {
      if (transaction != null) {
        transaction.rollback();
      }
    } finally {
      if (session != null) {
        session.close();
      }
    }
    return employees;
  }
}