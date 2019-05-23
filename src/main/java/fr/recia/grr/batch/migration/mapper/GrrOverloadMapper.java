package fr.recia.grr.batch.migration.mapper;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrOverload;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@FunctionalInterface
@Mapper
public interface GrrOverloadMapper {
    GrrOverloadMapper INSTANCE = Mappers.getMapper(GrrOverloadMapper.class);

    @Mapping(target = "id_area", ignore = true)
    GrrOverload roomToGrrRoomMulti(fr.recia.grr.batch.migration.entity.GrrOverload area);
}
