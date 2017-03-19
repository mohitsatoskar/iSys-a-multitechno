import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

class OpenDialog extends JDialog implements ActionListener
{
		JButton btnok,btncancel;
		JLabel lblimage,lblmessage,lblmessage1;
		ImageIcon icon;
		JComboBox jcmb;
		public static String url;
		public OpenDialog()
		{
			setSize(250,250);
			icon = new ImageIcon(getClass().getResource("images/Internet.jpg"));
			lblimage = new JLabel(icon);
			lblmessage = new JLabel("Type the Internet Address of a document or folder");
			lblmessage1 = new JLabel("and Web Browser will Open you");
			
			btnok = new JButton("Open");
			btncancel = new JButton("Cancel");
			jcmb = new JComboBox();
			jcmb.addItem("ram");
			jcmb.addItem("shyam");
			jcmb.setEditable(true);
			
			setLayout(null);
			
			
		
			lblimage.setBounds(20,20,200,200);
			lblmessage.setBounds(250,40,300,30);
			lblmessage1.setBounds(250,80,300,30);
			jcmb.setBounds(250,120,400,30);
			btnok.setBounds(300,160,80,30);
			btncancel.setBounds(420,160,80,30);
			
			add(lblimage);
			add(lblmessage);
			add(lblmessage1);
			add(jcmb);
		
			add(btnok);
			add(btncancel);
			
			btnok.addActionListener(this);
			btncancel.addActionListener(this);
			setTitle("Open");
			setVisible(true);
			setLocation(200,200);
			
			setSize(670,300);
			
		}
		
		
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == btnok)
			{
				this.url = jcmb.getSelectedItem().toString();
				
				System.out.println ("url = " + url);
				this.dispose();
			}
			if(e.getSource()==btncancel)
			{
				this.url = null;
				this.dispose();
			}
		}
			
		
		public static void main(String args[])
		{
			OpenDialog o = new OpenDialog();
		}
		
		
}