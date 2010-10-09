package euchre.gui;
import java.awt.*;
  
/**
 * Welcomes the user to the euchre program, and asks them whether they would like to play
 * a network or local game.
 *
 * @author Neil MacBay (nmmacbay)
 *
 */ 
public class Welcome extends javax.swing.JFrame{

	/**
	 * Construct a welcome screen.
	 */
    public Welcome(){
        initComponents();
        centerScreen();
    }

    /**
     * Moves the jFrame to the center of the screen
     */
    private void centerScreen(){
        int xCenter = Toolkit.getDefaultToolkit().getScreenSize().width/2;
        int yCenter = Toolkit.getDefaultToolkit().getScreenSize().height/2;
        int xSize = this.getSize().width;
        int ySize = this.getSize().height;
        Point p = new Point();
        p.setLocation(xCenter - xSize/2, yCenter - ySize/2);
        this.setLocation(p);
    }


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void initComponents(){

        jLabelWelcomeBanner = new javax.swing.JLabel();
        jButtonNetworkGame = new javax.swing.JButton();
        jButtonLocalGame = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Euchre");
        setName("frameWelcome"); // NOI18N

        jLabelWelcomeBanner.setFont(new java.awt.Font("DejaVu Sans", 0, 24)); // NOI18N
        jLabelWelcomeBanner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelWelcomeBanner.setText("Welcome to Euchre, which type of game do you wish to play?");

        jButtonNetworkGame.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jButtonNetworkGame.setText("Network Game");
        jButtonNetworkGame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                choseNetwork(evt);
            }
        });

        jButtonLocalGame.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jButtonLocalGame.setText("Local Game");
        jButtonLocalGame.addMouseListener(new java.awt.event.MouseAdapter(){
            public void mouseClicked(java.awt.event.MouseEvent evt){
                choseLoacal(evt);
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
                        .addComponent(jLabelWelcomeBanner, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButtonLocalGame, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(jButtonNetworkGame, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelWelcomeBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNetworkGame, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLocalGame, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(74, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * User chose a Network game, so launch the networkGameBrowser so that they can find or make one.
     */
    private void choseNetwork(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choseNetwork
        new NetworkGameBrowser().setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_choseNetwork

     /**
     * User chose a Local Game, so launch the setupLocal form so that they can setup their game.
     */
    private void choseLoacal(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_choseLoacal
        new SetupLocal().setVisible(true);
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_choseLoacal

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]){
        java.awt.EventQueue.invokeLater(new Runnable(){
            public void run(){
                new Welcome().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonLocalGame;
    private javax.swing.JButton jButtonNetworkGame;
    private javax.swing.JLabel jLabelWelcomeBanner;
    // End of variables declaration//GEN-END:variables
}
