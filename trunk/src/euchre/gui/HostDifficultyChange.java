package euchre.gui;

import java.awt.Point;

/**
 * A Form for changing the difficulties of the AI in GameLobby GUI.
 *
 * @author Neil MacBay (nmmacbay)
 */
public class HostDifficultyChange extends javax.swing.JFrame {

    GameLobby myLobby;
    boolean player3 = false; //whether or not the difficulties for player 3 are adjustable (because it is a AI).
    boolean player4 = false; //whether or not the difficulties for player 4 are adjustable (because it is a AI).

    /** Creates new form for the host to change the difficulties of the AI in the network game.
     *
     * @param parent The Game Lobby object that launched this form.
     * @param numberAI The number of AI your able to adjust, must be one or two.
     */
    public HostDifficultyChange(GameLobby parent, int numberAI) {
        initComponents();
        myLobby = parent;
        centerWindowUnderParent();
        //Changes to the GUI according to what players are AI.
        if (numberAI == 1){
        	//What the players able to change
        	player4 = true;
        	//Changes to what is visible for the user to change.
        	player3Difficulty.setVisible(false);
        	player3Easy.setVisible(false);
        	player3Medium.setVisible(false);
        	player3Hard.setVisible(false);
        }else if (numberAI == 2){
        	//What the players able to change3
        	player3 = true;
        	player4 = true;
        }
        if (player4){//then read old player 4 values and represent them
        	if (myLobby.getPlayer4Difficulty() == 'e'){
        		player4Easy.setSelected(true);
        	}else if (myLobby.getPlayer4Difficulty() == 'm'){
        		player4Medium.setSelected(true);
        	}else{ //difficulty == 'h'
        		player4Hard.setSelected(true);
        	}
        }
        if (player3){//then read old player 3 values and represent them
        	if (myLobby.getPlayer3Difficulty() == 'e'){
        		player3Easy.setSelected(true);
        	}else if (myLobby.getPlayer3Difficulty() == 'm'){
        		player3Medium.setSelected(true);
        	}else{ //difficulty == 'h'
        		player3Hard.setSelected(true);
        	}
        }
    }

    /**
     * Moves the jFrame to the center of the form that launched it
     */
    private void centerWindowUnderParent(){
        int xCenter = myLobby.getSize().width/2 + myLobby.getX();
        int yBottom = myLobby.getSize().height + myLobby.getY();
        int xSize = this.getSize().width;
        Point p = new Point();
        p.setLocation(xCenter - xSize/2, yBottom);
        this.setLocation(p);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        player3Group = new javax.swing.ButtonGroup();
        player4Group = new javax.swing.ButtonGroup();
        player3Difficulty = new javax.swing.JLabel();
        player4Difficulty = new javax.swing.JLabel();
        player3Easy = new javax.swing.JRadioButton();
        player3Medium = new javax.swing.JRadioButton();
        player3Hard = new javax.swing.JRadioButton();
        player4Hard = new javax.swing.JRadioButton();
        player4Easy = new javax.swing.JRadioButton();
        player4Medium = new javax.swing.JRadioButton();
        applyDifficulties = new javax.swing.JButton();

        setTitle("Change Computer Difficulties");
        setResizable(false);

        player3Difficulty.setText("Difficulty of Computer player 3:");

        player4Difficulty.setText("Difficulty of Computer player 4:");

        player3Group.add(player3Easy);
        player3Easy.setText("Easy");

        player3Group.add(player3Medium);
        player3Medium.setText("Medium");

        player3Group.add(player3Hard);
        player3Hard.setText("Hard");

        player4Group.add(player4Hard);
        player4Hard.setText("Hard");

        player4Group.add(player4Easy);
        player4Easy.setText("Easy");

        player4Group.add(player4Medium);
        player4Medium.setText("Medium");

        applyDifficulties.setText("Apply Difficulties");
        applyDifficulties.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                applyDifficultiesClicked(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(player3Difficulty)
                            .addComponent(player4Difficulty))
                        .addGap(67, 67, 67)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(player3Easy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(player3Medium)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(player3Hard))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(player4Easy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(player4Medium)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(player4Hard))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(applyDifficulties, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(player3Difficulty)
                        .addGap(25, 25, 25)
                        .addComponent(player4Difficulty))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(player3Easy)
                            .addComponent(player3Medium)
                            .addComponent(player3Hard))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(player4Easy)
                            .addComponent(player4Medium)
                            .addComponent(player4Hard))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(applyDifficulties)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void applyDifficultiesClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_applyDifficultiesClicked
    	if (player3){
    		if (player3Easy.isSelected()){
    			myLobby.setPlayer3Difficulty('e');
    		}else if(player3Medium.isSelected()){
    			myLobby.setPlayer3Difficulty('m');
    		}else{ //Difficulty == 'h'
    			myLobby.setPlayer3Difficulty('h');
    		}
    	}
    	if (player4){
    		if (player4Easy.isSelected()){
    			myLobby.setPlayer4Difficulty('e');
    		}else if(player4Medium.isSelected()){
    			myLobby.setPlayer4Difficulty('m');
    		}else{ //Difficulty == 'h'
    			myLobby.setPlayer4Difficulty('h');
    		}
    	}
    	this.setVisible(false);
    	myLobby.disableStart();
    	this.dispose();
    }//GEN-LAST:event_applyDifficultiesClicked

    /**
    * @param args the command line arguments
    */
    //public static void main(String args[]) {
    //    java.awt.EventQueue.invokeLater(new Runnable() {
    //        public void run() {
    //            new HostDifficultyChange().setVisible(true);
    //        }
    //    });
    //}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton applyDifficulties;
    private javax.swing.JLabel player3Difficulty;
    private javax.swing.JRadioButton player3Easy;
    private javax.swing.ButtonGroup player3Group;
    private javax.swing.JRadioButton player3Hard;
    private javax.swing.JRadioButton player3Medium;
    private javax.swing.JLabel player4Difficulty;
    private javax.swing.JRadioButton player4Easy;
    private javax.swing.ButtonGroup player4Group;
    private javax.swing.JRadioButton player4Hard;
    private javax.swing.JRadioButton player4Medium;
    // End of variables declaration//GEN-END:variables

}
