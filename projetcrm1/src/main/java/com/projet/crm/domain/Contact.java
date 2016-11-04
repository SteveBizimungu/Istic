package com.projet.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Contact.
 */
@Entity
@Table(name = "contact")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cont_nom")
    private String contNom;

    @Column(name = "cont_prenom")
    private String contPrenom;

    @Column(name = "cont_mail")
    private String contMail;

    @OneToMany(mappedBy = "contact")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Stage> stages = new HashSet<>();

    @ManyToOne
    private Entreprise entprise;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContNom() {
        return contNom;
    }

    public Contact contNom(String contNom) {
        this.contNom = contNom;
        return this;
    }

    public void setContNom(String contNom) {
        this.contNom = contNom;
    }

    public String getContPrenom() {
        return contPrenom;
    }

    public Contact contPrenom(String contPrenom) {
        this.contPrenom = contPrenom;
        return this;
    }

    public void setContPrenom(String contPrenom) {
        this.contPrenom = contPrenom;
    }

    public String getContMail() {
        return contMail;
    }

    public Contact contMail(String contMail) {
        this.contMail = contMail;
        return this;
    }

    public void setContMail(String contMail) {
        this.contMail = contMail;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public Contact stages(Set<Stage> stages) {
        this.stages = stages;
        return this;
    }

    public Contact addStage(Stage stage) {
        stages.add(stage);
        stage.setContact(this);
        return this;
    }

    public Contact removeStage(Stage stage) {
        stages.remove(stage);
        stage.setContact(null);
        return this;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
    }

    public Entreprise getEntprise() {
        return entprise;
    }

    public Contact entprise(Entreprise entreprise) {
        this.entprise = entreprise;
        return this;
    }

    public void setEntprise(Entreprise entreprise) {
        this.entprise = entreprise;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Contact contact = (Contact) o;
        if(contact.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + id +
            ", contNom='" + contNom + "'" +
            ", contPrenom='" + contPrenom + "'" +
            ", contMail='" + contMail + "'" +
            '}';
    }
}
