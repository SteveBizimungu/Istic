package com.projet.crm.service.mapper;

import com.projet.crm.domain.*;
import com.projet.crm.service.dto.EntrepriseDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Entreprise and its DTO EntrepriseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EntrepriseMapper {

    EntrepriseDTO entrepriseToEntrepriseDTO(Entreprise entreprise);

    List<EntrepriseDTO> entreprisesToEntrepriseDTOs(List<Entreprise> entreprises);

    @Mapping(target = "stages", ignore = true)
    @Mapping(target = "contacts", ignore = true)
    Entreprise entrepriseDTOToEntreprise(EntrepriseDTO entrepriseDTO);

    List<Entreprise> entrepriseDTOsToEntreprises(List<EntrepriseDTO> entrepriseDTOs);
}
