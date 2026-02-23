/*
 * Note for Source Code viewers:
 * 
 * I made this frame with WindowBuilder, an addon for
 * the Eclipse IDE that allows you to build Java GUI
 * in a WYSIWYG style.
 * 
 * I would've done the JFrame half of it myself but
 * since this is a basic testing app, it feels rather
 * overkill.
 * 
 * - John Fiore, 02/23/26
 * */

package johnfiore.laftest;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	public Map<String, String> themes = Map.of(
		    "Metal", "javax.swing.plaf.metal.MetalLookAndFeel",
		    "Nimbus", "javax.swing.plaf.nimbus.NimbusLookAndFeel",
		    "Motif", "com.sun.java.swing.plaf.motif.MotifLookAndFeel",
		    "Windows", "com.sun.java.swing.plaf.windows.WindowsLookAndFeel",
		    "Windows Classic", "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"
	);
	
	public static JLabel currentThemeTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					currentThemeTxt.setText("Current Theme: " + UIManager.getLookAndFeel().getName());
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel("Java: Look-and-Feel Test");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Calibri", Font.BOLD, 18));
		title.setBounds(10, 68, 240, 23);
		contentPane.add(title);
		
		currentThemeTxt = new JLabel("<null>");
		currentThemeTxt.setHorizontalAlignment(SwingConstants.CENTER);
		currentThemeTxt.setFont(new Font("Calibri", Font.PLAIN, 14));
		currentThemeTxt.setBounds(10, 92, 240, 23);
		contentPane.add(currentThemeTxt);
		
		JButton randomizeBtn = new JButton("Randomize Theme");
		randomizeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				randomizeTheme();
			}
		});
		randomizeBtn.setBounds(33, 116, 195, 23);
		contentPane.add(randomizeBtn);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 101, 22);
		contentPane.add(menuBar);
		
		JMenu fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		JMenuItem file_new = new JMenuItem("New");
		file_new.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
		fileMenu.add(file_new);
		
		JMenuItem file_exit = new JMenuItem("Exit");
		file_exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		fileMenu.add(file_exit);
	}
	
	public void randomizeTheme()
	{
		List<String> keys = new ArrayList<>(themes.keySet());
	    
	    String themeName = keys.get(randomNumber(0, keys.size()));
	    String lafClass = themes.get(themeName);
		
		try {
			UIManager.setLookAndFeel(lafClass);
			currentThemeTxt.setText("Current Theme: " + themeName);
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		}
	}
	
	public int randomNumber(int min, int max)
	{
		return (int) ((Math.random() * (max - min)) + min);
	}
}