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


public class Neweditor extends Frame implements ActionListener
{  
    
	static Frame frm;
	JFrame mediaTest;
	//MediaPanel mediaPanel; 
	Menu file,edit,format,foreground,background,help,picture,music,webBrowser;

	MenuItem fnew,fopen,fsave,fsaveas,fsep1,fpagesetup,fprint,fsep2,fexit,eundo,esep1,ecut,ecopy,epaste,edelete,esep2,efindreplace,egoto,esep3,eselectall,etimedate,ftfont,fgBlack,fgBlue,fgCyan,fgDarkGray,fgGray,fgGreen,fgLightGray,fgMagenta,fgOrange,fgPink,fgRed,fgYellow,fgWhite,bgBlack,bgBlue,bgCyan,bgDarkGray,bgGray,bgGreen,bgLightGray,bgMagenta,bgOrange,bgPink,bgRed,bgYellow,bgWhite,popen,psave,psaveas,pnormal,pgrayscale,pinvert,pcontrast,pblur,psharpen,pexit,mopen,mexit,habout,wopen;
	static TextArea ta1;
	static JLabel l1;
	String flag = "n",filee = "",directory = "",ans,text="",find,replace;
    String a,b;
    int c;
    Connection cn;
    Statement st;
    ResultSet rs;
    
    PlugInFilter pif;
    Image fimg;
    ImageIcon img;
    BufferedImage bimg;
    protected static Vector vFile;   
    
    int f = -1;
	int gposdel = 0;	
	
	PrinterJob p = PrinterJob.getPrinterJob();
			
	Date d = new Date();
	GregorianCalendar g = new GregorianCalendar();
	String stime;
	int hour=0;
	
	public void date()
	{
		hour = g.get(Calendar.HOUR);
		if (hour == 0)
		{
			hour = 12;
		}
		
		g.setTime(d);
		
		if ( g.get(Calendar.AM_PM) == 0 )
		{
			stime = hour + ":" + g.get(Calendar.MINUTE) + " AM " + (g.get(Calendar.MONTH)+1) + "/" + g.get(Calendar.DATE) + "/" + g.get(Calendar.YEAR);
		}
		if ( g.get(Calendar.AM_PM) == 1 )
		{
			stime = hour + ":" + g.get(Calendar.MINUTE) + " PM " + (g.get(Calendar.MONTH)+1) + "/" + g.get(Calendar.DATE) + "/" + g.get(Calendar.YEAR);
		}
	}
	
