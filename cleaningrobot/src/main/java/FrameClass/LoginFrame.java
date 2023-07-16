package FrameClass;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoginFrame {
    public void login(){
        JFrame loginFrame=new JFrame("cleaning robot");
        loginFrame.setBounds(100,100,500,500);
        loginFrame.setLayout(null);

        loginFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loginFrame.dispose();
            }
        });
        JLabel Label1=new JLabel("User:");
        JLabel Label2=new JLabel("Password:");
        JTextField field=new JTextField();
        JPasswordField passwordField=new JPasswordField();
        JButton button1=new JButton("Login");
        JButton button2=new JButton("Register");
        Label1.setLocation(100,100);
        Label1.setSize(50,50);
        loginFrame.add(Label1);
        Label2.setLocation(100,200);
        Label2.setSize(75,50);
        loginFrame.add(Label2);
        field.setSize(200,50);
        field.setLocation(200,100);
        loginFrame.add(field);
        passwordField.setSize(200,50);
        passwordField.setLocation(200,200);
        loginFrame.add(passwordField);
        button1.setSize(100,50);
        button1.setLocation(100,300);
        loginFrame.add(button1);
        button2.setSize(100,50);
        button2.setLocation(300,300);
        loginFrame.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                RegisterFrame registerFrame=new RegisterFrame();
                registerFrame.register();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!field.getText().isEmpty() && !passwordField.getText().isEmpty()){
                    boolean flag=false;
                    String[] strings=new String[]{field.getText(),passwordField.getText()};
                    String[] existStrings;
                    try {
                        File file=new File("Users.csv");
                        if(!file.exists()) {
                            file.createNewFile();
                        }
                        CSVReader reader=new CSVReader(new FileReader("Users.csv"));
                        while((existStrings=reader.readNext())!=null){
                            if(existStrings[0].equals(strings[0])){
                                flag=true;
                                break;
                            }
                        }
                        reader.close();
                        if(flag==true){
                            if(strings[1].equals(existStrings[1])){
                                StartFrame startFrame=new StartFrame();
                                startFrame.start();
                                loginFrame.dispose();
                                JOptionPane.showMessageDialog(null, "Manage to login", "INFORM", JOptionPane.INFORMATION_MESSAGE);
                            }else{
                                JOptionPane.showMessageDialog(null, "Wrong password", "ERROR", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "The user name does not exist", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    } catch (CsvValidationException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                }else{
                    JOptionPane.showMessageDialog(null, "Please enter the user name and password", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        loginFrame.setVisible(true);
    }

}
