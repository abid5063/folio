package com.folio.services;

import com.folio.dtos.ContactDTO;
import com.folio.dtos.ContactFilterDTO;
import com.folio.models.Contact;
import com.folio.repositories.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    // -------------------- ADD a new contact --------------------
    public Contact addContact(ContactDTO dto) {
        Contact contact = new Contact();

        contact.setName(dto.getName());
        contact.setInstitution(dto.getInstitution());
        contact.setDesignation(dto.getDesignation());
        contact.setPhone(dto.getPhone());
        contact.setPhone2(dto.getPhone2());
        contact.setEmail(dto.getEmail());
        contact.setWhatsapp(dto.getWhatsapp());
        contact.setIsCurrent(dto.getIsCurrent() != null ? dto.getIsCurrent() : true);
        contact.setRelevantDept(dto.getRelevantDept());
        contact.setExtraInfo(dto.getExtraInfo());

        return contactRepository.save(contact);
    }

    // -------------------- UPDATE existing contact --------------------
    public Contact updateContact(Long contactId, ContactDTO dto) {

        Optional<Contact> optionalContact = contactRepository.findById(contactId);
        if (optionalContact.isEmpty()) {
            return null; // not found
        }

        Contact contact = optionalContact.get();

        // Update only non-null fields
        if (dto.getName() != null) contact.setName(dto.getName());
        if (dto.getInstitution() != null) contact.setInstitution(dto.getInstitution());
        if (dto.getDesignation() != null) contact.setDesignation(dto.getDesignation());
        if (dto.getPhone() != null) contact.setPhone(dto.getPhone());
        if (dto.getPhone2() != null) contact.setPhone2(dto.getPhone2());
        if (dto.getEmail() != null) contact.setEmail(dto.getEmail());
        if (dto.getWhatsapp() != null) contact.setWhatsapp(dto.getWhatsapp());
        if (dto.getIsCurrent() != null) contact.setIsCurrent(dto.getIsCurrent());
        if (dto.getRelevantDept() != null) contact.setRelevantDept(dto.getRelevantDept());
        if (dto.getExtraInfo() != null) contact.setExtraInfo(dto.getExtraInfo());

        return contactRepository.save(contact);
    }

    public List<Contact> getAllContacts () {
        return contactRepository.findAll();
    }

    public List<Contact> filterContacts(ContactFilterDTO filter) {
        return contactRepository.filterContacts(
                emptyToNull(filter.getName()),
                emptyToNull(filter.getInstitution()),
                emptyToNull(filter.getDesignation()),
                emptyToNull(filter.getRelevantDept())
        );
    }

    // Convert empty strings to null so filtering works properly
    private String emptyToNull(String s) {
        return (s == null || s.trim().isEmpty()) ? null : s.trim();
    }
}
