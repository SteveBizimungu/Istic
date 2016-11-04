package com.projet.crm.service.mapper;

import com.projet.crm.domain.PeriodeStage;
import com.projet.crm.service.dto.PeriodeStageDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_102 (Oracle Corporation)"
)
@Component
public class PeriodeStageMapperImpl implements PeriodeStageMapper {

    @Override
    public PeriodeStageDTO periodeStageToPeriodeStageDTO(PeriodeStage periodeStage) {
        if ( periodeStage == null ) {
            return null;
        }

        PeriodeStageDTO periodeStageDTO = new PeriodeStageDTO();

        periodeStageDTO.setId( periodeStage.getId() );
        periodeStageDTO.setPerDebut( periodeStage.getPerDebut() );
        periodeStageDTO.setPerFin( periodeStage.getPerFin() );

        return periodeStageDTO;
    }

    @Override
    public List<PeriodeStageDTO> periodeStagesToPeriodeStageDTOs(List<PeriodeStage> periodeStages) {
        if ( periodeStages == null ) {
            return null;
        }

        List<PeriodeStageDTO> list = new ArrayList<PeriodeStageDTO>();
        for ( PeriodeStage periodeStage : periodeStages ) {
            list.add( periodeStageToPeriodeStageDTO( periodeStage ) );
        }

        return list;
    }

    @Override
    public PeriodeStage periodeStageDTOToPeriodeStage(PeriodeStageDTO periodeStageDTO) {
        if ( periodeStageDTO == null ) {
            return null;
        }

        PeriodeStage periodeStage = new PeriodeStage();

        periodeStage.setId( periodeStageDTO.getId() );
        periodeStage.setPerDebut( periodeStageDTO.getPerDebut() );
        periodeStage.setPerFin( periodeStageDTO.getPerFin() );

        return periodeStage;
    }

    @Override
    public List<PeriodeStage> periodeStageDTOsToPeriodeStages(List<PeriodeStageDTO> periodeStageDTOs) {
        if ( periodeStageDTOs == null ) {
            return null;
        }

        List<PeriodeStage> list = new ArrayList<PeriodeStage>();
        for ( PeriodeStageDTO periodeStageDTO : periodeStageDTOs ) {
            list.add( periodeStageDTOToPeriodeStage( periodeStageDTO ) );
        }

        return list;
    }
}
