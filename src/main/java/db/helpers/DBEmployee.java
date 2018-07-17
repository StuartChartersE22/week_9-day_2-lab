package db.helpers;

import db.DBHelper;
import models.Employee;

import java.util.List;

public class DBEmployee extends DBHelper {

    public static List<Employee> getAll(){
        return getAll(Employee.class);
    }

    public static void deleteAll(){
        deleteAll(Employee.class);
    }
}
