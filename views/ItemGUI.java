package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;

import models.IModel;
import models.Item;
import ModifiedPanels.ImagePanel;
import exceptions.NullFieldException;
import exceptions.StatsFaultException;

/**
 * This class is used in order to provide the user a way to create and assign common and magic item
 * to units or heroes. The user must insert name cost and description of the item.
 * @author MatteoOrzes
 *
 */
public class ItemGUI extends JFrame implements IItemGUI, ActionListener {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private ItemObserver observer;
	private final int index;
	private final ArmyBuildGUI frameFather;
	private static final int LABELHEIGHT = 15;
	private static final int LABELWIDTH = 65;
	private static final int TEXTANDCHOICEHEIGHT = 20;
	private static final int TEXTANDCHOICEWIDTH = 100;
	private static final int TEXT_COLUMNS = 10;
	private static final int COORDINATE_X = 10;
	private static final int COORDINATE_Y = 10;
	private static final int COORDINATE_X_1 = 85;
	private static final int COORDINATE_Y_1 = 5;
	private static final int SPACE = 30;
	private static final String ERROR_STR = "Error";
	private static final String ERROR_ITEM = "Error while creating the item";
	
	private static final Dimension PANELDIMENSION = new Dimension(394, 272);
	private static final Dimension DESCRIPTIONPANELDIMENSION = new Dimension(375, 170);
	private static final Dimension FRAMEDIMENSION = new Dimension(400, 300);
	private static final Dimension DESCRIPTIONPANELSCROLL_DIM = new Dimension(355, 140);
	private static final String FILENAME = "dark-elves-warhammer-wallpaper.jpg";
	private static final String[] STR = new String[]{"Common Item", "Magic Item"};
	
	private final ImagePanel itemMenuPanel;
	private final JPanel descriptionPanel;
	private final JTextField itemNameText;
	private final JTextField itemCostText;
	private final JTextArea descriptionText;
	private final JScrollPane descriptionPanelScroll;
	private final JLabel labelItemName;
	private final JLabel labelItemCost;
	private final JLabel labelItemType;
	private final JComboBox<String> choice;
	private final JButton buttonConfirmItem;

