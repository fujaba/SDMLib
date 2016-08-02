package org.sdmlib.models.tables.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.tables.Cell;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.tables.util.RowPO;
import org.sdmlib.models.tables.Row;
import org.sdmlib.models.tables.util.CellPO;
import org.sdmlib.models.tables.util.ColumnPO;
import org.sdmlib.models.tables.Column;

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
   
   public CellPO createValue(Object value)
   {
      this.startCreate().createValueCondition(value).endCreate();
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

   public RowPO createRow()
   {
      return this.startCreate().createRowPO().endCreate();
   }

   public CellPO createRowPO(RowPO tgt)
   {
      return hasLinkConstraint(tgt, Cell.PROPERTY_ROW);
   }

   public CellPO createRow(RowPO tgt)
   {
      return this.startCreate().createRowPO(tgt).endCreate();
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

   public CellPO createColumnPO(ColumnPO tgt)
   {
      return hasLinkConstraint(tgt, Cell.PROPERTY_COLUMN);
   }

   public Column getColumn()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Cell) this.getCurrentMatch()).getColumn();
      }
      return null;
   }

   public ColumnPO createColumn()
   {
      return this.startCreate().createColumnPO().endCreate();
   }

   public CellPO createColumn(ColumnPO tgt)
   {
      return this.startCreate().createColumnPO(tgt).endCreate();
   }

}
