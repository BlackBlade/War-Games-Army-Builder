package ModifiedPanels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This modified panel will be used near a JList to create a sort of index/indicator for every stat of a Unit/Hero.
 * @author MatteoOrzes
 * 
 */

public class StatsPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final Dimension PANELDIMENSION = new Dimension(380, 20);
	// Declaration of all the fields used in StatsPanel.
	// These fields contain all the components used by the constructor of the class.

	/**
	 * Constructor for StatsPanel class. 
	 */
	public StatsPanel() {
		super();
		this.setSize(PANELDIMENSION);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel cost;
		JLabel name;
		JLabel movement;
		JLabel weaponSkill;
		JLabel ballisticSkill;
		JLabel strength;
		JLabel toughness;
		JLabel wounds;
		JLabel initiative;
		JLabel attacks;
		JLabel leadership;
		JLabel occurrences;
		
		occurrences = new JLabel(" N  ");
		occurrences.setForeground(Color.WHITE);
		name = new JLabel("Name               ");
		name.setForeground(Color.WHITE);
		movement = new JLabel("         M ");
		movement.setForeground(Color.WHITE);
		weaponSkill = new JLabel("WS");
		weaponSkill.setForeground(Color.WHITE);
		ballisticSkill = new JLabel("BS");
		ballisticSkill.setForeground(Color.WHITE);
		strength = new JLabel("  S");
		strength.setForeground(Color.WHITE);
		toughness = new JLabel("   T");
		toughness.setForeground(Color.WHITE);
		wounds = new JLabel("   W");
		wounds.setForeground(Color.WHITE);
		initiative = new JLabel("    I");
		initiative.setForeground(Color.WHITE);
		attacks = new JLabel("    A");
		attacks.setForeground(Color.WHITE);
		leadership = new JLabel("  LD     ");
		leadership.setForeground(Color.WHITE);
		cost = new JLabel("Cost");
		cost.setForeground(Color.WHITE);
		this.add(occurrences);
		this.add(name);
		this.add(movement);
		this.add(weaponSkill);
		this.add(ballisticSkill);
		this.add(strength);
		this.add(toughness);
		this.add(wounds);
		this.add(initiative);
		this.add(attacks);
		this.add(leadership);
		this.add(cost);
	}
}
