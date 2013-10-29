package org.sdmlib.serialization;

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
import java.util.ArrayList;

import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.BaseEntityList;

public class StringTokener extends Tokener {
	private boolean isString = true;

	@Override
	public String nextString(char quote, boolean allowCRLF) {

		if(quote=='"'){
			if (getCurrentChar() == quote) {
				isString = true;
			} else {
				isString = !isString;
			}
		}else if(getCurrentChar()=='"'){
			isString = true;
			String sub = "";
			StringBuilder sb=new StringBuilder();
			for(;;){
				sub = super.nextString(quote, allowCRLF);
				sb.append(sub);
				if(sub.length()>0&&!sub.endsWith("\"")){
					sb.append(",");
				}else{
					break;
				}
			}
			return sb.toString();
		}
		return super.nextString(quote, allowCRLF);
	}
	
	/**
	 * get the () values
	 * 
	 * @return string of values
	 */
	public String getStringPart(Character start, Character end){
    	int count=1;
    	Character current = null;
    	int pos;
    	if(getCurrentChar()==start){
    		pos=buffer.position();
    		isString = true;
    	}else{
    		isString = !isString;
    		pos=buffer.position()-1;
    	}
		while(!isEnd()){
			current = next();
			if(current==end){
				count--;
				if(count==0){
					next();
					return buffer.substring(pos, buffer.position()-pos);
				}
				continue;
			}
			if(current==start){
				count++;
			}
		}
//FIXME		if(current==end){
//			if(count==1){
//				return buffer.substring(pos, buffer.position()-pos+1);
//			}
//		}
		return null;
    }

	@Override
	public void parseToEntity(BaseEntity entity) {
	}

	@Override
	public void parseToEntity(BaseEntityList entityList) {
	}

	public boolean isString() {
		return isString;
	}

	public void setString(boolean isString) {
		this.isString = isString;
	}
	
	public void setLength(int length){
		this.buffer.withLength(length);
	}
	
	public ArrayList<String> getStringList(){
		ArrayList<String> list=new ArrayList<String>();
		String sub;
		do{
			sub=nextString('"', true);
			if(sub.length()>0){
				if(isString()){
					list.add("\""+sub+"\"");
				}else{
					list.add(sub);
				}
			}
		}while(sub.length()>0);
		return list;
	}
	
	public String getString(String value){
		if(value.startsWith("\"") && value.endsWith("\"")){
			return value.substring(1, value.length()-1);
		}
		return value;
	}
	
	public ArrayList<String> getString(String value, boolean split){
		ArrayList<String> result = new ArrayList<String>();
		if(value.startsWith("\"") && value.endsWith("\"")){
			result.add(value.substring(1, value.length()-1));
			return result;
		}
		String[] values = value.split(" ");
		for(String item :values){
			result.add(item);
		}
		return result;
	}
}
