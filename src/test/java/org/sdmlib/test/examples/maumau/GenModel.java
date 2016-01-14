package org.sdmlib.test.examples.maumau;

import java.util.Enumeration;

import org.junit.Test;
import org.sdmlib.models.classes.ClassModel;

import de.uniks.networkparser.graph.Cardinality;
import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.DataType;

public class GenModel {
	@Test
	public void genModel() {
		ClassModel model = new ClassModel("org.sdmlib.test.examples.maumau.model");
		Clazz mauMauClass = model.createClazz("MauMau");
		Enumeration suitEnum = model.createEnumeration("Suit").withValueNames("Clubs", "Spades", "Hearts", "Diamonds");
		Enumeration valueEnum = model.createEnumeration("Value").withValueNames("7", "8", "9", "10", "Jack", "Queen",
				"King", "Ace");
		Clazz cardClass = model.createClazz("Card").withAttribute("suit", DataType.ref(suitEnum)).withAttribute("value",
				DataType.ref(valueEnum));
		mauMauClass.withBidirectional(cardClass, "cards", Cardinality.MANY, "game", Cardinality.ONE)
				.withAnnotation(new Annotation().withName(ApplicationObject.class.getName()));
		Clazz holderClass = model.createClazz("Holder").withBidirectional(cardClass, "cards", Cardinality.MANY, "holder", Cardinality.ONE);
		mauMauClass.withBidirectional(holderClass, "deck", Cardinality.ONE, "deckOwner", Cardinality.ONE);
		mauMauClass.withBidirectional(holderClass, "stack", Cardinality.ONE, "stackOwner", Cardinality.ONE);
		Clazz playerClass = model.createClazz("Player").withSuperClazz(holderClass).withAttribute("name",
				DataType.STRING);
		mauMauClass.withBidirectional(playerClass, "players", Cardinality.MANY, "game", Cardinality.ONE);
		mauMauClass.withBidirectional(playerClass, "winner", Cardinality.ONE, "wonGame", Cardinality.ONE);
		mauMauClass.withBidirectional(playerClass, "losers", Cardinality.MANY, "lostGame", Cardinality.ONE);
		mauMauClass.with(new Attribute("currentPlayer", DataType.ref(playerClass)));
		mauMauClass.with(new Attribute("currentSuit", DataType.ref(suitEnum)));
		playerClass.withBidirectional(playerClass, "next", Cardinality.ONE, "prev", Cardinality.ONE);
		playerClass.withAttribute("lane", DataType.ref(Lane.class));
		Clazz dutyClass = model.createClazz("Duty");
		Enumeration dutyType = model.createEnumeration("DutyType").withValueNames("PlayCard", "TakeOne", "TakeTwo");
		dutyClass.with(new Attribute("type", DataType.ref(dutyType)), new Attribute("number", DataType.INT));
		playerClass.withBidirectional(dutyClass, "duty", Cardinality.MANY, "player", Cardinality.ONE);
		Clazz openStack = model.createClazz("OpenStack").withSuperClazz(holderClass);
		Clazz drawingStack = model.createClazz("DrawingStack").withSuperClazz(holderClass);
		mauMauClass.withBidirectional(drawingStack, "drawingStack", Cardinality.ONE, "game", Cardinality.ONE);
		mauMauClass.withBidirectional(openStack, "openStack", Cardinality.ONE, "game", Cardinality.ONE);
		model.generate("src/test/java");
	}
}
