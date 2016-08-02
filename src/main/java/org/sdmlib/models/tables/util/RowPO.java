package org.sdmlib.models.tables.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.tables.Row;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.tables.util.TablePO;
import org.sdmlib.models.tables.Table;
import org.sdmlib.models.tables.util.RowPO;
import org.sdmlib.models.tables.util.CellPO;
import org.sdmlib.models.tables.Cell;
import org.sdmlib.models.tables.util.CellSet;

public class RowPO extends PatternObject<RowPO, Row>
{

    public RowSet allMatches()
   {
      this.setDoAllMatches(true);
      
      RowSet matches = new RowSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Row) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }


   public RowPO(){
      newInstance(null);
   }

   public RowPO(Row... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }
   public RowPO createNumberCondition(int value)
   {
      new AttributeConstraint()
      .withAttrName(Row.PROPERTY_NUMBER)
      .withTgtValue(value)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RowPO createNumberCondition(int lower, int upper)
   {
      new AttributeConstraint()
      .withAttrName(Row.PROPERTY_NUMBER)
      .withTgtValue(lower)
      .withUpperTgtValue(upper)
      .withSrc(this)
      .withModifier(this.getPattern().getModifier())
      .withPattern(this.getPattern());
      
      super.filterAttr();
      
      return this;
   }
   
   public RowPO createNumber(int value)
   {
      this.startCreate().createNumberCondition(value).endCreate();
      return this;
   }
   
   public int getNumber()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Row) getCurrentMatch()).getNumber();
      }
      return 0;
   }
   
   public RowPO withNumber(int value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Row) getCurrentMatch()).setNumber(value);
      }
      return this;
   }
   
   public TablePO createTablePO()
   {
      TablePO result = new TablePO(new Table[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Row.PROPERTY_TABLE, result);
      
      return result;
   }

   public TablePO createTable()
   {
      return this.startCreate().createTablePO().endCreate();
   }

   public RowPO createTablePO(TablePO tgt)
   {
      return hasLinkConstraint(tgt, Row.PROPERTY_TABLE);
   }

   public RowPO createTable(TablePO tgt)
   {
      return this.startCreate().createTablePO(tgt).endCreate();
   }

   public Table getTable()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Row) this.getCurrentMatch()).getTable();
      }
      return null;
   }

   public CellPO createCellsPO()
   {
      CellPO result = new CellPO(new Cell[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Row.PROPERTY_CELLS, result);
      
      return result;
   }

   public CellPO createCells()
   {
      return this.startCreate().createCellsPO().endCreate();
   }

   public RowPO createCellsPO(CellPO tgt)
   {
      return hasLinkConstraint(tgt, Row.PROPERTY_CELLS);
   }

   public RowPO createCells(CellPO tgt)
   {
      return this.startCreate().createCellsPO(tgt).endCreate();
   }

   public CellSet getCells()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Row) this.getCurrentMatch()).getCells();
      }
      return null;
   }

}
