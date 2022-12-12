import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;


public class Serveur_TCP {
    public ServerSocket ServSocket;
    public Socket socketOfServeur;
    public BufferedReader is;
    public BufferedWriter os;

    public Serveur_TCP(){
        this.ServSocket = null;
        this.socketOfServeur = null;
        this.is = null;
        this.os = null;


    }

    public void init_serveur(int port) throws  IOException{
        try{
            this.ServSocket = new ServerSocket(1234);
        }
        catch (UnknownHostException e){
            System.out.println("On a pas pu trouver l'adresse");
            System.exit(1);
        }


    }

    public void wait_connexion() throws IOException {

        try{
            System.out.println("En attente d'un client");
            this.socketOfServeur = this.ServSocket.accept();
            System.out.println("Client accepté");
        }
        catch(IOException e){
            System.out.println("Serveur TCP :"+e);
        }
    }

    public void init_listening(){
        try{
            this.is = new BufferedReader(new InputStreamReader(this.socketOfServeur.getInputStream()));
        }
        catch(IOException e){
            System.out.println("Serveur TCP init_listening:"+e);
        }

    }
    public String listening() throws IOException {
        String retour=null;
        try{
             retour = this.is.readLine();

        }
        catch(IOException e){
            System.out.println("Serveur TCP listening:"+e);
        }
        return retour;

    }

    public void init_send(){
        try{
            this.os = new BufferedWriter(new OutputStreamWriter(this.socketOfServeur.getOutputStream()));
        }
        catch(IOException e){
            System.out.println("Serveur TCP init_send:"+e);
        }

    }

    public void send(String message){
        try{
            this.os.write(message);
            this.os.newLine();
            this.os.flush();

        }
        catch(IOException e){
            System.out.println("Serveur TCP send:"+e);
        }

    }

    public void close_connexion() throws IOException {

        try{
            if(this.is != null){
                this.is.close();
            }
            if(this.os != null){
                this.os.close();
            }

        }
        catch(IOException e){
            System.out.println("Serveur TCP send:"+e);
        }
        System.out.println("Connexion fermée");

    }

}

