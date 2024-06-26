package my.casheri;

import com.mycompany.casheri.Database;
import com.mycompany.casheri.Ride;
import com.mycompany.casheri.Trip;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.viewer.WaypointPainter;
import waypoint.EventWaypoint;
import waypoint.MyWaypoint;
import waypoint.WaypointRender;
import javax.swing.ImageIcon;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Timer;
import java.awt.event.ActionListener;

public class RequestRideUI extends javax.swing.JFrame {

    private int driverId = 1; // <------ to change
    private int submit_flag = 0;
    private int clicksLeft = 2;
    private Trip newTrip = new Trip();
    private Ride newRide = new Ride();
    private final Set<MyWaypoint> waypoints = new HashSet<>();
    private EventWaypoint event;
    private String[] tripOptions;
    private String fullName;
    private Integer passenger_id;
    
    
    public RequestRideUI(String fullName, Integer passenger_id) {
        this.getContentPane().setBackground(Color.decode("#FFFFBA"));
        this.fullName = fullName;
        this.passenger_id=passenger_id;
        initComponents();
        jPanel1.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        jButton2.setVisible(false);
        jCheckBox1.setVisible(false);
        initMap();
    }
    
    private String durationEstimate(GeoPosition start, GeoPosition end) {
        return "00:30:54";
    }
    
        private void addWaypoint(MyWaypoint waypoint) {
        for (MyWaypoint d: waypoints) {
            jXMapViewer1.remove(d.getButton());
        }
        waypoints.add(waypoint);
        initWaypoint();
    }
    
    private void initWaypoint() {
        WaypointPainter<MyWaypoint> wp = new WaypointRender();
        wp.setWaypoints(waypoints);
        jXMapViewer1.setOverlayPainter(wp);
        for (MyWaypoint d: waypoints) {
            jXMapViewer1.add(d.getButton());
        }
    }
    
    private void clearWaypoint() {
        for (MyWaypoint d: waypoints) {
            jXMapViewer1.remove(d.getButton());
        }
        waypoints.clear();
        initWaypoint();
    }
    
    private EventWaypoint getEvent() {
        return new EventWaypoint() {
            @Override
            public void selected(MyWaypoint waypoint) {
                JOptionPane.showMessageDialog(RequestRideUI.this, waypoint.getName());
            }
        };
    }
    
