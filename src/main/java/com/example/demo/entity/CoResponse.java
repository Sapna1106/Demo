package com.example.demo.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class CoResponse<T>
{

    /** The status. */
    private HttpStatus status;

    /** The status code. */
    private int statusCode;

    /** The message. */
    private String message;

    /** The data. */
    private T data;

    private String fcr;

    /**
     * Sets the status.
     * Custom Setter to set both Status and Status Code
     *
     * @param status the new status
     */
    public void setStatus(HttpStatus status)
    {
        this.status = status;
        this.statusCode = status.value();
    }
}
