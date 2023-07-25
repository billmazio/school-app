package gr.aueb.cf.schoolapp.viewcontroller;


import gr.aueb.cf.schoolapp.dao.ICityDAO;
import gr.aueb.cf.schoolapp.dao.CityDAOImpl;
import gr.aueb.cf.schoolapp.dto.CityInsertDTO;
import gr.aueb.cf.schoolapp.service.CityServiceImpl;
import gr.aueb.cf.schoolapp.service.ICityService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminInsertCitiesForm extends JFrame {
    private JPanel contentPane;
    private JTextField nameTxt;
    private JTextField countryTxt;
    private ICityService cityService;

    public AdminInsertCitiesForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setLocationRelativeTo(null);
        setTitle("Admin Insert Cities");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("City Name");
        lblNewLabel.setBounds(74, 56, 80, 14);
        contentPane.add(lblNewLabel);

        nameTxt = new JTextField();
        nameTxt.setBounds(164, 53, 207, 20);
        contentPane.add(nameTxt);
        nameTxt.setColumns(10);



        JButton insertBtn = new JButton("Insert");
        insertBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertCity();
            }
        });
        insertBtn.setForeground(Color.BLUE);
        insertBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        insertBtn.setBounds(164, 145, 89, 23);
        contentPane.add(insertBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        closeBtn.setForeground(Color.BLUE);
        closeBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
        closeBtn.setBounds(282, 145, 89, 23);
        contentPane.add(closeBtn);

        ICityDAO dao = new CityDAOImpl();
        cityService = new CityServiceImpl(dao);
    }

    private void insertCity() {
        String name = nameTxt.getText().trim();


        if (name.isEmpty() ) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        CityInsertDTO dto = new CityInsertDTO();
        dto.setName(name);


        try {
            cityService.insertCity(dto);
            JOptionPane.showMessageDialog(null, "City inserted successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error occurred during city insertion.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}

