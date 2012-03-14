/*
   Copyright (c) 2012 zuendorf 
   
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
   
package org.sdmlib.models.classes;

import org.sdmlib.codegen.Parser;

public class Association
{
   public Association withSource(String roleName, Clazz sourceClass, String card)
   {
      withSource(roleName, sourceClass, card, Role.VANILLA);
      return this;
   }


   public Association withSource(String roleName, Clazz sourceClass, String card,
      String kind)
   {
      setSource(new Role()
      .withName(roleName)
      .withClazz(sourceClass)
      .withCard(card)
      .withKind(kind));
      
      return this;
   }

 
   public Association withTarget(String roleName, Clazz targetClass, String card)
   {
      withTarget(roleName, targetClass, card, Role.VANILLA);
      return this;
   }


   public Association withTarget(String roleName, Clazz targetClass, String card,
      String kind)
   {
      setTarget(new Role()
      .withName(roleName)
      .withClazz(targetClass)
      .withCard(card)
      .withKind(kind));
      
      return this;
   }

 
   public Association generate(String rootDir)
   {
      generate(rootDir, true);
      
      return this;
   }
   
   public Association generate(String rootDir, boolean doGenerate)
   {
      // open source class and get or insert role implementation
      // Parser sourceClassParser = getSourceClass().getOrCreateParser(rootDir);
      getSource().generate(rootDir, getTarget(), doGenerate);
      
      // open target class and get or insert role implementation
      getTarget().generate(rootDir, getSource(), doGenerate);
      
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_SOURCE = "source";
   
   private Role source;
   
   public Role getSource()
   {
      return this.source;
   }
   
   public void setSource(Role value)
   {
      this.source = value;
   }
   
   public Association withSource(Role value)
   {
      setSource(value);
      return this;
   } 

   
   //==========================================================================
   
   public static final String PROPERTY_TARGET = "target";
   
   private Role target;
   
   public Role getTarget()
   {
      return this.target;
   }
   
   public void setTarget(Role value)
   {
      this.target = value;
   }
   
   public Association withTarget(Role value)
   {
      setTarget(value);
      return this;
   } 
}





