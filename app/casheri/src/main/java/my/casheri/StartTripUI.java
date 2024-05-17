
package my.casheri;
import org.jxmapviewer.viewer.DefaultTileFactory;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import com.mycompany.casheri.Database;
import com.mycompany.casheri.Point;
import com.mycompany.casheri.RoutePainter;
import com.mycompany.casheri.RoutingData;
import com.mycompany.casheri.RoutingService;
import com.mycompany.casheri.Map;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.util.List;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.DefaultWaypointRenderer;

public class StartTripUI extends javax.swing.JFrame {

    private List<RoutingData> routingData = new ArrayList<>();

    public StartTripUI() {
        initComponents();
        ArrayList<Point> points = getPoints();
        initMap();
        addPins(points);
    }
    
    private ArrayList<Point> getPoints() {
        ArrayList<Point> points = new ArrayList<>();
        Connection con = (new Database()).con();
        String query = "select * from trip where date_time >= NOW() and driver_id = 1 order by date_time asc";
        Statement st;
        ResultSet rs;
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            Point point_start;
            Point point_end;
            while (rs.next()) {
                point_start = new Point("start", new GeoPosition(rs.getDouble("start_latitude"), rs.getDouble("start_longitude")));
                point_end = new Point("end", new GeoPosition(rs.getDouble("end_latitude"), rs.getDouble("end_longitude")));
                points.add(point_start);
                points.add(point_end);
                break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return points;
    }

    private void initMap() {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        map1.setTileFactory(tileFactory);
        GeoPosition geo = new GeoPosition(38.2483182, 21.7532223);
        map1.setAddressLocation(geo);
        map1.setZoom(8);
        MouseInputListener mm = new PanMouseInputListener(map1);
        map1.addMouseListener(mm);
        map1.addMouseMotionListener(mm);
        map1.addMouseWheelListener(new ZoomMouseWheelListenerCenter(map1));
    }
    
    private void addPins(ArrayList<Point> points) {
//        Set<Waypoint> waypoints = new HashSet<>();
        Set<Point> waypoints = new HashSet<>();
        List<GeoPosition> track = new ArrayList<>();
        
        for (Point point : points) {
            GeoPosition position = point.getCoord();
//            Point waypoint = new DefaultWaypoint(position);
//            waypoints.add(waypoint);
            waypoints.add(point);
            track.add(position);
        }
        RoutePainter routePainter = new RoutePainter(track);

        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);
        waypointPainter.setRenderer(new DefaultWaypointRenderer() {
            @Override
            public void paintWaypoint(Graphics2D g, JXMapViewer map, Waypoint wp) {
                Point2D point = map.getTileFactory().geoToPixel(wp.getPosition(), map.getZoom());
                g.setColor(Color.YELLOW);
                g.fillOval((int) point.getX() - 5, (int) point.getY() - 5, 10, 10);
            }
        });
        
        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add(routePainter);
        painters.add(waypointPainter);   
        CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);

        map1.setOverlayPainter(waypointPainter);
//        map1.setRoutingData(painter);
        map1.setOverlayPainter(painter);
    
    //  Routing Data
        if (waypoints.size() == 2) {
            GeoPosition start = null;
            GeoPosition end = null;
            for (Point w : waypoints) {
                if (w.getPointType() == Point.PointType.START) {
                    start = w.getPosition();
                } else if (w.getPointType() == Point.PointType.END) {
                    end = w.getPosition();
                }
            }
            if (start != null && end != null) {
                routingData = RoutingService.getInstance().routing(start.getLatitude(), start.getLongitude(), end.getLatitude(), end.getLongitude());

            } else {
                routingData.clear();
            }
            map1.setRoutingData(routingData);
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

        map1 = new com.mycompany.casheri.Map();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout map1Layout = new javax.swing.GroupLayout(map1);
        map1.setLayout(map1Layout);
        map1Layout.setHorizontalGroup(
            map1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 322, Short.MAX_VALUE)
        );
        map1Layout.setVerticalGroup(
            map1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(map1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(map1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(StartTripUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartTripUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartTripUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartTripUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StartTripUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.casheri.Map map1;
    // End of variables declaration//GEN-END:variables
}
