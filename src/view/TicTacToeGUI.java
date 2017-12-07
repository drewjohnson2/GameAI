package view;

import model.GridLocation;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TicTacToeGUI extends JFrame {
    
    private final JTextField display;
    public JButton pressMove;
    public GridLocation[] myLocation;
    Font displayFont = new Font("Courier New", Font.BOLD, 18);
    Font gridFont = new Font("Courier New", Font.BOLD, 56);
    
    public TicTacToeGUI() {
        
        Container contentPane = getContentPane();
        pressMove = new JButton();
        pressMove.setText("press to move");
        JPanel ticTacPanel = new JPanel();
        ticTacPanel.setLayout(new GridLayout(3, 3));
        display = new JTextField("Let's Play Super Tic-Tac-Toe!");
        display.setFont(displayFont);
        display.setEditable(false);
        contentPane.add("North", display);
        contentPane.add(ticTacPanel, "Center");
        contentPane.add(pressMove, "South");
        myLocation = new GridLocation[9];
        for (int i = 0; i < myLocation.length; i++) {
            myLocation[i] = new GridLocation("");
            myLocation[i].setFont(gridFont);
            myLocation[i].setEnabled(true);
            ticTacPanel.add(myLocation[i]);
        }
        
    }
    
    public void makeMove(int index, String token) {
        myLocation[index].setText(token);
        myLocation[index].setEnabled(false);
    }
    
}
