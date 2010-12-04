package euchre.gui;
import java.awt.*;

import javax.swing.JOptionPane;

import euchre.player.GameManager;

/**
 * Screen to setup a local game of euchre played between the user and some AI.
 *
 * @author Neil MacBay(nmmacbay)
 *
 */
public class SetupLocal extends javax.swing.JFrame {
	private static final long serialVersionUID = 1L;
	private boolean setupComplete = false;
	private GameManager GM;
	
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButtonGameStart;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JRadioButton jRadioButton1E;
    private javax.swing.JRadioButton jRadioButton1H;
    private javax.swing.JRadioButton jRadioButton1M;
    private javax.swing.JRadioButton jRadioButton2E;
    private javax.swing.JRadioButton jRadioButton2H;
    private javax.swing.JRadioButton jRadioButton2M;
    private javax.swing.JRadioButton jRadioButton3E;
    private javax.swing.JRadioButton jRadioButton3H;
    private javax.swing.JRadioButton jRadioButton3M;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextFieldPlayerName;
    // End of variables declaration//GEN-END:variables
	
    public SetupLocal(GameManager inManager) {
        initComponents();
        centerScreen();
        GM = inManager;
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextFieldPlayerName = new javax.swing.JTextField();
        jRadioButton1E = new javax.swing.JRadioButton();
        jRadioButton1M = new javax.swing.JRadioButton();
        jRadioButton1H = new javax.swing.JRadioButton();
        jRadioButton2H = new javax.swing.JRadioButton();
        jRadioButton2M = new javax.swing.JRadioButton();
        jRadioButton2E = new javax.swing.JRadioButton();
        jRadioButton3H = new javax.swing.JRadioButton();
        jRadioButton3M = new javax.swing.JRadioButton();
        jRadioButton3E = new javax.swing.JRadioButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButtonGameStart = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Setup Local Game");
        setResizable(false);

        jLabel1.setText("Your Name: ");

        jLabel2.setText("Computer 1 Dificulty: ");

        jLabel3.setText("Computer 2 Dificulty: ");

        jLabel4.setText("Computer 3 Dificulty:");

        jTextFieldPlayerName.setText("Player 1");

        buttonGroup1.add(jRadioButton1E);
        jRadioButton1E.setText("Easy");

        buttonGroup1.add(jRadioButton1M);
        jRadioButton1M.setSelected(true);
        jRadioButton1M.setText("Medium");

        buttonGroup1.add(jRadioButton1H);
        jRadioButton1H.setText("Hard");

        buttonGroup2.add(jRadioButton2H);
        jRadioButton2H.setText("Hard");

        buttonGroup2.add(jRadioButton2M);
        jRadioButton2M.setSelected(true);
        jRadioButton2M.setText("Medium");

        buttonGroup2.add(jRadioButton2E);
        jRadioButton2E.setText("Easy");

        buttonGroup3.add(jRadioButton3H);
        jRadioButton3H.setText("Hard");

        buttonGroup3.add(jRadioButton3M);
        jRadioButton3M.setSelected(true);
        jRadioButton3M.setText("Medium");

        buttonGroup3.add(jRadioButton3E);
        jRadioButton3E.setText("Easy");

        jLabel5.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Team 1");

        jLabel6.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Team 2");

        jButtonGameStart.setText("Start Game");
        jButtonGameStart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                startGame(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(265, Short.MAX_VALUE)
                .addComponent(jButtonGameStart, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214))
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton1E, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(jRadioButton1M))
                            .addComponent(jTextFieldPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                        .addComponent(jRadioButton1H)
                        .addGap(90, 90, 90))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton2E, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jRadioButton3E, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton3M)
                            .addComponent(jRadioButton2M))
                        .addGap(92, 92, 92)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jRadioButton3H)
                            .addComponent(jRadioButton2H))
                        .addGap(89, 89, 89))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(75, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                .addGap(33, 33, 33))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPlayerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jRadioButton1E)
                    .addComponent(jRadioButton1H)
                    .addComponent(jRadioButton1M))
                .addGap(7, 7, 7)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jRadioButton2E)
                    .addComponent(jRadioButton2H)
                    .addComponent(jRadioButton2M))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jRadioButton3E)
                    .addComponent(jRadioButton3H)
                    .addComponent(jRadioButton3M))
                .addGap(18, 18, 18)
                .addComponent(jButtonGameStart, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public char getComputer1Difficulty(){
    	if (jRadioButton1E.isSelected()){ //easy
    		return 'e';
    	}else if(jRadioButton1M.isSelected()){//medium
    		return 'm';
    	}else{//hard
    		return 'h';
    	}
    }
    
    public char getComputer2Difficulty(){
    	if (jRadioButton2E.isSelected()){ //easy
    		return 'e';
    	}else if(jRadioButton2M.isSelected()){//medium
    		return 'm';
    	}else{//hard
    		return 'h';
    	}
    }
    
    public char getComputer3Difficulty(){
    	if (jRadioButton3E.isSelected()){ //easy
    		return 'e';
    	}else if(jRadioButton3M.isSelected()){//medium
    		return 'm';
    	}else{//hard
    		return 'h';
    	}
    }
    
    /**
	 * Launch a local game with the options that were selected by the user.
	 */
    private void startGame(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_startGame
    	if (jTextFieldPlayerName.getText().isEmpty() || jTextFieldPlayerName.getText().trim().isEmpty()){ //Invalid input (whitespace only)
			JOptionPane.showMessageDialog(null, "Please enter a player name", "Error", JOptionPane.ERROR_MESSAGE);
		}else if(contains(jTextFieldPlayerName.getText().trim(), ',')){
			JOptionPane.showMessageDialog(null, "Please enter a player name without commas.", "Error", JOptionPane.ERROR_MESSAGE);
		}else { //Valid input
			//Team Setup.
			/**/GM.setTeam(1, 1);
			GM.getServerNetworkManager().toClients("SetTeam,1,1");
			GM.setTeam(2, 1);
			GM.getServerNetworkManager().toClients("SetTeam,2,1");
			GM.setTeam(3, 2);
			GM.getServerNetworkManager().toClients("SetTeam,3,2");
			GM.setTeam(4, 2);
			GM.getServerNetworkManager().toClients("SetTeam,4,2");
			setupComplete = true;
			this.setVisible(false);
		}
    }//GEN-LAST:event_startGame
    
    /**
     * Checks the given string for the given character.
     * 
     * @param string The string to look for the character in.
     * @param look The character to look for in the string.
     * @return True if the character was found in the string.
     */
    private boolean contains(String string, char look){
		boolean wasFound = false;
		for (int i=0; i < string.length(); i++){
				if (string.charAt(i) == look){
					wasFound = true;
				}
		}
    	return wasFound;	
    }
    
    public boolean getSetupComplete(){
    	return setupComplete;
    }
    
    public String getPlayerName(){
    	return jTextFieldPlayerName.getText().trim();
    }
}
