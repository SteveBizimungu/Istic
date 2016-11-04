package com.projet.crm.service.mapper;

import com.projet.crm.domain.*;
import com.projet.crm.service.dto.StageDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity Stage and its DTO StageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface StageMapper {

    @Mapping(source = "etudiant.id", target = "etudiantId")
    @Mapping(source = "periode.id", target = "periodeId")
    @Mapping(source = "enseignant.id", target = "enseignantId")
    @Mapping(source = "entreprise.id", target = "entrepriseId")
    @Mapping(source = "contact.id", target = "contactId")
    StageDTO stageToStageDTO(Stage stage);

    List<StageDTO> stagesToStageDTOs(List<Stage> stages);

    @Mapping(source = "etudiantId", target = "etudiant")
    @Mapping(source = "periodeId", target = "periode")
    @Mapping(source = "enseignantId", target = "enseignant")
    @Mapping(source = "entrepriseId", target = "entreprise")
    @Mapping(source = "contactId", target = "contact")
    Stage stageDTOToStage(StageDTO stageDTO);

    List<Stage> stageDTOsToStages(List<StageDTO> stageDTOs);

    default Etudiant etudiantFromId(Long id) {
        if (id == null) {
            return null;
        }
        Etudiant etudiant = new Etudiant();
        etudiant.setId(id);
        return etudiant;
    }

    default PeriodeStage periodeStageFromId(Long id) {
        if (id == null) {
            return null;
        }
        PeriodeStage periodeStage = new PeriodeStage();
        periodeStage.setId(id);
        return periodeStage;
    }

    default Enseignant enseignantFromId(Long id) {
        if (id == null) {
            return null;
        }
        Enseignant enseignant = new Enseignant();
        enseignant.setId(id);
        return enseignant;
    }

    default Entreprise entrepriseFromId(Long id) {
        if (id == null) {
            return null;
        }
        Entreprise entreprise = new Entreprise();
        entreprise.setId(id);
        return entreprise;
    }

    default Contact contactFromId(Long id) {
        if (id == null) {
            return null;
        }
        Contact contact = new Contact();
        contact.setId(id);
        return contact;
    }
}
