package social.media.elements;

import com.mycompany.casheri.Database;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import my.casheri.SocialMediaFeedUI;
import org.jxmapviewer.viewer.GeoPosition;

public class NewPostUI extends javax.swing.JFrame {
    
    private int userId;
    private boolean isFileChooserOpened = false;
    private Connection con = (new Database()).con();

    public NewPostUI(int userId) {
        this.userId = userId;
        initComponents();
        this.getContentPane().setBackground(Color.decode("#FFFFBA")); // Set background color
    }
    
    // Method gia metatropi proorismou se suntetagmenes
    public GeoPosition destinationToCoordinates(String dest){
        return null;
    }
    
    // To kanw stin apo katw methodo 
    public void checkEmptyFields(){
        
    }
    
    public void submitPost(){
        // Insert trip kai post stin db me vasi ta fields pou sumplirwthikan
        try{
            // Collect input data from text fields
            String title = titleField.getText();
            // start kai destination den ginontai insert stin db, kanonika tha kaname 
            // implement tin methodo destinationToCoordinates() gia na paroume coordinates
            String start = startField.getText(); 
            String destination = destinationField.getText();
            String dateTime = dateTimeField.getText();
            String maxPassengersStr = maxPassengersField.getText();
            String description = descriptionField.getText();
            String photoPath = uploadPhotoButton.getText();
            
            // Check if any field is empty
            if (title.equals("Title") || start.equals("Start Location") || destination.equals("Destination") 
                || dateTime.equals("Date and time (YYYY-MM-DD hh:mm:ss)") ||maxPassengersField.getText().equals("Number of passengers") 
                || description.isEmpty() || photoPath.equals("Upload a photo")) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            int maxPassengers = Integer.parseInt(maxPassengersStr);

            String insertTripQuery = "INSERT INTO trip(driver_id,date_time,duration,start_latitude,start_longitude,end_latitude,end_longitude,passenger_capacity,repeat_trip)" +
                                     "VALUES("+userId+",'"+dateTime+"', '"+"00:00:00"+"'," +10.99+", "+10.99+", "+99.99+", "+99.99+", "+maxPassengers+", "+0+")";

            PreparedStatement tripStmt = con.prepareStatement(insertTripQuery, new String[]{"id"});
            tripStmt.executeUpdate();
            
            // Retrieve the generated trip_id
            int tripId = 0;
            ResultSet generatedKeys = tripStmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                tripId = generatedKeys.getInt(1);
            }
            
            Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
            String insertPostQuery = "INSERT INTO post(driver_id, trip_id, post_datetime, post_location_lat, post_location_long, photo, title, max_passengers, description)" +
                                      "VALUES("+userId+","+tripId+", '"+currentTimestamp+"'," +10.99+", "+10.99+", '"+photoPath+"', '"+title+"', "+maxPassengers+", '"+description+"')";
            
            con.createStatement().executeUpdate(insertPostQuery);
            
            // Go back to the Feed
            SocialMediaFeedUI socialMediaFeed = new SocialMediaFeedUI(userId);
            socialMediaFeed.setVisible(true);
            dispose();
        
        }catch(Exception ex){
            ex.printStackTrace();
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

        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        titleField = new javax.swing.JTextField();
        destinationField = new javax.swing.JTextField();
        dateTimeField = new javax.swing.JTextField();
        maxPassengersField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        descriptionField = new javax.swing.JTextArea();
        createPostButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        uploadPhotoButton = new javax.swing.JButton();
        startField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(296, 455));

