package com.projet.crm.service.impl;

import com.projet.crm.service.PeriodeStageService;
import com.projet.crm.domain.PeriodeStage;
import com.projet.crm.repository.PeriodeStageRepository;
import com.projet.crm.service.dto.PeriodeStageDTO;
import com.projet.crm.service.mapper.PeriodeStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing PeriodeStage.
 */
@Service
@Transactional
public class PeriodeStageServiceImpl implements PeriodeStageService{

    private final Logger log = LoggerFactory.getLogger(PeriodeStageServiceImpl.class);
    
    @Inject
    private PeriodeStageRepository periodeStageRepository;

    @Inject
    private PeriodeStageMapper periodeStageMapper;

    /**
     * Save a periodeStage.
     *
     * @param periodeStageDTO the entity to save
     * @return the persisted entity
     */
    public PeriodeStageDTO save(PeriodeStageDTO periodeStageDTO) {
        log.debug("Request to save PeriodeStage : {}", periodeStageDTO);
        PeriodeStage periodeStage = periodeStageMapper.periodeStageDTOToPeriodeStage(periodeStageDTO);
        periodeStage = periodeStageRepository.save(periodeStage);
        PeriodeStageDTO result = periodeStageMapper.periodeStageToPeriodeStageDTO(periodeStage);
        return result;
    }

    /**
     *  Get all the periodeStages.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<PeriodeStageDTO> findAll() {
        log.debug("Request to get all PeriodeStages");
        List<PeriodeStageDTO> result = periodeStageRepository.findAll().stream()
            .map(periodeStageMapper::periodeStageToPeriodeStageDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one periodeStage by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public PeriodeStageDTO findOne(Long id) {
        log.debug("Request to get PeriodeStage : {}", id);
        PeriodeStage periodeStage = periodeStageRepository.findOne(id);
        PeriodeStageDTO periodeStageDTO = periodeStageMapper.periodeStageToPeriodeStageDTO(periodeStage);
        return periodeStageDTO;
    }

    /**
     *  Delete the  periodeStage by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete PeriodeStage : {}", id);
        periodeStageRepository.delete(id);
    }
}
