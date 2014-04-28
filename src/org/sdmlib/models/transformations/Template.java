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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.StrUtil;
import org.sdmlib.models.transformations.util.MatchSet;
import org.sdmlib.models.transformations.util.PlaceHolderDescriptionSet;
import org.sdmlib.models.transformations.util.TemplateSet;
import org.sdmlib.serialization.IdMap;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;
import org.sdmlib.serialization.json.JsonIdMap;
import org.sdmlib.storyboards.GenericCreator;
import org.sdmlib.storyboards.GenericIdMap;
import org.sdmlib.serialization.util.PropertyChangeInterface;


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

      if (PROPERTY_PLACEHOLDERS.equalsIgnoreCase(attrName))
      {
         return getPlaceholders();
      }

      if (PROPERTY_CHOOSER.equalsIgnoreCase(attrName))
      {
         return getChooser();
      }

      if (PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         return getMatches();
      }

      if (PROPERTY_PARENTS.equalsIgnoreCase(attrName))
      {
         return getParents();
      }

      if (PROPERTY_REFERENCELOOKUP.equalsIgnoreCase(attrName))
      {
         return getReferenceLookup();
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         return getName();
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

      if (PROPERTY_CHOOSER.equalsIgnoreCase(attrName))
      {
         setChooser((ChoiceTemplate) value);
         return true;
      }

      if (PROPERTY_MATCHES.equalsIgnoreCase(attrName))
      {
         addToMatches((Match) value);
         return true;
      }
      
      if ((PROPERTY_MATCHES + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromMatches((Match) value);
         return true;
      }

      if (PROPERTY_PARENTS.equalsIgnoreCase(attrName))
      {
         addToParents((PlaceHolderDescription) value);
         return true;
      }
      
      if ((PROPERTY_PARENTS + JsonIdMap.REMOVE).equalsIgnoreCase(attrName))
      {
         removeFromParents((PlaceHolderDescription) value);
         return true;
      }

      if (PROPERTY_REFERENCELOOKUP.equalsIgnoreCase(attrName))
      {
         setReferenceLookup((Boolean) value);
         return true;
      }

      if (PROPERTY_NAME.equalsIgnoreCase(attrName))
      {
         setName((String) value);
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
      setChooser(null);
      removeAllFromMatches();
      removeAllFromParents();
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }


   //==========================================================================

   public static final String PROPERTY_TEMPLATETEXT = "templateText";

   private String templateText = "";

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
      _.append(" ").append(this.getName());
      return _.substring(1);
   }


   public Template withSimple(String propertyName)
   {
      return with("_", "_", propertyName);
   }
   
   public Template withReplacements(String... placeholderAttrNamePairs)
   {
      for (int i = 0; i + 2 <= placeholderAttrNamePairs.length; i += 2)
      {
         this.createPlaceholders().withTextFragment(placeholderAttrNamePairs[i]).withAttrName(placeholderAttrNamePairs[i+1]);
      }
      
      return this;
   }
   
   public Template with(String templateText, String... placeholderAttrNamePairs)
   {
      this.setTemplateText(templateText);
      
      this.withReplacements(placeholderAttrNamePairs);
      
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

   //==========================================================================

   public static final String PROPERTY_EXPANDEDTEXT = "expandedText";

   private String expandedText;

   protected JsonIdMap idMap;

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


   protected  int currentPosInExpandedText = 0;

   protected String constFragmentFollowingAfterList;
   
   public int getValueStartPos()
   {
      return currentPosInExpandedText;
   }

   public Template withValueStartPos(int valueStartPos)
   {
      this.currentPosInExpandedText = valueStartPos;
      return this;
   }
   
   public Match parseOnceNew()
   {
	   this.currentPosInExpandedText = 0;
	   return parseOnce();
   }

   public MatchSet parse()
   {
      MatchSet result = new MatchSet();
      if ( ! isList())
      {
         Match subMatch = parseOnce();
         
         if (subMatch != null)
         {
            result.add(subMatch);
         }
      }
      else
      {
         int oldValueStartPos = 0;
         
            // look for list start
         int listStartPos = this.getExpandedText().indexOf(this.getListStart(), currentPosInExpandedText);

         if (listStartPos >= 0)
         {
            currentPosInExpandedText = currentPosInExpandedText + getListStart().length();

            LinkedHashSet<Object> modelObjectSet = new LinkedHashSet<Object>();

            oldValueStartPos = currentPosInExpandedText;
            Match subMatch = parseOnce();

            while (subMatch != null)
            {  
               // add to results
               modelObjectSet.add(this.getModelObject());
               result.add(subMatch);

               // skip list separator
               int listSeperatorPos = this.getExpandedText().indexOf(this.getListSeparator(), currentPosInExpandedText);
               int behindListSeparatorPos = listSeperatorPos + this.getListSeparator().length();
               int listEndPos = this.getExpandedText().indexOf(this.getListEnd(), currentPosInExpandedText);
               
               if (" ".equals(this.getListSeparator()))
               {
                  int length = this.getExpandedText().length();
                  boolean startFound = false;
                  for (int i = currentPosInExpandedText; i < length; i++)
                  {
                     char charAt = expandedText.charAt(i);
                     if (! startFound && Character.isWhitespace(charAt))
                     {
                        startFound = true;
                        listSeperatorPos = i;
                        behindListSeparatorPos = i + 1;
                     }
                     else if (startFound)
                     {
                        if (Character.isWhitespace(charAt))
                        {
                           behindListSeparatorPos = i + 1;
                        }
                        else
                        {
                           break;
                        }
                     }
                  }
               }
               
               if ("".equals(this.getListEnd()))
               {
                  // there is no good list end symbol use constFragment behind list placeholder instead
                  listEndPos = this.getExpandedText().indexOf(this.constFragmentFollowingAfterList, currentPosInExpandedText);
               }
               
               boolean found = listSeperatorPos == currentPosInExpandedText && listSeperatorPos <= listEndPos;

               if (found)
               {
                  // more to come
                  oldValueStartPos = currentPosInExpandedText;

                  currentPosInExpandedText = behindListSeparatorPos;
                  
                  subMatch = parseOnce();
               }
               else
               { 
                  subMatch = null;
                  oldValueStartPos = currentPosInExpandedText;
               }
            }
            
            currentPosInExpandedText = oldValueStartPos;

            // skip list end
            int listEndPos = this.getExpandedText().indexOf(this.getListEnd(), currentPosInExpandedText);

            if (listEndPos == currentPosInExpandedText)
            {
               currentPosInExpandedText = currentPosInExpandedText + getListEnd().length();
            }
            
            this.setModelObject(modelObjectSet);
         }
      }
      
      return result;
   }

   public static int logStartPos = 8331;
   
   public Match parseOnce()
   {
      // the templateText contains placeholders and constant text fragments. Match the constant fragments in the expanded text. 
      // Assign the content in between to the placeholders

      int placeHolderPosInTemplateText = 0;
      int currentPosInTemplateText = 0;

      // int constantStartPosInExpandedText = 0;

      int matchStartPos = currentPosInExpandedText;
      
      Match templateMatch = new Match()
      .withFullText(this.getExpandedText())
      .withStartPos(matchStartPos);

      PlaceHolderDescription previousPlaceHolder;

      // split template text into fragments and identify fragments in expanded text
      Iterator<PlaceHolderDescription> iterator = this.getPlaceholders().iterator();
      if (iterator.hasNext())
      {
         PlaceHolderDescription placeholder = iterator.next();

         // find constant fragment before placeholder
         placeHolderPosInTemplateText = this.getTemplateText().indexOf(placeholder.getTextFragment(), currentPosInTemplateText);

         String constfragment = this.getTemplateText().substring(currentPosInTemplateText, placeHolderPosInTemplateText);

         currentPosInTemplateText = placeHolderPosInTemplateText + placeholder.getTextFragment().length();

         // find fragment in expanded text
         int endOfMatchInExpandedText = matchConstantFragmentToExpandedText(constfragment, currentPosInExpandedText);
         
         // constantStartPosInExpandedText = this.getExpandedText().indexOf(constfragment, currentPosInExpandedText);

         if (startOfMatch != currentPosInExpandedText || endOfMatchInExpandedText < currentPosInExpandedText)
         {
            return null;
         }

         currentPosInExpandedText = endOfMatchInExpandedText;

         SendableEntityCreator creator = this.getIdMap().getCreatorClassName(this.getModelClassName(), true);

         boolean first = true;
         
         

         while (true)
         {
            if (currentPosInExpandedText >= logStartPos)
            {
               System.out.print("Parsing at " + currentPosInExpandedText + ": " + getExpandedText().substring(currentPosInExpandedText, Math.min(currentPosInExpandedText + 50, getExpandedText().length())));
               System.out.println();
            }
            
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
               placeHolderPosInTemplateText = this.getTemplateText().indexOf(placeholder.getTextFragment(), currentPosInTemplateText);
            }
            else
            {
               placeHolderPosInTemplateText = this.getTemplateText().length();
            }
            
            constfragment = this.getTemplateText().substring(currentPosInTemplateText, placeHolderPosInTemplateText);

            if (placeholder != null)
            {
               currentPosInTemplateText = placeHolderPosInTemplateText + placeholder.getTextFragment().length();
            }

            
            
            Template subTemplate = previousPlaceHolder.getSubTemplate();
            if (subTemplate != null)
            {
               // ask subtemplate to parse
               MatchSet subMatches = subTemplate.withExpandedText(this.getExpandedText()).withValueStartPos(currentPosInExpandedText)
               .withIdMap(idMap).withConstFragmentFollowingAfterList(constfragment)
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
               
               if ( ! subMatches.isEmpty())
               {
                  // create placeholderMatch
                  Match placeholderMatch = new Match()
                  .withPlaceholder(previousPlaceHolder)
                  .withModelObject(this.getModelObject())
                  .withFullText(this.getExpandedText())
                  .withStartPos(currentPosInExpandedText)
                  .withEndPos(subTemplate.getValueStartPos()-1)
                  .withParentMatch(templateMatch)
                  .withSubMatches(subMatches.toArray(new Match[]{}))
                  ;

               }
               
               this.currentPosInExpandedText = subTemplate.getValueStartPos();
               
               // now the constant fragment should follow
               endOfMatchInExpandedText = matchConstantFragmentToExpandedText(constfragment, currentPosInExpandedText);

               // constantStartPosInExpandedText = this.getExpandedText().indexOf(constfragment, currentPosInExpandedText);

               if (endOfMatchInExpandedText < 0)
               {
                  return null;
               }
               
               currentPosInExpandedText = endOfMatchInExpandedText;
            }
            else
            {
               // find fragment in expanded text
               endOfMatchInExpandedText = matchConstantFragmentToExpandedText(constfragment, currentPosInExpandedText);


               if (endOfMatchInExpandedText < 0)
               {
                  return null;
               }

               if (constfragment.equals(""))
               {
                  // empty constfragment, look for list separator 
                  int listSeparatorPos = this.getExpandedText().indexOf(this.getListSeparator(), currentPosInExpandedText);
                  int listEndPos = this.getExpandedText().indexOf(this.getListEnd(), currentPosInExpandedText);
                  
                  if ("".equals(this.getListEnd()))
                  {
                     // there is no good list end symbol use constFragment behind list placeholder instead
                     listEndPos = this.getExpandedText().indexOf(this.constFragmentFollowingAfterList, currentPosInExpandedText);
                  }
                  

                  if ( ! "".equals(this.getListSeparator()) && listSeparatorPos >= 0 && listSeparatorPos < listEndPos)
                  {
                     startOfMatch = listSeparatorPos;
                     endOfMatchInExpandedText = listSeparatorPos;
                  }
                  else if (listEndPos >= 0)
                  {
                     startOfMatch = listEndPos;
                     endOfMatchInExpandedText = listEndPos;
                  }
                  else
                  {
                     return null;
                  }
               }
               
               String value = this.getExpandedText().substring(currentPosInExpandedText, startOfMatch);
               
               int valueStartPos = currentPosInExpandedText;

               currentPosInExpandedText = endOfMatchInExpandedText;


               if (first)
               {
                  first = false;

                  // look for object in idMap
                  String key = getModelClassName() + "_" + value;
                  Object object = idMap.getObject(key);

                  if (object != null && this.referenceLookup == false)
                  {
                     System.out.println("Warning: two objects with id: " + key);
                  }                     

                  if (object == null  || this.referenceLookup == false)
                  {
                     // create object and assign attribute
                     object = creator.getSendableInstance(false);
                  }
                  
                  idMap.put(key, object);
                  
                  this.setModelObject(object);
               }

               creator.setValue(this.getModelObject(), previousPlaceHolder.getAttrName(), value, "update");
               
               // create placeholderMatch
               Match placeholderMatch = new Match()
               .withPlaceholder(previousPlaceHolder)
               .withModelObject(this.getModelObject())
               .withFullText(this.getExpandedText())
               .withStartPos(valueStartPos)
               .withEndPos(endOfMatchInExpandedText-1)
               .withParentMatch(templateMatch)
               ;
            }
            
            if (placeholder == null)
            {
               break;
            }
         }
      }

      // found a match, protocol it
      templateMatch.withEndPos(currentPosInExpandedText-1)
      .withTemplate(this)
      .withModelObject(this.getModelObject())
      ;
      
      return templateMatch;
   } 

   private int startOfMatch = -1;

   private int matchConstantFragmentToExpandedText(String constfragment, int currentPosInExpandedText)
   {
      int currentPosInConstFragment = 0;
      
      int blankPos = constfragment.indexOf(' ');
      
      if (blankPos <= 0)
      {
         // no blank, take it all
         blankPos = constfragment.length();
      }
         
      // search for real prefix in text
      currentPosInExpandedText = this.expandedText.indexOf(constfragment.substring(0, blankPos), currentPosInExpandedText);
      
      if (currentPosInExpandedText < 0)
      {
         return -1;
      }
      
      startOfMatch = currentPosInExpandedText;
      
      while (currentPosInConstFragment < constfragment.length())
      {
         char constFragmentChar = constfragment.charAt(currentPosInConstFragment);
         
         if (constFragmentChar == ' ')
         {
            char nextFragmentChar = Character.MIN_VALUE;
            if (currentPosInConstFragment + 1 < constfragment.length())
            {
               nextFragmentChar = constfragment.charAt(currentPosInConstFragment +1);
            }
            
            while (currentPosInExpandedText < expandedText.length() 
                  && Character.isWhitespace(expandedText.charAt(currentPosInExpandedText))
                  && expandedText.charAt(currentPosInExpandedText) != nextFragmentChar)
            {
               currentPosInExpandedText++;
            }
            
            currentPosInConstFragment++;
         }
         else
         {
             if (expandedText.charAt(currentPosInExpandedText) != constFragmentChar)
             {
                return -1;
             }
             currentPosInConstFragment++;
             currentPosInExpandedText++;
         }
      }
      
      return currentPosInExpandedText;
   }


   protected Template withConstFragmentFollowingAfterList(String constfragment)
   {
      this.constFragmentFollowingAfterList = constfragment;
      return this;
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


   protected Template withIdMap(JsonIdMap idMap2)
   {
      this.idMap = idMap2;
      
      return this;
   }

   public static int expansionStep = 0;
   
   public void generate()
   {
      // expand all placeholder descriptions
      StringBuilder result = new StringBuilder();

      provideIdMap();

      LinkedHashSet<Object> rootObjects = new LinkedHashSet<Object>();

      if (modelObject == null)
      {
         this.setExpandedText("");
         return;
      }
      
      
      if (modelObject instanceof Collection)
      {
         Collection collection = (Collection) modelObject;
         if ( ! collection.isEmpty())
         {
            Object elem = collection.iterator().next();
            
            if ("Double Integer String".indexOf(CGUtil.shortClassName(elem.getClass().getName())) >= 0)
            {
               rootObjects.add(modelObject);
            }
            else
            {
               rootObjects.addAll((Collection) modelObject);
            }
         }
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
                  expansionStep++;
                  if (expansionStep >= logStartStep)
                  {
                     System.out.print("Expansion step " + expansionStep + ": " + this + " " + attrValue);
                     System.out.println();
                  }
                  result.append(attrValue);
               }
               else 
               {
                  // use subtemplate
                  expansionStep++;
                  if (expansionStep >= logStartStep)
                  {
                     System.out.print("Expansion step " + expansionStep + ": " + subTemplate + " " + attrValue);
                     System.out.println();
                  }
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
   
   public static int logStartStep = 453;


   private Object getValue(PlaceHolderDescription placeholder, Object root)
   {
      SendableEntityCreator creator;

      String[] split = placeholder.getAttrName().split("\\.");

      Object currentObject = root;

      for (String attrName : split)
      {
         Integer index = null; 
         
         try
         {
            index = Integer.valueOf(attrName);
         }
         catch (NumberFormatException e)
         {
            // do nothing
         }
         
         if (index != null)
         {
            // look up the element with this index
            Object[] array = ((Collection)currentObject).toArray(new Object[((Collection)currentObject).size()]);
            
            currentObject = array[index];
         }
         else if (currentObject instanceof ArrayList)
         {
            ArrayList arrayList = (ArrayList) currentObject;
            currentObject = arrayList.get(Integer.valueOf(attrName));
         }
         else
         {
            creator = idMap.getCreatorClass(currentObject);
            if (creator == null)
            {
               creator = new GenericCreator().withClassName(currentObject
                  .getClass().getName());
            }
            currentObject = creator.getValue(currentObject, attrName);
         }
      }

      return currentObject;
   }


   private void provideIdMap()
   {
      try 
      {
         if (idMap == null)
         {
            String className = modelObject.getClass().getName();
            String packageName = CGUtil.packageName(className) + ".creators";
            className = packageName + ".CreatorCreator";

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


   public Template withParent(String textFragment, String attrName)
   {
      this.getParents().first().withTextFragment(textFragment).withAttrName(attrName);

      return this;
   }


   
   public static final TemplateSet EMPTY_SET = new TemplateSet();

   
   /********************************************************************
    * <pre>
    *              many                       many
    * Template ----------------------------------- PlaceHolderDescription
    *              owners                   placeholders
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
            value.withOwners(this);
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
            value.withoutOwners(this);
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
    *              many                       one
    * Template ----------------------------------- ChoiceTemplate
    *              choices                   chooser
    * </pre>
    */
   
   public static final String PROPERTY_CHOOSER = "chooser";
   
   private ChoiceTemplate chooser = null;
   
   public ChoiceTemplate getChooser()
   {
      return this.chooser;
   }
   
   public boolean setChooser(ChoiceTemplate value)
   {
      boolean changed = false;
      
      if (this.chooser != value)
      {
         ChoiceTemplate oldValue = this.chooser;
         
         if (this.chooser != null)
         {
            this.chooser = null;
            oldValue.withoutChoices(this);
         }
         
         this.chooser = value;
         
         if (value != null)
         {
            value.withChoices(this);
         }
         
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CHOOSER, oldValue, value);
         changed = true;
      }
      
      return changed;
   }
   
   public Template withChooser(ChoiceTemplate value)
   {
      setChooser(value);
      return this;
   } 
   
   public ChoiceTemplate createChooser()
   {
      ChoiceTemplate value = new ChoiceTemplate();
      withChooser(value);
      return value;
   } 

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Template ----------------------------------- Match
    *              template                   matches
    * </pre>
    */
   
   public static final String PROPERTY_MATCHES = "matches";
   
   private MatchSet matches = null;
   
   public MatchSet getMatches()
   {
      if (this.matches == null)
      {
         return Match.EMPTY_SET;
      }
   
      return this.matches;
   }
   
   public boolean addToMatches(Match value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.matches == null)
         {
            this.matches = new MatchSet();
         }
         
         changed = this.matches.add (value);
         
         if (changed)
         {
            value.withTemplate(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_MATCHES, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromMatches(Match value)
   {
      boolean changed = false;
      
      if ((this.matches != null) && (value != null))
      {
         changed = this.matches.remove (value);
         
         if (changed)
         {
            value.setTemplate(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_MATCHES, value, null);
         }
      }
         
      return changed;   
   }
   
   public Template withMatches(Match... value)
   {
      for (Match item : value)
      {
         addToMatches(item);
      }
      return this;
   } 
   
   public Template withoutMatches(Match... value)
   {
      for (Match item : value)
      {
         removeFromMatches(item);
      }
      return this;
   }
   
   public void removeAllFromMatches()
   {
      LinkedHashSet<Match> tmpSet = new LinkedHashSet<Match>(this.getMatches());
   
      for (Match value : tmpSet)
      {
         this.removeFromMatches(value);
      }
   }
   
   public Match createMatches()
   {
      Match value = new Match();
      withMatches(value);
      return value;
   } 

   

   
   /********************************************************************
    * <pre>
    *              one                       many
    * Template ----------------------------------- PlaceHolderDescription
    *              subTemplate                   parents
    * </pre>
    */
   
   public static final String PROPERTY_PARENTS = "parents";
   
   private PlaceHolderDescriptionSet parents = null;
   
   public PlaceHolderDescriptionSet getParents()
   {
      if (this.parents == null)
      {
         return PlaceHolderDescription.EMPTY_SET;
      }
   
      return this.parents;
   }
   
   public boolean addToParents(PlaceHolderDescription value)
   {
      boolean changed = false;
      
      if (value != null)
      {
         if (this.parents == null)
         {
            this.parents = new PlaceHolderDescriptionSet();
         }
         
         changed = this.parents.add (value);
         
         if (changed)
         {
            value.withSubTemplate(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENTS, null, value);
         }
      }
         
      return changed;   
   }
   
   public boolean removeFromParents(PlaceHolderDescription value)
   {
      boolean changed = false;
      
      if ((this.parents != null) && (value != null))
      {
         changed = this.parents.remove (value);
         
         if (changed)
         {
            value.setSubTemplate(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_PARENTS, value, null);
         }
      }
         
      return changed;   
   }
   
   public Template withParents(PlaceHolderDescription... value)
   {
      for (PlaceHolderDescription item : value)
      {
         addToParents(item);
      }
      return this;
   } 
   
   public Template withoutParents(PlaceHolderDescription... value)
   {
      for (PlaceHolderDescription item : value)
      {
         removeFromParents(item);
      }
      return this;
   }
   
   public void removeAllFromParents()
   {
      LinkedHashSet<PlaceHolderDescription> tmpSet = new LinkedHashSet<PlaceHolderDescription>(this.getParents());
   
      for (PlaceHolderDescription value : tmpSet)
      {
         this.removeFromParents(value);
      }
   }
   
   public PlaceHolderDescription createParents()
   {
      PlaceHolderDescription value = new PlaceHolderDescription();
      withParents(value);
      return value;
   }


   public Template createPlaceHolderAndSubTemplate(String textFragment,
         String attrName, String templateText, String listStart, String listSeparator, String listEnd)
   {
      Template subTemplate = this.createPlaceHolderAndSubTemplate(textFragment, attrName, templateText)
            .withList(listStart, listSeparator, listEnd);
      
      return subTemplate;
   }

      
   public Template createPlaceHolderAndSubTemplate(String textFragment,
         String attrName, String templateText)
   {
      Template subTemplate = this.createPlaceHolderAndSubTemplate()
      .withParent(textFragment, attrName)
      .withTemplateText(templateText);
      
      return subTemplate;
   }


   public Template withMultiStep(String textFragment, String... properties)
   {
      Template subTemplate = this.createPlaceHolderAndSubTemplate()
      .withParent(textFragment, properties[0]);
      
      for (int i = 1; i < properties.length - 1; i++)
      {
         subTemplate = subTemplate.createPlaceHolderAndSubTemplate("_", properties[i], "_");
      }
      
      subTemplate.withSimple(properties[properties.length - 1]);
      
      return this;
   } 

   public Template withSeparatedMultiStep(String textFragment, String listSeperator, String... properties)
   {
      Template subTemplate = this.createPlaceHolderAndSubTemplate()
      .withParent(textFragment, properties[0]).withListSeparator(listSeperator);
      
      for (int i = 1; i < properties.length - 1; i++)
      {
         subTemplate = subTemplate.createPlaceHolderAndSubTemplate("_", properties[i], "_");
      }
      
      subTemplate.withSimple(properties[properties.length - 1]);
      
      return this;
   }
   
   //==========================================================================
   
   public static final String PROPERTY_REFERENCELOOKUP = "referenceLookup";
   
   private boolean referenceLookup = false;

   public boolean getReferenceLookup()
   {
      return this.referenceLookup;
   }
   
   public void setReferenceLookup(boolean value)
   {
      if (this.referenceLookup != value)
      {
         boolean oldValue = this.referenceLookup;
         this.referenceLookup = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_REFERENCELOOKUP, oldValue, value);
      }
   }
   
   public Template withReferenceLookup(boolean value)
   {
      setReferenceLookup(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_NAME = "name";
   
   private String name;

   public String getName()
   {
      return this.name;
   }
   
   public void setName(String value)
   {
      if ( ! StrUtil.stringEquals(this.name, value))
      {
         String oldValue = this.name;
         this.name = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_NAME, oldValue, value);
      }
   }
   
   public Template withName(String value)
   {
      setName(value);
      return this;
   } 
}

