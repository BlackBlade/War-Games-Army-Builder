package views;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ModifiedPanels.ImagePanel;
/**
 * Abstract class that works as a base for UnitCreationGUI and MuontCreationGUI.
 * this class contains all the common components from UnitCreation and MountCreation GUIs.
 * @author MatteoOrzes
 *
 */
public abstract class AbstractUnitCreateGUI extends AbstractBasicGUI implements IAbstractUnitCreateGUI {
	
	private static final long serialVersionUID = 1L;
	private static final int DEFAULT_STATS_LENGTH = 9;	
	private static final String FILENAME = "dark-elves-warhammer-wallpaper.jpg";
	private static final String ERROR_FIELD = "Error: a field can't be empty.";
	private static final Dimension FRAMEDIMENSION = new Dimension(500, 465);
	private static final Dimension PANELDIMENSION = new Dimension(494, 437);
	private static final Dimension SCROLLPANEDIMENSION = new Dimension(230, 120);
	private static final Dimension DESCRIPTIONPANELDIMENSION = new Dimension(250, 150);
	private static final Dimension RIGIDAREADIMENSION = new Dimension(10, 25);
	private static final Dimension RIGIDAREADIMENSION1 = new Dimension(10, 14);
	private static final int STATSPANELHEIGHT = 400;
	private static final int STATSPANELWIDTH = 200;
	private static final int LABELNTEXTHEIGHT = 15;
	private static final int LABELWIDTH = 45;
	
	// These are some constants used in the setBounds of these class, 
	// there aren't constants for every single magic number because i created them only
	// for the most occurring numbers.
	
	private static final int COORDINATE_X = 25;
	private static final int COORDINATE_Y = 25;
	private static final int COORDINATE_X_1 = 80;
	private static final int COORDINATE_Y_1 = 15;
	private static final int COORDINATE_X_2 = 225;
	
	protected ImagePanel mainPanel;
	private final JPanel insertStatsPanel;
	private final JPanel labelsPanel;
	private final JPanel textsPanel;
	private final JPanel nameCostPanel;
	private final JPanel descriptionPanel;
	
	private final JTextField nametextField;
	private final JTextField costtextField;
	private final JLabel labelName;
	private final JLabel labelCost;
	
	private final JLabel movement;
	private final JLabel weaponSkill;
	private final JLabel ballisticSkill;
	private final JLabel strength;
	private final JLabel toughness;
	private final JLabel wounds;
	private final JLabel initiative;
	private final JLabel attacks;
	private final JLabel leadership;

	private final JTextField[] textFields;

	private final JTextArea descriptionText;
	private final JScrollPane descriptionPanelScroll;
	protected JButton clearButton;

