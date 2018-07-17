package controllers;

import db.DBHelper;
import models.Department;
import models.Engineer;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class EngineerController {

    public EngineerController(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){
        get("/engineers", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/engineers/index.vtl");

            List<Engineer> engineers = DBHelper.getAll(Engineer.class);
            model.put("engineers", engineers);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/engineers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Department> departments = DBHelper.getAll(Department.class);
            model.put("departments", departments);
            model.put("template", "templates/engineers/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/engineers", (req, res) -> {
            //get the departments id
            int departmentId = Integer.parseInt(req.queryParams("department"));
            //find the department by that id
            Department department = DBHelper.find(departmentId, Department.class);
            //get first name from request
            String firstName = req.queryParams("first_name");
            //get last name from request
            String lastName = req.queryParams("last_name");
            //get salary from request
            int salary = Integer.parseInt(req.queryParams("salary"));

            //use all these to create a new engineer
            Engineer engineer = new Engineer(firstName, lastName, salary, department);
            //save it using the DB Helper
            DBHelper.save(engineer);
            //do a redirect away from the page to the same page
            res.redirect("/engineers");
            return null;
        }, new VelocityTemplateEngine());


    }


}
