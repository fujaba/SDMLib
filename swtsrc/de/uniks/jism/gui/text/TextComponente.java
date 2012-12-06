package de.uniks.jism.gui.text;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;

import de.uniks.jism.gui.GUIElement;
import de.uniks.jism.gui.GUIPosition;
import de.uniks.jism.gui.layout.BorderLayout;

public class TextComponente extends Composite implements GUIElement{
	private Text searchText;
	private StyledText textarea;
	private Label lblSize;
	private String keyword;
	private String filename;
	private Composite parent;
	public TextComponente(Composite parent,int style) {
		super(parent, SWT.NONE);
		setLayout(new BorderLayout(0, 0));
		this.parent=parent;
		
		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new BorderLayout(0, 0));
		
		textarea = new StyledText(composite, SWT.BORDER | SWT.MULTI| SWT.H_SCROLL | SWT.V_SCROLL);
		
		textarea.addLineStyleListener(new LineStyleListener() {
		      public void lineGetStyle(LineStyleEvent event) {
		        if(keyword == null || keyword.length() == 0) {
		          event.styles = new StyleRange[0];
		          return;
		        }
		        
		        String line = event.lineText;
		        int cursor = -1;
		        
		        LinkedList<StyleRange> list = new LinkedList<StyleRange>();
		        while( (cursor = line.indexOf(keyword, cursor+1)) >= 0) {
		          list.add(getHighlightStyle(event.lineOffset+cursor, keyword.length()));
		        }
		        
		        event.styles = (StyleRange[]) list.toArray(new StyleRange[list.size()]);
		      }
		    });
		textarea.addMouseListener(new MouseListener() {
			@Override
			public void mouseDoubleClick(org.eclipse.swt.events.MouseEvent arg0) {
			}
			@Override
			public void mouseDown(org.eclipse.swt.events.MouseEvent arg0) {
			}
			@Override
			public void mouseUp(org.eclipse.swt.events.MouseEvent arg0) {
				refreshStatus();				
			}
			});

