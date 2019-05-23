package fr.recia.grr.batch.synchronisation.entity.dao;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(GrrEtablissementRegroupementPK.class)
@Table(name = "grr_etablissement_regroupement")
public class GrrEtablissementRegroupement implements Serializable {

    @Id
    @Column(name = "code_etablissement_principal", nullable = false)
    private String code_etablissement_principal;

    @Id
    @Column(name = "code_etablissement_secondaire",nullable = false)
    private String code_etablissement_secondaire;


    public GrrEtablissementRegroupement() {

    }

    public GrrEtablissementRegroupement(String code_etablissement_principal, String code_etablissement_secondaire) {
        this.code_etablissement_principal = code_etablissement_principal;
        this.code_etablissement_secondaire = code_etablissement_secondaire;
    }

    public String getCode_etablissement_principal() {
        return code_etablissement_principal;
    }

    public void setCode_etablissement_principal(String code_etablissement_principal) {
        this.code_etablissement_principal = code_etablissement_principal;
    }

    public String getCode_etablissement_secondaire() {
        return code_etablissement_secondaire;
    }

    public void setCode_etablissement_secondaire(String code_etablissement_secondaire) {
        this.code_etablissement_secondaire = code_etablissement_secondaire;
    }
}
