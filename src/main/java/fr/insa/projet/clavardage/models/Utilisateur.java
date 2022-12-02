package fr.insa.projet.clavardage.models;

import java.net.*;
import java.io.*;


public class Utilisateur {
	
	private String idUser;
	private String password;
	public String userPseudo;
	private InetAddress ipUser;
	
	public Utilisateur(String idOtherUser,String pseudoOtherUser)throws UnknownHostException{
		this.password = null;
		this.ipUser = InetAddress.getLocalHost();
		this.idUser = idOtherUser;
		this.userPseudo = pseudoOtherUser;

	}
	
	public Utilisateur(String idUser,String password,String pseudo){
		
		try {
			this.idUser = idUser;
			this.password = password;
			this.userPseudo = pseudo;
			this.ipUser = InetAddress.getLocalHost();	
		}
		catch(UnknownHostException e){
			System.out.println("Erreur venant du constructeur Utilisateur"+e);
			
		}
	}
	
	public String getIdUser() {
		return this.idUser;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public InetAddress getUserIpAdress() {
		return this.ipUser;
	}
	
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	
	public void setPseudo(String newPseudo) {
		this.userPseudo = newPseudo;
	}
	

}
