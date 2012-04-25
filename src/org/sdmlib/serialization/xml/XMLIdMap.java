package org.sdmlib.serialization.xml;

import java.util.HashMap;

import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.XMLEntityCreator;

public class XMLIdMap extends IdMap {
	public static final String ENTITYSPLITTER = "&";
	public static final String ATTRIBUTEVALUE = "?";
	private Encoding encoder;
	private Decoding decoder;
	private HashMap<String, XMLEntityCreator> decoderMap;

	public XMLIdMap() {
		super();
		isId=false;
	}
	
	public XMLIdMap(IdMap parent){
		super(parent);
		isId=false;
	}
	
	public boolean addStopWords(String... stopwords){
		if (decoder == null) {
			decoder = new Decoding(this);
		}
		decoder.addStopWords(stopwords);
		return true;
	}
	public XMLEntity encode(Object value) {
		if (encoder == null) {
			encoder = new Encoding(this);
		}
//		return encoder.encode(value, false, false).toString();
		return encoder.encode(value);
	}

	public XMLEntity encode(Object value, boolean simple, boolean fullEncoding) {
		if (encoder == null) {
			encoder = new Encoding(this);
		}
		return encoder.encode(value);
	}

	public Object decode(String value) {
		if (decoder == null) {
			decoder = new Decoding(this);
		}
		return decoder.decode(value);
	}
	@Override
	public boolean addCreator(SendableEntityCreator createrClass) {
		boolean result=super.addCreator(createrClass);
		if (decoderMap == null) {
			decoderMap = new HashMap<String, XMLEntityCreator>();
		}
		if(createrClass instanceof XMLEntityCreator){
			XMLEntityCreator xmlCreator=(XMLEntityCreator) createrClass;
			if (decoderMap.containsKey(xmlCreator.getTag())) {
				return false;
			}
			decoderMap.put(xmlCreator.getTag(), xmlCreator);
		}else{
			return false;
		}
		return result;
	}

	public XMLEntityCreator getCreatorDecodeClass(String tag) {
		return decoderMap.get(tag);
	}

	public void setId(boolean value) {
		this.isId=value;
	}
}
