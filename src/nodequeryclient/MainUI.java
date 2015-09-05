/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodequeryclient;

import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert
 */
public class MainUI extends javax.swing.JFrame {

    /**
     * Creates new form MainUI
     */
    public MainUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        updAccBtn = new javax.swing.JButton();
        setKeybtn = new javax.swing.JButton();
        keyFld = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        NmStatic = new javax.swing.JLabel();
        tzStatic = new javax.swing.JLabel();
        lblTz = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ShowAccStats = new javax.swing.JToggleButton();
        updServerDatabtn = new javax.swing.JButton();
        ShowServStats = new javax.swing.JButton();
        ShowServTBL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblName.setText("_____");

        updAccBtn.setText("Update Account Data");
        updAccBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updAccBtnActionPerformed(evt);
            }
        });

        setKeybtn.setText("Set");
        setKeybtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setKeybtnActionPerformed(evt);
            }
        });

        keyFld.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        keyFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyFldActionPerformed(evt);
            }
        });

        jLabel1.setText("Please Enter your API key below");

        NmStatic.setText("Name:");

        tzStatic.setText("Time Zone:");

        lblTz.setText("_____");

        ShowAccStats.setText("Show Account Statistics");
        ShowAccStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowAccStatsActionPerformed(evt);
            }
        });

        updServerDatabtn.setText("Update All Server Data");
        updServerDatabtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updServerDatabtnActionPerformed(evt);
            }
        });

        ShowServStats.setText("Show Server Statistics");
        ShowServStats.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowServStatsActionPerformed(evt);
            }
        });

        ShowServTBL.setText("Show Server Table");
        ShowServTBL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowServTBLActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(keyFld, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(setKeybtn)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(62, 62, 62))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NmStatic, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(tzStatic, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblName)
                                    .addComponent(lblTz))
                                .addGap(50, 50, 50))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(ShowAccStats)
                                .addGap(18, 18, 18)
                                .addComponent(updAccBtn))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(updServerDatabtn)
                                    .addComponent(ShowServStats))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ShowServTBL)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(updServerDatabtn)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(updAccBtn)
                            .addComponent(ShowAccStats))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel1)
                                .addGap(3, 3, 3)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(keyFld, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(setKeybtn)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblName)
                                    .addComponent(NmStatic))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tzStatic)
                                    .addComponent(lblTz))))
                        .addGap(65, 65, 65)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ShowServStats)
                    .addComponent(ShowServTBL))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        lblName.getAccessibleContext().setAccessibleName("lblName");
        lblName.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void updAccBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updAccBtnActionPerformed
        String Name=DB.getFromAccDBString("TBLACCOUNT", "NAME", 1);
        lblName.setText(Name);
        lblTz.setText(DB.getFromAccDBString("TBLACCOUNT", "TIMEZONE", 1));
    }//GEN-LAST:event_updAccBtnActionPerformed

    private void setKeybtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setKeybtnActionPerformed
       // DB.setAPIkey(keyFld.getText());
        DB.setAPIkey(keyFld.getText());
        Account ac2=new Account();
        
        Account ac7=new Account();
        try {
            ac7.getRequests();
        } catch (HTTPstatusException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ac7.InsertAccount();
        } catch (SQLException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (HTTPstatusException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            ServerList sl=new ServerList();
        } catch (SQLException ex) {
            Logger.getLogger(MainUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_setKeybtnActionPerformed

    private void keyFldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyFldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_keyFldActionPerformed

    private void ShowAccStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowAccStatsActionPerformed
        AccountStats acstat=new AccountStats();
        //acstat.setVisible(true);
        //AccountStats.launch("");
        //acstat.showWindow();
         javafx.application.Application.launch(AccountStats.class);

        
    }//GEN-LAST:event_ShowAccStatsActionPerformed

    private void updServerDatabtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updServerDatabtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updServerDatabtnActionPerformed

    private void ShowServStatsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowServStatsActionPerformed
//        ServerStats servstat=new ServerStats();
       // servstat.setVisible(true);
         javafx.application.Application.launch(ServerStats.class);
    }//GEN-LAST:event_ShowServStatsActionPerformed

    private void ShowServTBLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowServTBLActionPerformed
        ServerData sd=new ServerData();
        sd.setVisible(true);
    }//GEN-LAST:event_ShowServTBLActionPerformed

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
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainUI().setVisible(true);
            }
        });
        DB db=new DB();
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel NmStatic;
    private javax.swing.JToggleButton ShowAccStats;
    private javax.swing.JButton ShowServStats;
    private javax.swing.JButton ShowServTBL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPasswordField keyFld;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblTz;
    private javax.swing.JButton setKeybtn;
    private javax.swing.JLabel tzStatic;
    private javax.swing.JButton updAccBtn;
    private javax.swing.JButton updServerDatabtn;
    // End of variables declaration//GEN-END:variables

}
