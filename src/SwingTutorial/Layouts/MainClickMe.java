package SwingTutorial.Layouts;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class MainClickMe {
    public static void main(String args[]) {
        ClickMeMainFrame frame = new ClickMeMainFrame("Click me!");
        frame.setVisible(true);
    }
}
