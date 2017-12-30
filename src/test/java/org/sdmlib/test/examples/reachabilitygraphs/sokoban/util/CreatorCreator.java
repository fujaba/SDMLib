package org.sdmlib.test.examples.reachabilitygraphs.sokoban.util;

import de.uniks.networkparser.IdMap;

class CreatorCreator{

   public static IdMap createIdMap(String session)
   {
      IdMap jsonIdMap = new IdMap().withSession(session);
      jsonIdMap.with(new AKarliCreator());
      jsonIdMap.with(new AKarliPOCreator());
      jsonIdMap.with(new BoxCreator());
      jsonIdMap.with(new BoxPOCreator());
      jsonIdMap.with(new KarliCreator());
      jsonIdMap.with(new KarliPOCreator());
      jsonIdMap.with(new MazeCreator());
      jsonIdMap.with(new MazePOCreator());
      jsonIdMap.with(new SokobanCreator());
      jsonIdMap.with(new SokobanPOCreator());
      jsonIdMap.with(new TileCreator());
      jsonIdMap.with(new TilePOCreator());
      return jsonIdMap;
   }
}
