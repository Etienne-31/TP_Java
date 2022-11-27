package fr.insa.projet.clavardage.models;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner; // import the Scanner class 


import fr.insa.projet.clavardage.controlers.NetworkManager;
import fr.insa.projet.clavardage.models.Message;
import fr.insa.projet.clavardage.models.Utilisateur;

public class TestTCP {
    private BufferedReader is = null;
    private BufferedWriter os = null;
    private Socket socket = null;
    private ServerSocket socketInit = null;
    private Utilisateur user = null;
    private Utilisateur other_user = null;
     // mode false pour client et true pour serveur 
    private boolean mode;
    private NetworkManager networkManagement = null;

    public TestTCP(Utilisateur user,Utilisateur other_user,boolean mode,int port,String adress) {
        this.networkManagement = new NetworkManager();
        this.user = user;
        this.other_user = other_user;
        this.mode = mode;
        try {
            if(mode == false ){    
               
                    this.socket = this.networkManagement.init_socketClientTCP(adress, port, this.socket);
                    System.out.println("Connected !");
            } 
            else{
                
                this.socketInit = this.networkManagement.init_socketServerTCP(port, this.socketInit);
                this.socket = this.networkManagement.wait_connexionTCP(this.socket, this.socketInit);
            }
            this.os = this.networkManagement.init_bufferEmissionTCP(this.socket);
            this.is = this.networkManagement.init_bufferReceptionTCP(this.socket);     
        }
        catch (IOException e){
            e.printStackTrace();
        } 
        System.out.println("---------------------------- Command ---------------------------- ");
        System.out.println("Please press Enter to update the chat ");
        System.out.println("Please press Enter to send your message after you wrote it  ");
        System.out.println("---------------------------- Starting the conversation---------------------------- ");
        System.out.println("Please Enter a message to start the chat  ");
    }



    public void run(){
        boolean endConversation = false;
        String data;
        while(endConversation == false){
            receiveMessage();
            data = sendMessage();
            if(data == "EXIT"){
                endConversation = true;
            }
            

        }
        try {
            this.networkManagement.close_connexion(this.os, this.is);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public void run2(){
        if(this.mode==false){
            System.out.println("Vous êtes le client ");
            this.networkManagement.send("Echo client", this.os);
            Message message = new Message(this.user, this.other_user, "Echo client");
            System.out.println(message.toString());
            receiveMessage();

        }
        else{
            System.out.println("Vous êtes le Serveur");
            this.networkManagement.send("Echo Serveur", this.os);
            Message message = new Message(this.user, this.other_user, "Echo Serveur");
            System.out.println(message.toString());
            receiveMessage();

        }
        try {
            this.networkManagement.close_connexion(this.os, this.is);
            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveMessage(){
        try {
            Message message = this.networkManagement.receiveMessage(this.is, this.other_user, this.user);
            System.out.println(message.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String sendMessage(){
        String data = getUserInput();
        if(data != ""){
            Message message = new Message(this.user, this.other_user, data);
            this.networkManagement.send(data, this.os);
            System.out.println(message.toString());
        }
        return data;
    }

    private String getUserInput(){
        Scanner newMessage = new Scanner(System.in);
        String data = null;
        data = newMessage.nextLine();
        newMessage.close();
        return data;
    }
    


    
}
