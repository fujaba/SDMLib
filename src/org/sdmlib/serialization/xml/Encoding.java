package org.sdmlib.serialization.xml;

import java.util.Collection;

import org.sdmlib.serialization.EntityUtil;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.XMLEntityCreator;

public class Encoding {
	public static final String ID="id";
	private XMLIdMap parent;

	public Encoding(XMLIdMap parent){
		this.parent=parent;
	}

	public XMLEntity encode(Object entity) {
		SendableEntityCreator createrProtoTyp = parent.getCreatorClass(entity);
		if (createrProtoTyp == null) {
			return null;
		} 
		XMLEntity xmlEntity=new XMLEntity();
		
		if(createrProtoTyp instanceof XMLEntityCreator){
			XMLEntityCreator xmlCreater=(XMLEntityCreator) createrProtoTyp;
			if(xmlCreater.getTag()!=null){
				xmlEntity.setTag(xmlCreater.getTag());
			}else{
				xmlEntity.setTag(entity.getClass().getName());
			}
		}else{
			xmlEntity.setTag(entity.getClass().getName());
		}
		String[] properties = createrProtoTyp.getProperties();
		Object referenceObject = createrProtoTyp.getSendableInstance(true);
		
		if(parent.isId()){
			xmlEntity.put(ID, parent.getId(entity));
		}
		
		if (properties != null) {
			for (String property : properties) {
				Object value = createrProtoTyp.getValue(entity, property);
				if (value != null) {
					boolean encoding=parent.isSimpleCheck();
					if(!encoding){
						Object refValue = createrProtoTyp.getValue(referenceObject,
								property);
						encoding=!value.equals(refValue);
					}
					if(encoding){
						if (property.startsWith(XMLIdMap.ENTITYSPLITTER)) {
							parserChild(xmlEntity, property, value);
						}else{
							if (value instanceof Collection<?>) {
								for(Object item : (Collection<?>)value){
									xmlEntity.addChild(encode(item)); 
								}
								
							}else{
								SendableEntityCreator valueCreater = parent.getCreatorClass(value);
								if (valueCreater != null) {
									xmlEntity.addChild(encode(value));
								} else {
									xmlEntity.put(property, value);
								}
							}
						}
					}
				}
			}
		}
		return xmlEntity;
	}
	
	private XMLEntity parserChild(XMLEntity parent,
			String property, Object value) {
		
		if(property.startsWith(XMLIdMap.ENTITYSPLITTER)){
			int pos = property.indexOf(XMLIdMap.ENTITYSPLITTER, 1);
			if(pos<0){
				pos = property.indexOf(XMLIdMap.ATTRIBUTEVALUE, 1);
			}
			String label;
			String newProp="";
			if(pos>0){
				label=property.substring(1,pos);
				newProp=property.substring(pos);
			}else{
				label=property.substring(1);
			}
			if(label.length()>0){
				XMLEntity child = parent.getChild(label);
				if(child==null){
					child=new XMLEntity(label);
					parserChild(child, newProp, value);
					parent.addChild(child);
				}else{
					parserChild(child, newProp, value);
				}
				return child;
			}
		}else if(property.startsWith(XMLIdMap.ATTRIBUTEVALUE)){
			parent.put(property.substring(1), EntityUtil.valueToString(value, true, parent));
		}else if("".equals(property)){
			parent.setValue(EntityUtil.valueToString(value, true, parent));
		}
		return null;
	}
}
