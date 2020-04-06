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
        Map<Integer, String> telefonkonyv = new HashMap<>();
        telefonkonyv.put(202274907, "Vajda Gergely");
        telefonkonyv.put(209706789, "Vajda Tamás");
        telefonkonyv.put(203944931, "Vajda János");
        telefonkonyv.put(205876954, "Böröcz Gergely");
        System.out.println("1. feladat: " + telefonkonyv);

        //2. Tudjon keresni nevet telefonszám alapján (megmondja, ha van, hogy kié, illetve visszajelzi, ha nincs).
        Scanner sc1 = new Scanner(System.in);
        System.out.println("2. feladat: Kérem adja meg a keresendő telefonszámot (pl. 204587596)!");
        Integer telkeres = sc1.nextInt();
        if (telefonkonyv.get(telkeres) == null) {
            System.out.println("Nem található ilyen telefonszám a névjegyzékben.");
        } else {
            System.out.println("A keresett telefonszámhoz tartozó személy: " + telefonkonyv.get(telkeres));
        }
        //3. Tudjon keresni név alapján (adja vissza azoknak a számoknak a listáját, akik például Jancsi nevű emberekhez tartoznak).
        System.out.println("3. feladat: Kérem adja meg a keresett személy nevét!");
        Scanner sc2 = new Scanner(System.in, "windows-1252");
        String nevkeres = sc2.nextLine();
        Iterator it1 = telefonkonyv.entrySet().iterator();
        Integer jartItt=0;
        while (it1.hasNext()) {
            Map.Entry kereses = (Map.Entry) it1.next();
            if (kereses.getValue().equals(nevkeres)) {
                System.out.println(kereses.getKey());
                jartItt=1;
            } 

        }
        if (jartItt==0) {
            System.out.println("Nem található ilyen név a névjegyzékben.");
        }
        //4. Kérdezzük le az összes számot.
        System.out.println("4. feladat: ");
        Iterator it2 = telefonkonyv.entrySet().iterator();
        while (it2.hasNext()) {
            Map.Entry kereses = (Map.Entry) it2.next();
            System.out.println(kereses.getKey());

        }

        //5. Kérdezzük le az összes ember nevét. 
        System.out.println("5. feladat: ");
        Iterator it3 = telefonkonyv.entrySet().iterator();
        while (it3.hasNext()) {
            Map.Entry kereses = (Map.Entry) it3.next();
            System.out.println(kereses.getValue());

        } 
        
        //6. Tudjon törölni telefonszám alapján.
        System.out.println("6. feladat: Kérem adja meg a törölni kívánt telefonszámot!");
        telkeres=sc1.nextInt();
        Iterator it4 = telefonkonyv.entrySet().iterator();
        jartItt=0;
        while (it4.hasNext()) {
            Map.Entry kereses = (Map.Entry) it4.next();
            if (kereses.getKey().equals(telkeres)) {
                it4.remove();
                System.out.println("A szám törlése sikeres!");
                jartItt=1;
            } 

        }
        if (jartItt==0) {
            System.out.println("Nem található ilyen szám a névjegyzékben.");
        }
        System.out.println(telefonkonyv);
        
    }

}
