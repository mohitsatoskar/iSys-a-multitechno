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

class image extends JFrame
{
	 static ImageIcon img;
     static Image image;
     static int width;
     static int height;
     JPanel p;
     JLabel label;
     static String filee = "",directory = "";
     
     public image()
     {
         MediaTracker mt = new MediaTracker(this);
         FileDialog fdopen=new FileDialog(this,"Open",FileDialog.LOAD);
         fdopen.setVisible(true);
		 filee = fdopen.getFile();
		 directory = fdopen.getDirectory();
		 image = Toolkit.getDefaultToolkit().createImage(directory+filee);
         mt.addImage(image,0);
         try{mt.waitForID(0);}catch(InterruptedException ie){}
         width = image.getWidth(null);
         height = image.getHeight(null);
         BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
         bimg.createGraphics().drawImage(image, 0, 0, this);
         Neweditor.ta1.hide();
         img = new ImageIcon(bimg);
         Neweditor.l1.setIcon(img);
         Neweditor.l1.show();
    }
}
	