import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Serveur_UDP {

    public DatagramSocket dgramSocket = null;
    public byte[] myBuffer = null;
    public DatagramPacket inPacket = null;
    public InetAddress clientAddress = null;
    public int clientPort = 0;
    public InetAddress clientAdress = null;

    public Serveur_UDP(int serveurPort,int bufferSize) throws SocketException {

        try{
            this.dgramSocket = new DatagramSocket(serveurPort);
        }
        catch(SocketException e){
            System.out.println("Erreur lors de l'initialisation du serveur UDP : "+e);
        }
        this.myBuffer = new byte[bufferSize];
        inPacket = new DatagramPacket(this.myBuffer, this.myBuffer.length);
    }

    public void connexion() throws IOException {
        try{
            this.dgramSocket.receive(this.inPacket);
            this.clientPort = this.inPacket.getPort();
        }
        catch(IOException e){
            System.out.println("Erreur connexion serveur UDP :"+e);
        }

    }

    public String recieve() throws  IOException{
        String response = null;
        response = new String(this.inPacket.getData(),0,this.inPacket.getLength());

        return response;
    }

    public void send(String response) throws IOException{
        DatagramPacket outPacket = new DatagramPacket(response.getBytes(), response.length(),
                this.clientAddress, this.clientPort);
        dgramSocket.send(outPacket);

    }

    public void deconnexion() throws IOException{
        this.dgramSocket.close();
    }

}
