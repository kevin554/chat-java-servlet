package dto;

import diccionarios.ComparadorUsuarios;
import diccionarios.Diccionario;
import diccionarios.DiccionarioSecuencia;
import diccionarios.TablaHash;
import java.util.ArrayList;
import java.util.List;

public class Usuario {

    private int codigoID;
    private String nick;
    private String contrasena;
    private String token;
    private Diccionario<Usuario, List<String>> mensajes;

    public Usuario() {
        init();
    }

    public Usuario(int codigoID, String nick, String contrasena, String token) {
        this.codigoID = codigoID;
        this.nick = nick;
        this.contrasena = contrasena;
        this.token = token;

        init();
    }

    private void init() {
        mensajes = new DiccionarioSecuencia<>(new ComparadorUsuarios());
    }

    public void agregarMensaje(Usuario remitente, String mensaje) {
        try {
            List<String> listaMensajes = mensajes.obtener(remitente);

            if (listaMensajes == null) {
                listaMensajes = new ArrayList<>();
            }

            listaMensajes.add(mensaje);

            mensajes.insertar(remitente, listaMensajes);
        } catch (Exception ex) {

        }
    }

    public List<String> mensajesDe(Usuario remitente) throws Exception {
        return mensajes.obtener(remitente);
    }

    public List<String> ultimosMensajes() {
        List<String> ultimosMensajes = new ArrayList<>();
        
        List<List<String>> listaDeListas = mensajes.obtenerValores();
        
        for (int i = 0; i < listaDeListas.size(); i++) {
            List<String> lista = listaDeListas.get(i);
            
            String ultimoMensaje = lista.get(lista.size() - 1);
            ultimosMensajes.add(ultimoMensaje);
        }
        
        return ultimosMensajes;
    }
    
    public List<Usuario> mensajes() {
        return mensajes.obtenerLlaves();
    }

    // <editor-fold defaultstate="collapsed" desc="getters, setters y toString">
    public int getCodigoID() {
        return codigoID;
    }

    public void setCodigoID(int codigoID) {
        this.codigoID = codigoID;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Diccionario<Usuario, List<String>> getMensajes() {
        return mensajes;
    }

    public void setMensajes(Diccionario<Usuario, List<String>> mensajes) {
        this.mensajes = mensajes;
    }

    @Override
    public String toString() {
        return "Usuario{" + "codigoID=" + codigoID + ", nick=" + nick
                + ", contrasea=" + contrasena + ", token=" + token + '}';
    }

    // </editor-fold>
}
