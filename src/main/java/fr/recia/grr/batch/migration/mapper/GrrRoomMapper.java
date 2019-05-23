package fr.recia.grr.batch.migration.mapper;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrRoom;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@FunctionalInterface
@Mapper
public interface GrrRoomMapper {
    GrrRoomMapper INSTANCE = Mappers.getMapper(GrrRoomMapper.class);

    @Mapping(target = "area_id", ignore = true)
    GrrRoom roomToGrrRoomMulti(fr.recia.grr.batch.migration.entity.GrrRoom area);
}
