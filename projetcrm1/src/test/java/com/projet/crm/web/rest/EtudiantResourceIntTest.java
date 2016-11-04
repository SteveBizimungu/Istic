package com.projet.crm.web.rest;

import com.projet.crm.Projetcrm1App;

import com.projet.crm.domain.Etudiant;
import com.projet.crm.repository.EtudiantRepository;
import com.projet.crm.service.EtudiantService;
import com.projet.crm.service.dto.EtudiantDTO;
import com.projet.crm.service.mapper.EtudiantMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the EtudiantResource REST controller.
 *
 * @see EtudiantResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Projetcrm1App.class)
public class EtudiantResourceIntTest {

    private static final String DEFAULT_ETU_NAME = "AAAAA";
    private static final String UPDATED_ETU_NAME = "BBBBB";
    private static final String DEFAULT_ETU_PRENOM = "AAAAA";
    private static final String UPDATED_ETU_PRENOM = "BBBBB";
    private static final String DEFAULT_ETU_VILLE = "AAAAA";
    private static final String UPDATED_ETU_VILLE = "BBBBB";
    private static final String DEFAULT_ETU_RUE = "AAAAA";
    private static final String UPDATED_ETU_RUE = "BBBBB";

    private static final Integer DEFAULT_ETU_CODE_DEP = 1;
    private static final Integer UPDATED_ETU_CODE_DEP = 2;

    private static final LocalDate DEFAULT_ETU_PREMIERE_INSCRIPTION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ETU_PREMIERE_INSCRIPTION = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ETU_ANNEE_COURANTE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ETU_ANNEE_COURANTE = LocalDate.now(ZoneId.systemDefault());
    private static final String DEFAULT_ETU_MAIL = "AAAAA";
    private static final String UPDATED_ETU_MAIL = "BBBBB";

    @Inject
    private EtudiantRepository etudiantRepository;

    @Inject
    private EtudiantMapper etudiantMapper;

    @Inject
    private EtudiantService etudiantService;

    @Inject
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Inject
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Inject
    private EntityManager em;

    private MockMvc restEtudiantMockMvc;

    private Etudiant etudiant;

