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
import java.util.Comparator;

import org.sdmlib.serialization.interfaces.PeerMessage;

public class TableListComparator implements Comparator<Object>{
	public static final int ASC = 1;
	public static final int DESC = -1;

	private int direction = 1;
	private String column;
	public TableListComparator()
	{
	}
	
	@Override
	public int compare(Object o1, Object o2) {
		return direction*compareValue(o1, o2);
	}
	
	public int compareValue(Object o1, Object o2) {
		PeerMessage p1 = (PeerMessage) o1;
		PeerMessage p2 = (PeerMessage) o2;
		if(column==null){
			if(p1.equals(p2)){
				return 0;
			}
			if(p1.hashCode()<p2.hashCode()){
				return 1;
			}
			return -1;
		}
		
		if(p1.get(column) instanceof String){
			String valueA=(String) p1.get(column);
			String valueB=(String) p2.get(column);
			if(valueA!=null){
				return valueA.compareToIgnoreCase(valueB);
			}else{
				return -1;
			}
		}else if(p1.get(column) instanceof Integer){
			Integer valueA=(Integer) p1.get(column);
			Integer valueB=(Integer) p2.get(column);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}
		}else if(p1.get(column) instanceof Long){
			Long valueA=(Long) p1.get(column);
			Long valueB=(Long) p2.get(column);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}		}else if(p1.get(column) instanceof Boolean){
			Boolean valueA=(Boolean) p1.get(column);
			Boolean valueB=(Boolean) p2.get(column);
			if(valueA!=null){
				return valueA.compareTo(valueB);
			}else{
				return -1;
			}
		}
		return 0;
	}

	public int getDirection() {
		return direction;
	}

	public int setDirection(int direction) {
		this.direction = direction;
		return direction;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
}
