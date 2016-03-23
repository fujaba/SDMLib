package org.sdmlib.test.examples.maumau;

import org.junit.Assert;
import org.junit.Test;
import org.sdmlib.test.examples.maumau.model.Card;
import org.sdmlib.test.examples.maumau.model.MauMau;
import org.sdmlib.test.examples.maumau.model.Player;
import org.sdmlib.test.examples.maumau.model.Suit;
import org.sdmlib.test.examples.maumau.model.Value;

import de.uniks.networkparser.interfaces.Condition;

public class TestMauMau {

	@Test
	public void testList() {
		MauMau game = new MauMau();
		Player albert = new Player().withName("Albert");
		Player stefan = new Player().withName("Stefan");
		stefan.withCards(new Card().withSuit(Suit.Hearts).withValue(Value._7), new Card().withSuit(Suit.Spades).withValue(Value._9));
		albert.withCards(new Card().withSuit(Suit.Diamonds).withValue(Value._10));
//		stefan.withCards(new Card().withValue(Value.new Value"Heart 7"), new Card().withValue("Spade 9"));
//		albert.withCards(new Card().withValue("Square 10"));
		game.withPlayers(albert, stefan);
		game.getPlayers().getCards().filter(new Condition<Card>() {
			@Override
			public boolean update(Card value) {
				Assert.assertTrue(value.toString().startsWith("org.sdmlib.test.examples.maumau.model.Card@"));
				return false;
			}
		});
	}
}
