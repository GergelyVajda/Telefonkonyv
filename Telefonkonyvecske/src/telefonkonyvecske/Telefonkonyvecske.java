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
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

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
        String telkeres = sc1.nextLine();
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
            org.jdom2.Document jdomDoc = new org.jdom2.Document();
            Element rootElement = new Element("telefonkonyv");
            jdomDoc.setRootElement(rootElement);
            Iterator it = telefonkonyv.entrySet().iterator();
            String value;
            String key;
            while (it.hasNext()) {
                Map.Entry kereses = (Map.Entry) it.next();
                key = kereses.getKey().toString();
                value = kereses.getValue().toString();

                Element child = new Element("szemely");
                Element idElement = new Element("telefonszam");
                idElement.addContent(key);
                Element nevElement = new Element("nev");
                nevElement.addContent(value);
                child.addContent(idElement);
                child.addContent(nevElement);
                rootElement.addContent(child);

            }
            XMLOutputter xml = new XMLOutputter();
            xml.setFormat(Format.getPrettyFormat());
            PrintWriter okos = new PrintWriter("telefonkonyv.xml");
            okos.println(xml.outputString(jdomDoc));
            okos.flush();
            okos.close();
        } catch (IOException ex) {
            System.out.println("Hiba a file írásakor!");
        }
    }

    public static void beolvasas(Map telefonkonyv) {
        try {
            SAXBuilder jdomBuilder = new SAXBuilder();
            org.jdom2.Document jdomDocument = jdomBuilder.build("telefonkonyv.xml");
            Element jdomRoot = jdomDocument.getRootElement();
            List<Element> children = jdomRoot.getChildren();
            Element child;
            String telefonszam;
            String nev;
            for (int i = 0; i < children.size(); i++) {
                child = children.get(i);
                telefonszam = child.getChild("telefonszam").getText();
                nev = child.getChild("nev").getText();
                telefonkonyv.put(telefonszam, nev);
            }

        } catch (IOException ex) {
            System.out.println("Hiba a file beolvasásánál!");
        } catch (JDOMException ex) {
            System.out.println("SAXbuilder hiba!");
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
