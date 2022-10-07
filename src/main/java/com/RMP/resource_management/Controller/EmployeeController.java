package com.RMP.resource_management.Controller;

import com.RMP.resource_management.Model.*;
import com.RMP.resource_management.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private ShareService shareService;

    @Autowired
    private FormService formService;

    @GetMapping("/")
    public String viewHomePage() {
        List<Employee> profiles = employeeService.getAllEmployees();
        for (Employee employee : profiles) {
            if (employee.getBlockTime() != null) {
                long diffInMillies1 = Math.abs(new Date().getTime() - employee.getBlockTime().getTime());
                long diff1 = TimeUnit.DAYS.convert(diffInMillies1, TimeUnit.MILLISECONDS);
                if (diff1 >= 2) {
                    employee.setBlockTime(null);
                    employee.setBlock("true");
                    employeeService.saveEmployee(employee);
                }
            }
        }
        return "index";
    }

    @GetMapping("/manager/{id}")
    public String filterShare(@PathVariable(value = "id") long id, Model model) {
        Manager manager = managerService.getManagerDetails(id);
        List<Share> share = shareService.getAllShareDetails();
        List<Employee> employeeList = employeeService.getAllEmployees();
        List<Employee> filterProfiles = new ArrayList<>();
        for (int i = 0; i < share.size(); i++) {
            if (share.get(i).getManager_id() == id) {
                for (Employee employee : employeeList) {
                    if (employee.getId() == share.get(i).getEmployee_id()) {
                        filterProfiles.add(employee);
                    }
                }
            }
        }
        model.addAttribute("manager_block", "true");
        model.addAttribute("manager", manager);
        model.addAttribute("listEmployees", filterProfiles);

        return "details";
    }

    @GetMapping("/managerPage/{emp_id}")
    public String viewManagerPage(@PathVariable(value = "emp_id") long emp_id, Model model) {
        List<Manager> managerList = managerService.getAllManagerDetails();
        List<Share> shareList = shareService.getAllShareDetails();
        for (Share share : shareList) {
            if (emp_id == share.getEmployee_id()) {
                for (Manager manager : managerList) {
                    if (share.getManager_id() == manager.getId()) {
                        manager.setSelected("true");
                    }
                }
            }
        }
//        System.out.println(managerList.get(0).getSelected());
        model.addAttribute("listManagers", managerList);
        model.addAttribute("emp_id", emp_id);
        return "managers";
    }

    @GetMapping("/share/{emp_id}/{mng_id}")
    public String shareDetails(@PathVariable(value = "emp_id") long emp_id, @PathVariable(value = "mng_id") long mng_id) {
        Share share = new Share(emp_id, mng_id);
        shareService.saveSharedDetails(share);
        return "redirect:/managerPage/" + emp_id;
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
        List<Employee> filterResults = new ArrayList<>();
        if (value.equalsIgnoreCase("All")) {
            filterResults = employees;
        } else {
            for (Employee profile : employees) {
                if (profile.getSkill_Set().equalsIgnoreCase(value)) {
                    filterResults.add(profile);
                }
            }
        }
        model.addAttribute("listEmployees", filterResults);

        return "details";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employee.setBlock("true");
        employee.setBlockCount(0);
        employeeService.saveEmployee(employee);
        return "availablePools";
    }

    @GetMapping("/updateBlock/{id}")
    public String updateBlock(@PathVariable(value = "id") long id) {
        Employee e = employeeService.getEmployeeById(id);
        System.out.println(e.getName());
        e.setBlockTime(new Date());
        e.setBlock("false");
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
        employee.setBlock("false");
        this.employeeService.saveEmployee(employee);
        return "formPage";
    }

    @GetMapping("/track/{value}")
    public String fetchByTrack(@PathVariable(value = "value") String subSkill, Model model) {
        List<Employee> employee = employeeService.getEmployeesByTrack(subSkill);
//        System.out.println(employee.get(0).getName());
        model.addAttribute("listEmployees", employee);
        return "details";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") Long id) {
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/";
    }

    @GetMapping("/reject/{id}")
    public String reject(@PathVariable(value = "id") long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee.getBlockCount() == 2) {
            employee.setBlock("false");
            employee.setBlockCount(3);
        } else {
            employee.setBlockCount(employee.getBlockCount() + 1);
        }
        employeeService.saveEmployee(employee);
        List<Employee> employeesList = employeeService.getAllEmployees();
        model.addAttribute("listEmployees", employeesList);
        return "details";
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
        return "details";
    }
}
