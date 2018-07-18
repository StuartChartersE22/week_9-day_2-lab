package db.helpers;

import db.DBHelper;
import db.HibernateUtil;
import models.Department;
import models.Employee;
import models.Manager;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.List;

public class DBDepartment extends DBHelper {

    public static Department find(int id){
        return find(id, Department.class);
    }

    public static List<Department> getAll(){
        return getAll(Department.class);
    }

    public static void deleteAll(){
        deleteAll(Department.class);
    }

    public static List<Manager> getManagersForDepartment(Department department){
        return getAssociationsForAnObject(department, Manager.class, "department");
    }

    public static List<Employee> getEmployeesForDepartment(Department department){
        return getAssociationsForAnObject(department, Employee.class, "department");
    }

//    public static List<String[]> getEmployeesForDepartment(Department department){
//        List<String[]> results = null;
//        session = HibernateUtil.getSessionFactory().openSession();
//
//        try {
//            Criteria cr = session.createCriteria(Employee.class);
//            cr.createAlias("department", "single_object");
//            cr.add(Restrictions.eq("single_object.id", department.getId()));
//            cr.setProjection(Projections.property("position"));
//            cr.setProjection(Projections.id());
//            results = cr.list();
//        }catch (HibernateException e){
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//        return results;
//    }
}
