package com.folio.controllers;

import com.folio.dtos.ContactDTO;
import com.folio.dtos.ContactFilterDTO;
import com.folio.models.Contact;
import com.folio.services.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/create")
    public boolean createContact(@RequestBody ContactDTO contactDTO) {
        Contact contact = contactService.addContact(contactDTO);
        return contact != null;
    }

    @PostMapping("/update/{contactId}")
    public boolean updateContact(@RequestBody ContactDTO contactDTO, @PathVariable Long contactId) {
        Contact contact = contactService.updateContact(contactId, contactDTO);
        return contact != null;
    }

    @GetMapping("/all")
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }

    @PostMapping("/filter")
    public List<Contact> getFilteredContacts(@RequestBody ContactFilterDTO contactFilterDTO) {
        return contactService.filterContacts(contactFilterDTO);
    }
}
