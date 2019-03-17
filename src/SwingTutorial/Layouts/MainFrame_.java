package SwingTutorial.Layouts;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainFrame_ extends JFrame {
    JPanel contentPane;
    
    JButton btn1,btn2;
    JRadioButton radio1,radio2;
    JTextArea area;
    JCheckBox check;
    JPanel centerPanel;
    JLabel label;

    MainFrame_() {
        initialize();
    }

    void initBordered() {
        setLocation(new Point(200,100));
        this.setSize(new Dimension(700,600));
        contentPane=(JPanel) this.getContentPane();
        contentPane.setLayout(new BorderLayout());

        btn1=new JButton("First Button");
        btn1.setSize(90,65);

        btn2=new JButton("Second Button");
        btn2.setMaximumSize(new Dimension(1000,60));
        //btn2.setSize(90,65);


        radio1=new JRadioButton("Radio number one");
        radio1.setSize(new Dimension(100,60));

        radio2=new JRadioButton("Second radio button");
        radio2.setMaximumSize(new Dimension(1000,60));

        area=new JTextArea();
        area.setSize(new Dimension(300,150));

        check=new JCheckBox("CheckBox");

        label=new JLabel("This is a label");

        centerPanel=new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.LINE_AXIS));

        contentPane.add(btn1, BorderLayout.EAST);
        contentPane.add(check,BorderLayout.NORTH);
        contentPane.add(radio1, BorderLayout.SOUTH);
        contentPane.add(radio2, BorderLayout.WEST);
        contentPane.add(centerPanel);

        centerPanel.add(label);
        centerPanel.add(Box.createGlue());
        centerPanel.add(btn2);
        centerPanel.add(Box.createRigidArea(new Dimension(50,30)));
        centerPanel.add(area);

    }

    void initialize() {
        setLocation(new Point(200,100));
        this.setSize(new Dimension(700,600));
        contentPane=(JPanel) this.getContentPane();
        contentPane.setLayout(new GridLayout(3,3));

        btn1=new JButton("First Button");
        btn1.setSize(90,65);

        btn2=new JButton("Second Button");
        btn2.setMaximumSize(new Dimension(1000,60));

        radio1=new JRadioButton("Radio number one");
        radio1.setSize(new Dimension(100,60));

        radio2=new JRadioButton("Second radio button");
        radio2.setMaximumSize(new Dimension(1000,60));

        area=new JTextArea();
        area.setSize(new Dimension(300,150));

        check=new JCheckBox("CheckBox");

        label=new JLabel("This is a label");

        centerPanel=new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.LINE_AXIS));

        contentPane.add(btn1, BorderLayout.EAST);
        contentPane.add(check,BorderLayout.NORTH);
        contentPane.add(radio1, BorderLayout.SOUTH);
        contentPane.add(radio2, BorderLayout.WEST);
        contentPane.add(centerPanel);

        contentPane.add(label);
        contentPane.add(Box.createGlue());
        contentPane.add(btn2);
        contentPane.add(Box.createRigidArea(new Dimension(50,30)));
        contentPane.add(area);

    }
}
