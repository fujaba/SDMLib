package de.uniks.jism.gui.table;

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

import java.util.EventObject;
import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import de.uniks.jism.gui.GUIPosition;

public class TableViewerComponent extends TableViewer {
	protected ColumnViewerEditor viewerEditor;
	private TableComponent component;
	private GUIPosition position;
	
	public TableViewerComponent(Composite parent, TableComponent component, int style, GUIPosition position) {
		super(parent, style);
		this.component = component;
		this.position = position;
		setLabelProvider(new TableLabelProvider());

		createViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {
			private String savedValue="";
			private TableItem itemRow;
			private int index;
			@Override
			public void beforeEditorDeactivated(
					ColumnViewerEditorDeactivationEvent event) {
				itemRow.setText(index, savedValue);
			}
			
			@Override
			public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
				EventObject sourceEvent = event.sourceEvent;
				if(sourceEvent instanceof MouseEvent){
					ViewerCell cell = (ViewerCell) event.getSource();
					index = cell.getColumnIndex();
					
					MouseEvent evt=(MouseEvent)sourceEvent; 
					Point pt = new Point(evt.x, evt.y);
					itemRow = getTable().getItem(pt);
					savedValue = itemRow.getText(index);
					itemRow.setText(index, "");
				}else if (event.eventType == ColumnViewerEditorActivationEvent.PROGRAMMATIC){
					ViewerCell cell = (ViewerCell) event.getSource();
					index = cell.getColumnIndex();
//					Point pt = new Point(evt.x, evt.y);
//					item = getTable().getItem(pt);
					savedValue = itemRow.getText(index);
					itemRow.setText(index, "");
				}
			}
			
			@Override
			public void afterEditorDeactivated(ColumnViewerEditorDeactivationEvent event) {
			}
			
			@Override
			public void afterEditorActivated(ColumnViewerEditorActivationEvent event) {
			}
		});
	}
	
	@Override
	protected ColumnViewerEditor createViewerEditor() {
		if(viewerEditor==null){
			viewerEditor = super.createViewerEditor();
		}
		return viewerEditor;
	}
	public TableColumnView executeMouseEvent(MouseEvent event){
		Point pt = new Point(event.x, event.y);
        return  TableViewerComponent.this.component.updatePosition(position, pt);
	}
}
