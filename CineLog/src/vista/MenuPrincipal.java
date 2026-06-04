/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.BorderLayout;
import java.io.FileOutputStream;
import java.awt.Image;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import conexion.Conexion;
import java.awt.Desktop;
import java.io.File;
import com.itextpdf.text.Font;
import com.itextpdf.text.Element;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.PageSize;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Cristopher GP
 */
public class MenuPrincipal extends javax.swing.JFrame {
 String rol;
    String nombre;
    
    public MenuPrincipal(String rol, String nombre) {
    initComponents();
    panelContenido.setBackground(Color.BLACK);
    panelMenu.setBackground(
        new Color(120, 0, 0)
);
    
    java.net.URL rutaLogo =
        getClass().getResource(
            "/imagenes/logo.png"
        );

if(rutaLogo != null){

    ImageIcon logoOriginal =
        new ImageIcon(rutaLogo);

Image imagen =
        logoOriginal.getImage()
        .getScaledInstance(

            400,
            400,

            Image.SCALE_SMOOTH
        );

ImageIcon logo =
        new ImageIcon(imagen);

JLabel lblLogo =
        new JLabel(logo);

lblLogo.setHorizontalAlignment(
        JLabel.CENTER
);

panelContenido.setLayout(
        new BorderLayout()
);

JPanel panelLogo =
        new JPanel(
            new java.awt.GridBagLayout()
        );

panelLogo.setBackground(Color.BLACK);

panelLogo.add(lblLogo);

panelContenido.add(
        panelLogo,
        BorderLayout.CENTER
);

}else{

    JOptionPane.showMessageDialog(this,
            "No se encontró logo.png");
}
    setLocationRelativeTo(null);

    this.rol = rol;
    this.nombre = nombre;

    setLocationRelativeTo(null);
    
    lblUsuario.setText("Usuario: " + nombre + " (" + rol + ")");

    configurarMenu(); // 🔥 IMPORTANTE
}
    
    private void configurarMenu(){

    if(rol.equals("consultor")){
        btnUsuarios.setEnabled(false);
        btnPeliculas.setEnabled(true); // solo ver
        btnReporte.setEnabled(false);
    }

    if(rol.equals("operador")){
        btnUsuarios.setEnabled(false);
        btnPeliculas.setEnabled(true); // solo ver
        btnResenas.setEnabled(true); // solo ver
    }

    if(rol.equals("admin")){
        // no se bloquea nada
    }

    
}
    /**
     * Creates new form MenuPrincipal
     */
    public MenuPrincipal() {
        initComponents();
        lblUsuario.setText("Usuario: " + nombre + " (" + rol + ")"); 
        estiloBotones();
    }
    
