package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import models.Hero;
import models.ICharacter;
import models.IModel;
import models.Model;
import models.Unit;
import ModifiedPanels.ImagePanel;
import ModifiedPanels.StatsPanel;
import controllers.CreateArmyCodexController;
import controllers.EditHeroController;
import controllers.GdcController;
import controllers.ItemController;
import controllers.MountCreationController;
import exceptions.NullFieldException;

/**
 * Main GUI Class.
 * From this GUI the user is able to perform the following tasks:
 *   Select a Unit from the loaded ArmyList in order to add it to the CurrentArmy.
 *   Increment or Decrement the occurrences of a unit in the Army.
 *   Delete a Unit/Hero from the currentArmyList.
 *   Create a new Item using the "AddItem" button.
 *   Move the selected Unit up and down in the CurrentArmy to achieve the desired order.
 *
 * @author MatteoOrzes
 *
 */

public class ArmyBuildGUI extends AbstractBasicGUI implements IArmyBuildGUI, ActionListener {
	// Here i created some useful constant to set the bounds of the components in the GUI, i'll use the
	// same constant multiple times to set the bounds of various component aligned with each others.
	// I'm sorry but i haven't used a constant for all the numbers in this class and its possible to 
	// find some awful magic numbers.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension FRAME_DIM = new Dimension(850, 585);
	private static final Dimension MAINPANEL_DIM = new Dimension(844, 538);
	private static final Dimension DESCRIPTIONPANELDIM = new Dimension(300, 325);
	private static final Dimension DESCRIPTIONPANELSCROLL_DIM = new Dimension(270, 300);
	private static final Dimension LISTSCROLLPANEDIM = new Dimension(475, 180);
	private static final String FILENAME = "dark-elves-warhammer-wallpaper.jpg";
    private static final String ERROR_UNIT_NOT_FOUND = "Error: unit not found.";
    private static final String ERROR_UNSUPPORTED = "Error: a hero cant change his occurrence";
	private static final String ERROR_NO_MOUNT = "Error: no mount to remove.";
	private static final String DEFAULT_CODEX_EXTENSION = "co";
	private static final String DEFAULT_LIST_EXTENSION = "ar";
	private static final int BUTTONHEIGHT = 25;
	private static final int BUTTONWIDTH = 90;
	
	// Constants used in order to properly align components using setBounds methods in the specified location of the frame.
	private static final int COORDINATE_X = 15;
	private static final int COORDINATE_Y = 255; 
	private static final int COORDINATE_X_1 = 535;
	private static final int COORDINATE_Y_1 = 10;
	private static final int COORDINATE_X_2 = 25;
	private static final int COORDINATE_Y_2 = 35;
	private static final int EDITINGBUTTONY = 345;
	private static final int MINOR_SPACE = 30;
	private static final int SPACE = 100;
	private static final int EDITINGBUTTONX = 600;
	private static final int PANELHEIGHT = 230;
	private static final int PANELWIDTH = 505;
	
	private IArmyBuildObserver observer;
	private boolean listSelected = true;
	
	private final ImagePanel mainPanel;
	private final JFileChooser fileDialog;
	private final JPanel unitListPanel;
	private final JList<Object> currentArmyList;
	private final JList<Object> unitList;
	private final JScrollPane unitListPanelScroll;
	private final StatsPanel stats;
	private final JPanel currentArmyPanel;
	private final JLabel totalCost;
	private final JScrollPane currentArmyPanelScroll;
	private final StatsPanel stats2ND;
	private final JPanel descriptionPanel;
	private final JScrollPane descriptionPanelScroll;
	private final JButton buttonAdd;
	private final JButton buttonDelete;
	private final JButton buttonIncrementNumber;
	private final JButton buttonDecrementNumber;
	private final JButton buttonUnitUp;
	private final JButton buttonUnitDown;
	private final JButton buttonEditGDC;
	private final JButton buttonAddItem;
	private final JButton buttonAddMount;
	private final JButton buttonRemoveMount;
	private final JMenuBar menuBar;
	private final JMenu mainMenu;
	private final JMenuItem menuSaveAsTxt;
	private final JMenuItem menuCreateNewArmylist;
	private final JMenuItem menuLoadarmylist;
	private final JMenuItem menuSavecurrentarmy;
	private final JMenuItem menuLoadCodex;
	private final JMenuItem menuSaveCodex;
	private final JTextPane descriptionPane;
	
