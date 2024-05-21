/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package my.casheri;

import com.mycompany.casheri.Post;
import com.mycompany.casheri.SocialMediaFeed;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import social.media.elements.PostUI;

/**
 *
 * @author Greg
 */
public class SocialMediaFeedUI extends javax.swing.JFrame {
    /**
     * Creates new form SocialMediaFeedUI
     */
    
    private Connection connection;
    private SocialMediaFeed socialMediaFeed;
    
    public SocialMediaFeedUI() {
        initComponents();
        this.connection = casheriUI.connection;
        try {
            this.socialMediaFeed = new SocialMediaFeed(100, 1, connection);
            showPosts(socialMediaFeed.getPosts());
        } catch (SQLException ex) {
            System.out.println("Error occurred while creating SocialMediaFeed: " + ex.getMessage());
        }
    }
    
    public void showPosts(ArrayList<Post> posts){
        for (Post post : posts) {
            int numPassengers = post.getPassengers().size();
            PostUI postUI = new PostUI(post.getTitle(), post.getDate(), numPassengers, post.getPhoto());
            feed.add(postUI);
        }
        feed.revalidate();
        feed.repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        newPostButton = new javax.swing.JButton();
        feedScrollPane = new javax.swing.JScrollPane();
        feed = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(296, 455));
        setSize(new java.awt.Dimension(296, 455));

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        newPostButton.setText("New");
        newPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPostButtonActionPerformed(evt);
            }
        });

        feedScrollPane.setPreferredSize(new java.awt.Dimension(272, 373));

        feed.setLayout(new javax.swing.BoxLayout(feed, javax.swing.BoxLayout.Y_AXIS));
        feedScrollPane.setViewportView(feed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(newPostButton))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(backButton)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(feedScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(feedScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addComponent(newPostButton)
                .addGap(5, 5, 5))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        this.setVisible(false);
        new casheriUI().setVisible(true);
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void newPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPostButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newPostButtonActionPerformed

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
            java.util.logging.Logger.getLogger(SocialMediaFeedUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SocialMediaFeedUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SocialMediaFeedUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SocialMediaFeedUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SocialMediaFeedUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JPanel feed;
    private javax.swing.JScrollPane feedScrollPane;
    private javax.swing.JButton newPostButton;
    // End of variables declaration//GEN-END:variables
}
