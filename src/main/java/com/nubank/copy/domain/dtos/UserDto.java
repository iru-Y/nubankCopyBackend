package com.nubank.copy.domain.dtos;

import com.nubank.copy.domain.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String email;
    private String accountBalance;

    public UserDto (User user){
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        accountBalance = user.getAccountBalance();
    }
}
