package com.projet.crm.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Stage entity.
 */
public class StageDTO implements Serializable {

    private Long id;

    private LocalDate stageAnnee;

    private String stageSujet;


    private Long etudiantId;
    
    private Long periodeId;
    
    private Long enseignantId;
    
    private Long entrepriseId;
    
    private Long contactId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public LocalDate getStageAnnee() {
        return stageAnnee;
    }

    public void setStageAnnee(LocalDate stageAnnee) {
        this.stageAnnee = stageAnnee;
    }
    public String getStageSujet() {
        return stageSujet;
    }

    public void setStageSujet(String stageSujet) {
        this.stageSujet = stageSujet;
    }

    public Long getEtudiantId() {
        return etudiantId;
    }

    public void setEtudiantId(Long etudiantId) {
        this.etudiantId = etudiantId;
    }

    public Long getPeriodeId() {
        return periodeId;
    }

    public void setPeriodeId(Long periodeStageId) {
        this.periodeId = periodeStageId;
    }

    public Long getEnseignantId() {
        return enseignantId;
    }

    public void setEnseignantId(Long enseignantId) {
        this.enseignantId = enseignantId;
    }

    public Long getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(Long entrepriseId) {
        this.entrepriseId = entrepriseId;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StageDTO stageDTO = (StageDTO) o;

        if ( ! Objects.equals(id, stageDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "StageDTO{" +
            "id=" + id +
            ", stageAnnee='" + stageAnnee + "'" +
            ", stageSujet='" + stageSujet + "'" +
            '}';
    }
}
