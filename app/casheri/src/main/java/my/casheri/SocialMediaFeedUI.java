package my.casheri;

import com.mycompany.casheri.Database;
import com.mycompany.casheri.Post;
import com.mycompany.casheri.SocialMediaFeed;
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import social.media.elements.NewPostUI;
import social.media.elements.PostUI;


public class SocialMediaFeedUI extends javax.swing.JFrame {

    private Connection connection;
    private int userId;
    private SocialMediaFeed socialMediaFeed;
    
    public SocialMediaFeedUI(int userId) {
        this.userId = userId;
        initComponents();
        this.getContentPane().setBackground(Color.decode("#FFFFBA"));
        this.connection = (new Database()).con();
        try {
            this.socialMediaFeed = new SocialMediaFeed(100, userId, connection);
            showPosts(socialMediaFeed.getPosts());
        } catch (SQLException ex) {
            System.out.println("Error occurred while creating SocialMediaFeed: " + ex.getMessage());
        }
        // Hide "Create Post" button in passengers
        if("passenger".equals(this.getUserType())){
            newPostButton.setVisible(false);
        }
    }
    
    public void showPosts(ArrayList<Post> posts){
        for (Post post : posts) {
            int numPassengers = post.getPassengers().size();
            PostUI postUI = new PostUI(userId, post.getId(), post.getTitle(), post.getDate(), numPassengers, post.getPhoto(), post.getDescription(), this);
            feed.add(postUI);
        }
        feed.revalidate();
        feed.repaint();
    }
    
    public String getUserType(){
        return socialMediaFeed.getUserType();
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

        backButton.setBackground(new java.awt.Color(236, 218, 61));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        newPostButton.setBackground(new java.awt.Color(236, 218, 61));
        newPostButton.setText("Create Post");
        newPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPostButtonActionPerformed(evt);
            }
        });

        feedScrollPane.setPreferredSize(new java.awt.Dimension(272, 373));

        feed.setBackground(Color.decode("#FFFFBA"));
        feed.setLayout(new javax.swing.BoxLayout(feed, javax.swing.BoxLayout.Y_AXIS));
        feedScrollPane.setViewportView(feed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(feedScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(95, 95, 95)
                .addComponent(newPostButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(feedScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPostButton)
                .addGap(11, 11, 11))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        if("passenger".equals(socialMediaFeed.getUserType())){
            new PassengerUI(userId).setVisible(true);
        }
        else if("driver".equals(socialMediaFeed.getUserType())){
            new DriverMenuUI(userId).setVisible(true);
        }
        dispose();
        
    }//GEN-LAST:event_backButtonActionPerformed

    private void newPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPostButtonActionPerformed
        NewPostUI newPost = new NewPostUI(userId);
        newPost.setVisible(true);
        dispose();
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
                //new SocialMediaFeedUI().setVisible(true);
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
