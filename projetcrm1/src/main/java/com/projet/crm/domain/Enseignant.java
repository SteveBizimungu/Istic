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
 * A Enseignant.
 */
@Entity
@Table(name = "enseignant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Enseignant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "ens_name")
    private String ensName;

    @Column(name = "ens_prenom")
    private String ensPrenom;

    @Column(name = "ens_ville")
    private String ensVille;

    @Column(name = "ens_rue")
    private String ensRue;

    @Column(name = "ens_code_dep")
    private Integer ensCodeDep;

    @Column(name = "ens_tel")
    private String ensTel;

    @Column(name = "ens_mail")
    private String ensMail;

    @OneToMany(mappedBy = "enseignant")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Stage> stages = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnsName() {
        return ensName;
    }

    public Enseignant ensName(String ensName) {
        this.ensName = ensName;
        return this;
    }

    public void setEnsName(String ensName) {
        this.ensName = ensName;
    }

    public String getEnsPrenom() {
        return ensPrenom;
    }

    public Enseignant ensPrenom(String ensPrenom) {
        this.ensPrenom = ensPrenom;
        return this;
    }

    public void setEnsPrenom(String ensPrenom) {
        this.ensPrenom = ensPrenom;
    }

    public String getEnsVille() {
        return ensVille;
    }

    public Enseignant ensVille(String ensVille) {
        this.ensVille = ensVille;
        return this;
    }

    public void setEnsVille(String ensVille) {
        this.ensVille = ensVille;
    }

    public String getEnsRue() {
        return ensRue;
    }

    public Enseignant ensRue(String ensRue) {
        this.ensRue = ensRue;
        return this;
    }

    public void setEnsRue(String ensRue) {
        this.ensRue = ensRue;
    }

    public Integer getEnsCodeDep() {
        return ensCodeDep;
    }

    public Enseignant ensCodeDep(Integer ensCodeDep) {
        this.ensCodeDep = ensCodeDep;
        return this;
    }

    public void setEnsCodeDep(Integer ensCodeDep) {
        this.ensCodeDep = ensCodeDep;
    }

    public String getEnsTel() {
        return ensTel;
    }

    public Enseignant ensTel(String ensTel) {
        this.ensTel = ensTel;
        return this;
    }

    public void setEnsTel(String ensTel) {
        this.ensTel = ensTel;
    }

    public String getEnsMail() {
        return ensMail;
    }

    public Enseignant ensMail(String ensMail) {
        this.ensMail = ensMail;
        return this;
    }

    public void setEnsMail(String ensMail) {
        this.ensMail = ensMail;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public Enseignant stages(Set<Stage> stages) {
        this.stages = stages;
        return this;
    }

    public Enseignant addStage(Stage stage) {
        stages.add(stage);
        stage.setEnseignant(this);
        return this;
    }

    public Enseignant removeStage(Stage stage) {
        stages.remove(stage);
        stage.setEnseignant(null);
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
        Enseignant enseignant = (Enseignant) o;
        if(enseignant.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, enseignant.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Enseignant{" +
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
