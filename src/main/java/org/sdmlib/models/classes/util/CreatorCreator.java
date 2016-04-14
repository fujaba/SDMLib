package org.sdmlib.models.classes.util;

import org.sdmlib.codegen.util.LocalVarTableEntryCreator;
import org.sdmlib.codegen.util.StatementEntryCreator;
import org.sdmlib.codegen.util.SymTabEntryCreator;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String sessionID)
   {
      IdMap jsonIdMap = new IdMap().withSessionId(sessionID);
      jsonIdMap.with(new ClassModelCreator());
      jsonIdMap.with(new SymTabEntryCreator());
      jsonIdMap.with(new LocalVarTableEntryCreator());
      jsonIdMap.with(new StatementEntryCreator());
      jsonIdMap.with(new DataTypeCreator());
      jsonIdMap.with(new ArrayListCreator());
      jsonIdMap.with(new SDMLibClassCreator());
      jsonIdMap.with(new ClazzCreator());
      jsonIdMap.with(new ValueCreator());
      jsonIdMap.with(new AttributeCreator());
      jsonIdMap.with(new MethodCreator());
      jsonIdMap.with(new AnnotationCreator());
      jsonIdMap.with(new EnumerationCreator());
      jsonIdMap.with(new ParameterCreator());
      jsonIdMap.with(new AssociationCreator());
      jsonIdMap.with(new RoleCreator());
      return jsonIdMap;
   }
}
