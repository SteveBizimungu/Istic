package com.projet.crm.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A PeriodeStage.
 */
@Entity
@Table(name = "periode_stage")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PeriodeStage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "per_debut")
    private ZonedDateTime perDebut;

    @Column(name = "per_fin")
    private ZonedDateTime perFin;

    @OneToMany(mappedBy = "periode")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Stage> stages = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getPerDebut() {
        return perDebut;
    }

    public PeriodeStage perDebut(ZonedDateTime perDebut) {
        this.perDebut = perDebut;
        return this;
    }

    public void setPerDebut(ZonedDateTime perDebut) {
        this.perDebut = perDebut;
    }

    public ZonedDateTime getPerFin() {
        return perFin;
    }

    public PeriodeStage perFin(ZonedDateTime perFin) {
        this.perFin = perFin;
        return this;
    }

    public void setPerFin(ZonedDateTime perFin) {
        this.perFin = perFin;
    }

    public Set<Stage> getStages() {
        return stages;
    }

    public PeriodeStage stages(Set<Stage> stages) {
        this.stages = stages;
        return this;
    }

    public PeriodeStage addStage(Stage stage) {
        stages.add(stage);
        stage.setPeriode(this);
        return this;
    }

    public PeriodeStage removeStage(Stage stage) {
        stages.remove(stage);
        stage.setPeriode(null);
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
        PeriodeStage periodeStage = (PeriodeStage) o;
        if(periodeStage.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, periodeStage.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PeriodeStage{" +
            "id=" + id +
            ", perDebut='" + perDebut + "'" +
            ", perFin='" + perFin + "'" +
            '}';
    }
}
