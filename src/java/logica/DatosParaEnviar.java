package logica;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DatosParaEnviar {

    private HashMap<String, String> data;
    private HashMap<String, String> notification;
    private String prioridad;
    
    private List<String> registration_ids;

    public DatosParaEnviar() {
        data = new HashMap<>();
        notification = new HashMap<>();
        registration_ids = new LinkedList<>();
        prioridad = "high";
        
        registration_ids.add("");
    }

    public DatosParaEnviar(String token, String nick, String nickDestinatario,
            String mensaje) {
        data = new HashMap<>();
        data.put("tipo", "mensaje");
        data.put("mensaje", mensaje);
        data.put("nick", nick);
        data.put("nick_destinatario", nickDestinatario);
        
        notification = new HashMap<>();
        
        registration_ids = new LinkedList<>();
        registration_ids.add(token);
        
        prioridad = "high";
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    
    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }

    public HashMap<String, String> getNotification() {
        return notification;
    }

    public void setNotification(HashMap<String, String> notification) {
        this.notification = notification;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public List<String> getRegistration_ids() {
        return registration_ids;
    }

    public void setRegistration_ids(List<String> registration_ids) {
        this.registration_ids = registration_ids;
    }
    
    // </editor-fold>
    
}