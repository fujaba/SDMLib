package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;

public class TestGenEnum {
	@Test
	public void test() {
		ClassModel cm = new ClassModel("de.uniks.se1.ss18.teamb.UoM.model");
		Clazz gameState = cm.createClazz("GameState");
		gameState.enableEnumeration("PENDING", "STARTED", "STARTING_TURN", "PLAYERS_TURN", "ENDING_TURN", "ENDED");
		Clazz game = cm.createClazz("Game");
		game.withUniDirectional(gameState, "gameState", Association.ONE);
		cm.generate("src/test/java");	
		
	}
}