		textarea.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				refreshStatus();		
			}
		});
		textarea.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(arg0.stateMask==SWT.CTRL){
					if(arg0.keyCode=='x'){
						cut();
					}else if(arg0.keyCode=='c'){
						copy();
					}else if(arg0.keyCode=='v'){
						paste();
					}else if(arg0.keyCode=='a'){
						selectAll();
					}else if(arg0.keyCode=='s'){
						saveFileAs();
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				refreshStatus();
			}
		});
		Menu contextmenu = new Menu(textarea);
		textarea.setMenu(contextmenu);
	        
        // copy selection
		addMenuItem(contextmenu, "&Cut Selection\tCtrl+X");
		addMenuItem(contextmenu, "&Copy Selection\tCtrl+C");
		addMenuItem(contextmenu, "&Paste Selection\tCtrl+V");
		addMenuItem(contextmenu, "&Select all\tCtrl+A");
		addMenuItem(contextmenu, "&Save As\tCtrl+S");
		
		
		Composite composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayoutData(GUIPosition.SOUTH);
		composite_1.setLayout(new BorderLayout(0, 0));
		
		Label lblSearch = new Label(composite_1, SWT.SHADOW_IN);
		lblSearch.setLayoutData(GUIPosition.WEST);
		FormData fd_lblSearch = new FormData();
		fd_lblSearch.top = new FormAttachment(0, 8);
		fd_lblSearch.left = new FormAttachment(0, 5);
		lblSearch.setText("Search:");
		
		searchText = new Text(composite_1, SWT.BORDER);
		searchText.addKeyListener(new KeyListener() {
			
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode==13){
					searchText();
				}
			}
		});
		
		FormData fd_text_1 = new FormData();
		fd_text_1.top = new FormAttachment(0, 5);
		fd_text_1.left = new FormAttachment(0, 48);
		searchText.setLayoutData(fd_text_1);
		
		lblSize = new Label(composite_1, SWT.SHADOW_IN);
		lblSize.setLayoutData(GUIPosition.EAST);
		FormData fd_lblSize = new FormData();
		fd_lblSize.top = new FormAttachment(0, 8);
		fd_lblSize.left = new FormAttachment(0, 200);
		lblSize.setText("XXXXXXXXXXXXXXXXXXXXXX");
	}
	private void addMenuItem(Menu contextmenu, String label){
		MenuItem contextmenu_copy_item = new MenuItem (contextmenu, SWT.PUSH);
    	contextmenu_copy_item.setText (label);
    	contextmenu_copy_item.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MenuItem item=(MenuItem) arg0.getSource();
				executeMenu(item.getText());
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
	}
	public void executeMenu(String label){
		if(label.equals("&Cut Selection\tCtrl+X")){
			cut();
		}else if(label.equals("&Copy Selection\tCtrl+C")){
			copy();
		}else if(label.equals("&Paste Selection\tCtrl+V")){
			paste();
		}else if(label.equals("&Select all\tCtrl+A")){
			selectAll();
		}else if(label.equals("&Save As\tCtrl+S")){
			saveFileAs();
		}
	}
	
	public StyledText getTextArea(){
		return textarea;
	}
	
	private StyleRange getHighlightStyle(int startOffset, int length) {
	    StyleRange styleRange = new StyleRange();
	    styleRange.start = startOffset;
	    styleRange.length = length;
	    styleRange.background = getParent().getDisplay().getSystemColor(SWT.COLOR_YELLOW);
	    return styleRange;
	  }
	
	public void setText(String value) {
		textarea.setText(value);
		refreshStatus();
	}

	public void searchText(){
		int pos = textarea.getText().indexOf(searchText.getText());
		this.keyword=searchText.getText();
		String text=textarea.getText();
		textarea.setText("\n"+text);
		textarea.setText(text);

		if(pos>=0){
			textarea.setCaretOffset(pos);
			refreshStatus();
		}
	}
	
	
	
	public void refreshStatus(){
	    // Show the offset into the file, the total number of characters in the
	    // file,
	    // the current line number (1-based) and the total number of lines
	    StringBuffer buf = new StringBuffer();
	    buf.append("Pos: ");
	    buf.append(textarea.getCaretOffset());
	    buf.append(" / ");
	    buf.append(textarea.getCharCount());
	    buf.append("  Line: ");
	    buf.append(textarea.getLineAtOffset(textarea.getCaretOffset()) + 1);
	    buf.append(" / ");
	    buf.append(textarea.getLineCount());
	    lblSize.setText(buf.toString());
//		lblSize.setText("Zeile " + (textarea.getCaretOffset() + 1));
	}
	
	/**
	   * Cuts the current selection to the clipboard
	   */
	  public void cut() {
		  textarea.cut();
	  }

	  /**
	   * Copies the current selection to the clipboard
	   */
	  public void copy() {
		  textarea.copy();
	  }

	  /**
	   * Pastes the clipboard's contents
	   */
	  public void paste() {
		  textarea.paste();
	  }

	  /**
	   * Selects all the text
	   */
	  public void selectAll() {
		  textarea.selectAll();
	  }
	  private void saveFileAs() {
		  FileDialog dlg = new FileDialog(parent.getShell());
		    if (filename != null) {
		      dlg.setFileName(filename);
		    }
		    String temp = dlg.open();
		    if (temp != null) {
		      filename = temp;

		      // The extension may have changed; update the syntax data accordingly
		      saveFile();
		    }			
	  }
	  /**
	   * Saves a file
	   */
	  public void saveFile() {
	    if (filename == null) {
	      saveFileAs();
	    } else {
	      try {
	        // Save the file and update the title bar based on the new file name
	    	  File outputFile = new File(filename);
	    	    FileOutputStream out = new FileOutputStream(outputFile);
	    	    out.write(textarea.getText().getBytes());
	    	    out.close();
	      } catch (IOException e) {
//	        showError(e.getMessage());
	      }
	    }
	  }
	public void updateGUI(String typ, Object value) {
		textarea.append((String) value);
		refreshStatus();		
	}
}
