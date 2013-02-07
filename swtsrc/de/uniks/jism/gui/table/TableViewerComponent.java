package de.uniks.jism.gui.table;
/*
Copyright (c) 2013, Stefan Lindel
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
import java.util.EventObject;

import org.eclipse.jface.viewers.ColumnViewerEditor;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationEvent;
import org.eclipse.jface.viewers.ColumnViewerEditorActivationListener;
import org.eclipse.jface.viewers.ColumnViewerEditorDeactivationEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

public class TableViewerComponent extends TableViewer {
	protected ColumnViewerEditor viewerEditor;
	
	public TableViewerComponent(Composite parent, int style) {
		super(parent, style);
		 createViewerEditor().addEditorActivationListener(new ColumnViewerEditorActivationListener() {
			
			@Override
			public void beforeEditorDeactivated(
					ColumnViewerEditorDeactivationEvent event) {
				
			}
			
			@Override
			public void beforeEditorActivated(ColumnViewerEditorActivationEvent event) {
				EventObject sourceEvent = event.sourceEvent;
				if(sourceEvent instanceof MouseEvent){
					MouseEvent ev = (MouseEvent) sourceEvent;
					if(ev.stateMask == SWT.CTRL){
						event.cancel= true;
					}
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
}
