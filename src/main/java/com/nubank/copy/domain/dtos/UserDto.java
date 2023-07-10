package com.nubank.copy.domain.dtos;

import com.nubank.copy.domain.models.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private String name;
    private String email;
    private Double accountBalance;

    public UserDto (User user){
        name = user.getName();
        email = user.getEmail();
        accountBalance = user.getAccountBalance();
    }
}
