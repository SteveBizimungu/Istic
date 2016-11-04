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
 * A Entreprise.
 */
@Entity
@Table(name = "entreprise")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Entreprise implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ent_libele")
    private String entLibele;

    @Column(name = "ent_ville")
    private String entVille;

    @Column(name = "ent_rue")
    private String entRue;

    @Column(name = "ent_code_dep")
    private Integer entCodeDep;

    @OneToMany(mappedBy = "entreprise")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Stage> stages = new HashSet<>();

    @OneToMany(mappedBy = "entprise")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Contact> contacts = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntLibele() {
        return entLibele;
    }

    public Entreprise entLibele(String entLibele) {
        this.entLibele = entLibele;
        return this;
    }

    public void setEntLibele(String entLibele) {
        this.entLibele = entLibele;
    }

    public String getEntVille() {
        return entVille;
    }

    public Entreprise entVille(String entVille) {
        this.entVille = entVille;
        return this;
    }

    public void setEntVille(String entVille) {
        this.entVille = entVille;
    }

    public String getEntRue() {
        return entRue;
    }

    public Entreprise entRue(String entRue) {
        this.entRue = entRue;
        return this;
    }

    public void setEntRue(String entRue) {
        this.entRue = entRue;
    }

    public Integer getEntCodeDep() {
        return entCodeDep;
    }

    public Entreprise entCodeDep(Integer entCodeDep) {
        this.entCodeDep = entCodeDep;
        return this;
    }

    public void setEntCodeDep(Integer entCodeDep) {
        this.entCodeDep = entCodeDep;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public Entreprise stages(Set<Stage> stages) {
        this.stages = stages;
        return this;
    }

    public Entreprise addStage(Stage stage) {
        stages.add(stage);
        stage.setEntreprise(this);
        return this;
    }

    public Entreprise removeStage(Stage stage) {
        stages.remove(stage);
        stage.setEntreprise(null);
        return this;
    }

    public void setStages(Set<Stage> stages) {
        this.stages = stages;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public Entreprise contacts(Set<Contact> contacts) {
        this.contacts = contacts;
        return this;
    }

    public Entreprise addContact(Contact contact) {
        contacts.add(contact);
        contact.setEntprise(this);
        return this;
    }

    public Entreprise removeContact(Contact contact) {
        contacts.remove(contact);
        contact.setEntprise(null);
        return this;
    }

    public void setContacts(Set<Contact> contacts) {
        this.contacts = contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entreprise entreprise = (Entreprise) o;
        if(entreprise.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, entreprise.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Entreprise{" +
            "id=" + id +
            ", entLibele='" + entLibele + "'" +
            ", entVille='" + entVille + "'" +
            ", entRue='" + entRue + "'" +
            ", entCodeDep='" + entCodeDep + "'" +
            '}';
    }
}
