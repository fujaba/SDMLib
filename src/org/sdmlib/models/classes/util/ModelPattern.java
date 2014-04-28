package org.sdmlib.models.classes.util;

import org.sdmlib.codegen.LocalVarTableEntry;
import org.sdmlib.codegen.StatementEntry;
import org.sdmlib.codegen.SymTabEntry;
import org.sdmlib.codegen.util.LocalVarTableEntryPO;
import org.sdmlib.codegen.util.StatementEntryPO;
import org.sdmlib.codegen.util.SymTabEntryPO;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.pattern.Pattern;

public class ModelPattern extends Pattern<ModelPattern>
{
  
   @Override
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public ClassModelPO hasElementClassModelPO()
   {
      ClassModelPO value = new ClassModelPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ClassModelPO hasElementClassModelPO(ClassModel hostGraphObject)
   {
      ClassModelPO value = new ClassModelPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ClazzPO hasElementClazzPO()
   {
      ClazzPO value = new ClazzPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ClazzPO hasElementClazzPO(Clazz hostGraphObject)
   {
      ClazzPO value = new ClazzPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public AttributePO hasElementAttributePO()
   {
      AttributePO value = new AttributePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public AttributePO hasElementAttributePO(Attribute hostGraphObject)
   {
      AttributePO value = new AttributePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public MethodPO hasElementMethodPO()
   {
      MethodPO value = new MethodPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public MethodPO hasElementMethodPO(Method hostGraphObject)
   {
      MethodPO value = new MethodPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public AssociationPO hasElementAssociationPO()
   {
      AssociationPO value = new AssociationPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public AssociationPO hasElementAssociationPO(Association hostGraphObject)
   {
      AssociationPO value = new AssociationPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public RolePO hasElementRolePO()
   {
      RolePO value = new RolePO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public RolePO hasElementRolePO(Role hostGraphObject)
   {
      RolePO value = new RolePO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public SymTabEntryPO hasElementSymTabEntryPO()
   {
      SymTabEntryPO value = new SymTabEntryPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public SymTabEntryPO hasElementSymTabEntryPO(SymTabEntry hostGraphObject)
   {
      SymTabEntryPO value = new SymTabEntryPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public LocalVarTableEntryPO hasElementLocalVarTableEntryPO()
   {
      LocalVarTableEntryPO value = new LocalVarTableEntryPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public LocalVarTableEntryPO hasElementLocalVarTableEntryPO(LocalVarTableEntry hostGraphObject)
   {
      LocalVarTableEntryPO value = new LocalVarTableEntryPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public StatementEntryPO hasElementStatementEntryPO()
   {
      StatementEntryPO value = new StatementEntryPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public StatementEntryPO hasElementStatementEntryPO(StatementEntry hostGraphObject)
   {
      StatementEntryPO value = new StatementEntryPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 
}
