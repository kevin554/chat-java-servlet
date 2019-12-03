package diccionarios;

public final class ComparadorGenerico<K> implements Comparador<K> {

    @Override
    public boolean esIgual(K objA, K objB) {
        return objA.equals(objB);
    }

    @Override
    public int obtenerHashCode(K obj) {
        return obj.hashCode();
    }

}