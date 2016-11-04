package com.projet.crm.service.impl;

import com.projet.crm.service.ContactService;
import com.projet.crm.domain.Contact;
import com.projet.crm.repository.ContactRepository;
import com.projet.crm.service.dto.ContactDTO;
import com.projet.crm.service.mapper.ContactMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Contact.
 */
@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    private final Logger log = LoggerFactory.getLogger(ContactServiceImpl.class);
    
    @Inject
    private ContactRepository contactRepository;

    @Inject
    private ContactMapper contactMapper;

    /**
     * Save a contact.
     *
     * @param contactDTO the entity to save
     * @return the persisted entity
     */
    public ContactDTO save(ContactDTO contactDTO) {
        log.debug("Request to save Contact : {}", contactDTO);
        Contact contact = contactMapper.contactDTOToContact(contactDTO);
        contact = contactRepository.save(contact);
        ContactDTO result = contactMapper.contactToContactDTO(contact);
        return result;
    }

    /**
     *  Get all the contacts.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<ContactDTO> findAll() {
        log.debug("Request to get all Contacts");
        List<ContactDTO> result = contactRepository.findAll().stream()
            .map(contactMapper::contactToContactDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one contact by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public ContactDTO findOne(Long id) {
        log.debug("Request to get Contact : {}", id);
        Contact contact = contactRepository.findOne(id);
        ContactDTO contactDTO = contactMapper.contactToContactDTO(contact);
        return contactDTO;
    }

    /**
     *  Delete the  contact by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Contact : {}", id);
        contactRepository.delete(id);
    }
}
