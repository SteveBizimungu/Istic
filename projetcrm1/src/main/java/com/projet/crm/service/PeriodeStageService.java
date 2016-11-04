package com.projet.crm.service;

import com.projet.crm.service.dto.PeriodeStageDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing PeriodeStage.
 */
public interface PeriodeStageService {

    /**
     * Save a periodeStage.
     *
     * @param periodeStageDTO the entity to save
     * @return the persisted entity
     */
    PeriodeStageDTO save(PeriodeStageDTO periodeStageDTO);

    /**
     *  Get all the periodeStages.
     *  
     *  @return the list of entities
     */
    List<PeriodeStageDTO> findAll();

    /**
     *  Get the "id" periodeStage.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PeriodeStageDTO findOne(Long id);

    /**
     *  Delete the "id" periodeStage.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
