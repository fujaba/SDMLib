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
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class XsdValidationLoggingErrorHandler implements ErrorHandler
{
   private boolean isValid = true;
   private ArrayList<String> warnings = new ArrayList<String>();
   private ArrayList<String> errors = new ArrayList<String>();

   public void warning(SAXParseException ex) throws SAXException
   {
      isValid = false;
      warnings.add("Warnung: " + ex.getMessage());
   }

   public void error(SAXParseException ex) throws SAXException
   {
      isValid = false;
      errors.add("Fehler: " + ex.getMessage());
   }

   public void fatalError(SAXParseException ex) throws SAXException
   {
      isValid = false;
      errors.add("Fataler Fehler: " + ex.getMessage());
   }

   public boolean isValid()
   {
      return isValid;
   }

   public ArrayList<String> getErrors()
   {
      return errors;
   }

   public String getErrorText()
   {
      StringBuilder sb = new StringBuilder();
      for (String item : errors)
      {
         sb.append(item + "\n");
      }
      sb.append("ERRORS: " + errors.size());
      return sb.toString();
   }

   public ArrayList<String> getWarnings()
   {
      return warnings;
   }
}
