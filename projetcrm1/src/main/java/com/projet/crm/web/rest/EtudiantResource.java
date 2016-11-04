package com.projet.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.projet.crm.service.EtudiantService;
import com.projet.crm.web.rest.util.HeaderUtil;
import com.projet.crm.service.dto.EtudiantDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing Etudiant.
 */
@RestController
@RequestMapping("/api")
public class EtudiantResource {

    private final Logger log = LoggerFactory.getLogger(EtudiantResource.class);
        
    @Inject
    private EtudiantService etudiantService;

    /**
     * POST  /etudiants : Create a new etudiant.
     *
     * @param etudiantDTO the etudiantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new etudiantDTO, or with status 400 (Bad Request) if the etudiant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/etudiants",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EtudiantDTO> createEtudiant(@RequestBody EtudiantDTO etudiantDTO) throws URISyntaxException {
        log.debug("REST request to save Etudiant : {}", etudiantDTO);
        if (etudiantDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("etudiant", "idexists", "A new etudiant cannot already have an ID")).body(null);
        }
        EtudiantDTO result = etudiantService.save(etudiantDTO);
        return ResponseEntity.created(new URI("/api/etudiants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("etudiant", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /etudiants : Updates an existing etudiant.
     *
     * @param etudiantDTO the etudiantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated etudiantDTO,
     * or with status 400 (Bad Request) if the etudiantDTO is not valid,
     * or with status 500 (Internal Server Error) if the etudiantDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/etudiants",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EtudiantDTO> updateEtudiant(@RequestBody EtudiantDTO etudiantDTO) throws URISyntaxException {
        log.debug("REST request to update Etudiant : {}", etudiantDTO);
        if (etudiantDTO.getId() == null) {
            return createEtudiant(etudiantDTO);
        }
        EtudiantDTO result = etudiantService.save(etudiantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("etudiant", etudiantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /etudiants : get all the etudiants.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of etudiants in body
     */
    @RequestMapping(value = "/etudiants",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<EtudiantDTO> getAllEtudiants() {
        log.debug("REST request to get all Etudiants");
        return etudiantService.findAll();
    }

    /**
     * GET  /etudiants/:id : get the "id" etudiant.
     *
     * @param id the id of the etudiantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the etudiantDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/etudiants/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EtudiantDTO> getEtudiant(@PathVariable Long id) {
        log.debug("REST request to get Etudiant : {}", id);
        EtudiantDTO etudiantDTO = etudiantService.findOne(id);
        return Optional.ofNullable(etudiantDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /etudiants/:id : delete the "id" etudiant.
     *
     * @param id the id of the etudiantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/etudiants/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEtudiant(@PathVariable Long id) {
        log.debug("REST request to delete Etudiant : {}", id);
        etudiantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("etudiant", id.toString())).build();
    }

}
