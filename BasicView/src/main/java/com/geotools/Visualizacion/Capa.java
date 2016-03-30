/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geotools.Visualizacion;

import com.geotools.Datos.Archivo;
import java.io.IOException;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;

/**
 *
 * @author andres
 */
public  class Capa  {
    private Layer layer;
    
    public Capa(){
        
    }
    
    public Capa(Archivo file,Estilo style) throws IOException{
        setLayer(file,style);
    }
    
    public void setLayer(Archivo file,Estilo style) throws IOException {
        file.preparaDataSource();
        SimpleFeatureSource sfs =file.getSimpleFeatureSource();
        if(file.getExtension().equals(".shp")){
            style.createStyle(sfs);
         }else if(file.getExtension().equals(".kml")){
            style.createKmlStyle(file.getKML());
         }
        layer = new FeatureLayer(sfs,style.getStyle());
    }
    
    public ReferencedEnvelope ReferencedEnvelope(){
        ReferencedEnvelope re = getLayer().getBounds();
        return re;
    }
    
    public Layer getLayer() {
        return layer;
    }
    
}
