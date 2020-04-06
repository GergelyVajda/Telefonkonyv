/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telefonkonyvecske;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

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
        Scanner sc= new Scanner(System.in, "windows-1252");
        System.out.println("2. feladat: Kérem adja meg a keresendő telefonszámot (pl. 204587596)!");
        Integer telkeres=sc.nextInt();
        if (telefonkonyv.get(telkeres)==null) {
            System.out.println("Nem található ilyen telefonszám a névjegyzékben.");
        }else{
        System.out.println("A keresett telefonszámhoz tartozó személy: "+telefonkonyv.get(telkeres));
        }
        //3. Tudjon keresni név alapján (adja vissza azoknak a számoknak a listáját, akik például Jancsi nevű emberekhez tartoznak).
        System.out.println("3. feladat: Kérem adja meg a keresett személy nevét!");
        String nevkeres=sc.next();
        System.out.println(nevkeres);
        Iterator it= telefonkonyv.entrySet().iterator();
        while(it.hasNext()){
        Map.Entry kereses= (Map.Entry) it.next();
            if (kereses.getValue().equals(nevkeres)) {
            System.out.println(kereses.getKey());
            }else{
                System.out.println("Nem található ilyen név a névjegyzékben.");
            }

        }
        //4. Kérdezzük le az összes számot.
        System.out.println("4. feladat: ");
        
        //5. Kérdezzük le az összes ember nevét. 
        
        //6. Tudjon töröln telefonszám alapján.
        
    }
    
}