        backButton.setBackground(new java.awt.Color(236, 218, 61));
        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Describe Your Trip");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Let's embark on a journey of shared mobility!");

        titleField.setText("Title");
        titleField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                titleFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                titleFieldFocusLost(evt);
            }
        });
        titleField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                titleFieldActionPerformed(evt);
            }
        });

        destinationField.setText("Destination");
        destinationField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                destinationFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                destinationFieldFocusLost(evt);
            }
        });
        destinationField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinationFieldActionPerformed(evt);
            }
        });

        dateTimeField.setText("Date and time (YYYY-MM-DD hh:mm:ss)");
        dateTimeField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                dateTimeFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                dateTimeFieldFocusLost(evt);
            }
        });
        dateTimeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateTimeFieldActionPerformed(evt);
            }
        });

        maxPassengersField.setText("Number of passengers");
        maxPassengersField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                maxPassengersFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                maxPassengersFieldFocusLost(evt);
            }
        });
        maxPassengersField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maxPassengersFieldActionPerformed(evt);
            }
        });

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        descriptionField.setColumns(20);
        descriptionField.setRows(5);
        jScrollPane1.setViewportView(descriptionField);

        createPostButton.setBackground(new java.awt.Color(236, 218, 61));
        createPostButton.setText("Create Post");
        createPostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPostButtonActionPerformed(evt);
            }
        });

        jLabel3.setText("Write a small description");

        uploadPhotoButton.setBackground(new java.awt.Color(255, 255, 255));
        uploadPhotoButton.setText("Upload a photo");
        uploadPhotoButton.setAlignmentY(0.0F);
        uploadPhotoButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        uploadPhotoButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        uploadPhotoButton.setIconTextGap(0);
        uploadPhotoButton.setMargin(new java.awt.Insets(2, 0, 2, 14));
        uploadPhotoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadPhotoButtonActionPerformed(evt);
            }
        });

        startField.setText("Start Location");
        startField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                startFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                startFieldFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(createPostButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(titleField)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(destinationField)
                            .addComponent(dateTimeField)
                            .addComponent(maxPassengersField)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1)
                            .addComponent(uploadPhotoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(startField))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(titleField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(startField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(destinationField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dateTimeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(maxPassengersField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(uploadPhotoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createPostButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        SocialMediaFeedUI socialMediaFeed = new SocialMediaFeedUI(userId);
        socialMediaFeed.setVisible(true);
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void createPostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPostButtonActionPerformed
        submitPost();
    }//GEN-LAST:event_createPostButtonActionPerformed

    private void titleFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_titleFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_titleFieldActionPerformed

    private void destinationFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinationFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_destinationFieldActionPerformed

    private void titleFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_titleFieldFocusGained
        if (titleField.getText().equals("Title")) {
            titleField.setText("");
        }
    }//GEN-LAST:event_titleFieldFocusGained

    private void titleFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_titleFieldFocusLost
        if (titleField.getText().isEmpty()) {
            titleField.setText("Title");
        }
    }//GEN-LAST:event_titleFieldFocusLost

    private void destinationFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationFieldFocusGained
        if (destinationField.getText().equals("Destination")) {
            destinationField.setText("");
        }
    }//GEN-LAST:event_destinationFieldFocusGained

    private void destinationFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_destinationFieldFocusLost
        if (destinationField.getText().isEmpty()) {
            destinationField.setText("Destination");
        }
    }//GEN-LAST:event_destinationFieldFocusLost

    private void dateTimeFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateTimeFieldFocusGained
        if (dateTimeField.getText().equals("Date and time (YYYY-MM-DD hh:mm:ss)")) {
            dateTimeField.setText("");
        }
    }//GEN-LAST:event_dateTimeFieldFocusGained

    private void dateTimeFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_dateTimeFieldFocusLost
        if (dateTimeField.getText().isEmpty()) {
            dateTimeField.setText("Date and time (YYYY-MM-DD hh:mm:ss)");
        }
    }//GEN-LAST:event_dateTimeFieldFocusLost

    private void maxPassengersFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maxPassengersFieldFocusGained
        if (maxPassengersField.getText().equals("Number of passengers")) {
            maxPassengersField.setText("");
        }
    }//GEN-LAST:event_maxPassengersFieldFocusGained

    private void maxPassengersFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_maxPassengersFieldFocusLost
        if (maxPassengersField.getText().isEmpty()) {
            maxPassengersField.setText("Number of passengers");
        }
    }//GEN-LAST:event_maxPassengersFieldFocusLost

    private void maxPassengersFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maxPassengersFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_maxPassengersFieldActionPerformed

    private void uploadPhotoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uploadPhotoButtonActionPerformed
        // Open a file choser window to pick an image
        JFileChooser fileChooser = new JFileChooser();
        isFileChooserOpened = true;
        
        // Show the file chooser dialog
        int selectedPhoto = fileChooser.showOpenDialog(this);
        
        if (selectedPhoto == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();

            // Define the destination path (ensure the directory exists)
            java.io.File destDir = new java.io.File("src/main/java/icons/social_media_images");

            // Define the destination file
            java.io.File destFile = new java.io.File(destDir, selectedFile.getName());

            // Copy the file to the destination
            try (java.io.InputStream in = new java.io.FileInputStream(selectedFile);
                 java.io.OutputStream out = new java.io.FileOutputStream(destFile)) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }

                // Update the text field with the path of the saved file
                uploadPhotoButton.setText(selectedFile.getName());

            } catch (java.io.IOException e) {
                e.printStackTrace();
                javax.swing.JOptionPane.showMessageDialog(this, "Error saving the file: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_uploadPhotoButtonActionPerformed

    private void dateTimeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateTimeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dateTimeFieldActionPerformed

    private void startFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_startFieldFocusGained
        if (startField.getText().equals("Start Location")) {
            startField.setText("");
        }
    }//GEN-LAST:event_startFieldFocusGained

    private void startFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_startFieldFocusLost
        if (startField.getText().isEmpty()) {
            startField.setText("Start Location");
        }
    }//GEN-LAST:event_startFieldFocusLost

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
            java.util.logging.Logger.getLogger(NewPostUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewPostUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewPostUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewPostUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new NewPostUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton createPostButton;
    private javax.swing.JTextField dateTimeField;
    private javax.swing.JTextArea descriptionField;
    private javax.swing.JTextField destinationField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField maxPassengersField;
    private javax.swing.JTextField startField;
    private javax.swing.JTextField titleField;
    private javax.swing.JButton uploadPhotoButton;
    // End of variables declaration//GEN-END:variables
}
