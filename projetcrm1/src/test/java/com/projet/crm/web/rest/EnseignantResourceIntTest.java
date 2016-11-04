package com.projet.crm.web.rest;

import com.projet.crm.Projetcrm1App;

import com.projet.crm.domain.Enseignant;
import com.projet.crm.repository.EnseignantRepository;
import com.projet.crm.service.EnseignantService;
import com.projet.crm.service.dto.EnseignantDTO;
import com.projet.crm.service.mapper.EnseignantMapper;

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
 * Test class for the EnseignantResource REST controller.
 *
 * @see EnseignantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Projetcrm1App.class)
public class EnseignantResourceIntTest {

    private static final String DEFAULT_ENS_NAME = "AAAAA";
    private static final String UPDATED_ENS_NAME = "BBBBB";
    private static final String DEFAULT_ENS_PRENOM = "AAAAA";
    private static final String UPDATED_ENS_PRENOM = "BBBBB";
    private static final String DEFAULT_ENS_VILLE = "AAAAA";
    private static final String UPDATED_ENS_VILLE = "BBBBB";
    private static final String DEFAULT_ENS_RUE = "AAAAA";
    private static final String UPDATED_ENS_RUE = "BBBBB";

    private static final Integer DEFAULT_ENS_CODE_DEP = 1;
    private static final Integer UPDATED_ENS_CODE_DEP = 2;
    private static final String DEFAULT_ENS_TEL = "AAAAA";
    private static final String UPDATED_ENS_TEL = "BBBBB";
    private static final String DEFAULT_ENS_MAIL = "AAAAA";
    private static final String UPDATED_ENS_MAIL = "BBBBB";

    @Inject
    private EnseignantRepository enseignantRepository;

    @Inject
    private EnseignantMapper enseignantMapper;

    @Inject
    private EnseignantService enseignantService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restEnseignantMockMvc;

    private Enseignant enseignant;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EnseignantResource enseignantResource = new EnseignantResource();
        ReflectionTestUtils.setField(enseignantResource, "enseignantService", enseignantService);
        this.restEnseignantMockMvc = MockMvcBuilders.standaloneSetup(enseignantResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Enseignant createEntity(EntityManager em) {
        Enseignant enseignant = new Enseignant()
                .ensName(DEFAULT_ENS_NAME)
                .ensPrenom(DEFAULT_ENS_PRENOM)
                .ensVille(DEFAULT_ENS_VILLE)
                .ensRue(DEFAULT_ENS_RUE)
                .ensCodeDep(DEFAULT_ENS_CODE_DEP)
                .ensTel(DEFAULT_ENS_TEL)
                .ensMail(DEFAULT_ENS_MAIL);
        return enseignant;
    }

    @Before
    public void initTest() {
        enseignant = createEntity(em);
    }

    @Test
    @Transactional
    public void createEnseignant() throws Exception {
        int databaseSizeBeforeCreate = enseignantRepository.findAll().size();

        // Create the Enseignant
        EnseignantDTO enseignantDTO = enseignantMapper.enseignantToEnseignantDTO(enseignant);

        restEnseignantMockMvc.perform(post("/api/enseignants")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(enseignantDTO)))
                .andExpect(status().isCreated());

        // Validate the Enseignant in the database
        List<Enseignant> enseignants = enseignantRepository.findAll();
        assertThat(enseignants).hasSize(databaseSizeBeforeCreate + 1);
        Enseignant testEnseignant = enseignants.get(enseignants.size() - 1);
        assertThat(testEnseignant.getEnsName()).isEqualTo(DEFAULT_ENS_NAME);
        assertThat(testEnseignant.getEnsPrenom()).isEqualTo(DEFAULT_ENS_PRENOM);
        assertThat(testEnseignant.getEnsVille()).isEqualTo(DEFAULT_ENS_VILLE);
        assertThat(testEnseignant.getEnsRue()).isEqualTo(DEFAULT_ENS_RUE);
        assertThat(testEnseignant.getEnsCodeDep()).isEqualTo(DEFAULT_ENS_CODE_DEP);
        assertThat(testEnseignant.getEnsTel()).isEqualTo(DEFAULT_ENS_TEL);
        assertThat(testEnseignant.getEnsMail()).isEqualTo(DEFAULT_ENS_MAIL);
    }

