package com.projet.crm.service.mapper;

import com.projet.crm.domain.Etudiant;
import com.projet.crm.service.dto.EtudiantDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_102 (Oracle Corporation)"
)
@Component
public class EtudiantMapperImpl implements EtudiantMapper {

    @Override
    public EtudiantDTO etudiantToEtudiantDTO(Etudiant etudiant) {
        if ( etudiant == null ) {
            return null;
        }

        EtudiantDTO etudiantDTO = new EtudiantDTO();

        etudiantDTO.setId( etudiant.getId() );
        etudiantDTO.setEtuName( etudiant.getEtuName() );
        etudiantDTO.setEtuPrenom( etudiant.getEtuPrenom() );
        etudiantDTO.setEtuVille( etudiant.getEtuVille() );
        etudiantDTO.setEtuRue( etudiant.getEtuRue() );
        etudiantDTO.setEtuCodeDep( etudiant.getEtuCodeDep() );
        etudiantDTO.setEtuPremiereInscription( etudiant.getEtuPremiereInscription() );
        etudiantDTO.setEtuAnneeCourante( etudiant.getEtuAnneeCourante() );
        etudiantDTO.setEtuMail( etudiant.getEtuMail() );

        return etudiantDTO;
    }

    @Override
    public List<EtudiantDTO> etudiantsToEtudiantDTOs(List<Etudiant> etudiants) {
        if ( etudiants == null ) {
            return null;
        }

        List<EtudiantDTO> list = new ArrayList<EtudiantDTO>();
        for ( Etudiant etudiant : etudiants ) {
            list.add( etudiantToEtudiantDTO( etudiant ) );
        }

        return list;
    }

    @Override
    public Etudiant etudiantDTOToEtudiant(EtudiantDTO etudiantDTO) {
        if ( etudiantDTO == null ) {
            return null;
        }

        Etudiant etudiant = new Etudiant();

        etudiant.setId( etudiantDTO.getId() );
        etudiant.setEtuName( etudiantDTO.getEtuName() );
        etudiant.setEtuPrenom( etudiantDTO.getEtuPrenom() );
        etudiant.setEtuVille( etudiantDTO.getEtuVille() );
        etudiant.setEtuRue( etudiantDTO.getEtuRue() );
        etudiant.setEtuCodeDep( etudiantDTO.getEtuCodeDep() );
        etudiant.setEtuPremiereInscription( etudiantDTO.getEtuPremiereInscription() );
        etudiant.setEtuAnneeCourante( etudiantDTO.getEtuAnneeCourante() );
        etudiant.setEtuMail( etudiantDTO.getEtuMail() );

        return etudiant;
    }

    @Override
    public List<Etudiant> etudiantDTOsToEtudiants(List<EtudiantDTO> etudiantDTOs) {
        if ( etudiantDTOs == null ) {
            return null;
        }

        List<Etudiant> list = new ArrayList<Etudiant>();
        for ( EtudiantDTO etudiantDTO : etudiantDTOs ) {
            list.add( etudiantDTOToEtudiant( etudiantDTO ) );
        }

        return list;
    }
}
