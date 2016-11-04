package com.projet.crm.service.impl;

import com.projet.crm.service.EnseignantService;
import com.projet.crm.domain.Enseignant;
import com.projet.crm.repository.EnseignantRepository;
import com.projet.crm.service.dto.EnseignantDTO;
import com.projet.crm.service.mapper.EnseignantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Enseignant.
 */
@Service
@Transactional
public class EnseignantServiceImpl implements EnseignantService{

    private final Logger log = LoggerFactory.getLogger(EnseignantServiceImpl.class);
    
    @Inject
    private EnseignantRepository enseignantRepository;

    @Inject
    private EnseignantMapper enseignantMapper;

    /**
     * Save a enseignant.
     *
     * @param enseignantDTO the entity to save
     * @return the persisted entity
     */
    public EnseignantDTO save(EnseignantDTO enseignantDTO) {
        log.debug("Request to save Enseignant : {}", enseignantDTO);
        Enseignant enseignant = enseignantMapper.enseignantDTOToEnseignant(enseignantDTO);
        enseignant = enseignantRepository.save(enseignant);
        EnseignantDTO result = enseignantMapper.enseignantToEnseignantDTO(enseignant);
        return result;
    }

    /**
     *  Get all the enseignants.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EnseignantDTO> findAll() {
        log.debug("Request to get all Enseignants");
        List<EnseignantDTO> result = enseignantRepository.findAll().stream()
            .map(enseignantMapper::enseignantToEnseignantDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one enseignant by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public EnseignantDTO findOne(Long id) {
        log.debug("Request to get Enseignant : {}", id);
        Enseignant enseignant = enseignantRepository.findOne(id);
        EnseignantDTO enseignantDTO = enseignantMapper.enseignantToEnseignantDTO(enseignant);
        return enseignantDTO;
    }

    /**
     *  Delete the  enseignant by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Enseignant : {}", id);
        enseignantRepository.delete(id);
    }
}
