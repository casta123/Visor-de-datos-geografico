/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geotools.Visualizacion;

import com.geotools.Datos.GeomType;
import com.geotools.Datos.KMLFile;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.geom.MultiLineString;
import com.vividsolutions.jts.geom.MultiPoint;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import java.awt.Color;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.opengis.feature.simple.SimpleFeatureType;


public class Estilo  {
    private Style style;
    private Color LINE_COLOUR = Color.BLACK;
    private Color FILL_COLOUR = Color.BLUE;
    private String POINT_SYMBOL="Star";
    private float OPACITY = 1.0f;
    private float LINE_WIDTH = 1.0f;
    private float POINT_SIZE = 10.0f;
    private String LABEL_FIELD="";
    
   public Estilo(){
       
   }
    
   public Style getStyle(){
        return style;
    }
    
   public void createDefaultStyle(SimpleFeatureSource sfs){
        if(sfs!=null){
            style= SLD.createSimpleStyle(sfs.getSchema());
        }
    }
    
   public void createStyle(SimpleFeatureSource sfs){
        SimpleFeatureType squema=(SimpleFeatureType)sfs.getSchema();
        Class geometryType=squema.getGeometryDescriptor().getType().getBinding();
        if(geometryType.isAssignableFrom(Polygon.class)||geometryType.isAssignableFrom(MultiPolygon.class)){
            createPolygonStyle();
        }else if(geometryType.isAssignableFrom(LineString.class)||geometryType.isAssignableFrom(MultiLineString.class)){
            createLineStyle();
        }else if(geometryType.isAssignableFrom(Point.class)||geometryType.isAssignableFrom(MultiPoint.class)){
            createPointStyle();
        }
    }
   
      public void createKmlStyle(KMLFile file){
        GeomType geom = file.getGeometry();
        switch (geom) {
            case POLYGON:
                createPolygonStyle();
            case LINE:
                createLineStyle();
            case POINT:
                 createPointStyle();
        }
    }
   
    private void createPolygonStyle(){
         
        if(LABEL_FIELD.equals("")){
            style=SLD.createPolygonStyle(LINE_COLOUR, FILL_COLOUR, OPACITY);
        }else{
            style=SLD.createPolygonStyle(LINE_COLOUR, FILL_COLOUR, OPACITY,LABEL_FIELD,null);
        }
    }
    private void createLineStyle(){
        if(LABEL_FIELD.equals("")){
            style=SLD.createLineStyle(LINE_COLOUR, LINE_WIDTH);
        }else{
            style=SLD.createLineStyle(LINE_COLOUR, LINE_WIDTH,LABEL_FIELD,null);
        }
    }
    private void createPointStyle(){
        if(LABEL_FIELD.equals("")){
            style=SLD.createPointStyle(POINT_SYMBOL, Color.RED, Color.BLACK, 0.2f, POINT_SIZE);
        }else{
            style=SLD.createPointStyle(POINT_SYMBOL, Color.RED, Color.BLACK, 0.2f, POINT_SIZE,LABEL_FIELD,null);
        }
            //Seleccionar entre Circle, Square, Cross, X, Triangle or Star
    }

    public void setLineColour(Color LINE_COLOUR) {
        this.LINE_COLOUR = LINE_COLOUR;
    }
    public void setFillColour(Color FILL_COLOUR) {
        this.FILL_COLOUR = FILL_COLOUR;
    }

    public void setPointSymbol(String POINT_SYMBOL) {
        this.POINT_SYMBOL = POINT_SYMBOL;
    }
    public void setOpacity(float OPACITY) {
        this.OPACITY = OPACITY;
    }
    public void setLineWidth(float LINE_WIDTH) {
        this.LINE_WIDTH = LINE_WIDTH;
    }
    public void setPointSize(float POINT_SIZE) {
        this.POINT_SIZE = POINT_SIZE;
    }
    public void setLabelField(String LABEL_FIELD) {
        this.LABEL_FIELD = LABEL_FIELD;
    } 
}
