package SwingTutorial.XO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class XOFrame extends JFrame {
    JPanel contentPane;
    JButton[][] board=new JButton[3][3];

    int turn=0;
    String turnString[]={"O","X"};
    Color turnColor[]={Color.RED,Color.GREEN};

    public XOFrame() {
        initialize();
    }

    void initialize() {
    	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
        setLocation(new Point(200,100));
        this.setSize(new Dimension(600,600));
        contentPane=(JPanel) this.getContentPane();
        contentPane.setLayout(new GridLayout(3,3));

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                JButton button=new JButton() {
                    @Override
                    public void paintComponent(Graphics g) {
                        super.paintComponent(g);

                        g.setColor(Color.YELLOW);
                        g.fillOval(10,10,40,40);
                    }
                };
                board[i][j]=button;
                board[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(button.getText().compareTo("")==0) {
                            button.setText(turnString[turn]);
                            button.setBackground(turnColor[turn]);
                            checkStatus();
                            turn=1-turn;
                        }
                        
                        JFileChooser openFileChooser = new JFileChooser();
                        openFileChooser.showOpenDialog(button);
                        File file1 = openFileChooser.getSelectedFile();
                        
                        JFileChooser saveFileChooser = new JFileChooser();
                        saveFileChooser.showSaveDialog(button);
                        File file2 = saveFileChooser.getSelectedFile();
                        
                        JColorChooser colorChooser = new JColorChooser();
                        colorChooser.showDialog(button, "", Color.RED);
                        Color color = colorChooser.getColor();
                    }
                });

                contentPane.add(board[i][j]);
            }
        }
    }
    void checkStatus() {
        for(int i=0;i<3;i++) {
            if(board[i][0].getText().length()>0 && board[i][0].getText()==board[i][1].getText()&&board[i][2].getText()==board[i][1].getText()) {
                JOptionPane.showMessageDialog(this,turnString[turn]+" Wins");
            }
            if(board[0][i].getText().length()>0 && board[0][i].getText()==board[1][i].getText()&&board[2][i].getText()==board[1][i].getText()) {
                JOptionPane.showMessageDialog(this,turnString[turn]+" Wins");
            }
        }
        
        if(board[0][0].getText().length()>0 && board[0][0].getText()==board[1][1].getText()&&board[2][2].getText()==board[1][1].getText()) {
            JOptionPane.showMessageDialog(this,turnString[turn]+" Wins");
        }
        
        if(board[0][2].getText().length()>0 && board[0][2].getText()==board[1][1].getText()&&board[2][0].getText()==board[1][1].getText()) {
            JOptionPane.showMessageDialog(this,turnString[turn]+" Wins");
        }
    }
}
