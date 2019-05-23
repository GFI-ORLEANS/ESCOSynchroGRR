package fr.recia.grr.batch.migration.mapper;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrTypeArea;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@FunctionalInterface
@Mapper
public interface GrrTypeAreaMapper {
    GrrTypeAreaMapper INSTANCE = Mappers.getMapper(GrrTypeAreaMapper.class);



    @Mapping(target = "grr_j_type_area_etablissement", ignore = true)
    @Mapping(target = "grr_j_type_area", ignore = true)
    GrrTypeArea areaToGrrSiteMulti(fr.recia.grr.batch.migration.entity.GrrTypeArea area);
}
