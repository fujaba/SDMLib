package org.sdmlib.storyboard;

import java.util.ArrayList;

public class StoryBoard {
	private ArrayList<Dumpling> children=new ArrayList<Dumpling>();
	
	public boolean add(Dumpling child){
		if(child instanceof StartSituation){
			for(int i=0;i<children.size();i++){
				if(children.get(i) instanceof StartSituation){
					children.set(i, child);
					return true;
				}
			}
		}
		if(child instanceof EndSituation){
			for(int i=0;i<children.size();i++){
				if(children.get(i) instanceof EndSituation){
					children.set(i, child);
					return true;
				}
			}
		}
		return children.add(child);
	}
	
	public void generate(String root){
		
	}
	
	public StoryBoard with(Dumpling child){
		add(child);
		return this;
	}
}
