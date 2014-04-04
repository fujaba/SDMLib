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

public class XSDEntity extends XMLEntity
{
   public static final String PROPERTY_CHOICE = "choice";
   public static final String PROPERTY_SEQUENCE = "sequence";
   public static final String PROPERTY_ATTRIBUTE = "attribute";
   public static final String PROPERTY_MINOCCURS = "minOccurs";
   public static final String PROPERTY_MAXOCCURS = "minOccurs";

   private ArrayList<XSDEntity> choice;
   private ArrayList<XSDEntity> sequence;
   private ArrayList<String> attribute;
   private String minOccurs;
   private String maxOccurs;

   public ArrayList<XSDEntity> getChoice()
   {
      return choice;
   }

   public void setChoice(ArrayList<XSDEntity> choice)
   {
      this.choice = choice;
   }

   public ArrayList<XSDEntity> getSequence()
   {
      return sequence;
   }

   public void setSequence(ArrayList<XSDEntity> sequence)
   {
      this.sequence = sequence;
   }

   public ArrayList<String> getAttribute()
   {
      return attribute;
   }

   public void setAttribute(ArrayList<String> attribute)
   {
      this.attribute = attribute;
   }

   public String getMinOccurs()
   {
      return minOccurs;
   }

   public void setMinOccurs(String minOccurs)
   {
      this.minOccurs = minOccurs;
   }

   public String getMaxOccurs()
   {
      return maxOccurs;
   }

   public void setMaxOccurs(String maxOccurs)
   {
      this.maxOccurs = maxOccurs;
   }
}
