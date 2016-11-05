package com.projet.crm.service;

import com.projet.crm.service.dto.EtudiantDTO;

import java.util.LinkedList;
import java.util.List;

/**
 * Service Interface for managing Etudiant.
 */
public interface EtudiantService {

    /**
     * Save a etudiant.
     *
     * @param etudiantDTO the entity to save
     * @return the persisted entity
     */
    EtudiantDTO save(EtudiantDTO etudiantDTO);

    /**
     *  Get all the etudiants.
     *
     *  @return the list of entities
     */
    List<EtudiantDTO> findAll();

    /**
     *  Get the "id" etudiant.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    EtudiantDTO findOne(Long id);


    /**
     * Get the id of a periode
     * @param id the id of a given periode
     * @return the list of etudiants for that periode
     */
    /*
    List<EtudiantDTO> findByPeriode(Long id);*/

    /**
     *  Delete the "id" etudiant.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
