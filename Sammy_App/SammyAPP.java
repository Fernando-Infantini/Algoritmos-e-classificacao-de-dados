import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SammyAPP {

	private Rentals rentals = new Rentals();
	private JFrame frame;
	private JComboBox<String> equipmentComboBox;
	private JTextField timeTextField;
	private JCheckBox lessonCheckBox;
	private JTextPane resultTextPane;
	private JButton rentButton;
	private JButton listButton;
	private JScrollPane resultScrollPane;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			SammyAPP gui = new SammyAPP();
			gui.createAndShowGUI();
		});
	}

	private void createAndShowGUI() {
		frame = new JFrame("Sammy's Seashore Supplies");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);

		JLabel equipmentLabel = new JLabel("Equipamento:");
		equipmentComboBox = new JComboBox<>(new String[] { "Jet Ski", "Barco Pontão", "Barco a Remo", "Canoa",
				"Caiaque", "Cadeira de Praia", "Guarda-Sol", "Gazebo" });

		JLabel timeLabel = new JLabel("Tempo de  aluguel (em minutos):");
		timeTextField = new JTextField(10);

		lessonCheckBox = new JCheckBox("Incluir aula?");

		rentButton = new JButton("Confirmar Aluguel");
		rentButton.addActionListener(new RentButtonListener());

		listButton = new JButton("Listar Alugueis");
		listButton.addActionListener(new ListButtonListener());

		resultTextPane = new JTextPane();
		resultTextPane.setEditable(false);
		resultTextPane.setPreferredSize(
				new Dimension(30 * resultTextPane.getFontMetrics(resultTextPane.getFont()).charWidth('W'),
						10 * resultTextPane.getFontMetrics(resultTextPane.getFont()).getHeight()));
		resultScrollPane = new JScrollPane(resultTextPane);

		constraints.gridx = 0;
		constraints.gridy = 0;
		panel.add(equipmentLabel, constraints);

		constraints.gridx = 1;
		panel.add(equipmentComboBox, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		panel.add(timeLabel, constraints);

		constraints.gridx = 1;
		panel.add(timeTextField, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		panel.add(lessonCheckBox, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		panel.add(rentButton, constraints);

		constraints.gridx = 1;
		panel.add(listButton, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 2;
		panel.add(resultScrollPane, constraints);

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private class RentButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int equipmentIndex = equipmentComboBox.getSelectedIndex() + 1;
				int time = Integer.parseInt(timeTextField.getText());
				boolean hasLesson = lessonCheckBox.isSelected();

				String result = rentals.newRental(equipmentIndex, time, hasLesson);
				resultTextPane.setText(result);

				Container container = resultTextPane.getParent();
				container.invalidate();
				container.validate();
				container.repaint();
			} catch (InvalidEquipmentException | NumberFormatException ex) {
				resultTextPane.setText("Erro: " + ex.getMessage());
			}
		}
	}

	private class ListButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			resultTextPane.setText(rentals.listAll());
		}
	}
}
