package controllers;

import db.DBHelper;
import db.helpers.DBDepartment;
import db.helpers.DBManager;
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

            List<Manager> managers = DBManager.getAll();
            model.put("managers", managers);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/managers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Department> departments = DBDepartment.getAll();
            model.put("departments", departments);
            model.put("template", "templates/managers/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());


        post("/managers", (req, res) -> {
            //get the departments id
            int departmentId = Integer.parseInt(req.queryParams("department"));
            //find the department by that id
            Department department = DBDepartment.find(departmentId);
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

        get("/managers/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int managerId = Integer.parseInt(req.params(":id"));
            Manager selectedManager = DBManager.find(managerId);
            List<Department> departments = DBDepartment.getAll();
            model.put("departments", departments);
            model.put("manager", selectedManager);
            model.put("template", "templates/managers/edit.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        post("/managers/:id/update", (req,res) -> {
            //get the departments id
            int departmentId = Integer.parseInt(req.queryParams("department"));
            //find the department by that id
            Department department = DBDepartment.find(departmentId);
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
            int managerId = Integer.parseInt(req.params(":id"));
            manager.setId(managerId);
            //save it using the DB Helper
            DBHelper.save(manager);
            //do a redirect away from the page to the same page
            res.redirect("/managers");
            return null;
        }, new VelocityTemplateEngine());

        post("/managers/:id/delete", (req,res) -> {
            int managerId = Integer.parseInt(req.params(":id"));
            Manager selectedManager = DBManager.find(managerId);
            DBHelper.delete(selectedManager);
            res.redirect("/managers");
            return null;
        }, new VelocityTemplateEngine());

    }


}
