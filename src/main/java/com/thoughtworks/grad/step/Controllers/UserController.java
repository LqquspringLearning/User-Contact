package com.thoughtworks.grad.step.Controllers;

import com.thoughtworks.grad.step.Beans.Contact;
import com.thoughtworks.grad.step.Beans.User;
import com.thoughtworks.grad.step.Repository.UserRepository;
import com.thoughtworks.grad.step.Repository.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    UserRepository repository = new UserRepositoryImpl();

    @PostMapping("/user/{id}")
    public ResponseEntity createContact(@PathVariable int id, @RequestBody Contact contact) {
        if (id <= 0 || contact == null) {
            return BadRequest("");
        }
        User dbUser = repository.getById(id);
        if (dbUser == null) {
            return BadRequest("user not exists");
        }
        dbUser.setContacts(contact);
        User realUser = repository.updateOrCreateContact(dbUser);
        return new ResponseEntity<>(realUser, HttpStatus.CREATED);
    }

    private ResponseEntity BadRequest(String msg) {
        if (msg == null || msg.trim().isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
    }
}
