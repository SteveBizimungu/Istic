package com.projet.crm.service.mapper;

import com.projet.crm.domain.*;
import com.projet.crm.service.dto.EtudiantDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Etudiant and its DTO EtudiantDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EtudiantMapper {

    EtudiantDTO etudiantToEtudiantDTO(Etudiant etudiant);

    List<EtudiantDTO> etudiantsToEtudiantDTOs(List<Etudiant> etudiants);

    @Mapping(target = "stages", ignore = true)
    Etudiant etudiantDTOToEtudiant(EtudiantDTO etudiantDTO);

    List<Etudiant> etudiantDTOsToEtudiants(List<EtudiantDTO> etudiantDTOs);
}
