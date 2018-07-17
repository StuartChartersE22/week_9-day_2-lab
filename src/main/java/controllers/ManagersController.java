package controllers;

import db.DBHelper;
import models.Department;
import models.Manager;
import org.dom4j.rule.Mode;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ManagersController {

    public ManagersController(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){
        //TODO: add routes in here
        get("/managers", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/managers/index.vtl");

            List<Manager> managers = DBHelper.getAll(Manager.class);
            model.put("managers", managers);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/managers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Department> departments = DBHelper.getAll(Department.class);
            model.put("departments", departments);
            model.put("template", "templates/managers/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/managers", (req, res) -> {
            //get the departments id
            int departmentId = Integer.parseInt(req.queryParams("department"));
            //find the department by that id
            Department department = DBHelper.find(departmentId,Department.class);
            //get first name from request
            String firstName = req.queryParams("first_name");
            //get last name from request
            String lastName = req.queryParams("last_name");
            //get salary from request
            int salary = Integer.parseInt(req.queryParams("salary"));
            //get budget from request
            double budget = Double.parseDouble(req.queryParams("budget"));

            //use all these to create a new manager
            Manager manager = new Manager(firstName, lastName, salary, department, budget);
            //save it using the DB Helper
            DBHelper.save(manager);
            //do a redirect away from the page to the same page
            res.redirect("/managers");
            return null;
        }, new VelocityTemplateEngine());

    }


}
