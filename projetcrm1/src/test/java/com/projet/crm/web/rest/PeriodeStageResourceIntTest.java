package com.projet.crm.web.rest;

import com.projet.crm.Projetcrm1App;

import com.projet.crm.domain.PeriodeStage;
import com.projet.crm.repository.PeriodeStageRepository;
import com.projet.crm.service.PeriodeStageService;
import com.projet.crm.service.dto.PeriodeStageDTO;
import com.projet.crm.service.mapper.PeriodeStageMapper;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.Matchers.hasItem;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the PeriodeStageResource REST controller.
 *
 * @see PeriodeStageResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Projetcrm1App.class)
public class PeriodeStageResourceIntTest {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("Z"));


    private static final ZonedDateTime DEFAULT_PER_DEBUT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_PER_DEBUT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_PER_DEBUT_STR = dateTimeFormatter.format(DEFAULT_PER_DEBUT);

    private static final ZonedDateTime DEFAULT_PER_FIN = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneId.systemDefault());
    private static final ZonedDateTime UPDATED_PER_FIN = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);
    private static final String DEFAULT_PER_FIN_STR = dateTimeFormatter.format(DEFAULT_PER_FIN);

    @Inject
    private PeriodeStageRepository periodeStageRepository;

    @Inject
    private PeriodeStageMapper periodeStageMapper;

    @Inject
    private PeriodeStageService periodeStageService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restPeriodeStageMockMvc;

    private PeriodeStage periodeStage;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        PeriodeStageResource periodeStageResource = new PeriodeStageResource();
        ReflectionTestUtils.setField(periodeStageResource, "periodeStageService", periodeStageService);
        this.restPeriodeStageMockMvc = MockMvcBuilders.standaloneSetup(periodeStageResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PeriodeStage createEntity(EntityManager em) {
        PeriodeStage periodeStage = new PeriodeStage()
                .perDebut(DEFAULT_PER_DEBUT)
                .perFin(DEFAULT_PER_FIN);
        return periodeStage;
    }

    @Before
    public void initTest() {
        periodeStage = createEntity(em);
    }

    @Test
    @Transactional
    public void createPeriodeStage() throws Exception {
        int databaseSizeBeforeCreate = periodeStageRepository.findAll().size();

        // Create the PeriodeStage
        PeriodeStageDTO periodeStageDTO = periodeStageMapper.periodeStageToPeriodeStageDTO(periodeStage);

        restPeriodeStageMockMvc.perform(post("/api/periode-stages")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(periodeStageDTO)))
                .andExpect(status().isCreated());

        // Validate the PeriodeStage in the database
        List<PeriodeStage> periodeStages = periodeStageRepository.findAll();
        assertThat(periodeStages).hasSize(databaseSizeBeforeCreate + 1);
        PeriodeStage testPeriodeStage = periodeStages.get(periodeStages.size() - 1);
        assertThat(testPeriodeStage.getPerDebut()).isEqualTo(DEFAULT_PER_DEBUT);
        assertThat(testPeriodeStage.getPerFin()).isEqualTo(DEFAULT_PER_FIN);
    }

    @Test
    @Transactional
    public void getAllPeriodeStages() throws Exception {
        // Initialize the database
        periodeStageRepository.saveAndFlush(periodeStage);

        // Get all the periodeStages
        restPeriodeStageMockMvc.perform(get("/api/periode-stages?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(periodeStage.getId().intValue())))
                .andExpect(jsonPath("$.[*].perDebut").value(hasItem(DEFAULT_PER_DEBUT_STR)))
                .andExpect(jsonPath("$.[*].perFin").value(hasItem(DEFAULT_PER_FIN_STR)));
    }

    @Test
    @Transactional
    public void getPeriodeStage() throws Exception {
        // Initialize the database
        periodeStageRepository.saveAndFlush(periodeStage);

        // Get the periodeStage
        restPeriodeStageMockMvc.perform(get("/api/periode-stages/{id}", periodeStage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(periodeStage.getId().intValue()))
            .andExpect(jsonPath("$.perDebut").value(DEFAULT_PER_DEBUT_STR))
            .andExpect(jsonPath("$.perFin").value(DEFAULT_PER_FIN_STR));
    }

    @Test
    @Transactional
    public void getNonExistingPeriodeStage() throws Exception {
        // Get the periodeStage
        restPeriodeStageMockMvc.perform(get("/api/periode-stages/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePeriodeStage() throws Exception {
        // Initialize the database
        periodeStageRepository.saveAndFlush(periodeStage);
        int databaseSizeBeforeUpdate = periodeStageRepository.findAll().size();

        // Update the periodeStage
        PeriodeStage updatedPeriodeStage = periodeStageRepository.findOne(periodeStage.getId());
        updatedPeriodeStage
                .perDebut(UPDATED_PER_DEBUT)
                .perFin(UPDATED_PER_FIN);
        PeriodeStageDTO periodeStageDTO = periodeStageMapper.periodeStageToPeriodeStageDTO(updatedPeriodeStage);

        restPeriodeStageMockMvc.perform(put("/api/periode-stages")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(periodeStageDTO)))
                .andExpect(status().isOk());

        // Validate the PeriodeStage in the database
        List<PeriodeStage> periodeStages = periodeStageRepository.findAll();
        assertThat(periodeStages).hasSize(databaseSizeBeforeUpdate);
        PeriodeStage testPeriodeStage = periodeStages.get(periodeStages.size() - 1);
        assertThat(testPeriodeStage.getPerDebut()).isEqualTo(UPDATED_PER_DEBUT);
        assertThat(testPeriodeStage.getPerFin()).isEqualTo(UPDATED_PER_FIN);
    }

    @Test
    @Transactional
    public void deletePeriodeStage() throws Exception {
        // Initialize the database
        periodeStageRepository.saveAndFlush(periodeStage);
        int databaseSizeBeforeDelete = periodeStageRepository.findAll().size();

        // Get the periodeStage
        restPeriodeStageMockMvc.perform(delete("/api/periode-stages/{id}", periodeStage.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<PeriodeStage> periodeStages = periodeStageRepository.findAll();
        assertThat(periodeStages).hasSize(databaseSizeBeforeDelete - 1);
    }
}
