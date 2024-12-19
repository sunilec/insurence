package com.ies.bindings;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserAccForm {

    private String fullName;
    private String email;
    private Long mobileNo;
    private String gender;
    private LocalDate dob;
    private Long ssn;
    private String activeSw;
    private Integer roleId;



}
