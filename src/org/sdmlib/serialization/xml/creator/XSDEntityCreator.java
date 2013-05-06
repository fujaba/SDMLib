package org.sdmlib.serialization.xml.creator;

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

import java.util.ArrayList;
import org.sdmlib.serialization.Tokener;
import org.sdmlib.serialization.interfaces.XMLEntityCreator;
import org.sdmlib.serialization.interfaces.XMLGrammar;
import org.sdmlib.serialization.xml.XMLEntity;
import org.sdmlib.serialization.xml.XSDEntity;

public class XSDEntityCreator implements XMLEntityCreator, XMLGrammar{
private String nameSpace;
	private ArrayList<String> privateStack = new ArrayList<String>();
	public static final String[] IGNORETAGS = new String[] {"annotation",
			"documentation", "complextype", "simpletype" };

	/**
	 * @param namespace     the NameSpace for xsd
	 */
	public XSDEntityCreator(String namespace) {
		this.nameSpace = namespace;
	}

	@Override
	public String[] getProperties() {
		return new String[] {XSDEntity.PROPERTY_CHOICE,
				XSDEntity.PROPERTY_SEQUENCE, XSDEntity.PROPERTY_ATTRIBUTE,
				XSDEntity.PROPERTY_MINOCCURS, XSDEntity.PROPERTY_MAXOCCURS };
	}

	@Override
	public Object getSendableInstance(boolean prototyp) {
		return new XSDEntity();
	}

	@Override
	public Object getValue(Object entity, String attribute) {
		return ((XSDEntity) entity).getValue(attribute);
	}

	@Override
	public boolean setValue(Object entity, String attribute, Object value,
			String type) {
		((XSDEntity) entity).put(attribute, value);
		return true;
	}

	@Override
	public String getTag() {
		return nameSpace + ":element";
	}

	@Override
	public boolean parseChild(XMLEntity entity, XMLEntity child, Tokener value) {
		String tag = child.getTag();
		for (String ignoreTag : IGNORETAGS) {
			if (tag.equalsIgnoreCase(nameSpace + ":" + ignoreTag)) {
				return true;
			}
		}
		if (child.getTag().equalsIgnoreCase(
				nameSpace + ":" + XSDEntity.PROPERTY_SEQUENCE)) {
			this.privateStack.add(XSDEntity.PROPERTY_SEQUENCE);
		} else if (child.getTag().equalsIgnoreCase(
				nameSpace + ":" + XSDEntity.PROPERTY_CHOICE)) {
			this.privateStack.add(XSDEntity.PROPERTY_CHOICE);
		}
		return false;
	}

	@Override
	public void addChildren(XMLEntity parent, XMLEntity child) {
		if (this.privateStack.size() > 0) {
			String lastTag = this.privateStack
					.get(this.privateStack.size() - 1);
			if (lastTag.equals(XSDEntity.PROPERTY_CHOICE)) {
				((XSDEntity) parent).setValue(XSDEntity.PROPERTY_CHOICE, child);
			} else if (lastTag.equals(XSDEntity.PROPERTY_SEQUENCE)) {
				((XSDEntity) parent).setValue(XSDEntity.PROPERTY_SEQUENCE,
						child);
			}

		}
		parent.addChild(child);
	}

	@Override
	public void endChild(String tag) {
		this.privateStack.remove(this.privateStack.size() - 1);
	}

}
