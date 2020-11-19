package org.sdmlib;

import de.uniks.networkparser.graph.Feature;

public class SDMFeature extends Feature {
	public SDMFeature(Feature ref) {
		super(ref);
	}
	
	SDMFeature(String value) {
		super(value);
	}

	@Override
	protected SDMFeature newInstance(Feature ref) {
		return new SDMFeature(ref);
	}
	
	public static final SDMFeature EMFSTYLE = new SDMFeature("EMFSTYLE");
	public static final SDMFeature PATTERNOBJECT = new SDMFeature("PATTERNOBJECT");
	public static final SDMFeature STANDALONE = new SDMFeature("STANDALONE");
	public static final SDMFeature REMOVEYOUMETHOD = new SDMFeature("REMOVEYOUMETHOD");
}
