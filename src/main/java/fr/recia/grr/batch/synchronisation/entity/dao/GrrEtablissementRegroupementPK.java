package fr.recia.grr.batch.synchronisation.entity.dao;

import java.io.Serializable;
import java.util.Objects;

public class GrrEtablissementRegroupementPK implements Serializable {

    private String code_etablissement_principal;
    private String code_etablissement_secondaire;

    public GrrEtablissementRegroupementPK() {}

    public GrrEtablissementRegroupementPK(String code_etablissement_principal, String code_etablissement_secondaire) {
        this.code_etablissement_principal = code_etablissement_principal;
        this.code_etablissement_secondaire = code_etablissement_secondaire;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }
        if (!(o instanceof GrrEtablissementRegroupement)) {
            return false;
        }
        GrrEtablissementRegroupement assignedRole = (GrrEtablissementRegroupement) o;
        return Objects.equals(code_etablissement_principal, assignedRole.getCode_etablissement_principal()) &&
                Objects.equals(code_etablissement_secondaire, assignedRole.getCode_etablissement_secondaire());
    }

    @Override
    public int hashCode() {
        return Objects.hash(code_etablissement_principal, code_etablissement_secondaire);
    }
}