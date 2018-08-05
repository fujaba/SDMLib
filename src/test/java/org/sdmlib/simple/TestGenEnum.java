package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;

public class TestGenEnum {
	@Test
	public void test() {
		ClassModel cm = new ClassModel("de.uniks.se1.ss18.teamb.UoM.model");
		Clazz gameState = cm.createClazz("GameState");
		gameState.enableEnumeration("PENDING", "STARTED", "STARTING_TURN", "PLAYERS_TURN", "ENDING_TURN", "ENDED");
		Clazz game = cm.createClazz("Game");
		game.withUniDirectional(gameState, "gameState", Cardinality.ONE);
		cm.generate("src/test/java");	
		
	}
}
