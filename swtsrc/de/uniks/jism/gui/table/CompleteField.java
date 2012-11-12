package de.uniks.jism.gui.table;
/*
Copyright (c) 2012, Stefan Lindel
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

THIS SOFTWARE IS PROVIDED BY STEFAN LINDEL ''AS IS'' AND ANY
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
	protected Label lblTop;
	protected Composite composite;
	private Image image;
	protected boolean closeWhenMouseExit=true;
	private TimerTask timerTask=null;
	protected String labelText="New Label";
	private Timer timer;

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
		
		lblTop = new Label(composite, SWT.NONE);
		lblTop.setText(labelText);
		
		
		addListeners(lblTop);
		return composite;

	}
	public void open(String infoText, int x, int y){
		super.open();
		setBounds(x, y, 100, 100);
		this.setInfoText(infoText);
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
			if(lblTop!=null){
				lblTop.setImage(image);
			}
			setBounds(x, y, image.getBounds().width, image.getBounds().height+HEIGHTINFOFIELD);
		}else{
			this.close();
			return;
		}
		this.setInfoText(infoText);
	}
	
	
	public void setLabelText(String value){
		this.labelText=value;
	}
	
	public void setBounds(int x, int y, int width, int height){
		this.getShell().setBounds(x, y, width, height);
	}
	
	protected void addListeners(Control parent) {
		parent.addListener(SWT.MouseExit, this);
		parent.addListener(SWT.MouseEnter, this);
//		parent.addListener(SWT.MouseHover, this);
	}
	public Timer getTimer(){
		if(timer==null){
			timer=new Timer();
		}
		return timer;
	}
	public void enableTimer(int timeout){
		getTimer().schedule(new TimerTask() {
			
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
					getTimer().schedule(timerTask, 500);
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
