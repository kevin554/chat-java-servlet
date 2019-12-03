package diccionarios;

import java.util.List;

public abstract class Diccionario<K, V> {
    
    protected Comparador<K> comparador;
    protected int cantidadElementos;

    public Diccionario() {
        this.comparador = new ComparadorGenerico<>();
    }
    
    public Diccionario(Comparador<K> comparador) {
        this.comparador = comparador;
    }
    
    public abstract void insertar(K llave, V valor) throws Exception;

    public abstract V obtener(K llave) throws Exception;
    
    public abstract V eliminar(K llave) throws Exception;
    
    public abstract boolean contieneLlave(K llave) throws Exception;
    
    public abstract boolean estaVacio();
    
    public abstract List<K> obtenerLlaves();
    
    public abstract List<V> obtenerValores();
    
    public int getCantidadElementos() {
        return cantidadElementos;
    }
    
}