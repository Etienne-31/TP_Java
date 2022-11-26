package fr.insa.projet.clavardage.models;
import java.time.LocalDateTime;
public class Message {
    private String idSender;
    private String idReceiver;
    private LocalDateTime timestamp;
    private String Data;

    public Message(){}
    public Message(Utilisateur idSender, Utilisateur idReceiver,LocalDateTime timestamp,String Data){
        this.idSender = idSender.getIdUser();
        this.idReceiver = idReceiver.getIdUser();
        this.timestamp = timestamp;
        this.Data = Data;
    }
    public String getidSender(){
        return this.idSender;
    }
    public String getidReceiver(){
        return this.idReceiver;
    }
    public LocalDateTime gettimestamp(){
        return this.timestamp;
    }
    public String getData(){
        return this.Data;
    }


}

	
