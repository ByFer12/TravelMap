
package travelmap.grafos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;




public class Nodo {
   private String dato;
   private List<Arista>aristas;
   
   public Nodo(String dato){
       this.dato=dato;
       this.aristas=new ArrayList<>();
   }

    public String getDato() {
        return dato;
    }

    public List<Arista> getAristas() {
        return aristas;
    }
    public void agregarAristas(Nodo destino,Map<String,Integer> peso){
        Arista a=new Arista(destino, peso);
        aristas.add(a);
    }
    
    public Arista getAristaConDestino(Nodo destino){
        for(Arista a:aristas){
            if(a.getDestino().equals(destino)){
                return a;
            }
        }
        return null;
    }
   
}
