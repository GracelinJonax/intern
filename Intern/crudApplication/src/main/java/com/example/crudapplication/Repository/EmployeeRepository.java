package com.example.crudapplication.Repository;

import com.example.crudapplication.model.Department;
import com.example.crudapplication.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    @Query("SELECT Employee from Employee e where e.department=?1")
    List<Employee> findByDepartment(Department department);
    @Modifying
    @Query("UPDATE Employee e set e.department=null where e.department=?1")
    void updateEmpDept(Department department);

//    List<Employee> findByDepartment(Department department);
}



