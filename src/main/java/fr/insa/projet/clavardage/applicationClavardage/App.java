package fr.insa.projet.clavardage.applicationClavardage;

//

//import fr.insa.projet.clavardage.models.Message;
//import fr.insa.projet.clavardage.models.Utilisateur;
import java.util.Scanner; // import the Scanner class 

import fr.insa.projet.clavardage.models.TestTCP;
import fr.insa.projet.clavardage.models.Utilisateur;
public class App {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    String id_User;
    String password = "123";
    String pseudo;
    Utilisateur user;

    String id_OtherUser = " OtherUser";
    String pseudoOtherUser = "Tarto";
    Utilisateur Otheruser;
    Boolean mode;
    String response = "";
    String adress;
    int port;

    TestTCP conv;

    System.out.println("V10");
    System.out.println("Quelle est votre idUser ?");
    id_User = scan.nextLine();

    System.out.println("Quelle est votre Pseudo ?");
    pseudo = scan.nextLine();

    user = new Utilisateur(id_User, password, pseudo);
    Otheruser = new Utilisateur(id_OtherUser, pseudoOtherUser);

    System.out.println("Voulez vous lancez un serveur ou être client et vous connecter ? : S pour serveur / C pour client ");
    response = scan.nextLine();
    
    while(!(response.equals("S")|response.equals("C"))){

      System.out.println("Vous n'avez pas répondu ");
      System.out.println("Voulez vous lancez un serveur ou être client et vous connecter ? : S pour serveur / C pour client ");

      response = scan.nextLine();
      
    }

    if(response.equals("C")){
      System.out.println("A quelle adresse vous voulez vous , vous connecter ?");
     // adress = scan.nextLine();
     adress = "localhost";
     System.out.println("A quelle Port vous voulez vous , vous connecter ?");
     //port = Integer.parseInt(scan.nextLine());
     port = 1234;
     mode = false;

    }
    else{
      System.out.println("Sur quel port lancer le serveur  ?");
      //port = Integer.parseInt(scan.nextLine());
      port = 1234;
      adress = "";
      mode = true;
    }

    conv = new TestTCP(user, Otheruser, mode, port, adress);
    System.out.println("On lance l app");
    conv.run2();


    
    
    
    
    
    
  }
  


}

