
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import javax.swing.JFrame;

public class Screen extends JFrame{
	
	boolean fse = false;
	int fsm = 0;
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	public Screen(String title, int width, int height)
	{
		setTitle(title);
		setSize(width,height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
	}
	private void setfullscreen()
	{
		switch (fsm)
		{
		case 0:
			System.out.println("No Fullscreen");
			setUndecorated(false);
			break;
		case 1: 
			setUndecorated(true);
			setExtendedState(JFrame.MAXIMIZED_BOTH);
			break;
		case 2:
			setUndecorated(true);
			device.setFullScreenWindow(this);
			break;
		
		}
	}
	public void setFullscreen(int fsnm)
	{
		fse=true;
		if(fsm <= 2){
			this.fsm = fsnm;
			setfullscreen();
		}
		else{
			System.err.println("Error "+ fsnm+"is not supported.");
		}
	}
	
}
