package com.projet.crm.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Entreprise entity.
 */
public class EntrepriseDTO implements Serializable {

    private Long id;

    private String entLibele;

    private String entVille;

    private String entRue;

    private Integer entCodeDep;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEntLibele() {
        return entLibele;
    }

    public void setEntLibele(String entLibele) {
        this.entLibele = entLibele;
    }
    public String getEntVille() {
        return entVille;
    }

    public void setEntVille(String entVille) {
        this.entVille = entVille;
    }
    public String getEntRue() {
        return entRue;
    }

    public void setEntRue(String entRue) {
        this.entRue = entRue;
    }
    public Integer getEntCodeDep() {
        return entCodeDep;
    }

    public void setEntCodeDep(Integer entCodeDep) {
        this.entCodeDep = entCodeDep;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EntrepriseDTO entrepriseDTO = (EntrepriseDTO) o;

        if ( ! Objects.equals(id, entrepriseDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "EntrepriseDTO{" +
            "id=" + id +
            ", entLibele='" + entLibele + "'" +
            ", entVille='" + entVille + "'" +
            ", entRue='" + entRue + "'" +
            ", entCodeDep='" + entCodeDep + "'" +
            '}';
    }
}
