package com.projet.crm.service.impl;

import com.projet.crm.service.EntrepriseService;
import com.projet.crm.domain.Entreprise;
import com.projet.crm.repository.EntrepriseRepository;
import com.projet.crm.service.dto.EntrepriseDTO;
import com.projet.crm.service.mapper.EntrepriseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Entreprise.
 */
@Service
@Transactional
public class EntrepriseServiceImpl implements EntrepriseService{

    private final Logger log = LoggerFactory.getLogger(EntrepriseServiceImpl.class);
    
    @Inject
    private EntrepriseRepository entrepriseRepository;

    @Inject
    private EntrepriseMapper entrepriseMapper;

    /**
     * Save a entreprise.
     *
     * @param entrepriseDTO the entity to save
     * @return the persisted entity
     */
    public EntrepriseDTO save(EntrepriseDTO entrepriseDTO) {
        log.debug("Request to save Entreprise : {}", entrepriseDTO);
        Entreprise entreprise = entrepriseMapper.entrepriseDTOToEntreprise(entrepriseDTO);
        entreprise = entrepriseRepository.save(entreprise);
        EntrepriseDTO result = entrepriseMapper.entrepriseToEntrepriseDTO(entreprise);
        return result;
    }

    /**
     *  Get all the entreprises.
     *  
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<EntrepriseDTO> findAll() {
        log.debug("Request to get all Entreprises");
        List<EntrepriseDTO> result = entrepriseRepository.findAll().stream()
            .map(entrepriseMapper::entrepriseToEntrepriseDTO)
            .collect(Collectors.toCollection(LinkedList::new));

        return result;
    }

    /**
     *  Get one entreprise by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public EntrepriseDTO findOne(Long id) {
        log.debug("Request to get Entreprise : {}", id);
        Entreprise entreprise = entrepriseRepository.findOne(id);
        EntrepriseDTO entrepriseDTO = entrepriseMapper.entrepriseToEntrepriseDTO(entreprise);
        return entrepriseDTO;
    }

    /**
     *  Delete the  entreprise by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Entreprise : {}", id);
        entrepriseRepository.delete(id);
    }
}
