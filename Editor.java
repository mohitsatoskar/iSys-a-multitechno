import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Editor extends JFrame
{
	JTextArea textarea;
	JScrollPane scroll;
	public Editor(String s)
	{
		textarea = new JTextArea();
		textarea.setText(s);
		scroll = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setLayout(new BorderLayout());
		add(scroll,BorderLayout.CENTER);
		setVisible(true);
		setTitle("editor");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
			
		
	}
	
	public static void main(String args[])
	{
		Editor e = new Editor("dsdf");;
	}
}
		