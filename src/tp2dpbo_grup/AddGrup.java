/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package tp2dpbo_grup;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
/**
 *
 * @author naufal
 */
public class AddGrup extends javax.swing.JFrame {
    private int idGrup;
    private dbConnection db = new dbConnection();
    private Menu Menu;
    String ImagePath = "";
    /**
     * Creates new form AddGrup
     */
    public AddGrup(Menu Menu, int idGrup) {
        initComponents();
        this.Menu = Menu;
        this.idGrup = idGrup;
        ImageIcon imgG = null;
        ResultSet res = db.selectQuery("SELECT * FROM grup WHERE id_grup=" + this.idGrup);
        try {
            if (res.next()) {
                TextNamaGrup.setText(res.getString("nama_grup"));
                ImagePath = "src\\assets\\"+res.getString("foto_grup");
                imgG = new ImageIcon(ImagePath);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddGrup.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image image = imgG.getImage().getScaledInstance(100, 150, Image.SCALE_SMOOTH);
 
        LabelImageGrup.setIcon(new ImageIcon(image));
        LabelImageGrup.setVerticalAlignment(SwingConstants.CENTER);
        if(this.Menu.getIsUpdated()){
            ButtonOk.setText("Update Data");
        }else{
            ButtonOk.setText("Add Data");
        }
    }
    
    public AddGrup(Menu Menu){
        initComponents();
        this.db = new dbConnection();
        this.Menu = Menu;
        if(this.Menu.getIsUpdated()){
            ButtonOk.setText("Update Data");
        }
        else{
            ButtonOk.setText("Add Data");
        }
    }
    
    private void notifikasiUpdated() {
        Menu.onDataUpdated();
    }
    
    private void updateData(){       
        String nama = TextNamaGrup.getText();
        String Path = "src\\assets";
        File file1 = new File(ImagePath);
        String fotoG = this.ImagePath.substring(ImagePath.lastIndexOf('\\') + 1);
        File file2 = new File( Path+"\\"+ fotoG);
        try {
            Files.copy(file1.toPath(), file2.toPath());
        } catch (IOException ex) {
            Logger.getLogger(AddGrup.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (nama.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Data belum lengkap!");
        } else {
            String sql = "UPDATE grup SET nama_grup='" +nama+ "', foto_grup='" +fotoG+ "' WHERE id_grup=" + this.idGrup;
            int affectedRow = db.updateQuery(sql);
            notifikasiUpdated();
            dispose();
            if (affectedRow > 0) {
                JOptionPane.showMessageDialog(null, "Data berhasil diupdate!");
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal diupdate!");
            }
        }
        
    }
    private void addData(){
        String nama = TextNamaGrup.getText();
        String Path = "src\\assets";
        File directory = new File(Path);
        if(!directory.exists()){
            directory.mkdirs();
        }
        File file1 = new File(ImagePath);
        String fotoG = this.ImagePath.substring(ImagePath.lastIndexOf('\\') + 1);
        File file2 = new File( Path+"\\"+ fotoG);
        try {
            Files.copy(file1.toPath(), file2.toPath());
        } catch (IOException ex) {
            Logger.getLogger(AddGrup.class.getName()).log(Level.SEVERE, null, ex);
        } 
        String sql = "INSERT INTO grup(nama_grup, foto_grup) VALUES ('"+nama+"','"+fotoG+"')";
        db.updateQuery(sql);
        
        notifikasiUpdated();
        this.dispose();
        System.out.println("Insert Success!");
        JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelRating = new javax.swing.JLabel();
        ButtonBrowse = new javax.swing.JButton();
        LabelImageGrup = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        TextNamaGrup = new javax.swing.JTextField();
        ButtonOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        labelRating.setForeground(new java.awt.Color(0, 0, 0));
        labelRating.setText("Nama Grup");

        ButtonBrowse.setBackground(new java.awt.Color(102, 204, 255));
        ButtonBrowse.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        ButtonBrowse.setForeground(new java.awt.Color(0, 0, 0));
        ButtonBrowse.setText("Upload");
        ButtonBrowse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonBrowseActionPerformed(evt);
            }
        });

        LabelImageGrup.setBackground(new java.awt.Color(0, 0, 0));
        LabelImageGrup.setText("          Photo");
        LabelImageGrup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Foto");

        TextNamaGrup.setBackground(new java.awt.Color(255, 255, 255));
        TextNamaGrup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextNamaGrupActionPerformed(evt);
            }
        });

        ButtonOk.setBackground(new java.awt.Color(102, 255, 102));
        ButtonOk.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        ButtonOk.setForeground(new java.awt.Color(0, 0, 0));
        ButtonOk.setText("Add");
        ButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel1)
                                .addGap(55, 55, 55))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelRating)
                                .addGap(18, 18, 18)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelImageGrup, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(ButtonBrowse, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(TextNamaGrup, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(310, 310, 310)
                        .addComponent(ButtonOk)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TextNamaGrup, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRating))
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelImageGrup, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonBrowse)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1)))
                .addGap(47, 47, 47)
                .addComponent(ButtonOk)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 4, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonBrowseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonBrowseActionPerformed
        // TODO add your handling code here:
        JFileChooser browseImageFile = new JFileChooser();
        FileNameExtensionFilter fnef = new FileNameExtensionFilter("IMAGES", "png", "jpg", "jpeg");
        browseImageFile.addChoosableFileFilter(fnef);
        int showOpenDialogue = browseImageFile.showOpenDialog(null);

        if (showOpenDialogue == JFileChooser.APPROVE_OPTION) {
            File selectedImageFile = browseImageFile.getSelectedFile();
            ImagePath = selectedImageFile.getAbsolutePath();
            JOptionPane.showMessageDialog(null, ImagePath);
            ImageIcon imgG = new ImageIcon(ImagePath);
            Image image = imgG.getImage().getScaledInstance(LabelImageGrup.getWidth(), LabelImageGrup.getHeight(), Image.SCALE_SMOOTH);

            LabelImageGrup.setIcon(new ImageIcon(image));
        }
    }//GEN-LAST:event_ButtonBrowseActionPerformed

    private void ButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonOkActionPerformed
        // TODO add your handling code here:
        if(Menu.getIsUpdated()){
            updateData();
        }else{
            addData();
        }

    }//GEN-LAST:event_ButtonOkActionPerformed

    private void TextNamaGrupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextNamaGrupActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextNamaGrupActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AddGrup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AddGrup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AddGrup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AddGrup.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new AddGrup().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonBrowse;
    private javax.swing.JButton ButtonOk;
    private javax.swing.JLabel LabelImageGrup;
    private javax.swing.JTextField TextNamaGrup;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelRating;
    // End of variables declaration//GEN-END:variables
}
