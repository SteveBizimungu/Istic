package com.projet.crm.service.mapper;

import com.projet.crm.domain.Contact;
import com.projet.crm.domain.Entreprise;
import com.projet.crm.service.dto.ContactDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_102 (Oracle Corporation)"
)
@Component
public class ContactMapperImpl implements ContactMapper {

    @Override
    public ContactDTO contactToContactDTO(Contact contact) {
        if ( contact == null ) {
            return null;
        }

        ContactDTO contactDTO = new ContactDTO();

        contactDTO.setEntpriseId( contactEntpriseId( contact ) );
        contactDTO.setId( contact.getId() );
        contactDTO.setContNom( contact.getContNom() );
        contactDTO.setContPrenom( contact.getContPrenom() );
        contactDTO.setContMail( contact.getContMail() );

        return contactDTO;
    }

    @Override
    public List<ContactDTO> contactsToContactDTOs(List<Contact> contacts) {
        if ( contacts == null ) {
            return null;
        }

        List<ContactDTO> list = new ArrayList<ContactDTO>();
        for ( Contact contact : contacts ) {
            list.add( contactToContactDTO( contact ) );
        }

        return list;
    }

    @Override
    public Contact contactDTOToContact(ContactDTO contactDTO) {
        if ( contactDTO == null ) {
            return null;
        }

        Contact contact = new Contact();

        contact.setEntprise( entrepriseFromId( contactDTO.getEntpriseId() ) );
        contact.setId( contactDTO.getId() );
        contact.setContNom( contactDTO.getContNom() );
        contact.setContPrenom( contactDTO.getContPrenom() );
        contact.setContMail( contactDTO.getContMail() );

        return contact;
    }

    @Override
    public List<Contact> contactDTOsToContacts(List<ContactDTO> contactDTOs) {
        if ( contactDTOs == null ) {
            return null;
        }

        List<Contact> list = new ArrayList<Contact>();
        for ( ContactDTO contactDTO : contactDTOs ) {
            list.add( contactDTOToContact( contactDTO ) );
        }

        return list;
    }

    private Long contactEntpriseId(Contact contact) {

        if ( contact == null ) {
            return null;
        }
        Entreprise entprise = contact.getEntprise();
        if ( entprise == null ) {
            return null;
        }
        Long id = entprise.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
