package com.RMP.resource_management.Controller;

import com.RMP.resource_management.Model.Employee;
import com.RMP.resource_management.Model.FormDetails;
import com.RMP.resource_management.Service.EmployeeService;
import com.RMP.resource_management.Service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private FormService formService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "New_employee";
    }

    @GetMapping("/availablePools")
    public String availablePools() {
        return "availablePools";
    }

    @GetMapping("/details/{value}")
    public String filterDetails(@PathVariable(value = "value") String value, Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        List<Employee> temp = new ArrayList<Employee>();
        ;
        for (Employee profile : employees) {
            if (profile.getSkillset().toLowerCase().equals(value.toLowerCase())) {
                temp.add(profile);
            }
        }
        model.addAttribute("listEmployees", temp);
        return "details";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/updateBlock/{id}")
    public String updateBlock(@PathVariable(value = "id") long id, Model model) {
        Employee e = employeeService.getEmployeeById(id);
        e.setBlockTime(new Date());
        e.setBlock("true");
        this.employeeService.saveEmployee(e);
        return "redirect:/";
    }

    @GetMapping("/formPage/{id}")
    public String formPage(@PathVariable(value = "id") long id, Model model) {
//        System.out.println(id);
        model.addAttribute("empId", id);
        model.addAttribute("formDetails", new FormDetails());
        return "formPage";
    }

    @PostMapping("/saveForm/{id}")
    public String saveFormDetails(@ModelAttribute("formDetails") FormDetails formDetail,
                                  @PathVariable(value = "id") long id, Model model) {
        formDetail.setEmpID(id);
        this.formService.saveFormDetails(formDetail);
        model.addAttribute("empId", id);
        model.addAttribute("formDetails", new FormDetails());
        model.addAttribute("msg", "Form Submitted successfully");
        Employee employee = employeeService.getEmployeeById(id);
        employee.setBlockTime(new Date());
        employee.setBlock("true");
        this.employeeService.saveEmployee(employee);
        return "formPage";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 10;

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize);
        List<Employee> listEmployees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listEmployees", listEmployees);
        return "index";
    }
}