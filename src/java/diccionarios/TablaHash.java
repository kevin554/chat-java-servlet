package diccionarios;

import java.util.ArrayList;
import java.util.List;

public final class TablaHash<K, V> extends Diccionario<K, V> {

    private DiccionarioSecuencia<K, V>[] elementos;
    private float factorCarga;
    private int limiteElementos;

    public TablaHash() {
        super();
        init();
    }

    public TablaHash(Comparador<K> comparador) {
        super(comparador);
        init();
    }

    private void init() {
        cantidadElementos = 0;
        elementos = new DiccionarioSecuencia[11];
        factorCarga = 0.75f;
        limiteElementos = (int) (elementos.length * factorCarga);
    }

    @Override
    public void insertar(K llave, V valor) throws Exception {
        if (llave == null) {
            throw new Exception("La llave no puede estar vacia");
        }

        if (valor == null) {
            throw new Exception("El valor no puede estar vacio");
        }

        cantidadElementos++;
        int i = obtenerPosicion(llave);

        for (DiccionarioSecuencia<K, V> elemento : elementos) {
            if (elemento != null && elemento.contieneLlave(llave)) {
                cantidadElementos--;
                break;
            }
        }
        
        if (elementos[i] == null) {
            elementos[i] = new DiccionarioSecuencia<>(comparador);
        }

        if (cantidadElementos >= limiteElementos) {
            redimensionar();
        }

        elementos[i].insertar(llave, valor);
    }

    @Override
    public V obtener(K llave) throws Exception {
        if (llave == null) {
            throw new Exception("La llave no puede estar vacia");
        }
        
        int obtenerPosicion = obtenerPosicion(llave);
        
        return elementos[obtenerPosicion].obtener(llave);
    }

    @Override
    public V eliminar(K llave) throws Exception {
        if (llave == null) {
            throw new Exception("La llave no puede estar vacia");
        }
        
        int i = obtenerPosicion(llave);
        
        DiccionarioSecuencia<K, V> elemento = elementos[i];
        if (elemento == null) {
            return null;
        }
        
        V eliminado = elemento.eliminar(llave);
        cantidadElementos--;
        
        if (elemento.getCantidadElementos() == 0) {
            elementos[i] = null;
        }
        
        return eliminado;
    }

    @Override
    public boolean contieneLlave(K llave) throws Exception {
        if (llave == null) {
            throw new Exception("La llave no puede estar vacia");
        }

        for (DiccionarioSecuencia<K, V> elemento : elementos) {
            if (elemento != null && elemento.contieneLlave(llave)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean estaVacio() {
        return cantidadElementos == 0;
    }

    @Override
    public List<K> obtenerLlaves() {
        List<K> llaves = new ArrayList<>();

        for (DiccionarioSecuencia<K, V> elemento : elementos) {
            if (elemento != null) {
                llaves.addAll(elemento.obtenerLlaves());
            }
        }

        return llaves;
    }

    @Override
    public List<V> obtenerValores() {
        List<V> valores = new ArrayList<>();

        for (DiccionarioSecuencia<K, V> elemento : elementos) {
            if (elemento != null) {
                valores.addAll(elemento.obtenerValores());
            }
        }

        return valores;
    }
    
    
    private int obtenerPosicion(K llave, int length) {
        return (comparador.obtenerHashCode(llave) & 0x7FFFFFFF)
                % length;
    }


    private int obtenerPosicion(K llave) {
        return obtenerPosicion(llave, elementos.length);
    }

    private void redimensionar() throws Exception {
        int nuevaCapacidad = elementos.length * 2 + 1;

        DiccionarioSecuencia<K, V>[] arregloTemporal
                = new DiccionarioSecuencia[nuevaCapacidad];

        for (DiccionarioSecuencia<K, V> elemento : elementos) {
            if (elemento != null) {
                for (int i = 0; i < elemento.getCantidadElementos(); i++) {
                    K llave = elemento.obtenerLlaves().get(i);
                    
                    int posicion = obtenerPosicion(llave, nuevaCapacidad);
                    
                    if (arregloTemporal[posicion] == null) {
                        arregloTemporal[posicion] =
                                new DiccionarioSecuencia<>(comparador);
                    }
                    
                    arregloTemporal[posicion].insertar(llave, 
                            elemento.obtener(llave));
                }
            }
            
        }
        
        elementos = arregloTemporal;
        limiteElementos = (int) (elementos.length * factorCarga);
    }

    // <editor-fold defaultstate="collapsed" desc="getters y setters">
    
    public DiccionarioSecuencia<K, V>[] getElementos() {
        return elementos;
    }

    public void setElementos(DiccionarioSecuencia<K, V>[] elementos) {
        this.elementos = elementos;
    }

    public float getFactorCarga() {
        return factorCarga;
    }

    public void setFactorCarga(float factorCarga) {
        this.factorCarga = factorCarga;
    }

    public int getLimiteElementos() {
        return limiteElementos;
    }

    public void setLimiteElementos(int limiteElementos) {
        this.limiteElementos = limiteElementos;
    }

    // </editor-fold> 
    
}
