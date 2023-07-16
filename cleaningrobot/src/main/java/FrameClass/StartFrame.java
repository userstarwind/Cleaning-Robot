package FrameClass;

import StartFrameTool.CleaningRobot;
import StartFrameTool.PythonRunner;
import TrashClass.TrashMap;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StartFrame {

    public void start(){
        JFrame startFrame=new JFrame("cleaning robot");
        startFrame.setBounds(100,100,800,500);
        startFrame.setLayout(null);

        startFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                startFrame.dispose();
            }
        });
        JLabel Label1=new JLabel("length:");
        JLabel Label2=new JLabel("width:");
        JTextField field1=new JTextField();
        JTextField field2=new JTextField();
        JButton button=new JButton("Start");
        Label1.setLocation(100,100);
        Label1.setSize(50,50);
        startFrame.add(Label1);
        Label2.setLocation(100,200);
        Label2.setSize(50,50);
        startFrame.add(Label2);
        field1.setSize(200,50);
        field1.setLocation(200,100);
        startFrame.add(field1);
        field2.setSize(200,50);
        field2.setLocation(200,200);
        startFrame.add(field2);
        button.setSize(300,50);
        button.setLocation(100,300);
        startFrame.add(button);
        JTextArea outputArea=new JTextArea();
        outputArea.setEditable(false);
        outputArea.setLineWrap(true);
        JScrollPane jsp=new JScrollPane(outputArea);
        jsp.setBounds(450,100,300,300);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        startFrame.add(jsp);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!field1.getText().isEmpty() && !field2.getText().isEmpty()) {
                    int trash_amount=10;
                    TrashMap map=new TrashMap(Double.parseDouble(field1.getText()), Double.parseDouble(field2.getText()),trash_amount);
                    map.init();
                    outputArea.append(map.toString());
                    CleaningRobot cleaner=new CleaningRobot(map,outputArea);
                    cleaner.clean();
                    PythonRunner runner=new PythonRunner();
                    runner.run();
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter the size of the pool", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        startFrame.setVisible(true);
    }
}
