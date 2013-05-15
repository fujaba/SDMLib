package de.uniks.jism.gui;

/*
 Json Id Serialisierung Map
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:
 1. Redistributions of source code must retain the above copyright
 notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
 notice, this list of conditions and the following disclaimer in the
 documentation and/or other materials provided with the distribution.
 3. All advertising materials mentioning features or use of this software
 must display the following acknowledgement:
 This product includes software developed by Stefan Lindel.
 4. Neither the name of contributors may be used to endorse or promote products
 derived from this software without specific prior written permission.

 THE SOFTWARE 'AS IS' IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL STEFAN LINDEL BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.imageio.ImageIO;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public abstract class MasterShell extends Shell {
public static final String SECOND_MAC="secondmac";
	public static final String SECOND="second";
	protected String captionPrefix;
	protected boolean isInit=false;
	private String icon;
	protected Os os=new Os();


	public MasterShell() {
		this(Display.getDefault());
	}
	
	public MasterShell(Shell parent, int param){
		super(parent, param);
	}
	
	public MasterShell(Display display) {
		super(display);
		initShell();
	}

	public MasterShell(Display display, int param) {
		super(display, param);
		initShell();
	}
	
	protected abstract void createContents();
	
	private void initShell(){
		super.setText(getCaption());
		
		if(getIcon()!=null){
			InputStream stream = ClassLoader.getSystemResourceAsStream(getIcon());
			setImage(new Image(getDisplay(), stream));
		}
	}
	
	protected String getCommandHelp(){
		return "Help for the Commandline - "+ getCaption()+"\n\n";
	}
	
	public boolean openSecond(String[] args){
		Os os = new Os();
		String fileName = os.getFilename().toLowerCase();

		if (!fileName.endsWith(".jar") && !fileName.endsWith(".exe")){
			// ECLIPSE
			initShell();
		}else if("UTF-8".equals(System.getProperty("file.encoding"))||"UTF8".equals(System.getProperty("file.encoding"))){
		}else{
			// NOT Eclipse and not UTF-8
			// try to load data from config file
			if (args == null || args.length < 1) {
				if (os.isMac()) {
					args = new String[] { SECOND_MAC };
				} else {
					args = new String[] { SECOND };
				}
			}
	
			if ("-?".equalsIgnoreCase(args[0])) {
				getCommandHelp();
				return false;
			}

			ArrayList<String> params=new ArrayList<String>();
			if ("debug".equalsIgnoreCase(args[0])) {
				System.out.println("DEBUG-MODE");
				params.add("-Xms512m");
				params.add("-Xmx1024m");
				params.add("-Xdebug");
				params.add("-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=4223");
			} else if (SECOND.equalsIgnoreCase(args[0])) {
				System.out.println("STANDARD-MODE");
			} else if (SECOND_MAC.equalsIgnoreCase(args[0])) {
				System.out.println("STANDARD-MODE-MAC");
				params.add("-XstartOnFirstThread ");
			}
			params.add("-Dfile.encoding=UTF8");
			params.add("-jar");
			params.add(fileName);
			params.add(0, "\""+ System.getProperty("java.home").replace("\\", "/")+ "/bin/java\"");
			try {
				ProcessBuilder processBuilder = new ProcessBuilder( params );
				processBuilder.start();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		initFromParams(args);
		
		// Everything ok
		Display display = Display.getDefault();
				
		display.syncExec(new Runnable() {
			@Override
			public void run() {
				MasterShell.this.open();
			}
		});
		return true;
	}
	
	public void initFromParams(String[] params){
		
	}

	public void open() {
		open(!os.isEclipse());
	}
	public void open(boolean catchError) {
		try {
			if(isInit){
				return;
			}
			createContents();
			isInit=true;
			if (isDisposed()) {
				return;
			}
			super.open();
			layout();
			while (!isDisposed()) {
				if (!getDisplay().readAndDispatch()) {
					getDisplay().sleep();
				}
			}
		} catch (Exception e) {
			if (!catchError) {
				throw new RuntimeException(e);
			}
			saveException(e, true, null);
		}
	}

	public void saveException(Exception e, boolean saveFull, Object extra) {
		// Generate Error.txt
		String path = "errors/";
		GregorianCalendar temp = new GregorianCalendar();
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String prefixName = formatter.format(temp.getTime()) + "_";
		writeErrorFile(prefixName + "error.txt", path, e, extra);

		// Save Screenshot
		BufferedImage bi;
		try {
			bi = new Robot().createScreenCapture(new Rectangle(Toolkit
					.getDefaultToolkit().getScreenSize()));
			ImageIO.write(bi, "jpg", new File(path + prefixName + "Full.jpg"));
			if (saveFull) {
				bi = new Robot().createScreenCapture(new java.awt.Rectangle(
						this.getBounds().x, this.getBounds().y, this
								.getBounds().width, this.getBounds().height));
				ImageIO.write(bi, "jpg",
						new File(path + prefixName + "App.jpg"));
			}
		} catch (IOException e1) {
		} catch (HeadlessException e1) {
		} catch (AWTException e1) {
		}
	}
	
	protected boolean writeErrorFile(String fileName, String filepath, Exception e, Object extra){
		boolean success;
		try {
			filepath=createDir(filepath);
			String fullfilename=filepath+"/"+fileName;
			File file=new File(fullfilename);
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream networkFile = new FileOutputStream(filepath+"/"+fileName);
			
			PrintStream ps = new PrintStream( networkFile );
			ps.println("Error: "+e.getMessage());
			if(extra!=null){
				ps.println("Extra: "+extra.toString());
			}
			ps.println("Thread: "+Thread.currentThread().getName());
			ps.println("------------ SYSTEM-INFO ------------");
			printProperty(ps, "java.class.version");
			printProperty(ps, "java.runtime.version");
			printProperty(ps, "java.specification.version");
			printProperty(ps, "java.version");
			printProperty(ps, "os.arch");
			printProperty(ps, "os.name");
			printProperty(ps, "os.version");
			printProperty(ps, "user.dir");
			printProperty(ps, "user.home");
			printProperty(ps, "user.language");
			printProperty(ps, "user.name");
			printProperty(ps, "user.timezone");
			ps.println();
			e.printStackTrace(ps);
			ps.close();
			success=true;
		} catch (FileNotFoundException exception) {
			success=false;
		} catch (IOException exception) {
			success=false;
		}
		return success;
	}
	
	private void printProperty(PrintStream ps, String property){
		ps.println(property+": "+System.getProperty(property));
	}
	
	protected String createDir(String path){
		File dirPath = new File(path);
		dirPath = new File(dirPath.getPath());
		if(!dirPath.exists()){
			if(dirPath.mkdirs()){
				return path;
			}
		}else{
			return path;
		}
		return null; 
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public static boolean checkSystemTray(Shell window) {
		if (!SystemTray.isSupported()) {
			MessageBox messageDialog = new MessageBox(window, SWT.ICON_ERROR);
			messageDialog.setText(window.getText());
			messageDialog.setMessage("SystemTray is not supported");
			messageDialog.open();
			return false;
		}
		return true;
	}
	
	public String getCaption() {
		String caption="";
		if(captionPrefix!=null){
			caption =captionPrefix+" ";
		}
		return caption+getVersion()+" (" + System.getProperty("file.encoding") + " - " + System.getProperty("sun.arch.data.model") + "-Bit)";
	}
	
	protected String getVersion() {
		String result = MasterShell.class.getPackage()
				.getImplementationVersion();

		if (result == null) {
			result = "0.42.DEBUG";
		}

		return result;
	}

	public void setText(String value) {
		this.captionPrefix=value;
		super.setText(getCaption());
	}
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
