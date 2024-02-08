package com.example.demo.controller;

import com.example.demo.Service.impl.UserServiceImpl;
import com.example.demo.entity.CoResponse;
import com.example.demo.entity.User;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {


    @Autowired
    private UserServiceImpl userService;

    @PostMapping(value = "/createPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<CoResponse<byte[]>> createPdf() throws Exception {
        ByteArrayOutputStream outputStream = userService.createPdf();
        CoResponse<byte[]> coResponse = new CoResponse<>();
        coResponse.setStatus(HttpStatus.OK);
        coResponse.setData(outputStream.toByteArray());
        return ResponseEntity.ok(coResponse);
    }

    @PostMapping(value = "/createSelfDeclarationPDF", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> createSelfDeclaration() throws Exception {
        ByteArrayOutputStream outputStream = userService.createSelfDeclaration();

        return ResponseEntity.ok(outputStream.toByteArray());
    }

    @PostMapping("/")
    public ResponseEntity<User> saveUser(@RequestBody User user) throws Exception {
        User user1 = userService.saveUser(user);

        return ResponseEntity.ok(user1);
    }

    @PostMapping(value = "/setImage", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> setImage() throws Exception {
        ByteArrayOutputStream outputStream = userService.setImage();

        return ResponseEntity.ok(outputStream.toByteArray());
    }

    @PostMapping("/getResponse")
    public ResponseEntity<CoResponse<String>> getResponse(){

        CoResponse<String> coResponse = new CoResponse<>();
        String var = "Hello";
        coResponse.setData(var);
        coResponse.setStatus(HttpStatus.OK);
        return ResponseEntity.ok(coResponse);
    }
}
