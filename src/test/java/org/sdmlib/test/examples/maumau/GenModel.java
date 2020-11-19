package org.sdmlib.test.examples.maumau;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.replication.ApplicationObject;
import org.sdmlib.replication.Lane;

import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Association;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class GenModel {
	@Test
	public void genModel() {
		ClassModel model = new ClassModel("org.sdmlib.test.examples.maumau.model");
		Clazz mauMauClass = model.createClazz("MauMau");
		Clazz suitEnum = model.createClazz("Suit").enableEnumeration("Clubs", "Spades", "Hearts", "Diamonds");
		Clazz valueEnum = model.createClazz("Value").enableEnumeration("7", "8", "9", "10", "Jack", "Queen", "King", "Ace");
		Clazz cardClass = model.createClazz("Card").withAttribute("suit", DataType.create(suitEnum)).withAttribute("value",
				DataType.create(valueEnum));
		mauMauClass.withBidirectional(cardClass, "cards", Association.MANY, "game", Association.ONE).with(new Annotation(ApplicationObject.class.getName()));
		Clazz holderClass = model.createClazz("Holder").withBidirectional(cardClass, "cards", Association.MANY, "holder", Association.ONE);
		mauMauClass.withBidirectional(holderClass, "deck", Association.ONE, "deckOwner", Association.ONE);
		mauMauClass.withBidirectional(holderClass, "stack", Association.ONE, "stackOwner", Association.ONE);
		Clazz playerClass = model.createClazz("Player").withSuperClazz(holderClass).withAttribute("name",
				DataType.STRING);
		mauMauClass.withBidirectional(playerClass, "players", Association.MANY, "game", Association.ONE);
		mauMauClass.withBidirectional(playerClass, "winner", Association.ONE, "wonGame", Association.ONE);
		mauMauClass.withBidirectional(playerClass, "losers", Association.MANY, "lostGame", Association.ONE);
		mauMauClass.withAttribute("currentPlayer", DataType.create(playerClass));
		mauMauClass.withAttribute("currentSuit", DataType.create(suitEnum));
		playerClass.withBidirectional(playerClass, "next", Association.ONE, "prev", Association.ONE);
		playerClass.withAttribute("lane", DataType.create(Lane.class));
		Clazz dutyClass = model.createClazz("Duty");
		Clazz dutyType = model.createClazz("DutyType").enableEnumeration("PlayCard", "TakeOne", "TakeTwo");
		dutyClass.withAttribute("type", DataType.create(dutyType));
		dutyClass.withAttribute("number", DataType.INT);
		playerClass.withBidirectional(dutyClass, "duty", Association.MANY, "player", Association.ONE);
		Clazz openStack = model.createClazz("OpenStack").withSuperClazz(holderClass);
		Clazz drawingStack = model.createClazz("DrawingStack").withSuperClazz(holderClass);
		mauMauClass.withBidirectional(drawingStack, "drawingStack", Association.ONE, "game", Association.ONE);
		mauMauClass.withBidirectional(openStack, "openStack", Association.ONE, "game", Association.ONE);
		model.generate("src/test/java");
	}
}
