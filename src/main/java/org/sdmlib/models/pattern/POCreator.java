package org.sdmlib.models.pattern;

import java.util.LinkedHashMap;

import org.sdmlib.models.SDMLibIdMap;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import de.uniks.networkparser.list.SimpleSet;

public class POCreator
{

   LinkedHashMap<SendableEntity, PatternObject<PatternObject, SendableEntity>> alreadyCreatedPOs = new LinkedHashMap<>();
   private boolean nullFilter = false;

   /**
    * If Enabled, Properties that are null aren't considered in the
    * PatternObjects
    * 
    * @param nullFilter boolean for NullFilter
    */
   public void setNullFilter(boolean nullFilter)
   {
      this.nullFilter = nullFilter;
   }

   public POCreator withNullFilter(boolean nullFilter)
   {
      setNullFilter(nullFilter);
      return this;
   }

   /**
    * Creates a Pattern, that matches the given entity
    * 
    * @param entity
    *           The RootEntity that should be described as a Pattern
    * @return Returns the PatternObject, that describes the RootEntity
    */
   public PatternObject createPO(SendableEntity entity)
   {
      return createPO(entity, new SDMLibIdMap("s"), null, null);
   }

   /**
    * Creates a Pattern, that matches the given entity
    * 
    * @param entity
    *           The RootEntity that should be described as a Pattern
    * @param creators
    *           A IDMap with all the needed Creators
    * @return Returns the PatternObject, that describes the RootEntity
    */
   public PatternObject createPO(SendableEntity entity, IdMap creators)
   {
      return createPO(entity, creators, null, null);
   }

   /**
    * 
    * @param entity
    *           The RootEntity that should be described as a Pattern
    * @param creators
    *           A IDMap with all the needed Creators
    * @param parentPO
    *           if there's a PO that has an outgoing to the entity, we need the
    *           parent PO
    * @param incommingProperty
    *           The PropertyName of the Link from the parentPO to the entity
    * @return Returns the PatternObject, that describes the RootEntity
    */
   protected PatternObject createPO(SendableEntity entity, IdMap creators, PatternObject parentPO,
         String incommingProperty)
   {
      if (alreadyCreatedPOs.containsKey(entity))
      {
         return alreadyCreatedPOs.get(entity);
      }
      // TODO: is this the best solution for finding the corresponding POs?
      SendableEntityCreator poCreator = creators.getCreator(entity.getClass().getPackage().getName() + ".util."
         + entity.getClass().getSimpleName() + "PO", true, null);
      SendableEntityCreator creator = creators.getCreator(entity.getClass().getName(), true, null);
      PatternObject po = (PatternObject) poCreator.getSendableInstance(false);
      if (parentPO != null && incommingProperty != null)
      {
         parentPO.hasLink(incommingProperty, po);
      }

      alreadyCreatedPOs.put(entity, po);

      for (String property : creator.getProperties())
      {
         Object value = creator.getValue(entity, property);
         // filter out those values, that are not set if nullFilter is enabled
         if (this.nullFilter && value == null)
         {
            continue;
         }

         if (value instanceof SimpleSet)
         {
            // If to Many
            ((SimpleSet<SendableEntity>) value).forEach(t -> setValue(
               po, property, createPO(t, creators, po, property)));
         }
         else if (value instanceof SendableEntity)
         {
            // If to One
            setValue(
               po, property, createPO((SendableEntity) value, creators, po, property));
         }
         else
         {
            // just filter the value
            setValue(po, property, value);
         }
      }

      return po;
   }

   /**
    * For Associations
    * 
    * @param po
    *           The PatternObject that should filter for a Value
    * @param property
    *           The Property that should be filtered
    * @param createdPO
    *           The PatternObject, that describes the Value
    * @return Returns the PatternObject
    */
   private Object setValue(PatternObject po, String property, PatternObject createdPO)
   {
      return po.hasLinkConstraint(createdPO, property);
   }

   /**
    * For simple Properties
    * 
    * @param po
    *           The PatternObject that should filter for a Value
    * @param property
    *           The Property that should be filtered
    * @param targetValue
    *           The Value we are looking for
    * @return Returns the PatternObject
    */
   private Object setValue(PatternObject po, String property, Object targetValue)
   {
      // Currently only Equality... No Upper/lower value...
      new AttributeConstraint()
         .withAttrName(property)
         .withTgtValue(targetValue)
         .withSrc(po)
         .withModifier(po.getPattern().getModifier())
         .withPattern(po.getPattern());

      if (!po.getPattern().findMatch())
      {
         po.setCurrentMatch(null);
      }

      return po;
   }

}
