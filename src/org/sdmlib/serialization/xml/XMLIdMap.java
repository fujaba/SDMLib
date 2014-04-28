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
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.sdmlib.serialization.AbstractMap;
import org.sdmlib.serialization.EntityUtil;
import org.sdmlib.serialization.Filter;
import org.sdmlib.serialization.IdMapEncoder;
import org.sdmlib.serialization.ReferenceObject;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.interfaces.SendableEntityCreatorXML;
import org.sdmlib.serialization.logic.BooleanCondition;
import org.sdmlib.serialization.xml.creator.XMLEntityCreator;
import org.sdmlib.serialization.xml.creator.XMLGrammar;

/**
 * The Class XMLIdMap.
 */

public class XMLIdMap extends XMLSimpleIdMap
{
   /** The Constant ENTITYSPLITTER. */
   public static final String ENTITYSPLITTER = "&";

   /** The Constant ATTRIBUTEVALUE. */
   public static final String ATTRIBUTEVALUE = "?";

   /** The decoder map. */
   private HashMap<String, SendableEntityCreatorXML> decoderMap;

   /**
    * Inits the.
    */
   @Override
   protected void init()
   {
      super.init();
      getCounter();
      this.filter.withIdFilter(BooleanCondition.value(false));
   }

   /*
    * (non-Javadoc)
    * 
    * @see
    * org.sdmlib.serialization.IdMap#addCreator(org.sdmlib.serialization.interfaces
    * .SendableEntityCreator)
    */
   public boolean addCreator(SendableEntityCreator createrClass)
   {
      if (createrClass instanceof SendableEntityCreatorXML)
      {
         if (this.decoderMap != null)
         {
            if (this.decoderMap
               .containsKey(((SendableEntityCreatorXML) createrClass).getTag()))
            {
               return false;
            }
         }
      }
      else
      {
         return false;
      }
      super.withCreator(createrClass);
      return true;
   }

   @Override
   public AbstractMap withCreator(String className,
         SendableEntityCreator createrClass)
   {
      super.withCreator(className, createrClass);

      if (createrClass instanceof SendableEntityCreatorXML)
      {
         SendableEntityCreatorXML xmlCreator = (SendableEntityCreatorXML) createrClass;
         if (this.decoderMap == null)
         {
            this.decoderMap = new HashMap<String, SendableEntityCreatorXML>();
         }
         this.decoderMap.put(xmlCreator.getTag(), xmlCreator);
      }
      return this;
   }

   /**
    * Gets the creator decode class.
    * 
    * @param tag
    *           the tag
    * @return the creator decode class
    */
   public SendableEntityCreatorXML getCreatorDecodeClass(String tag)
   {
      if (this.decoderMap == null)
      {
         return null;
      }
      return this.decoderMap.get(tag);
   }

   /**
    * Encode.
    * 
    * @param entity
    *           the entity
    * @return the xML entity
    */
   @Override
   public XMLEntity encode(Object entity)
   {
      return encode(entity, filter.cloneObj());
   }

   public XMLEntity encode(Object entity, Filter filter)
   {
      SendableEntityCreator createrProtoTyp = getCreatorClass(entity);
      if (createrProtoTyp == null)
      {
         return null;
      }
      XMLEntity xmlEntity = new XMLEntity();

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

      if (filter.isId(this, entity, entity.getClass().getName()))
      {
         xmlEntity.put(ID, getId(entity));
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
                  if (property.startsWith(XMLIdMap.ENTITYSPLITTER))
                  {
                     parserChild(xmlEntity, property, value);
                  }
                  else
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
      }
      return xmlEntity;
   }

   /**
    * Parser child.
    * 
    * @param parent
    *           the parent
    * @param property
    *           the property
    * @param value
    *           the value
    * @return the xML entity
    */
   private XMLEntity parserChild(XMLEntity parent, String property, Object value)
   {

      if (property.startsWith(XMLIdMap.ENTITYSPLITTER))
      {
         int pos = property.indexOf(XMLIdMap.ENTITYSPLITTER, 1);
         if (pos < 0)
         {
            pos = property.indexOf(XMLIdMap.ATTRIBUTEVALUE, 1);
         }
         String label;
         String newProp = "";
         if (pos > 0)
         {
            label = property.substring(1, pos);
            newProp = property.substring(pos);
         }
         else
         {
            label = property.substring(1);
         }
         if (label.length() > 0)
         {
            XMLEntity child = parent.getChild(label);
            if (child == null)
            {
               child = new XMLEntity();
               child.setTag(label);
               parserChild(child, newProp, value);
               parent.addChild(child);
            }
            else
            {
               parserChild(child, newProp, value);
            }
            return child;
         }
      }
      else if (property.startsWith(XMLIdMap.ATTRIBUTEVALUE))
      {
         parent.put(property.substring(1),
            EntityUtil.valueToString(value, true, parent));
      }
      else if ("".equals(property))
      {
         parent.setValue(EntityUtil.valueToString(value, true, parent));
      }
      return null;
   }

