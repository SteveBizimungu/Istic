package com.projet.crm.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the Contact entity.
 */
public class ContactDTO implements Serializable {

    private Long id;

    private String contNom;

    private String contPrenom;

    private String contMail;


    private Long entpriseId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getContNom() {
        return contNom;
    }

    public void setContNom(String contNom) {
        this.contNom = contNom;
    }
    public String getContPrenom() {
        return contPrenom;
    }

    public void setContPrenom(String contPrenom) {
        this.contPrenom = contPrenom;
    }
    public String getContMail() {
        return contMail;
    }

    public void setContMail(String contMail) {
        this.contMail = contMail;
    }

    public Long getEntpriseId() {
        return entpriseId;
    }

    public void setEntpriseId(Long entrepriseId) {
        this.entpriseId = entrepriseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ContactDTO contactDTO = (ContactDTO) o;

        if ( ! Objects.equals(id, contactDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ContactDTO{" +
            "id=" + id +
            ", contNom='" + contNom + "'" +
            ", contPrenom='" + contPrenom + "'" +
            ", contMail='" + contMail + "'" +
            '}';
    }
}