	public Neweditor()
	{
		frm = new Frame("Text Neweditor");
		
		frm.setLayout(null);
		vFile=new Vector();
        
        date();	
	    ta1=new TextArea();
	    ta1.setFont(new Font("Times New Roman",Font.BOLD,20));
	    ta1.setBounds(1,50,1020,688);
	    frm.add(ta1);
				
		l1=new JLabel("");
		l1.setBounds(1,50,1020,688);
	    frm.add(l1);
	    l1.hide();
		
		
		MenuBar mb = new MenuBar();
		frm.setMenuBar(mb);
		
		file = new Menu("File");
   	fnew = new MenuItem("New");
		fopen = new MenuItem("Open...");
		fsave = new MenuItem("Save");
		fsaveas = new MenuItem("Save As...");
		fsep1 = new MenuItem("-");
		fpagesetup  = new MenuItem("Page Setup...");
		fprint = new MenuItem("Print...");
		fsep2 = new MenuItem("-");
		fexit = new MenuItem("Exit...");
		 		
		file.add(fnew);
		file.add(fopen);
		file.add(fsave);
		file.add(fsaveas);
		file.add(fsep1);
		file.add(fpagesetup);
		file.add(fprint);
		file.add(fsep2);
		file.add(fexit);
		
		fnew.addActionListener(this);
		fopen.addActionListener(this);
		fsave.addActionListener(this);
		fsaveas.addActionListener(this);
		fsep1.addActionListener(this);
		fpagesetup.addActionListener(this);
		fprint.addActionListener(this);
		fexit.addActionListener(this);
		
		edit = new Menu("Edit");
		 
		eundo = new MenuItem("Undo");
		esep1 = new MenuItem("-");
		ecut = new MenuItem("Cut");
		ecopy = new MenuItem("Copy");
		epaste = new MenuItem("Paste");
		edelete = new MenuItem("Delete");
		esep2 = new MenuItem("-");
		efindreplace = new MenuItem("Find & Replace");
		egoto = new MenuItem("Go to...");
		esep3 = new MenuItem("-");
		eselectall = new MenuItem("Select All");
		etimedate = new MenuItem("Time/Date");

		edit.add(eundo);
		edit.add(esep1);
		edit.add(ecut);
		edit.add(ecopy);
		edit.add(epaste);
		edit.add(edelete);
		edit.add(esep2);
		edit.add(efindreplace);
		edit.add(egoto);
		edit.add(esep3);
		edit.add(eselectall);
		edit.add(etimedate);
		
		eundo.addActionListener(this);
		ecut.addActionListener(this);
		ecopy.addActionListener(this);
		epaste.addActionListener(this);
		edelete.addActionListener(this);
		efindreplace.addActionListener(this);
		egoto.addActionListener(this);
		eselectall.addActionListener(this);
		etimedate.addActionListener(this);
		
		format = new Menu("Format");
		ftfont = new MenuItem("Font...");
		ftfont.addActionListener(this);
		
		foreground=new Menu("Foregound Color");
		fgBlack=new MenuItem("Black");
		fgBlue=new MenuItem("Blue");
		fgCyan=new MenuItem("Cyan");
		fgDarkGray=new MenuItem("Dark Gray");
		fgGray=new MenuItem("Gray");
		fgGreen=new MenuItem("Green");
		fgLightGray=new MenuItem("Light Gray");
		fgMagenta=new MenuItem("Magenta");
		fgOrange=new MenuItem("Orange");
		fgPink=new MenuItem("Pink");
		fgRed=new MenuItem("Red");
		fgYellow=new MenuItem("Yellow");
		fgWhite=new MenuItem("White");
		foreground.add(fgBlack);
		foreground.add(fgBlue);
		foreground.add(fgCyan);
		foreground.add(fgDarkGray);
		foreground.add(fgGray);
		foreground.add(fgGreen);
		foreground.add(fgLightGray);
		foreground.add(fgMagenta);
		foreground.add(fgOrange);
		foreground.add(fgPink);
		foreground.add(fgRed);
		foreground.add(fgYellow);
		foreground.add(fgWhite);
		fgBlack.addActionListener(this);
		fgBlue.addActionListener(this);
		fgCyan.addActionListener(this);
		fgDarkGray.addActionListener(this);
		fgGray.addActionListener(this);
		fgGreen.addActionListener(this);
		fgLightGray.addActionListener(this);
		fgMagenta.addActionListener(this);
		fgOrange.addActionListener(this);
		fgPink.addActionListener(this);
		fgRed.addActionListener(this);
		fgYellow.addActionListener(this);
		fgWhite.addActionListener(this);
						
		background=new Menu("Background Color");
		bgBlack=new MenuItem("Black");
		bgBlue=new MenuItem("Blue");
		bgCyan=new MenuItem("Cyan");
		bgDarkGray=new MenuItem("Dark Gray");
		bgGray=new MenuItem("Gray");
		bgGreen=new MenuItem("Green");
		bgLightGray=new MenuItem("Light Gray");
		bgMagenta=new MenuItem("Magenta");
		bgOrange=new MenuItem("Orange");
		bgPink=new MenuItem("Pink");
		bgRed=new MenuItem("Red");
		bgYellow=new MenuItem("Yellow");
		bgWhite=new MenuItem("White");
		background.add(bgBlack);
		background.add(bgBlue);
		background.add(bgCyan);
		background.add(bgDarkGray);
		background.add(bgGray);
		background.add(bgGreen);
		background.add(bgLightGray);
		background.add(bgMagenta);
		background.add(bgOrange);
		background.add(bgPink);
		background.add(bgRed);
		background.add(bgYellow);
		background.add(bgWhite);
		bgBlack.addActionListener(this);
		bgBlue.addActionListener(this);
		bgCyan.addActionListener(this);
		bgDarkGray.addActionListener(this);
		bgGray.addActionListener(this);
		bgGreen.addActionListener(this);
		bgLightGray.addActionListener(this);
		bgMagenta.addActionListener(this);
		bgOrange.addActionListener(this);
		bgPink.addActionListener(this);
		bgRed.addActionListener(this);
		bgYellow.addActionListener(this);
		bgWhite.addActionListener(this);
		
		
		format.add(ftfont);
		format.add(foreground);
		format.add(background);
			
		picture=new Menu("picture");
		popen=new MenuItem("Open Picture");
		psave=new MenuItem("Save");
		psaveas=new MenuItem("Save As...");
		pnormal=new MenuItem("Normal");
		pgrayscale=new MenuItem("GrayScale");
		pinvert=new MenuItem("Invert");
		pcontrast=new MenuItem("Contrast");
		pblur=new MenuItem("Blur");
		psharpen=new MenuItem("Sharpen");
		pexit=new MenuItem("Exit");
		psave.enable(false);
		psaveas.enable(false);
		pnormal.enable(false);
		pgrayscale.enable(false);
		pinvert.enable(false);
		pcontrast.enable(false);
		pblur.enable(false);
		psharpen.enable(false);
		popen.addActionListener(this);
		psave.addActionListener(this);
		psaveas.addActionListener(this);
		pnormal.addActionListener(this);
		pgrayscale.addActionListener(this);
		pinvert.addActionListener(this);
		pcontrast.addActionListener(this);
		pblur.addActionListener(this);
		psharpen.addActionListener(this);
		pexit.addActionListener(this);
		picture.add(popen);
		picture.add(psave);
		picture.add(psaveas);
		picture.add(pnormal);
		picture.add(pgrayscale);
		picture.add(pinvert);
		picture.add(pcontrast);
		picture.add(pblur);
		picture.add(psharpen);
		picture.add(pexit);
				
		music=new Menu("Music");
		mopen=new MenuItem("Open");
		mexit=new MenuItem("Exit");
		music.add(mopen);
		music.add(mexit);
		mopen.addActionListener(this);
		mexit.addActionListener(this);
		
		help=new Menu("Help");
		habout=new MenuItem("About...");
		help.add(habout);
		webBrowser=new Menu("Web Browser");
		wopen=new MenuItem("start");
		webBrowser.add(wopen);		
		 wopen.addActionListener(this);
		mb.add(file);
		mb.add(edit);
		mb.add(format);
		mb.add(picture);
		mb.add(music);
		mb.add(webBrowser);
		mb.add(help);
        
       	frm.setSize(1024,768);
		frm.setVisible(true);
		frm.addWindowListener(new WindowAdapter() { 
				public void windowClosing(WindowEvent ae){
				 System.exit(0);	
				}
			} );
		ta1.setForeground(Color.black);
		font();
	}
	
