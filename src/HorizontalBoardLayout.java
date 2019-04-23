import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * A concrete strategy used to display mancala board horizontally
 * when plugged into the context (MancalaBoardView)
 *
 */
public class HorizontalBoardLayout implements BoardLayoutStrategy{

	JButton mancalaB = new JButton("b");	// JButton representing Mancala B
	JButton mancalaA = new JButton("a");	// JButton representing Mancala A
	
	public void organizePitsJLabel(ArrayList<JButton> pits, JLabel label)
	{
		// Set Mancala A JButton color
		mancalaA.setBackground(new Color(207, 185, 154));
		mancalaA.setOpaque(true);
		mancalaA.setBorderPainted(false);

		// Set Mancala B JButton color
		mancalaB.setBackground(new Color(207, 185, 154));
		mancalaB.setOpaque(true);
		mancalaB.setBorderPainted(false);

		label.setLayout(new FlowLayout());
		
		label.add(mancalaB);
		
		// JPanel to add pit JButtons onto
		JPanel centerPits = new JPanel();
		centerPits.setOpaque(false);
		centerPits.setLayout(new GridLayout(4, 6, 10, 10));

		// Add pit labels for B to JPanel 
		for(int i = 6; i > 0; i --) {
			
			JButton pitLabel = new JButton("B" + i);
			pitLabel.setFont(new Font("Arial", Font.PLAIN, 40));
			pitLabel.setBackground(new Color(102, 51, 0));
			pitLabel.setOpaque(true);
			pitLabel.setBorderPainted(false);
			centerPits.add(pitLabel);
		}
		
		// Add pit B JButtons to JPanel
		for (int i = pits.size() - 1; i >= 6; i--) {
			centerPits.add(pits.get(i));
		}
		
		// Add pit A JButtons to JPanel
		for (int i = 0; i < 6; i++) {
			centerPits.add(pits.get(i));
		}

		// Add pit labels for A to JPanel 
		for (int i = 1; i <= 6; i++) {
			JButton pitLabel = new JButton("A" + i);
			pitLabel.setFont(new Font("Arial", Font.PLAIN, 40));
			pitLabel.setBackground(new Color(102, 51, 0));
			pitLabel.setOpaque(true);
			pitLabel.setBorderPainted(false);
			centerPits.add(pitLabel);
		}

		label.add(centerPits);
		label.add(mancalaA);
	}
	
	public Icon getBoard() {
		
		Icon board = new Icon() {

			@Override
			public void paintIcon(Component c, Graphics g, int x, int y) {
				
				Graphics2D g2 = (Graphics2D) g;
				g2.setColor(new Color(102, 51, 0));

				Rectangle2D.Double rectangle = new Rectangle2D.Double(0, 0, getIconWidth(), getIconHeight());
				g2.fill(rectangle);
			}

			@Override
			public int getIconWidth() {
				return 800;
			}

			@Override
			public int getIconHeight() {
				return 400;
			}
		};

		return board;
	}

	@Override
	public void addStones(ArrayList<JButton> pits, int [] mancalaData) {
		// TODO, consider:
		for(int i = 0; i < mancalaData.length; i++) {
	
			if(i == 6) { //Mancala A
				
				Stone stones = new Stone(mancalaData[i]);
				stones.setIconHeight(100);
				stones.setIconWidth(50);
				mancalaA.setIcon(stones);
				
			}else if(i == 13) { // Mancala B
				
				Stone stones = new Stone(mancalaData[i]);
				stones.setIconHeight(100);
				stones.setIconWidth(50);
				mancalaB.setIcon(stones);
				
			} else {
				
				if(i > 6) {
					
					pits.get(i - 1).setIcon(new Stone(mancalaData[i]));
				} else {
					
					pits.get(i).setIcon(new Stone(mancalaData[i]));
				}
			}
		}
	}
}