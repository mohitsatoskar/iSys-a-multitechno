
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MediaTest
{
   
   public MediaTest()
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
            JFrame mediaTest = new JFrame( "Media Tester" );
            mediaTest.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
            
            MediaPanel mediaPanel = new MediaPanel( mediaURL );
            mediaTest.add( mediaPanel );
            
            mediaTest.setSize( 300, 300 );
            mediaTest.setVisible( true );
         } 
      } 
   } 
} 