package fr.recia.grr.batch.migration.mapper;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrAreaPeriode;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@FunctionalInterface
@Mapper
public interface GrrAreaPeriodeMapper {
    GrrAreaPeriodeMapper INSTANCE = Mappers.getMapper(GrrAreaPeriodeMapper.class);

    @Mapping(target = "id_area", ignore = true)
    @Mapping(target = "id", ignore = true)
    GrrAreaPeriode areaPeriodeToGrrareaPeriodeMulti(fr.recia.grr.batch.migration.entity.GrrAreaPeriode area);
}
