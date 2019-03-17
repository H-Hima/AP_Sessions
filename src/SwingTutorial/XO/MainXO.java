package SwingTutorial.XO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MainXO {
    static Random random=new Random();
    public static void main(String args[]) {
        XOFrame myFrame=new XOFrame();
        myFrame.setVisible(true);
    }
}