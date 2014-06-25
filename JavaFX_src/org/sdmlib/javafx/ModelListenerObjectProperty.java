package org.sdmlib.javafx;

/*
 NetworkParser
 Copyright (c) 2011 - 2013, Stefan Lindel
 All rights reserved.

 Licensed under the EUPL, Version 1.1 or as soon they
 will be approved by the European Commission - subsequent
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
import javafx.beans.Observable;
import de.uniks.networkparser.interfaces.SendableEntityCreator;

public class ModelListenerObjectProperty extends ModelListenerProperty<Object>
{
   public ModelListenerObjectProperty(SendableEntityCreator creator,
         Object item, String property)
   {
      super(creator, item, property);
   }

   @Override
   public void invalidated(Observable arg0)
   {
   }

   @Override
   public Object getValue()
   {
      return creator.getValue(item, property);
   }
}
