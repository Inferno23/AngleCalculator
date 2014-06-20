package calculator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

public class OutputView {

	private JFrame frame;
	
	private JLabel contentPane;
	private JLabel minValue;
	private JLabel maxValue;
	private JButton back;

	/**
	 * Create the frame.
	 */
	public OutputView(int x, int y, final JFrame frame) {
		this.frame = frame;
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setBounds(100, 100, 160, 175);
		contentPane = new JLabel(new ImageIcon("src/MevionLogo-color.jpg"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.frame.setContentPane(contentPane);
				
		JLabel lblRangeMin = new JLabel("Range min");
		
		JLabel lblRangeMax = new JLabel("Range max");
		
		minValue = new JLabel();
		
		maxValue = new JLabel();
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new InputView(frame);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblRangeMin)
							.addGap(18)
							.addComponent(minValue))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblRangeMax)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(maxValue))
						.addComponent(back))
					.addContainerGap(328, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRangeMin)
						.addComponent(minValue))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRangeMax)
						.addComponent(maxValue))
					.addGap(18)
					.addComponent(back)
					.addContainerGap(192, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
		
		calculate(x, y);
	}
	
	private void calculate(int x, int y) {
		// Do the calculation

		double min, max;
	 	double iso = 188.288;
	 	double offset = 0.922;
	 	 
	 	double distanceY = 149.91 - y - offset;
	 	double distance = Math.sqrt((x * x) + (distanceY * distanceY));
	 	double xIntersect = -(distance*distance + iso*iso - 2500)/(2*iso);
	 	double range = 180 * Math.acos(-xIntersect/distance)/Math.PI;
	 	double center = 270 + 180 * Math.atan(x/distanceY)/Math.PI;
	 	 
	 	min = center - range;
	 	max = center + range;
	 	
	 	String minResult = String.format("%.2f",  min);
	 	String maxResult = String.format("%.2f",  max);
	 	 
		minValue.setText(minResult);
		maxValue.setText(maxResult);
	}
}
