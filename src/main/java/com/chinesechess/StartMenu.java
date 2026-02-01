package com.chinesechess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartMenu extends JPanel {

    public StartMenu() {
        // Set the size of the menu (and thus the window)
        setPreferredSize(new Dimension(800, 600));
        setLayout(new GridBagLayout());
        setBackground(new Color(240, 230, 210)); // A light, board-like background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 50, 0); // Padding around the title

        // Title Label
        JLabel titleLabel = new JLabel("Chinese Chess");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 60));
        add(titleLabel, gbc);

        // Button settings
        gbc.gridy++;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 40;
        gbc.ipady = 10;

        // Start Game Button
        JButton startButton = new JButton("Start Game");
        startButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        startButton.addActionListener(e -> {
            System.out.println("Start Game clicked!");
            // TODO: Logic to switch to the Game Board goes here
            Game game = new Game(new User("Player 1", "Red"), new AIUser("Black"));
            game.start();
        });
        add(startButton, gbc);

        // Exit Button
        gbc.gridy++;
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(new Font("SansSerif", Font.BOLD, 20));
        exitButton.addActionListener(e -> System.exit(0));
        add(exitButton, gbc);
    }
}