/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author elich
 */
public class WelcomeScreen extends JFrame {

  JPanel mainPanel = new JPanel();
  JPanel welcomePanel = new JPanel();
  JPanel controlPanel = new JPanel();
  Container contentPane = this.getContentPane();
  JPanel pokerCalPanel = new PokerPanel(new JPanel());


  public WelcomeScreen() {
    setTitle("Poker Calculator");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setSize(1300, 600);
    setVisible(true);
    SpringLayout layout = new SpringLayout();
    setLayout(layout);

    JLabel createdBy = new JLabel("Created by:");
    createdBy.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
    JLabel authorsNames = new JLabel("Vanessa Redman   " + "Robert Edwards   " + "Thomas Corea   " +
        "Yuko Takegoshi   " + "Keith DeMoura   " + "Eli Cheek");
    authorsNames.setFont(new Font("Sans-Serif", Font.PLAIN, 18));
    JLabel welcomeLabel = new JLabel("Welcome to the Poker Odds Calculator!");
    welcomeLabel.setFont(new Font("Sans-Serif", Font.PLAIN, 32));
    JButton calculatorButton = new JButton("Go to Poker Calculator");
    JButton databaseButton = new JButton("Go to Poker Database");

    controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
    controlPanel.setPreferredSize(new Dimension(175, contentPane.getHeight()));
    controlPanel.add(calculatorButton);
    controlPanel.add(databaseButton);
    mainPanel.add(welcomePanel);
    mainPanel.setPreferredSize(
        new Dimension(contentPane.getWidth() - (int) controlPanel.getPreferredSize().getWidth(), contentPane.getHeight()));
    welcomePanel.add(welcomeLabel);
    welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.Y_AXIS));
    welcomePanel.add(Box.createVerticalStrut(100));
    //mainPanel.add(Box.createHorizontalStrut(100));
    welcomePanel.add(Box.createVerticalStrut(40));
    welcomePanel.add(Box.createVerticalStrut(100));
    welcomePanel.add(createdBy);
    welcomePanel.add(authorsNames);
    add(mainPanel);
    add(controlPanel);

    //Using Spring Layout for resizing purposes not committed to this layout
    //seemed like the best for this basic purpose
    layout.putConstraint(SpringLayout.WEST, controlPanel,
        5,
        SpringLayout.WEST, contentPane);
    layout.putConstraint(SpringLayout.NORTH, controlPanel,
        5,
        SpringLayout.NORTH, contentPane);
    layout.putConstraint(SpringLayout.WEST, mainPanel,
        5,
        SpringLayout.EAST, controlPanel);
    layout.putConstraint(SpringLayout.EAST, mainPanel,
        5,
        SpringLayout.EAST, contentPane);
    layout.putConstraint(SpringLayout.NORTH, mainPanel,
        5,
        SpringLayout.NORTH, controlPanel);
    layout.putConstraint(SpringLayout.SOUTH, contentPane,
        5,
        SpringLayout.SOUTH, mainPanel);
    //pack();
    validate();
    //Buttons will manipulate the viewing screen
    calculatorButton.addActionListener((ActionEvent e) -> {
      SpringLayout mainLayout = new SpringLayout();
      mainPanel.removeAll();
      mainPanel.add(pokerCalPanel);
      mainPanel.setLayout(mainLayout);
      mainLayout.putConstraint(SpringLayout.WEST, pokerCalPanel, 5, SpringLayout.WEST, mainPanel);
      mainLayout.putConstraint(SpringLayout.NORTH, pokerCalPanel, 5, SpringLayout.NORTH, mainPanel);
      repaint();
      validate();
    });
    databaseButton.addActionListener((ActionEvent e) -> {
      SpringLayout mainLayout = new SpringLayout();
      mainPanel.removeAll();
      JPanel historyPanel = new PokerHistory(mainPanel);
      mainPanel.add(historyPanel);
      mainPanel.setLayout(mainLayout);
      mainLayout.putConstraint(SpringLayout.WEST, historyPanel, 5, SpringLayout.WEST, mainPanel);
      mainLayout.putConstraint(SpringLayout.NORTH, historyPanel, 5, SpringLayout.NORTH, mainPanel);

      repaint();
      validate();
    });
    validate();
  }
}

