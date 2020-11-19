package org.sdmlib.simple;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;

public class TestGameTest {
	@Test
	public void testManyToMany() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.game");
		Clazz game = model.createClazz("Game");
		Clazz player = model.createClazz("Player");
		game.withBidirectional(player, "won", Association.ONE, "winGame", Association.ONE);
		player.withBidirectional(game, "currentGame", Association.ONE, "currentPlayer", Association.ONE);

		model.dumpHTML("game");
	}
	@Test
	public void testSuperClazz() {
		ClassModel model = new ClassModel("org.sdmlib.simple.model.game");
		Clazz game = model.createClazz("Game");
		Clazz entity = model.createClazz("GameEntity");
		Clazz player = model.createClazz("Player");
		
		game.withSuperClazz(entity);
		player.withSuperClazz(entity);
		//		game.withBidirectional(player, "won", Association.ONE, "winGame", Association.ONE);
//		player.withBidirectional(game, "currentGame", Association.ONE, "currentPlayer", Association.ONE);

		model.dumpHTML("game");
	}
}
