/*
 -----------------------------------------------------------------------------
  (c) Copyright IBM Corp. 2003  All rights reserved.

 The sample program(s) is/are owned by International Business Machines
 Corporation or one of its subsidiaries ("IBM") and is/are copyrighted and
 licensed, not sold.

 You may copy, modify, and distribute this/these sample program(s) in any form
 without payment to IBM, for any purpose including developing, using, marketing
 or distributing programs that include or are derivative works of the sample
 program(s).

 The sample program(s) is/are provided to you on an "AS IS" basis, without
 warranty of any kind.  IBM HEREBY EXPRESSLY DISCLAIMS ALL WARRANTIES, EITHER
 EXPRESS OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE.  Some jurisdictions do
 not allow for the exclusion or limitation of implied warranties, so the above
 limitations or exclusions may not apply to you.  IBM shall not be liable for
 any damages you suffer as a result of using, modifying or distributing the
 sample program(s) or its/their derivatives.

 Each copy of any portion of this/these sample program(s) or any derivative
 work, must include the above copyright notice and disclaimer of warranty.

 -----------------------------------------------------------------------------
*/

package swing2swt.layout;

import java.awt.GridBagConstraints;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Port of AWT GridBagLayout to SWT.
 * @author Yannick Saillet
 */
public class GridBagLayout extends AWTLayout {

  public int[] columnWidths;
  public int[] rowHeights;
  public double[] columnWeights;
  public double[] rowWeights;

  private GridInfo gridInfo;

  protected Point computeSize(
    Composite composite,
    int wHint,
    int hHint,
    boolean flushCache) {
    if (flushCache || gridInfo == null)
      gridInfo = computeGridInfo(composite);
    Point size = new Point(0, 0);

    for (int i = 0; i < gridInfo.nbCols; i++)
      size.x += gridInfo.m_columnWidths[i];

    for (int i = 0; i < gridInfo.nbRows; i++)
      size.y += gridInfo.m_rowHeights[i];
    return size;
  }

  protected void layout(Composite composite, boolean flushCache) {
    Rectangle clientArea = composite.getClientArea();
    if (flushCache || gridInfo == null)
      gridInfo = computeGridInfo(composite);

    //*** first calculate the real size of the cells, depending on the size 
    //*** of the container
    int totalColumnsWidth = 0;
    for (int i = 0; i < gridInfo.nbCols; i++) {
      totalColumnsWidth += gridInfo.m_columnWidths[i];
    }

    int totalRowsHeight = 0;
    for (int i = 0; i < gridInfo.nbRows; i++) {
      totalRowsHeight += gridInfo.m_rowHeights[i];
    }

    //*** redistribute extra/missing space according to the weights
    int deltaWidth = clientArea.width - totalColumnsWidth;
    for (int i = 0; i < gridInfo.nbCols; i++)
      gridInfo.m_columnWidths[i] += gridInfo.m_columnWeights[i]
        * deltaWidth;

    int deltaHeight = clientArea.height - totalRowsHeight;
    for (int i = 0; i < gridInfo.nbRows; i++)
      gridInfo.m_rowHeights[i] += gridInfo.m_rowWeights[i] * deltaHeight;

    //*** calculate the origin of the group of cells:
    //*** if no weight were set, the components must be centered
    int origX = 0;
    int origY = 0;

    double totalWeight = 0.0;
    totalColumnsWidth = 0;
    for (int i = 0; i < gridInfo.nbCols; i++) {
      totalColumnsWidth += gridInfo.m_columnWidths[i];
      totalWeight += gridInfo.m_columnWeights[i];
    }
    if (totalWeight == 0.0 && clientArea.width > totalColumnsWidth)
      origX = (clientArea.width - totalColumnsWidth) / 2;

    totalWeight = 0.0;
    totalRowsHeight = 0;
    for (int i = 0; i < gridInfo.nbCols; i++) {
      totalRowsHeight += gridInfo.m_rowHeights[i];
      totalWeight += gridInfo.m_rowWeights[i];
    }
    if (totalWeight == 0.0 && clientArea.height > totalRowsHeight)
      origY = (clientArea.height - totalRowsHeight) / 2;

    //*** arrange the children

    Control[] children = composite.getChildren();
    for (int i = 0; i < children.length; i++) {
      //if (!children[i].getVisible())
      //  continue;
      GridBagConstraints c = getConstraints(children[i]);
      // compute the location and size of the cell
      int x = 0;
      int y = 0;
      int w = 0;
      int h = 0;
      for (int j = 0; j < c.gridx; j++)
        x += gridInfo.m_columnWidths[j];
      for (int j = 0; j < c.gridy; j++)
        y += gridInfo.m_rowHeights[j];
      for (int j = c.gridx; j < c.gridx + c.gridwidth; j++)
        w += gridInfo.m_columnWidths[j];
      for (int j = c.gridy; j < c.gridy + c.gridheight; j++)
        h += gridInfo.m_rowHeights[j];

      // adjust with the insets
      if (c.insets != null) {
        x += c.insets.left;
        y += c.insets.top;
        w -= c.insets.left + c.insets.right;
        h += c.insets.top + c.insets.bottom;
      }

      // compute the size of the component, depending on the constraint fill
      Point preferredSize =
        getPreferredSize(children[i], SWT.DEFAULT, SWT.DEFAULT, flushCache);
      preferredSize.x += 2 * c.ipadx;
      preferredSize.y += 2 * c.ipady;
      switch (c.fill) {
        case GridBagConstraints.HORIZONTAL :
          preferredSize.x = w;
          break;
        case GridBagConstraints.VERTICAL :
          preferredSize.y = h;
          break;
        case GridBagConstraints.BOTH :
          preferredSize.x = w;
          preferredSize.y = h;
          break;
      }
      if (preferredSize.x < 0)
        preferredSize.x = 0;
      if (preferredSize.y < 0)
        preferredSize.y = 0;

      // compute the location of the component depending on the anchor
      int locX, locY;
      switch (c.anchor) {
        case GridBagConstraints.NORTH :
          locX = x + (w - preferredSize.x) / 2;
          locY = y;
          break;
        case GridBagConstraints.NORTHEAST :
          locX = x + w - preferredSize.x;
          locY = y;
          break;
        case GridBagConstraints.EAST :
          locX = x + w - preferredSize.x;
          locY = y + (h - preferredSize.y) / 2;
          break;
        case GridBagConstraints.SOUTHEAST :
          locX = x + w - preferredSize.x;
          locY = y + h - preferredSize.y;
          break;
        case GridBagConstraints.SOUTH :
          locX = x + (w - preferredSize.x) / 2;
          locY = y + h - preferredSize.y;
          break;
        case GridBagConstraints.SOUTHWEST :
          locX = x;
          locY = y + h - preferredSize.y;
          break;
        case GridBagConstraints.WEST :
          locX = x;
          locY = y + (h - preferredSize.y) / 2;
          break;
        case GridBagConstraints.NORTHWEST :
          locX = x;
          locY = y;
          break;
        default :
          locX = x + (w - preferredSize.x) / 2;
          locY = y + (h - preferredSize.y) / 2;
          break;
      }
      locX += origX;
      locY += origY;

      children[i].setBounds(locX, locY, preferredSize.x, preferredSize.y);
    }

  }

