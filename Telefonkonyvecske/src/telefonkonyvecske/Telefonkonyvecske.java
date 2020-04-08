/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telefonkonyvecske;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gvajd
 */
public class Telefonkonyvecske {

    public static void hozzaadas(Map telefonkonyv) {
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in, "windows-1252");
        System.out.println("Kérem adja meg a névjegyzékbe felvenni kívánt telefonszámot (pl. 204587596)!");
        Integer szamBele = sc1.nextInt();
        System.out.println("Kérem adja meg az előbbi telfonszámhoz tartozó személy nevét!");
        String nevBele = sc2.nextLine();
        telefonkonyv.put(szamBele, nevBele);
    }

    public static void telkeres(Map telefonkonyv) {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Kérem adja meg a keresendő telefonszámot (pl. 204587596)!");
        Integer telkeres = sc1.nextInt();
        if (telefonkonyv.get(telkeres) == null) {
            System.out.println("Nem található ilyen telefonszám a névjegyzékben.");
        } else {
            System.out.println("A keresett telefonszámhoz tartozó személy: " + telefonkonyv.get(telkeres));
        }
    }

    public static void nevkeres(Map telefonkonyv) {
        System.out.println("Kérem adja meg a keresett személy nevét!");
        Scanner sc2 = new Scanner(System.in, "windows-1252");
        String nevkeres = sc2.nextLine();
        Integer jartItt = 0;
        Iterator it = telefonkonyv.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry kereses = (Map.Entry) it.next();
            if (kereses.getValue().equals(nevkeres)) {
                System.out.println(kereses.getKey());
                jartItt = 1;
            }

        }
        if (jartItt == 0) {
            System.out.println("Nem található ilyen név a névjegyzékben.");
        }
    }

    public static void osszSzamLekeres(Map telefonkonyv) {
        Iterator it = telefonkonyv.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry kereses = (Map.Entry) it.next();
            System.out.println(kereses.getKey());

        }
    }

    public static void osszNevLekeres(Map telefonkonyv) {
        Iterator it = telefonkonyv.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry kereses = (Map.Entry) it.next();
            System.out.println(kereses.getValue());

        }
    }

    public static void torolTelbol(Map telefonkonyv) {
        Scanner sc1 = new Scanner(System.in);
        System.out.println("Kérem adja meg a törölni kívánt telefonszámot!");
        Integer telkeres = sc1.nextInt();
        Iterator it = telefonkonyv.entrySet().iterator();
        Integer jartItt = 0;
        while (it.hasNext()) {
            Map.Entry kereses = (Map.Entry) it.next();
            if (kereses.getKey().equals(telkeres)) {
                it.remove();
                System.out.println("A szám törlése sikeres!");
                jartItt = 1;
            }
        }
        if (jartItt == 0) {
            System.out.println("Nem található ilyen szám a névjegyzékben.");
        }
    }

    public static void mentes(Map telefonkonyv) {
        try {
            FileWriter buta = new FileWriter("telkonyv.csv");
            PrintWriter okos = new PrintWriter(buta);
            Iterator it = telefonkonyv.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry kereses = (Map.Entry) it.next();
                okos.println(kereses.getKey() + ";" + kereses.getValue());
            }
            okos.close();
        } catch (IOException ex) {
            System.out.println("Hiba a file írásakor!");
        }
    }

    public static void beolvasas(Map telefonkonyv) {
        try {
            FileReader buta = new FileReader("telkonyv.csv");
            BufferedReader okos = new BufferedReader(buta);
            String egyben;
            String[] ketteszedo= new String[2];
            
            for (int i = 0; i < 1000; i++) {
                egyben=okos.readLine();
                if (egyben==null) {
                    break;
                }
                ketteszedo=egyben.split(";");
                telefonkonyv.put(Integer.parseInt(ketteszedo[0]), ketteszedo[1]);
            }

        } catch (IOException ex) {
            System.out.println("Hiba a file beolvasásánál!");
        }
    }

    public static void main(String[] args) {

        Map<Integer, String> telefonkonyv = new HashMap<>();
        
        beolvasas(telefonkonyv); 
        
        //1. Legyen képes belerakni a telefonkönyvbe egy telszám-név párt.
        hozzaadas(telefonkonyv);

        //2. Tudjon keresni nevet telefonszám alapján (megmondja, ha van, hogy kié, illetve visszajelzi, ha nincs).
        telkeres(telefonkonyv);
        
        //3. Tudjon keresni név alapján (adja vissza azoknak a számoknak a listáját, akik például Jancsi nevű emberekhez tartoznak).
        nevkeres(telefonkonyv);
        
        //4. Kérdezzük le az összes számot.
        osszSzamLekeres(telefonkonyv);

        //5. Kérdezzük le az összes ember nevét. 
        osszNevLekeres(telefonkonyv);
        
        //6. Tudjon törölni telefonszám alapján.
        torolTelbol(telefonkonyv);
        
        mentes(telefonkonyv);
    }

}
