package logica;

import diccionarios.ComparadorUsuarios;
import diccionarios.Diccionario;
import diccionarios.DiccionarioSecuencia;
import diccionarios.TablaHash;
import dto.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Administrador extends Observable {

    private static Administrador instancia;
    private Diccionario<Usuario, Usuario> usuarios;
    private int siguienteID;

    public static Administrador getOrCreate() {
        if (instancia == null) {
            instancia = new Administrador();
            instancia.addObserver(new Notificador());
        }

        return instancia;
    }

    private Administrador() {
        usuarios = new DiccionarioSecuencia<>(new ComparadorUsuarios());
        siguienteID = 1;
    }

    public List<Usuario> usuariosRegistrados() {
        return usuarios.obtenerLlaves();
    }

    public boolean estaRegistrado(Usuario usuario) throws Exception {
        return usuarios.contieneLlave(usuario);
    }

    public void agregarUsuario(Usuario objUsuario) throws Exception {
        usuarios.insertar(objUsuario, objUsuario);
    }

    public Usuario obtenerUsuario(Usuario usuario) throws Exception {
        return usuarios.obtener(usuario);
    }

    public List<Usuario> obtenerChats(Usuario usuario) throws Exception {
        Usuario objUsuario = usuarios.obtener(usuario);
        return objUsuario.mensajes();
    }
    
    public List<String> obtenerUltimosMensajes(Usuario usuario)
            throws Exception {
        Usuario objUsuario = usuarios.obtener(usuario);
        return objUsuario.ultimosMensajes();
    }

    public List<String> obtenerChats(Usuario usuario, Usuario remitente)
            throws Exception {
        Usuario objUsuario = usuarios.obtener(usuario);
        return objUsuario.mensajesDe(remitente);
    }

    public void enviarMensaje(Usuario quienEnvia, Usuario destinatario, String mensaje) {
        setChanged();
        notifyObservers(destinatario);
        setChanged();
        notifyObservers(quienEnvia);
        setChanged();
        notifyObservers(mensaje);
    }

    public List<String> getUsuariosQueEmpiezanCon(String letras) {
        List<Usuario> lista = usuarios.obtenerLlaves();
        
        ArrayList<String> nombres = new ArrayList<>();
        
        for (int i = 0; i < lista.size(); i++) {
            Usuario usuario = lista.get(i);
            
            if (usuario.getNick().startsWith(letras)) {
                nombres.add(usuario.getNick());
            }
        }
        
        return nombres;
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    public Diccionario<Usuario, Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Diccionario<Usuario, Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public int getSiguienteID() {
        return ++siguienteID;
    }

    public void setSiguienteID(int siguienteID) {
        this.siguienteID = siguienteID;
    }

    // </editor-fold>

}
