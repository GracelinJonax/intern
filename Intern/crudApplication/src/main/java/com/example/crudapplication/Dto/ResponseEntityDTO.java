package com.example.crudapplication.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseEntityDTO {
    HttpStatus httpStatus;
    String Message;
    Object object;
}
