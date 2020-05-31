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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        /*try {
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
        }*/
        try {
            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/telefon","root","Alma1023");
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery("INSERT INTO `telefon`.`nevjegyzek` VALUES ('202274907', 'Vajda', 'Gergely', '18')");
            while (rs.next()) {
                System.out.println(rs.getString(""));
            }
            rs.close();
            stat.close();
            con.close();
        } catch (SQLException ex) {
            System.out.println(ex.toString());
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
    public static void tablakeszites(){
    try {
        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306","root","Alma1023");
            Statement stat = con.createStatement();
            stat.executeUpdate("Create database telefon");
                String Sql = "CREATE TABLE telefon.nevjegyzek"
                        + "(id INTEGER not NULL,"
                        + "first VARCHAR(25),"
                        + "last VARCHAR(25),"
                        + "age INTEGER,"
                        + "PRIMARY KEY ( id ))";
                stat.executeUpdate(Sql);
        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }
    }

    public static void menu(Map telefonkonyv) {
        beolvasas(telefonkonyv);
        System.out.println("Válasszon menüpontot!");
        System.out.println("1-es gomb: névjegy hozzáadása");
        System.out.println("2-es gomb: telefonszám keresése");
        System.out.println("3-as gomb: név keresése");
        System.out.println("4-es gomb: az összes tárolt telefonszám lekérése");
        System.out.println("5-ös gomb: az összes tárolt név lekérése ");
        System.out.println("6-os gomb: névjegy törlése telefonszám alapján");
        System.out.println("7-es gomb: kilépés");
        System.out.println("Nyomja le a választott menüpont gombját, majd az enter billentyűt!");
        Scanner sc = new Scanner(System.in);
        Integer utasitas = sc.nextInt();
        if (utasitas == 1) {
            hozzaadas(telefonkonyv);
            menu(telefonkonyv);
        }
        if (utasitas == 2) {
            telkeres(telefonkonyv);
            menu(telefonkonyv);
        }
        if (utasitas == 3) {
            nevkeres(telefonkonyv);
            menu(telefonkonyv);
        }
        if (utasitas == 4) {
            osszSzamLekeres(telefonkonyv);
            menu(telefonkonyv);
        }
        if (utasitas == 5) {
            osszNevLekeres(telefonkonyv);
            menu(telefonkonyv);
        }
        if (utasitas == 6) {
            torolTelbol(telefonkonyv);
            menu(telefonkonyv);
        }
        if (utasitas == 7) {
            mentes(telefonkonyv);
        }
    }

    public static void main(String[] args) {

        Map<Integer, String> telefonkonyv = new HashMap<>();
        menu(telefonkonyv);


    }

}
