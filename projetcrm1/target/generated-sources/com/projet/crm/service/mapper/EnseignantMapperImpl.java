package com.projet.crm.service.mapper;

import com.projet.crm.domain.Enseignant;
import com.projet.crm.service.dto.EnseignantDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_102 (Oracle Corporation)"
)
@Component
public class EnseignantMapperImpl implements EnseignantMapper {

    @Override
    public EnseignantDTO enseignantToEnseignantDTO(Enseignant enseignant) {
        if ( enseignant == null ) {
            return null;
        }

        EnseignantDTO enseignantDTO = new EnseignantDTO();

        enseignantDTO.setId( enseignant.getId() );
        enseignantDTO.setEnsName( enseignant.getEnsName() );
        enseignantDTO.setEnsPrenom( enseignant.getEnsPrenom() );
        enseignantDTO.setEnsVille( enseignant.getEnsVille() );
        enseignantDTO.setEnsRue( enseignant.getEnsRue() );
        enseignantDTO.setEnsCodeDep( enseignant.getEnsCodeDep() );
        enseignantDTO.setEnsTel( enseignant.getEnsTel() );
        enseignantDTO.setEnsMail( enseignant.getEnsMail() );

        return enseignantDTO;
    }

    @Override
    public List<EnseignantDTO> enseignantsToEnseignantDTOs(List<Enseignant> enseignants) {
        if ( enseignants == null ) {
            return null;
        }

        List<EnseignantDTO> list = new ArrayList<EnseignantDTO>();
        for ( Enseignant enseignant : enseignants ) {
            list.add( enseignantToEnseignantDTO( enseignant ) );
        }

        return list;
    }

    @Override
    public Enseignant enseignantDTOToEnseignant(EnseignantDTO enseignantDTO) {
        if ( enseignantDTO == null ) {
            return null;
        }

        Enseignant enseignant = new Enseignant();

        enseignant.setId( enseignantDTO.getId() );
        enseignant.setEnsName( enseignantDTO.getEnsName() );
        enseignant.setEnsPrenom( enseignantDTO.getEnsPrenom() );
        enseignant.setEnsVille( enseignantDTO.getEnsVille() );
        enseignant.setEnsRue( enseignantDTO.getEnsRue() );
        enseignant.setEnsCodeDep( enseignantDTO.getEnsCodeDep() );
        enseignant.setEnsTel( enseignantDTO.getEnsTel() );
        enseignant.setEnsMail( enseignantDTO.getEnsMail() );

        return enseignant;
    }

    @Override
    public List<Enseignant> enseignantDTOsToEnseignants(List<EnseignantDTO> enseignantDTOs) {
        if ( enseignantDTOs == null ) {
            return null;
        }

        List<Enseignant> list = new ArrayList<Enseignant>();
        for ( EnseignantDTO enseignantDTO : enseignantDTOs ) {
            list.add( enseignantDTOToEnseignant( enseignantDTO ) );
        }

        return list;
    }
}
