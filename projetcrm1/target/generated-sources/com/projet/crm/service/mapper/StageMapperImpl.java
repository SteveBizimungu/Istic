package com.projet.crm.service.mapper;

import com.projet.crm.domain.Contact;
import com.projet.crm.domain.Enseignant;
import com.projet.crm.domain.Entreprise;
import com.projet.crm.domain.Etudiant;
import com.projet.crm.domain.PeriodeStage;
import com.projet.crm.domain.Stage;
import com.projet.crm.service.dto.StageDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_102 (Oracle Corporation)"
)
@Component
public class StageMapperImpl implements StageMapper {

    @Override
    public StageDTO stageToStageDTO(Stage stage) {
        if ( stage == null ) {
            return null;
        }

        StageDTO stageDTO = new StageDTO();

        stageDTO.setEnseignantId( stageEnseignantId( stage ) );
        stageDTO.setPeriodeId( stagePeriodeId( stage ) );
        stageDTO.setEtudiantId( stageEtudiantId( stage ) );
        stageDTO.setEntrepriseId( stageEntrepriseId( stage ) );
        stageDTO.setContactId( stageContactId( stage ) );
        stageDTO.setId( stage.getId() );
        stageDTO.setStageAnnee( stage.getStageAnnee() );
        stageDTO.setStageSujet( stage.getStageSujet() );

        return stageDTO;
    }

    @Override
    public List<StageDTO> stagesToStageDTOs(List<Stage> stages) {
        if ( stages == null ) {
            return null;
        }

        List<StageDTO> list = new ArrayList<StageDTO>();
        for ( Stage stage : stages ) {
            list.add( stageToStageDTO( stage ) );
        }

        return list;
    }

    @Override
    public Stage stageDTOToStage(StageDTO stageDTO) {
        if ( stageDTO == null ) {
            return null;
        }

        Stage stage = new Stage();

        stage.setEntreprise( entrepriseFromId( stageDTO.getEntrepriseId() ) );
        stage.setEnseignant( enseignantFromId( stageDTO.getEnseignantId() ) );
        stage.setEtudiant( etudiantFromId( stageDTO.getEtudiantId() ) );
        stage.setContact( contactFromId( stageDTO.getContactId() ) );
        stage.setPeriode( periodeStageFromId( stageDTO.getPeriodeId() ) );
        stage.setId( stageDTO.getId() );
        stage.setStageAnnee( stageDTO.getStageAnnee() );
        stage.setStageSujet( stageDTO.getStageSujet() );

        return stage;
    }

    @Override
    public List<Stage> stageDTOsToStages(List<StageDTO> stageDTOs) {
        if ( stageDTOs == null ) {
            return null;
        }

        List<Stage> list = new ArrayList<Stage>();
        for ( StageDTO stageDTO : stageDTOs ) {
            list.add( stageDTOToStage( stageDTO ) );
        }

        return list;
    }

    private Long stageEnseignantId(Stage stage) {

        if ( stage == null ) {
            return null;
        }
        Enseignant enseignant = stage.getEnseignant();
        if ( enseignant == null ) {
            return null;
        }
        Long id = enseignant.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long stagePeriodeId(Stage stage) {

        if ( stage == null ) {
            return null;
        }
        PeriodeStage periode = stage.getPeriode();
        if ( periode == null ) {
            return null;
        }
        Long id = periode.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long stageEtudiantId(Stage stage) {

        if ( stage == null ) {
            return null;
        }
        Etudiant etudiant = stage.getEtudiant();
        if ( etudiant == null ) {
            return null;
        }
        Long id = etudiant.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long stageEntrepriseId(Stage stage) {

        if ( stage == null ) {
            return null;
        }
        Entreprise entreprise = stage.getEntreprise();
        if ( entreprise == null ) {
            return null;
        }
        Long id = entreprise.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long stageContactId(Stage stage) {

        if ( stage == null ) {
            return null;
        }
        Contact contact = stage.getContact();
        if ( contact == null ) {
            return null;
        }
        Long id = contact.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
