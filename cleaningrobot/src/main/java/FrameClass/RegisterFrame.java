package FrameClass;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class RegisterFrame {
    public void register(){
        JFrame registerFrame=new JFrame("cleaning robot");
        registerFrame.setBounds(100,100,500,500);
        registerFrame.setLayout(null);

        registerFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                registerFrame.dispose();
            }
        });
        JLabel Label1=new JLabel("User:");
        JLabel Label2=new JLabel("Password:");
        JTextField field=new JTextField();
        JPasswordField passwordField=new JPasswordField();
        JButton button1=new JButton("Register");
        JButton button2=new JButton("Back");
        Label1.setLocation(100,100);
        Label1.setSize(50,50);
        registerFrame.add(Label1);
        Label2.setLocation(100,200);
        Label2.setSize(75,50);
        registerFrame.add(Label2);
        field.setSize(200,50);
        field.setLocation(200,100);
        registerFrame.add(field);
        passwordField.setSize(200,50);
        passwordField.setLocation(200,200);
        registerFrame.add(passwordField);
        button1.setSize(100,50);
        button1.setLocation(100,300);
        registerFrame.add(button1);
        button2.setSize(100,50);
        button2.setLocation(300,300);
        registerFrame.add(button2);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!field.getText().isEmpty() && !passwordField.getText().isEmpty()) {
                    try {
                        String[] strings=new String[]{field.getText(), passwordField.getText()};
                        String[] existStrings;
                        File file=new File("Users.csv");
                        if(!file.exists()) {
                            file.createNewFile();
                        }
                        CSVReader reader=new CSVReader(new FileReader("Users.csv"));
                        boolean flag=true;
                        while ((existStrings = reader.readNext()) != null) {
                            String firstColumn = existStrings[0];
                            if(firstColumn.equals(field.getText())){
                                JOptionPane.showMessageDialog(null, "Username already exists", "ERROR", JOptionPane.ERROR_MESSAGE);
                                flag=false;
                                break;
                            }
                        }
                        reader.close();
                        if(flag==true){
                        CSVWriter writer = new CSVWriter(new FileWriter("Users.csv",true));
                        writer.writeNext(strings,true);
                        writer.close();
                            JOptionPane.showMessageDialog(null, "Manage to create an account", "INFORM", JOptionPane.INFORMATION_MESSAGE);
                            registerFrame.dispose();
                            LoginFrame loginFrame=new LoginFrame();
                            loginFrame.login();
                        }
                    } catch (IOException x) {
                        x.printStackTrace();
                    } catch (CsvValidationException ex) {
                        throw new RuntimeException(ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "The user name or password cannot be empty", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }

        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerFrame.dispose();
                LoginFrame loginFrame=new LoginFrame();
                loginFrame.login();
            }
        });
        registerFrame.setVisible(true);
    }
}
