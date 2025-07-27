package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    EmployeeRepo employeeRepo;
    @PostMapping("/post")
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeRepo.save(employee);
    }
    @GetMapping("/get")
    public List<Employee> getEmployee(){
       return employeeRepo.findAll() ;
    }
    @GetMapping("/emp/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable long id){
        Optional<Employee> employee=employeeRepo.findById(id);
        if(employee.isPresent()){
            return new ResponseEntity<>(employee.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PutMapping("/put/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee emp){
        Optional<Employee> employee=employeeRepo.findById(id);
        if(employee.isPresent()){

            return new ResponseEntity<>(employeeRepo.save(employee.get()),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
