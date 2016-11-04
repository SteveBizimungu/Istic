package com.projet.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.projet.crm.service.PeriodeStageService;
import com.projet.crm.web.rest.util.HeaderUtil;
import com.projet.crm.service.dto.PeriodeStageDTO;
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
 * REST controller for managing PeriodeStage.
 */
@RestController
@RequestMapping("/api")
public class PeriodeStageResource {

    private final Logger log = LoggerFactory.getLogger(PeriodeStageResource.class);
        
    @Inject
    private PeriodeStageService periodeStageService;

    /**
     * POST  /periode-stages : Create a new periodeStage.
     *
     * @param periodeStageDTO the periodeStageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new periodeStageDTO, or with status 400 (Bad Request) if the periodeStage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/periode-stages",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PeriodeStageDTO> createPeriodeStage(@RequestBody PeriodeStageDTO periodeStageDTO) throws URISyntaxException {
        log.debug("REST request to save PeriodeStage : {}", periodeStageDTO);
        if (periodeStageDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("periodeStage", "idexists", "A new periodeStage cannot already have an ID")).body(null);
        }
        PeriodeStageDTO result = periodeStageService.save(periodeStageDTO);
        return ResponseEntity.created(new URI("/api/periode-stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("periodeStage", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /periode-stages : Updates an existing periodeStage.
     *
     * @param periodeStageDTO the periodeStageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated periodeStageDTO,
     * or with status 400 (Bad Request) if the periodeStageDTO is not valid,
     * or with status 500 (Internal Server Error) if the periodeStageDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/periode-stages",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PeriodeStageDTO> updatePeriodeStage(@RequestBody PeriodeStageDTO periodeStageDTO) throws URISyntaxException {
        log.debug("REST request to update PeriodeStage : {}", periodeStageDTO);
        if (periodeStageDTO.getId() == null) {
            return createPeriodeStage(periodeStageDTO);
        }
        PeriodeStageDTO result = periodeStageService.save(periodeStageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("periodeStage", periodeStageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /periode-stages : get all the periodeStages.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of periodeStages in body
     */
    @RequestMapping(value = "/periode-stages",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<PeriodeStageDTO> getAllPeriodeStages() {
        log.debug("REST request to get all PeriodeStages");
        return periodeStageService.findAll();
    }

    /**
     * GET  /periode-stages/:id : get the "id" periodeStage.
     *
     * @param id the id of the periodeStageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the periodeStageDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/periode-stages/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<PeriodeStageDTO> getPeriodeStage(@PathVariable Long id) {
        log.debug("REST request to get PeriodeStage : {}", id);
        PeriodeStageDTO periodeStageDTO = periodeStageService.findOne(id);
        return Optional.ofNullable(periodeStageDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /periode-stages/:id : delete the "id" periodeStage.
     *
     * @param id the id of the periodeStageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/periode-stages/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deletePeriodeStage(@PathVariable Long id) {
        log.debug("REST request to delete PeriodeStage : {}", id);
        periodeStageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("periodeStage", id.toString())).build();
    }

}
