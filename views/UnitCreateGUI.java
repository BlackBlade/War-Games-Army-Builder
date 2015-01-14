package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import exceptions.DuplicatedUnitException;
import exceptions.NullFieldException;
import exceptions.StatsFaultException;
/**
 * This class allows the user to create a new unit inserting all the stats, the name and the cost (for a single model).
 * Furthermore the user can decide if the new unit will be a unit or an hero. If it will be a unit the user must specify the cost
 * for every model of the Command Group (Standard, Champion, Musician).
 * @author MatteoOrzes
 *
 */
public class UnitCreateGUI extends AbstractUnitCreateGUI implements IUnitCreateGUI, ActionListener {
	// Here i created some useful constant to set the bounds of the components in the GUI, i'll use the
	// same constant multiple times to set the bounds of various component aligned with each others.
	// I'm sorry but i haven't used a constant for all the numbers in this class and its possible to 
	// find some awful magic numbers.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final CreateArmyCodexGUI frameFather;
	private UnitCreateObserver observer;
	private static final String[] STR = new String[]{"Unit", "Hero"};
	private static final int GDCPANELHEIGHT = 250;
	private static final int GDCPANELWIDTH = 100;
	private static final Dimension RIGIDAREADIM = new Dimension(10, 10);
	private static final Dimension RIGIDAREADIM2ND = new Dimension(10, 5);
	private static final String ERR_UNIT = "Error while creating the unit.";
	
	private final JPanel insertGDCPanel;
	private final JPanel labelsGDCPanel;
	private final JPanel textsGDCPanel;
	
	private final JLabel standardCost;
	private final JLabel championCost;
	private final JLabel musicianCost;
	
	private final JTextField textstandardCost;
	private final JTextField textchampionCost;
	private final JTextField textmusicianCost;
	