    @Test
    @Transactional
    public void getAllEnseignants() throws Exception {
        // Initialize the database
        enseignantRepository.saveAndFlush(enseignant);

        // Get all the enseignants
        restEnseignantMockMvc.perform(get("/api/enseignants?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(enseignant.getId().intValue())))
                .andExpect(jsonPath("$.[*].ensName").value(hasItem(DEFAULT_ENS_NAME.toString())))
                .andExpect(jsonPath("$.[*].ensPrenom").value(hasItem(DEFAULT_ENS_PRENOM.toString())))
                .andExpect(jsonPath("$.[*].ensVille").value(hasItem(DEFAULT_ENS_VILLE.toString())))
                .andExpect(jsonPath("$.[*].ensRue").value(hasItem(DEFAULT_ENS_RUE.toString())))
                .andExpect(jsonPath("$.[*].ensCodeDep").value(hasItem(DEFAULT_ENS_CODE_DEP)))
                .andExpect(jsonPath("$.[*].ensTel").value(hasItem(DEFAULT_ENS_TEL.toString())))
                .andExpect(jsonPath("$.[*].ensMail").value(hasItem(DEFAULT_ENS_MAIL.toString())));
    }

    @Test
    @Transactional
    public void getEnseignant() throws Exception {
        // Initialize the database
        enseignantRepository.saveAndFlush(enseignant);

        // Get the enseignant
        restEnseignantMockMvc.perform(get("/api/enseignants/{id}", enseignant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(enseignant.getId().intValue()))
            .andExpect(jsonPath("$.ensName").value(DEFAULT_ENS_NAME.toString()))
            .andExpect(jsonPath("$.ensPrenom").value(DEFAULT_ENS_PRENOM.toString()))
            .andExpect(jsonPath("$.ensVille").value(DEFAULT_ENS_VILLE.toString()))
            .andExpect(jsonPath("$.ensRue").value(DEFAULT_ENS_RUE.toString()))
            .andExpect(jsonPath("$.ensCodeDep").value(DEFAULT_ENS_CODE_DEP))
            .andExpect(jsonPath("$.ensTel").value(DEFAULT_ENS_TEL.toString()))
            .andExpect(jsonPath("$.ensMail").value(DEFAULT_ENS_MAIL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEnseignant() throws Exception {
        // Get the enseignant
        restEnseignantMockMvc.perform(get("/api/enseignants/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEnseignant() throws Exception {
        // Initialize the database
        enseignantRepository.saveAndFlush(enseignant);
        int databaseSizeBeforeUpdate = enseignantRepository.findAll().size();

        // Update the enseignant
        Enseignant updatedEnseignant = enseignantRepository.findOne(enseignant.getId());
        updatedEnseignant
                .ensName(UPDATED_ENS_NAME)
                .ensPrenom(UPDATED_ENS_PRENOM)
                .ensVille(UPDATED_ENS_VILLE)
                .ensRue(UPDATED_ENS_RUE)
                .ensCodeDep(UPDATED_ENS_CODE_DEP)
                .ensTel(UPDATED_ENS_TEL)
                .ensMail(UPDATED_ENS_MAIL);
        EnseignantDTO enseignantDTO = enseignantMapper.enseignantToEnseignantDTO(updatedEnseignant);

        restEnseignantMockMvc.perform(put("/api/enseignants")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(enseignantDTO)))
                .andExpect(status().isOk());

        // Validate the Enseignant in the database
        List<Enseignant> enseignants = enseignantRepository.findAll();
        assertThat(enseignants).hasSize(databaseSizeBeforeUpdate);
        Enseignant testEnseignant = enseignants.get(enseignants.size() - 1);
        assertThat(testEnseignant.getEnsName()).isEqualTo(UPDATED_ENS_NAME);
        assertThat(testEnseignant.getEnsPrenom()).isEqualTo(UPDATED_ENS_PRENOM);
        assertThat(testEnseignant.getEnsVille()).isEqualTo(UPDATED_ENS_VILLE);
        assertThat(testEnseignant.getEnsRue()).isEqualTo(UPDATED_ENS_RUE);
        assertThat(testEnseignant.getEnsCodeDep()).isEqualTo(UPDATED_ENS_CODE_DEP);
        assertThat(testEnseignant.getEnsTel()).isEqualTo(UPDATED_ENS_TEL);
        assertThat(testEnseignant.getEnsMail()).isEqualTo(UPDATED_ENS_MAIL);
    }

    @Test
    @Transactional
    public void deleteEnseignant() throws Exception {
        // Initialize the database
        enseignantRepository.saveAndFlush(enseignant);
        int databaseSizeBeforeDelete = enseignantRepository.findAll().size();

        // Get the enseignant
        restEnseignantMockMvc.perform(delete("/api/enseignants/{id}", enseignant.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Enseignant> enseignants = enseignantRepository.findAll();
        assertThat(enseignants).hasSize(databaseSizeBeforeDelete - 1);
    }
}
