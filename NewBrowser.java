
//importing essential packages
import java.util.*; //importing utility package for basic functioning
import java.io.*;//importing package for input output files
import java.awt.*;//importing package of abstract window toolkit for setting frames
import java.awt.event.*;//importing package for event handeling
import javax.swing.*;//importing swing package to make and use lightweight components
import javax.swing.event.*;//importing package for event handelling for swing component
import java.util.Stack;//importing package to use stoarage like stack
import java.net.*;//importing package to connect to internet
import org.jdesktop.jdic.browser.*;//importing user defined package of web browser
import org.jdesktop.jdic.tray.*;//importing user defined package of web browser

@SuppressWarnings("serial")

public class NewBrowser extends JFrame implements ActionListener//implements the interface of ActionListener 
{ 

   //declaring and initialising essential components of swing framework
	Menu menuFile,menuEdit,menuView,menuNavigation,menuHelp,menuViewGoto,menuDownLoad;
	MenuItem menuFileNewWindow,menuFileOpen,menuSaveAs,menuFilePrint,menuFileExit;
	MenuItem menuEditCut,menuEditCopy,menuEditPaste,menuEditSelectAll,menuEditFindOnPage,menuViewGotoBack,menuViewGotoForward,menuViewGotoHome;
	MenuItem menuViewToolbar,menuViewStatusbar,menuViewRefresh,menuViewSource,menuViewHistory;
	MenuItem menuHelpAboutWeb,menuHelpDeveloper;
	MenuItem menuNavigationForward,menuNavigationBack,menuNavigationRefresh,menuNavigationStop,menuNavigationHome,menuNavigationSetHome;
	MenuItem menuDownLoadFile;
	MenuBar menubar;
	JTextField txtfaddress;
	JButton btnBack,btnForward,btnRefresh,btnGo,btnHome,btnStop;
	
	JLabel lblstatus1,lblstatus2;
	String urlHome="http://www.google.co.in";

	JToolBar toolbar;
	JEditorPane ed = new JEditorPane();
	WebBrowser webBrowser;
	
