package com.projet.crm.service.mapper;

import com.projet.crm.domain.*;
import com.projet.crm.service.dto.EnseignantDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Enseignant and its DTO EnseignantDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface EnseignantMapper {

    EnseignantDTO enseignantToEnseignantDTO(Enseignant enseignant);

    List<EnseignantDTO> enseignantsToEnseignantDTOs(List<Enseignant> enseignants);

    @Mapping(target = "stages", ignore = true)
    Enseignant enseignantDTOToEnseignant(EnseignantDTO enseignantDTO);

    List<Enseignant> enseignantDTOsToEnseignants(List<EnseignantDTO> enseignantDTOs);
}
