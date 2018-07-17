package controllers;

import db.DBHelper;
import db.Seeds;
import db.helpers.DBEmployee;
import models.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;

public class EmployeesController {

    public static void main(String[] args) {

        Seeds.seedData();

        ManagersController managersController = new ManagersController();
        DepartmentContoller departmentContoller = new DepartmentContoller();
        EngineerController engineerController = new EngineerController();

        get("/employees", (req, res) -> {
            Map<String, Object> model= new HashMap();
            model.put("template", "templates/employees/index.vtl");

            List<Employee> employees = DBEmployee.getAll();
            model.put("employees", employees);
            return new ModelAndView(model, "templates/layout.vtl");
        }, new VelocityTemplateEngine());

    }
}
