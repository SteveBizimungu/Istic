package com.projet.crm.service.dto;

import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;


/**
 * A DTO for the PeriodeStage entity.
 */
public class PeriodeStageDTO implements Serializable {

    private Long id;

    private ZonedDateTime perDebut;

    private ZonedDateTime perFin;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public ZonedDateTime getPerDebut() {
        return perDebut;
    }

    public void setPerDebut(ZonedDateTime perDebut) {
        this.perDebut = perDebut;
    }
    public ZonedDateTime getPerFin() {
        return perFin;
    }

    public void setPerFin(ZonedDateTime perFin) {
        this.perFin = perFin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PeriodeStageDTO periodeStageDTO = (PeriodeStageDTO) o;

        if ( ! Objects.equals(id, periodeStageDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PeriodeStageDTO{" +
            "id=" + id +
            ", perDebut='" + perDebut + "'" +
            ", perFin='" + perFin + "'" +
            '}';
    }
}
