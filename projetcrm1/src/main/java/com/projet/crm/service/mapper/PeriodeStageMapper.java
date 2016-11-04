package com.projet.crm.service.mapper;

import com.projet.crm.domain.*;
import com.projet.crm.service.dto.PeriodeStageDTO;

import org.mapstruct.*;
import java.util.List;

/**
 * Mapper for the entity PeriodeStage and its DTO PeriodeStageDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PeriodeStageMapper {

    PeriodeStageDTO periodeStageToPeriodeStageDTO(PeriodeStage periodeStage);

    List<PeriodeStageDTO> periodeStagesToPeriodeStageDTOs(List<PeriodeStage> periodeStages);

    @Mapping(target = "stages", ignore = true)
    PeriodeStage periodeStageDTOToPeriodeStage(PeriodeStageDTO periodeStageDTO);

    List<PeriodeStage> periodeStageDTOsToPeriodeStages(List<PeriodeStageDTO> periodeStageDTOs);
}
