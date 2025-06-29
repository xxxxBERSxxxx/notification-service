
package com.example.notificationservice.dto;

import lombok.Data;

@Data
public class EmailRequest {
    private String email;
    private String type; // "create" или "delete"
}