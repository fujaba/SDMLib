package org.sdmlib.doc.svg;

import java.awt.Rectangle;

import com.mxgraph.canvas.mxGraphics2DCanvas;
import com.mxgraph.shape.mxBasicShape;
import com.mxgraph.view.mxCellState;

public class ObjectShape extends mxBasicShape {

	/**
	 * 
	 */
	public void paintShape(mxGraphics2DCanvas canvas, mxCellState state) {

		Rectangle rect = state.getRectangle();
//		Rectangle label = state.getLabelBounds().getRectangle();
//		rect.width = label.width;
//		rect.height = label.height;

		// Paints the background
		if (configureGraphics(canvas, state, true)) {
			canvas.fillShape(rect, hasShadow(canvas, state));
		}

		// Paints the foreground
		if (configureGraphics(canvas, state, false)) {
			canvas.getGraphics().drawRect(rect.x, rect.y, rect.width,
					rect.height);
		}
	}

}
