package controllers;

import db.DBHelper;
import models.Department;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class DepartmentContoller {
    public DepartmentContoller(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){
        get("/departments", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            List<Department> departmentList = DBHelper.getAll(Department.class);
            model.put("departments", departmentList);
            model.put("template", "templates/departments/index.vtl");
            return  new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());
    }
}
