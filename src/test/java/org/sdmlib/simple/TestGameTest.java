package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;

public class TestGameTest {
	@Test
	public void testManyToMany() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.game");
		Clazz game = model.createClazz("Game");
		Clazz player = model.createClazz("Player");
		game.withBidirectional(player, "won", Cardinality.ONE, "winGame", Cardinality.ONE);
		player.withBidirectional(game, "currentGame", Cardinality.ONE, "currentPlayer", Cardinality.ONE);

		model.dumpHTML("game");
	}
}
