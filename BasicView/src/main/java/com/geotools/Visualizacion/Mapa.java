/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geotools.Visualizacion;

import org.geotools.map.MapContent;


public class Mapa  {
   
    MapContent map;
    
    public MapContent getMap() {
        return map;
    }

    public Mapa(){
       map = new MapContent();
    }
    
    public void AgregarCapa(Capa ly){
        if(ly.getLayer() != null){
            getMap().addLayer(ly.getLayer());
        }         
    }
}
