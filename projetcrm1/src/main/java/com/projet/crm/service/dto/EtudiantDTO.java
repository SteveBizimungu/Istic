package com.projet.crm.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Etudiant entity.
 */
public class EtudiantDTO implements Serializable {

    private Long id;

    private String etuName;

    private String etuPrenom;

    private String etuVille;

    private String etuRue;

    private Integer etuCodeDep;

    private LocalDate etuPremiereInscription;

    private LocalDate etuAnneeCourante;

    private String etuMail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEtuName() {
        return etuName;
    }

    public void setEtuName(String etuName) {
        this.etuName = etuName;
    }
    public String getEtuPrenom() {
        return etuPrenom;
    }

    public void setEtuPrenom(String etuPrenom) {
        this.etuPrenom = etuPrenom;
    }
    public String getEtuVille() {
        return etuVille;
    }

    public void setEtuVille(String etuVille) {
        this.etuVille = etuVille;
    }
    public String getEtuRue() {
        return etuRue;
    }

    public void setEtuRue(String etuRue) {
        this.etuRue = etuRue;
    }
    public Integer getEtuCodeDep() {
        return etuCodeDep;
    }

    public void setEtuCodeDep(Integer etuCodeDep) {
        this.etuCodeDep = etuCodeDep;
    }
    public LocalDate getEtuPremiereInscription() {
        return etuPremiereInscription;
    }

    public void setEtuPremiereInscription(LocalDate etuPremiereInscription) {
        this.etuPremiereInscription = etuPremiereInscription;
    }
    public LocalDate getEtuAnneeCourante() {
        return etuAnneeCourante;
    }

    public void setEtuAnneeCourante(LocalDate etuAnneeCourante) {
        this.etuAnneeCourante = etuAnneeCourante;
    }
    public String getEtuMail() {
        return etuMail;
    }

    public void setEtuMail(String etuMail) {
        this.etuMail = etuMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EtudiantDTO etudiantDTO = (EtudiantDTO) o;

        if ( ! Objects.equals(id, etudiantDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EtudiantDTO{" +
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
