package db.helpers;

import db.DBHelper;
import models.Manager;

import java.util.List;

public class DBManager extends DBHelper {

    public static Manager find(int id){
        return find(id, Manager.class);
    }

    public static List<Manager> getAll(){
        return getAll(Manager.class);
    }

    public static void deleteAll(){
        deleteAll(Manager.class);
    }
}
