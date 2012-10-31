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

import java.awt.Insets;

/**
 * Port of AWT GridBagConstraints to SWT.
 * @author Yannick Saillet
 */
public class GridBagConstraints extends java.awt.GridBagConstraints {

	private static final long serialVersionUID = 1L;
public GridBagConstraints() {
    super();
  }

  public GridBagConstraints(
    int gridx,
    int gridy,
    int gridwidth,
    int gridheight,
    double weightx,
    double weighty,
    int anchor,
    int fill,
    Insets insets,
    int ipadx,
    int ipady) {
    super(
      gridx,
      gridy,
      gridwidth,
      gridheight,
      weightx,
      weighty,
      anchor,
      fill,
      insets,
      ipadx,
      ipady);
  }
}
