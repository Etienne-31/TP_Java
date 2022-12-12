import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client_TCP {

    public String serveurHost;
    public Socket socketOfClient;
    public BufferedReader is;
    public BufferedWriter os;

    public Client_TCP() {
        this.serveurHost = null;
        this.socketOfClient = null;
        this.is = null;
        this.os = null;
    }

    public void init_Connexion(String serveurHost, int port) throws IOException {
        this.serveurHost = serveurHost;
        try {
            socketOfClient = new Socket(this.serveurHost, port);
        } catch (UnknownHostException e) {
            System.out.println("On ne sait rien de cet host");
            return;
        }
        System.out.println("Connexion etabli avec :" + this.serveurHost + " au port : " + port);
    }

    public void init_listening() {
        try {
            this.is = new BufferedReader(new InputStreamReader(this.socketOfClient.getInputStream()));
        } catch (IOException e) {
            System.out.println("Client TCP init_listening:" + e);
        }
    }
    public String listening() throws IOException {
        String retour=null;
        try{
            retour = this.is.readLine();

        }
        catch(IOException e){
            System.out.println("Client TCP listening:"+e);
        }
        return retour;

    }
    public void init_send(){
        try{
            this.os = new BufferedWriter(new OutputStreamWriter(this.socketOfClient.getOutputStream()));
        }
        catch(IOException e){
            System.out.println("Client TCP init_send:"+e);
        }

    }

    public void send(String message){
        try{
            this.os.write(message);
            this.os.newLine();
            this.os.flush();

        }
        catch(IOException e){
            System.out.println("Client TCP send:"+e);
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
            System.out.println("Client TCP send:"+e);
        }
        System.out.println("Connexion fermée");

    }


}
