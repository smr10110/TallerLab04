package Guis;

import Model.Player;
import Model.Position;
import Model.Team;
import Data.DataPlayer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GuiPlayers extends JFrame {
    private JTable playersTable;
    private JButton editButton, saveButton, backButton;
    private Team team;
    private DefaultTableModel tableModel;

    public GuiPlayers(Team team) {
        this.team = team;
        initializeUI();
        loadPlayers();
    }

    private void initializeUI() {
        setTitle("Players - " + team.getNombre());
        setSize(600, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Número", "Nombre", "Posición"};
        tableModel = new DefaultTableModel(columnNames, 0);
        playersTable = new JTable(tableModel);

        add(new JScrollPane(playersTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        editButton = new JButton("Edit player");
        saveButton = new JButton("Save changes");
        backButton = new JButton("Back");

        buttonPanel.add(editButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = playersTable.getSelectedRow();
                if (selectedRow != -1) {
                    editPlayer(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(GuiPlayers.this,
                            "Selecciona un jugador para editar.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savePlayerChanges();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void loadPlayers() {
        tableModel.setRowCount(0);

        for (Player player : team.getJugadores()) {
            Object[] rowData = {player.getNumero(), player.getNombre(), player.getPosicion()};
            tableModel.addRow(rowData);
        }
    }

    private void editPlayer(int rowIndex) {
        int numero = (int) tableModel.getValueAt(rowIndex, 0);
        String nombre = (String) tableModel.getValueAt(rowIndex, 1);
        Position posicion = (Position) tableModel.getValueAt(rowIndex, 2);

        String newNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", nombre);
        if (newNombre != null) {
            Position newPosicion = (Position) JOptionPane.showInputDialog(this, "Nueva posición:", "Editar posición",
                    JOptionPane.QUESTION_MESSAGE, null, Position.values(), posicion);
            if (newPosicion != null) {
                tableModel.setValueAt(newNombre, rowIndex, 1);
                tableModel.setValueAt(newPosicion, rowIndex, 2);
                DataPlayer.editarJugador(team, rowIndex, newNombre, newPosicion);
            }
        }
    }

    private void savePlayerChanges() {
        try {
            DataPlayer.guardarCambios(team);
            JOptionPane.showMessageDialog(this, "Cambios guardados exitosamente.", "Guardar", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar cambios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(); // Aquí podrías manejar el error de forma más específica según tus necesidades
        }
    }
}
