package com.projet.crm.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A Stage.
 */
@Entity
@Table(name = "stage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Stage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "stage_annee")
    private LocalDate stageAnnee;

    @Column(name = "stage_sujet")
    private String stageSujet;

    @ManyToOne
    private Etudiant etudiant;

    @ManyToOne
    private PeriodeStage periode;

    @ManyToOne
    private Enseignant enseignant;

    @ManyToOne
    private Entreprise entreprise;

    @ManyToOne
    private Contact contact;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStageAnnee() {
        return stageAnnee;
    }

    public Stage stageAnnee(LocalDate stageAnnee) {
        this.stageAnnee = stageAnnee;
        return this;
    }

    public void setStageAnnee(LocalDate stageAnnee) {
        this.stageAnnee = stageAnnee;
    }

    public String getStageSujet() {
        return stageSujet;
    }

    public Stage stageSujet(String stageSujet) {
        this.stageSujet = stageSujet;
        return this;
    }

    public void setStageSujet(String stageSujet) {
        this.stageSujet = stageSujet;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Stage etudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
        return this;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public PeriodeStage getPeriode() {
        return periode;
    }

    public Stage periode(PeriodeStage periodeStage) {
        this.periode = periodeStage;
        return this;
    }

    public void setPeriode(PeriodeStage periodeStage) {
        this.periode = periodeStage;
    }

    public Enseignant getEnseignant() {
        return enseignant;
    }

    public Stage enseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
        return this;
    }

    public void setEnseignant(Enseignant enseignant) {
        this.enseignant = enseignant;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public Stage entreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
        return this;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Contact getContact() {
        return contact;
    }

    public Stage contact(Contact contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stage stage = (Stage) o;
        if(stage.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, stage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Stage{" +
            "id=" + id +
            ", stageAnnee='" + stageAnnee + "'" +
            ", stageSujet='" + stageSujet + "'" +
            '}';
    }
}
