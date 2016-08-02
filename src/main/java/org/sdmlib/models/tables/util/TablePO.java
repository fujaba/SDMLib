package org.sdmlib.models.tables.util;

import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.tables.Table;
import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.tables.util.ColumnPO;
import org.sdmlib.models.tables.Column;
import org.sdmlib.models.tables.util.TablePO;
import org.sdmlib.models.tables.util.ColumnSet;
import org.sdmlib.models.tables.util.RowPO;
import org.sdmlib.models.tables.Row;
import org.sdmlib.models.tables.util.RowSet;

public class TablePO extends PatternObject<TablePO, Table>
{

   public TableSet allMatches()
   {
      this.setDoAllMatches(true);

      TableSet matches = new TableSet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((Table) this.getCurrentMatch());

         this.getPattern().findMatch();
      }

      return matches;
   }


   public TablePO()
   {
      newInstance(null);
   }


   public TablePO(String modifier)
   {
      this.withModifier(modifier);
   }


   public TablePO(Table... hostGraphObject)
   {
      if (hostGraphObject == null || hostGraphObject.length < 1)
      {
         return;
      }
      newInstance(null, hostGraphObject);
   }


   public TablePO createNameAssignment(String value)
   {
      new AttributeConstraint()
         .withAttrName(Table.PROPERTY_NAME)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(Pattern.CREATE)
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public TablePO createNameCondition(String lower, String upper)
   {
      new AttributeConstraint()
         .withAttrName(Table.PROPERTY_NAME)
         .withTgtValue(lower)
         .withUpperTgtValue(upper)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public TablePO createNameCondition(String value)
   {
      new AttributeConstraint()
         .withAttrName(Table.PROPERTY_NAME)
         .withTgtValue(value)
         .withSrc(this)
         .withModifier(this.getPattern().getModifier())
         .withPattern(this.getPattern());

      super.filterAttr();

      return this;
   }


   public TablePO createName(String value)
   {
      this.startCreate().createNameCondition(value).endCreate();
      return this;
   }


   public String getName()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Table) getCurrentMatch()).getName();
      }
      return null;
   }


   public TablePO withName(String value)
   {
      if (this.getPattern().getHasMatch())
      {
         ((Table) getCurrentMatch()).setName(value);
      }
      return this;
   }


   public ColumnPO createColumnsPO()
   {
      ColumnPO result = new ColumnPO(new Column[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Table.PROPERTY_COLUMNS, result);

      return result;
   }


   public ColumnPO createColumnsPO(String modifier)
   {
      ColumnPO result = new ColumnPO(new Column[]
      {});

      result.setModifier(modifier);
      
      super.hasLink(Table.PROPERTY_COLUMNS, result);

      return result;
   }


   public ColumnPO createColumns()
   {
      return this.startCreate().createColumnsPO().endCreate();
   }


   public TablePO createColumnsPO(ColumnPO tgt)
   {
      return hasLinkConstraint(tgt, Table.PROPERTY_COLUMNS);
   }


   public TablePO createColumns(ColumnPO tgt)
   {
      return this.startCreate().createColumnsPO(tgt).endCreate();
   }


   public ColumnSet getColumns()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Table) this.getCurrentMatch()).getColumns();
      }
      return null;
   }


   public RowPO createRowsPO()
   {
      RowPO result = new RowPO(new Row[]
      {});

      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Table.PROPERTY_ROWS, result);

      return result;
   }


   public RowPO createRows()
   {
      return this.startCreate().createRowsPO().endCreate();
   }


   public TablePO createRowsPO(RowPO tgt)
   {
      return hasLinkConstraint(tgt, Table.PROPERTY_ROWS);
   }


   public TablePO createRows(RowPO tgt)
   {
      return this.startCreate().createRowsPO(tgt).endCreate();
   }


   public RowSet getRows()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Table) this.getCurrentMatch()).getRows();
      }
      return null;
   }

}
