package com.example.demo.controller;

import com.example.demo.Service.StudentService;
import com.example.demo.Service.impl.UserServiceImpl;
import com.example.demo.entity.CoResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/students")
public class StudentController {

//    @Autowired
//    private StudentService studentService;
//
//    @PostMapping(value = "/", produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<String> createPdf() throws Exception {
//        Str outputStream = userService.createPdf();
//        CoResponse<byte[]> coResponse = new CoResponse<>();
//        coResponse.setStatus(HttpStatus.OK);
//        coResponse.setData(outputStream.toByteArray());
//        return ResponseEntity.ok("coResponse");
//    }

}

