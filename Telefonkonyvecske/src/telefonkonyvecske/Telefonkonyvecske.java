/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telefonkonyvecske;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author gvajd
 */
public class Telefonkonyvecske {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //1. Legyen képes belerakni a telefonkönyvbe egy telszám-név párt.
        Map<Integer,String> telefonkonyv= new HashMap<>();
        telefonkonyv.put(202274907,"Vajda Gergely");
        telefonkonyv.put(209706789,"Vajda Tamás");
        telefonkonyv.put(203944931,"Vajda János");
        telefonkonyv.put(205876954,"Böröcz Gergely");
        System.out.println("1. feladat: "+telefonkonyv);
        
        //2. Tudjon keresni nevet telefonszám alapján (megmondja, ha van, hogy kié, illetve visszajelzi, ha nincs).
        System.out.println("2. feladat: "+telefonkonyv.get(202274907));
        
        //3. Tudjon keresni név alapján (adja vissza azoknak a számoknak a listáját, akik például Jancsi nevű emberekhez tartoznak).
        Iterator it= telefonkonyv.entrySet().iterator();
        Map.Entry kereses= (Map.Entry) it.next();
        if (kereses.getValue().equals("Vajda János")) {
            System.out.println("3. feladat: "+kereses.getKey());
        }
        
        //4. Kérdezzük le az összes számot.
        
        //5. Kérdezzük le az összes ember nevét. 
        
        //6. Tudjon töröln telefonszám alapján.
        
    }
    
}
