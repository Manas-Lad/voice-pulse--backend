package com.semicolons.controller;

import com.semicolons.entity.Contact;
import com.semicolons.repository.ContactRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@CrossOrigin(origins = "*")
public class ContactController {

    private final ContactRepository contactRepository;

    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping
    public List<Contact> getAll() {
        return contactRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Contact> create(@RequestBody Contact contact) {
        return new ResponseEntity<>(contactRepository.save(contact), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> update(@PathVariable Long id, @RequestBody Contact updated) {
        return contactRepository.findById(id).map(contact -> {
            contact.setName(updated.getName());
            contact.setPhone(updated.getPhone());
            contact.setEmergencyAlerts(updated.isEmergencyAlerts());
            contact.setLocationSharing(updated.isLocationSharing());
            return ResponseEntity.ok(contactRepository.save(contact));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}