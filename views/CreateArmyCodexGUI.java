package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
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

import models.IModel;
import ModifiedPanels.ImagePanel;
import ModifiedPanels.StatsPanel;
import controllers.UnitCreationController;
/**
 * Class used in order to create a GUI that offers the user the possibility to create a new Codex that will 
 * be used to create lots of different Army. The addNew button allows to create a new Unit/Hero in the codex.
 * @author MatteoOrzes
 *
 */
public class CreateArmyCodexGUI extends AbstractBasicGUI implements ICreateArmyCodexGUI, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ArmyBuildGUI frameFather;
	private ICreateArmyCodexObserver observer;
	private static final String FILENAME = "dark-elves-warhammer-wallpaper.jpg";
	private static final Dimension FRAME_DIM = new Dimension(760, 520);
	private static final Dimension MAINPANEL_DIM = new Dimension(754, 473);
	private static final Dimension DESCRIPTIONPANELSCROLL_DIM = new Dimension(170, 300);
	private static final String ERROR_UNIT = "Error: no unit is selected";
	private static final String DEFAULT_CODEX_EXTENSION = "co";
	private static final String DEFAULT_LIST_EXTENSION = "ar";
	
	// Here i created some useful constant to set the bounds of the components in the GUI, i'll use the
	// same constant multiple times to set the bounds of various component aligned with each others.
	// I'm sorry but i haven't used a constant for all the numbers in this class and its possible to 
	// find some awful magic numbers.
	private static final int BUTTONHEIGHT = 25;
	private static final int BUTTONWIDTH = 90;
	private static final int COORDINATE_X = 15;
	private static final int COORDINATE_X_1 = 535;
	private static final int COORDINATE_Y_1 = 10;
	private static final int COORDINATE_X_2 = 25;
	private static final int COORDINATE_Y_2 = 35;
	private static final int SPACE = 100;
	private static final int PANELHEIGHT = 350;
	private static final int PANELWIDTH = 505;
	private static final int SCROLLPANELHEIGHT = 300;
	private static final int SCROLLPANELWIDTH = 475;
	private static final int DESCRIPTIONPANELHEIGHT = 335;
	private static final int DESCRIPTIONPANELWIDTH = 200;
	
	private final JFileChooser fileDialog;
	private final ImagePanel mainPanel;
	private final JPanel currentUnitListPanel;
	private final JList<Object> currentUnitList;
	private final JScrollPane currentUnitListPanelScroll;
	private final StatsPanel stats;
	private final JPanel descriptionPanel;
	private final JTextPane descriptionPane;
	private final JScrollPane descriptionPanelScroll;
	private final JButton buttonAddNew;
	private final JButton buttonDelete;
	private final JMenuBar menuBar;
	private final JMenu mainMenu;
	private final JMenuItem menuLoadCodex;
	private final JMenuItem menuSaveCodex;

	/**
	 * Constructor for the class CreateArmyCodexGUI.
	 * @param frame the frame from which this GUI has been generated.
	 */
	public CreateArmyCodexGUI(final ArmyBuildGUI frame) {
		super();
		this.frameFather = frame;
		this.fileDialog = new JFileChooser();
		this.setResizable(false);
		this.setTitle("CreateArmyListGUI");
		this.setSize(FRAME_DIM);
		this.setLocationRelativeTo(frameFather);
		getContentPane().setLayout(null);
		
		mainPanel = new ImagePanel(FILENAME, MAINPANEL_DIM);
		mainPanel.setLocation(0, 0);
		mainPanel.setSize(MAINPANEL_DIM);
		getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		currentUnitListPanel = new JPanel();
		currentUnitListPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "CurrentUnitList",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		currentUnitListPanel.setBounds(COORDINATE_X_2, COORDINATE_Y_1, PANELWIDTH, PANELHEIGHT);
		mainPanel.add(currentUnitListPanel);
		currentUnitListPanel.setLayout(null);
		currentUnitListPanel.setOpaque(false);

		currentUnitList = new JList<>();
		currentUnitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		currentUnitList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				updateDescription();
			}
		});
		currentUnitList.setOpaque(false);

		currentUnitListPanelScroll = new JScrollPane(currentUnitList);
		currentUnitListPanelScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		currentUnitListPanelScroll.setSize(SCROLLPANELWIDTH, SCROLLPANELHEIGHT);
		currentUnitListPanelScroll.setLocation(COORDINATE_X, COORDINATE_Y_2);
		currentUnitListPanelScroll.setOpaque(false);
		currentUnitListPanelScroll.getViewport().setOpaque(false);
		currentUnitListPanel.add(currentUnitListPanelScroll);
		
		stats = new StatsPanel();
		stats.setLocation(COORDINATE_X, COORDINATE_Y_1);
		stats.setOpaque(false);
		currentUnitListPanel.add(stats);

		descriptionPanel = new JPanel();
		descriptionPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Description",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		descriptionPanel.setBounds(COORDINATE_X_1, COORDINATE_Y_1,  DESCRIPTIONPANELWIDTH, DESCRIPTIONPANELHEIGHT);
		mainPanel.add(descriptionPanel);
		descriptionPanel.setLayout(null);
		descriptionPanel.setOpaque(false);

		descriptionPane = new JTextPane();
		descriptionPane.setEditable(false);
		descriptionPane.setOpaque(false);
		descriptionPane.setForeground(Color.WHITE);
		
		descriptionPanelScroll = new JScrollPane(descriptionPane);
		descriptionPanelScroll.setSize(DESCRIPTIONPANELSCROLL_DIM);
		descriptionPanelScroll.setLocation(COORDINATE_X, COORDINATE_Y_1 * 2);
		descriptionPanelScroll.setOpaque(false);
		descriptionPanelScroll.getViewport().setOpaque(false);
		descriptionPanel.add(descriptionPanelScroll);
				
		buttonAddNew = new JButton("Add New");
		buttonAddNew.addActionListener(this);
		buttonAddNew.setBounds(COORDINATE_X + COORDINATE_X_2, COORDINATE_Y_1 + PANELHEIGHT + SPACE / 2, BUTTONWIDTH, BUTTONHEIGHT);
		mainPanel.add(buttonAddNew);

		buttonDelete = new JButton("Delete");
		buttonDelete.addActionListener(this);
		buttonDelete.setBounds(COORDINATE_X + COORDINATE_X_2 + SPACE, COORDINATE_Y_1 + PANELHEIGHT + SPACE / 2, BUTTONWIDTH, BUTTONHEIGHT);
		mainPanel.add(buttonDelete);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mainMenu = new JMenu("MainMenu");
		menuBar.add(mainMenu);
		
		menuSaveCodex = new JMenuItem("Save Codex");
		menuSaveCodex.addActionListener(this);
		mainMenu.add(menuSaveCodex);

		menuLoadCodex = new JMenuItem("Load Codex");
		menuLoadCodex.addActionListener(this);
		mainMenu.add(menuLoadCodex);
		
		this.addWindowListener(new WindowAdapter() { public void windowClosing(final WindowEvent e) {
			frameFather.setEnabled(true);
		} });
		
		this.fileDialog.addChoosableFileFilter(new FileNameExtensionFilter("Codex list(*.co)", DEFAULT_CODEX_EXTENSION));
		this.fileDialog.addChoosableFileFilter(new FileNameExtensionFilter("Army list(*.ar)", DEFAULT_LIST_EXTENSION));
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		final Object source = e.getSource();
		if (source.equals(menuLoadCodex)) {
			if (this.fileDialog.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.observer.loadCodexCmd(this.fileDialog.getSelectedFile().getPath());
				this.currentUnitList.setListData(this.observer.getModel().getCodexlist().toArray());			
			}
		} else if (source.equals(menuSaveCodex)) {
			if (this.fileDialog.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
				this.observer.saveCodexCmd(this.fileDialog.getSelectedFile().getPath() + "." + DEFAULT_CODEX_EXTENSION);
			}
		} else if (source.equals(buttonAddNew)) {
			this.setEnabled(false);
			final UnitCreationController controller = new UnitCreationController(this.observer.getModel());
			final IUnitCreateGUI gui = new UnitCreateGUI(this);
			controller.setView(gui);
		} else if (source.equals(buttonDelete)) {
			try {
				this.observer.getModel().removeFromCodex(this.observer.getModel().getCodexlist().get(currentUnitList.getSelectedIndex()));
			} catch (Exception e1) {
				this.showErrorDialog(ERROR_UNIT);
			}
			this.currentUnitList.setListData(this.observer.getModel().getCodexlist().toArray());
		}
	}
	
	@Override
	public void updateDescription() {
		if (currentUnitList.getSelectedIndex() != -1) {
		    descriptionPane.setText(this.observer.getModel().getCodexlist().get(currentUnitList.getSelectedIndex()).getDescription());
		}
	}
	
	@Override
	public JList<Object> getCodexList() {
		return this.currentUnitList;
	}
	
	@Override
	public void updateView() {
		this.currentUnitList.setListData(this.observer.getModel().getCodexlist().toArray());
	}
	
	@Override
	public void attachObserver(final ICreateArmyCodexObserver newObserver) {
		this.observer = newObserver;
	}

	/**
	 * Interface for an Observer of CreateArmyCodexGUI.
	 * @author MatteoOrzes
	 *
	 */
	public interface ICreateArmyCodexObserver {
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
		 * Method invoked in order to get the model bounded to the controller. 
		 * @return IModel, the currently used Model
		 */
		IModel getModel();
		
		/**
	     * Sets the new parameter view as default.
	     * Source: A. Santi Acme Exam project
	     * @param newGui the new view
	     */
		void setView(ICreateArmyCodexGUI newGui);		
	}
}