	//initilazing constructor
	public NewBrowser()
	{
		//crating one file to check whether browser have its fixed home URL or not
		try
		{
			File file = new File("Home.txt");
			if(!file.exists())
			{
				FileWriter fw = new FileWriter(file);
				fw.write("http://www.google.co.in");
				fw.flush();
				fw.close();
			}
			toolbar = new JToolBar();
			
			//declaring and initialising browser engine to run browser and open google page.
			BrowserEngineManager bem= BrowserEngineManager.instance();
  		    bem.setActiveEngine(BrowserEngineManager.IE);//browser is embeded with intrnet explorer.
  		    webBrowser = new WebBrowser(new URL(urlHome));
		}
	
		catch(MalformedURLException mue)
		{
			System.out.println ("mue  " + mue.getMessage());
		}
		catch(IOException ioe)
		{
			System.out.println ("File IOE Error " + ioe.getMessage());
		}
		
	
	//setting the menubar components with actionlistener
	
			menubar = new MenuBar();
		
			menuFile = new Menu("File");
			menuFileNewWindow = new MenuItem("New Window");
			menuFileNewWindow.setShortcut(new MenuShortcut(KeyEvent.VK_N,false));
			menuFileNewWindow.addActionListener(this);
			menuFile.add(menuFileNewWindow);
			
			menuFileOpen = new MenuItem("Open...");
			menuFileOpen.setShortcut(new MenuShortcut(KeyEvent.VK_O,false)); 
			menuFileOpen.addActionListener(this);
			menuFile.add(menuFileOpen);
			
			/*JMenuItem openMenuItem = new JMenuItem("Open", KeyEvent.VK_O);
    		openMenuItem.addActionListener(menuListener);
    		fileMenu.add(openMenuItem);
              */
			
			
			menuSaveAs = new MenuItem("Save As...");
			menuSaveAs.setShortcut(new MenuShortcut(KeyEvent.VK_S,false));
			menuSaveAs.addActionListener(this);
			menuFile.add(menuSaveAs);
			menuFile.addSeparator();
			
			menuFilePrint = new MenuItem("Print...");
			menuFilePrint.setShortcut(new MenuShortcut(KeyEvent.VK_P,false));
			menuFile.add(menuFilePrint);
			menuFile.addSeparator();
			menuFileExit = new MenuItem("Exit");
			menuFileExit.addActionListener(new ActionListener(){public void actionPerformed(ActionEvent e){System.exit(0);}});
			menuFile.add(menuFileExit);
		
		
		
	
		    menuEdit = new Menu("Edit");
			menuEditCut= new MenuItem("Cut");
			menuEditCut.setShortcut(new MenuShortcut(KeyEvent.VK_X,false));
			//menuEditCut.setEnabled(false);
			menuEdit.add(menuEditCut);
			
			menuEditCopy= new MenuItem("Copy");
			menuEditCopy.setShortcut(new MenuShortcut(KeyEvent.VK_C,false));
			menuEdit.add(menuEditCopy);
			
			menuEditPaste= new MenuItem("Paste");
			menuEditPaste.setShortcut(new MenuShortcut(KeyEvent.VK_P,false));
			menuEdit.add(menuEditPaste);
			
			menuEditSelectAll= new MenuItem("Select All");
			menuEditSelectAll.setShortcut(new MenuShortcut(KeyEvent.VK_A,false));
			menuEdit.add(menuEditSelectAll);
		
			menuEditFindOnPage= new MenuItem("Find On Page");
			menuEditFindOnPage.setShortcut(new MenuShortcut(KeyEvent.VK_F,false));
			menuEdit.add(menuEditFindOnPage);
			
			menuView = new Menu("View");
			menuViewToolbar=new MenuItem("Toolbar");
			menuView.add(menuViewToolbar);

			menuViewStatusbar=new MenuItem("Statusbar");
			menuView.add(menuViewStatusbar);

			menuViewGoto=new Menu("Goto");
		
			menuViewGotoBack = new MenuItem("Back");
			//menuViewGotoBack.setShortcut(new MenuShortcut(KeyEvent.VK_B,false));
			menuViewGotoBack.addActionListener(this);
			menuViewGoto.add(menuViewGotoBack);

			menuViewGotoForward = new MenuItem("Forward");
			//menuViewGotoForward.setShortcut(new MenuShortcut(KeyEvent.VK_F,true));
			menuViewGotoForward.addActionListener(this);
			menuViewGoto.add(menuViewGotoForward);
			
			menuViewGotoHome = new MenuItem("Home");
			menuViewGotoHome.setShortcut(new MenuShortcut(KeyEvent.VK_HOME,false));
			menuViewGotoHome.addActionListener(this);
			menuViewGoto.add(menuViewGotoHome);

			menuViewHistory = new MenuItem("History");
			menuViewHistory.setShortcut(new MenuShortcut(KeyEvent.VK_H,false));
			menuView.add(menuViewGoto);
			
			menuViewHistory.addActionListener(this);
			menuView.add(menuViewHistory);
					
			menuViewRefresh=new MenuItem("Refresh");
			menuViewRefresh.setShortcut(new MenuShortcut(KeyEvent.VK_R,false));
			menuView.add(menuViewRefresh);
	
			menuViewSource=new MenuItem("Source");
			menuViewSource.addActionListener(this);
			menuView.add(menuViewSource);
			
			menuNavigation = new Menu("Navigation");
	
			menuNavigationForward = new MenuItem("Forward");
			//menuNavigationForward.setShortcut(new MenuShortcut(KeyEvent.VK_F,true));
			menuNavigationForward.addActionListener(this);
			menuNavigation.add(menuNavigationForward);
	
			menuNavigationBack = new MenuItem("Back");
			//menuNavigationBack.setShortcut(new MenuShortcut(KeyEvent.VK_B,false));
			menuNavigationBack.addActionListener(this);
			menuNavigation.add(menuNavigationBack);
	
			menuNavigationRefresh = new MenuItem("Refresh");
			//menuNavigationRefresh.setShortcut(new MenuShortcut(KeyEvent.VK_R,false));
			menuNavigationRefresh.addActionListener(this);
			menuNavigation.add(menuNavigationRefresh);
		
			menuNavigationStop = new MenuItem("Stop");
			menuNavigationStop.addActionListener(this);
			menuNavigation.add(menuNavigationStop);
		
			menuDownLoad = new Menu("Download");
			menuDownLoadFile = new MenuItem("File");
			menuDownLoadFile.addActionListener(this);
			menuDownLoad.add(menuDownLoadFile);
		
			menuHelp = new Menu("Help");
			menuHelpAboutWeb=new MenuItem("About Web");
			menuHelp.add(menuHelpAboutWeb);

			menuHelpDeveloper=new MenuItem("Developer");
			menuHelp.add(menuHelpDeveloper);
	
	
	//adding the menubar component to swing frame			
			menubar.add(menuFile);
			menubar.add(menuEdit);
			menubar.add(menuView);
			menubar.add(menuNavigation);
			menubar.add(menuDownLoad);
			menubar.add(menuHelp);
			
			setMenuBar(menubar);
			
				
		
		setLayout(new BorderLayout());//setting border layout
		Toppanel toppanel = new Toppanel();
		CenterPanel ceneterpanel = new CenterPanel();
		BottomPanel bottompanel = new BottomPanel();
		
		
		//setting panels with their positions
		add(toppanel,BorderLayout.NORTH);
		add(ceneterpanel,BorderLayout.CENTER);
		add(bottompanel,BorderLayout.SOUTH);
		
		    		
		txtfaddress.setText("http://");//settinf http protocol to path

		
		
		txtfaddress.addActionListener(this);
		btnGo.addActionListener(this);
		btnBack.addActionListener(this);
		btnForward.addActionListener(this);
		btnHome.addActionListener(this);
		try
		{
			webBrowser.setURL(new URL("http://www.google.co.in"));//by default home page
		}
		catch(MalformedURLException mue)
		{
			System.out.println ("mal url ex" + mue.getMessage());
		}
		
		
		//adding web listener to web browser 
		webBrowser.addWebBrowserListener(new WebBrowserListener() {
            public void downloadStarted(WebBrowserEvent event) {
            	System.out.println ("download started");
            }

            public void downloadCompleted(WebBrowserEvent event) 
            {
            	System.out.println ("download completed");

                URL currentUrl = webBrowser.getURL();

                if (currentUrl != null) 
                {
                   txtfaddress.setText(currentUrl.toString());
                }
            }

            public void downloadProgress(WebBrowserEvent event) 
            {
            	lblstatus1.setText("Loading in Progress.....");
                
            }

            public void downloadError(WebBrowserEvent event) 
            {
            	lblstatus1.setText("Error in Loading .");

            }

            public void documentCompleted(WebBrowserEvent event) 
            {
            	lblstatus1.setText("Document loading completed.");

            }

            public void titleChange(WebBrowserEvent event) 
            {
	            lblstatus1.setText("Title of the browser window changed.");
            }  

            public void statusTextChange(WebBrowserEvent event) 
            {
                lblstatus1.setText("Status text changed.");
            }

			@Override
			public void initializationCompleted(WebBrowserEvent event) {
				// TODO Auto-generated method stub
				
			}  
        });
	
		
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);//extend the window to its maximum size
		setTitle("web Browser");//setting title as web browser to window
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//jframe will exit when clicks on close button
		
		
	}
	
	
