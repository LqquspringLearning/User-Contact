package com.thoughtworks.grad.step.Controllers;

import com.thoughtworks.grad.step.Beans.Contact;
import com.thoughtworks.grad.step.Beans.User;
import com.thoughtworks.grad.step.Repository.UserRepository;
import com.thoughtworks.grad.step.Repository.UserRepositoryImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserRepository repository = new UserRepositoryImpl();

    @PostMapping("/{id}")
    public ResponseEntity createContact(@PathVariable int id, @RequestBody Contact contact) {
        if (id <= 0 || contact == null) {
            return badRequest("");
        }
        User dbUser = repository.getById(id);
        if (dbUser == null) {
            return notFound();
        }
        dbUser.setContacts(contact);
        User realUser = repository.createContact(dbUser);
        return new ResponseEntity<>(realUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity updateUserContact(@PathVariable int id, @RequestBody Contact contact) {
        if (id <= 0 || contact == null) {
            return badRequest("");
        }
        User dbUser = repository.getById(id);
        if (dbUser == null) {
            return notFound();
        }
        if (!dbUser.getContactsMap().containsKey(id)) {
            dbUser.setContacts(contact);
            return new ResponseEntity<>(repository.updateContact(id, dbUser), HttpStatus.CREATED);
        }
        dbUser.setContacts(contact);
        repository.createContact(dbUser);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}/contacts")
    public ResponseEntity getAllContacts(@PathVariable int id) {
        if (id <= 0) {
            return badRequest("");
        }
        User dbUser = repository.getById(id);
        if (dbUser == null) {
            return notFound();
        }
        return new ResponseEntity<>(dbUser.getContacts(), HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/contact/{contactId}")
    public ResponseEntity deleteContact(@PathVariable int userId, @PathVariable int contactId) {
        if (userId <= 0 || contactId <= 0) {
            return badRequest("");
        }
        User dbUser = repository.getById(userId);
        if (dbUser == null) {
            return notFound();
        }
        repository.deleteContact(contactId, dbUser);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private ResponseEntity notFound() {
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    private ResponseEntity badRequest(String msg) {
        if (msg == null || msg.trim().isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(msg, HttpStatus.BAD_REQUEST);
    }
}