   /**
    * // * Decode. // * // * @param value // * the value // * @return the object
    * //
    */
   @Override
   public Object decode(XMLTokener entity, XMLGrammar factory)
   {
      if (factory == null)
      {
         factory = new XMLEntityCreator();
      }
      return super.decode(entity, factory);
   }

   public Object decode(String value)
   {
      return decode((XMLTokener) new XMLTokener().withText(value), null);
   }

   /**
    * Step empty pos.
    * 
    * @param newPrefix
    *           the new prefix
    * @param entity
    *           the entity
    * @param tag
    *           the tag
    * @return true, if successful
    */
   public boolean stepEmptyPos(String newPrefix, Object entity, String tag,
         XMLTokener tokener)
   {
      boolean exit = false;
      boolean empty = true;

      if (!newPrefix.equals("&"))
      {
         return tokener.stepPos("" + ITEMSTART, false, false);
      }
      if (tokener.getCurrentChar() != ITEMSTART)
      {
         tokener.next();
      }
      int start = tokener.position();
      ArrayList<String> stack = new ArrayList<String>();
      while (!tokener.isEnd() && !exit)
      {
         if (!tokener.checkValues('\t', '\r', '\n', ' ', ITEMSTART))
         {
            empty = false;
         }
         if (tokener.getCurrentChar() == ITEMSTART)
         {
            if (empty)
            {
               exit = true;
               break;
            }
            String nextTag = tokener.getNextTag();
            if (nextTag.length() > 0)
            {
               stack.add(nextTag);
               continue;
            }
            if (tokener.getCurrentChar() == ENDTAG)
            {
               if (stack.size() > 0)
               {
                  int temp = tokener.position();
                  String endTag = tokener.getNextTag();
                  if (stack.get(stack.size() - 1).equals(endTag))
                  {
                     stack.remove(stack.size() - 1);
                  }
                  else
                  {
                     stack.remove(stack.size() - 1);
                     tokener.setIndex(temp - 1);
                     continue;
                  }

               }
               else
               {
                  tokener.back();
                  exit = true;
                  break;
               }
            }
         }
         if (!exit)
         {
            tokener.next();
         }
      }
      if (!empty && exit)
      {
         String value = tokener.substring(start, -1);
         ReferenceObject refObject = null;
         if ("&".equals(newPrefix))
         {
            refObject = tokener.popStack();
         }
         if (refObject != null)
         {
            SendableEntityCreator parentCreator = refObject.getCreater();
            parentCreator.setValue(refObject.getEntity(), newPrefix, value,
               IdMapEncoder.NEW);
         }
      }
      return exit;
   }

   /**
    * Find tag.
    * 
    * @param prefix
    *           the prefix
    * @param tag
    *           the tag
    * @return the object
    */
   @Override
   protected Object parse(XMLEntity entity, XMLTokener tokener,
         XMLGrammar grammar)
   {
      String tag = entity.getTag();
      if (tag.length() < 1)
      {
         return null;
      }
      SendableEntityCreatorXML entityCreater = getCreatorDecodeClass(tag);
      if (entityCreater != null || tokener.getStackSize() > 0)
      {
         return parseIdEntity(entity, grammar, tokener, entityCreater);
      }
      // Must be a Child of Root
      ArrayList<SendableEntityCreatorXML> filter = new ArrayList<SendableEntityCreatorXML>();
      for (SendableEntityCreator creator : getCreators())
      {
         if (creator instanceof SendableEntityCreatorXML)
         {
            SendableEntityCreatorXML xmlCreator = (SendableEntityCreatorXML) creator;
            if (xmlCreator.getTag().startsWith(tag))
            {
               filter.add(xmlCreator);
            }
         }
      }
      while (filter.size() > 1)
      {
         while (!tokener.isEnd())
         {
            if (tokener.stepPos("" + ITEMSTART, false, false))
            {
               XMLEntity item = getEntity(grammar, tokener);
               if (item != null)
               {
                  tag += XMLIdMap.ENTITYSPLITTER + item.getTag();
                  for (Iterator<SendableEntityCreatorXML> i = filter.iterator(); i
                     .hasNext();)
                  {
                     if (!i.next().getTag().startsWith(tag))
                     {
                        i.remove();
                     }
                  }
               }
            }
         }
      }
      if (filter.size() == 1)
      {
         return parseIdEntity(entity, grammar, tokener.withPrefix(""),
            filter.get(0));
      }
      return null;
   }

