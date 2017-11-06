package org.sdmlib.test.examples.maumau;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.replication.ApplicationObject;
import org.sdmlib.replication.Lane;

import de.uniks.networkparser.graph.Annotation;
import de.uniks.networkparser.graph.Cardinality;
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
		mauMauClass.withBidirectional(cardClass, "cards", Cardinality.MANY, "game", Cardinality.ONE).with(new Annotation(ApplicationObject.class.getName()));
		Clazz holderClass = model.createClazz("Holder").withBidirectional(cardClass, "cards", Cardinality.MANY, "holder", Cardinality.ONE);
		mauMauClass.withBidirectional(holderClass, "deck", Cardinality.ONE, "deckOwner", Cardinality.ONE);
		mauMauClass.withBidirectional(holderClass, "stack", Cardinality.ONE, "stackOwner", Cardinality.ONE);
		Clazz playerClass = model.createClazz("Player").withSuperClazz(holderClass).withAttribute("name",
				DataType.STRING);
		mauMauClass.withBidirectional(playerClass, "players", Cardinality.MANY, "game", Cardinality.ONE);
		mauMauClass.withBidirectional(playerClass, "winner", Cardinality.ONE, "wonGame", Cardinality.ONE);
		mauMauClass.withBidirectional(playerClass, "losers", Cardinality.MANY, "lostGame", Cardinality.ONE);
		mauMauClass.withAttribute("currentPlayer", DataType.create(playerClass));
		mauMauClass.withAttribute("currentSuit", DataType.create(suitEnum));
		playerClass.withBidirectional(playerClass, "next", Cardinality.ONE, "prev", Cardinality.ONE);
		playerClass.withAttribute("lane", DataType.create(Lane.class));
		Clazz dutyClass = model.createClazz("Duty");
		Clazz dutyType = model.createClazz("DutyType").enableEnumeration("PlayCard", "TakeOne", "TakeTwo");
		dutyClass.withAttribute("type", DataType.create(dutyType));
		dutyClass.withAttribute("number", DataType.INT);
		playerClass.withBidirectional(dutyClass, "duty", Cardinality.MANY, "player", Cardinality.ONE);
		Clazz openStack = model.createClazz("OpenStack").withSuperClazz(holderClass);
		Clazz drawingStack = model.createClazz("DrawingStack").withSuperClazz(holderClass);
		mauMauClass.withBidirectional(drawingStack, "drawingStack", Cardinality.ONE, "game", Cardinality.ONE);
		mauMauClass.withBidirectional(openStack, "openStack", Cardinality.ONE, "game", Cardinality.ONE);
		model.generate("src/test/java");
	}
}
