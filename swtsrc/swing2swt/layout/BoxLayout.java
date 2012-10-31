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

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Port of Swing BoxLayout to SWT.
 * @author Yannick Saillet
 */
public class BoxLayout extends AWTLayout {

  public static final int X_AXIS = 0;

  public static final int Y_AXIS = 1;

  private int axis;

  public BoxLayout(int axis) {
    this.axis = axis;
  }

  protected Point computeSize(
    Composite composite,
    int wHint,
    int hHint,
    boolean flushCache) {
    Point size = new Point(0, 0);
    Control[] children = composite.getChildren();

    if (axis == X_AXIS) {
      for (int i = 0; i < children.length; i++) {
        //if (!children[i].getVisible())
        //  continue;
        Point childSize =
          getPreferredSize(children[i], wHint, hHint, flushCache);
        size.x += childSize.x;
        size.y = Math.max(childSize.y, size.y);
      }
    } else {
      for (int i = 0; i < children.length; i++) {
        if (!children[i].getVisible())
          continue;
        Point childSize =
          getPreferredSize(children[i], wHint, hHint, flushCache);
        size.y += childSize.y;
        size.x = Math.max(childSize.x, size.x);
      }
    }
    return size;
  }

  protected void layout(Composite composite, boolean flushCache) {
    Rectangle clientArea = composite.getClientArea();
    Control[] children = composite.getChildren();
    Point[] childrenSize = new Point[children.length];

    if (axis == X_AXIS) {
      int maxHeight = 0;
      for (int i = 0; i < children.length; i++) {
        if (!children[i].getVisible())
          continue;
        childrenSize[i] =
          getPreferredSize(children[i], SWT.DEFAULT, SWT.DEFAULT, flushCache);
        if (childrenSize[i].y > maxHeight)
          maxHeight = childrenSize[i].y;
      }

      int x = clientArea.x;
      int y = clientArea.y + (clientArea.height - maxHeight) / 2;
      for (int i = 0; i < children.length; i++) {
        if (!children[i].getVisible())
          continue;
        children[i].setBounds(x, y, childrenSize[i].x, maxHeight);
        x += childrenSize[i].x;
      }
    } else {
      int maxWidth = 0;
      for (int i = 0; i < children.length; i++) {
        if (!children[i].getVisible())
          continue;
        childrenSize[i] =
          getPreferredSize(children[i], SWT.DEFAULT, SWT.DEFAULT, flushCache);
        if (childrenSize[i].x > maxWidth)
          maxWidth = childrenSize[i].x;
      }
      if (maxWidth > clientArea.width)
        maxWidth = clientArea.width;

      int y = clientArea.y;
      int x = clientArea.x;
      for (int i = 0; i < children.length; i++) {
        if (!children[i].getVisible())
          continue;
        children[i].setBounds(x, y, maxWidth, childrenSize[i].y);
        y += childrenSize[i].y;
      }
    }
  }

}
