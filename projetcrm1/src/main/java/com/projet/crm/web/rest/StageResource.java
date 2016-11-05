package com.projet.crm.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.projet.crm.service.StageService;
import com.projet.crm.web.rest.util.HeaderUtil;
import com.projet.crm.web.rest.util.PaginationUtil;
import com.projet.crm.service.dto.StageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
 * REST controller for managing Stage.
 */
@RestController
@RequestMapping("/api")
public class StageResource {

    private final Logger log = LoggerFactory.getLogger(StageResource.class);

    @Inject
    private StageService stageService;

    /**
     * POST  /stages : Create a new stage.
     *
     * @param stageDTO the stageDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new stageDTO, or with status 400 (Bad Request) if the stage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/stages",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StageDTO> createStage(@RequestBody StageDTO stageDTO) throws URISyntaxException {
        log.debug("REST request to save Stage : {}", stageDTO);
        if (stageDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("stage", "idexists", "A new stage cannot already have an ID")).body(null);
        }
        StageDTO result = stageService.save(stageDTO);
        return ResponseEntity.created(new URI("/api/stages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert("stage", result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /stages : Updates an existing stage.
     *
     * @param stageDTO the stageDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated stageDTO,
     * or with status 400 (Bad Request) if the stageDTO is not valid,
     * or with status 500 (Internal Server Error) if the stageDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @RequestMapping(value = "/stages",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StageDTO> updateStage(@RequestBody StageDTO stageDTO) throws URISyntaxException {
        log.debug("REST request to update Stage : {}", stageDTO);
        if (stageDTO.getId() == null) {
            return createStage(stageDTO);
        }
        StageDTO result = stageService.save(stageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("stage", stageDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /stages : get all the stages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of stages in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @RequestMapping(value = "/stages",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<List<StageDTO>> getAllStages(Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of Stages");
        Page<StageDTO> page = stageService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/stages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /stages/:id : get the "id" stage.
     *
     * @param id the id of the stageDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the stageDTO, or with status 404 (Not Found)
     */
    @RequestMapping(value = "/stages/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<StageDTO> getStage(@PathVariable Long id) {
        log.debug("REST request to get Stage : {}", id);
        StageDTO stageDTO = stageService.findOne(id);
        return Optional.ofNullable(stageDTO)
            .map(result -> new ResponseEntity<>(
                result,
                HttpStatus.OK))
            .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE  /stages/:id : delete the "id" stage.
     *
     * @param id the id of the stageDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @RequestMapping(value = "/stages/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        log.debug("REST request to delete Stage : {}", id);
        stageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert("stage", id.toString())).build();
    }
/*
@RequestMapping(value="/stages/repertoire/etudiant/{periode}")
    public ResponseEntity<EtudiantDTO>getEtudiantPeriode(@PathVariable Long periode){
    log.debug("REST request to load etudiant contact liste per periode : {}", periode);
    StageService.findEtundiantPerPeriode(periode);
    return
    }*/
}
