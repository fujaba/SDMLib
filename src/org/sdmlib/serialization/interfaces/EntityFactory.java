package org.sdmlib.serialization.interfaces;

public interface EntityFactory extends SendableEntityCreator
{
   /**
    * Calls entity.removeYou().
    *
    * @param entity the entity to be deleted
    */
   public void removeObject(Object entity);


}
