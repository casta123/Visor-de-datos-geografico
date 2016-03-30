/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.geotools.Datos;

import com.vividsolutions.jts.geom.Geometry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.kml.v22.KML;
import org.geotools.kml.v22.KMLConfiguration;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.geotools.xml.PullParser;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.xml.sax.SAXException;
//import org.geotools.

/**
 *
 * @author amgomez
 */
public class KMLFile {

    private PullParser parser;
    private GeomType geom;

    public KMLFile(File file) {
        try {
            FileInputStream fis = new FileInputStream(file);
            parser = new PullParser(new KMLConfiguration(), fis, KML.Placemark);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public DefaultFeatureCollection createFeatureCollection(File file) {
        DefaultFeatureCollection dataCollection = null;
        try {
            dataCollection = new DefaultFeatureCollection();

            //Data model for type
            SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
            typeBuilder.setName(file.getName());
            typeBuilder.setCRS(DefaultGeographicCRS.WGS84);
            typeBuilder.add("name", String.class);
            typeBuilder.add("the_geom", Geometry.class);
            

            //Cree el constructor de tipo
            SimpleFeatureType type = typeBuilder.buildFeatureType();
            //Cree el contenedor del modelo de los datos
            SimpleFeatureBuilder builder = new SimpleFeatureBuilder(type);

            SimpleFeature simpleF;
            int index = 0;
            while((simpleF = (SimpleFeature) parser.parse()) != null) {
                //Obtener la geometría y el valor del atributo               
                Geometry geometry = (Geometry) simpleF.getDefaultGeometry();
                geom = getGeomType(geometry.toString());
                String value = (String) simpleF.getAttribute("name");
                //formo el registro que va para la capa
                Object[] register = new Object[]{value, geometry};

                //Adicionar la geometría y el campo al modelo
                builder.addAll(register);
                //Construir el simplefeature con el modelo y con un índice que identifique al dato
                SimpleFeature sfFinal = builder.buildFeature(String.valueOf(index++));
                //Adicionar el feature a la colección
                dataCollection.add(sfFinal);
            }

        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        }
        return dataCollection;
    }

    private GeomType getGeomType(String geometryDesc) {
        if (geometryDesc.contains("POINT") || geometryDesc.contains("MULTIPOINT")) {
            geom = GeomType.POINT;
        } else if (geometryDesc.contains("POLYGON") || geometryDesc.contains("MULTIPOLYGON")) {
            geom = GeomType.POLYGON;
        } else if (geometryDesc.contains("LINESTRING") || geometryDesc.contains("MULTILINESTRING")) {
            geom = GeomType.LINE;
        }
        return geom;
    }

    public GeomType getGeometry() {
        return geom;
    }

}
