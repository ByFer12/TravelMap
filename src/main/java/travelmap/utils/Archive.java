package travelmap.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JLabel;
import travelmap.grafos.Grafo;

/**
 *
 * @author fer
 */
public class Archive implements IArchive {

    ArrayList<String> nodosss = new ArrayList<>();
    public static Grafo g = new Grafo();

    @Override
    public void readArchive(String archive, JLabel label) {
        try (BufferedReader br = new BufferedReader(new FileReader(archive))) {
            String linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                String origen = datos[0];
                String destino = datos[1];
                Map<String, Integer> pesos=new LinkedHashMap<>();
                
                pesos.put("tiempoVehiculo", Integer.parseInt(datos[2]));
                pesos.put("tiempoPie", Integer.parseInt(datos[3]));
                pesos.put("consumoGas", Integer.parseInt(datos[4]));
                pesos.put("desgastePersona", Integer.parseInt(datos[5]));
                pesos.put("distancia", Integer.parseInt(datos[6]));
                g.agregarNodo(origen);
                g.agregarNodo(destino);
                g.agregarArista(origen, destino, pesos);

            }

        } catch (IOException e) {

        }
        g.graficar("grafo.dot", "grafo");
       //label.setIcon(new javax.swing.ImageIcon("/home/tuxrex/NetBeansProjects/Web/TravelMap/grafo.jpeg"));
        System.out.println("Grafo generado correctamente.");
    }
    
        public void addMoreArchive(String archive, JLabel label) {
        try (BufferedReader br = new BufferedReader(new FileReader(archive))) {
            String linea = "";
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split("\\|");
                String origen = datos[0];
                String destino = datos[1];
                Map<String,Integer>pesos=new LinkedHashMap<>();
                
                pesos.put("horaInicio", Integer.parseInt(datos[2]));
                pesos.put("horaFin", Integer.parseInt(datos[3]));
                pesos.put("probabilidad",Integer.parseInt(datos[4]));
                g.modificarArista(origen, destino, pesos);

            }

        } catch (IOException e) {

        }
        g.graficar("grafo.dot", "grafo");
       label.setIcon(new javax.swing.ImageIcon("/home/tuxrex/NetBeansProjects/Web/TravelMap/grafo.jpeg"));
        System.out.println("Grafo generado correctamente.");
    }

}
