package src;
import javax.swing.JFrame;

public class Screen extends JFrame{
	
	
	public Screen(String title, int width, int height)
	{
		setTitle(title);
		setSize(width,height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	
}
