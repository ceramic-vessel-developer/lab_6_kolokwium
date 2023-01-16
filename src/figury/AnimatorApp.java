package figury;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AnimatorApp extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					final AnimatorApp frame = new AnimatorApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public AnimatorApp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int ww = 1000, wh = 800;
		setBounds((screen.width-ww)/2, (screen.height-wh)/2, ww, wh);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		AnimPanel kanwa = new AnimPanel();
		kanwa.setBounds(10, 11, 922, 660);
		kanwa.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int[] corner_1 =kanwa.getCorner_temp_1();
				if(corner_1 == null){
					kanwa.setCorner_temp_1(new int[] {e.getX(),e.getY()});
				}else{
					kanwa.setCorner_temp_2(new int[]{e.getX(),e.getY()});
					kanwa.addFigure();
					kanwa.setCorner_temp_1(null);
					kanwa.setCorner_temp_2(null);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {

			}

			@Override
			public void mouseReleased(MouseEvent e) {

			}

			@Override
			public void mouseEntered(MouseEvent e) {

			}

			@Override
			public void mouseExited(MouseEvent e) {

			}
		});
		contentPane.add(kanwa);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				kanwa.initialize();
			}
		});


		JButton btnAnimate = new JButton("Animate");
		btnAnimate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kanwa.animate();
			}
		});
		btnAnimate.setBounds(10, 700, 90, 23);
		contentPane.add(btnAnimate);

		JButton btnUp = new JButton("Up");
		btnUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kanwa.set_gravity(Gravity.UP);
			}
		});
		btnUp.setBounds(200, 700, 80, 23);
		contentPane.add(btnUp);

		JButton btnDown = new JButton("Down");
		btnDown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kanwa.set_gravity(Gravity.DOWN);
			}
		});
		btnDown.setBounds(290, 700, 80, 23);
		contentPane.add(btnDown);

		JButton btnRight = new JButton("Right");
		btnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kanwa.set_gravity(Gravity.RIGHT);
			}
		});
		btnRight.setBounds(380, 700, 80, 23);
		contentPane.add(btnRight);

		JButton btnLeft = new JButton("Left");
		btnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				kanwa.set_gravity(Gravity.LEFT);
			}
		});
		btnLeft.setBounds(470, 700, 80, 23);
		contentPane.add(btnLeft);

		JLabel label = new JLabel("Select gravity:");
		label.setLabelFor(btnUp);
		label.setBounds(110,700,100,23);
		contentPane.add(label);


	}

}
