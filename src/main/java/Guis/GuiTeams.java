package Guis;

import Model.Team;
import Data.DataPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GuiTeams extends JFrame {
    private JComboBox<String> teamComboBox;
    private JTextField rankingField;
    private JLabel flagLabel;
    private JButton playersButton, exitButton;
    private List<Team> equipos;

    public GuiTeams() {
        initializeUI();
        cargarEquipos();
    }

    private void initializeUI() {
        setTitle("Teams");
        setSize(400, 300);
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel teamLabel = new JLabel("Choose team:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(teamLabel, gbc);

        teamComboBox = new JComboBox<>();
        gbc.gridx = 1;
        add(teamComboBox, gbc);
        teamComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDetallesEquipo();
            }
        });

        JLabel rankingLabel = new JLabel("Ranking FIFA:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(rankingLabel, gbc);

        rankingField = new JTextField(5);
        rankingField.setEditable(false);
        gbc.gridx = 1;
        add(rankingField, gbc);

        flagLabel = new JLabel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        add(flagLabel, gbc);

        playersButton = new JButton("Players");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(playersButton, gbc);
        playersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarJugadores();
            }
        });

        exitButton = new JButton("Exit");
        gbc.gridx = 1;
        add(exitButton, gbc);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void cargarEquipos() {
        try {
            equipos = DataPlayer.leerEquipos();
            for (Team equipo : equipos) {
                teamComboBox.addItem(equipo.getNombre());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizarDetallesEquipo() {
        int selectedIndex = teamComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            Team selectedTeam = equipos.get(selectedIndex);
            rankingField.setText(String.valueOf(selectedTeam.getRanking()));
            flagLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource(selectedTeam.getRutaBandera())));
        }
    }

    private void mostrarJugadores() {
        int selectedIndex = teamComboBox.getSelectedIndex();
        if (selectedIndex >= 0) {
            Team selectedTeam = equipos.get(selectedIndex);
            new GuiPlayers(selectedTeam);
        }
    }
}
