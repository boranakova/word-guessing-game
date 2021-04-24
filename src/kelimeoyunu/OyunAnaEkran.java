package kelimeoyunu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.util.Random;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class OyunAnaEkran extends javax.swing.JFrame {

    static int butonsayaci = 0;
    static int kelimepuanimiz = 100;
    static ArrayList<Integer> PuanDegerleri = new ArrayList<Integer>();
    static Connection conn = null;
    static Statement stmt = null;
    static ArrayList<String> KelimeAnlami = new ArrayList<String>();
    static ArrayList<Integer> KelimePuani = new ArrayList<Integer>();
    static int AnaPuan = 9800;
    static String APuan;
    static ArrayList<String> KelimeAdi = new ArrayList<String>();
    static ArrayList<String> AdSoyad = new ArrayList<String>();
    static int a;
    Random randomize = new Random();
    static int randomsayi;
    static ArrayList<Character> Harfler = new ArrayList<Character>();
    static ArrayList<Character> BosYerler = new ArrayList<Character>();
    static int anasayacsuresi=239600;
    static int kisacevapsuresi = 21000;
    static int sureyidurdursayac = 0;
    static int harfbutonsayac=0;
    static int yansiyacakpuan=0;
static ArrayList<Character> KelimeHarfleri1 = new ArrayList<Character>();ArrayList<Character> KelimeHarfleri2 = new ArrayList<Character>();ArrayList<Character> KelimeHarfleri3 = new ArrayList<Character>();ArrayList<Character> KelimeHarfleri4 = new ArrayList<Character>();ArrayList<Character> KelimeHarfleri5 = new ArrayList<Character>();ArrayList<Character> KelimeHarfleri6 = new ArrayList<Character>(); ArrayList<Character> KelimeHarfleri7 = new ArrayList<Character>();ArrayList<Character> KelimeHarfleri8 = new ArrayList<Character>();ArrayList<Character> KelimeHarfleri9 = new ArrayList<Character>(); ArrayList<Character> KelimeHarfleri10 = new ArrayList<Character>();ArrayList<Character> KelimeHarfleri11 = new ArrayList<Character>(); ArrayList<Character> KelimeHarfleri12 = new ArrayList<Character>();  ArrayList<Character> KelimeHarfleri13 = new ArrayList<Character>(); ArrayList<Character> KelimeHarfleri14 = new ArrayList<Character>();
    // KULLANICI 20 SANİYE İÇİDE KELİMEYİ BİLEMEZSE HARF ALDIĞI KADAR PUANI ANAPUANA GERİ EKLEYİP TOTALDE HARF PUANINI CIKAR.
    public void Listele() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/kelimeoynu", "root", "");
            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from sozcuk");
            ResultSet res = stmt2.executeQuery("select * from yarismaci");

            while (rs.next()) {

                KelimeAnlami.add(rs.getString(3));
                KelimeAdi.add(rs.getString(4));
                KelimePuani.add(rs.getInt(2));

            }
            rs.close();

            while (res.next()) {
                AdSoyad.add(rs.getString(2));
            }
            res.close();

        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
     ActionListener GeriSayim = new ActionListener(){
         
    public void actionPerformed(ActionEvent e){
    
            
        anasayacsuresi -=100;
        
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");
        anasayac.setText(df.format(anasayacsuresi));
        if (anasayacsuresi<=0) {
            
            timer.stop();
        }
       if (anasayacsuresi == 0) {
            int input = JOptionPane.showOptionDialog(null, "Süreniz bitti.Puan kazanamadınız.", "Kelime Oyunu",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            harfalayim.setEnabled(false);
            sureyidurdur.setEnabled(false);
            for (int i = 1; i < 16; i++) {
                if (sureyidurdursayac==i) {
                    jTextField1.setText(KelimeAdi.get(i-1));
                    AnaPuan+=harfbutonsayac*100;
                    AnaPuan-=KelimePuani.get(i-1)*100;
                    System.out.println(AnaPuan);
                    harfbutonsayac = 0;
                }
            
        }
       }
       
       
       

    }

     };Timer timer = new Timer(100,GeriSayim);
     
     ActionListener GeriSayim2 = new ActionListener(){
         
    public void actionPerformed(ActionEvent e){
    
            
        kisacevapsuresi -=100;
        
        SimpleDateFormat df = new SimpleDateFormat("ss");
        kisasayac.setText(df.format(kisacevapsuresi));
        if (kisacevapsuresi<=0) {
            
            timer2.stop();
        }
        if (kisacevapsuresi == 0) {
            int input = JOptionPane.showOptionDialog(null, "Süreniz bitti.Puan kazanamadınız.", "Kelime Oyunu",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            harfalayim.setEnabled(false);
            sureyidurdur.setEnabled(false);
            
            for (int i = 1; i < 16; i++) {
                if (sureyidurdursayac==i) {
                    
                    jTextField1.setText(KelimeAdi.get(i-1));
                    AnaPuan+=harfbutonsayac*100; // anapuan = anapuan + harfbutonsayac
                    AnaPuan-=KelimePuani.get(i-1)*100;
                    System.out.println(AnaPuan);
                    harfbutonsayac = 0;
                }
            }
        }
    }
     };
       Timer timer2 = new Timer(100,GeriSayim2);
 
 
    public OyunAnaEkran() {
        initComponents();
        cevabisoyle.setEnabled(false);
        jTextField1.setEnabled(false);
        randomsayi = 0;
        Listele();
        kelimetanimi.setText(KelimeAnlami.get(0));
        kelimepuani.setText(String.valueOf(KelimePuani.get(0) * 100));
       
        for (int i = 0; i < 4; i++) {
            BosYerler.add('?');
        }
        timer.start();
       
       soru.setText("Soru-1");
        
        
        
        
       
      

    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        harfalayim = new javax.swing.JButton();
        sureyidurdur = new javax.swing.JButton();
        cevabisoyle = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        anotherlabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        kelimetanimi = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        kelimepuani = new javax.swing.JLabel();
        gosterilenkelime = new javax.swing.JLabel();
        anasayac = new javax.swing.JLabel();
        kisasayac = new javax.swing.JLabel();
        abc = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        yansiyacakpuanimiz = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        soru = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        harfalayim.setBackground(new java.awt.Color(0, 255, 102));
        harfalayim.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        harfalayim.setForeground(new java.awt.Color(255, 255, 255));
        harfalayim.setText("HARF ALAYIM");
        harfalayim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                harfalayimActionPerformed(evt);
            }
        });
        getContentPane().add(harfalayim, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 721, 530, 220));

        sureyidurdur.setBackground(new java.awt.Color(255, 51, 0));
        sureyidurdur.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 36)); // NOI18N
        sureyidurdur.setForeground(new java.awt.Color(255, 255, 255));
        sureyidurdur.setText("SUREYI DURDUR");
        sureyidurdur.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        sureyidurdur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sureyidurdurActionPerformed(evt);
            }
        });
        getContentPane().add(sureyidurdur, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 720, 670, 220));

        cevabisoyle.setBackground(new java.awt.Color(255, 255, 0));
        cevabisoyle.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        cevabisoyle.setText("GONDER");
        cevabisoyle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cevabisoyleActionPerformed(evt);
            }
        });
        getContentPane().add(cevabisoyle, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 460, 161, 56));

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jTextField1.setText("TAHMIN EDINIZ....");
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 460, 391, 56));

        anotherlabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        anotherlabel.setText("Süre :");
        getContentPane().add(anotherlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 60, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tanımı:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 620, -1, -1));

        kelimetanimi.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        kelimetanimi.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(kelimetanimi, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 596, 730, 70));

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("x");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 59, 79));

        kelimepuani.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        kelimepuani.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(kelimepuani, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 640, 73, 34));

        gosterilenkelime.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        gosterilenkelime.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(gosterilenkelime, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 520, 580, 70));

        anasayac.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        anasayac.setText("0:00:00");
        getContentPane().add(anasayac, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 60, -1, -1));

        kisasayac.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        kisasayac.setForeground(new java.awt.Color(153, 255, 153));
        kisasayac.setText("0");
        getContentPane().add(kisasayac, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 80, 90));

        abc.setBackground(new java.awt.Color(255, 255, 255));
        abc.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        abc.setIcon(new javax.swing.ImageIcon("C:\\Users\\Boran\\Desktop\\5963160161361f10b45308f7.jpg")); // NOI18N
        getContentPane().add(abc, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 820));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Puan:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 20, -1, -1));

        yansiyacakpuanimiz.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        yansiyacakpuanimiz.setText("0");
        getContentPane().add(yansiyacakpuanimiz, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 10, 80, 40));

        jLabel3.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Tanımı:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 620, -1, -1));

        soru.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        getContentPane().add(soru, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 230, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void harfalayimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_harfalayimActionPerformed
        harfbutonsayac++;
        
        AnaPuan -= 100;
        kelimepuanimiz = Integer.parseInt(kelimepuani.getText());

        if (kelimepuanimiz != 100) {
            kelimepuanimiz = Integer.parseInt(kelimepuani.getText());
            kelimepuanimiz = kelimepuanimiz - 100;
            kelimepuani.setText(String.valueOf(kelimepuanimiz));
        } else if (kelimepuanimiz == 100) {

            int input = JOptionPane.showOptionDialog(null, "Harf hakkınız kalmadı.Puan kazanamadınız.", "Kelime Oyunu",
                    JOptionPane.OK_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
            kelimepuani.setText("0");
            harfalayim.setEnabled(false);
            if (input == JOptionPane.OK_OPTION) {
                
                cevabisoyle.setEnabled(true);
            }
        }

        String kelime1 = KelimeAdi.get(0);String kelime2 = KelimeAdi.get(1);String kelime3 = KelimeAdi.get(2);String kelime4 = KelimeAdi.get(3); String kelime5 = KelimeAdi.get(4);String kelime6 = KelimeAdi.get(5);String kelime7 = KelimeAdi.get(6);String kelime8 = KelimeAdi.get(7);String kelime9 = KelimeAdi.get(8);String kelime10 = KelimeAdi.get(9);String kelime11 = KelimeAdi.get(10); String kelime12 = KelimeAdi.get(11);String kelime13 = KelimeAdi.get(12); String kelime14 = KelimeAdi.get(13);

        

        for (int i = 0; i < kelime1.length(); i++) { KelimeHarfleri1.add(kelime1.charAt(i));}
        for (int i = 0; i < kelime2.length(); i++) {  KelimeHarfleri2.add(kelime2.charAt(i));}
        for (int i = 0; i < kelime3.length(); i++) { KelimeHarfleri3.add(kelime3.charAt(i));}
        for (int i = 0; i < kelime4.length(); i++) { KelimeHarfleri4.add(kelime4.charAt(i));}
        for (int i = 0; i < kelime5.length(); i++) {  KelimeHarfleri5.add(kelime5.charAt(i));}
        for (int i = 0; i < kelime6.length(); i++) { KelimeHarfleri6.add(kelime6.charAt(i));}
        for (int i = 0; i < kelime7.length(); i++) { KelimeHarfleri7.add(kelime7.charAt(i)); }
        for (int i = 0; i < kelime8.length(); i++) {KelimeHarfleri8.add(kelime8.charAt(i));}
        for (int i = 0; i < kelime9.length(); i++) { KelimeHarfleri9.add(kelime9.charAt(i));}
        for (int i = 0; i < kelime10.length(); i++) {KelimeHarfleri10.add(kelime10.charAt(i));}
        for (int i = 0; i < kelime11.length(); i++) {KelimeHarfleri11.add(kelime11.charAt(i));}
        for (int i = 0; i < kelime12.length(); i++) { KelimeHarfleri12.add(kelime12.charAt(i));}
        for (int i = 0; i < kelime13.length(); i++) {KelimeHarfleri13.add(kelime13.charAt(i));}
        for (int i = 0; i < kelime14.length(); i++) {KelimeHarfleri14.add(kelime14.charAt(i));}

        if (butonsayaci == 0) {
            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri1.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri1.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri1.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri1.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            }
           
        }
        

        if (butonsayaci == 1) {
            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri2.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri2.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri2.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri2.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }

        if (butonsayaci == 2) {
            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri3.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri3.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri3.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri3.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri3.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }

        if (butonsayaci == 3) {
            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri4.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri4.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri4.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri4.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri4.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }

        if (butonsayaci == 4) {
            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri5.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri5.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri5.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri5.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri5.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(5) == '?') {
                BosYerler.set(5, KelimeHarfleri5.get(5));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }

        if (butonsayaci == 5) {
            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri6.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri6.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri6.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri6.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri6.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(5) == '?') {
                BosYerler.set(5, KelimeHarfleri6.get(5));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }

        if (butonsayaci == 6) {
            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri7.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri7.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri7.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri7.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri7.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(6) == '?') {
                BosYerler.set(6, KelimeHarfleri7.get(6));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(5) == '?') {
                BosYerler.set(5, KelimeHarfleri7.get(5));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }

        if (butonsayaci == 7) {
            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri8.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri8.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri8.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri8.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(6) == '?') {
                BosYerler.set(6, KelimeHarfleri8.get(6));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri8.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(5) == '?') {
                BosYerler.set(5, KelimeHarfleri8.get(5));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }

        if (butonsayaci == 8) {
            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri9.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(7) == '?') {
                BosYerler.set(7, KelimeHarfleri9.get(7));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri9.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri9.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri9.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(6) == '?') {
                BosYerler.set(6, KelimeHarfleri9.get(6));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri9.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(5) == '?') {
                BosYerler.set(5, KelimeHarfleri9.get(5));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }
        if (butonsayaci == 9) {

            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri10.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri10.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri10.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri10.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(6) == '?') {
                BosYerler.set(6, KelimeHarfleri10.get(6));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri10.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(5) == '?') {
                BosYerler.set(5, KelimeHarfleri10.get(5));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(7) == '?') {
                BosYerler.set(7, KelimeHarfleri10.get(7));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }

        if (butonsayaci == 10) {

            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri11.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri11.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri11.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri11.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(6) == '?') {
                BosYerler.set(6, KelimeHarfleri11.get(6));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri11.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(5) == '?') {
                BosYerler.set(5, KelimeHarfleri11.get(5));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(8) == '?') {
                BosYerler.set(8, KelimeHarfleri11.get(8));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(7) == '?') {
                BosYerler.set(7, KelimeHarfleri11.get(7));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }
        if (butonsayaci == 11) {

            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri12.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri12.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri12.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri12.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(6) == '?') {
                BosYerler.set(6, KelimeHarfleri12.get(6));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri12.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(5) == '?') {
                BosYerler.set(5, KelimeHarfleri12.get(5));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(8) == '?') {
                BosYerler.set(8, KelimeHarfleri12.get(8));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(7) == '?') {
                BosYerler.set(7, KelimeHarfleri12.get(7));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }
        if (butonsayaci == 12) {

            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri13.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri13.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri13.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri13.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(6) == '?') {
                BosYerler.set(6, KelimeHarfleri13.get(6));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri13.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(5) == '?') {
                BosYerler.set(5, KelimeHarfleri13.get(5));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(8) == '?') {
                BosYerler.set(8, KelimeHarfleri13.get(8));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(7) == '?') {
                BosYerler.set(7, KelimeHarfleri13.get(7));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(9) == '?') {
                BosYerler.set(9, KelimeHarfleri13.get(9));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }
        if (butonsayaci == 13) {

            if (BosYerler.get(1) == '?') {
                BosYerler.set(1, KelimeHarfleri14.get(1));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(0) == '?') {
                BosYerler.set(0, KelimeHarfleri14.get(0));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(3) == '?') {
                BosYerler.set(3, KelimeHarfleri14.get(3));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(2) == '?') {
                BosYerler.set(2, KelimeHarfleri14.get(2));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(6) == '?') {
                BosYerler.set(6, KelimeHarfleri14.get(6));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(4) == '?') {
                BosYerler.set(4, KelimeHarfleri14.get(4));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(5) == '?') {
                BosYerler.set(5, KelimeHarfleri14.get(5));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(8) == '?') {
                BosYerler.set(8, KelimeHarfleri14.get(8));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(7) == '?') {
                BosYerler.set(7, KelimeHarfleri14.get(7));
                gosterilenkelime.setText(BosYerler.toString());
            } else if (BosYerler.get(9) == '?') {
                BosYerler.set(9, KelimeHarfleri14.get(9));
                gosterilenkelime.setText(BosYerler.toString());
            }
        }


    }//GEN-LAST:event_harfalayimActionPerformed

    private void sureyidurdurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sureyidurdurActionPerformed
        /*Sadece süreyi durduracak ve kelimeye özel ayrılan
    20 saniyelik başka bir süre başlatacak. Bu süre bittiğinde otomatik olarak 
    0 puan kazanacak ve yeni soruya atayacak.
         */
        sureyidurdursayac++;
        
        
        sureyidurdur.setEnabled(false);
        harfalayim.setEnabled(false);
        cevabisoyle.setEnabled(true);
        jTextField1.setEnabled(true);
        timer.stop();
        timer2.start();
       
       
        
    }//GEN-LAST:event_sureyidurdurActionPerformed

    private void cevabisoyleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cevabisoyleActionPerformed
        /* Bu buton tahmin edilecek kelimeyi eğer veritabanındaki kelime ile eşitse üretilen puanı ana puanına ekleyecek.
        Eğer bilemezse yeni soruya atayacak
         */
        
        
        
        for (int i = 1; i < 15; i++) {
            
            if (i == 14) {

                if (jTextField1.getText().equals(KelimeAdi.get(13))) {
                    
                        if (BosYerler.contains('?') == false) {
                        yansiyacakpuan = yansiyacakpuan - 1000;
                         yansiyacakpuanimiz.setText(String.valueOf(yansiyacakpuan));
                         
                         if (yansiyacakpuan <0){
                        yansiyacakpuan =0;
                        yansiyacakpuanimiz.setText(String.valueOf(yansiyacakpuan));
                        }
                         JOptionPane.showMessageDialog(null, yansiyacakpuan, "P U A N : ", JOptionPane.OK_OPTION);
                    int input = JOptionPane.showOptionDialog(null, "Oyun Bitti. Bilgilerinizi Kaydetmek İster Misiniz ?", "Kelime Oyunu",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    
                    if (input == JOptionPane.OK_OPTION) {
                        
                       anasayacsuresi=239600;
                        KullaniciEkrani k1 = new KullaniciEkrani();
                        k1.setVisible(true);
                        dispose();
                    } 
                    else if (input == JOptionPane.CANCEL_OPTION)
                    {
                    timer.stop();
                    timer2.stop();
                    sureyidurdur.setEnabled(false);
                    cevabisoyle.setEnabled(false);
                    harfalayim.setEnabled(false);
                    jTextField1.setEnabled(false);
                    }
                    
                     break;    
                    }
                   
                        else {
                      

                    yansiyacakpuanimiz.setText(String.valueOf(yansiyacakpuan));
                    JOptionPane.showMessageDialog(null, yansiyacakpuan, "P U A N : ", JOptionPane.OK_OPTION);
                    int input = JOptionPane.showOptionDialog(null, "Oyun Bitti. Bilgilerinizi Kaydetmek İster Misiniz ?", "Kelime Oyunu",
                            JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    if (input == JOptionPane.OK_OPTION) {
                        
                        KullaniciEkrani k1 = new KullaniciEkrani();
                        k1.setVisible(true);
                        dispose();
                    } 
                    else if (input == JOptionPane.CANCEL_OPTION)
                    {
                    timer.stop();
                    timer2.stop();
                    sureyidurdur.setEnabled(false);
                    cevabisoyle.setEnabled(false);
                    harfalayim.setEnabled(false);
                    jTextField1.setEnabled(false);
                    }
                    
                    
                }
                } } else {
                
                if (jTextField1.getText().equals(KelimeAdi.get(i - 1))) {
                    
                    
                    
                           
                    soru.setText("Soru-" +String.valueOf(i+1));
                    
                   
                    
                    if (sureyidurdursayac == i) {
                       
                        if( BosYerler.contains('?') == false)  
                    {
                         yansiyacakpuan = yansiyacakpuan-KelimePuani.get(i-1)*100;
                         
                        while (yansiyacakpuan <0){
                        yansiyacakpuan =0;
                        yansiyacakpuanimiz.setText(String.valueOf(yansiyacakpuan));
                        };
                       
           }
                         if (kisacevapsuresi != 0 && anasayacsuresi != 0) {
                             
                             while (yansiyacakpuan <0){
                        yansiyacakpuan =0;
                        yansiyacakpuanimiz.setText(String.valueOf(yansiyacakpuan));
                        };
                             yansiyacakpuan += (KelimePuani.get(i-1)-harfbutonsayac)*100;
                    yansiyacakpuanimiz.setText(String.valueOf(yansiyacakpuan));
                    harfbutonsayac = 0;
                        } 
                       
                         else if (yansiyacakpuan != 0) {
                                yansiyacakpuan = yansiyacakpuan-KelimePuani.get(i-1)*100;
                          yansiyacakpuanimiz.setText(String.valueOf(yansiyacakpuan));
                            }
                        
                        else   if (yansiyacakpuan < 0) {
                            yansiyacakpuan =0;
                            yansiyacakpuanimiz.setText(String.valueOf(yansiyacakpuan));
                        }
                        }
                  
                   timer2.stop();
                   kisacevapsuresi = 21000;
                    kisasayac.setText("0");
                    
                         
                         
                        
                    
                    
                    
                    
                    BosYerler.clear();
                    gosterilenkelime.setText("");
                    for (int j = 0; j < KelimePuani.get(i); j++) {
                        BosYerler.add('?');
                    }
                    JOptionPane.showMessageDialog(null, "Doğru Bildiniz.", "Kelime Oyunu", JOptionPane.INFORMATION_MESSAGE);
                     sureyidurdur.setEnabled(true);
                    timer.start();
                   
                    butonsayaci++;
                    kelimetanimi.setText(KelimeAnlami.get(i));

                    if (i != 14) {
                        a = 100 * KelimePuani.get(i);
                        kelimepuani.setText(String.valueOf(a));
                        cevabisoyle.setEnabled(false);
                        jTextField1.setText("");
                        jTextField1.setEnabled(false);
                        harfalayim.setEnabled(true); }}
               
                
 
            }}
        
        
        
    }//GEN-LAST:event_cevabisoyleActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OyunAnaEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OyunAnaEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OyunAnaEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OyunAnaEkran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OyunAnaEkran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel abc;
    private javax.swing.JLabel anasayac;
    private javax.swing.JLabel anotherlabel;
    private javax.swing.JButton cevabisoyle;
    private javax.swing.JLabel gosterilenkelime;
    private javax.swing.JButton harfalayim;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel kelimepuani;
    private javax.swing.JLabel kelimetanimi;
    private javax.swing.JLabel kisasayac;
    private javax.swing.JLabel soru;
    private javax.swing.JButton sureyidurdur;
    private javax.swing.JLabel yansiyacakpuanimiz;
    // End of variables declaration//GEN-END:variables

}
