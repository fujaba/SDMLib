package de.uniks.jism.gui.text;

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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.LineStyleEvent;
import org.eclipse.swt.custom.LineStyleListener;
import org.eclipse.swt.custom.ST;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.TextChangedEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.sdmlib.serialization.DefaultTextItems;
import org.sdmlib.serialization.Style;
import org.sdmlib.serialization.TextItems;
import de.uniks.jism.gui.ColorSWT;
import de.uniks.jism.gui.GUIElement;
import de.uniks.jism.gui.GUIPosition;
import de.uniks.jism.gui.brush.Brush;
import de.uniks.jism.gui.layout.BorderLayout;
import de.uniks.jism.gui.theme.StyleConstants;
import de.uniks.jism.gui.theme.Theme;

public class TextComponent extends Composite implements GUIElement{
	private Text searchText;
	private StyledText textarea;
	private Label lblSize;
	private Composite parent;
	private ColorSWT colors=new ColorSWT();
	private TextItems items;
	private Composite statusLine;
	
	// TextArea
	private TreeMap<Integer, StyleRange> styles=new TreeMap<Integer, StyleRange>();
	private String keyword;
	private ArrayList<MenueItem> menueItems=new ArrayList<MenueItem>();
	private String fileName;
	private boolean changed;
	private Menu contextMenue;
	private boolean isEnableLineNumber;
	private SWTEventListener updateListener;
	private boolean isChangeRefreshRules=true;
	private TextParser parser;

	public TextComponent(Composite parent, int style) {
		super(parent, SWT.NONE);
		
		setLayout(new BorderLayout(0, 0));
		this.parent=parent;
		textarea = new StyledText(this, SWT.BORDER | SWT.MULTI| SWT.H_SCROLL | SWT.V_SCROLL);
		
		textarea.addLineStyleListener(new LineStyleListener()
		{
		    public void lineGetStyle(LineStyleEvent event)
		    {
		    	if(isEnableLineNumber){
			        //Set the line number
			        event.bulletIndex = textarea.getLineAtOffset(event.lineOffset);
	
			        //Set the style, 12 pixles wide for each digit
			        StyleRange style = new StyleRange();
			        style.metrics = new org.eclipse.swt.graphics.GlyphMetrics(0, 0, Integer.toString(textarea.getLineCount()+1).length()*12);
	
			        //Create and set the bullet
			        event.bullet = new org.eclipse.swt.custom.Bullet(ST.BULLET_NUMBER,style);
		    	}
		        
		        int end=event.lineOffset+event.lineText.length();
		        ArrayList<StyleRange> items=new ArrayList<StyleRange>();
		        for(Iterator<Entry<Integer, StyleRange>> iterator = styles.entrySet().iterator();iterator.hasNext();){
		        	Entry<Integer, StyleRange> item = iterator.next();
		        	if(item.getKey()>end){
		        		break;
		        	}
		        	StyleRange styleItem=item.getValue();
		        	if(styleItem.start>=event.lineOffset){
		        		items.add(styleItem);
		        	}else if(styleItem.start+styleItem.length>=event.lineOffset){
		        		items.add(styleItem);
		        	}
		        	
		        }
		        
		        String line = event.lineText;
		        if(line != null && keyword != null&& !keyword.isEmpty() ){
			        int cursor = -1;
			        while( (cursor = line.indexOf(keyword, cursor+1)) >= 0) {
			        	items.add(getHighlightStyle(event.lineOffset+cursor, keyword.length()));
			        }
		        }
		        event.styles=items.toArray(new StyleRange[items.size()]);
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
				if(!changed){
					changed = true;
					if(updateListener != null){
						updateListener.handleEvent(new TextChangedEvent(textarea.getContent()));	
					}
				}
				refreshStatus();
				if(isChangeRefreshRules){
					TextComponent.this.refreshText(textarea.getText());
//					TextComponent.this.parse(content, brush)
					textarea.redraw();
				}
			}
		});
		
		
		textarea.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent event) {
				for(MenueItem item : menueItems){
					if(isExecuteEvent(item, event.stateMask, event.keyCode)){
						executeMenue(item);
						break;
					}
				}
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				refreshStatus();
			}
			
