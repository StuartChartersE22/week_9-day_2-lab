package controllers;

import db.DBHelper;
import db.helpers.DBDepartment;
import models.Department;
import models.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class DepartmentContoller {
    public DepartmentContoller(){
        this.setupEndPoints();
    }

    private void setupEndPoints(){
        get("/departments", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            List<Department> departmentList = DBDepartment.getAll();
            model.put("departments", departmentList);
            model.put("template", "templates/departments/index.vtl");
            return  new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

        get("/departments/new", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/departments/create.vtl");
            return new ModelAndView(model, "templates/layout.vtl");


        }, new VelocityTemplateEngine());

        post("/departments", (req, res) -> {
            String title = req.queryParams("title");
            Department department = new Department(title);
            DBHelper.save(department);
            res.redirect("/departments");
            return null;
        }, new VelocityTemplateEngine());



        get("/departments/:id/edit", (req, res) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/departments/edit.vtl");
            String title = req.queryParams("title");
            int departmentId = Integer.parseInt(req.params(":id"));
            Department department = DBDepartment.find(departmentId);
            model.put("department", department);
            return new ModelAndView(model, "templates/layout.vtl");

            }, new VelocityTemplateEngine());

        post("/departments/:id/update", (req, res) -> {
            String title = req.queryParams("title");
            Department department = new Department(title);
            int departmentId = Integer.parseInt(req.params(":id"));
            department.setId(departmentId);
            DBHelper.save(department);
            res.redirect("/departments");
            return null;
        }, new VelocityTemplateEngine());

        post("/departments/:id/delete", (req,res) -> {
            int departmentId = Integer.parseInt(req.params(":id"));
            Department selectedDepartment = DBDepartment.find(departmentId);
            DBHelper.delete(selectedDepartment);
            res.redirect("/departments");
            return null;
        }, new VelocityTemplateEngine());

        get("/department/:id", (req,res) -> {
            Map<String, Object> model = new HashMap<>();
            int departmentId = Integer.parseInt(req.params(":id"));
            Department selectedDepartment = DBDepartment.find(departmentId);
//            List<String[]> employees = DBDepartment.getEmployeesForDepartment(selectedDepartment);
            model.put("department", selectedDepartment);
//            model.put("employees", employees);
            model.put("template", "templates/departments/show.vtl");
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}