	private final JButton buttonConfirmUnit;
	private final JComboBox<String> choice;
	/**
	 * Constructor for the class UnitCreateGUI.
	 * @param frame the frame from which this GUi has been created.
	 */
	public UnitCreateGUI(final CreateArmyCodexGUI frame) {
		super();		
		this.frameFather = frame;
		this.setLocationRelativeTo(frameFather);
		this.setTitle("Unit Creation");
		
		choice = new JComboBox<String>(STR);
		choice.addActionListener(this);
		choice.setBounds(225, 265, 115, 25);
		mainPanel.add(choice);
		
		insertGDCPanel = new JPanel();
		insertGDCPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "INSERT GDC HERE, ONLY FOR UNITS",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		insertGDCPanel.setBounds(225, 295, GDCPANELHEIGHT, GDCPANELWIDTH);
		insertGDCPanel.setLayout(new BoxLayout(insertGDCPanel, BoxLayout.X_AXIS));
		insertGDCPanel.setOpaque(false);
		mainPanel.add(insertGDCPanel);
		
		labelsGDCPanel = new JPanel();
		labelsGDCPanel.setOpaque(false);
		labelsGDCPanel.setLayout(new BoxLayout(labelsGDCPanel, BoxLayout.Y_AXIS));
		
		textsGDCPanel = new JPanel();
		textsGDCPanel.setOpaque(false);
		textsGDCPanel.setLayout(new BoxLayout(textsGDCPanel, BoxLayout.Y_AXIS));
		
		standardCost = new JLabel("Standard Cost");
		standardCost.setForeground(Color.WHITE);
		labelsGDCPanel.add(standardCost);
		labelsGDCPanel.add(Box.createRigidArea(RIGIDAREADIM));
		musicianCost = new JLabel("Musician Cost");
		musicianCost.setForeground(Color.WHITE);
		labelsGDCPanel.add(musicianCost);
		labelsGDCPanel.add(Box.createRigidArea(RIGIDAREADIM));
		championCost = new JLabel("Champion Cost");
		championCost.setForeground(Color.WHITE);
		labelsGDCPanel.add(championCost);
		labelsGDCPanel.add(Box.createRigidArea(RIGIDAREADIM));
		
		textstandardCost = new JTextField();
		textsGDCPanel.add(textstandardCost);
		textsGDCPanel.add(Box.createRigidArea(RIGIDAREADIM2ND));
		textmusicianCost = new JTextField();
		textsGDCPanel.add(textmusicianCost);
		textsGDCPanel.add(Box.createRigidArea(RIGIDAREADIM2ND));
		textchampionCost = new JTextField();
		textsGDCPanel.add(textchampionCost);
		textsGDCPanel.add(Box.createRigidArea(RIGIDAREADIM2ND));

		insertGDCPanel.add(labelsGDCPanel);
		insertGDCPanel.add(Box.createRigidArea(RIGIDAREADIM2ND));
		insertGDCPanel.add(textsGDCPanel);

		buttonConfirmUnit = new JButton("CONFIRM UNIT");
		buttonConfirmUnit.addActionListener(this);
		buttonConfirmUnit.setBounds(375, 400, 115, 25);
		mainPanel.add(buttonConfirmUnit);
		
		clearButton.addActionListener(this);

		this.addWindowListener(new WindowAdapter() { public void windowClosing(final WindowEvent e) {
			frameFather.setEnabled(true);
			frameFather.updateView();
		} });

		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		final Object source = e.getSource();
		if (source.equals(buttonConfirmUnit)) {
			if (choice.getSelectedItem().equals("Unit")) {
				try {
					this.observer.createUnit();
				} catch (DuplicatedUnitException | StatsFaultException
						| NullFieldException | NumberFormatException e1) {
					this.showErrorDialog(ERR_UNIT);
				}
			} else {
				try {
					this.observer.createHero();
				} catch (DuplicatedUnitException | StatsFaultException
						| NullFieldException | NumberFormatException e1) {
					this.showErrorDialog(ERR_UNIT);
				}
			}
			this.frameFather.setEnabled(true);
			frameFather.updateView();
			this.dispose();
		} else if (source.equals(choice)) {
			if (choice.getSelectedItem().equals("Hero")) {
				this.textstandardCost.setEnabled(false);
				this.textchampionCost.setEnabled(false);
				this.textmusicianCost.setEnabled(false);
			} else {
				this.textstandardCost.setEnabled(true);
				this.textchampionCost.setEnabled(true);
				this.textmusicianCost.setEnabled(true);
			}
		} else if (source.equals(clearButton)) {
			this.clearCommand();
		}
	}
	
	@Override
	public void clearCommand() {
		super.clearCommand();
		this.textchampionCost.setText("");
		this.textmusicianCost.setText("");
		this.textstandardCost.setText("");
	}
	
	@Override
	public void attachObserver(final UnitCreateObserver newObserver) {
		this.observer = newObserver;
	}
	
	@Override
	public int getStandardCost() {
		return Integer.parseInt(this.textstandardCost.getText());
	}

	@Override
	public int getChampionCost() {
		return Integer.parseInt(this.textchampionCost.getText());
	}

	@Override
	public int getMusicianCost() {
		return Integer.parseInt(this.textmusicianCost.getText());
	}
	
	/**
	 * Interface for an Observer of UnitCreateGUI.
	 * @author MatteoOrzes
	 *
	 */
	public interface UnitCreateObserver {
		
		/**
	     * Sets the new parameter view as default.
	     * Source: A. Santi Acme Exam project
	     * @param v the new view
	     */
		void setView(final IUnitCreateGUI v);
		
		/**
		 * Add to the Codex list the Hero with the inserted characteristics.
		 * @throws DuplicatedUnitException no Unit/Hero can be duplicated.
		 * @throws StatsFaultException 
		 * @throws NullFieldException 
		 */
		void createHero() throws DuplicatedUnitException, StatsFaultException, NullFieldException;
		
		/**
		 * Add to the Codex list the Unit with the inserted characteristics.
		 * @throws DuplicatedUnitException no Unit/Hero can be duplicated.
		 * @throws StatsFaultException 
		 * @throws NullFieldException 
		 */
		void createUnit() throws DuplicatedUnitException, StatsFaultException, NullFieldException;
	}

}
