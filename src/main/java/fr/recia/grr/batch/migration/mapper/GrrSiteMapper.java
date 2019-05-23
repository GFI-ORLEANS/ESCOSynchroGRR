package fr.recia.grr.batch.migration.mapper;

import fr.recia.grr.batch.synchronisation.entity.dao.GrrSite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@FunctionalInterface
@Mapper(uses = { GrrAreaMapper.class, GrrTypeAreaMapper.class, GrrRoomMapper.class })
public interface GrrSiteMapper {
    GrrSiteMapper INSTANCE = Mappers.getMapper(GrrSiteMapper.class);

    @Mapping(target = "grr_j_site_useradmin", ignore = true)
    @Mapping(target = "grr_j_site_etablissement", ignore = true)
    GrrSite areaToGrrSiteMulti(fr.recia.grr.batch.migration.entity.GrrSite area);
}
