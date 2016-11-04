package com.projet.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.projet.crm.service.EnseignantService;
import com.projet.crm.web.rest.util.HeaderUtil;
import com.projet.crm.service.dto.EnseignantDTO;
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
 * REST controller for managing Enseignant.
 */
@RestController
@RequestMapping("/api")
public class EnseignantResource {

    private final Logger log = LoggerFactory.getLogger(EnseignantResource.class);
        
    @Inject
    private EnseignantService enseignantService;

    /**
     * POST  /enseignants : Create a new enseignant.
     *
     * @param enseignantDTO the enseignantDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new enseignantDTO, or with status 400 (Bad Request) if the enseignant has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/enseignants",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EnseignantDTO> createEnseignant(@RequestBody EnseignantDTO enseignantDTO) throws URISyntaxException {
        log.debug("REST request to save Enseignant : {}", enseignantDTO);
        if (enseignantDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("enseignant", "idexists", "A new enseignant cannot already have an ID")).body(null);
        }
        EnseignantDTO result = enseignantService.save(enseignantDTO);
        return ResponseEntity.created(new URI("/api/enseignants/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("enseignant", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /enseignants : Updates an existing enseignant.
     *
     * @param enseignantDTO the enseignantDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated enseignantDTO,
     * or with status 400 (Bad Request) if the enseignantDTO is not valid,
     * or with status 500 (Internal Server Error) if the enseignantDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/enseignants",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EnseignantDTO> updateEnseignant(@RequestBody EnseignantDTO enseignantDTO) throws URISyntaxException {
        log.debug("REST request to update Enseignant : {}", enseignantDTO);
        if (enseignantDTO.getId() == null) {
            return createEnseignant(enseignantDTO);
        }
        EnseignantDTO result = enseignantService.save(enseignantDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("enseignant", enseignantDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /enseignants : get all the enseignants.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of enseignants in body
     */
    @RequestMapping(value = "/enseignants",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<EnseignantDTO> getAllEnseignants() {
        log.debug("REST request to get all Enseignants");
        return enseignantService.findAll();
    }

    /**
     * GET  /enseignants/:id : get the "id" enseignant.
     *
     * @param id the id of the enseignantDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the enseignantDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/enseignants/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<EnseignantDTO> getEnseignant(@PathVariable Long id) {
        log.debug("REST request to get Enseignant : {}", id);
        EnseignantDTO enseignantDTO = enseignantService.findOne(id);
        return Optional.ofNullable(enseignantDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /enseignants/:id : delete the "id" enseignant.
     *
     * @param id the id of the enseignantDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/enseignants/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteEnseignant(@PathVariable Long id) {
        log.debug("REST request to delete Enseignant : {}", id);
        enseignantService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("enseignant", id.toString())).build();
    }

}
