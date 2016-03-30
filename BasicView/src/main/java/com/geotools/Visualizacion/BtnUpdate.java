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
import org.geotools.swing.data.JFileDataStoreChooser;

/**
 *
 * @author andres
 */
public class BtnUpdate implements ActionListener {
    
    private Ventana v;
    private JButton btn;
    
    public BtnUpdate(Ventana v){
        this.v =v;
        btn = new JButton("Subir capa");
    }
    
    public void SubirArchivo() throws IOException{
        Archivo file  = new Archivo();
        file.setFile(JFileDataStoreChooser.showOpenFile("", null));
        if(file.getFile() != null){
            Estilo st=new Estilo();
            Capa ly =new Capa(file,st);
            String clase= ly.getLayer().getFeatureSource().getClass().toString();
            if(!clase.equals("class org.geotools.data.collection.CollectionFeatureSource")){
                String LyRefS=ly.getLayer().getFeatureSource().getInfo().getCRS().toWKT();
                String MapRefS= v.getMapContent().layers().get(0).getFeatureSource().getInfo().getCRS().toWKT();
                if(MapRefS.equals(LyRefS))
                { 
                    if(v.getMapContent().getMaxBounds().covers(ly.ReferencedEnvelope()))
                    {
                        v.getMapContent().addLayer(ly.getLayer());
                    }
                }
            }
            else
            {
                v.getMapContent().addLayer(ly.getLayer());
            }
        }

     }
     
     public void AddButton(){
        JToolBar toolBar = v.getToolBar();
        toolBar.addSeparator();
        toolBar.add(btn);
        btn.addActionListener(this);
     }

    public void actionPerformed(ActionEvent e) {
        try {
            SubirArchivo();
        } catch (IOException ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
