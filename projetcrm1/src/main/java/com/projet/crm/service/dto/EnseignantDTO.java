package com.projet.crm.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Enseignant entity.
 */
public class EnseignantDTO implements Serializable {

    private Long id;

    private String ensName;

    private String ensPrenom;

    private String ensVille;

    private String ensRue;

    private Integer ensCodeDep;

    private String ensTel;

    private String ensMail;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEnsName() {
        return ensName;
    }

    public void setEnsName(String ensName) {
        this.ensName = ensName;
    }
    public String getEnsPrenom() {
        return ensPrenom;
    }

    public void setEnsPrenom(String ensPrenom) {
        this.ensPrenom = ensPrenom;
    }
    public String getEnsVille() {
        return ensVille;
    }

    public void setEnsVille(String ensVille) {
        this.ensVille = ensVille;
    }
    public String getEnsRue() {
        return ensRue;
    }

    public void setEnsRue(String ensRue) {
        this.ensRue = ensRue;
    }
    public Integer getEnsCodeDep() {
        return ensCodeDep;
    }

    public void setEnsCodeDep(Integer ensCodeDep) {
        this.ensCodeDep = ensCodeDep;
    }
    public String getEnsTel() {
        return ensTel;
    }

    public void setEnsTel(String ensTel) {
        this.ensTel = ensTel;
    }
    public String getEnsMail() {
        return ensMail;
    }

    public void setEnsMail(String ensMail) {
        this.ensMail = ensMail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnseignantDTO enseignantDTO = (EnseignantDTO) o;

        if ( ! Objects.equals(id, enseignantDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EnseignantDTO{" +
            "id=" + id +
            ", ensName='" + ensName + "'" +
            ", ensPrenom='" + ensPrenom + "'" +
            ", ensVille='" + ensVille + "'" +
            ", ensRue='" + ensRue + "'" +
            ", ensCodeDep='" + ensCodeDep + "'" +
            ", ensTel='" + ensTel + "'" +
            ", ensMail='" + ensMail + "'" +
            '}';
    }
}