    @PostConstruct
    public void setup() {
        MockitoAnnotations.initMocks(this);
        EtudiantResource etudiantResource = new EtudiantResource();
        ReflectionTestUtils.setField(etudiantResource, "etudiantService", etudiantService);
        this.restEtudiantMockMvc = MockMvcBuilders.standaloneSetup(etudiantResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Etudiant createEntity(EntityManager em) {
        Etudiant etudiant = new Etudiant()
                .etuName(DEFAULT_ETU_NAME)
                .etuPrenom(DEFAULT_ETU_PRENOM)
                .etuVille(DEFAULT_ETU_VILLE)
                .etuRue(DEFAULT_ETU_RUE)
                .etuCodeDep(DEFAULT_ETU_CODE_DEP)
                .etuPremiereInscription(DEFAULT_ETU_PREMIERE_INSCRIPTION)
                .etuAnneeCourante(DEFAULT_ETU_ANNEE_COURANTE)
                .etuMail(DEFAULT_ETU_MAIL);
        return etudiant;
    }

    @Before
    public void initTest() {
        etudiant = createEntity(em);
    }

    @Test
    @Transactional
    public void createEtudiant() throws Exception {
        int databaseSizeBeforeCreate = etudiantRepository.findAll().size();

        // Create the Etudiant
        EtudiantDTO etudiantDTO = etudiantMapper.etudiantToEtudiantDTO(etudiant);

        restEtudiantMockMvc.perform(post("/api/etudiants")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(etudiantDTO)))
                .andExpect(status().isCreated());

        // Validate the Etudiant in the database
        List<Etudiant> etudiants = etudiantRepository.findAll();
        assertThat(etudiants).hasSize(databaseSizeBeforeCreate + 1);
        Etudiant testEtudiant = etudiants.get(etudiants.size() - 1);
        assertThat(testEtudiant.getEtuName()).isEqualTo(DEFAULT_ETU_NAME);
        assertThat(testEtudiant.getEtuPrenom()).isEqualTo(DEFAULT_ETU_PRENOM);
        assertThat(testEtudiant.getEtuVille()).isEqualTo(DEFAULT_ETU_VILLE);
        assertThat(testEtudiant.getEtuRue()).isEqualTo(DEFAULT_ETU_RUE);
        assertThat(testEtudiant.getEtuCodeDep()).isEqualTo(DEFAULT_ETU_CODE_DEP);
        assertThat(testEtudiant.getEtuPremiereInscription()).isEqualTo(DEFAULT_ETU_PREMIERE_INSCRIPTION);
        assertThat(testEtudiant.getEtuAnneeCourante()).isEqualTo(DEFAULT_ETU_ANNEE_COURANTE);
        assertThat(testEtudiant.getEtuMail()).isEqualTo(DEFAULT_ETU_MAIL);
    }

    @Test
    @Transactional
    public void getAllEtudiants() throws Exception {
        // Initialize the database
        etudiantRepository.saveAndFlush(etudiant);

        // Get all the etudiants
        restEtudiantMockMvc.perform(get("/api/etudiants?sort=id,desc"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.[*].id").value(hasItem(etudiant.getId().intValue())))
                .andExpect(jsonPath("$.[*].etuName").value(hasItem(DEFAULT_ETU_NAME.toString())))
                .andExpect(jsonPath("$.[*].etuPrenom").value(hasItem(DEFAULT_ETU_PRENOM.toString())))
                .andExpect(jsonPath("$.[*].etuVille").value(hasItem(DEFAULT_ETU_VILLE.toString())))
                .andExpect(jsonPath("$.[*].etuRue").value(hasItem(DEFAULT_ETU_RUE.toString())))
                .andExpect(jsonPath("$.[*].etuCodeDep").value(hasItem(DEFAULT_ETU_CODE_DEP)))
                .andExpect(jsonPath("$.[*].etuPremiereInscription").value(hasItem(DEFAULT_ETU_PREMIERE_INSCRIPTION.toString())))
                .andExpect(jsonPath("$.[*].etuAnneeCourante").value(hasItem(DEFAULT_ETU_ANNEE_COURANTE.toString())))
                .andExpect(jsonPath("$.[*].etuMail").value(hasItem(DEFAULT_ETU_MAIL.toString())));
    }

    @Test
    @Transactional
    public void getEtudiant() throws Exception {
        // Initialize the database
        etudiantRepository.saveAndFlush(etudiant);

        // Get the etudiant
        restEtudiantMockMvc.perform(get("/api/etudiants/{id}", etudiant.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(etudiant.getId().intValue()))
            .andExpect(jsonPath("$.etuName").value(DEFAULT_ETU_NAME.toString()))
            .andExpect(jsonPath("$.etuPrenom").value(DEFAULT_ETU_PRENOM.toString()))
            .andExpect(jsonPath("$.etuVille").value(DEFAULT_ETU_VILLE.toString()))
            .andExpect(jsonPath("$.etuRue").value(DEFAULT_ETU_RUE.toString()))
            .andExpect(jsonPath("$.etuCodeDep").value(DEFAULT_ETU_CODE_DEP))
            .andExpect(jsonPath("$.etuPremiereInscription").value(DEFAULT_ETU_PREMIERE_INSCRIPTION.toString()))
            .andExpect(jsonPath("$.etuAnneeCourante").value(DEFAULT_ETU_ANNEE_COURANTE.toString()))
            .andExpect(jsonPath("$.etuMail").value(DEFAULT_ETU_MAIL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingEtudiant() throws Exception {
        // Get the etudiant
        restEtudiantMockMvc.perform(get("/api/etudiants/{id}", Long.MAX_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEtudiant() throws Exception {
        // Initialize the database
        etudiantRepository.saveAndFlush(etudiant);
        int databaseSizeBeforeUpdate = etudiantRepository.findAll().size();

        // Update the etudiant
        Etudiant updatedEtudiant = etudiantRepository.findOne(etudiant.getId());
        updatedEtudiant
                .etuName(UPDATED_ETU_NAME)
                .etuPrenom(UPDATED_ETU_PRENOM)
                .etuVille(UPDATED_ETU_VILLE)
                .etuRue(UPDATED_ETU_RUE)
                .etuCodeDep(UPDATED_ETU_CODE_DEP)
                .etuPremiereInscription(UPDATED_ETU_PREMIERE_INSCRIPTION)
                .etuAnneeCourante(UPDATED_ETU_ANNEE_COURANTE)
                .etuMail(UPDATED_ETU_MAIL);
        EtudiantDTO etudiantDTO = etudiantMapper.etudiantToEtudiantDTO(updatedEtudiant);

        restEtudiantMockMvc.perform(put("/api/etudiants")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(etudiantDTO)))
                .andExpect(status().isOk());

        // Validate the Etudiant in the database
        List<Etudiant> etudiants = etudiantRepository.findAll();
        assertThat(etudiants).hasSize(databaseSizeBeforeUpdate);
        Etudiant testEtudiant = etudiants.get(etudiants.size() - 1);
        assertThat(testEtudiant.getEtuName()).isEqualTo(UPDATED_ETU_NAME);
        assertThat(testEtudiant.getEtuPrenom()).isEqualTo(UPDATED_ETU_PRENOM);
        assertThat(testEtudiant.getEtuVille()).isEqualTo(UPDATED_ETU_VILLE);
        assertThat(testEtudiant.getEtuRue()).isEqualTo(UPDATED_ETU_RUE);
        assertThat(testEtudiant.getEtuCodeDep()).isEqualTo(UPDATED_ETU_CODE_DEP);
        assertThat(testEtudiant.getEtuPremiereInscription()).isEqualTo(UPDATED_ETU_PREMIERE_INSCRIPTION);
        assertThat(testEtudiant.getEtuAnneeCourante()).isEqualTo(UPDATED_ETU_ANNEE_COURANTE);
        assertThat(testEtudiant.getEtuMail()).isEqualTo(UPDATED_ETU_MAIL);
    }

    @Test
    @Transactional
    public void deleteEtudiant() throws Exception {
        // Initialize the database
        etudiantRepository.saveAndFlush(etudiant);
        int databaseSizeBeforeDelete = etudiantRepository.findAll().size();

        // Get the etudiant
        restEtudiantMockMvc.perform(delete("/api/etudiants/{id}", etudiant.getId())
                .accept(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());

        // Validate the database is empty
        List<Etudiant> etudiants = etudiantRepository.findAll();
        assertThat(etudiants).hasSize(databaseSizeBeforeDelete - 1);
    }
}
