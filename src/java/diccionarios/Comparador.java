package diccionarios;

public interface Comparador<K> {

   boolean esIgual(K objA, K objB);
    
   int obtenerHashCode(K obj);
   
}