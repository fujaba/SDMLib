package org.sdmlib.serialization.xml;
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
import org.sdmlib.serialization.BaseEntity;
import org.sdmlib.serialization.Entity;
import org.sdmlib.serialization.EntityList;
import org.sdmlib.serialization.Tokener;

public class XMLTokener extends Tokener{

	public XMLTokener(String s) {
		super(s);
	}
	/**
     * Get the next value. The value can be a Boolean, Double, Integer,
     * JSONArray, JSONObject, Long, or String, or the JSONObject.NULL object.
     *
     * @return An object.
     */
	@Override
	public Object nextValue(BaseEntity creator) {
        char c = nextClean();
        
        switch (c) {
        case '"':
        case '\'':
            return nextString(c);
        case '<':
            back();
            Entity element = creator.getNewObject();
            parseToEntity(element);
            return element;
		default:
			break;
        }
    	back();
        return super.nextValue(creator);
    }
	
	@Override
	public void parseToEntity(Entity entity) {
        char c;
        
        if (nextClean() != '<') {
            throw syntaxError("A XML text must begin with '<'");
        }
        if(!(entity instanceof XMLEntity)){
        	throw syntaxError("Parse only XMLEntity");
        }
        XMLEntity xmlEntity=(XMLEntity) entity;
        StringBuffer sb = new StringBuffer();
        c = nextClean();
        while (c >= ' ' && ",:]>/\\\"<;=# ".indexOf(c) < 0) {
            sb.append(c);
            c = next();
        }
        back();
        xmlEntity.setTag(sb.toString());
        XMLEntity child;
        boolean lExit=false;
        while(!lExit){
            c = nextClean();
            if(c==0){
        		lExit=true;
            }else if(c=='>'){
            	if(getCurrentChar()>' '||nextClean()>' '){
            		if(getCurrentChar()=='<'){
                		child = (XMLEntity) xmlEntity.getNewObject();
                		parseToEntity(child);
                		xmlEntity.addChild(child);
            		}else{
            			xmlEntity.setValue(nextString('<'));
            			back();
            		}
            	}
        	}else if(c=='<'){
            	if(next()=='/'){
            		stepPos('>');
            		next();
            		lExit=true;
            	}else{
            		back();
            		back();
            		child = (XMLEntity) xmlEntity.getNewObject();
            		parseToEntity(child);
            		xmlEntity.addChild(child);
            	}
            }else if(c=='/'){
            	next();
            	lExit=true;
            }else{
                back();
                String key = nextValue(xmlEntity).toString();
                if(key!=null){
                	// The key is followed by ':'. We will also tolerate '=' or '=>'.
		            c = nextClean();
		            if (c == '=') {
		                if (next() != '>') {
		                    back();
		                }
		            }
		            xmlEntity.put(key, nextValue(xmlEntity));
	            }
            }
        }		
	}

	@Override
	public void parseToEntity(EntityList entityList) {
		// Do Nothing
	}
}
