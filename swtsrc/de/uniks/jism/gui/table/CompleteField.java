package de.uniks.jism.gui.table;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.jface.dialogs.PopupDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import swing2swt.layout.BorderLayout;

public class CompleteField  extends PopupDialog implements Listener {
	protected static final int POPUP_OFFSET = 3;
	protected static final int HEIGHTINFOFIELD = 20;
	protected Collection<?> collection;
	protected Control control;
	private Label lblImage;
	protected Composite composite;
	private Image image;
	protected boolean closeWhenMouseExit=false;
	private Timer timer=new Timer();
	private TimerTask timerTask=null;

	/**
	 * @wbp.parser.constructor
	 */
	public CompleteField(Control control, Collection<?> collection) {

		super(control.getShell(), SWT.ON_TOP, false, false, false,
				false, false, null, "infotextplaceholder");

		this.control = control;

		this.collection = collection;
	}

	public CompleteField(Shell shell) {
		super(shell, SWT.ON_TOP, false, false, false,
				false, false, null, "infotextplaceholder");
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new BorderLayout());
		
		lblImage = new Label(composite, SWT.NONE);
		lblImage.setText("New Label");
		
		
		addListeners(lblImage);
	
		return composite;

	}
	public void open(String file, String infoText, int x, int y){
		super.open();

		Display myDisplay=Display.getDefault();
		FileInputStream stream=null;
		try {
			File fileInput = new File(file);
			if(fileInput.exists()){
				stream = new FileInputStream(fileInput);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if(stream!=null){
			image = new Image (myDisplay, stream);
			if(lblImage!=null){
				lblImage.setImage(image);
			}
			setBounds(x, y, image.getBounds().width, image.getBounds().height+HEIGHTINFOFIELD);
		}else{
			this.close();
			return;
		}
		this.setInfoText(infoText);
	}
	
	public void setBounds(int x, int y, int width, int height){
		this.getShell().setBounds(x, y, width, height);
	}
	
	protected void addListeners(Control parent) {
		parent.addListener(SWT.MouseExit, this);
		parent.addListener(SWT.MouseEnter, this);
//		parent.addListener(SWT.MouseHover, this);
	}
	
	public void enableTimer(int timeout){
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				runTask();
			}
		}, timeout);
	}

	protected void runTask() {
		Display display=Display.getDefault();
		display.asyncExec(new Runnable() {
			@Override
			public void run() {
				CompleteField.this.close();
			}
		});
	}
	

	@Override
	public void handleEvent(Event event) {
		if(event.type==SWT.MouseExit){
			if(closeWhenMouseExit){
				if(timerTask==null){
					timerTask = new TimerTask() {
						@Override
						public void run() {
							timerTask=null;
							runTask();
						}
					};
					timer.schedule(timerTask, 500);
				}
			}
		}else if(event.type==SWT.MouseEnter){
			if(timerTask!=null){
				timerTask.cancel();
				timerTask=null;
			}
		}
	}
}