   /**
    * Parse a Element with IdCreater
    * 
    * @param entity
    * @param grammar
    *           The Grammar of XML
    * @param tokener
    *           The XML-Tokener
    * @param entityCreater
    * @return the Object
    */
   protected Object parseIdEntity(XMLEntity entity, XMLGrammar grammar,
         XMLTokener tokener, SendableEntityCreatorXML entityCreater)
   {
      boolean plainvalue = false;
      Object item = null;

      String newPrefix = "";
      String tag = entity.getTag();
      if (entityCreater == null)
      {
         if (tokener.getStackSize() == 0)
         {
            return null;
         }
         // Not found child creater
         ReferenceObject referenceObject = tokener.getStackLast(0);
         entityCreater = (SendableEntityCreatorXML) referenceObject
            .getCreater();
         String[] properties = entityCreater.getProperties();
         tokener.addPrefix(tag);

         for (String prop : properties)
         {
            if (prop.equalsIgnoreCase(tokener.getPrefix()))
            {
               // It is a Attribute
               item = referenceObject.getEntity();
               plainvalue = true;
               break;
            }
            else if (prop.startsWith(tokener.getPrefix()))
            {
               // it is a Child
               item = referenceObject.getEntity();
               break;
            }
         }

         if (item != null && !plainvalue)
         {
            newPrefix = tokener.getPrefix() + XMLIdMap.ENTITYSPLITTER;
            tokener.addPrefix(XMLIdMap.ATTRIBUTEVALUE);
         }
      }
      else
      {
         item = entityCreater.getSendableInstance(false);
         tokener.withStack(new ReferenceObject().withCreator(entityCreater)
            .withProperty(tag).withEntity(item));
         newPrefix = XMLIdMap.ENTITYSPLITTER;
      }
      if (item == null)
      {
         // First Skip not valid entry
         ArrayList<String> myStack = new ArrayList<String>();
         myStack.add(tag);

         while (!tokener.isEnd() && myStack.size() > 0)
         {
            if (tokener.getCurrentChar() == ENDTAG)
            {
               String nextTag = tokener.getNextTag();
               if (nextTag.length() < 1
                  || myStack.get(myStack.size() - 1).equalsIgnoreCase(nextTag))
               {
                  myStack.remove(myStack.size() - 1);
                  continue;
               }
            }
            if (tokener.getCurrentChar() == ITEMSTART)
            {
               XMLEntity nextTag = getEntity(null, tokener);
               if (nextTag != null && nextTag.getTag().length() > 0)
               {
                  myStack.add(nextTag.getTag());
               }
               continue;
            }
            tokener.next();
         }
      }
      else
      {
         if (!plainvalue)
         {
            // Parse Attributes
            while (!tokener.isEnd() && tokener.getCurrentChar() != ITEMEND)
            {
               if (tokener.getCurrentChar() == ENDTAG)
               {
                  break;
               }
               tokener.next();
               int start = tokener.position();
               if (tokener.getCurrentChar() != ENDTAG)
               {
                  if (tokener.stepPos("=", false, false))
                  {
                     String key = tokener.substring(start, -1);
                     tokener.skip(2);
                     start = tokener.position();
                     if (tokener.stepPos("\"", false, true))
                     {
                        String value = tokener.substring(start, -1);
                        tokener.next();
                        entityCreater.setValue(item, tokener.getPrefix() + key,
                           value, IdMapEncoder.NEW);
                     }
                  }
               }
            }

            if (tokener.getCurrentChar() != ENDTAG)
            {
               // Children
               while (!tokener.isEnd())
               {
                  if (stepEmptyPos(newPrefix, item, tag, tokener))
                  {
                     XMLEntity nextTag = getEntity(null, tokener);

                     if (nextTag != null)
                     {
                        Object result = parse(nextTag,
                           tokener.withPrefix(newPrefix), grammar);

                        if (result != null)
                        {
                           ReferenceObject refObject = null;
                           if (result != item)
                           {
                              if ("&".equals(newPrefix))
                              {
                                 refObject = tokener.getStackLast(1);
                              }
                              else
                              {
                                 refObject = tokener.getStackLast(0);
                              }
                              if (refObject != null)
                              {
                                 SendableEntityCreator parentCreator = refObject
                                    .getCreater();
                                 parentCreator.setValue(refObject.getEntity(),
                                    nextTag.getTag(), result, IdMapEncoder.NEW);
                                 if (item != null && tokener.getStackSize() > 0)
                                 {
                                    tokener.popStack();
                                 }
                              }
                           }
                        }
                     }
                     if (tokener.isEnd())
                     {
                        if (item != null && tokener.getStackSize() > 0)
                        {
                           tokener.popStack();
                        }
                     }
                     else if (tokener.getCurrentChar() == ENDTAG)
                     {
                        tokener.stepPos("" + ITEMEND, false, false);
                        break;
                     }
                     tokener.next();
                  }
               }
            }
            else
            {
               tokener.next();
            }
            return item;
         }
         if (tokener.getCurrentChar() == ENDTAG)
         {
            tokener.next();
         }
         else
         {
            tokener.next();
            int start = tokener.position();
            tokener.stepPos("" + ITEMSTART, false, true);
            String value = tokener.substring(start, -1);
            entityCreater.setValue(item, tokener.getPrefix(), value,
               IdMapEncoder.NEW);
            tokener.stepPos("" + ITEMSTART, false, false);
            tokener.stepPos("" + ITEMEND, false, false);
         }
         return null;
      }
      return item;
   }

   /**
    * Adds the stop words.
    * 
    * @param stopwords
    *           the stopwords
    * @return true, if successful
    */
   public boolean addStopWords(String... stopwords)
   {
      for (String stopword : stopwords)
      {
         this.stopwords.add(stopword);
      }
      return true;
   }
}
