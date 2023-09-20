package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepository extends JpaRepository<Contact, Long> {
}
