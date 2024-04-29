    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
     */
    package travelmap.grafos;

    /**
     *
     * @author fer
     */
    import java.util.Arrays;
import java.util.Map;

    public class Arista {

        private Nodo destino;
        private Map<String, Integer> pesos;

        public Arista(Nodo destino, Map<String, Integer> pesos) {
            this.destino = destino;

            this.pesos = pesos;
        }

        public Nodo getDestino() {
            return destino;
        }

        public Map<String, Integer> getPesos() {
            return this.pesos; // Devuelve una copia para evitar modificaciones externas
        }

        public void addValuesToAray(Map<String, Integer> newElement) {
          pesos.putAll(newElement);
        }
    }
