package com.semicolons.repository;

import com.semicolons.entity.Contact;
import com.semicolons.entity.ContactId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, ContactId> {
    List<Contact> findAllById_UserId(UUID userId);
}
