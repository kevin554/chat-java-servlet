package diccionarios;

import dto.Usuario;

public final class ComparadorUsuarios implements Comparador<Usuario> {

    @Override
    public boolean esIgual(Usuario objA, Usuario objB) {
        return objA.getNick().equals(objB.getNick());
    }

    @Override
    public int obtenerHashCode(Usuario obj) {
        return obj.getNick().hashCode();
    }

}