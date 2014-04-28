package org.sdmlib.serialization.xml;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Licensed under the EUPL, Version 1.1 or (as soon they
 will be approved by the European Commission) subsequent
 versions of the EUPL (the "Licence");
 You may not use this work except in compliance with the Licence.
 You may obtain a copy of the Licence at:

 http://ec.europa.eu/idabc/eupl5

 Unless required by applicable law or agreed to in
 writing, software distributed under the Licence is
 distributed on an "AS IS" basis,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 express or implied.
 See the Licence for the specific language governing
 permissions and limitations under the Licence.
 */
import java.util.Collection;
import java.util.HashSet;

import org.sdmlib.serialization.Filter;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.IdMapEncoder;
import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.interfaces.BaseEntity;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreatorXML;
import org.sdmlib.serialization.xml.creator.XMLGrammar;
import org.sdmlib.serialization.xml.creator.XSDEntityCreator;

public class XMLSimpleIdMap extends IdMap
{
   /** The Constant ENDTAG. */
   public static final char ENDTAG = '/';

   /** The Constant ITEMEND. */
   public static final char ITEMEND = '>';

   /** The Constant ITEMSTART. */
   public static final char ITEMSTART = '<';

   /** The Constant SPACE. */
   public static final char SPACE = ' ';

   /** The stopwords. */
   protected HashSet<String> stopwords = new HashSet<String>();

   /**
    * Instantiates a new XML id map.
    */
   public XMLSimpleIdMap()
   {
      super();
      init();
   }

   /**
    * Instantiates a new XML id map.
    */
   protected void init()
   {
      this.stopwords.add("?xml");
      this.stopwords.add("!--");
      this.stopwords.add("!DOCTYPE");
   }

   @Override
   public Object decode(BaseEntity value)
   {
      return decode((XMLTokener) new XMLTokener().withText(value.toString()),
         null);
   }

   public Object decode(XMLTokener tokener, XMLGrammar factory)
   {
      if (factory == null)
      {
         factory = new XSDEntityCreator();
      }
      while (!tokener.isEnd())
      {
         if (tokener.stepPos("" + ITEMSTART, false, false))
         {
            XMLEntity item = getEntity(factory, tokener);
            if (item != null)
            {
               return parse(item, tokener, factory);
            }
         }
      }
      return null;
   }

   /**
    * Read Json Automatic create JsonArray or JsonObject
    * 
    * @return the object
    */
   public Object decode(String value)
   {
      if (value.startsWith("<"))
      {
         return decode(getPrototyp().getNewArray().withValue(value));
      }
      return decode(getPrototyp().withValue(value));
   }

   @Override
   public XMLEntity encode(Object value)
   {
      return encode(value, filter.cloneObj());
   }

   @Override
   public XMLEntity encode(Object entity, Filter filter)
   {
      XMLEntity xmlEntity = new XMLEntity();
      SendableEntityCreator createrProtoTyp = getCreatorClass(entity);
      if (createrProtoTyp == null)
      {
         return null;
      }
      if (createrProtoTyp instanceof SendableEntityCreatorXML)
      {
         SendableEntityCreatorXML xmlCreater = (SendableEntityCreatorXML) createrProtoTyp;
         if (xmlCreater.getTag() != null)
         {
            xmlEntity.setTag(xmlCreater.getTag());
         }
         else
         {
            xmlEntity.setTag(entity.getClass().getName());
         }
      }
      else
      {
         xmlEntity.setTag(entity.getClass().getName());
      }
      String[] properties = createrProtoTyp.getProperties();
      if (properties != null)
      {
         Object referenceObject = createrProtoTyp.getSendableInstance(true);
         for (String property : properties)
         {
            Object value = createrProtoTyp.getValue(entity, property);
            if (value != null)
            {
               Object refValue = createrProtoTyp.getValue(referenceObject,
                  property);
               boolean encoding = !value.equals(refValue);
               if (encoding)
               {
                  if (value instanceof Collection<?>)
                  {
                     for (Object item : (Collection<?>) value)
                     {
                        xmlEntity.addChild(encode(item));
                     }
                  }
                  else
                  {
                     SendableEntityCreator valueCreater = getCreatorClass(value);
                     if (valueCreater != null)
                     {
                        xmlEntity.addChild(encode(value));
                     }
                     else
                     {
                        xmlEntity.put(property, value);
                     }
                  }
               }
            }
         }
      }
      return xmlEntity;
   }

