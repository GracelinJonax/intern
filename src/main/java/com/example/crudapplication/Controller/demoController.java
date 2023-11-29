package com.example.crudapplication.Controller;

import com.example.crudapplication.Dto.ResponseEntityDTO;
import com.example.crudapplication.Repository.EmployeeRepository;
import com.example.crudapplication.Service.demoService;
import com.example.crudapplication.model.Department;
import com.example.crudapplication.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class demoController {
    @Autowired
    demoService demoService;
    @PostMapping("/addEmpolyee/{dept_id}")
    public void addEmployeee(@PathVariable int dept_id, @RequestBody Employee employee){
        demoService.addEmployeeService(employee,dept_id);
    }
    @PostMapping("/addDepartment")
    public void addEmployee(@RequestBody Department department){
        demoService.addDeparmentSertvice(department);
    }
    @GetMapping("/allEmployee")
    public List<Employee> getAllEmployee(){
        return demoService.getAllEmployeeService();
    }
    @GetMapping("getEmployee/{emp_id}")
    public ResponseEntity<ResponseEntityDTO> getEmployee(@PathVariable int emp_id){
        return demoService.getEmployeeService(emp_id);
    }
    @GetMapping("allDepartment")
    public List<Department> getAllDepartment(){
        return demoService.getAllDepartmentService();
    }
    @GetMapping("getDepartment/{dept_id}")
    public ResponseEntity<ResponseEntityDTO> getDepartment(@PathVariable int dept_id){
        return demoService.getDepartmentService(dept_id);
    }
    @GetMapping("getDeptEmployee/{dept_id}")
    public ResponseEntity<ResponseEntityDTO> getDeptEmployee(@PathVariable int dept_id){
        return demoService.getDeptEmployeeService(dept_id);
    }
    @PutMapping("updateEmployee/{emp_id}")
        public void updateEmployee(@PathVariable int emp_id, @RequestBody Employee employee){
            demoService.updateEmployeeService(emp_id,employee);
        }
    @DeleteMapping("deleteEmployee/{emp_id}")
    public void deleteEmployee(@PathVariable int emp_id){
        demoService.deleteEmployeeService(emp_id);
    }
    @DeleteMapping("deleteDepartment/{dept_id}")
    public void deleteDepartment(@PathVariable int dept_id){
        demoService.deleteDepartmentService(dept_id);
    }
}
