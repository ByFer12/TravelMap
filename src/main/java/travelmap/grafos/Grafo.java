/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package travelmap.grafos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import javax.swing.JOptionPane;


public class Grafo {
    public  String tipoPeso;
    public Map<String, Nodo> nodos;

    public Grafo() {
        this.nodos = new HashMap<>();
    }

    public void agregarNodo(String id) {
        if (!nodos.containsKey(id)) {
            nodos.put(id, new Nodo(id));
        }
    }

    public void agregarArista(String origenId, String destinoId, Map<String, Integer>pesos) {
        if (nodos.containsKey(origenId) && nodos.containsKey(destinoId)) {
            Nodo origen = nodos.get(origenId);
            Nodo destino = nodos.get(destinoId);
            origen.agregarAristas(destino, pesos);
        } else {
            JOptionPane.showMessageDialog(null, "Nodo no encontrado en el grafo");
        }
    }
       
   public Map<Integer, List<Nodo>> encontrarTodasLasRutas(String origenId, String destinoId) {
    if (!nodos.containsKey(origenId) || !nodos.containsKey(destinoId)) {
        JOptionPane.showMessageDialog(null, "Nodo de origen o destino no encontrado en el grafo");
        return null;
    }

    Nodo origen = nodos.get(origenId);
    Nodo destino = nodos.get(destinoId);
    Map<Integer, List<Nodo>> todasLasRutas = new HashMap<>();
    List<Nodo> rutaActual = new ArrayList<>();
    rutaActual.add(origen);
    encontrarRutasRecursivamente(origen, destino, rutaActual, todasLasRutas);
    return todasLasRutas;
}

private void encontrarRutasRecursivamente(Nodo nodoActual, Nodo destino, List<Nodo> rutaActual, Map<Integer, List<Nodo>> todasLasRutas) {
    if (nodoActual.equals(destino)) {
        // Se ha encontrado una ruta completa
        List<Nodo> rutaCompletaConPesos = new ArrayList<>(rutaActual);
        int sumaPesos = 0;
        for (int i = 0; i < rutaCompletaConPesos.size() - 1; i++) {
            Nodo nodoOrigen = rutaCompletaConPesos.get(i);
            Nodo nodoDestino = rutaCompletaConPesos.get(i + 1);
            Arista arista = nodoOrigen.getAristaConDestino(nodoDestino);
            sumaPesos += arista.getPesos().get(tipoPeso);
        }
        todasLasRutas.put(sumaPesos, rutaCompletaConPesos);
    } else {
        // Explorar todas las aristas salientes del nodo actual
        for (Arista arista : nodoActual.getAristas()) {
            Nodo vecino = arista.getDestino();
            if (!rutaActual.contains(vecino)) {
                rutaActual.add(vecino);
                encontrarRutasRecursivamente(vecino, destino, rutaActual, todasLasRutas);
                rutaActual.remove(rutaActual.size() - 1); // Retroceder al nivel anterior
            }
        }
    }
}
    public void modificarArista(String origen, String destino,  Map<String, Integer>pesos){
        if(nodos.containsKey(origen)&&nodos.containsKey(destino)){
            Nodo origin =nodos.get(origen);
            Nodo destine=nodos.get(destino);
            
            Arista aristaActual=origin.getAristaConDestino(destine);
            
            if(aristaActual!=null){
                aristaActual.addValuesToAray(pesos);
            }else{
                  JOptionPane.showMessageDialog(null, "No existe arista entre los nodos especificados origen "+origin.getDato()+" y Destino "+destine.getDato());
            }
            
        }
        
    }

    public List<Nodo> obtenerNodos() {
        return new ArrayList<>(nodos.values());
    }
    
    public List<String> obtenerNodo() {
        return new ArrayList<>(nodos.keySet());
    }

    public Nodo obtenerNodo(int id) {
        return nodos.get(id);
    }

    // Método para visualizar el grafo en Graphviz (formato DOT)
    public void graficar(String nombreArchivoDot, String nombreImagen) {
        
    try (PrintWriter writer = new PrintWriter(nombreArchivoDot)) {
        writer.println("digraph Grafo {");

        for (Nodo nodo : nodos.values()) {
            for (Arista arista : nodo.getAristas()) {
                // Construir el label para la arista con todos los pesos
                StringBuilder labelBuilder = new StringBuilder();
                labelBuilder.append("[");
                List<Integer> pesos = new ArrayList<>(arista.getPesos().values());
                for (int i = 0; i < pesos.size(); i++) {
                    if (i > 0) {
                        labelBuilder.append(", "); // Separador entre pesos
                    }
                    labelBuilder.append(pesos.get(i));
                }
                labelBuilder.append("]");

        System.out.println("Elementos dentro del StringBuilder "+labelBuilder);
                // Escribir la línea DOT para la arista con el label construido
                writer.printf("  %s -> %s ",
                              nodo.getDato(), arista.getDestino().getDato());
            }
        }

        writer.println("}");
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    // Generar la imagen a partir del archivo DOT usando Graphviz
    generarImagenDesdeDot(nombreArchivoDot, nombreImagen);
}
    public  void generarImagenDesdeDot(String nombreArchivoDot, String nombreArchivoImagen) {
    // Comando para ejecutar Graphviz y generar la imagen
    String comandoDot = String.format("dot -Tjpg -o %s %s", nombreArchivoImagen+".jpeg", nombreArchivoDot);

    try {
        // Ejecutar el comando en el sistema
        Process proceso = Runtime.getRuntime().exec(comandoDot);

        // Esperar a que el proceso termine
        int exitVal = proceso.waitFor();

        if (exitVal == 0) {
            System.out.println("Imagen generada correctamente: " + nombreArchivoImagen);
        } else {
            JOptionPane.showMessageDialog(null, "Error al generar la imagen. Código de salida: " + exitVal);
        }
    } catch (IOException | InterruptedException e) {
        e.printStackTrace();
    }
}
    
}
