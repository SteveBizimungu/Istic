package com.projet.crm.service.mapper;

import com.projet.crm.domain.*;
import com.projet.crm.service.dto.ContactDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Contact and its DTO ContactDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface ContactMapper {

    @Mapping(source = "entprise.id", target = "entpriseId")
    ContactDTO contactToContactDTO(Contact contact);

    List<ContactDTO> contactsToContactDTOs(List<Contact> contacts);

    @Mapping(target = "stages", ignore = true)
    @Mapping(source = "entpriseId", target = "entprise")
    Contact contactDTOToContact(ContactDTO contactDTO);

    List<Contact> contactDTOsToContacts(List<ContactDTO> contactDTOs);

    default Entreprise entrepriseFromId(Long id) {
        if (id == null) {
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);
        return entreprise;
    }
}
