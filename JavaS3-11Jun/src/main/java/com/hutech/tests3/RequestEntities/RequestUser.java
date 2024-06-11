package com.hutech.tests3.RequestEntities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private String birthDate;
}
