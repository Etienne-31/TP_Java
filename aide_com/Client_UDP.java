import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client_UDP {

    public DatagramSocket dgramSocket = null;
    public byte[] myBuffer = null;
    public DatagramPacket inPacket = null;

    public Client_UDP(int serveurPort,int bufferSize) throws SocketException {

        try{
            this.dgramSocket = new DatagramSocket();
        }
        catch(SocketException e){
            System.out.println("Erreur lors de l'initialisation du serveur UDP : "+e);
        }
        this.myBuffer = new byte[bufferSize];
    }



    public String recieve() throws  IOException{
        this.dgramSocket.receive(this.inPacket);
        String response = null;
        response = new String(this.inPacket.getData(),0,this.inPacket.getLength());

        return response;
    }

    public void send(String response,InetAddress hostAdress,int hostPort ) throws IOException{
        DatagramPacket outPacket = new DatagramPacket(response.getBytes(), response.length(),
                hostAdress, hostPort);
        dgramSocket.send(outPacket);

    }

    public void deconnexion() throws IOException{
        this.dgramSocket.close();
    }

}

