package com.projet.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Etudiant.
 */
@Entity
@Table(name = "etudiant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Etudiant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "etu_name")
    private String etuName;

    @Column(name = "etu_prenom")
    private String etuPrenom;

    @Column(name = "etu_ville")
    private String etuVille;

    @Column(name = "etu_rue")
    private String etuRue;

    @Column(name = "etu_code_dep")
    private Integer etuCodeDep;

    @Column(name = "etu_premiere_inscription")
    private LocalDate etuPremiereInscription;

    @Column(name = "etu_annee_courante")
    private LocalDate etuAnneeCourante;

    @Column(name = "etu_mail")
    private String etuMail;

    @OneToMany(mappedBy = "etudiant")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Stage> stages = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEtuName() {
        return etuName;
    }

    public Etudiant etuName(String etuName) {
        this.etuName = etuName;
        return this;
    }

    public void setEtuName(String etuName) {
        this.etuName = etuName;
    }

    public String getEtuPrenom() {
        return etuPrenom;
    }

    public Etudiant etuPrenom(String etuPrenom) {
        this.etuPrenom = etuPrenom;
        return this;
    }

    public void setEtuPrenom(String etuPrenom) {
        this.etuPrenom = etuPrenom;
    }

    public String getEtuVille() {
        return etuVille;
    }

    public Etudiant etuVille(String etuVille) {
        this.etuVille = etuVille;
        return this;
    }

    public void setEtuVille(String etuVille) {
        this.etuVille = etuVille;
    }

    public String getEtuRue() {
        return etuRue;
    }

    public Etudiant etuRue(String etuRue) {
        this.etuRue = etuRue;
        return this;
    }

    public void setEtuRue(String etuRue) {
        this.etuRue = etuRue;
    }

    public Integer getEtuCodeDep() {
        return etuCodeDep;
    }

    public Etudiant etuCodeDep(Integer etuCodeDep) {
        this.etuCodeDep = etuCodeDep;
        return this;
    }

    public void setEtuCodeDep(Integer etuCodeDep) {
        this.etuCodeDep = etuCodeDep;
    }

    public LocalDate getEtuPremiereInscription() {
        return etuPremiereInscription;
    }

    public Etudiant etuPremiereInscription(LocalDate etuPremiereInscription) {
        this.etuPremiereInscription = etuPremiereInscription;
        return this;
    }

    public void setEtuPremiereInscription(LocalDate etuPremiereInscription) {
        this.etuPremiereInscription = etuPremiereInscription;
    }

    public LocalDate getEtuAnneeCourante() {
        return etuAnneeCourante;
    }

    public Etudiant etuAnneeCourante(LocalDate etuAnneeCourante) {
        this.etuAnneeCourante = etuAnneeCourante;
        return this;
    }

    public void setEtuAnneeCourante(LocalDate etuAnneeCourante) {
        this.etuAnneeCourante = etuAnneeCourante;
    }

    public String getEtuMail() {
        return etuMail;
    }

    public Etudiant etuMail(String etuMail) {
        this.etuMail = etuMail;
        return this;
    }

    public void setEtuMail(String etuMail) {
        this.etuMail = etuMail;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public Etudiant stages(Set<Stage> stages) {
        this.stages = stages;
        return this;
    }

    public Etudiant addStage(Stage stage) {
        stages.add(stage);
        stage.setEtudiant(this);
        return this;
    }

    public Etudiant removeStage(Stage stage) {
        stages.remove(stage);
        stage.setEtudiant(null);
        return this;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Etudiant etudiant = (Etudiant) o;
        if(etudiant.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, etudiant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Etudiant{" +
            "id=" + id +
            ", etuName='" + etuName + "'" +
            ", etuPrenom='" + etuPrenom + "'" +
            ", etuVille='" + etuVille + "'" +
            ", etuRue='" + etuRue + "'" +
            ", etuCodeDep='" + etuCodeDep + "'" +
            ", etuPremiereInscription='" + etuPremiereInscription + "'" +
            ", etuAnneeCourante='" + etuAnneeCourante + "'" +
            ", etuMail='" + etuMail + "'" +
            '}';
    }
}
