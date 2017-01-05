package org.sdmlib.models.tables.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.tables.Cell;
import org.sdmlib.models.tables.Column;
import org.sdmlib.models.tables.Row;

public class CellPO extends PatternObject<CellPO, Cell>
{

    public CellSet allMatches()
   {
      this.setDoAllMatches(true);
      
      CellSet matches = new CellSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Cell) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public CellPO(){
      newInstance(null);
   }

   public CellPO(Cell... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

   public CellPO(String modifier)
   {
      this.setModifier(modifier);
   }
   public CellPO createValueCondition(Object value)
   {
      new AttributeConstraint()
      .withAttrName(Cell.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public CellPO createValueAssignment(Object value)
   {
      new AttributeConstraint()
      .withAttrName(Cell.PROPERTY_VALUE)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(Pattern.CREATE)
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public Object getValue()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Cell) getCurrentMatch()).getValue();
      }
      return null;
   }
   
   public CellPO withValue(Object value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Cell) getCurrentMatch()).setValue(value);
      }
      return this;
   }
   
   public RowPO createRowPO()
   {
      RowPO result = new RowPO(new Row[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cell.PROPERTY_ROW, result);
      
      return result;
   }

   public RowPO createRowPO(String modifier)
   {
      RowPO result = new RowPO(new Row[]{});
      
      result.setModifier(modifier);
      super.hasLink(Cell.PROPERTY_ROW, result);
      
      return result;
   }

   public CellPO createRowLink(RowPO tgt)
   {
      return hasLinkConstraint(tgt, Cell.PROPERTY_ROW);
   }

   public CellPO createRowLink(RowPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Cell.PROPERTY_ROW, modifier);
   }

   public Row getRow()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Cell) this.getCurrentMatch()).getRow();
      }
      return null;
   }

   public ColumnPO createColumnPO()
   {
      ColumnPO result = new ColumnPO(new Column[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Cell.PROPERTY_COLUMN, result);
      
      return result;
   }

   public ColumnPO createColumnPO(String modifier)
   {
      ColumnPO result = new ColumnPO(new Column[]{});
      
      result.setModifier(modifier);
      super.hasLink(Cell.PROPERTY_COLUMN, result);
      
      return result;
   }

   public CellPO createColumnLink(ColumnPO tgt)
   {
      return hasLinkConstraint(tgt, Cell.PROPERTY_COLUMN);
   }

   public CellPO createColumnLink(ColumnPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Cell.PROPERTY_COLUMN, modifier);
   }

   public Column getColumn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Cell) this.getCurrentMatch()).getColumn();
      }
      return null;
   }

}
