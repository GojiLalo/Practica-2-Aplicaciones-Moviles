package com.api.crud.controllers;

import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import com.api.crud.services.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ArrayList<UserModel> getUsers(){
        return this.userService.getUsers();
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }

    @GetMapping(path ="/{id}")
    public Optional<UserModel> getUserById(@PathVariable Long id){
        return this.userService.getById(id);
    }

    @PutMapping(path ="/{id}")
    public UserModel updateByUserId(@RequestBody UserModel request, @PathVariable("id") Long id ){
        return this.userService.updateByID(request, id);
    }

    @DeleteMapping(path ="/{id}")
    public String deleteByID(@PathVariable("id") Long id){
        boolean ok = this.userService.deleteUser(id);
        if(ok){
            return "Usuario" + id + "borrado";
        }
        else {
            return "Error";
        }
    }
}