	public void font()
	{
		try
		{
			
		    Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		    cn  = DriverManager.getConnection("jdbc:odbc:Neweditor");
			st = cn.createStatement();
			rs=st.executeQuery("Select * from FontTable");
			rs.next();
			a=rs.getString("Font");
			b=rs.getString("FontStyle");
			c=Integer.parseInt(rs.getString("FontSize"));
					
			if(b.equals("PLAIN"))
			   ta1.setFont(new Font(a,Font.PLAIN,c));
			if(b.equals("BOLD"))
			   ta1.setFont(new Font(a,Font.BOLD,c));
			if(b.equals("ITALIC"))
			   ta1.setFont(new Font(a,Font.ITALIC,c));
         }  
		 catch(SQLException ee){}
		 catch(Exception e){}
		
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
		
		if(ae.getSource()== wopen)
		{
			System.out.println("perfect");
			NewBrowser newb=new NewBrowser();
		}		
		

		FileDialog fdopen=new FileDialog(frm,"Open",FileDialog.LOAD);
		FileDialog fdsave=new FileDialog(frm,"Save",FileDialog.SAVE);
		
		if(ae.getSource()==fnew) 
		{
			if(!ta1.getText().equals(""))
			{
				//JOptionPane.showmessageDialog(error,"Do u want to save changes?");
				ta1.setText("");
			}
		}
		
		if (ae.getSource()==fopen)
		{
			flag = "y";
			fdopen.setVisible(true);
			filee = fdopen.getFile();
			directory = fdopen.getDirectory();
			File openfile = new File(directory,filee);
			
			try
			{
				RandomAccessFile ram2 = new RandomAccessFile(openfile,"rw");
				
				long size = ram2.length();
				String msg = "";
				
				for(int counter=0;counter<size;counter++)
				{
					char words = (char)ram2.read();
					msg = msg + words;
				}
				ta1.setText(msg);
			}
			catch(Exception e1)
			{
				System.out.println(e1);
			}
		}
		
		if (ae.getSource() == fsave)
		{
			int count=0;
			if (flag.equals("y"))
			{
				File delme = new File(directory,filee);
			
			    String a = directory;
			    String b = filee;
    			String filename=a+b;
    			try
	    		{
	    			delme.delete();
			    }
			    catch(SecurityException eeme)
			    {
			    	System.out.println(eeme);
			    }
			    File filesaveit = new File(a,b);
			
			    try
			    {
			    	for(int i=0;i<filename.length();i++)
			    	{
			    		if(filename.charAt(i)=='.')
			    		    count++;
			    	}
			    	
			    	if(count!=0)
			    	{
			    		RandomAccessFile ram = new RandomAccessFile(filename,"rw");
			    		String msh = ta1.getText();
				    	ram.writeBytes(msh);
				    	ram.close();
			    	}
				    else if(count==0)
				    {
				    	RandomAccessFile ram = new RandomAccessFile(filename+".txt","rw");
				    	String msh = ta1.getText();
				    	ram.writeBytes(msh);
				    	ram.close();
				    }
				    
			    }
			    catch(Exception rt)
			    {
			    	System.out.println(rt);
			    }
			}
			
			if (flag.equals("n"))
			{
				fdsave.setVisible(true);
			    String dir = fdsave.getDirectory();
			    String fil = fdsave.getFile();
			    String filename=dir+fil;
			    File filesave = new File(dir,fil);
			
			    try
			    {
			    	for(int i=0;i<filename.length();i++)
			    	{
			    		if(filename.charAt(i)=='.')
			    		    count++;
			    	}
			    	
			    	if(count!=0)
			    	{
			    		
				       	RandomAccessFile ram = new RandomAccessFile(filename,"rw");
						ram.seek(ram.length());
						String msh = ta1.getText();
						ram.writeBytes(msh);
						ram.close();
					}
					else if(count==0)
			    	{
			    		
				       	RandomAccessFile ram = new RandomAccessFile(filename+".txt","rw");
						ram.seek(ram.length());
						String msh = ta1.getText();
						ram.writeBytes(msh);
						ram.close();
					}
				}
				catch(Exception rt)
				{
					System.out.println(rt);
				}
			}
		}
		
		if (ae.getSource() == fsaveas)
		{
			int count=0;
			fdsave.setVisible(true);
			String dir = fdsave.getDirectory();
			String fil = fdsave.getFile();
			String filename=dir+fil;
			File filesave = new File(dir,fil);
			
			try
			{
				for(int i=0;i<filename.length();i++)
		    	{
		    		if(filename.charAt(i)=='.')
		    		    count++;
		    	}
					
				if(count!=0)
		    	{
		    		
			       	RandomAccessFile ram = new RandomAccessFile(filename,"rw");
					ram.seek(ram.length());
					String msh = ta1.getText();
					ram.writeBytes(msh);
					ram.close();
				}
				else if(count==0)
		    	{
		    		
			       	RandomAccessFile ram = new RandomAccessFile(filename+".txt","rw");
					ram.seek(ram.length());
					String msh = ta1.getText();
					ram.writeBytes(msh);
					ram.close();
				}
			}
			catch(Exception rt)
			{
				System.out.println(rt);
			}
		}
		
		if(ae.getSource() == fpagesetup)
		{
			p.printDialog(); //print Dialog is inbuilt method
		}
		
		if(ae.getSource() == fprint) 
		{
			try
			{
				p.print();
			}
			catch(Exception ew)
			{}
		}
		
		if(ae.getSource() == fexit)
		{
			System.exit(0);
		}
	
	
	//---------------------------------------------------------------------------
	
	    if(ae.getSource() == eundo)
		{
			switch(f)
			{
				case 4:
				{
		    		ta1.insert(stime,gposdel);
					f = 3;
					break;
				}
				
				case 3:
				{
					ta1.replaceRange("",gposdel,gposdel+stime.length());
					f = 4;
					break;
				}
				
			case 1:
				{
					ta1.insert(text,gposdel);
					f = 2;
					break;
				}
				
				case 2:
				{
					ta1.replaceRange("",gposdel,gposdel+text.length());
					f = 1;
					break;
				}
				
				case -1:
				{
					f = 1;
					text = ta1.getText();
					ta1.setText("");
					break;
				}
			}
		}
		
		if(ae.getSource() == ecut)
		{
			f = 1;
			text = ta1.getSelectedText();
			gposdel = ta1.getCaretPosition();
			ta1.replaceRange("",gposdel,gposdel+text.length());
		}
		
		if(ae.getSource() == ecopy)
		{
			text = ta1.getSelectedText();
		}
		
		if(ae.getSource() == epaste)
		{
			if (text.equals(""))
			{
				gposdel = ta1.getCaretPosition();
				ta1.insertText(text,gposdel);
				f=2;
			}
			else
			{
				gposdel = ta1.getCaretPosition();
				int hg = ta1.getSelectionStart();
				int gh = ta1.getSelectionEnd();
				ta1.replaceRange(text,hg,gh);
				f=2;
			}
		}
		
		if(ae.getSource() == edelete)
		{
			f = 1;
			text = ta1.getSelectedText();
			gposdel = ta1.getCaretPosition();
			ta1.replaceRange("",gposdel,gposdel+text.length());
		}
		
		if(ae.getSource() == efindreplace)
		{
			int i=0,j;
			find = JOptionPane.showInputDialog("Enter The Word You Want To Find");
			replace=JOptionPane.showInputDialog("Enter The Word You Want To Raplace");
			String s1=ta1.getText();
			String s2[]=new String[2000];
			StringTokenizer st=new StringTokenizer(s1," ");
		    while(st.hasMoreTokens())
		    {
		    	s2[i]=st.nextToken();
			    if(s2[i].equals(find))
			       s2[i]=replace;
			    i++;
		    }
		    ta1.setText("");
		    
		    for(j=0;j<i;j++)
      		    ta1.append(s2[j]+" ");
		}
		
		if(ae.getSource() == eselectall)
		{
			ta1.selectAll();
		}
		
		if (ae.getSource() == etimedate)
		{
			String text2=ta1.getSelectedText();
			
			if (text2.equals(""))
			{
				gposdel = ta1.getCaretPosition();
				ta1.insertText(stime,gposdel);
				f=3;
			}
			else
			{
				gposdel = ta1.getCaretPosition();
				int hg = ta1.getSelectionStart();
				int gh = ta1.getSelectionEnd();
				ta1.replaceRange(stime,hg,gh);
				f=3;
			}
		}
		
		if (ae.getSource() == ftfont)
		{
			font fob1=new font();
		}
		
		if (ae.getSource() == fgBlack)
		{
			ta1.setForeground(Color.black);
		}
		
		if (ae.getSource() == fgBlue)
		{
			ta1.setForeground(Color.blue);
		}
		
		if (ae.getSource() == fgCyan)
		{
			ta1.setForeground(Color.cyan);
		}
		
		if (ae.getSource() == fgDarkGray)
		{
			ta1.setForeground(Color.darkGray);
		}
		
		if (ae.getSource() == fgGray)
		{
			ta1.setForeground(Color.gray);
		}
		
		if (ae.getSource() == fgGreen)
		{
			ta1.setForeground(Color.green);
		}
		
		if (ae.getSource() == fgLightGray)
		{
			ta1.setForeground(Color.lightGray);
		}
		
		if (ae.getSource() == fgMagenta)
		{
			ta1.setForeground(Color.magenta);
		}
		
		if (ae.getSource() == fgOrange)
		{
			System.out.println("orange");
			ta1.setForeground(Color.orange);
		}
		
		if (ae.getSource() == fgPink)
		{
			ta1.setForeground(Color.pink);
		}
		
		if (ae.getSource() == fgRed)
		{
			ta1.setForeground(Color.red);
		}
		
		if (ae.getSource() == fgYellow)
		{
			ta1.setForeground(Color.yellow);
		}
		
		if (ae.getSource() == fgWhite)
		{
			ta1.setForeground(Color.white);
		}
			
		if (ae.getSource() == bgBlack)
		{
			ta1.setBackground(Color.black);
		}
		
		if (ae.getSource() == bgBlue)
		{
			ta1.setBackground(Color.blue);
		}
		
		if (ae.getSource() == bgCyan)
		{
			ta1.setBackground(Color.cyan);
		}
		
		if (ae.getSource() == bgDarkGray)
		{
			ta1.setBackground(Color.darkGray);
		}
		
		if (ae.getSource() == bgGray)
		{
			ta1.setBackground(Color.gray);
		}
		
		if (ae.getSource() == bgGreen)
		{
			ta1.setBackground(Color.green);
		}
		
		if (ae.getSource() == bgLightGray)
		{
			ta1.setBackground(Color.lightGray);
		}
		
		if (ae.getSource() == bgMagenta)
		{
			ta1.setBackground(Color.magenta);
		}
		
		if (ae.getSource() == bgOrange)
		{
			ta1.setBackground(Color.orange);
		}
		
		if (ae.getSource() == bgPink)
		{
			ta1.setBackground(Color.pink);
		}
		
		if (ae.getSource() == bgRed)
		{
			ta1.setBackground(Color.red);
		}
		
		if (ae.getSource() == bgYellow)
		{
			ta1.setBackground(Color.yellow);
		}
		
		if (ae.getSource() == bgWhite)
		{
			ta1.setBackground(Color.white);
		}
		
		if(ae.getSource()==popen)
		{
			new image();
			file.enable(false);
			edit.enable(false);
			format.enable(false);
			pnormal.enable(true);
			pgrayscale.enable(true);
			pinvert.enable(true);
			pcontrast.enable(true);
			pblur.enable(true);
			psharpen.enable(true);
		}
		
		
		if(ae.getSource()==psave)
		{
			vFile.removeAllElements();
			vFile.addElement(fimg);	
							
			RenderedImage rendImage = bimg;
				
			try
			{
				FileOutputStream fos = new FileOutputStream(image.directory+image.filee);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(bimg);
			}
			catch(Exception exp){}
			
			try 
			{
        		File file = new File(image.directory+image.filee);        		
        		ImageIO.write(rendImage, "jpg", file);
    		}
    		catch (IOException e) {}
		}		    

		if(ae.getSource()==psaveas)
		{
			fdsave.setVisible(true);
		    filee = fdsave.getFile();
		    directory = fdsave.getDirectory();
		 	String fileName=directory+filee;
	
			vFile.removeAllElements();
			vFile.addElement(fimg);	
						
			RenderedImage rendImage = bimg;
			
			try
			{
				FileOutputStream fos = new FileOutputStream(fileName);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(bimg);
			}catch(Exception exp){}
			
			try {
        		File file = new File(fileName+ ".jpg");        		
        		ImageIO.write(rendImage, "jpg", file);
    		}catch (IOException e) {}
		}		    
			
		if(ae.getSource()==pnormal)
		{
			l1.setIcon(image.img);
			psave.enable(false);
			psaveas.enable(false);
		}
		
		if(ae.getSource()==pgrayscale)
		{
			try
			{
   			    Grayscale g=new Grayscale();
		 		MediaTracker mt = new MediaTracker(this);
                fimg=g.filter(this,image.image);
		 		mt.addImage(fimg,0);
				try{mt.waitForID(0);}catch(InterruptedException ie){}
		 		int width = fimg.getWidth(null);
	            int height = fimg.getHeight(null);
	            bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            bimg.createGraphics().drawImage(fimg, 0, 0, this);
	            img = new ImageIcon(bimg);
	         	l1.setIcon(img);
	         	psave.enable(true);
	         	psaveas.enable(true);
	         }
	         catch(Exception e)
	         {
	         	System.out.println(e);
	         }
	 	}
	 	
	 	if(ae.getSource()==pinvert)
		{
			try
			{
   			    Invert g=new Invert();
   			    MediaTracker mt = new MediaTracker(this);
		 		fimg=g.filter(this,image.image);
		 		mt.addImage(fimg,0);
				try{mt.waitForID(0);}catch(InterruptedException ie){}
		 		int width = fimg.getWidth(null);
	            int height = fimg.getHeight(null);
	            bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            bimg.createGraphics().drawImage(fimg, 0, 0, this);
	            img = new ImageIcon(bimg);
	         	l1.setIcon(img);
	         	psave.enable(true);
	         	psaveas.enable(true);
	         }
	         catch(Exception e)
	         {
	         	System.out.println(e);
	         }
	 	}
	 	
	 	if(ae.getSource()==pcontrast)
		{
			try
			{
   			    Contrast g=new Contrast();
   			    MediaTracker mt = new MediaTracker(this);
		 		fimg=g.filter(this,image.image);
		 		mt.addImage(fimg,0);
				try{mt.waitForID(0);}catch(InterruptedException ie){}
		 		int width = fimg.getWidth(null);
	            int height = fimg.getHeight(null);
	            bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            bimg.createGraphics().drawImage(fimg, 0, 0, this);
	            img = new ImageIcon(bimg);
	         	l1.setIcon(img);
	         	psave.enable(true);
	         	psaveas.enable(true);
	         }
	         catch(Exception e)
	         {
	         	System.out.println(e);
	         }
	 	}

		if(ae.getSource()==pblur)
		{
			try
			{
   			    Blur g=new Blur();
   			    MediaTracker mt = new MediaTracker(this);
		 		fimg=g.filter(this,image.image);
		 		mt.addImage(fimg,0);
				try{mt.waitForID(0);}catch(InterruptedException ie){}
		 		int width = fimg.getWidth(null);
	            int height = fimg.getHeight(null);
	            bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            bimg.createGraphics().drawImage(fimg, 0, 0, this);
	            img = new ImageIcon(bimg);
	         	l1.setIcon(img);
                psave.enable(true);
	         	psaveas.enable(true);
	         }
	         catch(Exception e)
	         {
	         	System.out.println(e);
	         }
	 	}
	 	
	 	if(ae.getSource()==psharpen)
		{
			try
			{
   			    Sharpen g=new Sharpen();
   			    MediaTracker mt = new MediaTracker(this);
		 		fimg=g.filter(this,image.image);
		 		mt.addImage(fimg,0);
				try{mt.waitForID(0);}catch(InterruptedException ie){}
		 		int width = fimg.getWidth(null);
	            int height = fimg.getHeight(null);
	            bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	            bimg.createGraphics().drawImage(fimg, 0, 0, this);
	            img = new ImageIcon(bimg);
	         	l1.setIcon(img);
	         	psave.enable(true);
	         	psaveas.enable(true);
	         }
	         catch(Exception e){}
	 	}
		
		if(ae.getSource()==pexit)
		{
			l1.hide();
			ta1.show();
			file.enable(true);
			edit.enable(true);
			format.enable(true);
			psave.enable(false);
			psaveas.enable(false);
			pnormal.enable(false);
			pgrayscale.enable(false);
			pinvert.enable(false);
			pcontrast.enable(false);
			pblur.enable(false);
			psharpen.enable(false);
		}
		
		if(ae.getSource()==mopen)
		{
			JFileChooser fileChooser = new JFileChooser();
      
	        int result = fileChooser.showOpenDialog( null );
	
	        if ( result == JFileChooser.APPROVE_OPTION ) 
	        {
	           URL mediaURL = null;
	         
	           try
	           {
	           	   mediaURL = fileChooser.getSelectedFile().toURL();
	           }
	           catch ( MalformedURLException malformedURLException )
	           {
	               System.err.println( "Could not create URL for the file" );
	           }
               if ( mediaURL != null )
	           {
	           	   mediaTest = new JFrame( "Media Player" );//creates new window for media player
	               mediaTest.setDefaultCloseOperation( mediaTest.EXIT_ON_CLOSE );
	            
	          //     mediaPanel = new MediaPanel( mediaURL );
	             //  mediaTest.add( mediaPanel );
	            
	               mediaTest.setSize( 300, 300 );
	               mediaTest.setVisible( true );
	           }
	        }
	    }
	    
	    if(ae.getSource()==mexit)
	    {
	    	mediaTest.setVisible(false);
	    }
	    
	}
	
	public static void main (String[] args) 
	{
		new Neweditor();
	}
}



