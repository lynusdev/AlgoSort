import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * @author Matthias Zimmer und Uwe Seckinger
 * @version 2017.04.20
 * Oberfläche zur Wiedergabe von Sortierergebnissen 
 *
 */
public class GUI_Sortierverfahren extends JFrame {
    private JPanel contentPane;
    private JTextField textFieldAnzahlDatensaetze;
    private JTextArea textAreaDatensaetze;
    private Datenverwaltung datenverwaltung;
    private JScrollPane scrollPane;
    private JLabel lblSortierdauer;
    private JTextField textFieldZuSuchenderDatensatz;
    private JLabel lblAusgabeDatensatzSuche0;
    private JLabel lblAusgabeDatensatzSuche1;
    private JLabel lblAnzahlVergleiche ;
    private int bt_tfHoehe=23;
    private int btAbst=11;
    private int anzBt=4;

    /**
     * Create the frame.
     */
    public GUI_Sortierverfahren() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AlgoSort");
        ImageIcon icon = new ImageIcon("assets/icon.jpg");
        setIconImage(icon.getImage());
        setBounds(100, 100, 467, 644);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBackground(Color.gray);

        textFieldAnzahlDatensaetze = new JTextField();
        textFieldAnzahlDatensaetze.setText("100");
        textFieldAnzahlDatensaetze.setBounds(10, 11, 131, bt_tfHoehe);
        contentPane.add(textFieldAnzahlDatensaetze);
        textFieldAnzahlDatensaetze.setColumns(10);
        
        JButton btnSwitchSort = new JButton("<");
        btnSwitchSort.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    datenverwaltung.switchSort();
                    btnSwitchSort.setText(datenverwaltung.getSwitch());
                    datenverwaltung.playSound("click");
                }
            });
        btnSwitchSort.setBounds(10, 33+btAbst, 131, bt_tfHoehe);
        contentPane.add(btnSwitchSort);

        JButton btnDatensaetzeErzeugen = new JButton("Random");
        btnDatensaetzeErzeugen.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    datenverwaltung.zufaelligeDatensaetzeErzeugen(Integer.parseInt(textFieldAnzahlDatensaetze.getText()));
                    datensaetzeAusgeben();
                    datenverwaltung.playSound("click");
                }
            });
        btnDatensaetzeErzeugen.setBounds(151, 10, 290, bt_tfHoehe);
        contentPane.add(btnDatensaetzeErzeugen);

        JButton btnDatensaetzeMitWiederholung = new JButton("Random (repetitive)");
        btnDatensaetzeMitWiederholung.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent arg0) {
                    datenverwaltung
                    .datensaetzeMitWiederholungErzeugen(Integer.parseInt(textFieldAnzahlDatensaetze.getText()));
                    datensaetzeAusgeben();
                    datenverwaltung.playSound("click");
                }
            });
        btnDatensaetzeMitWiederholung.setBounds(151, 44, 290, bt_tfHoehe);
        contentPane.add(btnDatensaetzeMitWiederholung);

        JButton btnSelectionsort = new JButton("Selectionsort");
        btnSelectionsort.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    datenverwaltung.selectionSort();
                    datensaetzeAusgeben();
                    datenverwaltung.playSound("sort");
                }
            });
        btnSelectionsort.setBounds(10, 67+btAbst, 431, bt_tfHoehe);
        contentPane.add(btnSelectionsort);

        JButton btnBubbleSort = new JButton("Bubblesort");
        btnBubbleSort.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    datenverwaltung.bubbleSort();
                    datensaetzeAusgeben();
                    datenverwaltung.playSound("sort");
                }
            });
        btnBubbleSort.setBounds(10, 101+btAbst, 431, bt_tfHoehe);
        contentPane.add(btnBubbleSort);

        JButton btnInsertionSort = new JButton("Insertionsort");
        btnInsertionSort.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    datenverwaltung.insertionSort();
                    datensaetzeAusgeben();
                    datenverwaltung.playSound("sort");
                }
            });
        btnInsertionSort.setBounds(10, 135+btAbst, 431, bt_tfHoehe);
        contentPane.add(btnInsertionSort);
        
        JButton btnRadixSort = new JButton("Radixsort");
        btnRadixSort.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    datenverwaltung.radixSort();
                    datensaetzeAusgeben();
                    datenverwaltung.playSound("sort");
                }
            });
        btnRadixSort.setBounds(10, 169+btAbst, 431, bt_tfHoehe);
        contentPane.add(btnRadixSort);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 203+btAbst, 431, 330);
        contentPane.add(scrollPane);

        textAreaDatensaetze = new JTextArea();
        textAreaDatensaetze.setEditable(false);
        scrollPane.setViewportView(textAreaDatensaetze);

        lblSortierdauer = new JLabel("Duration: 0 ms ");
        lblSortierdauer.setBounds(10, 544+btAbst, 431, 14);
        lblSortierdauer.setForeground(Color.white);
        contentPane.add(lblSortierdauer);

        lblAnzahlVergleiche = new JLabel("Comparisons:  0");
        lblAnzahlVergleiche.setBounds(10, 568+btAbst, 431, 14);
        lblAnzahlVergleiche.setForeground(Color.white);
        contentPane.add(lblAnzahlVergleiche);

        // Instanz der Datenverwaltung erzeugen
        datenverwaltung = new Datenverwaltung();

        this.setVisible(true);
        datenverwaltung.zufaelligeDatensaetzeErzeugen(10);
        datensaetzeAusgeben();
        datenverwaltung.playSound("start");
    }

    private void datensaetzeAusgeben() {
        // Ausgabefeld leeren
        textAreaDatensaetze.setText("");
        // Datensätze ausgeben
        for (int i = 0; i < datenverwaltung.getDaten().length; i++) {
            textAreaDatensaetze.append(datenverwaltung.getDaten()[i] + "\n");
        }
        // Zeitdauer setzen
        lblSortierdauer.setText("Duration: " + datenverwaltung.getLaufzeit() + " ms");

        // Anzahl Vergleiche setzen
        lblAnzahlVergleiche.setText("Comparisons: " + datenverwaltung.getAnzahlVergleiche());
    }

    public Datenverwaltung getDV(){
        return datenverwaltung;
    }
}