	/**
	 * Constructor for the class AbstractUnitCreateGUI that will be extended by UnitCreateGUI and MountCreateGUI.
	 */
	public AbstractUnitCreateGUI() {
		super();		
		this.setResizable(false);
		this.setTitle("Unit Creation");
		this.setSize(FRAMEDIMENSION);
		getContentPane().setLayout(null);

		mainPanel = new ImagePanel(FILENAME, PANELDIMENSION);
		mainPanel.setLocation(0, 0);
		mainPanel.setSize(PANELDIMENSION);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		insertStatsPanel = new JPanel();
		insertStatsPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "INSERT STATS HERE",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		insertStatsPanel.setBounds(10, 15, STATSPANELWIDTH, STATSPANELHEIGHT);
		insertStatsPanel.setLayout(new BoxLayout(insertStatsPanel, BoxLayout.X_AXIS));
		insertStatsPanel.setOpaque(false);
		mainPanel.add(insertStatsPanel);
		
		movement = new JLabel("Movement");
		movement.setForeground(Color.WHITE);
		weaponSkill = new JLabel("Weapon Skill");
		weaponSkill.setForeground(Color.WHITE);
		ballisticSkill = new JLabel("Ballistic Skill");
		ballisticSkill.setForeground(Color.WHITE);
		strength = new JLabel("Strength");
		strength.setForeground(Color.WHITE);
		toughness = new JLabel("Toughness");
		toughness.setForeground(Color.WHITE);
		wounds = new JLabel("Wounds");
		wounds.setForeground(Color.WHITE);
		initiative = new JLabel("Initiative");
		initiative.setForeground(Color.WHITE);
		attacks = new JLabel("Attacks");
		attacks.setForeground(Color.WHITE);
		leadership = new JLabel("Leadership");
		leadership.setForeground(Color.WHITE);
		
		labelsPanel = new JPanel();
		labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
		labelsPanel.add(movement);
		labelsPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));
		labelsPanel.add(weaponSkill);
		labelsPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));
		labelsPanel.add(ballisticSkill);
		labelsPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));
		labelsPanel.add(strength);
		labelsPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));
		labelsPanel.add(toughness);
		labelsPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));
		labelsPanel.add(wounds);
		labelsPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));
		labelsPanel.add(initiative);
		labelsPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));
		labelsPanel.add(attacks);
		labelsPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));
		labelsPanel.add(leadership);
		labelsPanel.setOpaque(false);
		insertStatsPanel.add(labelsPanel);
		insertStatsPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));
		
		textFields = new JTextField[DEFAULT_STATS_LENGTH];
		for (int i = 0; i < DEFAULT_STATS_LENGTH; i++) {
			textFields[i] = new JTextField("");
		}
		
		textsPanel = new JPanel();
		textsPanel.setLayout(new BoxLayout(textsPanel, BoxLayout.Y_AXIS));
		for (int i = 0; i < DEFAULT_STATS_LENGTH; i++) {
			textsPanel.add(Box.createRigidArea(RIGIDAREADIMENSION1));
			textsPanel.add(textFields[i]);
		}
		textsPanel.setOpaque(false);
		insertStatsPanel.add(textsPanel);
		
		nameCostPanel = new JPanel();
		nameCostPanel .setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "INSERT NAME AND COST HERE",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		nameCostPanel.setBounds(COORDINATE_X_2, COORDINATE_Y_1, STATSPANELWIDTH, 80);
		nameCostPanel.setOpaque(false);
		mainPanel.add(nameCostPanel);
		nameCostPanel.setLayout(null);
		
		labelName = new JLabel("NAME");
		labelName.setForeground(Color.WHITE);
		labelName.setBounds(COORDINATE_X, COORDINATE_Y, LABELWIDTH, LABELNTEXTHEIGHT);
		nameCostPanel.add(labelName);
		
		labelCost = new JLabel("COST");
		labelCost.setForeground(Color.WHITE);
		labelCost.setBounds(COORDINATE_X, COORDINATE_Y * 2, LABELWIDTH, LABELNTEXTHEIGHT);
		nameCostPanel.add(labelCost);
		
		nametextField = new JTextField();
		nametextField.setBounds(COORDINATE_X_1, COORDINATE_Y, LABELWIDTH * 2, LABELNTEXTHEIGHT);
		nameCostPanel.add(nametextField);
		
		costtextField = new JTextField();
		costtextField.setBounds(COORDINATE_X_1, COORDINATE_Y * 2, LABELWIDTH * 2, LABELNTEXTHEIGHT);
		nameCostPanel.add(costtextField);
		
		descriptionPanel = new JPanel();
		descriptionPanel.setLayout(null);
		descriptionPanel.setBorder(new TitledBorder(null, "INSERT DESCRIPTION",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		descriptionPanel.setLocation(COORDINATE_X_2, 100);
		descriptionPanel.setSize(DESCRIPTIONPANELDIMENSION);
		descriptionPanel.setOpaque(false);
		mainPanel.add(descriptionPanel);

		descriptionText = new JTextArea("");
		descriptionText.setForeground(Color.WHITE);
		descriptionText.setOpaque(false);
		
		descriptionPanelScroll = new JScrollPane(descriptionText);
		descriptionPanelScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descriptionPanelScroll.setSize(SCROLLPANEDIMENSION);
		descriptionPanelScroll.setLocation(10, 20);
		descriptionPanelScroll.setOpaque(false);
		descriptionPanelScroll.getViewport().setOpaque(false);
		descriptionPanel.add(descriptionPanelScroll);
		
		clearButton = new JButton("Clear");
		clearButton.setBounds(355, 265, 75, 25);
		mainPanel.add(clearButton);
	}
	
	@Override
	public void clearCommand() {
		for (int i = 0; i < DEFAULT_STATS_LENGTH; i++) {
			textFields[i].setText("");
		}
	}
	
	@Override
	public int[] getStats() {
		int[] stats = new int[DEFAULT_STATS_LENGTH];
		try {
			for (int i = 0; i < DEFAULT_STATS_LENGTH; i++) {
				stats[i] = Integer.parseInt(textFields[i].getText());
			}
		} catch (Exception e1) {
			this.showErrorDialog(ERROR_FIELD);
		}
		return stats;
	}
	
	@Override
	public String getName() {
		return this.nametextField.getText();
	}
	
	@Override
	public String getDescription() {
		return this.descriptionText.getText();
	}
	
	@Override
	public int getCost() {
		return Integer.parseInt(this.costtextField.getText());
	}
}
