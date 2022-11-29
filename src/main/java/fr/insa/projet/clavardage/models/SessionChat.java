package fr.insa.projet.clavardage.models;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner; // import the Scanner class 
import fr.insa.projet.clavardage.controlers.NetworkManager;
import java.util.LinkedList;

public class SessionChat extends Thread {

    private boolean mode;
    private ThreadGroup gestionConversation;
    private Socket socket = null;
    private ServerSocket socketInit = null;
    private NetworkManager networkManagement = null;
    private Utilisateur user = null;
    private Utilisateur other_user = null;

    public SessionChat(Utilisateur user,Utilisateur other_user,boolean mode,String adress,int port){
        super();
        this.networkManagement = new NetworkManager();
        this.user = user;
        this.other_user = other_user;
        this.mode = mode;
        this.gestionConversation = new ThreadGroup("gestionDelaConversation");

        try {
            if(mode == false ){    
               
                    this.socket = this.networkManagement.init_socketClientTCP(adress, port, this.socket);
                    System.out.println("Connected !");
            } 
            else{
                
                this.socketInit = this.networkManagement.init_socketServerTCP(port, this.socketInit);
                this.socket = this.networkManagement.wait_connexionTCP(this.socket, this.socketInit);
            }
 
        }
        catch (IOException e){
            e.printStackTrace();
        }
           

    }
    
    public void run(){
        GestionReception gestion = new GestionReception("reception",socket, user, other_user,this.getThreadGroup());
        gestion.run();
        try {
            gestion.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("La conversation avec "+this.other_user.getIdUser()+" est terminée");
        
    }
    public boolean getMode(){return this.mode;}

    class GestionReception extends Thread{

        private BufferedReader is = null;
        private Socket socket = null;
        private NetworkManager networkManagement = null;
        private Utilisateur sender;
        private Utilisateur receiver;

        public GestionReception(String name,Socket socket,Utilisateur sender,Utilisateur receiver,ThreadGroup group){
            super(group,name);
            this.socket = socket;
            this.is = this.networkManagement.init_bufferReceptionTCP(this.socket);
            this.sender = sender;
            this.receiver = receiver;
        }
        @Override
        public void run(){
            Message message = null;
            boolean quit = false;
            
            while(quit == false){
                try {
                    message = receptionMessage();
                } catch (IOException e) {
                   e.printStackTrace();
                }

                if(message != null){
                    this.networkManagement.printMessage(message);
                }
                if(message.getData().equals("Exit")){
                    quit = true;
                }


            }
            if(this.is != null){

                try {
                    System.out.println("Deconnexion du buffer de reception...");
                    this.is.close();
                    System.out.println("buffer de reception Deconnecté");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
        }
        
        private Message receptionMessage() throws IOException{
            Message data = null;
            data = networkManagement.receiveMessage(this.is, this.sender, this.receiver);
            return data;
        }

        
    }
    
}
