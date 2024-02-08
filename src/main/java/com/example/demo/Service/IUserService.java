package com.example.demo.Service;

import com.example.demo.entity.User;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.output.ByteArrayOutputStream;

import java.io.IOException;
import java.sql.SQLException;

public interface IUserService {
    public ByteArrayOutputStream createSelfDeclaration() throws DocumentException, IOException;
    public ByteArrayOutputStream createPdf() throws Exception;
    public User saveUser(User user);
    public ByteArrayOutputStream setImage() throws Exception;
}
