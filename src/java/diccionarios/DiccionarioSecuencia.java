package diccionarios;

import java.util.ArrayList;
import java.util.List;

public class DiccionarioSecuencia<K, V> extends Diccionario<K, V> {

    private Nodo primero;
    private Nodo ultimo;

    public DiccionarioSecuencia() {
        super();
        init();
    }

    public DiccionarioSecuencia(Comparador comparador) {
        super(comparador);
        init();
    }
    
    private void init() {
        this.primero = null;
        this.ultimo = null;
    }

    @Override
    public void insertar(K llave, V valor) throws Exception {
        if (llave == null) {
            throw new Exception("La llave no puede estar vacia");
        }
        
        if (valor == null) {
            throw new Exception("El valor no puede estar vacio");
        }
        
        Nodo<K, V> nodo = obtenerNodo(llave);
        
        if (nodo != null) {
            nodo.setValor(valor);
            return;
        }
        
        Nodo<K, V> nuevo = new Nodo<>(llave, valor);
        
        if (estaVacio()) {
            ultimo = nuevo;
            primero = ultimo;
        } else {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
        }
        
        cantidadElementos++;
    }

    @Override
    public V obtener(K llave) throws Exception {
        if (llave == null) {
            throw new Exception("La llave no puede estar vacia");
        }
        
        Nodo<K, V> nodo = obtenerNodo(llave);
        
        return nodo != null ? nodo.getValor() : null;
    }

    @Override
    public V eliminar(K llave) throws Exception {
        if (llave == null) {
            throw new Exception("La llave no puede estar vacia");
        }
        
        Nodo<K, V> nodo = obtenerNodo(llave);

        if (nodo == null) {
            return null;
        }
            
        Nodo<K, V> anterior = nodo.getAnterior();
        
        if (anterior != null) {
            anterior.setSiguiente(nodo.getSiguiente());
        } else if (nodo.getSiguiente() != null) {
            primero = nodo.getSiguiente();
        } else {
            primero = nodo.getSiguiente();
            ultimo = nodo.getSiguiente();
        }

        Nodo<K, V> siguiente = nodo.getSiguiente();
        
        if (siguiente != null) {
            siguiente.setAnterior(anterior);
        } else if (anterior != null) {
            ultimo = anterior;
        } else {
            primero = anterior;
            ultimo = anterior;
        }
        
        cantidadElementos--;
        return nodo.getValor();
    }

    @Override
    public boolean contieneLlave(K llave) throws Exception {
        if (llave == null) {
            throw new Exception("La llave no puede estar vacia");
        }
        
        return obtenerNodo(llave) != null;
    }

    @Override
    public boolean estaVacio() {
        return primero == null && ultimo == null;
    }

    @Override
    public List<K> obtenerLlaves() {
        Nodo<K, V> actual = primero;
        
        List<K> lista = new ArrayList<>();
        
        while (actual != null) {
            lista.add(actual.getLlave());
            
            actual = actual.getSiguiente();
        }
        
        return lista;
    }

    @Override
    public List<V> obtenerValores() {
        Nodo<K, V> actual = primero;
        
        List<V> lista = new ArrayList<>();
        
        while (actual != null) {
            lista.add(actual.getValor());
            
            actual = actual.getSiguiente();
        }
        
        return lista;
    }

    private Nodo<K, V> obtenerNodo(K llave) {
        Nodo<K, V> actual = primero;
        
        while (actual != null) {
            if (comparador.esIgual(llave, actual.getLlave())) {
                return actual;
            }
            
            actual = actual.getSiguiente();
        }
        
        return null;
    }
    
    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    
    public Nodo getPrimero() {
        return primero;
    }

    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }

    public Nodo getUltimo() {
        return ultimo;
    }

    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }

    // </editor-fold>

}
