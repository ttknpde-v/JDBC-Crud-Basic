package com.java.api.modules.controller;

import com.java.api.modules.model.Student;
import com.java.api.modules.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api")
public class StudentController {

    private final StudentRepository repository;

    @Autowired
    public StudentController(StudentRepository repository) {
        /* Construct Dependency Injection */
        this.repository = repository;
    }

    @RequestMapping(value = "/create" , method = RequestMethod.POST)
    public ResponseEntity<?> getCreate(@RequestBody Student student) {
            ResponseEntity<Student> response = this.repository.create(student);
            return response;
    }

    @RequestMapping(value = "/read" , method = RequestMethod.GET)
    public HashMap<?,?> getReadAtAll() {

            HashMap hashMap = this.repository.readAtAll();
            return hashMap;
    }

    @RequestMapping(value = "/read/{idStudent}" , method = RequestMethod.GET)
    public HashMap<?,?> getReadById(@PathVariable Long idStudent) {

            HashMap<String,Student> hashMap = this.repository.readById(idStudent);
            /* System.out.println(hashMap.get("student").getFullname());
            *  we can use data of HashMap =>   get(<key>).<attribute> */
            return hashMap;
    }

    @RequestMapping(value = "/update/{idStudent}" , method = RequestMethod.PUT)
    public ResponseEntity<?> getUpdate (@PathVariable int idStudent , @RequestBody Student student) {
        HashMap<String , List<Student> > listHashMap = this.repository.readAtAll();
        /* retrieve all students */
        try {
            for(Map.Entry<String ,List< Student> > set : listHashMap.entrySet() ) {
                if (set.getValue().get(idStudent-1).getOrder() != null) {
                    /** when find is do ((Index start at 1 always Bro))*/
                        return this.repository.update(student, idStudent);
                }
                /* System.out.println(set.getValue().get(idStudent-1).getOrder()); /* result is idStudent */
                /* because set.getValue() => show all order in table student => students{1,...},{2,...},{n,...}
                 *  set.getValue().get(index) => show just order of index =>  students{index}
                 *  set.getValue().get(index).<get your attribute>() => attribute */
            } /* end loop */
        } catch (IndexOutOfBoundsException errors) {
            System.out.println(errors);
        }
        return  new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @RequestMapping(value = "/delete/{idStudent}" , method = RequestMethod.DELETE)
    public HashMap<?,?> getDelete(@PathVariable Long idStudent) {
        HashMap<String,Student> listHashMap = this.repository.deleteById(idStudent);
        if (listHashMap.get("student").getFullname() != null) {
            return listHashMap;
        }
        return listHashMap;
    }
}
