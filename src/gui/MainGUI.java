package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import main.Main;
import fileParser.FileParser;

public class MainGUI {

	private JFrame frmRip;
	private JTextField txtInputFile;
	JButton btnStartRouter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.frmRip.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainGUI() {
		initialize();
	}

	/**
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frmRip = new JFrame();
		frmRip.setResizable(false);
		frmRip.setTitle("RIP");
		frmRip.setBounds(100, 100, 615, 450);
		frmRip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRip.getContentPane().setLayout(null);

		JLabel lblConfigurationFile = new JLabel("Configuration File:");
		lblConfigurationFile.setBounds(10, 11, 102, 14);
		frmRip.getContentPane().add(lblConfigurationFile);

		txtInputFile = new JTextField();
		txtInputFile.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					FileParser fp = new FileParser(txtInputFile.getText());
					Main.StartRoutingDaemon(fp);
					btnStartRouter.setEnabled(false);
					txtInputFile.setEnabled(false);
				}
			}
		});
		txtInputFile.setBounds(122, 8, 125, 20);
		frmRip.getContentPane().add(txtInputFile);
		txtInputFile.setColumns(10);

		btnStartRouter = new JButton("Start Router");
		btnStartRouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FileParser fp = new FileParser(txtInputFile.getText());
				Main.StartRoutingDaemon(fp);
				btnStartRouter.setEnabled(false);
				txtInputFile.setEnabled(false);
			}
		});
		btnStartRouter.setBounds(369, 391, 118, 23);
		frmRip.getContentPane().add(btnStartRouter);

		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(497, 391, 102, 23);
		frmRip.getContentPane().add(btnExit);

		JTextArea txtOutput = new JTextArea();
		JScrollPane scroll = new JScrollPane(txtOutput);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		txtOutput.setEditable(false);
		PrintStream out = new PrintStream(new TextAreaOutputStream(txtOutput));
		System.setOut(out);
		System.setErr(out);
		scroll.setBounds(10, 52, 589, 328);
		frmRip.getContentPane().add(scroll);

		JLabel lblNewLabel = new JLabel("Output");
		lblNewLabel.setBounds(10, 36, 46, 14);
		frmRip.getContentPane().add(lblNewLabel);
	}
}