    private static double haversine(double lat1, double lon1, double lat2, double lon2) {
    final int R = 6371; // Radius of the earth in kilometers
    double latDistance = Math.toRadians(lat2 - lat1);
    double lonDistance = Math.toRadians(lon2 - lon1);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
             + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
             * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c; // convert to kilometers
    return distance;
}

    
    private List<Trip> getClosestTrips(GeoPosition start, GeoPosition end) {
    List<Trip> trips = new ArrayList<>();
    String query = "SELECT t.*, d.user_id, d.car_model, d.car_id, d.car_color " +
                   "FROM trip t JOIN driver d ON t.driver_id = d.user_id " +
                   "ORDER BY t.date_time ASC;";

    try (Connection con = (new Database()).con();
         Statement stmt = con.createStatement()) {
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            GeoPosition startPos = new GeoPosition(rs.getDouble("start_latitude"), rs.getDouble("start_longitude"));
            GeoPosition endPos = new GeoPosition(rs.getDouble("end_latitude"), rs.getDouble("end_longitude"));
            if (haversine(start.getLatitude(), start.getLongitude(), startPos.getLatitude(), startPos.getLongitude()) <= 5 &&
                haversine(end.getLatitude(), end.getLongitude(), endPos.getLatitude(), endPos.getLongitude()) <= 5) {
                Trip trip = new Trip();
                trip.setId(rs.getInt("id"));
                trip.setDriverId(rs.getInt("driver_id"));
                trip.setDatetime(rs.getString("date_time"));
                trip.setDuration(rs.getString("duration"));
                trip.setCoordStart(startPos);
                trip.setCoordEnd(endPos);
                trip.setPassengerCapacity(rs.getInt("passenger_capacity"));
                trip.setRepeatTrip(rs.getInt("repeat_trip"));
                trips.add(trip);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return trips.isEmpty() ? null : trips;
}




    
    private float calculateCost(GeoPosition start, GeoPosition end) {
        return Math.round((float) ((start.getLatitude()*1000000000 + 
                                    start.getLongitude()*1000000000 + 
                                    end.getLatitude()*1000000000 + 
                                    end.getLongitude()*1000000000))
                                  % 1000) / (float) 100;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        cmdClear1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jXMapViewer1 = new org.jxmapviewer.JXMapViewer();
        jCheckBox1 = new javax.swing.JCheckBox();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        cmdClear1.setBackground(new java.awt.Color(236, 218, 61));
        cmdClear1.setText("Clear Pins");
        cmdClear1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdClear1ActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(236, 218, 61));
        jButton1.setText("Next");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Select Start and End Trip Location");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jPanel1.setBackground(new java.awt.Color(228, 226, 203));

        jLabel2.setText("Drop Off Point:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setText("Location");

        jLabel5.setText("Your Driver:");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel6.setText("Name Driver");

        jLabel8.setText("Time for Pickup:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("8:30 am");

        jLabel10.setText("Pick up Point:");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel11.setText("Location");

        jLabel12.setText("Estimated Arival Time:");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setText("8:47 am (5km)");

        jLabel14.setText("Estimated Cost:");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("1.1 €");

        jButton2.setBackground(new java.awt.Color(255, 255, 120));
        jButton2.setText("Continue to Payment");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)))))
                .addContainerGap(152, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(14, 14, 14))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel3.setText("jLabel3");

        javax.swing.GroupLayout jXMapViewer1Layout = new javax.swing.GroupLayout(jXMapViewer1);
        jXMapViewer1.setLayout(jXMapViewer1Layout);
        jXMapViewer1Layout.setHorizontalGroup(
            jXMapViewer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 284, Short.MAX_VALUE)
        );
        jXMapViewer1Layout.setVerticalGroup(
            jXMapViewer1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );

        jCheckBox1.setText("No Trips");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jXMapViewer1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 62, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(58, 58, 58))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(cmdClear1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBox1)
                .addGap(112, 112, 112))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(129, 129, 129)
                    .addComponent(jLabel3)
                    .addContainerGap(130, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jXMapViewer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(cmdClear1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jCheckBox1)
                .addGap(23, 23, 23))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(139, 139, 139)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(166, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(45, 45, 45)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(17, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(219, 219, 219)
                    .addComponent(jLabel3)
                    .addContainerGap(220, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (submit_flag == 0) {
            if (clicksLeft > 0) {
                JOptionPane.showMessageDialog(this, "Please select both start and end points on the map.");
                return;
            }

            jXMapViewer1.setVisible(false);
            cmdClear1.setVisible(true);
            cmdClear1.setText("Cancel");
            jLabel1.setVisible(false);
            jLabel2.setVisible(false);
            jPanel1.setVisible(false);
            jButton1.setText("Submit");
            submit_flag = 1;
            jLabel2.setVisible(false);
            // Query and display closest trips
        List<Trip> closestTrips = getClosestTrips(newRide.getCoordStart(), newRide.getCoordEnd());
        if (closestTrips == null || closestTrips.isEmpty()) {
            this.dispose(); // Dispose of the current UI to clean up resources

            // Create a non-modal JDialog to show the message
            JDialog dialog = new JDialog();
            dialog.setTitle("Information");
            dialog.setModal(false);
            dialog.setSize(300, 150);
            dialog.setLocationRelativeTo(null);

            // Add a JLabel to display your message
            JLabel messageLabel = new JLabel("No trips available. Check back later.", JLabel.CENTER);
            dialog.add(messageLabel);
            dialog.setVisible(true); // Show the dialog

            // Create a Timer to close the dialog after 2 seconds and then perform further actions
            Timer timer = new Timer(2000, new ActionListener() {
                
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    dialog.dispose(); // Dispose of the dialog box
                    // Assuming jCheckBox1 is accessible, mark it to show no trips available
                    jCheckBox1.setSelected(true);
                    // Open Passenger UI
                    PassengerUI passengerUI = new PassengerUI(fullName, passenger_id);
                    passengerUI.setVisible(true);
                }
            });
            timer.setRepeats(false); // Ensure the timer only runs once
            timer.start(); // Start the timer

            return; // Stop further processing since no trips are available
        }



        tripOptions = new String[closestTrips.size()];
        for (int i = 0; i < closestTrips.size(); i++) {
            Trip trip = closestTrips.get(i);
            tripOptions[i] = "Trip ID: " + trip.getId() +
                             " - Start: (" + trip.getCoordStart().getLatitude() + ", " +
                             trip.getCoordStart().getLongitude() + ")" +
                             " - End: (" + trip.getCoordEnd().getLatitude() + ", " +
                             trip.getCoordEnd().getLongitude() + ")";
        }
            jList1.setListData(tripOptions); // Set list data

            jLabel2.setVisible(false);
            jScrollPane2.setVisible(true);
        } else {
            String selectedTrip = jList1.getSelectedValue();
            if (selectedTrip != null) {
                int selectedTripId = Integer.parseInt(selectedTrip.split(" ")[2]);
                newRide.setTripId(selectedTripId);
            }
            if (selectedTrip == null) {
            JOptionPane.showMessageDialog(this, "Please select a trip from the list before submitting.");
            return; 
        }

            
            submit_flag = submit_flag+1;
            newRide.setDriverId(driverId);
            newRide.setDatetime("2024-12-12 12:12:12");
            newRide.setDuration("00:32:00");
            newRide.setPassengerId(2);
            newRide.setCost(calculateCost(newRide.getCoordStart(), newRide.getCoordEnd()));
            

           if(submit_flag==2){
            jButton1.setVisible(false);
            jButton2.setVisible(true);
            jScrollPane1.setVisible(false);
            jScrollPane2.setVisible(false);
            jPanel1.setVisible(true);
            jLabel2.setVisible(true);
            ImageIcon icon = new ImageIcon("src\\main\\java\\icons\\pin_icon\\user1.png"); 
            jLabel7.setIcon(icon);
           }
 
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        new PaymentPageUI(fullName,passenger_id,newRide).setVisible(true);
            dispose();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cmdClear1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdClear1ActionPerformed
        // TODO add your handling code here:
        if (submit_flag == 0) {
            clearWaypoint();
            clicksLeft = 2;
        } 
        else
        {
        new PassengerUI(fullName,passenger_id).setVisible(true);
        dispose();
        }

    }//GEN-LAST:event_cmdClear1ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void initMap() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        jXMapViewer1.setTileFactory(tileFactory);
        GeoPosition geo = new GeoPosition(38.2469604,21.7356681);
        jXMapViewer1.setAddressLocation(geo);
        jXMapViewer1.setZoom(5);
        
        // Create event mouse move
        MouseInputListener mm = new PanMouseInputListener(jXMapViewer1);
        jXMapViewer1.addMouseListener(mm);
        jXMapViewer1.addMouseMotionListener(mm);
        jXMapViewer1.addMouseWheelListener(new ZoomMouseWheelListenerCenter(jXMapViewer1));
        
        jXMapViewer1.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 1 && e.getButton() == MouseEvent.BUTTON1 && clicksLeft > 0){
                    java.awt.Point p = e.getPoint();
                    GeoPosition geo = jXMapViewer1.convertPointToGeoPosition(p);
                    // System.out.println("X:"+geo.getLatitude()+",Y:"+geo.getLongitude());
                    if (clicksLeft == 2) {
                        addWaypoint(new MyWaypoint("Start", event, new GeoPosition(geo.getLatitude(), geo.getLongitude()), "src\\main\\java\\icons\\pin_icon\\start_pin_1_small.png"));
                        newRide.setCoordStart(new GeoPosition(geo.getLatitude(), geo.getLongitude()));
                    } else if (clicksLeft == 1) {
                        addWaypoint(new MyWaypoint("End", event, new GeoPosition(geo.getLatitude(), geo.getLongitude()), "src\\main\\java\\icons\\pin_icon\\start_pin_1_small.png"));
                        newRide.setCoordEnd(new GeoPosition(geo.getLatitude(), geo.getLongitude()));
                    }
                    clicksLeft -= 1;
                }
            }
        });
        
        event = getEvent();
        
        // hide form


    }


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
            java.util.logging.Logger.getLogger(RequestRideUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RequestRideUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RequestRideUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RequestRideUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RequestRideUI("test",1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdClear1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JList<String> jList1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private org.jxmapviewer.JXMapViewer jXMapViewer1;
    // End of variables declaration//GEN-END:variables

}



