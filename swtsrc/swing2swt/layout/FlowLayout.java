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

import java.awt.ComponentOrientation;
import java.util.Locale;
import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
 * Port of AWT FlowLayout to SWT.
 * @author Yannick Saillet
 */
public class FlowLayout extends AWTLayout {
  public final static int CENTER = 1;
  public final static int LEADING = 2;
  public final static int LEFT = 3;
  public final static int RIGHT = 4;
  public final static int TRAILING = 5;

  //---------

  private int hgap, vgap;
  private int align;
  private Vector<Control> currentLine = new Vector<Control>();

  //---------

  public FlowLayout() {
    this(CENTER);
  }

  public FlowLayout(int align) {
    this(align, 5, 5);
  }

  public FlowLayout(int align, int hgap, int vgap) {
  	setAlignment(align);
    this.hgap = hgap;
    this.vgap = vgap;
  }

  protected Point computeSize(
    Composite composite,
    int wHint,
    int hHint,
    boolean flushCache) {
    Point size = new Point(0, 0);
    Control[] children = composite.getChildren();
    int nb = 0;
    for (int i = 0; i < children.length; i++) {
      //if (!children[i].getVisible())
      //  continue;
      Point childSize = getPreferredSize(children[i], wHint, hHint, flushCache);
      size.x += childSize.x;
      size.y = Math.max(childSize.y, size.y);
      nb++;
    }
    if (nb > 0) {
      size.x += (nb + 1) * hgap;
      size.y += vgap;
    }

    return size;
  }

  protected void layout(Composite composite, boolean flushCache) {
    Rectangle clientArea = composite.getClientArea();
    Control[] children = composite.getChildren();
    int x = clientArea.x + hgap;
    int y = clientArea.y + vgap;
    int lineWidth = hgap;
    int lineHeight = 0;
    for (int i = 0; i < children.length; i++) {
      Control child = children[i];
      if (!children[i].getVisible())
        continue;
      Point childSize =
        getPreferredSize(child, SWT.DEFAULT, SWT.DEFAULT, flushCache);

      // if a new line is necessary
      if ((lineWidth + childSize.x) > (clientArea.width)
        && currentLine.size() > 0) {
        // correct the alignment of the components of the current line
        alignCurrentLine(clientArea, lineWidth, lineHeight);

        // start a new line
        currentLine.clear();
        x = clientArea.x + hgap;
        y += lineHeight + vgap;
        lineHeight = hgap;
        lineWidth = 0;
      }
      child.setBounds(x, y, childSize.x, childSize.y);
      currentLine.add(child);
      lineWidth += childSize.x + hgap;
      lineHeight = Math.max(lineHeight, childSize.y);
      x += childSize.x + hgap;
    }
    alignCurrentLine(clientArea, lineWidth, lineHeight);
    currentLine.clear();
  }

  private void alignCurrentLine(
    Rectangle clientArea,
    int lineWidth,
    int lineHeight) {
    for (int j = 0; j < currentLine.size(); j++) {
      Control c = (Control)currentLine.get(j);
      Rectangle bounds = c.getBounds();

      // horizontal alignment
      int x = bounds.x;
      if (align == CENTER)
        x += (clientArea.width - lineWidth) / 2;
      else if (align == RIGHT)
        x += clientArea.width - lineWidth;

      // vertical alignment: all the component are aligned on their center
      int y = bounds.y + (lineHeight - bounds.height) / 2;

      c.setLocation(x, y);
    }
  }

/**
 * @return Returns the hgap.
 */
public int getHgap() {
	return hgap;
}
/**
 * @param hgap The hgap to set.
 */
public void setHgap(int hgap) {
	this.hgap = hgap;
}
/**
 * @return Returns the vgap.
 */
public int getVgap() {
	return vgap;
}
/**
 * @param vgap The vgap to set.
 */
public void setVgap(int vgap) {
	this.vgap = vgap;
}
/**
 * @return Returns the align.
 */
public int getAlignment() {
	return align;
}
/**
 * @param align The align to set.
 */
public void setAlignment(int align) {
	if (align == LEADING || align == TRAILING) {
		ComponentOrientation orientation =
			ComponentOrientation.getOrientation(Locale.getDefault());
		if (align == LEADING)
			align = orientation.isLeftToRight() ? LEFT : RIGHT;
			else
				align = orientation.isLeftToRight() ? RIGHT : LEFT;
	}

	this.align = align;
}
}
