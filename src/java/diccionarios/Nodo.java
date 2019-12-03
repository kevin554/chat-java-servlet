package diccionarios;

public final class Nodo<K, V> {

    private K llave;
    private V valor;
    private Nodo<K, V> anterior;
    private Nodo<K, V> siguiente;

    public Nodo(K llave, V valor) {
        this.llave = llave;
        this.valor = valor;
        this.anterior = null;
        this.siguiente = null;
    }

    // <editor-fold defaultstate="collapsed" desc="getters, setters y toString">
    
    public K getLlave() {
        return llave;
    }

    public void setLlave(K llave) {
        this.llave = llave;
    }

    public V getValor() {
        return valor;
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public Nodo<K, V> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<K, V> anterior) {
        this.anterior = anterior;
    }

    public Nodo<K, V> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<K, V> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "Nodo{" + "llave=" + llave + ", valor=" + valor + ", anterior="
                + anterior + ", siguiente=" + siguiente + '}';
    }

    // </editor-fold>

}