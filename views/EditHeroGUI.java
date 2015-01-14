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
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import models.IModel;
import ModifiedPanels.ImagePanel;
import exceptions.NullFieldException;

/**
 * Gui used for the edit of an hero, contains only a label a checkbox and three button.
 * The CheckBox is used to state if the Hero is a Bsb or not.
 * The Button AddMount allows the user to open the MountCreateGUI in order to create a new mount for the hero.
 * @author MatteoOrzes
 *
 */

public class EditHeroGUI extends AbstractBasicGUI implements ActionListener, IEditHeroGUI {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Dimension FRAMEDIM = new Dimension(200, 130);
	private static final Dimension MAINPANELDIM = new Dimension(194, 102);
	private static final Dimension RIGIDAREADIMENSION = new Dimension(10, 10);
	private static final String FILENAME = "dark-elves-warhammer-wallpaper.jpg";
	private static final String ERROR_UNIT_NOT_FOUND = "Error: unit not found.";
	private final  ArmyBuildGUI frameFather;
	private final int index;
	private final ImagePanel mainPanel;
	private final JPanel checkPanel;
	private final JCheckBox isBsb;
	private final JLabel bsbLabel;
	private final JButton confirmButton;
	private IEditHeroObserver observer;
	/**
	 * Constructor for the Class EditHeroGUI.
	 * @param frame the frame from which this GUi has been created.
	 * @param newIndex the index of the Hero the user is editing.
	 */
	public EditHeroGUI(final ArmyBuildGUI frame, final int newIndex) {
		super();

		this.index = newIndex;
		this.frameFather = frame;
		this.setTitle("EditHero");
		this.setResizable(false);
		this.setSize(FRAMEDIM);
		this.setLocationRelativeTo(frameFather);

		this.getContentPane().setLayout(null);

		mainPanel = new ImagePanel(FILENAME, MAINPANELDIM);
		mainPanel.setLocation(0, 0);
		mainPanel.setSize(MAINPANELDIM);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.getContentPane().add(mainPanel);
		mainPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));

		checkPanel = new JPanel();
		checkPanel.setOpaque(false);
		checkPanel.setLayout(new BoxLayout(checkPanel, BoxLayout.X_AXIS));
		checkPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));

		bsbLabel = new JLabel("CHECK IF BSB");
		bsbLabel.setForeground(Color.WHITE);
		bsbLabel.setOpaque(false);
		checkPanel.add(bsbLabel);
		checkPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));

		isBsb = new JCheckBox();
		isBsb.setOpaque(false);
		checkPanel.add(isBsb);
		checkPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));

		mainPanel.add(checkPanel);
		mainPanel.add(Box.createRigidArea(RIGIDAREADIMENSION));
		
		confirmButton = new JButton("Confirm");
		confirmButton.addActionListener(this);
		confirmButton.setHorizontalAlignment(SwingConstants.LEFT);
		mainPanel.add(confirmButton);

		this.addWindowListener(new WindowAdapter() { public void windowClosing(final WindowEvent e) {
			frameFather.setEnabled(true);
		} });

		this.setVisible(true);
	}
	
	@Override
	public void updateCheckBox() {
		isBsb.setSelected(this.observer.isBsb());
	}

	@Override
	public void attachObserver(final IEditHeroObserver newObserver) {
		this.observer = newObserver;		
	}

	@Override
	public int getIndex() {
		return this.index;
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		final Object source = e.getSource();
		if (source.equals(confirmButton)) {
			if (isBsb.isSelected() != this.observer.isBsb()) {
				try {
					this.observer.setBsb();
				} catch (NullFieldException e1) {
					this.showErrorDialog(ERROR_UNIT_NOT_FOUND);
				}
			}
			this.frameFather.setEnabled(true);
			frameFather.getCurrentArmyList().setListData(this.observer.getModel().getArmyList().toArray());
			frameFather.updateTotalCost();
			this.dispose();
		}
	}
	
	
	/**
	 * Interface for an Observer of class EditHeroGUI.
	 * @author MatteoOrzes
	 *
	 */
	public interface IEditHeroObserver {
		
	    /**
	     * Sets the new parameter view as default.
	     * Source: A. Santi Acme Exam project
	     * @param newGui the new view.
	     */
		void setView(IEditHeroGUI newGui);
		
		/**
		 * Method invoked in order to get the model bounded to the controller. 
		 * @return IModel, the currently used Model.
		 */
		IModel getModel();
		
		/**
		 * Method invoked in order to know if the selected Hero is Bsb.
		 * @return true if the Hero is Bsb, else false
		 */
		boolean isBsb();
		
		/**
		 * Method that is used when the confirmButton get pressed.
		 * It sets the corresponding field for Bsb of the Hero.
		 * @throws NullFieldException 
		 */
		void setBsb() throws NullFieldException;		
	}
}
