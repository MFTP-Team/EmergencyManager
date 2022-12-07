package com.cpe.emergencymanager.mapper;

import com.cpe.emergencymanager.dto.SimpleEntityDTO;
import com.cpe.emergencymanager.model.SimpleEntity;
import org.mapstruct.Mapper;

@Mapper
public interface SimpleSourceDestinationMapper {
    SimpleEntityDTO simpleEntityToSimpleEntityDTO(SimpleEntity simpleEntity);
}
