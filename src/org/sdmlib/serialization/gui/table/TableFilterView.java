package org.sdmlib.serialization.gui.table;

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

import org.sdmlib.serialization.StringTokener;
import org.sdmlib.serialization.interfaces.SendableEntityCreator;

public class TableFilterView
{
   private String[] lastSearchCriteriaItems;
   private ArrayList<String> searchProperties = new ArrayList<String>();
   protected TableComponentInterface component;
   private Column updateField;
   protected boolean lastSearchDetails;
   protected String lastSearchCriteria = "##";

   public TableFilterView(TableComponentInterface tableComponent)
   {
      this.component = tableComponent;
   }

   public void setSearchProperties(String... searchProperties)
   {
      if (searchProperties != null)
      {
         this.searchProperties.clear();
         for (String item : searchProperties)
         {
            this.searchProperties.add(item);
         }
      }
   }

   public void refresh()
   {
      refresh("");
   }

   public void refresh(String searchCriteria)
   {
      // if search did not change do nothing
      if (searchCriteria == null)
         return; // <========= sudden death
      lastSearchDetails = searchCriteria.contains(lastSearchCriteria);
      lastSearchCriteria = searchCriteria;

      StringTokener stringTokener = new StringTokener();
      stringTokener.withText(searchCriteria.toLowerCase());
      ArrayList<String> stringList = stringTokener.getStringList();
      ArrayList<String> searchList = new ArrayList<String>();
      for (int i = 0; i < stringList.size(); i++)
      {
         if (stringList.get(i).endsWith("-") && i < stringList.size() - 1)
         {
            String temp = stringList.get(i);
            temp = temp.substring(0, temp.length() - 1);
            searchList.addAll(stringTokener.getString(temp.trim(), true));
            searchList.add("-" + stringList.get(++i).trim());
         }
         else
         {
            searchList.addAll(stringTokener.getString(stringList.get(i), true));
         }
      }
      lastSearchCriteriaItems = searchList
         .toArray(new String[searchList.size()]);

      refreshSearch();
      refreshCounter();
   }

   public void refreshSearch()
   {
      component.refreshViewer();
   }

   public boolean matchesSearchCriteria(Object item)
   {
      if (lastSearchCriteriaItems == null)
      {
         return true;
      }
      StringBuilder fullText = new StringBuilder();
      SendableEntityCreator creatorClass = component.getMap().getCreatorClass(
         item);
      // SEARCH FOR #ID:3
      if (creatorClass != null)
      {
         for (String property : searchProperties)
         {
            Object value = creatorClass.getValue(item, property);
            if (value != null)
            {
               fullText.append(" " + value.toString().toLowerCase());
            }
         }
      }

      Boolean matches = true;
      for (String word : lastSearchCriteriaItems)
      {
         if (!"".equals(word))
         {
            int pos = word.indexOf(":");
            if (word.startsWith("#") && pos > 1)
            {
               String propString = word.substring(1, pos);

               if (searchProperties.contains(propString))
               {
                  String value = word.substring(pos + 1);
                  Object objValue = creatorClass.getValue(item,
                     word.substring(1, pos));
                  if (objValue != null)
                  {
                     String itemValue = objValue.toString().toLowerCase();
                     // Search for simple Property
                     if (itemValue.indexOf(value) < 0)
                     {
                        return false;
                     }
                  }
               }
               else
               {
                  return false;
               }
            }
            else if (word.startsWith("-") && word.length() > 1)
            {
               if (fullText.indexOf(word.substring(1)) >= 0)
               {
                  return false;
               }
            }
            else
            {
               if (fullText.indexOf(word) < 0)
               {
                  // no this search word is not found in full text
                  return false;
               }
            }
         }
      }

      return matches;
   }

   public boolean setCounterField(Column column)
   {
      this.updateField = column;
      refreshCounter();
      return true;
   }

   public void refreshCounter()
   {
      if (updateField != null)
      {
         TableColumnInterface column = component.getColumn(updateField);
         if (column != null)
         {
            // column.UpdateCount()
            // FIXME column.getTableColumn().setText(updateField.getLabel() +
            // " (" + component.getTable(GUIPosition.CENTER).getItemCount() +
            // ")");
         }
      }
   }
}
