/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * PanelSelectGame.java
 *
 * Created on 02-ene-2012, 23:11:11
 */
package Vista;

import controlador.ControladorCartaAlta;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Properties;
import javax.imageio.ImageIO;
import general.Casino;

/**
 *
 * @author Miguel Angel Fuentes Cardenas
 */
public class PanelSelectGame extends javax.swing.JPanel implements Observer {

    //IMAGEN DE FONDO
    Image imagen;
    ControladorCartaAlta controller;
    Casino casino;
    CasinoView cv;
    
    /** Creates new form PanelSelectGame */
    public PanelSelectGame(int idioma, ControladorCartaAlta controller, Casino casino, CasinoView cv) {
        
        this.cv =cv;
        
        initComponents();
        
        this.controller=controller;
        
        //LEO LA IMAGEN DE FONDO
        try{
        imagen = ImageIO.read(new File("Recursos/login.jpg"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        //CARGO EL IDIOMA
        CargarIdioma(idioma);
        
        //AGREGO A ESTA CLASE COMO OBSERVADORA DE CASINO
        this.casino = casino;
        this.casino.addObserver(this);
        
    }
    
    private void CargarIdioma(int idioma){
        String fichero="";
        InputStream f;
        Properties defProps = new Properties();
        
        switch(idioma){            
            case 0: //ESPANOL
                fichero = "Recursos/lenguajes_es_ES.properties";
                break;
                
            case 1: //INGLES
                fichero = "Recursos/lenguajes_en_GB.properties";
                break;
        }
        
        
        //CONFIGURO LA INTERFAZ EN FUNCION DEL IDIOMA
        try{            
            f = new FileInputStream(fichero);
            defProps.load(f);
            
//            this.LabelUser.setText(defProps.getProperty("user"));
//            this.LabelCalve.setText(defProps.getProperty("password"));
            
            f.close();
        }catch(IOException e){
            System.err.println("Error al cargar idioma por defecto");
        }
    }
    
    
       /**
     * Sobre escribo el metodo pintar para que dibuje el panel con
     * una imagen de fondo.
     * 
     * @param g 
     */
    @Override
    public void paint(Graphics g) 
    {
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);
        } else {
            setOpaque(true);
        }

        super.paint(g);
    }
    

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ListJuegos = new javax.swing.JComboBox();

        jLabel1.setText("Select game:");

        ListJuegos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        ListJuegos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListJuegosActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(276, Short.MAX_VALUE)
                .add(jLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(ListJuegos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 185, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(255, 255, 255))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(226, 226, 226)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(ListJuegos, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .addContainerGap(347, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void ListJuegosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListJuegosActionPerformed
    
    String juego = (String)(this.ListJuegos.getItemAt(this.ListJuegos.getSelectedIndex()));
    
    this.controller.seleccionaJuego(juego,cv);
}//GEN-LAST:event_ListJuegosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox ListJuegos;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object o1) {
        
        ArrayList<String> juegos=null;
        
        if(o == this.casino){
            juegos = casino.getJuegos();
            
            for(int i=0;i<juegos.size();i++){
                this.ListJuegos.setModel(new javax.swing.DefaultComboBoxModel(juegos.toArray()));
            }
        }
    }

  
}
