package com.example.crudapplication.Service;

import com.example.crudapplication.Dto.ResponseEntityDTO;
import com.example.crudapplication.Repository.DepartmentRepository;
import com.example.crudapplication.Repository.EmployeeRepository;
import com.example.crudapplication.model.Department;
import com.example.crudapplication.model.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class demoService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    public void addEmployeeService(Employee employee,int deparmentId){
        employee.setDepartment(departmentRepository.findById(deparmentId).get());
        employeeRepository.save(employee);
    }
    public void addDeparmentSertvice(Department department){
        departmentRepository.save(department);
    }
    public List<Employee> getAllEmployeeService(){
        return employeeRepository.findAll();
    }
    public ResponseEntity<ResponseEntityDTO> getEmployeeService(int employeeId){
        if(employeeRepository.findById(employeeId).isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseEntityDTO(HttpStatus.NOT_FOUND,"Employee Not Found",null));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseEntityDTO(HttpStatus.OK,"Employee retrived successfully",employeeRepository.findById(employeeId)));
    }
    public List<Department> getAllDepartmentService(){
        return departmentRepository.findAll();
    }

    public  ResponseEntity<ResponseEntityDTO> getDepartmentService(int departmentId){
        if(departmentRepository.findById(departmentId).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseEntityDTO(HttpStatus.NOT_FOUND,"Department Not Found",null));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseEntityDTO(HttpStatus.OK,"Department Retrived Successfully",departmentRepository.findById(departmentId)));
    }
    public ResponseEntity<ResponseEntityDTO> getDeptEmployeeService(int departmentId){
        if(departmentRepository.findById(departmentId).isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseEntityDTO(HttpStatus.NOT_FOUND,"Department Not Found",null));
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseEntityDTO(HttpStatus.OK,"Employee Retrived Successfully",employeeRepository.findByDepartment(departmentRepository.findById(departmentId).get())));
    }
    public void deleteEmployeeService(int employeeId){
        employeeRepository.deleteById(employeeId);
    }
    public void updateEmployeeService(int employeeId,Employee employee){
        Employee e=employeeRepository.findById(employeeId).get();
        employee.setDepartment(e.getDepartment());
        employeeRepository.save(employee);
    }
    @Transactional
    public void deleteDepartmentService(int departmentId){
        Department d=departmentRepository.findById(departmentId).get();
        employeeRepository.updateEmpDept(d);
        departmentRepository.deleteById(departmentId);
    }
}
