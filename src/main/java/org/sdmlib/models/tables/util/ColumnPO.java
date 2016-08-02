package org.sdmlib.models.tables.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.tables.Column;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.tables.util.TablePO;
import org.sdmlib.models.tables.Table;
import org.sdmlib.models.tables.util.ColumnPO;
import org.sdmlib.models.tables.util.CellPO;
import org.sdmlib.models.tables.Cell;
import org.sdmlib.models.tables.util.CellSet;

public class ColumnPO extends PatternObject<ColumnPO, Column>
{

    public ColumnSet allMatches()
   {
      this.setDoAllMatches(true);
      
      ColumnSet matches = new ColumnSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Column) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public ColumnPO(){
      newInstance(null);
   }

   public ColumnPO(Column... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   
   public ColumnPO createNameCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Column.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ColumnPO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Column.PROPERTY_NAME)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ColumnPO createNameAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Column.PROPERTY_NAME)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ColumnPO createName(String value)
   {
      this.startCreate().createNameCondition(value).endCreate();
      return this;
   }
   
   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Column) getCurrentMatch()).getName();
      }
      return null;
   }
   
   public ColumnPO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Column) getCurrentMatch()).setName(value);
      }
      return this;
   }
   
   public TablePO createTablePO()
   {
      TablePO result = new TablePO(new Table[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Column.PROPERTY_TABLE, result);
      
      return result;
   }

   public TablePO createTable()
   {
      return this.startCreate().createTablePO().endCreate();
   }

   public ColumnPO createTablePO(TablePO tgt)
   {
      return hasLinkConstraint(tgt, Column.PROPERTY_TABLE);
   }

   public ColumnPO createTable(TablePO tgt)
   {
      return this.startCreate().createTablePO(tgt).endCreate();
   }

   public Table getTable()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Column) this.getCurrentMatch()).getTable();
      }
      return null;
   }

   public CellPO createCellsPO()
   {
      CellPO result = new CellPO(new Cell[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Column.PROPERTY_CELLS, result);
      
      return result;
   }

   public CellPO createCells()
   {
      return this.startCreate().createCellsPO().endCreate();
   }

   public ColumnPO createCellsPO(CellPO tgt)
   {
      return hasLinkConstraint(tgt, Column.PROPERTY_CELLS);
   }

   public ColumnPO createCells(CellPO tgt)
   {
      return this.startCreate().createCellsPO(tgt).endCreate();
   }

   public CellSet getCells()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Column) this.getCurrentMatch()).getCells();
      }
      return null;
   }

   public ColumnPO createTdCssClassCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Column.PROPERTY_TDCSSCLASS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ColumnPO createTdCssClassCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Column.PROPERTY_TDCSSCLASS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ColumnPO createTdCssClassAssignment(String value)
   {
      new AttributeConstraint()
      .withAttrName(Column.PROPERTY_TDCSSCLASS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   

   
   public ColumnPO createTdCssClass(String value)
   {
      this.startCreate().createTdCssClassCondition(value).endCreate();
      return this;
   }
   
   public String getTdCssClass()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Column) getCurrentMatch()).getTdCssClass();
      }
      return null;
   }
   
   public ColumnPO withTdCssClass(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Column) getCurrentMatch()).setTdCssClass(value);
      }
      return this;
   }
   
   public ColumnPO createThCssClassCondition(String value)
   {
      new AttributeConstraint()
      .withAttrName(Column.PROPERTY_THCSSCLASS)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ColumnPO createThCssClassCondition(String lower, String upper)
   {
      new AttributeConstraint()
      .withAttrName(Column.PROPERTY_THCSSCLASS)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public ColumnPO createThCssClass(String value)
   {
      this.startCreate().createThCssClassCondition(value).endCreate();
      return this;
   }
   
   public String getThCssClass()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Column) getCurrentMatch()).getThCssClass();
      }
      return null;
   }
   
   public ColumnPO withThCssClass(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Column) getCurrentMatch()).setThCssClass(value);
      }
      return this;
   }
   
}
