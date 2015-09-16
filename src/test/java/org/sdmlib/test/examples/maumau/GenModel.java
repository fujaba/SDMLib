package org.sdmlib.test.examples.maumau;

import org.junit.Test;
import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.Card;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.DataType;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.replication.ApplicationObject;
import org.sdmlib.replication.Lane;

public class GenModel {
	@Test
	public void genModel() {
		ClassModel model = new ClassModel("org.sdmlib.tfd.examples.maumau.shared.model");
		Clazz mauMauClass = model.createClazz("MauMau");
		Enumeration suitEnum = model.createEnumeration("Suit").withValueNames("Clubs", "Spades", "Hearts", "Diamonds");
		Enumeration valueEnum = model.createEnumeration("Value").withValueNames("7", "8", "9", "10", "Jack", "Queen",
				"King", "Ace");
		Clazz cardClass = model.createClazz("Card").withAttribute("suit", DataType.ref(suitEnum)).withAttribute("value",
				DataType.ref(valueEnum));
		mauMauClass.withAssoc(cardClass, "cards", Card.MANY, "game", Card.ONE)
				.withAnnotation(new Annotation().withName(ApplicationObject.class.getName()));
		Clazz holderClass = model.createClazz("Holder").withAssoc(cardClass, "cards", Card.MANY, "holder", Card.ONE);
		mauMauClass.withAssoc(holderClass, "deck", Card.ONE, "deckOwner", Card.ONE);
		mauMauClass.withAssoc(holderClass, "stack", Card.ONE, "stackOwner", Card.ONE);
		Clazz playerClass = model.createClazz("Player").withSuperClazz(holderClass).withAttribute("name",
				DataType.STRING);
		mauMauClass.withAssoc(playerClass, "players", Card.MANY, "game", Card.ONE);
		mauMauClass.withAssoc(playerClass, "winner", Card.ONE, "wonGame", Card.ONE);
		mauMauClass.withAssoc(playerClass, "losers", Card.MANY, "lostGame", Card.ONE);
		mauMauClass.with(new Attribute("currentPlayer", DataType.ref(playerClass)));
		mauMauClass.with(new Attribute("currentSuit", DataType.ref(suitEnum)));
		playerClass.withAssoc(playerClass, "next", Card.ONE, "prev", Card.ONE);
		playerClass.withAttribute("lane", DataType.ref(Lane.class));
		Clazz dutyClass = model.createClazz("Duty");
		Enumeration dutyType = model.createEnumeration("DutyType").withValueNames("PlayCard", "TakeOne", "TakeTwo");
		dutyClass.with(new Attribute("type", DataType.ref(dutyType)), new Attribute("number", DataType.INT));
		playerClass.withAssoc(dutyClass, "duty", Card.MANY, "player", Card.ONE);
		Clazz openStack = model.createClazz("OpenStack").withSuperClazz(holderClass);
		Clazz drawingStack = model.createClazz("DrawingStack").withSuperClazz(holderClass);
		mauMauClass.withAssoc(drawingStack, "drawingStack", Card.ONE, "game", Card.ONE);
		mauMauClass.withAssoc(openStack, "openStack", Card.ONE, "game", Card.ONE);
		model.generate("src/test/java");
	}
}
