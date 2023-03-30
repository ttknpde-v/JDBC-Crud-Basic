package com.java.api.modules.repository;

import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public interface StudentRepository<T> {
    public ResponseEntity<T> create(T request) ;

    public HashMap<String, List<T> > readAtAll();

    public HashMap<String,T> readById(Long request);

    public ResponseEntity<T> update(T request , int id);

    public HashMap<String,T> deleteById(Long request);
}
