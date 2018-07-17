package db.helpers;

import db.DBHelper;
import models.Engineer;

import java.util.List;

public class DBEngineer extends DBHelper {

    public static Engineer find(int id){
        return find(id, Engineer.class);
    }

    public static List<Engineer> getAll(){
        return getAll(Engineer.class);
    }

    public static void deleteAll(){
        deleteAll(Engineer.class);
    }
}
