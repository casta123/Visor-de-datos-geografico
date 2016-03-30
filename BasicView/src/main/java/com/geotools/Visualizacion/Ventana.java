/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geotools.Visualizacion;

import com.geotools.Datos.Archivo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JToolBar;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;

/**
 *
 * @author andres
 */
public class Ventana extends JMapFrame {
    
    public Ventana(Mapa map,int ancho,int alto){
         super();
         setMapContent(map.getMap());
         setSize(alto,ancho);
     }
     
     public Ventana(Mapa map){
         super();
         setMapContent(map.getMap());
         setSize(700,700);
     }
     
     public void Redimensionar(int alto,int ancho){
         setSize(alto,ancho);
     }
     
     public void Mostrar(boolean valor){
         setVisible(valor);
     }
    
     public void BarraHerramientas(boolean valor){
         enableToolBar(valor);
     }
         
     public void VistaBasica(boolean valor){
         
         enableToolBar(valor);
         enableToolBar(valor);
         enableLayerTable(valor);
         setVisible(valor);
         
     }
     
     public void BarraEstados(boolean valor){
         enableToolBar(valor);
     }
          
     public void TablaDeCapas(boolean valor){
         enableLayerTable(valor);
     }
     
     public void VisualizarArea(ReferencedEnvelope re){
         getMapPane().setDisplayArea(re);
     }
     
 
}
