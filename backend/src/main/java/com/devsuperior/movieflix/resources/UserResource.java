package com.devsuperior.movieflix.resources;

import com.devsuperior.movieflix.services.UserService;
import com.devsuperior.movieflix.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/profile")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<UserDTO> getProfile(){
        UserDTO dto = service.findUser();
        return ResponseEntity.ok(dto);
    }
}
