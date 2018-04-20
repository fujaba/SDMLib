package org.sdmlib.models.tables.util;

import org.sdmlib.models.pattern.AttributeConstraint;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.PatternObject;
import org.sdmlib.models.tables.Column;
import org.sdmlib.models.tables.Row;
import org.sdmlib.models.tables.Table;
   /**
    * 
    * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightTablesAndReports
 */
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


     /**
    * 
    * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightTablesAndReports
 */
   public TablePO(){
      newInstance(null);
   }

     /**
    * 
    * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightTablesAndReports
 */
   public TablePO(Table... hostGraphObject) {
      if(hostGraphObject==null || hostGraphObject.length<1){
         return ;
      }
      newInstance(null, hostGraphObject);
   }

     /**
    * 
    * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightTablesAndReports
 */
   public TablePO(String modifier)
   {
      this.setModifier(modifier);
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
      ColumnPO result = new ColumnPO(new Column[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Table.PROPERTY_COLUMNS, result);
      
      return result;
   }

   public ColumnPO createColumnsPO(String modifier)
   {
      ColumnPO result = new ColumnPO(new Column[]{});
      
      result.setModifier(modifier);
      super.hasLink(Table.PROPERTY_COLUMNS, result);
      
      return result;
   }

   public TablePO createColumnsLink(ColumnPO tgt)
   {
      return hasLinkConstraint(tgt, Table.PROPERTY_COLUMNS);
   }

   public TablePO createColumnsLink(ColumnPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Table.PROPERTY_COLUMNS, modifier);
   }

   public ColumnSet getColumns()
   {
      if (this.getPattern().getHasMatch())
      {
         return ((Table) this.getCurrentMatch()).getColumns();
      }
      return null;
   }

     /**
    * 
    * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightTablesAndReports
 */
   public RowPO createRowsPO()
   {
      RowPO result = new RowPO(new Row[]{});
      
      result.setModifier(this.getPattern().getModifier());
      super.hasLink(Table.PROPERTY_ROWS, result);
      
      return result;
   }

     /**
    * 
    * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsStoryboards#testStudyRightTablesAndReports
 */
   public RowPO createRowsPO(String modifier)
   {
      RowPO result = new RowPO(new Row[]{});
      
      result.setModifier(modifier);
      super.hasLink(Table.PROPERTY_ROWS, result);
      
      return result;
   }

   public TablePO createRowsLink(RowPO tgt)
   {
      return hasLinkConstraint(tgt, Table.PROPERTY_ROWS);
   }

   public TablePO createRowsLink(RowPO tgt, String modifier)
   {
      return hasLinkConstraint(tgt, Table.PROPERTY_ROWS, modifier);
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
