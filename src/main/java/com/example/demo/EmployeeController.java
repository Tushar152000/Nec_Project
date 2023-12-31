package com.example.demo;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private AdminRepository adminRepository;
    
    @GetMapping("/")
    public String viewFirstPage() {
        return "First";
    }
    @GetMapping("/First.html")
    public String viewFirst() {
        return "First";
    }

    
    @GetMapping("/about.html")
    public String viewAboutPage() {
        return "about";
    }
    
    @GetMapping("/contact.html")
    public String viewContactPage() {
        return "contact";
    }
    @GetMapping("/EmployeeLogin.html")
    public String viewEmpPage() {
        return "EmployeeLogin";
    }
    @GetMapping("/admin.html")
    public String viewAdminPage() {
        return "admin";
    }
    @GetMapping("/welcome")
    public String showWelcomePage(Model model) {
       
        String employeeName = getEmployeeName(); 
        model.addAttribute("employeeName", employeeName);
        return "welcome";
}
        private String getEmployeeName() {
        return "Tushar Chauhan";
    }
        @GetMapping("/logout")
    public String logout() {
        return "redirect:/admin.html";
    }
    @GetMapping("/logoutemp")
    public String logoutemp() {
        return "redirect:/EmployeeLogin.html";
    }
    @GetMapping("/update/{id}")
    public String updateEmployee(@PathVariable long id, Model model) {
        // Your code here
        return "update";
    }
        @PostMapping("/admin-login")
    public String adminLogin(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        RedirectAttributes att
    )
     {
        Admin admin = adminRepository.findByUsername(username);

        if (admin != null) {
            System.out.println("Stored Password: " + admin.getPassword());
            System.out.println("Entered Password: " + password);

            if (Objects.equals(admin.getPassword(), password)) {
                return "redirect:/index";
            }
        }

        String errorMsg = "Invalid Username or Password!!";
        att.addFlashAttribute("errorMsg", errorMsg);
        return "redirect:/admin.html";
    }

    @GetMapping("/index")
    public String getAllEmployees(Model model) {
        model.addAttribute("listEmployees", employeeService.getAllEmployees());
        return "index";
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.saveEmployee(employee);
        return "redirect:/index";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") long id) {

        // call delete employee method 
        this.employeeService.deleteEmployeeById(id);
        return "redirect:/index";
    }

    @PostMapping("/employee-login")
    public String employeeLogin(
        @RequestParam("username") String username,
        @RequestParam("password") String password,
        RedirectAttributes att
    ) {
        // Authenticate the employee using username and password
        Employee employee = employeeService.authenticateEmployee(username, password);

        if (employee != null) {
            // Authentication successful, redirect to welcome.html
            return "redirect:/welcome";
        } else {
            // Authentication failed, add error message and stay on the login page
            String errorMsg = "Invalid Username or Password!!";
            att.addFlashAttribute("errorMsg", errorMsg);
            return "redirect:/EmployeeLogin.html";
        }
    }
}