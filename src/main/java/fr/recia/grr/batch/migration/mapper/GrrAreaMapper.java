package fr.recia.grr.batch.migration.mapper;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrArea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@FunctionalInterface
@Mapper(uses = {  GrrTypeAreaMapper.class, GrrRoomMapper.class, GrrOverloadMapper.class, GrrAreaPeriodeMapper.class })
public interface GrrAreaMapper {
    GrrAreaMapper INSTANCE = Mappers.getMapper(GrrAreaMapper.class);



    @Mapping(target = "grr_j_area_site", ignore = true)
    GrrArea areaToGrrSiteMulti(fr.recia.grr.batch.migration.entity.GrrArea area);
}
