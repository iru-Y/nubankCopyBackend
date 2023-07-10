package com.nubank.copy.controllers;

import com.nubank.copy.domain.dtos.UserDto;
import com.nubank.copy.domain.models.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nubank.copy.service.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    private List<UserDto> findAll(){
        List<User> user = userService.findAll();
        return user.stream().map(UserDto::new).toList();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    private UserDto findById (@PathVariable Long id){
        User user = userService.findById(id);
        return new UserDto(user);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    private UserDto post(@RequestBody User user){
        User createdUser = userService.create(user);
       return new UserDto(createdUser);
    }

    @PutMapping("/{id}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    private UserDto put(@PathVariable Long id, @RequestBody User user) {
        User existingUser = userService.findById(id);

        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setEmail(user.getEmail());
        existingUser.setAccountBalance(user.getAccountBalance());

        User updatedUser = userService.update(existingUser);

        UserDto userDto = new UserDto();
        userDto.setName(updatedUser.getName());
        userDto.setEmail(updatedUser.getEmail());
        userDto.setAccountBalance(updatedUser.getAccountBalance());

        return userDto;
    }

    @DeleteMapping
    @ResponseBody
    public void delete (@PathVariable Long id){
        userService.deleteById(id);
    }
}