    private void estiloBotones() {
    java.awt.Color fondo = new java.awt.Color(33, 47, 61);
    java.awt.Color texto = java.awt.Color.WHITE;

    javax.swing.JButton[] botones = {
        btnPeliculas, btnDirectores,
        btnGeneros, btnResenas, btnUsuarios
    };

    for(javax.swing.JButton btn : botones){
        btn.setBackground(fondo);
        btn.setForeground(texto);
        btn.setFocusPainted(false);
        btn.setBorder(null);
    }
}
private void mostrarPanel(javax.swing.JPanel p){
    p.setSize(panelContenido.getWidth(), panelContenido.getHeight());
    p.setLocation(0, 0);

    panelContenido.removeAll();
    panelContenido.add(p);
    panelContenido.revalidate();
    panelContenido.repaint();
}
private void generarPDF(){

    try{

        Document documento =
        new Document(
            PageSize.A4.rotate()
        );
  
String fechaArchivo =
    new SimpleDateFormat(
        "yyyyMMdd_HHmmss"
    ).format(new Date());

String rutaPDF =
"C:\\Users\\Cristopher GP\\Documents\\reporte_peliculas_"
+ fechaArchivo +
".pdf";
        PdfWriter.getInstance(

    documento,

    new FileOutputStream(
        rutaPDF
    )
);

        documento.open();

        try{

    com.itextpdf.text.Image logo =
       com.itextpdf.text.Image.getInstance(

            getClass().getResource(
                "/imagenes/logo.png"
            )
        );

    logo.scaleToFit(350, 350);

    logo.setAlignment(
            Element.ALIGN_CENTER
    );

    documento.add(logo);

}catch(Exception e){

}
        Font appFont =
        new Font(

            Font.FontFamily.HELVETICA,
            28,
            Font.BOLD
        );

Paragraph app =
        new Paragraph(

            "CINELOG",
            appFont
        );

app.setAlignment(
        Element.ALIGN_CENTER
);

app.setSpacingAfter(10);

documento.add(app);
Paragraph integrantes =
        new Paragraph(

            "Equipo de trabajo:\n\n" +

            "• Cristopher Guerrero Puente\n" +
            "• Ana Paulina Lopez Valenzuela\n" +
            "• Omar Grimaldo Barbosa\n" +
            "• Johana Elizabeth Torres Garcia\n" +
            "• Diego Francisco Felix Garza\n\n"
        );

integrantes.setAlignment(
        Element.ALIGN_CENTER
);

integrantes.setSpacingAfter(10);

documento.add(integrantes);
String fechaActual =

    new SimpleDateFormat(
            "dd/MM/yyyy HH:mm"
    ).format(new Date());

Paragraph fecha =
        new Paragraph(

            "Fecha de generación: "
            + fechaActual + "\n\n"
        );

fecha.setAlignment(
        Element.ALIGN_RIGHT
);

documento.add(fecha);
Paragraph usuario =
        new Paragraph(

            "Generado por: " +
            nombre + "\n\n"
        );

usuario.setAlignment(
        Element.ALIGN_RIGHT
);

documento.add(usuario);
       Font tituloFont =
        new Font(

            Font.FontFamily.HELVETICA,
            22,
            Font.BOLD
        );

Paragraph titulo =
        new Paragraph(

            "REPORTE DE PELÍCULAS",
            tituloFont
        );

titulo.setAlignment(
        Element.ALIGN_CENTER
);

titulo.setSpacingAfter(20);

documento.add(titulo);

        PdfPTable tabla =
                new PdfPTable(9);
        
        tabla.setWidthPercentage(100);

tabla.setSpacingBefore(10);

tabla.setSpacingAfter(10);

        Font encabezadoFont =
        new Font(

            Font.FontFamily.HELVETICA,
            12,
            Font.BOLD
        );

String[] encabezados = {

    "Título",
    "Género",
    "Director",
    "Año",
    "Clasificación",
    "Usuario",
    "Calificación",
    "Comentario",
    "Fecha"
};

for(String texto : encabezados){

    PdfPCell celda =
            new PdfPCell(

                new Phrase(
                    texto,
                    encabezadoFont
                )
            );

    celda.setHorizontalAlignment(
            Element.ALIGN_CENTER
    );

    celda.setBackgroundColor(
            BaseColor.LIGHT_GRAY
    );

    celda.setPadding(5);

    tabla.addCell(celda);
}

        Connection con =
                Conexion.conectar();

        Statement st =
                con.createStatement();

        ResultSet rs =
                st.executeQuery(

            "SELECT p.titulo, " +
"g.nombre genero, " +
"d.nombre director, " +
"p.anio, " +
"p.clasificacion, " +
"r.usuario, " +
"r.calificacion, " +
"r.comentario, " +
"r.fecha " +

"FROM peliculas p " +

"INNER JOIN generos g " +
"ON p.id_genero = g.id_genero " +

"INNER JOIN directores d " +
"ON p.id_director = d.id_director " +

"LEFT JOIN resenas r " +
"ON p.id_pelicula = r.id_pelicula"
        );

        while(rs.next()){

            tabla.addCell(
    rs.getString("titulo")
);

tabla.addCell(
    rs.getString("genero")
);

tabla.addCell(
    rs.getString("director")
);

tabla.addCell(
    rs.getString("anio")
);

tabla.addCell(
    rs.getString("clasificacion")
);

tabla.addCell(
    rs.getString("usuario")
);

tabla.addCell(
    rs.getString("calificacion")
);

tabla.addCell(
    rs.getString("comentario")
);

tabla.addCell(
    rs.getString("fecha")
);
        }

        documento.add(tabla);
Paragraph estadisticas =
        new Paragraph(

            "\n\nRESUMEN ESTADÍSTICO\n\n"
        );

estadisticas.setAlignment(
        Element.ALIGN_CENTER
);

documento.add(estadisticas);
Statement st2 =
        con.createStatement();

ResultSet rs2 =
        st2.executeQuery(

            "SELECT COUNT(*) total " +
            "FROM peliculas"
        );

if(rs2.next()){

    documento.add(

        new Paragraph(

            "Total de películas: "
            + rs2.getInt("total")
        )
    );
}
ResultSet rsResenas =
    st2.executeQuery(

        "SELECT COUNT(*) total " +
        "FROM resenas"
    );

if(rsResenas.next()){

    documento.add(

        new Paragraph(

            "Total de reseñas: "
            + rsResenas.getInt("total")
        )
    );
}
ResultSet rsPromedio =
    st2.executeQuery(

        "SELECT AVG(calificacion) promedio " +
        "FROM resenas"
    );

if(rsPromedio.next()){

    documento.add(

        new Paragraph(

            "Promedio de calificación: "
            + String.format("%.2f",
                    rsPromedio.getDouble("promedio"))
        )
    );
}
documento.add(

    new Paragraph(

        "\nPelículas por género:\n"
    )
);
ResultSet rsGenero =
    st2.executeQuery(

        "SELECT g.nombre, " +
        "COUNT(*) cantidad " +

        "FROM peliculas p " +

        "INNER JOIN generos g " +
        "ON p.id_genero = g.id_genero " +

        "GROUP BY g.nombre"
    );

while(rsGenero.next()){

    documento.add(

        new Paragraph(

            rsGenero.getString("nombre")
            + ": "
            + rsGenero.getInt("cantidad")
        )
    );
}
        documento.close();

        JOptionPane.showMessageDialog(this,
        "PDF generado en:\n" + rutaPDF);

java.awt.Desktop.getDesktop().open(

    new java.io.File(rutaPDF)
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

        panelMenu = new javax.swing.JPanel();
        btnPeliculas = new javax.swing.JButton();
        btnDirectores = new javax.swing.JButton();
        btnGeneros = new javax.swing.JButton();
        btnUsuarios = new javax.swing.JButton();
        btnResenas = new javax.swing.JButton();
        lblUsuario = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnCerrarSesion = new javax.swing.JButton();
        btnReporte = new javax.swing.JButton();
        panelContenido = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelMenu.setBackground(new java.awt.Color(204, 204, 204));

        btnPeliculas.setBackground(new java.awt.Color(0, 0, 0));
        btnPeliculas.setForeground(new java.awt.Color(255, 200, 0));
        btnPeliculas.setText("Peliculas");
        btnPeliculas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPeliculasActionPerformed(evt);
            }
        });