  private GridInfo computeGridInfo(Composite composite) {
    GridInfo newGridInfo = new GridInfo();

    int x = 0;
    int y = 0;
    int maxX = 0;
    int maxY = 0;
    int[] nextFreeIndex = new int[512]; // next free index for each row

    //*** First pass
    //*** find out the size of the grid (nb of rows/column)
    Control[] children = composite.getChildren();
    for (int i = 0; i < children.length; i++) {
      if (!children[i].getVisible())
        continue;
      GridBagConstraints c = getConstraints(children[i]);
      if (c.gridx == GridBagConstraints.RELATIVE)
        c.gridx = x;
      if (c.gridy == GridBagConstraints.RELATIVE)
        c.gridy = y;
      if (c.gridwidth == GridBagConstraints.RELATIVE)
        c.gridwidth = 1;
      if (c.gridheight == GridBagConstraints.RELATIVE)
        c.gridheight = 1;

      if (c.gridwidth == GridBagConstraints.REMAINDER) // if last of the row
        {
        y++;
        x = nextFreeIndex[y];
      } else {
        x = c.gridx + c.gridwidth;
        y = c.gridy;
      }
      if (x > maxX)
        maxX = x;
      if (y > maxY)
        maxY = y;
      // update the last used idx of the rows occupied by this component
      for (int j = c.gridy; j < c.gridy + c.gridheight; j++) {
        if (x > nextFreeIndex[j])
          nextFreeIndex[j] = x;
      }
    }

    //***  the nb of rows is maxY+1, the nb of columns is maxX+1
    newGridInfo.nbRows = maxY + 1;
    newGridInfo.nbCols = maxX + 1;

    if (columnWidths != null) // if column widths were specified...
      {
      if (columnWidths.length > newGridInfo.nbCols)
        newGridInfo.nbCols = columnWidths.length;

      newGridInfo.m_columnWidths = new int[newGridInfo.nbCols];
      System.arraycopy(
        columnWidths,
        0,
        newGridInfo.m_columnWidths,
        0,
        Math.min(columnWidths.length, newGridInfo.m_columnWidths.length));
    } else
      newGridInfo.m_columnWidths = new int[newGridInfo.nbRows];

    if (rowHeights != null) // if row heigths were specified...
      {
      if (rowHeights.length > newGridInfo.nbRows)
        newGridInfo.nbRows = rowHeights.length;

      newGridInfo.m_rowHeights = new int[newGridInfo.nbRows];
      System.arraycopy(
        rowHeights,
        0,
        newGridInfo.m_rowHeights,
        0,
        Math.min(rowHeights.length, newGridInfo.m_rowHeights.length));
    } else
      newGridInfo.m_rowHeights = new int[newGridInfo.nbRows];

    newGridInfo.m_columnWeights = new double[newGridInfo.nbCols];
    newGridInfo.m_rowWeights = new double[newGridInfo.nbRows];

    //*** Second pass
    //*** compute the preferred width/height of each column/row
    for (int i = 0; i < children.length; i++) {
      if (!children[i].getVisible())
        continue;
      GridBagConstraints c = getConstraints(children[i]);
      // if in a remainder cell, calculate the real width/height
      if (c.gridwidth == GridBagConstraints.REMAINDER)
        c.gridwidth = newGridInfo.nbCols - c.gridx;
      if (c.gridheight == GridBagConstraints.REMAINDER)
        c.gridheight = newGridInfo.nbRows - c.gridy;

      // calculate the preferred size of the cell  
      Point compSize =
        getPreferredSize(children[i], SWT.DEFAULT, SWT.DEFAULT, true);
      int cellPreferredWidth = compSize.x / c.gridwidth + 2 * c.ipadx;
      int cellPreferredHeight = compSize.y / c.gridheight + 2 * c.ipady;
      if (c.insets != null) {
        cellPreferredWidth += c.insets.left + c.insets.right;
        cellPreferredHeight += c.insets.left + c.insets.right;
      }

      // if the preferred size is larger as the previously computed size, 
      // update it.
      if (newGridInfo.m_columnWidths[c.gridx] < cellPreferredWidth)
        newGridInfo.m_columnWidths[c.gridx] = cellPreferredWidth;
      if (newGridInfo.m_rowHeights[c.gridy] < cellPreferredHeight)
        newGridInfo.m_rowHeights[c.gridy] = cellPreferredHeight;

      // update the weight of the row/column
      for (x = c.gridx;
        (x < c.gridx + c.gridwidth) && (x < newGridInfo.nbCols);
        x++) {
        if (c.weightx > newGridInfo.m_columnWeights[x])
          newGridInfo.m_columnWeights[x] = c.weightx;
        if (columnWeights != null
          && x < columnWeights.length
          && columnWeights[x] > newGridInfo.m_columnWeights[x])
          newGridInfo.m_columnWeights[x] = columnWeights[x];
      }
      for (y = c.gridy;
        (y < c.gridy + c.gridheight) && (y < newGridInfo.nbRows);
        y++) {
        if (c.weighty > newGridInfo.m_rowWeights[y])
          newGridInfo.m_rowWeights[y] = c.weighty;
        if (rowWeights != null
          && y < rowWeights.length
          && rowWeights[y] > newGridInfo.m_rowWeights[y])
          newGridInfo.m_rowWeights[y] = rowWeights[y];
      }
    }

    // normalize the weights
    double total = 0.0;
    for (int i = 0; i < newGridInfo.nbCols; i++)
      total += newGridInfo.m_columnWeights[i];
    if (total > 0.0) {
      for (int i = 0; i < newGridInfo.nbCols; i++)
        newGridInfo.m_columnWeights[i] /= total;
    }

    total = 0.0;
    for (int i = 0; i < newGridInfo.nbRows; i++)
      total += newGridInfo.m_rowWeights[i];
    if (total > 0.0) {
      for (int i = 0; i < newGridInfo.nbRows; i++)
        newGridInfo.m_rowWeights[i] /= total;
    }

    return newGridInfo;
  }

  private GridBagConstraints getConstraints(Control control) {
    Object data = control.getLayoutData();
    if (data instanceof GridBagConstraints)
      return (GridBagConstraints)data;
    return new GridBagConstraints();
  }

  /**
   * Inner class to hold the information about the computed grid in one object. 
   */
  private class GridInfo {
    int nbRows, nbCols; // nb of rows and columns of the grid
    int[] m_columnWidths; // widths in pixels of each column
    int[] m_rowHeights; // heights in pixels of each row
    double[] m_columnWeights; // the weights of the columns
    double[] m_rowWeights; // the weights of the rows
  }

}
