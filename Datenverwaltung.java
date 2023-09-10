import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Datenverwaltung {

    /**
     * Array, das die zu sortierenden Daten enthält
     */
    private int[] daten;

    /**
     * Variable, in der die Anzahl der Vergleiche gespeichert werden kann.
     * Inhalt wird automatisch von Oberfläche übernommen.
     */
    private long anzahlVergleiche = 0;

    /**
     * Variable, in die die Laufzeit des Algorithmus in Millisekunden
     * gespeichert werden kann Wert wird automatisch in die Oberfläche
     * übernommen.
     */
    private long laufzeit = 0;

    public boolean backwards;

    /**
     * Methode, die die Elemente an den beiden vorgegebenen Stellen vertauscht 
     * 
     * @param ersterIndex - Index des ersten Elements
     * @param zweiterIndex - Indes des zweiten Elements
     */
    private void tauscheElementeAnPositionen(int ersterIndex, int zweiterIndex) {
        // Element an erster Position in Zwischenspeicher merken
        int zwischenspeicher = daten[ersterIndex]; 
        // Element an zweiter Position an erste Position kopieren 
        daten[ersterIndex] = daten[zweiterIndex];
        // Element aus Zwischenspeicher in zweite Position kopieren
        daten[zweiterIndex] = zwischenspeicher; 
    }

    /**
     * wechselt die sortierrichtungs variable
     */
    public void switchSort() {
        if (backwards){
            backwards = false;
        }
        else{
            backwards = true;
        }
    }

    /**
     * Sortiert die Daten im Array int[] daten mit Hilfe von SelectionSort
     */
    public void selectionSort() {
        long startTime = System.currentTimeMillis();
        int anfang=0;
        int aktuelles=0;
        anzahlVergleiche = 0;
        while(anfang<daten.length){
            aktuelles=anfang+1;
            int bester=anfang;
            while(aktuelles<daten.length){
                if(backwards){
                    if(daten[aktuelles]>daten[bester]){
                        bester=aktuelles;
                    }
                }
                else{
                    if(daten[aktuelles]<daten[bester]){
                        bester=aktuelles;
                    }
                }
                anzahlVergleiche++;
                aktuelles++;
            }
            tauscheElementeAnPositionen(anfang, bester);
            anfang++;
        }
        laufzeit = System.currentTimeMillis() - startTime;
    }

    /**
     * Sortiert die Daten im Array int[] daten mit Hilfe von SelectionSort
     */
    public void bubbleSort() {
        long startTime = System.currentTimeMillis();
        int ende = daten.length-1;
        anzahlVergleiche = 0;
        while(ende >= 0){
            int aktuelles = 0;
            while(aktuelles != ende){
                if(backwards){
                    if(daten[aktuelles+1] > daten[aktuelles]){
                        tauscheElementeAnPositionen(aktuelles, aktuelles+1);
                    }
                }
                else{
                    if(daten[aktuelles+1] < daten[aktuelles]){
                        tauscheElementeAnPositionen(aktuelles, aktuelles+1);
                    }
                }
                anzahlVergleiche++;
                aktuelles++;
            }
            ende --;
        }
        laufzeit = System.currentTimeMillis() - startTime;
    }

    /**
     * sortiert die Daten im Array int[] daten mit Hilfe von SelectionSort
     */
    public void insertionSort() {
        long startTime = System.currentTimeMillis();
        anzahlVergleiche = 0;
        for (int i = 1; i < daten.length; i++) {
            int elementToSort = daten[i];
            int j = i;
            while (j > 0 && elementToSort < daten[j - 1]) {
                daten[j] = daten[j - 1];
                j--;
            }
            daten[j] = elementToSort;
            anzahlVergleiche++;
        }
        laufzeit = System.currentTimeMillis() - startTime;
    }

    public void radixSort() {
        int maxStellen = 0;
        for(int i = 1;i<daten.length;i++){
            if(daten[i] > maxStellen){
                maxStellen = daten[i];
            }
        }
        maxStellen = (int)(Math.log10(maxStellen)+1);
        //System.out.println((12398%100)/10);
        ArrayList[] buckets1 = new ArrayList[10];
        for (int o = 0; o < 10; o++) {
            buckets1[o] = new ArrayList <Integer> ();
        }
        ArrayList[] buckets2 = new ArrayList[10];
        for (int o = 0; o < 10; o++) {
            buckets2[o] = new ArrayList <Integer> ();
        }
        int digit = 0;
        for (int i = 0; i < daten.length; i++){
            digit = daten[i]%10;
            buckets1[digit].add(daten[i]);
        }
        boolean ersteBucketList = true;
        for (int i = 0; i < maxStellen-1; i++){
            for (int j = 0; j < 10; j++){
                if (ersteBucketList){
                    for (int k = 0; k < buckets1[j]; j++){
                        
                    }
                }
                else{
                    
                }
                
            }
        }
        System.out.println(buckets1[0]);
    }

    public int[] getDaten() {
        return daten;
    }

    public static synchronized void playSound(String sound) {
        new Thread(new Runnable() {
                public void run() {
                    try{
                        File path = new File("./assets/"+sound+".wav");
                        AudioInputStream audioinput = AudioSystem.getAudioInputStream(path);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioinput);
                        clip.start();
                        Thread.sleep(3000);
                        clip.close();
                        Thread.currentThread().interrupt();
                        return;
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }).start();
    }

    /**
     * stellt die Anzahl der Vergleiche für die Oberfläche bereit
     * 
     * @return Bei Algorithmus ermittelte Anzahl der Vergleiche
     */
    public long getAnzahlVergleiche() {
        return anzahlVergleiche;
    }

    /**
     * stellt die Laufzeit des Algorithmus für Oberfläche bereit
     * 
     * @return Bei Algorithmus ermittelte Laufzeit
     */
    public long getLaufzeit() {
        return laufzeit;
    }

    public String getSwitch() {
        if(backwards){
            return ">";            
        }
        else{
            return "<";
        }
    }

    /**
     * Füllt das Array daten mit Zufallszahlen zwischen 0 und der Zehnfachen
     * Anzahl
     * 
     * @param Anzahl
     *            der zu erzeugende Datensätze
     */
    public void zufaelligeDatensaetzeErzeugen(int anzahl) {
        daten = new int[anzahl];
        for (int i = 0; i < anzahl; i++) {
            daten[i] = (int) (Math.random() * anzahl * 10);
        }
        maxStellenZahl10=(int)(Math.log10(anzahl)+2);
    }

    /**
     * Füllt das Array daten mit Zufallszahlen zwischen 0 und der Hälfte der
     * Anzahl -> häufige Wiederholungen
     * 
     * @param Anzahl
     *            der zu erzeugende Datensätze
     */
    public void datensaetzeMitWiederholungErzeugen(int anzahl) {
        daten = new int[anzahl];
        for (int i = 0; i < anzahl; i++) {
            daten[i] = (int) (Math.random() * anzahl / 2);
        }
    }
}