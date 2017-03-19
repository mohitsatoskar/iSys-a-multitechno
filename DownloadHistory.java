import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.StringTokenizer;

public class DownloadHistory extends JFrame implements ActionListener
{
	public static void write()
	{
		try
		{
		/*
			File file =new File("History.txt");
		
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter br = new BufferedWriter(fw);
			
			br.write("Hello\n");
			br.flush();
			br.close();
			*/
			
		
			
		}
		catch(Exception e)
		{
			System.out.println (e.getMessage());
		}
			
		
	
	}
	
	public void read()
	{
		StringTokenizer st;	
	 File file = new File("History.txt");
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    DataInputStream dis = null;

    try {
    	int i=0,j=0;
      fis = new FileInputStream(file);

      // Here BufferedInputStream is added for fast reading.
      bis = new BufferedInputStream(fis);
      dis = new DataInputStream(bis);

      // dis.available() returns 0 if the file does not have more lines.
      while (dis.available() != 0) 
      {
		
      	// this statement reads the line from the file and print it to
        // the console.
        st = new StringTokenizer(dis.readLine(),"#");
        table.setValueAt(st.nextToken(),i,0);
        table.setValueAt(st.nextToken(),i,1);
        //System.out.println(dis.readLine());
        i++;
        
        
        
        
      }

      // dispose all the resources after using them.
      fis.close();
      bis.close();
      dis.close();

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
		
	}
	
	JTable table;
	JScrollPane scrollpane;
	JLabel lblHistory;
	JButton btnok;
	//NewBrowser nw;
	DownloadManager nw;
	public DownloadHistory()
	{
		String [] colheaders = {"Date " , "URL"};
		String data [][] = new String[20][2];
		table = new JTable(data,colheaders);
		scrollpane = new JScrollPane(table);
		
		setLayout(new BorderLayout());
		lblHistory = new JLabel(" Downlaod History  ");
		btnok = new JButton("Ok");
		
		
		add(lblHistory , BorderLayout.NORTH);
		add(scrollpane,BorderLayout.CENTER);
		add(btnok,BorderLayout.SOUTH);
		setVisible(true);
		setSize(450,400);
		setSize(450,450);
		
		read();
		
		btnok.addActionListener(this);
		
		
	}
	
	public DownloadHistory(DownloadManager nw)
	{
		this();
		this.nw = nw;
		
			
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String s = table.getValueAt(table.getSelectedRow(),1).toString();
		System.out.println (s);
		//nw.loadURL(s);
		nw.addTextField.setText(s);
		nw.actionAdd();
		setVisible(false);
	}
	
	public static void main(String args[])
	{
		new DownloadHistory();
    
	}
}
		