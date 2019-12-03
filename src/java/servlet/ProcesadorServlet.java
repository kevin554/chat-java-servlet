package servlet;

import com.google.gson.Gson;
import dto.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Administrador;

public class ProcesadorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String evento = request.getParameter("evento");

        try (PrintWriter out = response.getWriter()) {
            switch (evento) {
                case "seleccionar":
                    seleccionar(request, out);
                    break;

                case "insertar":
                    insertar(request, out);
                    break;

                case "actualizar":
                    actualizar(request, out);
                    break;

                case "eliminar":
                    eliminar(request, out);
                    break;

                case "todos":
                    todos(request, out);
                    break;

                case "ingresar":
                    ingresar(request, out);
                    break;

                case "cargar_chats":
                    cargarChats(request, out);
                    break;
                    
                case "cargar_ultimos_mensajes":
                    cargarUltimosMensajes(request, out);
                    break;

                case "enviar_mensaje":
                    enviarMensaje(request, out);
                    break;

                case "cargar_mensajes":
                    cargarMensajes(request, out);
                    break;

                case "cargar_usuarios":
                    cargarUsuarios(request, out);
                    break;
                    
                default:
                    break;
            }
        } catch (Exception ex) {

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void seleccionar(HttpServletRequest request, PrintWriter out)
            throws Exception {
        Gson gson = new Gson();

        String nick = request.getParameter("nick");

        Usuario obj = new Usuario();
        obj.setNick(nick);

        obj = Administrador.getOrCreate().obtenerUsuario(obj);

        out.print(gson.toJson(obj));
    }

    private void insertar(HttpServletRequest request, PrintWriter out)
            throws Exception {
        Gson gson = new Gson();

        String objStr = request.getParameter("obj");

        Usuario obj = gson.fromJson(objStr, Usuario.class);

        // Si el objeto no se encuentra registrado
        if (!Administrador.getOrCreate().estaRegistrado(obj)) {
            obj.setCodigoID(Administrador.getOrCreate().getSiguienteID());
            Administrador.getOrCreate().agregarUsuario(obj);
            
            out.print(obj.getCodigoID());
        } else {
            out.print("-4");
        }
    }

    private void actualizar(HttpServletRequest request, PrintWriter out)
            throws Exception {
        Gson gson = new Gson();

        String objStr = request.getParameter("obj");

        Usuario obj = gson.fromJson(objStr, Usuario.class);

        // Si el objeto se encuentra registrado
        if (Administrador.getOrCreate().estaRegistrado(obj)) {
            Administrador.getOrCreate().agregarUsuario(obj);
        }
    }

    private void eliminar(HttpServletRequest request, PrintWriter out) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void todos(HttpServletRequest request, PrintWriter out) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void ingresar(HttpServletRequest request, PrintWriter out)
            throws Exception {
        Gson gson = new Gson();

        String objStr = request.getParameter("obj");
        Usuario usuarioQueEntra = gson.fromJson(objStr, Usuario.class);

        if (Administrador.getOrCreate().estaRegistrado(usuarioQueEntra)) {
            Usuario usuarioDelDicionario = Administrador.getOrCreate().obtenerUsuario(usuarioQueEntra);

            if (usuarioDelDicionario.getContrasena().equals(usuarioQueEntra.getContrasena())) {
                out.print(usuarioDelDicionario.getNick());
            } else {
                out.print("-2");
            }
        } else {
            out.print("-1");
        }
    }

    private void cargarChats(HttpServletRequest request, PrintWriter out)
            throws Exception {
        Gson gson = new Gson();

        String objStr = request.getParameter("obj");
        Usuario obj = gson.fromJson(objStr, Usuario.class);

        List<Usuario> chats = Administrador.getOrCreate().obtenerChats(obj);

        out.print(gson.toJson(chats));
    }

    private void cargarUltimosMensajes(HttpServletRequest request,
            PrintWriter out) throws Exception{
        Gson gson = new Gson();

        String objStr = request.getParameter("obj");

        Usuario obj = gson.fromJson(objStr, Usuario.class);

        List<String> chats = Administrador.getOrCreate()
                .obtenerUltimosMensajes(obj);

        out.print(gson.toJson(chats));
    }

    private void enviarMensaje(HttpServletRequest request, PrintWriter out)
            throws Exception {
        Gson gson = new Gson();

        String objStr = request.getParameter("obj");
        String destinatarioStr = request.getParameter("destinatario");
        String mensaje = request.getParameter("mensaje");

        Usuario obj = gson.fromJson(objStr, Usuario.class);
        Usuario destinatario = gson.fromJson(destinatarioStr, Usuario.class);

        destinatario = Administrador.getOrCreate().obtenerUsuario(destinatario);

        if (destinatario != null) {
            destinatario.agregarMensaje(obj, mensaje);

                                                    // cami, isa
            Administrador.getOrCreate().enviarMensaje(obj, destinatario, mensaje);
            
            out.print("ok");
        } else {
            out.print("usuario_inexistente");
        }
    }

    private void cargarMensajes(HttpServletRequest request, PrintWriter out)
            throws Exception {
        Gson gson = new Gson();

        String objStr = request.getParameter("obj");
        String remitenteStr = request.getParameter("remitente");

        Usuario obj = gson.fromJson(objStr, Usuario.class);
        Usuario remitente = gson.fromJson(remitenteStr, Usuario.class);

        List<String> chats = Administrador.getOrCreate().obtenerChats(obj,
                remitente);

        out.print(gson.toJson(chats));
    }

    private void cargarUsuarios(HttpServletRequest request, PrintWriter out) {
        Gson gson = new Gson();
        
        String nombre = request.getParameter("nombre");
        
        List<String> nombres =
                Administrador.getOrCreate().getUsuariosQueEmpiezanCon(nombre);
        
        out.print( gson.toJson(nombres) );
    }

}
