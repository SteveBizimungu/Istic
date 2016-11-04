package com.projet.crm.service.impl;

import com.projet.crm.service.EtudiantService;
import com.projet.crm.domain.Etudiant;
import com.projet.crm.repository.EtudiantRepository;
import com.projet.crm.service.dto.EtudiantDTO;
import com.projet.crm.service.mapper.EtudiantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Etudiant.
 */
@Service
@Transactional
public class EtudiantServiceImpl implements EtudiantService{

    private final Logger log = LoggerFactory.getLogger(EtudiantServiceImpl.class);
    
    @Inject
    private EtudiantRepository etudiantRepository;

    @Inject
    private EtudiantMapper etudiantMapper;

    /**
     * Save a etudiant.
     *
     * @param etudiantDTO the entity to save
     * @return the persisted entity
     */
    public EtudiantDTO save(EtudiantDTO etudiantDTO) {
        log.debug("Request to save Etudiant : {}", etudiantDTO);
        Etudiant etudiant = etudiantMapper.etudiantDTOToEtudiant(etudiantDTO);
        etudiant = etudiantRepository.save(etudiant);
        EtudiantDTO result = etudiantMapper.etudiantToEtudiantDTO(etudiant);
        return result;
    }

    /**
     *  Get all the etudiants.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EtudiantDTO> findAll() {
        log.debug("Request to get all Etudiants");
        List<EtudiantDTO> result = etudiantRepository.findAll().stream()
            .map(etudiantMapper::etudiantToEtudiantDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one etudiant by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public EtudiantDTO findOne(Long id) {
        log.debug("Request to get Etudiant : {}", id);
        Etudiant etudiant = etudiantRepository.findOne(id);
        EtudiantDTO etudiantDTO = etudiantMapper.etudiantToEtudiantDTO(etudiant);
        return etudiantDTO;
    }

    /**
     *  Delete the  etudiant by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Etudiant : {}", id);
        etudiantRepository.delete(id);
    }
}
