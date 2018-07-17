package db.helpers;

import db.DBHelper;
import models.Department;
import models.Manager;

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

    public static List<Manager> getManagerForDepartment(Department department){
        return getAssociationsForAnObject(department, Manager.class, "department");
    }
}
