
package com.geotools.Principal;

import com.geotools.Datos.*;
import com.geotools.Visualizacion.*;
import java.awt.Color;
import java.io.IOException;
/**
 *
 * @author andres
 */
public class Main {
    public static void main(String[] args)throws IOException{
        
        Archivo Paises = new Archivo();
        Paises.setPath("src/main/resources/Shapefiles/Suramerica.shp");
        Estilo stPaises=new Estilo();
        stPaises.setFillColour(Color.yellow);
        stPaises.setLabelField("CNTRY_NAME");
        stPaises.setOpacity((float)0.2);
        Capa lyPaises=new Capa(Paises,stPaises);
            
        Archivo Ciudades = new Archivo();
        Ciudades.setPath("src/main/resources/Shapefiles/Ciudades_Suramérica.shp");
        Estilo stCities=new Estilo();
        stCities.setFillColour(Color.blue);
        stCities.setPointSize(10);
        stCities.setLabelField("NAME");
        Capa lyCiudades=new Capa(Ciudades,stCities);
        
        Archivo Lagos  = new Archivo();
        Lagos.setPath("src/main/resources/Shapefiles/South_America_Lakes.shp");
        Estilo stLakes=new Estilo();
        stLakes.setFillColour(Color.blue);
        stLakes.setLabelField("NAME");
        Capa lyLakes=new Capa(Lagos,stLakes);
        
        Archivo Rios  = new Archivo();
        Rios.setPath("src/main/resources/Shapefiles/South_America_Hydrography.shp");
        Estilo stRivers=new Estilo();
        stRivers.setLineColour(Color.blue);
        stRivers.setLabelField("NAME");
        Capa lyRivers=new Capa(Rios,stRivers);
        
        Mapa map= new Mapa();
        
        map.AgregarCapa(lyPaises);
        map.AgregarCapa(lyCiudades);
        map.AgregarCapa(lyLakes);
        map.AgregarCapa(lyRivers);
        
        Ventana v=new Ventana(map,700,1000);
        BtnUpdate btn = new BtnUpdate(v);
        v.VistaBasica(true);
        btn.AddButton();
        v.VisualizarArea(lyPaises.ReferencedEnvelope());
    }
}
