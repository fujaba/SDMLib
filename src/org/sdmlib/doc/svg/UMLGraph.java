package org.sdmlib.doc.svg;

import com.mxgraph.model.mxCell;
import com.mxgraph.model.mxGeometry;
import com.mxgraph.util.mxRectangle;
import com.mxgraph.view.mxCellState;
import com.mxgraph.view.mxGraph;

public class UMLGraph extends mxGraph {

	@Override
	public String convertValueToString(Object cell) {
		return super.convertValueToString(cell);
	}

	public void updateVertexBounds(Object[] cells) {
		if (cells != null) {
			model.beginUpdate();
			try {
				for (Object parent : cells) {
					mxRectangle cellBounds = getCellBounds(parent);
					mxCellState state = getView().getState(parent);
					mxRectangle labelBounds = state.getLabelBounds();
					if(labelBounds.getWidth() > cellBounds.getWidth()) {
						cellBounds.setWidth(labelBounds.getWidth());
						mxCell cell = (mxCell) parent;
						cell.setGeometry(new mxGeometry(0, 0, cellBounds.getWidth(), cellBounds.getHeight()));
					}
				}
			} finally {
				model.endUpdate();
			}
		}
	}

}
