package views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import ModifiedPanels.ImagePanel;
import exceptions.NullFieldException;
/**
 * GUI created in order to handle the GDC("gruppo di comando") of the selected Unit.
 * 
 * @author MatteoOrzes
 */
public class GdcGUI extends AbstractBasicGUI implements ActionListener, IGdcGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension FRAMEDIMENSION = new Dimension(200, 140);
	private static final Dimension PANELDIMENSION = new Dimension(194, 112);
	private static final Dimension VOIDAREADIMENSION = new Dimension(10, 5);
	private static final Dimension VOIDAREADIMENSION2ND = new Dimension(10, 8);
	private static final String FILENAME = "dark-elves-warhammer-wallpaper.jpg";
	private static final String ERROR_FIELD = "Error: a field can't be null.";
	private GdcObserver observer;
	//Declaration of all the fields of GdcGUI. this
	//These fields contain all the components used in the GUI's constructor.
	private final int index;
	private final ArmyBuildGUI frameFather;
	private final ImagePanel editPanel;
	private final JPanel gdcPanel;
	private final JPanel labelsGDCPanel;
	private final JPanel checkGDCPanel;
	private final JLabel standardLabel;
	private final JLabel musicianLabel;
	private final JLabel championLabel;
	private final JCheckBox checkStandard;
	private final JCheckBox checkMusician;
	private final JCheckBox checkChampion;
	private final JButton buttonOK;

	/**
	 * Constructor for the class GdcGUI.
	 * @param frame the frame from which this frame has been generated.
	 * @param newIndex the index of the last clicked element before pressing EditGdc button.
	 */
	public GdcGUI(final ArmyBuildGUI frame, final int newIndex) {
		super();
		this.index = newIndex;
		this.frameFather = frame;
		setResizable(false);
		this.setTitle("UnitEditGUI");
		this.setSize(FRAMEDIMENSION);
		this.getContentPane().setLayout(null);
		
		editPanel = new ImagePanel(FILENAME, PANELDIMENSION);
		this.getContentPane().add(editPanel);
		editPanel.setLocation(0, 0);
		editPanel.setSize(PANELDIMENSION);
		this.setLocationRelativeTo(frameFather);
		editPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			
		gdcPanel = new JPanel();
		gdcPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "Options for GDC",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		gdcPanel.setLayout(new BoxLayout(gdcPanel, BoxLayout.X_AXIS));
		gdcPanel.setOpaque(false);
		editPanel.add(gdcPanel);
		gdcPanel.add(Box.createRigidArea(VOIDAREADIMENSION));
		
		labelsGDCPanel = new JPanel();
		labelsGDCPanel.setOpaque(false);
		labelsGDCPanel.setLayout(new BoxLayout(labelsGDCPanel, BoxLayout.Y_AXIS));
		
		checkGDCPanel = new JPanel();
		checkGDCPanel.setOpaque(false);
		checkGDCPanel.setLayout(new BoxLayout(checkGDCPanel, BoxLayout.Y_AXIS));
		
		standardLabel = new JLabel("Standard");
		standardLabel.setForeground(Color.WHITE);
		labelsGDCPanel.add(standardLabel);
		labelsGDCPanel.add(Box.createRigidArea(VOIDAREADIMENSION2ND));
		musicianLabel = new JLabel("Musician");
		musicianLabel.setForeground(Color.WHITE);
		labelsGDCPanel.add(musicianLabel);
		labelsGDCPanel.add(Box.createRigidArea(VOIDAREADIMENSION2ND));
		championLabel = new JLabel("Champion");
		championLabel.setForeground(Color.WHITE);
		labelsGDCPanel.add(championLabel);
		
		checkStandard = new JCheckBox("");
		checkGDCPanel.add(checkStandard);
		checkMusician = new JCheckBox("");
		checkGDCPanel.add(checkMusician);
		checkChampion = new JCheckBox("");
		checkGDCPanel.add(checkChampion);
		
		gdcPanel.add(labelsGDCPanel);
		gdcPanel.add(Box.createRigidArea(VOIDAREADIMENSION));
		gdcPanel.add(checkGDCPanel);
		gdcPanel.add(Box.createRigidArea(VOIDAREADIMENSION));
		
		buttonOK = new JButton("OK");
		buttonOK.addActionListener(this);
		editPanel.add(buttonOK);
		
		this.addWindowListener(new WindowAdapter() { public void windowClosing(final WindowEvent e) {
			frameFather.setEnabled(true);
		} });
		
		this.setVisible(true);
	}

	@Override
	public void updateCheckBox() {
		checkStandard.setSelected(this.observer.hasStandard());
		checkMusician.setSelected(this.observer.hasMusician());
		checkChampion.setSelected(this.observer.hasChampion());
	}
	
	@Override
	public void attachObserver(final GdcObserver newObserver) {
		this.observer = newObserver;
	}

	@Override
	public void setStandardCheckbox(final boolean b) {
		this.checkStandard.setSelected(b);
	}

	@Override
	public void setChampionCheckbox(final boolean b) {
		this.checkChampion.setSelected(b);
	}

	@Override
	public void setMusicianCheckbox(final boolean b) {
		this.checkMusician.setSelected(b);
	}

	@Override
	public int getIndex() {
		return this.index;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		final Object source = e.getSource();
		if (source.equals(buttonOK)) {
			if (checkStandard.isSelected() != this.observer.hasStandard()) {
				try {
					this.observer.setStandard();
				} catch (NullFieldException e1) {
					this.showErrorDialog(ERROR_FIELD);
				}
			}
			if (checkMusician.isSelected() != this.observer.hasMusician()) {
				try {
					this.observer.setMusician();
				} catch (NullFieldException e1) {
					this.showErrorDialog(ERROR_FIELD);
				}				
			}
			if (checkChampion.isSelected() != this.observer.hasChampion()) {
				try {
					this.observer.setChampion();
				} catch (NullFieldException e1) {
					this.showErrorDialog(ERROR_FIELD);
				}
			}
			this.frameFather.setEnabled(true);
			frameFather.updateTotalCost();
			this.dispose();
		}
	}

	/**
	 * This is an Interface for an Observer of a GdcGUI gui.
	 * @author MatteoOrzes
	 *
	 */
	public interface GdcObserver {

		/**
		 * Method invoked in order to know the actual situation of the unit's Standard. 
		 * @return boolean that states if the Unit already has a Standard.
		 */
		boolean hasStandard();
		/**
		 * Method invoked in order to know the actual situation of the unit's Champion. 
		 * @return boolean that states if the Unit already has a Champion.
		 */
		boolean hasChampion();
		/**
		 * Method invoked in order to know the actual situation of the unit's Musician.
		 * @return boolean that states if the Unit already has a Musician.
		 */
		boolean hasMusician();
		/**
		 * Method that is used when the buttonOk get pressed.
		 * It sets the corresponding field for Standard of the unit.
		 * @throws NullFieldException if you call this method on a non-existing unit
		 */
		void setStandard() throws NullFieldException;
		/**
		 * Method that is used when the buttonOk get pressed.
		 * It sets the corresponding field for Champion of the unit.
		 * @throws NullFieldException if you call this method on a non-existing unit
		 */
		void setChampion() throws NullFieldException;
		/**
		 * Method that is used when the buttonOK get pressed.
		 * It sets the corresponding field for Musician of the unit.
		 * @throws NullFieldException if you call this method on a non-existing unit
		 */
		void setMusician() throws NullFieldException;
		
	    /**
	     * Sets the new parameter view as default.
	     * Source: A. Santi Acme Exam project
	     * @param newGui the new view
	     */
		void setView(IGdcGUI newGui);
	}
}
