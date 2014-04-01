package org.sdmlib.serialization.gui.table;

/*
NetworkParser
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
import java.util.List;

import org.sdmlib.serialization.IdMapEncoder;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public interface TableComponentInterface {
	public IdMapEncoder getMap();
	public boolean addItem(Object item);
	public boolean removeItem(Object item);
	public void addUpdateListener(Object list);
	public String getProperty();
	public TableColumnInterface getColumn(Column column);
	public void init();
	public void refreshViewer();
	public List<Object> getItems(boolean all);
	public TableComponentInterface withSearchProperties(String... searchProperties);
	public TableComponentInterface withColumn(Column column);
	public TableComponentInterface withList(TableList item);
	public TableComponentInterface withList(Object item, String property);
	public TableComponentInterface createFromCreator(SendableEntityCreator creator, boolean edit);
	public TableComponentInterface withScrollPosition(double pos);
	public SendableEntityCreator getCreator(Object entity);
}
