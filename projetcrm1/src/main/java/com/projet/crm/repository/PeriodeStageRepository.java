package com.projet.crm.repository;

import com.projet.crm.domain.PeriodeStage;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the PeriodeStage entity.
 */
@SuppressWarnings("unused")
public interface PeriodeStageRepository extends JpaRepository<PeriodeStage,Long> {

}
