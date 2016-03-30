/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geotools.Datos;

import java.io.File;
import java.io.IOException;
import org.geotools.data.DataUtilities;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;

/**
 *
 * @author andres
 */
public class Archivo {
    private String Path;
    private File file;
    private FileDataStore fds;
    private SimpleFeatureSource sfs;
    private KMLFile kml;
    private String nombre;
    private String extension;
    
    
    public Archivo(){
        
    }

    public Archivo(String ruta) throws IOException{
        setPath(ruta);
    }
    
    public void preparaDataSource()throws IOException {
        if (file != null) {
        nombre = file.getName();
        extension = nombre.substring(nombre.indexOf("."));
            if(extension.equals(".shp")){
               fds = FileDataStoreFinder.getDataStore(file);
               sfs=fds.getFeatureSource();
            }else if(extension.equals(".kml")){
               kml = new KMLFile(file);
               SimpleFeatureCollection fc = kml.createFeatureCollection(file);
               sfs = DataUtilities.source(fc);
            }
        }
    }
    
    public String getExtension(){
        return extension;
    }
    
    public void setPath(String ruta) throws IOException{
        Path = ruta;
        file = new File(Path);
        preparaDataSource();
    }
    
    public SimpleFeatureSource getSimpleFeatureSource(){
        return sfs;
    }
    
    public void setFile(File file) throws IOException{
        this.file=file;
        preparaDataSource();
    }
    
    public File getFile(){
        return file;
    }
    
    public KMLFile getKML(){
        return kml;
    }
    
}
