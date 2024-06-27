package Guis;

import Model.Player;
import Model.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiPlayers extends JFrame {
    private JTable playersTable;
    private JButton editButton, saveButton, backButton;
    private Team team;

    public GuiPlayers(Team team) {
        this.team = team;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Players - " + team.getNombre());
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Número", "Nombre", "Posición"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        playersTable = new JTable(tableModel);
        loadPlayers(tableModel);

        add(new JScrollPane(playersTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        editButton = new JButton("Edit player");
        saveButton = new JButton("Save changes");
        backButton = new JButton("Back");

        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void loadPlayers(DefaultTableModel tableModel) {
        for (Player player : team.getJugadores()) {
            Object[] rowData = {player.getNumero(), player.getNombre(), player.getPosicion()};
            tableModel.addRow(rowData);
        }
    }
}