   /**
    * Find tag.
    * 
    * @param entity2
    * 
    * @param prefix
    *           the prefix
    * @param tag
    *           the tag
    * @param styleFormat
    * @param styleFormatCreator
    * @return the object
    */
   protected Object parse(XMLEntity entity, XMLTokener tokener,
         XMLGrammar grammar)
   {
      if (entity != null)
      {
         // Parsing attributes
         char myChar = tokener.getCurrentChar();
         while (myChar != ITEMEND)
         {
            if (myChar == SPACE)
            {
               tokener.next();
            }
            int start = tokener.position();
            if (tokener.stepPos("=>", false, false))
            {
               myChar = tokener.getCurrentChar();
               if (myChar == '=')
               {
                  String key = tokener.substring(start, -1);
                  tokener.skip(2);
                  start = tokener.position();
                  if (tokener.stepPos("\"", false, true))
                  {
                     String value = tokener.substring(start, -1);
                     tokener.next();
                     grammar.setValue(entity, key, value, IdMapEncoder.NEW);
                  }
               }
            }
            else
            {
               break;
            }
         }

         // Add to StackTrace
         tokener.withStack(new ReferenceObject().withProperty(entity.getTag())
            .withEntity(entity));

         // Parsing next Element
         if (tokener.stepPos("/>", false, false))
         {
            if (tokener.getCurrentChar() == '/')
            {
               tokener.popStack();
               tokener.next();
               String tag = tokener.getNextTag();
               grammar.endChild(tag);
               // skipEntity();
               return entity;
            }

            char quote = (char) ITEMSTART;
            // Skip >
            tokener.next();
            String strvalue = tokener.nextString(quote, true);

            // BACK TO <
            tokener.back();
            strvalue = strvalue.trim();
            XMLEntity newTag;
            if (tokener.getCurrentChar() == ITEMSTART)
            {
               // show next Tag
               Object child;
               do
               {
                  boolean saveValue = true;
                  do
                  {
                     newTag = getEntity(grammar, tokener);
                     if (newTag == null)
                     {
                        entity.setValue(strvalue);
                        tokener.popStack();
                        tokener.skipEntity();
                        return entity;
                     }
                     if (newTag.getTag().isEmpty())
                     {
                        if (saveValue)
                        {
                           entity.setValue(newTag.getValue());
                        }
                        tokener.skipEntity();
                        newTag = getEntity(grammar, tokener);
                        if (newTag == null)
                        {
                           tokener.popStack();
                           tokener.skipEntity();
                        }
                        return entity;
                     }
                     if (grammar.parseChild(entity, newTag, tokener))
                     {
                        // Skip >
                        saveValue = false;
                        tokener.next();
                     }
                     else
                     {
                        break;
                     }
                  }
                  while (newTag != null);
                  child = parse(newTag, tokener.withPrefix(""), grammar);
                  if (child != null && child instanceof XMLEntity)
                  {
                     grammar.addChildren(entity, (XMLEntity) child);
                  }
               }
               while (child != null);
            }
         }
      }
      return entity;
   }

   /**
    * Gets the entity.
    * 
    * @param start
    *           the start
    * @return the entity
    */
   protected XMLEntity getEntity(XMLGrammar factory, XMLTokener tokener)
   {
      XMLEntity entity;
      if (factory != null)
      {
         Object newObj = factory.getSendableInstance(false);
         if (newObj instanceof XMLEntity)
         {
            entity = (XMLEntity) newObj;
         }
         else
         {
            entity = new XMLEntity();
         }
      }
      else
      {
         entity = new XMLEntity();
      }
      String tag = null;
      boolean isEmpty = true;
      do
      {
         if (tokener.getCurrentChar() != ITEMSTART)
         {
            String strValue = tokener.nextString(ITEMSTART, true);
            if (strValue != null)
            {
               tokener.back();
               strValue = strValue.trim();
               isEmpty = strValue.isEmpty();
            }
            entity.setValue(strValue);
         }
         tag = tokener.getNextTag();
         if (tag != null)
         {
            for (String stopword : this.stopwords)
            {
               if (tag.startsWith(stopword))
               {
                  tokener.stepPos(">", false, false);
                  tokener.stepPos("<", false, false);
                  tag = null;
                  break;
               }
            }
         }
      }
      while (tag == null);
      if (tag.isEmpty() && isEmpty)
      {
         return null;
      }
      entity.setTag(tag);
      return entity;
   }

   @Override
   public XMLEntity getPrototyp()
   {
      return new XMLEntity();
   }

}
