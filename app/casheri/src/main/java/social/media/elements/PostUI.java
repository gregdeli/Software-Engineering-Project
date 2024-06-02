package social.media.elements;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import my.casheri.SocialMediaFeedUI;

class ImageUtil {    
    public static ImageIcon getScaledIcon(String path, int width, int height) {
        // Load the image from the path
        ImageIcon icon = new ImageIcon(path);

        // Get the image from the icon
        Image img = icon.getImage();

        // Scale the image to the specified width and height
        Image scaledImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Return the new ImageIcon
        return new ImageIcon(scaledImg);
    }
}

public class PostUI extends javax.swing.JPanel {

    /**
     * Creates new form PostUI
     */
    private int userId;
    private int postId;
    private String title;
    private String date;
    private String numPassengers;
    private String imagePath;
    private String description;
    
    private SocialMediaFeedUI socialMediaFeed;
    
    public PostUI(int userId, int postId, String title, LocalDate date, int numPassengers, String postImage, String description, SocialMediaFeedUI socialMediaFeed) {
        this.userId = userId;
        this.title = title;
        this.postId = postId;
        this.numPassengers = Integer.toString(numPassengers);
        // Gia to date 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");
        this.date = date.format(formatter);
        this.imagePath = "src/main/java/icons/social_media_images/" + postImage;
        this.description = description;
        initComponents();
        this.setBackground(Color.decode("#FFFFBA"));
        postHeader.setBackground(Color.decode("#FFFFBA"));
        tripPhoto.setIcon(ImageUtil.getScaledIcon(this.imagePath, 245, 133));
        
        this.socialMediaFeed = socialMediaFeed;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        postHeader = new javax.swing.JPanel();
        destinationLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        numPassengersLabel = new javax.swing.JLabel();
        tripPhoto = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(220, 214));

        postHeader.setPreferredSize(new java.awt.Dimension(15, 50));

        destinationLabel.setText(title);

        dateLabel.setText(date);

        numPassengersLabel.setText(numPassengers);

        javax.swing.GroupLayout postHeaderLayout = new javax.swing.GroupLayout(postHeader);
        postHeader.setLayout(postHeaderLayout);
        postHeaderLayout.setHorizontalGroup(
            postHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(postHeaderLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addComponent(destinationLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(dateLabel)
                .addGap(18, 18, 18)
                .addComponent(numPassengersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        postHeaderLayout.setVerticalGroup(
            postHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(postHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(postHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destinationLabel)
                    .addComponent(dateLabel)
                    .addComponent(numPassengersLabel))
                .addContainerGap())
        );

        tripPhoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tripPhotoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tripPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(postHeader, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(postHeader, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tripPhoto, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tripPhotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tripPhotoMouseClicked
        String userType = socialMediaFeed.getUserType();
        PostDetailsUI postDetails = new PostDetailsUI(userId, postId, title, date, numPassengers, imagePath, description, userType);
        postDetails.setVisible(true);
        socialMediaFeed.setVisible(false);
    }//GEN-LAST:event_tripPhotoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel dateLabel;
    private javax.swing.JLabel destinationLabel;
    private javax.swing.JLabel numPassengersLabel;
    private javax.swing.JPanel postHeader;
    private javax.swing.JLabel tripPhoto;
    // End of variables declaration//GEN-END:variables
}
