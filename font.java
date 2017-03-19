import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.print.*;
import java.util.Date;
import java.util.*;
import java.sql.*;
import java.awt.List;
import java.awt.image.*;
import javax.imageio.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

class font extends Frame implements ActionListener,ItemListener
{
	Label lf,lfs,lsize,lsample1,lsample2;
	TextField tf,tfs,tsize;
	List f,fs,size;
	JButton ok,cancel;
	String a,b;
	int c;
	Connection cn;
	Statement st;
	
	public font()
	{
		super("Font");
		setLayout(null);
		lf=new Label("Font");
		lf.setBounds(10,40,100,20);
		add(lf);
		
		lfs=new Label("Font Style");
		lfs.setBounds(160,40,100,20);
		add(lfs);
		
		lsize=new Label("Size");
		lsize.setBounds(260,40,100,20);
		add(lsize);
		
		tf=new TextField();
		tf.setBounds(10,60,130,20);
		add(tf);
		
		tfs=new TextField();
		tfs.setBounds(160,60,80,20);
		add(tfs);
		
		tsize=new TextField();
		tsize.setBounds(260,60,50,20);
		add(tsize);
		
		f=new List();
		f.add("Dialog");
		f.add("SansSerif");
		f.add("Serif");
		f.add("Monospaced");
		f.add("Helvetica");
		f.add("TimesRoman");
		f.add("Courier");
		f.add("DialogInput");
		f.add("ZapfDingbats");
		f.add("Symbol");
		f.setBounds(10,80,130,100);
		add(f);
		f.addItemListener(this);
		f.select(0);
		
		fs=new List();
		fs.add("PLAIN");
		fs.add("BOLD");
		fs.add("ITALIC");
				
		fs.setBounds(160,80,80,100);
		add(fs);
		fs.addItemListener(this);
		fs.select(0);
		
		size=new List();
		size.add("8");
		size.add("9");
		size.add("10");
		size.add("11");
		size.add("12");
		size.add("14");
		size.add("16");
		size.add("18");
		size.add("20");
		size.add("22");
		size.add("24");
		size.add("26");
		size.add("28");
		size.add("36");
		size.add("48");
		size.select(0);
		
		size.setBounds(260,80,50,100);
		add(size);
		size.addItemListener(this);
		
		ok=new JButton("OK");
		ok.setBounds(320,60,80,20);
		add(ok);
		ok.addActionListener(this);
		
		cancel=new JButton("CANCEL");
		cancel.setBounds(320,90,80,20);
		add(cancel);
		cancel.addActionListener(this);
		
		lsample1=new Label("Sample");
		lsample1.setBounds(180,220,100,20);
		add(lsample1);
		lsample1.setFont(new Font("Dialog",Font.BOLD,16));
		
		
		lsample2=new Label("AaBbYyZz");
		lsample2.setBounds(130,250,300,60);
		add(lsample2);
				
		setSize(440,400);
		setVisible(true);
	}
	
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource()==f || ie.getSource()==fs || ie.getSource()==size )
		{
			tf.setText(f.getSelectedItem());
			tfs.setText(fs.getSelectedItem());
			tsize.setText(size.getSelectedItem());
			if(tfs.getText().equals("PLAIN"))
			       lsample2.setFont(new Font(tf.getText(),Font.PLAIN,Integer.parseInt(tsize.getText())));
			if(tfs.getText().equals("BOLD"))
			       lsample2.setFont(new Font(tf.getText(),Font.BOLD,Integer.parseInt(tsize.getText())));
			if(tfs.getText().equals("ITALIC"))
			       lsample2.setFont(new Font(tf.getText(),Font.ITALIC,Integer.parseInt(tsize.getText())));
		}
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==ok)
		{
			a=tf.getText();
			b=tfs.getText();
			c=Integer.parseInt(tsize.getText());
			try
		    {
		        Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			    cn  = DriverManager.getConnection("jdbc:odbc:Neweditor");
			    st = cn.createStatement();
			    st.executeUpdate("Delete * from FontTable");
			    st.executeUpdate("Insert Into FontTable values('"+a+"','"+b+"','"+Integer.toString(c)+"')");
            }  
			catch(SQLException ee){}
			catch(Exception e){}
		
			if(tfs.getText().equals("PLAIN"))
			       Neweditor.ta1.setFont(new Font(tf.getText(),Font.PLAIN,Integer.parseInt(tsize.getText())));
			if(tfs.getText().equals("BOLD"))
			       Neweditor.ta1.setFont(new Font(tf.getText(),Font.BOLD,Integer.parseInt(tsize.getText())));
			if(tfs.getText().equals("ITALIC"))
			       Neweditor.ta1.setFont(new Font(tf.getText(),Font.ITALIC,Integer.parseInt(tsize.getText())));
		
			this.hide();
		}
		
		if(ae.getSource()==cancel)
		{
			this.hide();
		}
	}
		
}
	