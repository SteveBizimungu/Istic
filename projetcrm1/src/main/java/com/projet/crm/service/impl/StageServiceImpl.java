package com.projet.crm.service.impl;

import com.projet.crm.service.StageService;
import com.projet.crm.domain.Stage;
import com.projet.crm.repository.StageRepository;
import com.projet.crm.service.dto.StageDTO;
import com.projet.crm.service.mapper.StageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Stage.
 */
@Service
@Transactional
public class StageServiceImpl implements StageService{

    private final Logger log = LoggerFactory.getLogger(StageServiceImpl.class);
    
    @Inject
    private StageRepository stageRepository;

    @Inject
    private StageMapper stageMapper;

    /**
     * Save a stage.
     *
     * @param stageDTO the entity to save
     * @return the persisted entity
     */
    public StageDTO save(StageDTO stageDTO) {
        log.debug("Request to save Stage : {}", stageDTO);
        Stage stage = stageMapper.stageDTOToStage(stageDTO);
        stage = stageRepository.save(stage);
        StageDTO result = stageMapper.stageToStageDTO(stage);
        return result;
    }

    /**
     *  Get all the stages.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<StageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Stages");
        Page<Stage> result = stageRepository.findAll(pageable);
        return result.map(stage -> stageMapper.stageToStageDTO(stage));
    }

    /**
     *  Get one stage by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public StageDTO findOne(Long id) {
        log.debug("Request to get Stage : {}", id);
        Stage stage = stageRepository.findOne(id);
        StageDTO stageDTO = stageMapper.stageToStageDTO(stage);
        return stageDTO;
    }

    /**
     *  Delete the  stage by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Stage : {}", id);
        stageRepository.delete(id);
    }
}
