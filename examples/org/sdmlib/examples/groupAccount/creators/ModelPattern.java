package org.sdmlib.examples.groupAccount.creators;

import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.examples.groupAccount.creators.GroupAccountPO;
import org.sdmlib.examples.groupAccount.GroupAccount;
import org.sdmlib.examples.groupAccount.creators.PersonPO;
import org.sdmlib.examples.groupAccount.Person;
import org.sdmlib.examples.groupAccount.creators.ItemPO;
import org.sdmlib.examples.groupAccount.Item;

public class ModelPattern extends Pattern
{
   public ModelPattern()
   {
      super(CreatorCreator.createIdMap("hg"));
   }
   
   public ModelPattern startCreate()
   {
      super.startCreate();
      return this;
   }

   public GroupAccountPO hasElementGroupAccountPO()
   {
      GroupAccountPO value = new GroupAccountPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public GroupAccountPO hasElementGroupAccountPO(GroupAccount hostGraphObject)
   {
      GroupAccountPO value = new GroupAccountPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public PersonPO hasElementPersonPO()
   {
      PersonPO value = new PersonPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public PersonPO hasElementPersonPO(Person hostGraphObject)
   {
      PersonPO value = new PersonPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

   public ItemPO hasElementItemPO()
   {
      ItemPO value = new ItemPO();
      this.addToElements(value);
      value.setModifier(this.getModifier());
      
      this.findMatch();
      
      return value;
   }
   
   public ItemPO hasElementItemPO(Item hostGraphObject)
   {
      ItemPO value = new ItemPO();
      this.addToElements(value);
      value.setModifier(Pattern.BOUND);
      
      value.setCurrentMatch(hostGraphObject);
      
      this.findMatch();
      
      return value;
   } 

}


