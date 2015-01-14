package views;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import exceptions.DuplicatedUnitException;
import exceptions.NullFieldException;
import exceptions.StatsFaultException;
/** 
 * This class allows the user to create a new mount that will be assigned to a unit or a hero.
 * It's very similar to UnitCreateGUI but the GdcCost Panel is substituted with a checkBox that states if 
 * the mount is or not flying.
 * @author MatteoOrzes
 */
public class MountCreateGUI extends AbstractUnitCreateGUI implements IMountCreateGUI, ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IMountCreateObserver  observer;
	private ArmyBuildGUI frameFather;
	private final int index;
	private JPanel mountPanel;
	private JCheckBox flyingCheckBox;
	private final JLabel flyingLabel;
	private final JButton buttonConfirmMount;
	private static final String ERROR_FIELD = "Error: a field can't be empty.";
	
	/**
	 * Constructor for the class MountCreateGUI.
	 * @param frame the frame from which this one has been generated.
	 * @param newIndex the index of the last selected unit/hero.
	 */
	public MountCreateGUI(final ArmyBuildGUI frame, final int newIndex) {
		super();
		this.frameFather = frame;
		this.setLocationRelativeTo(frameFather);
		this.index = newIndex;
		this.setTitle("Mount Creation");
		
		mountPanel = new JPanel();
		mountPanel.setBorder(new TitledBorder(UIManager
				.getBorder("TitledBorder.border"), "IS YOUR MOUNT FLYING ??",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.WHITE));
		mountPanel.setBounds(225, 295, 200, 80);
		mountPanel.setOpaque(false);
		mountPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		flyingLabel = new JLabel("is your mount flying?");
		flyingLabel.setForeground(Color.WHITE);
		flyingLabel.setOpaque(false);
		mountPanel.add(flyingLabel);
		
		flyingCheckBox = new JCheckBox();
		mountPanel.add(flyingCheckBox);
		
		mainPanel.add(mountPanel);
		
		clearButton.addActionListener(this);
		buttonConfirmMount = new JButton("CONFIRM MOUNT");
		buttonConfirmMount.addActionListener(this);
		buttonConfirmMount.setBounds(345, 400, 145, 25);
		mainPanel.add(buttonConfirmMount);
		
		this.addWindowListener(new WindowAdapter() { public void windowClosing(final WindowEvent e) {
			frameFather.setEnabled(true);
		} });

		this.setVisible(true);
	}

	@Override
	public int getIndex() {
		return this.index;
	}
	
	@Override
	public void attachObserver(final IMountCreateObserver newObserver) {
		this.observer = newObserver;
	}

	@Override
	public boolean isFlying() {
		return this.flyingCheckBox.isSelected();
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		final Object source = e.getSource();
		if (source.equals(buttonConfirmMount)) {
			try {
				this.observer.createMount(index);
			} catch (DuplicatedUnitException | StatsFaultException
					| NullFieldException | NumberFormatException e1) {
				this.showErrorDialog(ERROR_FIELD);
			}
			this.frameFather.setEnabled(true);
			this.frameFather.updateTotalCost();
			this.dispose();
		}  else if (source.equals(clearButton)) {
		   this.clearCommand();
		}
	}

	/**
	 * Interface for an Observer for mountCreateGUI.
	 * @author MatteoOrzes
	 *
	 */
	public interface IMountCreateObserver {
		
		/**
	     * Sets the new parameter view as default.
	     * Source: A. Santi Acme Exam project
	     * @param newGui the new view
	     */
		void setView(final IMountCreateGUI newGui);
		/**
		 * Method that creates a new mount and than assigns it to the last selected unit/hero.
		 * @param index the index of the unit/hero that will get the mount
		 * @throws DuplicatedUnitException 
		 * @throws StatsFaultException 
		 * @throws NullFieldException 
		 */
		void createMount(final int index) throws DuplicatedUnitException, StatsFaultException, NullFieldException;
		
	}

}
