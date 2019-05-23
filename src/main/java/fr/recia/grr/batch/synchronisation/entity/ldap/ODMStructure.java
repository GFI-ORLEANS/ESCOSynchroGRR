/*
 * Projet ENT-CRLR - Conseil Regional Languedoc Roussillon.
 * Copyright (c) 2009 Bull SAS
 *
 * $Id: StructureDTO.java,v 1.2 2009/09/16 08:46:39 weberent Exp $
 */

package fr.recia.grr.batch.synchronisation.entity.ldap;


import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;


/**
 *  Object directory mapping d'une structure provenant du ldap
 */
@Entry(objectClasses = {"ENTStructure"} )
public class ODMStructure {

	/*
	 * ===============================================
	 * Propriétés de la classe
	 * ===============================================
	 */

    @Id
	private Name dn;

	@Attribute(name = "ENTStructureUAI")
    private String code;

	@Attribute(name = "ENTStructureNomCourant" )
    private String nomCourt;

	@Attribute(name = "ou" )
    private String nomLong;

	@Attribute(name = "postalCode" )
    private String codePostal;

	@Attribute(name = "l" )
    private String ville;

	@Attribute(name = "street")
	private String adresse;

	@Attribute(name = "ENTStructureUAI")
	private String sitecode;

	@Attribute(name = "ENTStructureNomCourant")
	private String sitename;



	/*
	 * ===============================================
	 * Constructeurs de la classe
	 * ===============================================
	 */

	public ODMStructure() {
	}

	public ODMStructure(String code, String nomCourt) {
		this();
		this.code = code;
		this.nomCourt = nomCourt;
	}

	/*
	 * ===============================================
	 * Getter / Setter de la classe
	 * ===============================================
	 */

	/**
	 * Getter de la proprieté Dn
	 * @return la proprieté dn
	 */
	public Name getDn() {
		return dn;
	}

	/**
	 * Setter de la proprieté Dn
	 * @param dn
	 */
	public void setDn(Name dn) {
		this.dn = dn;
	}


	/**
	 * Getter de la proprieté code
	 * @return la proprieté code
	 */
	public String getCode() {
		if(code==null){
			return this.nomLong;
		}
		return code;
	}


	/**
	 * Setter de la proprieté code
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}


	/**
	 * Getter de la proprieté nomCourt
	 * @return la proprieté nomCourt
	 */
	public String getNomCourt() {
		return nomCourt;
	}


	/**
	 * Setter de la proprieté nomCourt
	 * @param nomCourt
	 */
	public void setNomCourt(String nomCourt) {
		this.nomCourt = nomCourt;
	}


	/**
	 * Getter de la proprieté nomLong
	 * @return la proprieté nomLong
	 */
	public String getNomLong() {
		return nomLong;
	}


	/**
	 * Setter de la proprieté nomLong
	 * @param nomLong
	 */
	public void setNomLong(String nomLong) {
		this.nomLong = nomLong;
	}


	/**
	 * Getter de la proprieté codePostal
	 * @return la proprieté codePostal
	 */
	public String getCodePostal() {
		return codePostal;
	}


	/**
	 * Setter de la proprieté codePostal
	 * @param codePostal
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}


	/**
	 * Getter de la proprieté ville
	 * @return la proprieté ville
	 */
	public String getVille() {
		return ville;
	}


	/**
	 * Setter de la proprieté ville
	 * @param ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}


	/**
	 * Getter de la proprieté adresse
	 * @return la proprieté adresse
	 */
	public String getAdresse() {
		return adresse;
	}


	/**
	 * Setter de la proprieté adresse
	 * @param adresse
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getSitecode() {
		return sitecode;
	}

	public void setSitecode(String sitecode) {
		this.sitecode = sitecode;
	}

	/*
	 * ===============================================
	 * toString de la classe
	 * ===============================================
	 */

	@Override
	public String toString() {
		return "ODMStructure{" +
				"dn=" + dn +
				", code='" + code + '\'' +
				", nomCourt='" + nomCourt + '\'' +
				", nomLong='" + nomLong + '\'' +
				", codePostal='" + codePostal + '\'' +
				", ville='" + ville + '\'' +
				", adresse='" + adresse + '\'' +
				", sitecode='" + sitecode + '\'' +
				", sitename='" + sitename + '\'' +
				'}';
	}


}
