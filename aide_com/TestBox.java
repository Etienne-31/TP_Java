import java.io.IOException;

public class TestBox {
    public Serveur_TCP myServTCP;
    public Serveur_UDP myServUDP;
    public Client_TCP myClientTCP;
    public Client_UDP  myClientUDP;
    public TestBox(){
        this.myServTCP = null;
        this.myServUDP = null;
        this.myClientTCP = null;
        this.myClientUDP = null;
    }

    public void init_serv_tcp(int port) throws IOException {
        this.myServTCP.init_serveur(port);
    }

    public void init_serv_udp(int port,int bufferSize) throws IOException {
        this.myServUDP = new Serveur_UDP(port,bufferSize);
    }

    public int procedure() throws IOException {
        int nb_message_send = 0;
        int compteur = 0;
        String first_message = null;
        try{
            init_serv_tcp(1200);
            this.myServTCP.init_listening();
            while((first_message = this.myServTCP.listening())== null){
                System.out.println("Aucun message recu sortie de la procedure dans "+(10-compteur));
                wait(3000);
                compteur++;
                if(compteur == 10){
                    System.exit(0);
                }
            }
            if(first_message.equals("Ready to test")){
                this.myClientTCP = new Client_TCP();
                this.myClientTCP.init_Connexion("localhost",1300);

            }
            else{
                System.out.println("Fin de la procedure");
                System.exit(1);
            }
        }
        catch(IOException | InterruptedException e){
            System.out.println("Erreur lors de la procedure : "+e);
        }

      return nb_message_send;
    }
}
