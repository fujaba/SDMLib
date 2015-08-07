package org.sdmlib.models.classes.util;

import org.sdmlib.codegen.util.LocalVarTableEntryCreator;
import org.sdmlib.codegen.util.StatementEntryCreator;
import org.sdmlib.codegen.util.SymTabEntryCreator;
import org.sdmlib.serialization.SDMLibJsonIdMap;

import de.uniks.networkparser.json.JsonIdMap;

class CreatorCreator{

   public static JsonIdMap createIdMap(String sessionID)
   {
      JsonIdMap jsonIdMap = (JsonIdMap) new SDMLibJsonIdMap().withSessionId(sessionID);
      
      jsonIdMap.withCreator(new AssociationCreator());
      jsonIdMap.withCreator(new AttributeCreator());
      jsonIdMap.withCreator(new ClassModelCreator());
      jsonIdMap.withCreator(new ClazzCreator());
      jsonIdMap.withCreator(new ParameterCreator());
      jsonIdMap.withCreator(new RoleCreator());
      jsonIdMap.withCreator(new ValueCreator());
      jsonIdMap.withCreator(new MethodCreator());
      jsonIdMap.withCreator(new AnnotationCreator());
      jsonIdMap.withCreator(new EnumerationCreator());
      jsonIdMap.withCreator(new DataTypeCreator());
      jsonIdMap.withCreator(new ArrayListCreator());
      jsonIdMap.withCreator(new SDMLibClassCreator());
      jsonIdMap.withCreator(new ClassModelCreator());
      jsonIdMap.withCreator(new ClazzCreator());
      jsonIdMap.withCreator(new ValueCreator());
      jsonIdMap.withCreator(new AttributeCreator());
      jsonIdMap.withCreator(new MethodCreator());
      jsonIdMap.withCreator(new AnnotationCreator());
      jsonIdMap.withCreator(new EnumerationCreator());
      jsonIdMap.withCreator(new ParameterCreator());
      jsonIdMap.withCreator(new AssociationCreator());
      jsonIdMap.withCreator(new RoleCreator());
      jsonIdMap.withCreator(new SymTabEntryCreator());
      jsonIdMap.withCreator(new LocalVarTableEntryCreator());
      jsonIdMap.withCreator(new StatementEntryCreator());
      jsonIdMap.withCreator(new DataTypeCreator());
      jsonIdMap.withCreator(new ArrayListCreator());
      jsonIdMap.withCreator(new SDMLibClassCreator());
      jsonIdMap.withCreator(new ClassModelCreator());
      jsonIdMap.withCreator(new ClazzCreator());
      jsonIdMap.withCreator(new ValueCreator());
      jsonIdMap.withCreator(new AttributeCreator());
      jsonIdMap.withCreator(new MethodCreator());
      jsonIdMap.withCreator(new AnnotationCreator());
      jsonIdMap.withCreator(new EnumerationCreator());
      jsonIdMap.withCreator(new ParameterCreator());
      jsonIdMap.withCreator(new AssociationCreator());
      jsonIdMap.withCreator(new RoleCreator());
      jsonIdMap.withCreator(new SymTabEntryCreator());
      jsonIdMap.withCreator(new LocalVarTableEntryCreator());
      jsonIdMap.withCreator(new StatementEntryCreator());
      jsonIdMap.withCreator(new DataTypeCreator());
      jsonIdMap.withCreator(new ArrayListCreator());
      jsonIdMap.withCreator(new SDMLibClassCreator());
      jsonIdMap.withCreator(new ClassModelCreator());
      jsonIdMap.withCreator(new ClazzCreator());
      jsonIdMap.withCreator(new ValueCreator());
      jsonIdMap.withCreator(new AttributeCreator());
      jsonIdMap.withCreator(new MethodCreator());
      jsonIdMap.withCreator(new AnnotationCreator());
      jsonIdMap.withCreator(new EnumerationCreator());
      jsonIdMap.withCreator(new ParameterCreator());
      jsonIdMap.withCreator(new AssociationCreator());
      jsonIdMap.withCreator(new RoleCreator());
      jsonIdMap.withCreator(new SymTabEntryCreator());
      jsonIdMap.withCreator(new LocalVarTableEntryCreator());
      jsonIdMap.withCreator(new StatementEntryCreator());
      jsonIdMap.withCreator(new DataTypeCreator());
      jsonIdMap.withCreator(new ArrayListCreator());
      return jsonIdMap;
   }
}
