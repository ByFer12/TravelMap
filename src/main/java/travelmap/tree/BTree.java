/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package travelmap.tree;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import travelmap.frontend.Principal;
import travelmap.grafos.Nodo;
import travelmap.utils.Archive;

/**
 *
 * @author fer
 */
public class BTree {
   // Map<Double, List<Nodo>>rutas=Principal.rutaOptima;
     private BTreeNode root;
    private int order;

    public BTree(int order) {
        this.root = new BTreeNode();
        this.order = order;
    }

    public void insert(double key) {
        BTreeNode r = root;
        if (r.getKeys().size() == order - 1) {
            BTreeNode s = new BTreeNode();
            s.getChildren().add(r);
            separarHijos(s, 0, null);
            root = s;
        }
        insertNonFull(root, key);
    }

    private void insertNonFull(BTreeNode node, double key) {
        int i = node.getKeys().size() - 1;
        while (i >= 0 && key < node.getKeys().get(i)) {
            i--;
        }
        i++;

        if (node.getChildren().isEmpty()) {
            // El nodo es una hoja, simplemente inserta la clave
            node.getKeys().add(i, key);
        } else {
            // El nodo es interno, decide en qué hijo insertar la clave
            BTreeNode child = node.getChildren().get(i);
            if (child.getKeys().size() == order - 1) {
                separarHijos(node, i, key);
                if (key > node.getKeys().get(i)) {
                    child = node.getChildren().get(i + 1);
                }
            }
            insertNonFull(child, key);
        }
    }

    private void separarHijos(BTreeNode parentNode, int childIndex, Double value) {
        BTreeNode y = parentNode.getChildren().get(childIndex);
        BTreeNode z = new BTreeNode();

        // Calcular el índice medio de las claves
        int midIndex = y.getKeys().size() / 2;

        // Obtener la clave mediana
        double medianKey = y.getKeys().get(midIndex);

        // Mover las claves del nodo lleno (y) al nuevo nodo (z)
        z.getKeys().addAll(y.getKeys().subList(midIndex +1, y.getKeys().size()));
        y.getKeys().subList(midIndex, y.getKeys().size()).clear();

        // Mover los hijos del nodo lleno (y) al nuevo nodo (z), si existen
        if (!y.getChildren().isEmpty()) {
            z.getChildren().addAll(y.getChildren().subList(midIndex+1, y.getChildren().size()));
            y.getChildren().subList(midIndex + 1, y.getChildren().size()).clear();
        }

        // Insertar la clave mediana en el padre
        parentNode.getKeys().add(childIndex, medianKey);

        // Insertar el nuevo nodo (z) en el lugar correcto en el padre
        parentNode.getChildren().add(childIndex + 1, z);

        // Insertar la nueva clave si se especifica
        if (value != null) {
            if (value <medianKey) {
                insertNonFull(y, value); // Insertar en el nodo izquierdo si es menor que la clave mediana
            } else if (value > medianKey) {
                //insertNonFull(y, value); // Insertar en el nodo derecho si es mayor que la clave mediana
            } else {
                // Si es igual a la clave mediana, debería ser insertado en el nodo derecho
                insertNonFull(z, value);
            }
        }
    }


    public void generateDotFile(String filename, Map<Double, List<Nodo>> map) {
        StringBuilder dotContent = new StringBuilder();
        dotContent.append("digraph BTree {\n");
        dotContent.append("node [shape=record];\n");
        generateDotContent(root, dotContent, map);
        dotContent.append("}\n");

        try {
            FileWriter fileWriter = new FileWriter(filename + ".dot");
            fileWriter.write(dotContent.toString());
            fileWriter.close();

            // Ejecutar el comando dot para generar la imagen JPEG
            String command = "dot -Tjpg " + filename + ".dot -o " + filename + ".jpg";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor(); // Esperar a que el proceso termine
            System.out.println("Archivo DOT y JPEG generados con éxito.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void generateDotContent(BTreeNode node, StringBuilder dotContent, Map<Double, List<Nodo>> map) {
        if (node != null) {
            dotContent.append("node").append(node.hashCode()).append("[label=\"");
            for (int i = 0; i < node.getKeys().size(); i++) {
                StringBuilder routs=new StringBuilder();
                double key = node.getKeys().get(i);
                List<Nodo> rutas = map.get(key);
                for (Nodo ruta : rutas) {
                    routs.append(ruta.getDato()+" - ");
                }
                if (rutas != null) {
                    dotContent.append("<f").append(i).append("> ").append(routs.toString());
                } else {
                    dotContent.append("<f").append(i).append("> ").append(key);
                }
                if (i < node.getKeys().size() - 1) {
                    dotContent.append(" | ");
                }
            }
            dotContent.append("\"];\n");

            if (!node.getChildren().isEmpty()) {
                for (int i = 0; i < node.getChildren().size(); i++) {
                    BTreeNode child = node.getChildren().get(i);
                    generateDotContent(child, dotContent, map);
                    dotContent.append("node").append(node.hashCode()).append(":f").append(i).append(" -> node").append(child.hashCode()).append(";\n");
                }
            }
        }
    }

    
}
