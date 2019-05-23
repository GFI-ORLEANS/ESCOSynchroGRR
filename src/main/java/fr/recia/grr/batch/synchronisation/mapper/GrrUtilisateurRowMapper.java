package fr.recia.grr.batch.synchronisation.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GrrUtilisateurRowMapper implements org.springframework.jdbc.core.RowMapper<String> {

    private static final String ID_COLUMN = "login";

    @Override
    public String mapRow(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getString(ID_COLUMN);
    }
}
