/*
   Copyright (c) 2013 zuendorf 

   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 

   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 

   The Software shall be used for Good, not Evil. 

   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package org.sdmlib.models.transformations;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.TreeMap;

import org.sdmlib.codegen.CGUtil;
import org.sdmlib.models.modelsets.ObjectSet;
import org.sdmlib.models.modelsets.StringList;
import org.sdmlib.models.transformations.creators.PlaceHolderDescriptionSet;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.storyboards.GenericCreator;
import org.sdmlib.storyboards.GenericIdMap;
import org.sdmlib.utils.PropertyChangeInterface;
import org.sdmlib.utils.StrUtil;

public class Template implements PropertyChangeInterface
{


   //==========================================================================

   public Object get(String attrName)
   {
      if (PROPERTY_TEMPLATETEXT.equalsIgnoreCase(attrName))
      {
         return getTemplateText();
      }

      if (PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         return getModelObject();
      }

      if (PROPERTY_MODELCLASSNAME.equalsIgnoreCase(attrName))
      {
         return getModelClassName();
      }

      if (PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attrName))
      {
         return getPlaceholders();
      }

      if (PROPERTY_PLACEHOLDERDESCRIPTION.equalsIgnoreCase(attrName))
      {
         return getPlaceholderDescription();
      }

      if (PROPERTY_EXPANDEDTEXT.equalsIgnoreCase(attrName))
      {
         return getExpandedText();
      }

      if (PROPERTY_LISTSTART.equalsIgnoreCase(attrName))
      {
         return getListStart();
      }

      if (PROPERTY_LISTSEPARATOR.equalsIgnoreCase(attrName))
      {
         return getListSeparator();
      }

      if (PROPERTY_LISTEND.equalsIgnoreCase(attrName))
      {
         return getListEnd();
      }

      return null;
   }


   //==========================================================================

   public boolean set(String attrName, Object value)
   {
      if (PROPERTY_TEMPLATETEXT.equalsIgnoreCase(attrName))
      {
         setTemplateText((String) value);
         return true;
      }

      if (PROPERTY_MODELOBJECT.equalsIgnoreCase(attrName))
      {
         setModelObject((Object) value);
         return true;
      }

      if (PROPERTY_MODELCLASSNAME.equalsIgnoreCase(attrName))
      {
         setModelClassName((String) value);
         return true;
      }

      if (PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attrName))
      {
         addToPlaceholders((PlaceHolderDescription) value);
         return true;
      }

      if ((PROPERTY_PLACEHOLDERS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromPlaceholders((PlaceHolderDescription) value);
         return true;
      }

      if (PROPERTY_PLACEHOLDERDESCRIPTION.equalsIgnoreCase(attrName))
      {
         setPlaceholderDescription((PlaceHolderDescription) value);
         return true;
      }

      if (PROPERTY_EXPANDEDTEXT.equalsIgnoreCase(attrName))
      {
         setExpandedText((String) value);
         return true;
      }

      if (PROPERTY_LISTSTART.equalsIgnoreCase(attrName))
      {
         setListStart((String) value);
         return true;
      }

      if (PROPERTY_LISTSEPARATOR.equalsIgnoreCase(attrName))
      {
         setListSeparator((String) value);
         return true;
      }

      if (PROPERTY_LISTEND.equalsIgnoreCase(attrName))
      {
         setListEnd((String) value);
         return true;
      }

      return false;
   }


   //==========================================================================

   protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

   public PropertyChangeSupport getPropertyChangeSupport()
   {
      return listeners;
   }

   public void addPropertyChangeListener(PropertyChangeListener listener) 
   {
      getPropertyChangeSupport().addPropertyChangeListener(listener);
   }


   //==========================================================================

   public void removeYou()
   {
      removeAllFromPlaceholders();
      setPlaceholderDescription(null);
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


   //==========================================================================

   public static final String PROPERTY_TEMPLATETEXT = "templateText";

   private String templateText;

   public String getTemplateText()
   {
      return this.templateText;
   }

   public void setTemplateText(String value)
   {
      if ( ! StrUtil.stringEquals(this.templateText, value))
      {
         String oldValue = this.templateText;
         this.templateText = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_TEMPLATETEXT, oldValue, value);
      }
   }

   public Template withTemplateText(String value)
   {
      setTemplateText(value);
      return this;
   } 

   public String toString()
   {
      StringBuilder _ = new StringBuilder();

      _.append(" ").append(this.getTemplateText());
      _.append(" ").append(this.getModelClassName());
      _.append(" ").append(this.getExpandedText());
      _.append(" ").append(this.getListStart());
      _.append(" ").append(this.getListSeparator());
      _.append(" ").append(this.getListEnd());
      return _.substring(1);
   }

   public Template with(String templateText, Object modelObject, String... placeholderAttrNamePairs)
   {
      this.setTemplateText(templateText);
      this.setModelObject(modelObject);

      for (int i = 0; i + 2 <= placeholderAttrNamePairs.length; i += 2)
      {
         this.createPlaceholders().withTextFragment(placeholderAttrNamePairs[i]).withAttrName(placeholderAttrNamePairs[i+1]);
      }
      return this;
   }

   public Template withList(String start, String separator, String end)
   {
      this.setListStart(start);
      this.setListSeparator(separator);
      this.setListEnd(end);

      return this;
   }
   //==========================================================================

   public static final String PROPERTY_MODELOBJECT = "modelObject";

   private Object modelObject;

   public Object getModelObject()
   {
      return this.modelObject;
   }

   public void setModelObject(Object value)
   {
      if (this.modelObject != value)
      {
         Object oldValue = this.modelObject;
         this.modelObject = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MODELOBJECT, oldValue, value);
      }
   }

   public Template withModelObject(Object value)
   {
      setModelObject(value);
      return this;
   } 


   //==========================================================================

   public static final String PROPERTY_MODELCLASSNAME = "modelClassName";

   private String modelClassName;

   public String getModelClassName()
   {
      return this.modelClassName;
   }

   public void setModelClassName(String value)
   {
      if ( ! StrUtil.stringEquals(this.modelClassName, value))
      {
         String oldValue = this.modelClassName;
         this.modelClassName = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_MODELCLASSNAME, oldValue, value);
      }
   }

   public Template withModelClassName(String value)
   {
      setModelClassName(value);
      return this;
   } 

   public Template createPlaceHolderAndSubTemplate()
   {
      PlaceHolderDescription placeholder = this.createPlaceholders();

      Template subTemplate = placeholder.createSubTemplate();

      return subTemplate;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * Template ----------------------------------- PlaceHolderDescription
    *              template                   placeholders
    * </pre>
    */

   public static final String PROPERTY_PLACEHOLDERS = "placeholders";

   private PlaceHolderDescriptionSet placeholders = null;

   public PlaceHolderDescriptionSet getPlaceholders()
   {
      if (this.placeholders == null)
      {
         return PlaceHolderDescription.EMPTY_SET;
      }

      return this.placeholders;
   }

   public boolean addToPlaceholders(PlaceHolderDescription value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.placeholders == null)
         {
            this.placeholders = new PlaceHolderDescriptionSet();
         }

         changed = this.placeholders.add (value);

         if (changed)
         {
            value.withTemplate(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PLACEHOLDERS, null, value);
         }
      }

      return changed;   
   }

   public boolean removeFromPlaceholders(PlaceHolderDescription value)
   {
      boolean changed = false;

      if ((this.placeholders != null) && (value != null))
      {
         changed = this.placeholders.remove (value);

         if (changed)
         {
            value.setTemplate(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PLACEHOLDERS, value, null);
         }
      }

      return changed;   
   }

   public Template withPlaceholders(PlaceHolderDescription... value)
   {
      for (PlaceHolderDescription item : value)
      {
         addToPlaceholders(item);
      }
      return this;
   } 

   public Template withoutPlaceholders(PlaceHolderDescription... value)
   {
      for (PlaceHolderDescription item : value)
      {
         removeFromPlaceholders(item);
      }
      return this;
   }

   public void removeAllFromPlaceholders()
   {
      LinkedHashSet<PlaceHolderDescription> tmpSet = new LinkedHashSet<PlaceHolderDescription>(this.getPlaceholders());

      for (PlaceHolderDescription value : tmpSet)
      {
         this.removeFromPlaceholders(value);
      }
   }

   public PlaceHolderDescription createPlaceholders()
   {
      PlaceHolderDescription value = new PlaceHolderDescription();
      withPlaceholders(value);
      return value;
   } 


   /********************************************************************
    * <pre>
    *              one                       one
    * Template ----------------------------------- PlaceHolderDescription
    *              subTemplate                   placeholderDescription
    * </pre>
    */

   public static final String PROPERTY_PLACEHOLDERDESCRIPTION = "placeholderDescription";

   private PlaceHolderDescription placeholderDescription = null;

   public PlaceHolderDescription getPlaceholderDescription()
   {
      return this.placeholderDescription;
   }

   public boolean setPlaceholderDescription(PlaceHolderDescription value)
   {
      boolean changed = false;

      if (this.placeholderDescription != value)
      {
         PlaceHolderDescription oldValue = this.placeholderDescription;

         if (this.placeholderDescription != null)
         {
            this.placeholderDescription = null;
            oldValue.setSubTemplate(null);
         }

         this.placeholderDescription = value;

         if (value != null)
         {
            value.withSubTemplate(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_PLACEHOLDERDESCRIPTION, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public Template withPlaceholderDescription(PlaceHolderDescription value)
   {
      setPlaceholderDescription(value);
      return this;
   } 

   public PlaceHolderDescription createPlaceholderDescription()
   {
      PlaceHolderDescription value = new PlaceHolderDescription();
      withPlaceholderDescription(value);
      return value;
   } 


   //==========================================================================

   public static final String PROPERTY_EXPANDEDTEXT = "expandedText";

   private String expandedText;

   private JsonIdMap idMap;

   public String getExpandedText()
   {
      return this.expandedText;
   }

   public void setExpandedText(String value)
   {
      if ( ! StrUtil.stringEquals(this.expandedText, value))
      {
         String oldValue = this.expandedText;
         this.expandedText = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_EXPANDEDTEXT, oldValue, value);
      }
   }

   public Template withExpandedText(String value)
   {
      setExpandedText(value);
      return this;
   }


   private  int valueStartPos = 0;
   
   public int getValueStartPos()
   {
      return valueStartPos;
   }

   public Template withValueStartPos(int valueStartPos)
   {
      this.valueStartPos = valueStartPos;
      return this;
   }

   public void parse()
   {
      if ( ! isList())
      {
         parseOnce();
      }
      else
      {
         int oldValueStartPos = 0;
         
            // look for list start
         int listStartPos = this.getExpandedText().indexOf(this.getListStart(), valueStartPos);

         if (listStartPos >= 0)
         {
            valueStartPos = valueStartPos + getListStart().length();

            LinkedHashSet<Object> modelObjectSet = new LinkedHashSet<>();

            oldValueStartPos = valueStartPos;
            boolean found = parseOnce();

            while (found)
            {  
               // add to results
               modelObjectSet.add(this.getModelObject());

               // skip list separator
               int listSeperatorPos = this.getExpandedText().indexOf(this.getListSeparator(), valueStartPos);
               int listEndPos = this.getExpandedText().indexOf(this.getListEnd(), valueStartPos);

               found = listSeperatorPos == valueStartPos && listSeperatorPos < listEndPos;

               if (found)
               {
                  // more to come
                  valueStartPos = valueStartPos + listSeparator.length();
                  
                  oldValueStartPos = valueStartPos;
                  found = parseOnce();
               }
               else
               {
                  oldValueStartPos = valueStartPos;
               }
            }
            
            valueStartPos = oldValueStartPos;

            // skip list end
            int listEndPos = this.getExpandedText().indexOf(this.getListEnd(), valueStartPos);

            if (listEndPos == valueStartPos)
            {
               valueStartPos = valueStartPos + getListEnd().length();
            }
            
            this.setModelObject(modelObjectSet);
         }
      }
   }

   public boolean parseOnce()
   {
      // the templateText contains placeholders and constant text fragments. Match the constant fragments in the expanded text. 
      // Assign the content in between to the placeholders

      int placeHolderPos = 0;
      int searchPos = 0;

      int constantStartPos = 0;


      PlaceHolderDescription previousPlaceHolder;

      // split template text into fragments and identify fragments in expanded text
      Iterator<PlaceHolderDescription> iterator = this.getPlaceholders().iterator();
      if (iterator.hasNext())
      {
         PlaceHolderDescription placeholder = iterator.next();

         // find constant fragment before placeholder
         placeHolderPos = this.getTemplateText().indexOf(placeholder.getTextFragment(), searchPos);

         String constfragment = this.getTemplateText().substring(searchPos, placeHolderPos);

         searchPos = placeHolderPos + placeholder.getTextFragment().length();

         // find fragment in expanded text
         constantStartPos = this.getExpandedText().indexOf(constfragment, valueStartPos);

         if (constantStartPos < 0)
         {
            return false;
         }

         valueStartPos = constantStartPos + constfragment.length();

         SendableEntityCreator creator = this.getIdMap().getCreatorClasses(this.getModelClassName());

         boolean first = true;

         while (true)
         {
            previousPlaceHolder = placeholder;

            if (iterator.hasNext())
            {
               placeholder = iterator.next();
            }
            else 
            {
               placeholder = null;
            }
            
            // find constant fragment before placeholder
            if (placeholder != null)
            {
               placeHolderPos = this.getTemplateText().indexOf(placeholder.getTextFragment(), searchPos);
            }
            else
            {
               placeHolderPos = this.getTemplateText().length();
            }
            
            constfragment = this.getTemplateText().substring(searchPos, placeHolderPos);

            if (placeholder != null)
            {
               searchPos = placeHolderPos + placeholder.getTextFragment().length();
            }

            
            
            Template subTemplate = previousPlaceHolder.getSubTemplate();
            if (subTemplate != null)
            {
               // ask subtemplate to parse
               subTemplate.withExpandedText(this.getExpandedText()).withValueStartPos(valueStartPos).withIdMap(idMap)
               .parse();
               
               if (subTemplate.isList())
               {
                  for (Object value : (Collection) subTemplate.getModelObject())
                  {
                     creator.setValue(this.getModelObject(), previousPlaceHolder.getAttrName(), value, "update");
                  }
               }
               else
               {
                  creator.setValue(this.getModelObject(), previousPlaceHolder.getAttrName(), subTemplate.getModelObject(), "update");
               }
               
               this.valueStartPos = subTemplate.getValueStartPos();
               
               // now the constant fragment should follow
               constantStartPos = this.getExpandedText().indexOf(constfragment, valueStartPos);

               if (constantStartPos < 0)
               {
                  return false;
               }
               
               if (constantStartPos == valueStartPos)
               {
                  valueStartPos = valueStartPos + constfragment.length();
               }
               else 
               {
                  return false;
               }
            }
            else
            {
               // find fragment in expanded text
               constantStartPos = this.getExpandedText().indexOf(constfragment, valueStartPos);

               if (constantStartPos < 0)
               {
                  return false;
               }
               
               if (constantStartPos == valueStartPos)
               {
                  // empty constfragment, look for list separator 
                  int listSeparatorPos = this.getExpandedText().indexOf(this.getListSeparator(), valueStartPos);
                  int listEndPos = this.getExpandedText().indexOf(this.getListEnd(), valueStartPos);
                  
                  if (listSeparatorPos >= 0 && listSeparatorPos < listEndPos)
                  {
                     constantStartPos = listSeparatorPos;
                  }
                  else if (listEndPos >= 0)
                  {
                     constantStartPos = listEndPos;
                  }
                  else
                  {
                     return false;
                  }
               }

               
               String value = this.getExpandedText().substring(valueStartPos, constantStartPos);

               valueStartPos = constantStartPos + constfragment.length();


               if (first)
               {
                  first = false;

                  // look for object in idMap
                  Object object = idMap.getObject(value);

                  if (object == null)
                  {
                     // create object and assign attribute
                     object = creator.getSendableInstance(false);
                     
                     idMap.put(value, object);
                  }    

                  this.setModelObject(object);
               }

               creator.setValue(this.getModelObject(), previousPlaceHolder.getAttrName(), value, "update");
            }
            
            if (placeholder == null)
            {
               break;
            }
         }
         
         
      }

      return true;
   } 


   private IdMap getIdMap()
   {
      if (this.idMap == null)
      {
         this.idMap = new GenericIdMap();
      }
      return idMap;
   }


   private boolean isList()
   {
      return ! "".equals(this.getListStart()+this.getListSeparator()+this.getListEnd());
   }


   private Template withIdMap(JsonIdMap idMap2)
   {
      this.idMap = idMap2;
      
      return this;
   }


   public void generate()
   {
      // expand all placeholder descriptions
      StringBuilder result = new StringBuilder();

      provideIdMap();

      LinkedHashSet<Object> rootObjects = new LinkedHashSet<Object>();

      if (modelObject instanceof Collection)
      {
         rootObjects.addAll((Collection) modelObject);
      }
      else
      {
         rootObjects.add(modelObject);
      }

      result.append(this.getListStart());

      boolean first = true;

      for (Object root : rootObjects)
      {
         if (first)
         {
            first = false;
            this.setModelClassName(root.getClass().getName());
         }
         else
         {
            result.append(this.getListSeparator());
         }

         int searchPos = 0;

         // expand template
         for (PlaceHolderDescription placeholder : this.getPlaceholders())
         {
            // find textFragment in template text
            int foundPos = this.getTemplateText().indexOf(placeholder.getTextFragment(), searchPos);

            if (foundPos >= 0)
            {
               // copy initial template fragment to result
               result.append(this.getTemplateText().substring(searchPos, foundPos));

               // read attribute and append to result
               Object attrValue = getValue(placeholder, root);

               Template subTemplate = placeholder.getSubTemplate();
               if (subTemplate == null)
               {
                  // use value direct
                  result.append(attrValue);
               }
               else 
               {
                  // use subtemplate
                  subTemplate.withModelObject(attrValue)
                  .generate();

                  result.append(subTemplate.getExpandedText());
               }

               // update positions
               searchPos = foundPos + placeholder.getTextFragment().length();
            }
         }

         // append closing text fragment
         result.append(this.getTemplateText().substring(searchPos));
      }

      result.append(this.getListEnd());

      this.setExpandedText(result.toString());
   }


   private Object getValue(PlaceHolderDescription placeholder, Object root)
   {
      SendableEntityCreator creator;

      String[] split = placeholder.getAttrName().split("\\.");

      Object currentObject = root;

      for (String attrName : split)
      {
         creator = idMap.getCreatorClass(currentObject);

         if (creator == null)
         {
            creator = new GenericCreator().withClassName(currentObject.getClass().getName());
         }

         currentObject = creator.getValue(currentObject, attrName);
      }

      return currentObject;
   }


   private void provideIdMap()
   {
      String className = modelObject.getClass().getName();
      String packageName = CGUtil.packageName(className) + ".creators";
      className = packageName + ".CreatorCreator";

      try 
      {
         if (idMap == null)
         {
            idMap = new GenericIdMap();

            Class<?> creatorClass = Class.forName(className);
            Method method = creatorClass.getDeclaredMethod("createIdMap", String.class);

            JsonIdMap newIdMap = (JsonIdMap) method.invoke(null, "debug");

            idMap.getCreators().addAll(newIdMap.getCreators());
         }
      }
      catch (Exception e)
      {

      }

   } 


   //==========================================================================

   public static final String PROPERTY_LISTSTART = "listStart";

   private String listStart = "";

   public String getListStart()
   {
      return this.listStart;
   }

   public void setListStart(String value)
   {
      if ( ! StrUtil.stringEquals(this.listStart, value))
      {
         String oldValue = this.listStart;
         this.listStart = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LISTSTART, oldValue, value);
      }
   }

   public Template withListStart(String value)
   {
      setListStart(value);
      return this;
   } 


   //==========================================================================

   public static final String PROPERTY_LISTSEPARATOR = "listSeparator";

   private String listSeparator = "";

   public String getListSeparator()
   {
      return this.listSeparator;
   }

   public void setListSeparator(String value)
   {
      if ( ! StrUtil.stringEquals(this.listSeparator, value))
      {
         String oldValue = this.listSeparator;
         this.listSeparator = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LISTSEPARATOR, oldValue, value);
      }
   }

   public Template withListSeparator(String value)
   {
      setListSeparator(value);
      return this;
   } 


   //==========================================================================

   public static final String PROPERTY_LISTEND = "listEnd";

   private String listEnd = "";

   public String getListEnd()
   {
      return this.listEnd;
   }

   public void setListEnd(String value)
   {
      if ( ! StrUtil.stringEquals(this.listEnd, value))
      {
         String oldValue = this.listEnd;
         this.listEnd = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_LISTEND, oldValue, value);
      }
   }

   public Template withListEnd(String value)
   {
      setListEnd(value);
      return this;
   }


   public Template withPlaceholderDescription(String textFragment, String attrName)
   {
      this.getPlaceholderDescription().withTextFragment(textFragment).withAttrName(attrName);

      return this;
   }

}

