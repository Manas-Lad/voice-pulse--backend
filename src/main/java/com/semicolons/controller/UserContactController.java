package com.semicolons.controller;

import com.semicolons.entity.Contact;
import com.semicolons.entity.ContactId;
import com.semicolons.entity.User;
import com.semicolons.repository.ContactRepository;
import com.semicolons.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/device/{deviceUuid}/contacts")
@CrossOrigin(origins = "*")
public class UserContactController {

    private final ContactRepository contactRepository;
    private final UserRepository userRepository;

    public UserContactController(ContactRepository contactRepository, UserRepository userRepository) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<List<Contact>> getContacts(@PathVariable UUID deviceUuid) {
        return ResponseEntity.ok(contactRepository.findAllById_UserId(deviceUuid));
    }

    @PostMapping
    public ResponseEntity<Contact> createContact(
            @PathVariable UUID deviceUuid,
            @RequestBody Contact request) {
        if (request.getName() == null || request.getName().isBlank()
                || request.getPhone() == null || request.getPhone().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        User user = userRepository.findById(deviceUuid).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();

        ContactId id = new ContactId(deviceUuid, request.getPhone());
        if (contactRepository.existsById(id)) return ResponseEntity.status(HttpStatus.CONFLICT).build();

        Contact contact = new Contact(request.getName(), request.getPhone(),
                request.isEmergencyAlerts(), request.isLocationSharing());
        contact.setUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(contactRepository.save(contact));
    }

    @PutMapping("/{phone}")
    @Transactional
    public ResponseEntity<Contact> updateContact(
            @PathVariable UUID deviceUuid,
            @PathVariable String phone,
            @RequestBody Contact request) {
        if (request.getName() == null || request.getName().isBlank()
                || request.getPhone() == null || request.getPhone().isBlank()) {
            return ResponseEntity.badRequest().build();
        }
        ContactId currentId = new ContactId(deviceUuid, phone);
        Contact current = contactRepository.findById(currentId).orElse(null);
        if (current == null) return ResponseEntity.notFound().build();

        String updatedPhone = request.getPhone();
        ContactId updatedId = new ContactId(deviceUuid, updatedPhone);
        if (!currentId.equals(updatedId) && contactRepository.existsById(updatedId)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        current.setName(request.getName());
        current.setEmergencyAlerts(request.isEmergencyAlerts());
        current.setLocationSharing(request.isLocationSharing());

        if (currentId.equals(updatedId)) {
            return ResponseEntity.ok(contactRepository.save(current));
        }

        User owner = current.getUser();
        contactRepository.delete(current);
        Contact moved = new Contact(request.getName(), updatedPhone,
                request.isEmergencyAlerts(), request.isLocationSharing());
        moved.setUser(owner);
        return ResponseEntity.ok(contactRepository.save(moved));
    }

    @DeleteMapping("/{phone}")
    public ResponseEntity<Void> deleteContact(
            @PathVariable UUID deviceUuid,
            @PathVariable String phone) {
        ContactId id = new ContactId(deviceUuid, phone);
        if (!contactRepository.existsById(id)) return ResponseEntity.notFound().build();
        contactRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