	/**
	 * Constructor for the class ItemGUI.
	 * @param newIndex the index of the last selected Unit/hero.
	 * @param frame the frame from wich this one has been generated.
	 */
	public ItemGUI(final int newIndex, final ArmyBuildGUI frame) {
		super();
		this.index = newIndex;
		this.frameFather = frame;
		setResizable(false);
		this.setTitle("ItemGUI");
		this.setSize(FRAMEDIMENSION);
		this.setLocationRelativeTo(frameFather);
		this.getContentPane().setLayout(null);
		
		itemMenuPanel = new ImagePanel(FILENAME, PANELDIMENSION);
		this.getContentPane().add(itemMenuPanel);
		itemMenuPanel.setLocation(0, 0);
		itemMenuPanel.setSize(PANELDIMENSION);
		itemMenuPanel.setLayout(null);

		labelItemName = new JLabel("ITEM NAME");
		labelItemName.setForeground(Color.WHITE);
		labelItemName.setBounds(COORDINATE_X, COORDINATE_Y, LABELWIDTH, LABELHEIGHT);
		itemMenuPanel.add(labelItemName);

		labelItemCost = new JLabel("ITEM COST");
		labelItemCost.setForeground(Color.WHITE);
		labelItemCost.setBounds(COORDINATE_X, COORDINATE_Y + SPACE, LABELWIDTH, LABELHEIGHT);
		itemMenuPanel.add(labelItemCost);

		labelItemType = new JLabel("ITEM TYPE");
		labelItemType.setForeground(Color.WHITE);
		labelItemType.setBounds(COORDINATE_X, COORDINATE_Y + 2 * SPACE, LABELWIDTH, LABELHEIGHT);
		itemMenuPanel.add(labelItemType);

		itemNameText = new JTextField();
		itemNameText.setBounds(COORDINATE_X_1, COORDINATE_Y_1, TEXTANDCHOICEWIDTH, TEXTANDCHOICEHEIGHT);
		itemMenuPanel.add(itemNameText);
		itemNameText.setColumns(TEXT_COLUMNS);

		itemCostText = new JTextField();
		itemCostText.setBounds(COORDINATE_X_1, COORDINATE_Y_1 + SPACE, TEXTANDCHOICEWIDTH, TEXTANDCHOICEHEIGHT);
		itemMenuPanel.add(itemCostText);
		itemCostText.setColumns(TEXT_COLUMNS);
		
		choice = new JComboBox<String>(STR);
		choice.setBounds(COORDINATE_X_1, COORDINATE_Y_1 + 2 * SPACE, TEXTANDCHOICEWIDTH, TEXTANDCHOICEHEIGHT);
		itemMenuPanel.add(choice);

		
		descriptionPanel = new JPanel();
		descriptionPanel.setLayout(null);
		descriptionPanel.setBorder(new TitledBorder(null, "Description",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		descriptionPanel.setBounds(COORDINATE_X, COORDINATE_Y + 3 * SPACE, (int) DESCRIPTIONPANELDIMENSION.getWidth(), (int) DESCRIPTIONPANELDIMENSION.getHeight());
		descriptionPanel.setOpaque(false);
		itemMenuPanel.add(descriptionPanel);

		descriptionText = new JTextArea();
		descriptionText.setForeground(Color.WHITE);
		descriptionText.setLineWrap(true);
		descriptionText.setOpaque(false);

		descriptionPanelScroll = new JScrollPane(descriptionText);
		descriptionPanelScroll.setSize(DESCRIPTIONPANELSCROLL_DIM);
		descriptionPanelScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		descriptionPanelScroll.setLocation(COORDINATE_X, COORDINATE_Y * 2);
		descriptionPanelScroll.setOpaque(false);
		descriptionPanelScroll.getViewport().setOpaque(false);
		descriptionPanel.add(descriptionPanelScroll);

		buttonConfirmItem = new JButton("CONFIRM ITEM");
		buttonConfirmItem.addActionListener(this);
		buttonConfirmItem.setBounds(240, COORDINATE_Y_1 + SPACE, 120, 25);
		itemMenuPanel.add(buttonConfirmItem);

		this.addWindowListener(new WindowAdapter() { public void windowClosing(final WindowEvent e) {
			frameFather.setEnabled(true);
		} });
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		final Object source = e.getSource(); 
		if (source.equals(buttonConfirmItem)) {
			Item item = null;
			try {
				item = new Item(this.itemNameText.getText(), this.descriptionText.getText(), Integer.parseInt(this.itemCostText.getText()));
				this.observer.confirmItem(index, item);
			} catch (NumberFormatException | NullFieldException | StatsFaultException e1) {
				this.showErrorDialog(ERROR_ITEM);
			}
			frameFather.setEnabled(true);
			frameFather.updateTotalCost();
			this.dispose();
		}
	}
	
	@Override
	public void attachObserver(final ItemObserver newObserver) {
		this.observer = newObserver;
	}
	
	@Override
	public void showErrorDialog(final String msg) {
		JOptionPane.showMessageDialog(this, msg, ERROR_STR, JOptionPane.ERROR_MESSAGE);
	}
	
	@Override
	public Object getComboBoxValue() {
		return this.choice.getSelectedItem();
	}

	/**
	 * Interface for an observer of class ItemGUI.
	 * @author MatteoOrzes
	 *
	 */
	public interface ItemObserver {

		/**
		 * Getter for the currently used model.
		 * @return the Model we are working on.
		 */
		IModel getModel();

		/**
		 * Create a Item for the last selected Unit/Hero.
		 * @param newIndex index of the unit that will own the new Item.
		 * @param item the new Item that will be assigned to the Unit of position "Index".
		 * @throws NullFieldException if a field is null
		 */
		void confirmItem(final int newIndex, final Item item) throws NullFieldException;

	    /**
	     * Sets the new parameter view as default.
	     * Source: A. Santi Acme Exam project
	     * @param newGui the new view
	     */
		void setView(IItemGUI newGui);
	}
}
