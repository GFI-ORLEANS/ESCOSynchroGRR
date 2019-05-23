/*
 * Projet ENT-CRLR - Conseil Regional Languedoc Roussillon.
 * Copyright (c) 2009 Bull SAS
 *
 * $Id: ODMPersonne.java,v 1.2 2009/09/16 08:46:39 weberent Exp $
 */

package fr.recia.grr.batch.synchronisation.entity.ldap;


import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.util.List;


/**
 * Object directory mapping d'une presonne provenant du ldap
 */
@Entry(objectClasses = {"ENTPerson"})
public final class ODMPersonne {

	/*
	 * ===============================================
	 * Propriétés de la classe
	 * ===============================================
	 */

	@Id
	private Name dn;

	@Attribute(name = "uid")
	private String uid;

	@Attribute(name ="sn" )
    private String nom;

	@Attribute(name = "givenName")
	private String prenom;

	@Attribute(name = "mail")
	private String email;

	@Attribute(name = "isMemberOf")
	private List<String> isMemberOf;

	@Attribute(name = "ESCOUAIRattachement")
	private String defaultEtablissement;

	@Attribute(name = "ESCOUAICourant")
	private String defaultEtablissement2;

	/*
	 * ===============================================
	 * Constructeurs de la classe
	 * ===============================================
	 */

    public ODMPersonne() {
    }

	public ODMPersonne(String login, String nom) {
		this();
 		this.nom=nom;
	}

	/*
	 * ===============================================
	 * Getter / Setter de la classe
	 * ===============================================
	 */

	/**
	 * Getter de la proprieté nom
	 * @return la proprieté nom
	 */
	public String getNom() {
		return nom;
	}


	/**
	 * Setter de la proprieté nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}


	/**
	 * Getter de la proprieté prenom
	 * @return la proprieté prenom
	 */
	public String getPrenom() {
		return prenom;
	}


	/**
	 * Setter de la proprieté prenom
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getIsMemberOf() {
		return isMemberOf;
	}

	public void setIsMemberOf(List<String> isMemberOf) {
		this.isMemberOf = isMemberOf;
	}

	public String getDefaultEtablissement() {
		return defaultEtablissement;
	}

	public void setDefaultEtablissement(String defaultEtablissement) {
		this.defaultEtablissement = defaultEtablissement;
	}
	/*
	 * ===============================================
	 * toString de la classe
	 * ===============================================
	 */

	@Override
	public String toString() {
		return "ODMPersonne{" +
				"dn=" + dn +
 				", nom='" + nom + '\'' +
				", prenom='" + prenom + '\'' +
				'}';
	}

	public String getDefaultEtablissement2() {
		return defaultEtablissement2;
	}

	public void setDefaultEtablissement2(String defaultEtablissement2) {
		this.defaultEtablissement2 = defaultEtablissement2;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
}