//implementing all action events 
	
	public void actionPerformed(ActionEvent e)
	{
		
		//setting browser to its home page i.e. google.com
		if(e.getSource()==btnHome)
		{
			try
			{
				loadURL(urlHome);						
			
			}
			catch(Exception ioe)
			{
				System.out.println ("IOError " + ioe.getMessage());
			}
		}
		
		//opening the desired web page by giving URL
		if(e.getSource()==btnGo)
		{
			//JOptionPane.showMessageDialog(null,"Going to open","Web Browser",JOptionPane.INFORMATION_MESSAGE);
			try
			{
					loadURL(txtfaddress.getText());
			
			
			}
			catch(Exception ioe)
			{
				System.out.println ("IOError " + ioe.getMessage());
			}
		}
		
		
		//getting back page 
		if(e.getSource() == btnBack)
		{
			try
            {
                  webBrowser.back();                 
                
            }
            catch(Exception ioe)
            {
             	System.out.println ("Back Error  " + ioe.getMessage())    ;
            }
		}
		
		//getting next page
		if(e.getSource() == btnForward)
		{
			try
            {
                 webBrowser.forward();
                   
            }
            catch(Exception ioe)
            {
                  System.out.println ("Forward Error " + ioe.getMessage());
            }
		}
		
		//refreshing the web browser
		
		if(e.getSource()== btnRefresh)
		{
			try
            {
                 webBrowser.refresh();
                   
            }
            catch(Exception ioe)
            {
                  System.out.println ("Refresh Error  " + ioe.getMessage());
            }
        }
		
		//stopping the webbrowser
		if(e.getSource()== btnStop)
		{
			try
            {
                 webBrowser.stop();
                   
            }
            catch(Exception ioe)
            {
                  System.out.println ("Stop Error " + ioe.getMessage());
            }
        }
        
        //getting the address i.e.URL
        if(e.getSource()== txtfaddress)
		{
			try
            {
                 loadURL(txtfaddress.getText());
                   
            }
            catch(Exception ioe)
            {
                  System.out.println ("Text Address Error " + ioe.getMessage());
            }
        }
		


		

		//Menu Code Here 

		if(e.getSource()==menuFileNewWindow)
		{
			NewBrowser webBrowser = new NewBrowser();
		}
		
		
		//opeoning new file with open dialogue box
		if(e.getSource() == menuFileOpen)
		{
			menuFileOpen.setEnabled(false);
			OpenDialog o = new OpenDialog();
			//loadURL(o.url);
			//menuFileOpen.setEnabled(true);
			//if(OpenDialog.url!=null)
			//{
			//	System.out.println ("true");
			//	return;
			//}
			//else
			//{
			//	System.out.println ("else");
			//	loadURL(OpenDialog.url);	
			//}
			
		}
		
		//saving the page on specefied location
		if(e.getSource()== menuSaveAs)
		{
			JFileChooser jfc = new JFileChooser("C:\\Windows");
			int returnval = jfc.showSaveDialog(this);
			System.out.println ("return val = " + returnval);
			if(returnval==0)
			{
				File filename = jfc.getSelectedFile();
				System.out.println ("filepath   =" + filename.getPath());
				try
				{
					FileWriter fw = new FileWriter(filename.getPath());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(webBrowser.getContent());
					bw.flush();
					bw.close();
				}
				catch(IOException ioe)
				{
					System.out.println ("IOE Error " + ioe.getMessage());
					
				}
					
				
				
				//System.out.println ("filename = " + filename.getName());
				
				
			}
			
			
			
			
		}
		
		//same options again 
		if(e.getSource() == menuViewGotoBack)
		{
			webBrowser.back();	
		}
		if(e.getSource() == menuViewGotoForward)
		{
			webBrowser.forward();
		}
		if(e.getSource()==menuViewGotoHome)
		{
			loadURL(urlHome);	
		}
		
		if(e.getSource() == menuViewRefresh)
		{
			webBrowser.refresh();	
		}
		
		if(e.getSource() == menuViewSource)
		{
			String source = webBrowser.getContent();
		}
		if(e.getSource() == menuViewHistory)
		{
			new History(this);
		}
		if(e.getSource()==menuNavigationForward)
		{
			webBrowser.forward();
		}		
		if(e.getSource() == menuNavigationBack)
		{
			webBrowser.back();
		}
		if(e.getSource()== menuNavigationRefresh)
		{
			webBrowser.refresh();
		}
		if(e.getSource() == menuNavigationStop)
		{
			webBrowser.stop();
		}
		if(e.getSource() == menuNavigationHome)
		{
			loadURL(urlHome);
		}
		if(e.getSource() == menuNavigationSetHome)
		{
			//String s = JOptionPane.showInputDialog(null,"Home Address")
		}
		if(e.getSource() == menuDownLoadFile)
		{
			DownloadManager manager = new DownloadManager();
    			
		}

		
		
		
	}
	
	  public void loadURL(String url) 
	  {
        String inputValue = url;
        System.out.println (inputValue);

        if (inputValue == null) 
        {
            JOptionPane.showMessageDialog(null, "The given URL is NULL:","Warning", JOptionPane.WARNING_MESSAGE);
        } 
        else 
        {
            // Check if the text value is a URL string.
            URL curUrl = null;

            try 
            {
                // Check if the input string is a local path by checking if it starts
                // with a driver name(on Windows) or root path(on Unix).               
                File[] roots = File.listRoots();

                for (int i = 0; i < roots.length; i++) {
                    if (inputValue.toLowerCase().startsWith(roots[i].toString().toLowerCase())) {
                        File curLocalFile = new File(inputValue);

                        curUrl = curLocalFile.toURL();
                        break;
                    }
                }

                if (curUrl == null) {
                    // Check if the text value is a valid URL.
                    try {
                        curUrl = new URL(inputValue);
                    } catch (MalformedURLException e) {
                            if (inputValue.toLowerCase().startsWith("ftp.")) {
                                curUrl = new URL("ftp://" + inputValue);
                            } else if (inputValue.toLowerCase().startsWith("gopher.")) {
                                curUrl = new URL("gopher://" + inputValue);
                            } 
                            else 
                            {
                                curUrl = new URL("http://" + inputValue);
                            }
                    }
                }
                 loadHistory(curUrl.toExternalForm());        
                webBrowser.setURL(curUrl);

            } 
            catch (MalformedURLException mue) 
            {
                JOptionPane.showMessageDialog(this,"The given URL is not valid:" + inputValue, "Warning",JOptionPane.WARNING_MESSAGE);
            }                
        }
    }
    
    //saving history of web browser in History.txt
    public void loadHistory(String url)
    {
    	try
		{
			File file =new File("History.txt");
		
			FileWriter fw = new FileWriter(file,true);
			BufferedWriter br = new BufferedWriter(fw);
			Date d = new Date();
			br.write(d + "#" + url + "\n");
			br.flush();
			br.close();
						
			
			
		}
		catch(Exception e)
		{
			System.out.println (e.getMessage());
		}
    	
    }
    
    
	//creating object of web browser
	public static void main(String args[])
	{
		NewBrowser newwebBrowser = new NewBrowser();
	}
	
	
	//new class toppanel to set proper look to browser
	class Toppanel extends JPanel//Toppanel class inherits the data from Jpanel
	{
		JLabel lbladdress;
		
		public Toppanel()
		
		{
			
			//declaring the components and setting images to them for proper look and feel of  browser
			lbladdress = new JLabel("Address ");
			
			txtfaddress = new JTextField(60);
			
			btnBack = new JButton("Back",new ImageIcon(getClass().getResource("images/Back.png")));
			btnBack.setToolTipText("Back");
			btnForward = new JButton("Forward",new ImageIcon(getClass().getResource("images/Forward.png")));
			btnForward.setToolTipText("Forward");
			btnRefresh = new JButton("Refresh",new ImageIcon(getClass().getResource("images/Reload.png")));
			btnForward.setToolTipText("Refresh");
			btnGo = new JButton("Go",new ImageIcon(getClass().getResource("images/Go.gif")));
			btnGo.setToolTipText("Load Page");
			btnHome = new JButton("Home",new ImageIcon(getClass().getResource("images/home-icon.jpg")));
			btnHome.setToolTipText("Home");
			btnStop = new JButton("Stop",new ImageIcon(getClass().getResource("images/Stop.png")));
			btnStop.setToolTipText("Stop");
			add(btnBack);
			add(btnForward);
			add(btnRefresh);
			add(btnHome);
			add(btnStop);
			add(lbladdress);
			add(txtfaddress);
			add(btnGo);
			
		}
		
	}
	//new class Centerpanel to view web pages
	class CenterPanel extends JPanel
	{
		
    	public CenterPanel()
		{
			setLayout(new BorderLayout());
			add(webBrowser,BorderLayout.CENTER);
			//new GridLayout(1,1);
			//Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			//d.height = d.height-140;
			//d.width = d.width -20;
			//webBrowser.setPreferredSize(d);
			//add(webBrowser);
			
		}
	}
	
	class BottomPanel extends JPanel
	{
		public BottomPanel()
		{
			setLayout(new BorderLayout());
			lblstatus1 = new JLabel("a");
			lblstatus2 = new JLabel("Internet        ");
			add(lblstatus1,BorderLayout.WEST);
			add(lblstatus2,BorderLayout.EAST);
			
			
			
		}
	}
	
	
	
		
			
}