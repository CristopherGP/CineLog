/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;
import conexion.Conexion;
import modelo.ComboItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import javax.swing.ImageIcon;
import javax.swing.Icon;

import java.awt.Image;

/**
 *
 * @author Cristopher GP
 */
public class VistaDirectores extends javax.swing.JPanel {
String rol;
    /**
     * Creates new form VistaDirectores
     */
    public VistaDirectores(String rol) {
        initComponents();
        lblFoto.setBounds(650, 120, 250, 250);
        
        this.rol = rol;

    cargarDirectores();
    }

    private void cargarDirectores(){

    try{

        Connection con = Conexion.conectar();

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery(
            "SELECT * FROM directores"
        );

        cbDirector.removeAllItems();

        while(rs.next()){

            cbDirector.addItem(

                new ComboItem(

                    rs.getInt("id_director"),
                    rs.getString("nombre")
                )
            );
        }

    }catch(Exception e){

        JOptionPane.showMessageDialog(this,
                "Error");
    }
}
    
    private void cargarPeliculasDirector(){

    DefaultTableModel modelo =
            new DefaultTableModel();

    modelo.addColumn("Película");
    modelo.addColumn("Año");
    modelo.addColumn("Clasificación");
    modelo.addColumn("Duración");
    modelo.addColumn("Director");

    tablaPeliculas.setModel(modelo);

    try{

        ComboItem director =
                (ComboItem) cbDirector.getSelectedItem();

        Connection con = Conexion.conectar();

        PreparedStatement ps =
                con.prepareStatement(

            "SELECT * FROM vista_peliculas_directores " +
"WHERE id_director=?"
        );

        ps.setInt(1, director.getId());

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            Object[] fila = new Object[5];

            fila[0] = rs.getString("titulo");
            fila[1] = rs.getInt("anio");
            fila[2] = rs.getString("clasificacion");
            fila[3] = rs.getInt("duracion") + " min";
            fila[4] = rs.getString("director");

            modelo.addRow(fila);
        }

    }catch(Exception e){

        JOptionPane.showMessageDialog(this,
                "Error");
    }
}
    private void mostrarInfoDirector(){

    try{

        ComboItem director =
                (ComboItem) cbDirector.getSelectedItem();

        Connection con = Conexion.conectar();

        PreparedStatement ps =
                con.prepareStatement(

            "SELECT COUNT(*) total " +

            "FROM peliculas " +

            "WHERE id_director=?"
        );

        ps.setInt(1, director.getId());

        ResultSet rs = ps.executeQuery();

        if(rs.next()){

            txtInfo.setText(

                "Director: " +

                director.toString() +

                "\n\nPelículas dirigidas: " +

                rs.getInt("total")
            );
        }

    }catch(Exception e){

    }
}
   private void cargarFotoDirector(){

    try{

        ComboItem director =
                (ComboItem) cbDirector.getSelectedItem();

        String nombre =
                director.toString().trim();

        String ruta = "";

        switch(nombre){

            case "Christopher Nolan":

                ruta =
                "/imagenes_directores/Nolan.jpg";

                break;

            case "Quentin Tarantino":

                ruta =
                "/imagenes_directores/Tarantino.jpg";

                break;

            case "Guillermo del Toro":

                ruta =
                "/imagenes_directores/Toro.jpg";

                break;
                
                case "Greta Gerwig":

                ruta =
                "/imagenes_directores/Greta.jpg";

                break;
                 case "Steven Spielberg":

                ruta =
                "/imagenes_directores/Steven.jpg";

                break;
                
        }

        java.net.URL url =
                getClass().getResource(ruta);

        ImageIcon icono =
                new ImageIcon(url);

        Image imagen =
                icono.getImage().getScaledInstance(

                    lblFoto.getWidth(),
                    lblFoto.getHeight(),

                    Image.SCALE_SMOOTH
                );

        lblFoto.setIcon(
                new ImageIcon(imagen)
        );
    }catch(Exception e){

        JOptionPane.showMessageDialog(this,
                e.toString());
    }
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cbDirector = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtInfo = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPeliculas = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblFoto = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 200, 0));
        jLabel1.setText("Directores de Peliculas");

        cbDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDirectorActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 200, 0));
        jLabel2.setText("Directores:");

        txtInfo.setColumns(20);
        txtInfo.setRows(5);
        jScrollPane1.setViewportView(txtInfo);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 200, 0));
        jLabel3.setText("Información:");

        tablaPeliculas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaPeliculas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaPeliculasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaPeliculas);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 200, 0));
        jLabel4.setText("Peliculas dirigidas:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 200, 0));
        jLabel5.setText("Director");

        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5)
                        .addGap(124, 124, 124))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(496, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                            .addComponent(cbDirector, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tablaPeliculasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaPeliculasMouseClicked
         int fila = tablaPeliculas.getSelectedRow();
    }//GEN-LAST:event_tablaPeliculasMouseClicked

    private void cbDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDirectorActionPerformed
        cargarPeliculasDirector();

    mostrarInfoDirector();
    cargarFotoDirector();
    }//GEN-LAST:event_cbDirectorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<ComboItem> cbDirector;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JTable tablaPeliculas;
    private javax.swing.JTextArea txtInfo;
    // End of variables declaration//GEN-END:variables
}