        btnDirectores.setBackground(new java.awt.Color(0, 0, 0));
        btnDirectores.setForeground(new java.awt.Color(255, 209, 0));
        btnDirectores.setText("Directores");
        btnDirectores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDirectoresActionPerformed(evt);
            }
        });

        btnGeneros.setBackground(new java.awt.Color(0, 0, 0));
        btnGeneros.setForeground(new java.awt.Color(255, 209, 0));
        btnGeneros.setText("Generos");
        btnGeneros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerosActionPerformed(evt);
            }
        });

        btnUsuarios.setBackground(new java.awt.Color(0, 0, 0));
        btnUsuarios.setForeground(new java.awt.Color(255, 200, 0));
        btnUsuarios.setText("Usuarios");
        btnUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUsuariosActionPerformed(evt);
            }
        });

        btnResenas.setBackground(new java.awt.Color(0, 0, 0));
        btnResenas.setForeground(new java.awt.Color(255, 200, 0));
        btnResenas.setText("Reseñas");
        btnResenas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResenasActionPerformed(evt);
            }
        });

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(255, 200, 0));
        lblUsuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblUsuario.setText("Usuario: ");

        jLabel1.setFont(new java.awt.Font("Palatino Linotype", 0, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 200, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("CINELOG");

        btnCerrarSesion.setBackground(new java.awt.Color(0, 0, 0));
        btnCerrarSesion.setForeground(new java.awt.Color(255, 200, 0));
        btnCerrarSesion.setText("Cerrar Sesion");
        btnCerrarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSesionActionPerformed(evt);
            }
        });

        btnReporte.setBackground(new java.awt.Color(0, 0, 0));
        btnReporte.setForeground(new java.awt.Color(255, 200, 0));
        btnReporte.setText("Generar PDF");
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelMenuLayout = new javax.swing.GroupLayout(panelMenu);
        panelMenu.setLayout(panelMenuLayout);
        panelMenuLayout.setHorizontalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnResenas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnGeneros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDirectores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPeliculas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(28, 28, 28))
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                    .addComponent(lblUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelMenuLayout.createSequentialGroup()
                        .addGroup(panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCerrarSesion, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReporte, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelMenuLayout.setVerticalGroup(
            panelMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPeliculas)
                .addGap(18, 18, 18)
                .addComponent(btnDirectores)
                .addGap(18, 18, 18)
                .addComponent(btnGeneros)
                .addGap(18, 18, 18)
                .addComponent(btnResenas)
                .addGap(18, 18, 18)
                .addComponent(btnUsuarios)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                .addComponent(btnReporte)
                .addGap(18, 18, 18)
                .addComponent(btnCerrarSesion)
                .addGap(18, 18, 18)
                .addComponent(lblUsuario)
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout panelContenidoLayout = new javax.swing.GroupLayout(panelContenido);
        panelContenido.setLayout(panelContenidoLayout);
        panelContenidoLayout.setHorizontalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 617, Short.MAX_VALUE)
        );
        panelContenidoLayout.setVerticalGroup(
            panelContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPeliculasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPeliculasActionPerformed
        VistaPeliculas vp = new VistaPeliculas(rol);
   mostrarPanel(new VistaPeliculas(rol));
    }//GEN-LAST:event_btnPeliculasActionPerformed

    private void btnDirectoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDirectoresActionPerformed
         VistaDirectores vp = new VistaDirectores(rol);
   mostrarPanel(new VistaDirectores(rol));
    }//GEN-LAST:event_btnDirectoresActionPerformed

    private void btnGenerosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerosActionPerformed
          mostrarPanel(
        new VistaGeneros(rol)
    );
    }//GEN-LAST:event_btnGenerosActionPerformed

    private void btnResenasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResenasActionPerformed
         mostrarPanel(
        new VistaResenas(rol, nombre));
    }//GEN-LAST:event_btnResenasActionPerformed

    private void btnUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUsuariosActionPerformed
         VistaUsuarios vp = new VistaUsuarios(rol);
   mostrarPanel(new VistaUsuarios(rol));
    }//GEN-LAST:event_btnUsuariosActionPerformed

    private void btnCerrarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSesionActionPerformed
         int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Seguro que deseas cerrar sesión?");

    if(confirm == 0){

        // Abrir login
        Login login = new Login();
        login.setVisible(true);

        // Cerrar menú actual
        this.dispose();
    }
    }//GEN-LAST:event_btnCerrarSesionActionPerformed

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        generarPDF();
    }//GEN-LAST:event_btnReporteActionPerformed

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
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrarSesion;
    private javax.swing.JButton btnDirectores;
    private javax.swing.JButton btnGeneros;
    private javax.swing.JButton btnPeliculas;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton btnResenas;
    private javax.swing.JButton btnUsuarios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel panelContenido;
    private javax.swing.JPanel panelMenu;
    // End of variables declaration//GEN-END:variables
}
