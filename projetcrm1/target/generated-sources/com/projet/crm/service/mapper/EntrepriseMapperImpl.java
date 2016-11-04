package com.projet.crm.service.mapper;

import com.projet.crm.domain.Entreprise;
import com.projet.crm.service.dto.EntrepriseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_102 (Oracle Corporation)"
)
@Component
public class EntrepriseMapperImpl implements EntrepriseMapper {

    @Override
    public EntrepriseDTO entrepriseToEntrepriseDTO(Entreprise entreprise) {
        if ( entreprise == null ) {
            return null;
        }

        EntrepriseDTO entrepriseDTO = new EntrepriseDTO();

        entrepriseDTO.setId( entreprise.getId() );
        entrepriseDTO.setEntLibele( entreprise.getEntLibele() );
        entrepriseDTO.setEntVille( entreprise.getEntVille() );
        entrepriseDTO.setEntRue( entreprise.getEntRue() );
        entrepriseDTO.setEntCodeDep( entreprise.getEntCodeDep() );

        return entrepriseDTO;
    }

    @Override
    public List<EntrepriseDTO> entreprisesToEntrepriseDTOs(List<Entreprise> entreprises) {
        if ( entreprises == null ) {
            return null;
        }

        List<EntrepriseDTO> list = new ArrayList<EntrepriseDTO>();
        for ( Entreprise entreprise : entreprises ) {
            list.add( entrepriseToEntrepriseDTO( entreprise ) );
        }

        return list;
    }

    @Override
    public Entreprise entrepriseDTOToEntreprise(EntrepriseDTO entrepriseDTO) {
        if ( entrepriseDTO == null ) {
            return null;
        }

        Entreprise entreprise = new Entreprise();

        entreprise.setId( entrepriseDTO.getId() );
        entreprise.setEntLibele( entrepriseDTO.getEntLibele() );
        entreprise.setEntVille( entrepriseDTO.getEntVille() );
        entreprise.setEntRue( entrepriseDTO.getEntRue() );
        entreprise.setEntCodeDep( entrepriseDTO.getEntCodeDep() );

        return entreprise;
    }

    @Override
    public List<Entreprise> entrepriseDTOsToEntreprises(List<EntrepriseDTO> entrepriseDTOs) {
        if ( entrepriseDTOs == null ) {
            return null;
        }

        List<Entreprise> list = new ArrayList<Entreprise>();
        for ( EntrepriseDTO entrepriseDTO : entrepriseDTOs ) {
            list.add( entrepriseDTOToEntreprise( entrepriseDTO ) );
        }

        return list;
    }
}
