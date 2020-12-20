package userInterface;

import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public abstract class commonWindow {
	public JFrame frmMain;
	public JPanel pnlRight;
	public JPanel pnlRightFix;

	/**
	 * Create the application.
	 */
	public commonWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
//		frmMain.setVisible(true);
//		frmMain.setResizable(false);
		frmMain.getContentPane().setBackground(new Color(0x161616));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 367, 487, 0 };
		gridBagLayout.rowHeights = new int[] { 311, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		frmMain.getContentPane().setLayout(gridBagLayout);
//		frmMain.setPreferredSize(new Dimension(600, 600));

		pnlRight = new JPanel();
		pnlRight.setBackground(new Color(0x161616));
		GridBagConstraints gbc_pnlRight = new GridBagConstraints();
		gbc_pnlRight.fill = GridBagConstraints.BOTH;
		gbc_pnlRight.gridx = 1;
		gbc_pnlRight.gridy = 0;
		frmMain.getContentPane().add(pnlRight, gbc_pnlRight);
		pnlRight.setLayout(new GridLayout(3, 1, 0, 25));

		pnlRightFix = new JPanel();
		pnlRightFix.setBackground(new Color(0x161616));
		pnlRight.add(pnlRightFix);

		JPanel pnlLeft = new JPanel();
		pnlLeft.setBackground(new Color(0x7F5AF0));
		GridBagConstraints gbc_pnlLeft = new GridBagConstraints();
		gbc_pnlLeft.fill = GridBagConstraints.BOTH;
		gbc_pnlLeft.insets = new Insets(0, 0, 0, 5);
		gbc_pnlLeft.gridx = 0;
		gbc_pnlLeft.gridy = 0;
		frmMain.getContentPane().add(pnlLeft, gbc_pnlLeft);
		pnlLeft.setLayout(new GridLayout(0, 1, 0, 25));

		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(0x7F5AF0));
		pnlLeft.add(pnlMenu);
		GridBagLayout gbl_pnlMenu = new GridBagLayout();
		gbl_pnlMenu.columnWidths = new int[] { 299, 0 };
		gbl_pnlMenu.rowHeights = new int[] { 22, 0 };
		gbl_pnlMenu.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlMenu.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		pnlMenu.setLayout(gbl_pnlMenu);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setMinimumSize(new Dimension(0, 30));
		menuBar.setMaximumSize(new Dimension(0, 4));
		menuBar.setFont(new Font("Lato", Font.PLAIN, 14));
		menuBar.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		GridBagConstraints gbc_menuBar = new GridBagConstraints();
		gbc_menuBar.insets = new Insets(0, 5, 0, 0);
		gbc_menuBar.anchor = GridBagConstraints.NORTH;
		gbc_menuBar.fill = GridBagConstraints.HORIZONTAL;
		gbc_menuBar.gridx = 0;
		gbc_menuBar.gridy = 0;
		pnlMenu.add(menuBar, gbc_menuBar);
		menuBar.setBorder(null);
		menuBar.setBackground(new Color(0x7F5AF0));
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x7F5AF0));
		panel.setPreferredSize(new Dimension(200, 10));
		menuBar.add(panel);

		JMenuItem menuHelp = new JMenuItem("Help"); //t
		menuHelp.setFont(new Font("Lato", Font.PLAIN, 14));
		menuHelp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuHelp.setBorder(null);
		menuHelp.setBackground(new Color(0x7F5AF0));
		menuBar.add(menuHelp);
		menuHelp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				MENU BAR HELP EVENT	
			}
		});

		JMenuItem menuAbout = new JMenuItem("About"); //t
		menuAbout.setFont(new Font("Lato", Font.PLAIN, 14));
		menuAbout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuAbout.setBorder(null);
		menuAbout.setBackground(new Color(0x7F5AF0));
		menuBar.add(menuAbout);
		menuAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				MENU BAR ABOUT EVENT
			}
		});

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBackground(new Color(0x7F5AF0));
		pnlLeft.add(pnlTitle);
		GridBagLayout gbl_pnlTitle = new GridBagLayout();
		gbl_pnlTitle.columnWidths = new int[] { 294, 0 };
		gbl_pnlTitle.rowHeights = new int[] { 29, 29, 0 };
		gbl_pnlTitle.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_pnlTitle.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		pnlTitle.setLayout(gbl_pnlTitle);

		JLabel lblGroup = new JLabel("B-6"); //t
		lblGroup.setForeground(Color.WHITE);
		lblGroup.setFont(new Font("Lato", Font.BOLD, 24));
		GridBagConstraints gbc_lblGroup = new GridBagConstraints();
		gbc_lblGroup.fill = GridBagConstraints.BOTH;
		gbc_lblGroup.insets = new Insets(0, 10, 0, 0);
		gbc_lblGroup.gridx = 0;
		gbc_lblGroup.gridy = 0;
		pnlTitle.add(lblGroup, gbc_lblGroup);

		JLabel lblTitle = new JLabel("Traffic Light Monitor"); //t
		lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblTitle.setForeground(new Color(0x161616));
		lblTitle.setFont(new Font("Lato", Font.BOLD, 24));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 10, 0, 0);
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 1;
		pnlTitle.add(lblTitle, gbc_lblTitle);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_4.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_4.setBackground(new Color(0x7F5AF0));
		pnlLeft.add(panel_4);

		JLabel lblLogo = new JLabel(new ImageIcon(commonWindow.class.getResource("/assets/uniLogo.png")));
		panel_4.add(lblLogo);

		frmMain.setSize(600, 311);
	}

}
