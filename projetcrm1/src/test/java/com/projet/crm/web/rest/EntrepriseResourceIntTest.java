package com.projet.crm.web.rest;

import com.projet.crm.Projetcrm1App;

import com.projet.crm.domain.Entreprise;
import com.projet.crm.repository.EntrepriseRepository;
import com.projet.crm.service.EntrepriseService;
import com.projet.crm.service.dto.EntrepriseDTO;
import com.projet.crm.service.mapper.EntrepriseMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EntrepriseResource REST controller.
 *
 * @see EntrepriseResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Projetcrm1App.class)
public class EntrepriseResourceIntTest {

    private static final String DEFAULT_ENT_LIBELE = "AAAAA";
    private static final String UPDATED_ENT_LIBELE = "BBBBB";
    private static final String DEFAULT_ENT_VILLE = "AAAAA";
    private static final String UPDATED_ENT_VILLE = "BBBBB";
    private static final String DEFAULT_ENT_RUE = "AAAAA";
    private static final String UPDATED_ENT_RUE = "BBBBB";

    private static final Integer DEFAULT_ENT_CODE_DEP = 1;
    private static final Integer UPDATED_ENT_CODE_DEP = 2;

    @Inject
    private EntrepriseRepository entrepriseRepository;

    @Inject
    private EntrepriseMapper entrepriseMapper;

    @Inject
    private EntrepriseService entrepriseService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restEntrepriseMockMvc;

    private Entreprise entreprise;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EntrepriseResource entrepriseResource = new EntrepriseResource();
        ReflectionTestUtils.setField(entrepriseResource, "entrepriseService", entrepriseService);
        this.restEntrepriseMockMvc = MockMvcBuilders.standaloneSetup(entrepriseResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Entreprise createEntity(EntityManager em) {
        Entreprise entreprise = new Entreprise()
                .entLibele(DEFAULT_ENT_LIBELE)
                .entVille(DEFAULT_ENT_VILLE)
                .entRue(DEFAULT_ENT_RUE)
                .entCodeDep(DEFAULT_ENT_CODE_DEP);
        return entreprise;
    }

    @Before
    public void initTest() {
        entreprise = createEntity(em);
    }

    @Test
    @Transactional
    public void createEntreprise() throws Exception {
        int databaseSizeBeforeCreate = entrepriseRepository.findAll().size();

        // Create the Entreprise
        EntrepriseDTO entrepriseDTO = entrepriseMapper.entrepriseToEntrepriseDTO(entreprise);

        restEntrepriseMockMvc.perform(post("/api/entreprises")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
                .andExpect(status().isCreated());

        // Validate the Entreprise in the database
        List<Entreprise> entreprises = entrepriseRepository.findAll();
        assertThat(entreprises).hasSize(databaseSizeBeforeCreate + 1);
        Entreprise testEntreprise = entreprises.get(entreprises.size() - 1);
        assertThat(testEntreprise.getEntLibele()).isEqualTo(DEFAULT_ENT_LIBELE);
        assertThat(testEntreprise.getEntVille()).isEqualTo(DEFAULT_ENT_VILLE);
        assertThat(testEntreprise.getEntRue()).isEqualTo(DEFAULT_ENT_RUE);
        assertThat(testEntreprise.getEntCodeDep()).isEqualTo(DEFAULT_ENT_CODE_DEP);
    }

    @Test
    @Transactional
    public void getAllEntreprises() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        // Get all the entreprises
        restEntrepriseMockMvc.perform(get("/api/entreprises?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(entreprise.getId().intValue())))
                .andExpect(jsonPath("$.[*].entLibele").value(hasItem(DEFAULT_ENT_LIBELE.toString())))
                .andExpect(jsonPath("$.[*].entVille").value(hasItem(DEFAULT_ENT_VILLE.toString())))
                .andExpect(jsonPath("$.[*].entRue").value(hasItem(DEFAULT_ENT_RUE.toString())))
                .andExpect(jsonPath("$.[*].entCodeDep").value(hasItem(DEFAULT_ENT_CODE_DEP)));
    }

    @Test
    @Transactional
    public void getEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);

        // Get the entreprise
        restEntrepriseMockMvc.perform(get("/api/entreprises/{id}", entreprise.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(entreprise.getId().intValue()))
            .andExpect(jsonPath("$.entLibele").value(DEFAULT_ENT_LIBELE.toString()))
            .andExpect(jsonPath("$.entVille").value(DEFAULT_ENT_VILLE.toString()))
            .andExpect(jsonPath("$.entRue").value(DEFAULT_ENT_RUE.toString()))
            .andExpect(jsonPath("$.entCodeDep").value(DEFAULT_ENT_CODE_DEP));
    }

    @Test
    @Transactional
    public void getNonExistingEntreprise() throws Exception {
        // Get the entreprise
        restEntrepriseMockMvc.perform(get("/api/entreprises/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);
        int databaseSizeBeforeUpdate = entrepriseRepository.findAll().size();

        // Update the entreprise
        Entreprise updatedEntreprise = entrepriseRepository.findOne(entreprise.getId());
        updatedEntreprise
                .entLibele(UPDATED_ENT_LIBELE)
                .entVille(UPDATED_ENT_VILLE)
                .entRue(UPDATED_ENT_RUE)
                .entCodeDep(UPDATED_ENT_CODE_DEP);
        EntrepriseDTO entrepriseDTO = entrepriseMapper.entrepriseToEntrepriseDTO(updatedEntreprise);

        restEntrepriseMockMvc.perform(put("/api/entreprises")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(entrepriseDTO)))
                .andExpect(status().isOk());

        // Validate the Entreprise in the database
        List<Entreprise> entreprises = entrepriseRepository.findAll();
        assertThat(entreprises).hasSize(databaseSizeBeforeUpdate);
        Entreprise testEntreprise = entreprises.get(entreprises.size() - 1);
        assertThat(testEntreprise.getEntLibele()).isEqualTo(UPDATED_ENT_LIBELE);
        assertThat(testEntreprise.getEntVille()).isEqualTo(UPDATED_ENT_VILLE);
        assertThat(testEntreprise.getEntRue()).isEqualTo(UPDATED_ENT_RUE);
        assertThat(testEntreprise.getEntCodeDep()).isEqualTo(UPDATED_ENT_CODE_DEP);
    }

    @Test
    @Transactional
    public void deleteEntreprise() throws Exception {
        // Initialize the database
        entrepriseRepository.saveAndFlush(entreprise);
        int databaseSizeBeforeDelete = entrepriseRepository.findAll().size();

        // Get the entreprise
        restEntrepriseMockMvc.perform(delete("/api/entreprises/{id}", entreprise.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Entreprise> entreprises = entrepriseRepository.findAll();
        assertThat(entreprises).hasSize(databaseSizeBeforeDelete - 1);
    }
}
