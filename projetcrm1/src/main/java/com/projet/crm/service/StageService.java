package com.projet.crm.service;

import com.projet.crm.service.dto.StageDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Stage.
 */
public interface StageService {

    /**
     * Save a stage.
     *
     * @param stageDTO the entity to save
     * @return the persisted entity
     */
    StageDTO save(StageDTO stageDTO);

    /**
     *  Get all the stages.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<StageDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" stage.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    StageDTO findOne(Long id);

    /**
     *  Delete the "id" stage.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
