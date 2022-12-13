package com.projectjava.expensessystem.controller;

import com.projectjava.expensessystem.model.User;
import com.projectjava.expensessystem.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@RequestBody User user) {
        User userSave = null;
        userSave = userService.saveUser(user);
        if (userSave == null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "email ja cadastrado");
            return ResponseEntity.badRequest().body(map);
        }
        return ResponseEntity.ok(userSave);
    }

    @GetMapping("/users")
    public List<User> getAllusers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/users/{id}")
    public ResponseEntity getUserById(@PathVariable("id") Long id) {
        User user = null;
        user = userService.getUserById(id);
        if (user == null ) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "id nao encontrado");
            return ResponseEntity.badRequest().body(map);
        }

        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String,String>> deleteEmployee(@PathVariable("id")Long id) {
        boolean deleted = false;
        deleted = userService.deleteUser(id);
        Map<String,String> response = new HashMap<>();

        if (!deleted) {
            response.put("message","Id nao encontrado");
            return ResponseEntity.badRequest().body(response);
        }
        response.put("deleted", "True");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable("id")Long id, @RequestBody User user){
        user = userService.updateUser(id, user);
        if (user == null) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("message", "id nao encontrado");
            return ResponseEntity.badRequest().body(map);
        }
        return ResponseEntity.ok(user);
    }
}