			private boolean isExecuteEvent(MenueItem item, int stateMask, int keyCode){
				if(item.getKeyState()==stateMask && keyCode == item.getKeyCode()){
					if(item.getLabelTag().equals(DefaultTextItems.CUT)){
						return false;
					}else if(item.getLabelTag().equals(DefaultTextItems.COPY)){
						return false;
					}else if(item.getLabelTag().equals(DefaultTextItems.PASTE)){
						return false;
					}
					return true;
				}
				return false;
			}
		});
		
 		// Add Menue
		this.contextMenue = new Menu(this);
		textarea.setMenu(contextMenue);
	}
	
	
	
	public void enableLineNumber(){
		this.isEnableLineNumber=true;
		this.layout();
	}
	
	public void enableSearchLine(){
		statusLine = new Composite(this, SWT.NONE);
		statusLine.setLayoutData(GUIPosition.SOUTH);
		statusLine.setLayout(new BorderLayout(0, 0));
		
		Label lblSearch = new Label(statusLine, SWT.SHADOW_IN);
		lblSearch.setLayoutData(GUIPosition.WEST);
		lblSearch.setText(getText("Search") + ": ");
		
		searchText = new Text(statusLine, SWT.BORDER);
		searchText.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent arg0) {
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode==13){
					searchText(searchText.getText());
				}
			}
		});
		
		searchText.setLayoutData(GUIPosition.CENTER);
		
		lblSize = new Label(statusLine, SWT.SHADOW_IN);
		lblSize.setLayoutData(GUIPosition.EAST);
	}
	
	public String getText(String label) {
		if(this.items!=null){
			return this.items.getText(label, null, this);
		}
		return label;
	}
	
	public void setItems(TextItems items) {
		this.items = items;
		
        // copy selection
		
		if(this.items!=null){
			enableSearchLine();
			enableLineNumber();
			addContextMenuItem(new MenueItem(DefaultTextItems.CUT, SWT.CTRL, 'x'));
			addContextMenuItem(new MenueItem(DefaultTextItems.COPY, SWT.CTRL, 'c'));
			addContextMenuItem(new MenueItem(DefaultTextItems.PASTE, SWT.CTRL, 'v'));
			addContextMenuItem(new MenueItem(DefaultTextItems.SELECTALL, SWT.CTRL, 'a'));
			addContextMenuItem(new MenueItem(DefaultTextItems.SAVE, SWT.CTRL, 's'));
		}
	}
		
	public void setText(String value) {
		textarea.setText(value);
		refreshStatus();
	}

	public boolean searchText(String searchText){
		int pos = textarea.getText().indexOf(searchText);

		if(pos>=0){
			textarea.setCaretOffset(pos);
			this.keyword = searchText;
			refreshStatus();
			textarea.redraw();
			return true;
		}else{
			this.keyword = null;
		}
		return false;
	}

	public void refreshStatus(){
	    // Show the offset into the file, the total number of characters in the file,
	    // the current line number (1-based) and the total number of lines
		if(lblSize != null){
			lblSize.setText(DefaultTextItems.TEXTSTATUSLINE
					.replaceAll("%POS%", ""+textarea.getCaretOffset())
					.replaceAll("%LEN%", ""+textarea.getCharCount())
					.replaceAll("%LINE%", ""+(textarea.getLineAtOffset(textarea.getCaretOffset()) + 1))
					.replaceAll("%LINECOUNT%", ""+textarea.getLineCount()));
		    statusLine.layout();
		}
	}

	public void updateGUI(String typ, Object value) {
		textarea.append((String) value);
		refreshStatus();		
	}
	
	public void setSimpleText(String value) {
		this.parser = new TextParser();
		textarea.setText(value);
		this.changed = false;

		refreshText(value);
		refreshStatus();
	}
	
	public void setSimpleText(String value, Brush brush, Theme theme) {
		this.parser = new TextParser();
		this.parser.setDefaultColors(theme, brush);

		textarea.setText(value);
		this.changed = false;
		
		refreshText(value);

		refreshStatus();
	}

	public void refreshText(String value){
		this.styles.clear();
		if(this.parser==null){
			setSimpleText(value);
		}
		List<ParsePosition> styleList = this.parser.parse(value);
		if (styleList != null) {
			int lastOffset = 0;
			for (ParsePosition position : styleList) {
				int newOffset = position.getOffset() + position.getLength();
				if (lastOffset > newOffset) {
					continue;
				}
				String key = position.getStyleTyp();
				Style attributeSet = this.parser.getStyle(key);
				lastOffset = newOffset;
				addStyle(new SWTStyle(attributeSet, position, colors));
			}
		}
	}
	
	// Config TextArea
	private void addContextMenuItem(MenueItem item){
		MenuItem contextmenu = new MenuItem (this.contextMenue, SWT.PUSH);
		item.setItem(contextmenu);
		
		contextmenu.setText("&" + getText(item.getLabelTag()) + "\t"+item.getKeyString());
    	contextmenu.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				MenuItem mitem = (MenuItem) arg0.getSource();
				for(MenueItem item : menueItems){
					if(item.getItem()==mitem){
						executeMenue(item);
						break;
					}
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
			}
		});
    	this.menueItems.add(item);
	}
	
	protected StyleRange getHighlightStyle(int offset, int length) {
		ParsePosition pos=new ParsePosition(offset, length, null);
		Style attributeSet = this.parser.getStyle(StyleConstants.STYLE_HIGHTLIGHTED);
		return new SWTStyle(attributeSet, pos, colors);
	}

	public void addStyle(StyleRange style){
		this.styles.put(style.start, style);
	}
	
	public void setKeyWord(String keyword){
		this.keyword = keyword;
	}

	private void executeMenue(MenueItem item){
		if(item.getLabelTag().equals(DefaultTextItems.CUT)){
			this.cut();
		}else if(item.getLabelTag().equals(DefaultTextItems.COPY)){
			this.copy();
		}else if(item.getLabelTag().equals(DefaultTextItems.PASTE)){
			this.paste();
		}else if(item.getLabelTag().equals(DefaultTextItems.SELECTALL)){
			this.selectAll();
		}else if(item.getLabelTag().equals(DefaultTextItems.SAVE)){
			this.saveFile();
		}
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

	public void saveFileAs() {
		FileDialog dlg = new FileDialog(parent.getShell(), SWT.SAVE);
		if (fileName != null) {
			dlg.setFileName(fileName);
		}
		String temp = dlg.open();
		if (temp != null) {
			fileName = temp;

			// The extension may have changed; update the syntax data accordingly
			saveFile();
		}
	}

	/**
	 * Saves a file
	 */
	public void saveFile() {
		if (fileName == null) {
			saveFileAs();
		} else {
			try {
				// Save the file and update the title bar based on the new file name
				File outputFile = new File(fileName);
				FileOutputStream out = new FileOutputStream(outputFile);
				out.write(textarea.getText().getBytes());
				out.close();
			} catch (IOException e) {
			}
		}
	}
	
	public boolean getChanged(){
		return changed;
	}
	
	public void setListener(SWTEventListener update){
		this.updateListener = update;
	}

	public void setChangeRefreshRules(boolean value) {
		this.isChangeRefreshRules = value;
	}
}