	/**
	 * Constructor for the class ArmyBuildGUI.
	 */
	public ArmyBuildGUI() {
		super();
		setResizable(false);
		this.setTitle("ArmyBuildGUI");
		this.setSize(FRAME_DIM);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		this.fileDialog = new JFileChooser(); //dialog for file choice.

		mainPanel = new ImagePanel(FILENAME, MAINPANEL_DIM);
		mainPanel.setLocation(0, 0);
		mainPanel.setSize(MAINPANEL_DIM);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);

		unitListPanel = new JPanel();
		unitListPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Codex",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		unitListPanel.setBounds(COORDINATE_X_2, COORDINATE_Y_1, PANELWIDTH, PANELHEIGHT);
		mainPanel.add(unitListPanel);
		unitListPanel.setLayout(null);
		unitListPanel.setOpaque(false);
		
		unitList = new JList<>();
		unitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		unitList.setOpaque(false);
		unitList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				listSelected = true;
				updateDescription();
			}
		});
		
		unitListPanelScroll = new JScrollPane(unitList);
		unitListPanelScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		unitListPanelScroll.setSize(LISTSCROLLPANEDIM);
		unitListPanelScroll.setLocation(COORDINATE_X, COORDINATE_Y_2);
		unitListPanelScroll.setOpaque(false);
		unitListPanelScroll.getViewport().setOpaque(false);
		unitListPanel.add(unitListPanelScroll);
		
		stats = new StatsPanel();
		stats.setLocation(COORDINATE_X, COORDINATE_Y_1);
		stats.setOpaque(false);
		unitListPanel.add(stats);

		currentArmyPanel = new JPanel();
		currentArmyPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Current Army",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		currentArmyPanel.setBounds(COORDINATE_X_2, 285, PANELWIDTH, PANELHEIGHT);  // sorry for this magic number, i thought that creating a constant
		mainPanel.add(currentArmyPanel);                                           // only for this one would have been worse than the magic number itself.
		currentArmyPanel.setLayout(null);
		currentArmyPanel.setOpaque(false);

		currentArmyList = new JList<>();
		currentArmyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		currentArmyList.setOpaque(false);
		currentArmyList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				listSelected = false;
				updateDescription();
			}
		});

		currentArmyPanelScroll = new JScrollPane(currentArmyList);
		currentArmyPanelScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		currentArmyPanelScroll.setSize(LISTSCROLLPANEDIM);
		currentArmyPanelScroll.setLocation(COORDINATE_X, COORDINATE_Y_2);
		currentArmyPanelScroll.setOpaque(false);
		currentArmyPanelScroll.getViewport().setOpaque(false);
		currentArmyPanel.add(currentArmyPanelScroll);
		
		stats2ND = new StatsPanel();
		stats2ND.setLocation(COORDINATE_X, COORDINATE_Y_1);
		stats2ND.setOpaque(false);
		currentArmyPanel.add(stats2ND);

		descriptionPanel = new JPanel();
		descriptionPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Description",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		descriptionPanel.setLocation(COORDINATE_X_1, COORDINATE_Y_1);
		descriptionPanel.setSize(DESCRIPTIONPANELDIM);
		mainPanel.add(descriptionPanel);
		descriptionPanel.setLayout(null);
		descriptionPanel.setOpaque(false);

		descriptionPane = new JTextPane();
		descriptionPane.setOpaque(false);
		descriptionPane.setEditable(false);
		descriptionPane.setForeground(Color.WHITE);

		descriptionPanelScroll = new JScrollPane(descriptionPane);
		descriptionPanelScroll.setSize(DESCRIPTIONPANELSCROLL_DIM);
		descriptionPanelScroll.setLocation(COORDINATE_X, 15); // sorry for this magic number, i thought that creating a constant
		descriptionPanelScroll.setOpaque(false);              // only for this one would have been worse than the magic number itself.
		descriptionPanelScroll.getViewport().setOpaque(false);
		descriptionPanel.add(descriptionPanelScroll);
		
		buttonAdd = new JButton("Add");
		buttonAdd.setBounds(COORDINATE_X + COORDINATE_X_2, COORDINATE_Y, BUTTONWIDTH, BUTTONHEIGHT);
		buttonAdd.addActionListener(this);
		mainPanel.add(buttonAdd);

		buttonDelete = new JButton("Delete");
		buttonDelete.setBounds(COORDINATE_X + COORDINATE_X_2 + SPACE, COORDINATE_Y, BUTTONWIDTH, BUTTONHEIGHT);
		buttonDelete.addActionListener(this);
		mainPanel.add(buttonDelete);

		buttonIncrementNumber = new JButton("Increment");
		buttonIncrementNumber.setBounds(COORDINATE_X + COORDINATE_X_2 + 2*SPACE, COORDINATE_Y, BUTTONWIDTH+7, BUTTONHEIGHT);
		buttonIncrementNumber.addActionListener(this);
		mainPanel.add(buttonIncrementNumber);

		buttonDecrementNumber = new JButton("Decrement");
		buttonDecrementNumber.setBounds(COORDINATE_X +  + COORDINATE_X_2 + 3*SPACE + 7, COORDINATE_Y, BUTTONWIDTH+7, BUTTONHEIGHT); // the add of 7 in location X for this button is due to the fact that the previous button is wider than the others.
		buttonDecrementNumber.addActionListener(this);
		mainPanel.add(buttonDecrementNumber);

		
		totalCost = new JLabel("TOTAL LIST COST : 0");
		totalCost.setBounds(EDITINGBUTTONX, EDITINGBUTTONY, BUTTONWIDTH + SPACE, BUTTONHEIGHT);
		totalCost.setForeground(Color.WHITE);
		totalCost.setOpaque(false);
		mainPanel.add(totalCost);
		
		buttonUnitUp = new JButton("UnitUP");
		buttonUnitUp.setBounds(EDITINGBUTTONX, EDITINGBUTTONY + MINOR_SPACE, BUTTONWIDTH, BUTTONHEIGHT);
		buttonUnitUp.addActionListener(this);
		mainPanel.add(buttonUnitUp);
		
		buttonAddMount = new JButton("AddMount");
		buttonAddMount.setBounds(EDITINGBUTTONX + SPACE, EDITINGBUTTONY + MINOR_SPACE, BUTTONWIDTH + 25, BUTTONHEIGHT);
		buttonAddMount.addActionListener(this);
		mainPanel.add(buttonAddMount);
		
		buttonRemoveMount = new JButton("RemoveMount");
		buttonRemoveMount.setBounds(EDITINGBUTTONX + SPACE, EDITINGBUTTONY + 2 * MINOR_SPACE, BUTTONWIDTH + 25, BUTTONHEIGHT);
		buttonRemoveMount.addActionListener(this);
		mainPanel.add(buttonRemoveMount);

		buttonUnitDown = new JButton("UnitDown");
		buttonUnitDown.setBounds(EDITINGBUTTONX, EDITINGBUTTONY + 2 * MINOR_SPACE, BUTTONWIDTH, BUTTONHEIGHT);
		buttonUnitDown.addActionListener(this);
		mainPanel.add(buttonUnitDown);
		
		buttonEditGDC = new JButton("Edit");
		buttonEditGDC.setBounds(EDITINGBUTTONX, EDITINGBUTTONY + 3 * MINOR_SPACE, BUTTONWIDTH, BUTTONHEIGHT);
		buttonEditGDC.addActionListener(this);
		mainPanel.add(buttonEditGDC);
		
		buttonAddItem = new JButton("Add Item");
		buttonAddItem.setBounds(EDITINGBUTTONX, EDITINGBUTTONY + 4 * MINOR_SPACE, BUTTONWIDTH, BUTTONHEIGHT);
		buttonAddItem.addActionListener(this);
		mainPanel.add(buttonAddItem);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mainMenu = new JMenu("MainMenu");
		menuBar.add(mainMenu);

		menuCreateNewArmylist = new JMenuItem("Create new Armylist");
		menuCreateNewArmylist.addActionListener(this);
		mainMenu.add(menuCreateNewArmylist);

		menuLoadarmylist = new JMenuItem("LoadArmyList");
		menuLoadarmylist.addActionListener(this);
		mainMenu.add(menuLoadarmylist);
		
		menuSaveCodex = new JMenuItem("Save Codex");
		menuSaveCodex.addActionListener(this);
		mainMenu.add(menuSaveCodex);

		menuLoadCodex = new JMenuItem("Load Codex");
		menuLoadCodex.addActionListener(this);
		mainMenu.add(menuLoadCodex);

		menuSavecurrentarmy = new JMenuItem("SaveCurrentArmy");
		menuSavecurrentarmy.addActionListener(this);
		mainMenu.add(menuSavecurrentarmy);
		
		menuSaveAsTxt = new JMenuItem("Save as Txt");
		menuSaveAsTxt.addActionListener(this);
		mainMenu.add(menuSaveAsTxt);
		
		this.fileDialog.addChoosableFileFilter(new FileNameExtensionFilter("Codex list(*.co)", DEFAULT_CODEX_EXTENSION));
		this.fileDialog.addChoosableFileFilter(new FileNameExtensionFilter("Army list(*.ar)", DEFAULT_LIST_EXTENSION));
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		final Object source = e.getSource(); 
		if (source.equals(menuCreateNewArmylist)) {
			final CreateArmyCodexController controller = new CreateArmyCodexController(new Model());
			this.setEnabled(false);
			final ICreateArmyCodexGUI gui = new CreateArmyCodexGUI(this);
			controller.setView(gui);
		} else if (source.equals(menuLoadarmylist)) {
			if (this.fileDialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.observer.loadListCmd(this.fileDialog.getSelectedFile().getPath());
				this.unitList.setListData(this.observer.getModel().getCodexlist().toArray());
				this.currentArmyList.setListData(this.observer.getModel().getArmyList().toArray());
				this.updateTotalCost();
			}
		} else if (source.equals(menuSaveCodex)) {
			if (this.fileDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.observer.saveCodexCmd(this.fileDialog.getSelectedFile().getPath() + "." + DEFAULT_CODEX_EXTENSION);
			}
		} else if (source.equals(menuLoadCodex)) {
			if (this.fileDialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.observer.loadCodexCmd(this.fileDialog.getSelectedFile().getPath());
				this.unitList.setListData(this.observer.getModel().getCodexlist().toArray());
				this.currentArmyList.setListData(this.observer.getModel().getArmyList().toArray());
				this.updateTotalCost();
			}
		} else if (source.equals(menuSavecurrentarmy)) {
			if (this.fileDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.observer.saveListCmd(this.fileDialog.getSelectedFile().getPath() + "." + DEFAULT_LIST_EXTENSION);
			}
		} else if (source.equals(menuSaveAsTxt)) {
			if (this.fileDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.observer.saveListAsTxtCmd(this.fileDialog.getSelectedFile().getPath() + "." + "txt");
			}
		} else if (source.equals(buttonAdd)) {
			if (listSelected) {
				try {
					this.observer.getModel().addToArmy(this.observer.getModel().getCodexlist().get(unitList.getSelectedIndex()));
				} catch (Exception e1) {
					this.showErrorDialog(ERROR_UNIT_NOT_FOUND);
				}
				this.currentArmyList.setListData(this.observer.getModel().getArmyList().toArray());
				this.updateTotalCost();
			}
		} else if (source.equals(buttonDelete)) {
			if (!listSelected) {
				try {
					this.observer.getModel().removeFromArmy(this.observer.getModel().getArmyList().get(currentArmyList.getSelectedIndex()));
				} catch (Exception e1) {
					this.showErrorDialog(ERROR_UNIT_NOT_FOUND);
				}
				this.currentArmyList.setListData(this.observer.getModel().getArmyList().toArray());
				this.updateTotalCost();
			}
		} else if (source.equals(buttonIncrementNumber)) {
			if (!listSelected && currentArmyList.getSelectedIndex() != -1) {
				try {
					this.observer.getModel().getArmyList().get(currentArmyList.getSelectedIndex()).addOccurrence();
				} catch (NullFieldException | UnsupportedOperationException e1) {
					this.showErrorDialog(ERROR_UNSUPPORTED);
				}
				this.currentArmyList.setListData(this.observer.getModel().getArmyList().toArray());
				this.updateTotalCost();
			}
		} else if (source.equals(buttonDecrementNumber)) {
			if (!listSelected && currentArmyList.getSelectedIndex() != -1) {
				try {
					this.observer.getModel().getArmyList().get(currentArmyList.getSelectedIndex()).removeOccurrence();
				} catch (NullFieldException | UnsupportedOperationException e1) {
					this.showErrorDialog(ERROR_UNSUPPORTED);
				}
				this.currentArmyList.setListData(this.observer.getModel().getArmyList().toArray());
				this.updateTotalCost();
			}
		} else if (source.equals(buttonUnitUp)) {
			if (!listSelected) {
				this.observer.moveUp(currentArmyList.getSelectedIndex());
				this.currentArmyList.setListData(this.observer.getModel().getArmyList().toArray());
			}
		} else if (source.equals(buttonUnitDown)) {
			if (!listSelected) {
				this.observer.moveDown(currentArmyList.getSelectedIndex());
				this.currentArmyList.setListData(this.observer.getModel().getArmyList().toArray());
			}	
		} else if (source.equals(buttonAddItem)) {
			if (!listSelected && currentArmyList.getSelectedIndex() != -1) {
				this.setEnabled(false);
				final ItemController controller = new ItemController(this.observer.getModel());
				final IItemGUI gui = new ItemGUI(currentArmyList.getSelectedIndex(), this);
				controller.setView(gui);
			}
		} else if (source.equals(buttonEditGDC)) {
			if (!listSelected && currentArmyList.getSelectedIndex() != -1) {
				if (this.observer.getModel().getArmyList().get(currentArmyList.getSelectedIndex()) instanceof Unit) {
				this.setEnabled(false);
				final GdcController controller = new GdcController(this.observer.getModel());
				final IGdcGUI gui = new GdcGUI(this, currentArmyList.getSelectedIndex());
				controller.setView(gui);
				} else if (this.observer.getModel().getArmyList().get(currentArmyList.getSelectedIndex()) instanceof Hero) {
				this.setEnabled(false);
				final EditHeroController controller = new EditHeroController(this.observer.getModel());
				final IEditHeroGUI gui = new EditHeroGUI(this, currentArmyList.getSelectedIndex());
				controller.setView(gui);
				}
				this.currentArmyList.setListData(this.observer.getModel().getArmyList().toArray());
			}
		} else if (source.equals(buttonAddMount)) {
			if (!listSelected && currentArmyList.getSelectedIndex() != -1) {
				this.setEnabled(false);
				final MountCreationController controller = new MountCreationController(this.observer.getModel());
				final IMountCreateGUI gui = new MountCreateGUI(this, currentArmyList.getSelectedIndex());
				controller.setView(gui);
			}
		} else if (source.equals(buttonRemoveMount)) {
			if (!listSelected && currentArmyList.getSelectedIndex() != -1) {
				try {
					this.observer.getModel().getArmyList().get(currentArmyList.getSelectedIndex()).removeMount();
				} catch (NullFieldException e1) {
					this.showErrorDialog(ERROR_NO_MOUNT);
				}
				this.currentArmyList.setListData(this.observer.getModel().getArmyList().toArray());
				this.updateTotalCost();
			}
		}
	}
	
	@Override
	public void updateDescription() {
		if (listSelected && unitList.getSelectedIndex() != -1) {
			String description = this.observer.getModel().getCodexlist().get(unitList.getSelectedIndex()).toDescription();
			description += "\nDescription:\n";
			description += this.observer.getModel().getCodexlist().get(unitList.getSelectedIndex()).getDescription();
			descriptionPane.setText(description);
		} else if (currentArmyList.getSelectedIndex() != -1) {
			String description = this.observer.getModel().getArmyList().get(currentArmyList.getSelectedIndex()).toDescription();
			description += "\nDescription:\n";
			description += this.observer.getModel().getArmyList().get(currentArmyList.getSelectedIndex()).getDescription();
			descriptionPane.setText(description);
		}
	}
	
	@Override
	public void updateTotalCost() {
		int totalcost = 0;
		for (final ICharacter character : this.observer.getModel().getArmyList()) {
			totalcost += character.getCost();
		}
		totalCost.setText("TOTAL LIST COST : " + totalcost);
	}
	
	@Override
	public JList<Object> getUnitList() {
		return this.unitList;
	}
	
	@Override
	public JList<Object> getCurrentArmyList() {
		return this.currentArmyList;
	}

	@Override
	public void attachObserver(final IArmyBuildObserver newObserver) {
		this.observer = newObserver;
	}

	/**
	 * interface for an Observer of ArmyBuildGUI.
	 * @author MatteoOrzes
	 *
	 */
	public interface IArmyBuildObserver {
		
		/**
		 * Method invoked in order to get the model bounded to the controller. 
		 * @return IModel, the currently used Model
		 */
		IModel getModel();
		
		/**
	     * Method invoked when a load codex command has been triggered by the view
	     * Source: A. Santi Acme Exam project 
	     * @param path The path from which load the model
	     */
	    void loadCodexCmd(String path);
	    
	    /**
	     * Method invoked when a save codex command has been triggered by the view
	     * Source: A. Santi Acme Exam project 
	     * @param path The path to which save the model
	     */
	    void saveCodexCmd(String path);
	    
	    /**
	     * Method invoked when the view triggered the command for saving the data
	     * in the default path
	     * Source: A. Santi Acme Exam project
	     */
	    void saveDataCmd();
	    
	    /**
	      * Method invoked when the view triggered the command for loading the data
	     * from the default path
	     * Source: A. Santi Acme Exam project
	    */
	    void loadDataCmd();
	    
	    /**
	     * Method invoked when a save list command has been triggered by the view.
	     * Source: A. Santi Acme Exam project
	     * @param path The path to which save the model
	     */
	    void saveListCmd(String path);
	    
	    /**
	     * Method invoked when a save list as txt command has been triggered by the view.
	     * @param path the path to which save the txt.
	     */
	    void saveListAsTxtCmd(String path);
	    
	    /**
	     * Method invoked when a load list command has been triggered by the view.
	     * Source: A. Santi Acme Exam project 
	     * @param path The path to which save the model
	     */
	    void loadListCmd(String path);
	    
	    /**
	     * Sets the new parameter view as default.
	     * Source: A. Santi Acme Exam project
	     * @param v the new view
	     */
	    void setView(IArmyBuildGUI v);
	    
	    /**
	     * Method invoked when a UnitUp command has been triggered by the view.
	     * @param index the index of the last selected unit in the list
	     */	    
	    void moveUp(int index);
	    

	    /**
	     * Method invoked when a UnitDown command has been triggered by the view.
	     * @param index the index of the last selected unit in the list
	     */	    
	    void moveDown(int index);
	}
}
