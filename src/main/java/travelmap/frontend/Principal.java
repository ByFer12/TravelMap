/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package travelmap.frontend;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultTreeModel;
import travelmap.grafos.Arista;
import travelmap.grafos.Nodo;
import travelmap.utils.Archive;
import travelmap.utils.IArchive;

/**
 *
 * @author fer
 */
public class Principal extends javax.swing.JFrame {

    /**
     * Creates new form Principal
     */
    IArchive a;
    private int horas = 12;
    private int minutos = 0;
    private int segundos = 0;
    Timer timer;
    public Map<String, Nodo> nodo;
    private final int segundosPorMinuto = 15;
    private final int minutosPorHora = 60;
    private final int horasEnDia = 24;
    public boolean isChecked = false;
    private DecimalFormat formatoHora = new DecimalFormat("00");
    Map<Integer, List<Nodo>> rutaOptima;

    public Principal() {

        initComponents();
        System.out.println(tipoVehiculo.getSelectedIndex());
        txtHora.setEditable(false);
        txtMinuto.setEditable(false);
        a = new Archive();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                avanzarReloj();
                actualizarHoraLabel();
            }
        });

        timer.start();
        getKindVehicle();
    }

    public void getKindVehicle() {
        if (tipoVehiculo.getSelectedIndex() == 0) {
            DefaultComboBoxModel<String> nuevo = new DefaultComboBoxModel<>();
            nuevo.addElement("Gasolina");
            nuevo.addElement("Distancia");
            nuevo.addElement("Gasolina y la distancia");
            nuevo.addElement("La ruta más rápida");
            nuevo.addElement("Las peores rutas");
            calcularCombo.setModel(nuevo);

        }
        tipoVehiculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (tipoVehiculo.getSelectedIndex() == 0) {
                    cargarVehiculo();

                } else {
                    cargarCaminando();

                }
            }

        });
    }

    public void cargarVehiculo() {
        DefaultComboBoxModel<String> nuevo = new DefaultComboBoxModel<>();
        nuevo.addElement("Gasolina");
        nuevo.addElement("Distancia");
        nuevo.addElement("Gasolina y la distancia");
        nuevo.addElement("La ruta más rápida");
        nuevo.addElement("Las peores rutas");
        calcularCombo.setModel(nuevo);

    }

    public void cargarCaminando() {
        DefaultComboBoxModel<String> nuevo = new DefaultComboBoxModel<>();
        nuevo.addElement("Desgaste físico");
        nuevo.addElement("Distancia");
        nuevo.addElement("Desgaste físico y la distancia");
        nuevo.addElement("Las peores rutas");
        calcularCombo.setModel(nuevo);

    }

    private void avanzarReloj() {
        segundos += 1;
        if (segundos >= segundosPorMinuto) {
            segundos = 0;
            minutos += 1;
            if (minutos >= minutosPorHora) {
                minutos = 0;
                horas += 1;
                if (horas >= horasEnDia) {
                    horas = 0; // Reiniciar a medianoche (00:00)
                }
            }
        }
    }

    private void actualizarHoraLabel() {
        String hora = formatoHora.format(horas) + ":" + formatoHora.format(minutos);
        horaLabel.setText(hora);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        tipoVehiculo = new javax.swing.JComboBox<>();
        horaLabel = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        txtHora = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        nodoOrigen = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        nodoDestino = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMinuto = new javax.swing.JTextField();
        calcularRuta = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        calcularCombo = new javax.swing.JComboBox<>();
        agregarImagen = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        cargarGrafos = new javax.swing.JMenuItem();
        cargarTrafico = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TravelMap");

        tipoVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vehiculo", "Caminando" }));

        horaLabel.setFont(new java.awt.Font("Liberation Sans", 1, 33)); // NOI18N
        horaLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jCheckBox1.setText("Editar");
        jCheckBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox1ItemStateChanged(evt);
            }
        });
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jCheckBox2.setText("Detener");
        jCheckBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCheckBox2ItemStateChanged(evt);
            }
        });

        txtHora.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        nodoOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nodoOrigenActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Liberation Sans", 1, 17)); // NOI18N
        jLabel2.setText("Destino");

        jLabel3.setFont(new java.awt.Font("Liberation Sans", 1, 17)); // NOI18N
        jLabel3.setText("Origen");

        jLabel1.setText("Hora");

        jLabel4.setText("Minuto");

        txtMinuto.setFont(new java.awt.Font("Liberation Sans", 1, 16)); // NOI18N

        calcularRuta.setText("Calcular Ruta Optima");
        calcularRuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularRutaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Liberation Sans", 1, 17)); // NOI18N
        jLabel5.setText("Mejor ruta");

        calcularCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        calcularCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularComboActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtHora, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(horaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jCheckBox1)
                                .addComponent(jCheckBox2))
                            .addGap(8, 8, 8))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(21, 21, 21)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(calcularRuta)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2)
                                        .addComponent(tipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3)
                                        .addComponent(nodoOrigen, 0, 221, Short.MAX_VALUE)
                                        .addComponent(nodoDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(calcularCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jCheckBox2)
                            .addComponent(horaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtHora)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMinuto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(tipoVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodoOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nodoDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calcularCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(calcularRuta)
                .addGap(367, 367, 367))
        );

        jMenu1.setText("Archivos");

        cargarGrafos.setText("Cargar archivo");
        cargarGrafos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarGrafosActionPerformed(evt);
            }
        });
        jMenu1.add(cargarGrafos);

        cargarTrafico.setText("Cargar Trafico");
        cargarTrafico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarTraficoActionPerformed(evt);
            }
        });
        jMenu1.add(cargarTrafico);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(agregarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 1160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(agregarImagen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarGrafosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarGrafosActionPerformed
        JFileChooser abrirDirectorio = new JFileChooser();
        FileNameExtensionFilter filtrar = new FileNameExtensionFilter("Solo archivos ide", "txt");
        abrirDirectorio.setFileFilter(filtrar);
        abrirDirectorio.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = abrirDirectorio.showOpenDialog(this);

        if (seleccion == abrirDirectorio.APPROVE_OPTION) {
            File archivo = abrirDirectorio.getSelectedFile();
            a.readArchive(archivo.getAbsolutePath(), agregarImagen);

        } else {
            JOptionPane.showMessageDialog(null, "No se selecciono ninguna carpeta");
        }
    }//GEN-LAST:event_cargarGrafosActionPerformed

    private void cargarTraficoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarTraficoActionPerformed
        JFileChooser abrirDirectorio = new JFileChooser();
        FileNameExtensionFilter filtrar = new FileNameExtensionFilter("Solo archivos ide", "txt");
        abrirDirectorio.setFileFilter(filtrar);
        abrirDirectorio.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int seleccion = abrirDirectorio.showOpenDialog(this);

        if (seleccion == abrirDirectorio.APPROVE_OPTION) {
            File archivo = abrirDirectorio.getSelectedFile();
            a.addMoreArchive(archivo.getAbsolutePath(), agregarImagen);
            DefaultComboBoxModel<String> opciones = new DefaultComboBoxModel<>();
            DefaultComboBoxModel<String> opciones2 = new DefaultComboBoxModel<>();
            for (String string : Archive.g.obtenerNodo()) {
                opciones.addElement(string);
            }
            for (String string : Archive.g.obtenerNodo()) {
                opciones2.addElement(string);
            }
            nodoOrigen.setModel(opciones);
            nodoDestino.setModel(opciones2);

        } else {
            JOptionPane.showMessageDialog(null, "No se selecciono ninguna carpeta");
        }
    }//GEN-LAST:event_cargarTraficoActionPerformed

    private void calcularComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularComboActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_calcularComboActionPerformed

    private void nodoOrigenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nodoOrigenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nodoOrigenActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String hora = txtHora.getText();
        String minut = txtMinuto.getText();
        if (hora.length() == 2 && minut.length() == 2) {
            if ((Integer.parseInt(hora) >= 0 && Integer.parseInt(hora) < 24) && (Integer.parseInt(minut) >= 0 && Integer.parseInt(minut) < 59)) {
                horas = Integer.parseInt(hora);
                minutos = Integer.parseInt(minut);
                txtHora.setText("");
                txtMinuto.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "En horas solo debe ingresar de 0 a 23 y minutos de 0 a 59");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Solo debe ingresar dos numeros");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jCheckBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox2ItemStateChanged

        if (evt.getStateChange() == ItemEvent.SELECTED) {
            timer.stop();
        } else {
            timer.start();
        }
    }//GEN-LAST:event_jCheckBox2ItemStateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jCheckBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCheckBox1ItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            txtHora.setEditable(true);
            txtMinuto.setEditable(true);
        } else {
            txtHora.setEditable(false);
            txtMinuto.setEditable(false);
        }
    }//GEN-LAST:event_jCheckBox1ItemStateChanged

    private void calcularRutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calcularRutaActionPerformed
        if (nodoOrigen.getItemCount() != 0 && nodoDestino.getItemCount() != 0) {

            String origen = nodoOrigen.getSelectedItem().toString();
            String destino = nodoDestino.getSelectedItem().toString();

            int opcion = tipoVehiculo.getSelectedIndex();
            int mejorRuta=calcularCombo.getSelectedIndex();
            if (opcion == 0) {
                vehicleCalculator(mejorRuta, origen, destino);

            } else {
                walkingCalculator(mejorRuta, origen, destino);

            }
        }else{
            JOptionPane.showMessageDialog(null, "Aun no hay Origen ni Destino, verifique");
        }
    }//GEN-LAST:event_calcularRutaActionPerformed

    public void graficar(String nombreArchivoDot, String nombreImagen) {

        try (PrintWriter writer = new PrintWriter(nombreArchivoDot)) {
            writer.println("digraph Grafo {");

            for (Nodo nod : nodo.values()) {
                for (Arista arista : nod.getAristas()) {
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

                    System.out.println("Elementos dentro del StringBuilder " + labelBuilder);
                    // Escribir la línea DOT para la arista con el label construido
                    writer.printf("  %s -> %s ",
                            nod.getDato(), arista.getDestino().getDato());
                }
            }

            writer.println("}");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Generar la imagen a partir del archivo DOT usando Graphviz
        Archive.g.generarImagenDesdeDot(nombreArchivoDot, nombreImagen);
    }

    private void vehicleCalculator(int tipoVijaje, String origen, String destino) {
        System.out.println("VEHICULO");
        switch (tipoVijaje) {
            case 0:
                System.out.println("GASOLINA*****");
                Archive.g.tipoPeso = "consumoGas";
                rutaOptima = Archive.g.encontrarTodasLasRutas(origen, destino);
                for (Map.Entry<Integer, List<Nodo>> entrada : rutaOptima.entrySet()) {
                    Integer sumaPesos = entrada.getKey();
                    List<Nodo> ruta = entrada.getValue();

                    System.out.println("Ruta: " + sumaPesos);

                    // Recorrer la lista de nodos de la ruta
                    for (Nodo nodo : ruta) {
                        System.out.print(nodo.getDato() + "->");
                    }

                    System.out.println("\n-------------------");
                }
                break;
            case 1:
                System.out.println("DISTANCIA*****");
                Archive.g.tipoPeso = "distancia";
                Map<Integer, List<Nodo>> rutaOptima = Archive.g.encontrarTodasLasRutas(origen, destino);
                for (Map.Entry<Integer, List<Nodo>> entrada : rutaOptima.entrySet()) {
                    Integer sumaPesos = entrada.getKey();
                    List<Nodo> ruta = entrada.getValue();

                    System.out.println("Ruta: " + sumaPesos);

                    // Recorrer la lista de nodos de la ruta
                    for (Nodo nodo : ruta) {
                        System.out.print(nodo.getDato() + "->");
                    }

                    System.out.println("\n-------------------");
                }
                break;
            case 2:
                System.out.println("Seleccionaste el Item: " + tipoVijaje + " Origen: " + origen + " Destino: " + destino);
                break;
            case 3:
                System.out.println("Seleccionaste el Item: " + tipoVijaje + " Origen: " + origen + " Destino: " + destino);
                break;
            case 4:
                System.out.println("Seleccionaste el Item: " + tipoVijaje + " Origen: " + origen + " Destino: " + destino);
                break;
            default:
                throw new AssertionError();
        }
    }

    private void walkingCalculator(int tipoVijaje, String origen, String destino) {
        System.out.println("CAMINANDO");
        switch (tipoVijaje) {
            case 0:
                System.out.println("DESGASTE#####");
                Archive.g.tipoPeso = "desgastePersona";
                rutaOptima = Archive.g.encontrarTodasLasRutas(origen, destino);
                for (Map.Entry<Integer, List<Nodo>> entrada : rutaOptima.entrySet()) {
                    Integer sumaPesos = entrada.getKey();
                    List<Nodo> ruta = entrada.getValue();

                    System.out.println("Ruta: " + sumaPesos);

                    // Recorrer la lista de nodos de la ruta
                    for (Nodo nodo : ruta) {
                        System.out.print(nodo.getDato() + "->");
                    }

                    System.out.println("\n-------------------");
                }
                break;
            case 1:
                 System.out.println("DISTANCIA#####");
                Archive.g.tipoPeso = "distancia";
                rutaOptima = Archive.g.encontrarTodasLasRutas(origen, destino);
                for (Map.Entry<Integer, List<Nodo>> entrada : rutaOptima.entrySet()) {
                    Integer sumaPesos = entrada.getKey();
                    List<Nodo> ruta = entrada.getValue();

                    System.out.println("Ruta: " + sumaPesos);

                    // Recorrer la lista de nodos de la ruta
                    for (Nodo nodo : ruta) {
                        System.out.print(nodo.getDato() + "->");
                    }

                    System.out.println("\n-------------------");
                }
                break;
            case 2:
                System.out.println("Seleccionaste el Item: " + tipoVijaje + " Origen: " + origen + " Destino: " + destino);
                break;
            case 3:
                System.out.println("Seleccionaste el Item: " + tipoVijaje + " Origen: " + origen + " Destino: " + destino);
                break;

            default:
                throw new AssertionError();
        }
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel agregarImagen;
    private javax.swing.JComboBox<String> calcularCombo;
    private javax.swing.JButton calcularRuta;
    private javax.swing.JMenuItem cargarGrafos;
    private javax.swing.JMenuItem cargarTrafico;
    private javax.swing.JLabel horaLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox<String> nodoDestino;
    private javax.swing.JComboBox<String> nodoOrigen;
    private javax.swing.JComboBox<String> tipoVehiculo;
    private javax.swing.JTextField txtHora;
    private javax.swing.JTextField txtMinuto;
    // End of variables declaration//GEN-END:variables
}
