package com.nubank.copy.service;

import com.nubank.copy.domain.models.User;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.nubank.copy.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findById (Long id){
        if(id == null){
            throw new RuntimeException("Defina um usuário");
        }

        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("id não encontrado"));
    }

    public User create (User user){
        if (user == null){
            throw new RuntimeException("Você precisa preencher os campos");
        }
        return  userRepository.save(user);
    }

    public User update(User updatedUser) {
        User existingUser = userRepository.findById(updatedUser.getId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o ID: " + updatedUser.getId()));

        existingUser.setName(updatedUser.getName());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setAccountBalance(updatedUser.getAccountBalance());

        return userRepository.save(existingUser);
    }

    public void deleteById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado para o ID: " + id));

        userRepository.delete(user);
    }

}
