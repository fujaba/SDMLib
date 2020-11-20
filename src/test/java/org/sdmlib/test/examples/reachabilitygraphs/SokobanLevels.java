package org.sdmlib.test.examples.reachabilitygraphs;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.Date;
import java.util.logging.Logger;
import org.junit.Test;
import org.sdmlib.models.pattern.Pattern;
import org.sdmlib.models.pattern.ReachabilityGraph;
import org.sdmlib.models.pattern.ReachabilityGraph.Searchmode;
import org.sdmlib.models.pattern.ReachableState;
import org.sdmlib.models.pattern.RuleApplication;
import org.sdmlib.models.tables.Table;
import org.sdmlib.storyboards.Storyboard;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.AKarli;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Box;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Maze;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Sokoban;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.Tile;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.AKarliPO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.BoxPO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.KarliPO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.SokobanCreator;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.SokobanPO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TilePO;
import org.sdmlib.test.examples.reachabilitygraphs.sokoban.util.TileSet;
import de.uniks.networkparser.list.ObjectSet;
import de.uniks.networkparser.list.SimpleList;

public class SokobanLevels {
  private String level;
  private ReachabilityGraph reachabilityGraph;
  private Storyboard story;

  // @Test
  public void SokobanTrafos() throws Exception {
    level = ""
        + "  wwww\n"
        + "  w..w\n"
        + "wwwb.w\n"
        + "woobkw\n"
        + "wwwwww\n";

    int size = sokobanTrafoLevel();

    printStates();

    story.dumpHTML();

  }


  /**
   * 
   * <p>
   * Storyboard
   * <a href='.././src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanLevels.java' type=
   * 'text/x-java'>sokobanTrafoLevel</a>
   * </p>
   * <p>
   * Start: First Level
   * </p>
   * <p>
   * Check: akarli reaches tiles: 4 actual 4
   * </p>
   * <p>
   * <a name = 'step_1'>Step 1: Statistics:</a>
   * </p>
   * <table style="width: auto;" class="table table-bordered table-condensed">
   * <thead>
   * <tr>
   * <th class="text-center">Descr</th>
   * <th class="text-center">Data</th>
   * <th class="text-center"></th>
   * </tr>
   * </thead>
   * 
   * <tbody>
   * <tr>
   * <td class="text-right col-md-1">nodes per state:</td>
   * <td class="text-right col-md-1">0</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">number of states:</td>
   * <td class="text-right col-md-1">8</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">product:</td>
   * <td class="text-right col-md-1">0</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">actual object number:</td>
   * <td class="text-right col-md-1">23</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">collectable object number:</td>
   * <td class="text-right col-md-1">31</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">garbage object number:</td>
   * <td class="text-right col-md-1">8</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">ratio:</td>
   * <td class="text-right col-md-1">Infinity%</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * </tbody>
   * 
   * </table>
   * 
   * <pre>
   * 7 -move left-&gt;
   *  6
   *   wwww
   *   waaw
   * wwwaaw
   * wBBaaw
   * wwwwww
   * 
   * 
   * </pre>
   * 
   * <pre>
   * 1 0.0
   *  6
   *   wwww
   *   waaw
   * wwwbaw
   * woobaw
   * wwwwww
   * 
   * 
   * 1 -move left-&gt;
   * 2 0.0
   *  6
   *   wwww
   *   waaw
   * wwwbaw
   * woBaaw
   * wwwwww
   * 
   * 
   * 2 -move up-&gt;
   * 3 0.0
   *  6
   *   wwww
   *   wbaw
   * wwwaaw
   * woBaaw
   * wwwwww
   * 
   * 
   * 2 -move down-&gt;
   * 4 0.0
   *  6
   *   wwww
   *   waaw
   * wwwaaw
   * woBbaw
   * wwwwww
   * 
   * 
   * 2 -move left-&gt;
   * 5 0.0
   *  6
   *   wwww
   *   waaw
   * wwwbaw
   * wBAaaw
   * wwwwww
   * 
   * 
   * 3 -move left-&gt;
   * 5 -move up-&gt;
   * 6 0.0
   *  6
   *   wwww
   *   wbaw
   * wwwaaw
   * wBAaaw
   * wwwwww
   * 
   * 
   * 5 -move down-&gt;
   * 7 0.0
   *  6
   *   wwww
   *   waaw
   * wwwaaw
   * wBobaw
   * wwwwww
   * 
   * 
   * 7 -move left-&gt;
   * 8 0.0
   *  6
   *   wwww
   *   waaw
   * wwwaaw
   * wBBaaw
   * wwwwww
   * 
   * 
   * </pre>
   */
  private int sokobanTrafoLevel() {
    long noOfStates = 30000;
    story = new Storyboard().withDocDirName("doc/internal");

    story.addStep("First Level");

    Sokoban soko = createLevel(level);

    Tile oldKarliTile = soko.getKarli().getTile();
    soko.getKarli().removeYou();
    soko.setKarli(null);

    AKarli akarli = soko.createAkarli().withTiles(oldKarliTile);

    expandAKarliSpace(soko, akarli);

    story.assertEquals("akarli reaches tiles:", 4, akarli.getTiles().size());

    reachabilityGraph = new ReachabilityGraph()
        .withLazyBackup()
        .withMasterMap(SokobanCreator.createIdMap("s"));

    reachabilityGraph.withTrafo("move up", g -> getBoxes(g), (g, box) -> moveBox("up", g, box));
    reachabilityGraph.withTrafo("move down", g -> getBoxes(g), (g, box) -> moveBox("down", g, box));
    reachabilityGraph.withTrafo("move left", g -> getBoxes(g), (g, box) -> moveBox("left", g, box));
    reachabilityGraph.withTrafo("move right", g -> getBoxes(g), (g, box) -> moveBox("right", g, box));

    ObjectSet statics = new ObjectSet();
    statics.with(soko.getMaze()).withList(soko.getMaze().getTiles());
    reachabilityGraph.setStaticNodes(statics);

    ReachableState startState = new ReachableState().withGraphRoot(soko);
    reachabilityGraph.withStart(startState);

    ObjectSet startElems = new ObjectSet();
    SimpleList<Object> dynEdges = new SimpleList<Object>();
    // reachabilityGraph.getLazyCloneOp().aggregate(startElems, dynEdges,
    // reachabilityGraph.getStaticNodes(), soko);

    // =============================================================
    long startTime = System.currentTimeMillis();
    reachabilityGraph.explore(noOfStates, Searchmode.DEFAULT);
    long endTime = System.currentTimeMillis();
    long usedMillis1 = endTime - startTime;
    // =============================================================

    Table stats = new Table();
    stats.createColumns().withName("Descr").withTdCssClass("text-right col-md-1");
    stats.createColumns().withName("Data").withTdCssClass("text-right col-md-1");
    stats.createColumns().withName(" ").withTdCssClass("text-right col-md-6");

    int startElemsSize = startElems.size();
    stats.createRows("nodes per state:", startElemsSize, " ");

    noOfStates = reachabilityGraph.getStates().size();
    stats.createRows("number of states:", noOfStates, " ");

    long expectedObjectNumber = startElemsSize * noOfStates;
    stats.createRows("product:", expectedObjectNumber, " ");

    startElems.clear();
    dynEdges.clear();

    for (ReachableState state : reachabilityGraph.getStates()) {
      reachabilityGraph.getLazyCloneOp().aggregate(startElems, dynEdges, reachabilityGraph.getStaticNodes(), state.getGraphRoot());
    }

    int actualObjectNumber = startElems.size();
    stats.createRows("actual object number:", actualObjectNumber, " ");

    startElems.clear();
    reachabilityGraph.getLazyCloneOp().collectComponent(startElems, soko);
    int collectedComponentSize = startElems.size();

    stats.createRows("collectable object number:", collectedComponentSize, " ");
    int garbageSize = collectedComponentSize - actualObjectNumber;
    stats.createRows("garbage object number:", garbageSize, " ");

    double ratio = 1.0 * actualObjectNumber / expectedObjectNumber * 100;
    String txt = String.format("%.2f", ratio);
    stats.createRows("ratio:", txt + "%", " ");

    story.addStep("Statistics:");
    story.addTable(stats);

    // story.assertEquals("Garbage size", 0, garbageSize);
    // printStates(story, reachabilityGraph);

    Runtime runtime = Runtime.getRuntime();
    // Run the garbage collector
    runtime.gc();
    // Calculate the used memory
    long emptyMemory = runtime.totalMemory() - runtime.freeMemory();

    try {
      Date now = Date.from(Instant.now());

      String msg = String.format("%s memory lazy %s uselong %s certificates %,d bytes %,d millis \n",
          now.toString(),
          false ? "clean" : "keep ",
          false ? "true " : "false",
          emptyMemory, usedMillis1);
      System.out.println(msg);
      Files.write(Paths.get("Sokoban.log"), msg.getBytes(), StandardOpenOption.APPEND);
      story.add(msg);
    } catch (IOException e) {
      // exception handling left as an exercise for the reader
    }

    for (ReachableState r : reachabilityGraph.getStates()) {
      Sokoban finalSoko = (Sokoban) r.getGraphRoot();

      int size = finalSoko.getBoxes().getTile().createGoalCondition(true).size();

      if (size == finalSoko.getBoxes().size()) {
        // all boxes on goal fields

        StringBuilder buf = new StringBuilder();

        for (RuleApplication ra : r.getResultOf()) {
          ReachableState src = ra.getSrc();

          buf.append(src.getNumber()).append(" -").append(ra.getDescription()).append("->\n");
        }

        String stateDescription = finalSoko.toString();
        buf.append(stateDescription).append("\n\n");
        System.out.println(buf.toString());

        story.addPreformatted(buf.toString());

        break;
      }
    }

    long usedMillis = usedMillis1;

    int size = reachabilityGraph.getStates().size();

    return size;
  }



  private ObjectSet getBoxes(Object g) {
    ObjectSet handles = new ObjectSet();

    Sokoban soko = (Sokoban) g;

    handles.addAll(soko.getBoxes());

    return handles;
  }


  private void moveBox(String direction, Object root, Object b) {
    Sokoban soko = (Sokoban) root;

    Box box = (Box) b;

    Tile boxTile = box.getTile();

    int deltaX = 0;
    int deltaY = 0;

    if ("up".equals(direction)) {
      deltaY = -1;
    } else if ("down".equals(direction)) {
      deltaY = +1;
    } else if ("left".equals(direction)) {
      deltaX = -1;
    } else if ("right".equals(direction)) {
      deltaX = 1;
    }


    Tile tgtTile = boxTile.getNeighbors().createXCondition(boxTile.getX() + deltaX).createYCondition(boxTile.getY() + deltaY).first();
    Tile aKarliTile = boxTile.getNeighbors().createXCondition(boxTile.getX() - deltaX).createYCondition(boxTile.getY() - deltaY).first();

    AKarli aKarli = soko.getAkarli();

    if (aKarliTile == null || !aKarli.getTiles().contains(aKarliTile)) {
      return;
    }

    if (tgtTile == null || tgtTile.isWall() || soko.getBoxes().getTile().contains(tgtTile)) {
      return;
    }

    // OK, do it
    box.setTile(tgtTile);

    aKarli.withoutTiles(aKarli.getTiles().toArray(new Tile[aKarli.getTiles().size()]));

    aKarli.withTiles(boxTile);

    expandAKarliSpace(soko, aKarli);

    return;
  }


  @Test
  public void SokobanAbstraction() throws Exception {
    level = ""
        + "  wwww\n"
        + "  w..w\n"
        + "wwwb.w\n"
        + "woobkw\n"
        + "wwwwww\n";

    int size = sokobanAbstractLevel();

    printStates();

    story.dumpHTML();

    assertEquals("wrong number of levels", 8, size);

    level = ""
        + "     wwwww\n"
        + "    ww.o.w\n"
        + "   ww.bo.w\n"
        + "  ww.b...w\n"
        + " ww.bk.www\n"
        + " w.b..ww  \n"
        + " woo.ww   \n"
        + " w...ww   \n"
        + " wwwwww   \n";

    size = sokobanAbstractLevel();

    story.dumpHTML();
  }


  private void printStates() {
    StringBuilder buf = new StringBuilder();

    for (ReachableState s : reachabilityGraph.getStates()) {
      for (RuleApplication r : s.getResultOf()) {
        ReachableState src = r.getSrc();

        buf.append(src.getNumber()).append(" -").append(r.getDescription()).append("->\n");
      }

      String stateDescription = s.toString();
      buf.append(stateDescription).append("\n\n");
    }

    System.out.println(buf.toString());

    story.addPreformatted(buf.toString());
  }


  /**
   * 
   * <p>
   * Storyboard
   * <a href='.././src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanLevels.java' type=
   * 'text/x-java'>sokobanAbstractLevel</a>
   * </p>
   * <p>
   * Start: First Level
   * </p>
   * <p>
   * <a name = 'step_1'>Step 1: Statistics:</a>
   * </p>
   * <table style="width: auto;" class="table table-bordered table-condensed">
   * <thead>
   * <tr>
   * <th class="text-center">Descr</th>
   * <th class="text-center">Data</th>
   * <th class="text-center"></th>
   * </tr>
   * </thead>
   * 
   * <tbody>
   * <tr>
   * <td class="text-right col-md-1">nodes per state:</td>
   * <td class="text-right col-md-1">0</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">number of states:</td>
   * <td class="text-right col-md-1">17614</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">product:</td>
   * <td class="text-right col-md-1">0</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">actual object number:</td>
   * <td class="text-right col-md-1">35258</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">collectable object number:</td>
   * <td class="text-right col-md-1">67</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">garbage object number:</td>
   * <td class="text-right col-md-1">-35191</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">ratio:</td>
   * <td class="text-right col-md-1">Infinity%</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * </tbody>
   * 
   * </table>
   * 
   * <pre>
   * 15942 -push box-&gt;
   * 15952 -push box-&gt;
   *  10
   *      wwwww
   *     wwaBaw
   *    wwaaBaw
   *   wwaaaaaw
   *  wwaaaawww
   *  waaaaww  
   *  wBBaww   
   *  waaaww   
   *  wwwwww
   * 
   * 
   * </pre>
   */
  private int sokobanAbstractLevel() {
    long noOfStates = 30000;
    story = new Storyboard().withDocDirName("doc/internal");

    story.addStep("First Level");

    Sokoban soko = createLevel(level);

    Tile oldKarliTile = soko.getKarli().getTile();
    soko.getKarli().removeYou();
    soko.setKarli(null);

    AKarli akarli = soko.createAkarli();

    akarli.withTiles(oldKarliTile);

    SokobanPO sokobanPO = new SokobanPO(soko);

    doAbstraction(sokobanPO);

    // story.assertEquals("akarli reaches tiles:", 4, akarli.getTiles().size());

    // story.addObjectDiagram(soko);

    SokobanPO pushBoxSokoPO = createAbstractPushBoxRule();

    reachabilityGraph = new ReachabilityGraph()
        .withMasterMap(SokobanCreator.createIdMap("s"))
        .withRules("push box", pushBoxSokoPO);

    ObjectSet statics = new ObjectSet();
    statics.with(soko.getMaze()).withList(soko.getMaze().getTiles());
    reachabilityGraph.setStaticNodes(statics);

    ReachableState startState = new ReachableState().withGraphRoot(soko);
    reachabilityGraph.withStart(startState);

    ObjectSet startElems = new ObjectSet();
    SimpleList<Object> dynEdges = new SimpleList<Object>();
    // reachabilityGraph.getLazyCloneOp().aggregate(startElems, dynEdges,
    // reachabilityGraph.getStaticNodes(), soko);

    // =============================================================
    long startTime = System.currentTimeMillis();
    reachabilityGraph.explore(noOfStates, Searchmode.DEFAULT);
    long endTime = System.currentTimeMillis();
    long usedMillis1 = endTime - startTime;
    // =============================================================

    Table stats = new Table();
    stats.createColumns().withName("Descr").withTdCssClass("text-right col-md-1");
    stats.createColumns().withName("Data").withTdCssClass("text-right col-md-1");
    stats.createColumns().withName(" ").withTdCssClass("text-right col-md-6");

    int startElemsSize = startElems.size();
    stats.createRows("nodes per state:", startElemsSize, " ");

    noOfStates = reachabilityGraph.getStates().size();
    stats.createRows("number of states:", noOfStates, " ");

    long expectedObjectNumber = startElemsSize * noOfStates;
    stats.createRows("product:", expectedObjectNumber, " ");

    startElems.clear();
    dynEdges.clear();

    for (ReachableState state : reachabilityGraph.getStates()) {
      reachabilityGraph.getLazyCloneOp().aggregate(startElems, dynEdges, reachabilityGraph.getStaticNodes(), state.getGraphRoot());
    }

    int actualObjectNumber = startElems.size();
    stats.createRows("actual object number:", actualObjectNumber, " ");

    startElems.clear();
    reachabilityGraph.getLazyCloneOp().collectComponent(startElems, soko);
    int collectedComponentSize = startElems.size();

    stats.createRows("collectable object number:", collectedComponentSize, " ");
    int garbageSize = collectedComponentSize - actualObjectNumber;
    stats.createRows("garbage object number:", garbageSize, " ");

    double ratio = 1.0 * actualObjectNumber / expectedObjectNumber * 100;
    String txt = String.format("%.2f", ratio);
    stats.createRows("ratio:", txt + "%", " ");

    story.addStep("Statistics:");
    story.addTable(stats);

    // story.assertEquals("Garbage size", 0, garbageSize);
    // printStates(story, reachabilityGraph);

    Runtime runtime = Runtime.getRuntime();
    // Run the garbage collector
    runtime.gc();
    // Calculate the used memory
    long emptyMemory = runtime.totalMemory() - runtime.freeMemory();

    try {
      Date now = Date.from(Instant.now());

      String msg = String.format("%s memory lazy %s uselong %s certificates %,d bytes %,d millis \n",
          now.toString(),
          false ? "clean" : "keep ",
          false ? "true " : "false",
          emptyMemory, usedMillis1);
      System.out.println(msg);
      Files.write(Paths.get("Sokoban.log"), msg.getBytes(), StandardOpenOption.APPEND);
      story.add(msg);
    } catch (IOException e) {
      // exception handling left as an exercise for the reader
    }

    for (ReachableState r : reachabilityGraph.getStates()) {
      Sokoban finalSoko = (Sokoban) r.getGraphRoot();

      int size = finalSoko.getBoxes().getTile().createGoalCondition(true).size();

      if (size == finalSoko.getBoxes().size()) {
        // all boxes on goal fields

        StringBuilder buf = new StringBuilder();

        for (RuleApplication ra : r.getResultOf()) {
          ReachableState src = ra.getSrc();

          buf.append(src.getNumber()).append(" -").append(ra.getDescription()).append("->\n");
        }

        String stateDescription = finalSoko.toString();
        buf.append(stateDescription).append("\n\n");
        System.out.println(buf.toString());

        story.addPreformatted(buf.toString());

        break;
      }
    }

    long usedMillis = usedMillis1;

    int size = reachabilityGraph.getStates().size();

    return size;
  }


  private boolean doAbstraction(SokobanPO sokobanPO) {
    Sokoban soko = sokobanPO.getCurrentMatch();

    AKarli akarli = soko.getAkarli();

    expandAKarliSpace(soko, akarli);

    return true;
  }


  private void expandAKarliSpace(Sokoban soko, AKarli akarli) {
    TileSet todo = new TileSet();
    todo.addAll(akarli.getTiles());

    while (!todo.isEmpty()) {
      Tile current = todo.remove(0);

      // add neighbors that akarli could move to
      for (Tile neighbor : current.getNeighbors()) {
        if (neighbor.isWall() || soko.getBoxes().getTile().contains(neighbor)) {
          continue;
        }

        if (akarli.getTiles().contains(neighbor)) {
          continue;
        }

        akarli.withTiles(neighbor);
        todo.add(neighbor);
      }
    }
  }


  @Test
  public void SokobanSimpleLevel() throws Exception {
    level = ""
        + "  wwww\n"
        + "  w..w\n"
        + "wwwb.w\n"
        + "woobkw\n"
        + "wwwwww\n";

    long usedMillis = SokobanLevel1(false, false, "Level0", level, 3000);

    int size = reachabilityGraph.getStates().size();

    for (ReachableState s : reachabilityGraph.getStates()) {
      System.out.println(s.toString());
    }

    assertEquals("wrong number of levels", 42, size);
  }


  public static void main(String[] args) {
    try {
      new SokobanLevels().SokobanLevel1();
    } catch (Exception e) {
      Logger.getGlobal().warning(e.toString());
    }
  }

  /**
   * 
   * <p>
   * Storyboard
   * <a href='.././src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanLevels.java' type=
   * 'text/x-java'>SokobanLevel1_Level0false</a>
   * </p>
   * <p>
   * Start: First Level
   * </p>
   * <script> var json = { "type":"object", "nodes":[ { "type":"patternObject", "id":"s1 : SokobanPO",
   * "attributes":[] }, { "type":"patternObject", "id":"k2 : KarliPO", "attributes":[] }, {
   * "type":"patternObject", "id":"t3 : TilePO", "attributes":[] }, { "type":"patternObject", "id":"t4
   * : TilePO", "attributes":[ "wall == false" ] }, { "type":"objectdiagram", "style":"nac",
   * "info":"NegativeApplicationCondition", "nodes":[ { "type":"patternObject", "id":"b5 : BoxPO",
   * "attributes":[] } ] } ], "edges":[ { "typ":"EDGE", "source":{ "property":" ", "id":"s1 :
   * SokobanPO" }, "target":{ "property":"karli", "id":"k2 : KarliPO" } }, { "typ":"EDGE", "source":{
   * "property":" ", "id":"k2 : KarliPO" }, "target":{ "property":"tile", "id":"t3 : TilePO" } }, {
   * "typ":"EDGE", "source":{ "property":" ", "id":"t3 : TilePO" }, "target":{ "property":"neighbors",
   * "id":"t4 : TilePO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"s1 : SokobanPO" },
   * "target":{ "property":"boxes", "id":"b5 : BoxPO" } }, { "typ":"EDGE", "source":{ "property":" ",
   * "id":"b5 : BoxPO" }, "target":{ "property":"tile", "id":"t4 : TilePO" } }, { "typ":"EDGE",
   * "source":{ "property":" ", "id":"k2 : KarliPO" }, "target":{ "property":"tile", "id":"t3 :
   * TilePO" }, "style":"destroy" }, { "typ":"EDGE", "source":{ "property":" ", "id":"k2 : KarliPO" },
   * "target":{ "property":"tile", "id":"t4 : TilePO" }, "style":"create" } ] } ;
   * json["options"]={"canvasid":"canvasSokobanLevel1_Level0falsePatternDiagram1", "display":"html",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * <a name = 'step_1'>Step 1: Statistics:</a>
   * </p>
   * <table style="width: auto;" class="table table-bordered table-condensed">
   * <thead>
   * <tr>
   * <th class="text-center">Descr</th>
   * <th class="text-center">Data</th>
   * <th class="text-center"></th>
   * </tr>
   * </thead>
   * 
   * <tbody>
   * <tr>
   * <td class="text-right col-md-1">nodes per state:</td>
   * <td class="text-right col-md-1">0</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">number of states:</td>
   * <td class="text-right col-md-1">42</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">product:</td>
   * <td class="text-right col-md-1">0</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">actual object number:</td>
   * <td class="text-right col-md-1">61</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">collectable object number:</td>
   * <td class="text-right col-md-1">31</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">garbage object number:</td>
   * <td class="text-right col-md-1">-30</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">ratio:</td>
   * <td class="text-right col-md-1">Infinity%</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * </tbody>
   * 
   * </table>
   */
  public void SokobanLevel1() throws Exception {


    level = ""
        + "     wwwww\n"
        + "    ww.o.w\n"
        + "   ww.bo.w\n"
        + "  ww.b...w\n"
        + " ww.bk.www\n"
        + " w.b..ww  \n"
        + " woo.ww   \n"
        + " w...ww   \n"
        + " wwwwww   \n";


    long prevKeepTimeLong = Long.MAX_VALUE;
    long prevCleanTimeLong = Long.MAX_VALUE;
    long newKeepTimeLong = Long.MAX_VALUE - 1;
    long newCleanTimeLong = Long.MAX_VALUE - 1;

    long prevKeepTimeString = Long.MAX_VALUE;
    long prevCleanTimeString = Long.MAX_VALUE;
    long newKeepTimeString = Long.MAX_VALUE - 1;
    long newCleanTimeString = Long.MAX_VALUE - 1;

    int abortCount = 0;

    while (abortCount < 2) {
      if (newKeepTimeLong < prevKeepTimeLong && newCleanTimeLong < prevCleanTimeLong
          && newKeepTimeString < prevKeepTimeString && newCleanTimeString < prevCleanTimeString) {
        abortCount = 0;
      } else {
        abortCount++;
      }

      prevCleanTimeLong = newCleanTimeLong;

      newCleanTimeLong = SokobanLevel1(true, true, "Level1", level, 3000);

      prevKeepTimeLong = newKeepTimeLong;

      newKeepTimeLong = SokobanLevel1(false, true, "Level1", level, 3000);

      prevCleanTimeString = newCleanTimeString;

      newCleanTimeString = SokobanLevel1(true, false, "Level1", level, 3000);

      prevKeepTimeString = newKeepTimeString;

      newKeepTimeString = SokobanLevel1(false, false, "Level1", level, 3000);

    }
  }

  /**
   * 
   * <p>
   * Storyboard
   * <a href='.././src/test/java/org/sdmlib/test/examples/reachabilitygraphs/SokobanLevels.java' type=
   * 'text/x-java'>SokobanLevel1_Level0false</a>
   * </p>
   * <p>
   * Start: First Level
   * </p>
   * <script> var json = { "type":"object", "nodes":[ { "type":"patternObject", "id":"s1 : SokobanPO",
   * "attributes":[] }, { "type":"patternObject", "id":"k2 : KarliPO", "attributes":[] }, {
   * "type":"patternObject", "id":"t3 : TilePO", "attributes":[] }, { "type":"patternObject", "id":"t4
   * : TilePO", "attributes":[ "wall == false" ] }, { "type":"objectdiagram", "style":"nac",
   * "info":"NegativeApplicationCondition", "nodes":[ { "type":"patternObject", "id":"b5 : BoxPO",
   * "attributes":[] } ] } ], "edges":[ { "typ":"EDGE", "source":{ "property":" ", "id":"s1 :
   * SokobanPO" }, "target":{ "property":"karli", "id":"k2 : KarliPO" } }, { "typ":"EDGE", "source":{
   * "property":" ", "id":"k2 : KarliPO" }, "target":{ "property":"tile", "id":"t3 : TilePO" } }, {
   * "typ":"EDGE", "source":{ "property":" ", "id":"t3 : TilePO" }, "target":{ "property":"neighbors",
   * "id":"t4 : TilePO" } }, { "typ":"EDGE", "source":{ "property":" ", "id":"s1 : SokobanPO" },
   * "target":{ "property":"boxes", "id":"b5 : BoxPO" } }, { "typ":"EDGE", "source":{ "property":" ",
   * "id":"b5 : BoxPO" }, "target":{ "property":"tile", "id":"t4 : TilePO" } }, { "typ":"EDGE",
   * "source":{ "property":" ", "id":"k2 : KarliPO" }, "target":{ "property":"tile", "id":"t3 :
   * TilePO" }, "style":"destroy" }, { "typ":"EDGE", "source":{ "property":" ", "id":"k2 : KarliPO" },
   * "target":{ "property":"tile", "id":"t4 : TilePO" }, "style":"create" } ] } ;
   * json["options"]={"canvasid":"canvasSokobanLevel1_Level0falsePatternDiagram1", "display":"html",
   * "fontsize":10,"bar":true}; var g = new Graph(json); g.layout(100,100); </script>
   * <p>
   * <a name = 'step_1'>Step 1: Statistics:</a>
   * </p>
   * <table style="width: auto;" class="table table-bordered table-condensed">
   * <thead>
   * <tr>
   * <th class="text-center">Descr</th>
   * <th class="text-center">Data</th>
   * <th class="text-center"></th>
   * </tr>
   * </thead>
   * 
   * <tbody>
   * <tr>
   * <td class="text-right col-md-1">nodes per state:</td>
   * <td class="text-right col-md-1">0</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">number of states:</td>
   * <td class="text-right col-md-1">42</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">product:</td>
   * <td class="text-right col-md-1">0</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">actual object number:</td>
   * <td class="text-right col-md-1">61</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">collectable object number:</td>
   * <td class="text-right col-md-1">31</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">garbage object number:</td>
   * <td class="text-right col-md-1">-30</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * <tr>
   * <td class="text-right col-md-1">ratio:</td>
   * <td class="text-right col-md-1">Infinity%</td>
   * <td class="text-right col-md-6"></td>
   * </tr>
   * </tbody>
   * 
   * </table>
   */
  public long SokobanLevel1(boolean cleanUp, boolean useLong, String levelName, String level, long noOfStates) throws Exception {
    Storyboard story = new Storyboard().withDocDirName("doc/internal");

    story.getStoryboard().withName("SokobanLevel1_" + levelName + cleanUp);

    story.addStep("First Level");

    Sokoban soko = createLevel(level);

    // story.addObjectDiagram(soko);

    SokobanPO sokoPO = createMoveKarlRule();

    story.addPattern(sokoPO, false);

    SokobanPO pushBoxSokoPO = createPushBoxRule();

    reachabilityGraph = new ReachabilityGraph()
        .withMasterMap(SokobanCreator.createIdMap("s"))
        .withRules("move karl", sokoPO)
        .withRules("push box", pushBoxSokoPO);

    ObjectSet statics = new ObjectSet();
    statics.with(soko.getMaze()).withList(soko.getMaze().getTiles());
    reachabilityGraph.setStaticNodes(statics);

    if (cleanUp) {
      reachabilityGraph.withDoCleanUpTemps();
    }

    if (useLong) {
      reachabilityGraph.withUseLongCertificates();
    }

    ReachableState startState = new ReachableState().withGraphRoot(soko);
    reachabilityGraph.withStart(startState);

    ObjectSet startElems = new ObjectSet();
    SimpleList<Object> dynEdges = new SimpleList<Object>();
    // reachabilityGraph.getLazyCloneOp().aggregate(startElems, dynEdges,
    // reachabilityGraph.getStaticNodes(), soko);

    // =============================================================
    long startTime = System.currentTimeMillis();
    reachabilityGraph.explore(noOfStates, Searchmode.DEFAULT);
    long endTime = System.currentTimeMillis();
    long usedMillis = endTime - startTime;
    // =============================================================

    Table stats = new Table();
    stats.createColumns().withName("Descr").withTdCssClass("text-right col-md-1");
    stats.createColumns().withName("Data").withTdCssClass("text-right col-md-1");
    stats.createColumns().withName(" ").withTdCssClass("text-right col-md-6");

    int startElemsSize = startElems.size();
    stats.createRows("nodes per state:", startElemsSize, " ");

    noOfStates = reachabilityGraph.getStates().size();
    stats.createRows("number of states:", noOfStates, " ");

    long expectedObjectNumber = startElemsSize * noOfStates;
    stats.createRows("product:", expectedObjectNumber, " ");

    startElems.clear();
    dynEdges.clear();

    for (ReachableState state : reachabilityGraph.getStates()) {
      reachabilityGraph.getLazyCloneOp().aggregate(startElems, dynEdges, reachabilityGraph.getStaticNodes(), state.getGraphRoot());
    }
    int actualObjectNumber = startElems.size();
    stats.createRows("actual object number:", actualObjectNumber, " ");

    startElems.clear();
    reachabilityGraph.getLazyCloneOp().collectComponent(startElems, soko);
    int collectedComponentSize = startElems.size();

    stats.createRows("collectable object number:", collectedComponentSize, " ");
    int garbageSize = collectedComponentSize - actualObjectNumber;
    stats.createRows("garbage object number:", garbageSize, " ");

    double ratio = 1.0 * actualObjectNumber / expectedObjectNumber * 100;
    String txt = String.format("%.2f", ratio);
    stats.createRows("ratio:", txt + "%", " ");

    story.addStep("Statistics:");
    story.addTable(stats);

    // story.assertEquals("Garbage size", 0, garbageSize);
    // printStates(story, reachabilityGraph);

    Runtime runtime = Runtime.getRuntime();
    // Run the garbage collector
    runtime.gc();
    // Calculate the used memory
    long emptyMemory = runtime.totalMemory() - runtime.freeMemory();

    try {
      Date now = Date.from(Instant.now());

      String msg = String.format("%s memory lazy %s uselong %s certificates %,d bytes %,d millis \n",
          now.toString(),
          cleanUp ? "clean" : "keep ",
          useLong ? "true " : "false",
          emptyMemory, usedMillis);
      System.out.println(msg);
      Files.write(Paths.get("Sokoban.log"), msg.getBytes(), StandardOpenOption.APPEND);
      story.add(msg);
    } catch (IOException e) {
      // exception handling left as an exercise for the reader
    }


    // story.addObjectDiagramOnlyWith(reachabilityGraph.getStates(),
    // reachabilityGraph.getStates().getRuleapplications());



    story.dumpHTML();

    return usedMillis;
  }


  private void printStates(Storyboard story, ReachabilityGraph reachabilityGraph) {
    for (ReachableState state : reachabilityGraph.getStates()) {
      story.addPreformatted(state.toString());
    }
  }

  private SokobanPO createMoveKarlRule() {
    SokobanPO sokoPO = new SokobanPO();

    KarliPO karliPO = sokoPO.createKarliPO();

    TilePO posPO = karliPO.createTilePO();

    TilePO newPosPO = posPO.createNeighborsPO();

    newPosPO.createWallCondition(false);

    sokoPO.startNAC();

    BoxPO obstacleBoxPO = sokoPO.createBoxesPO();

    obstacleBoxPO.createTileLink(newPosPO);

    sokoPO.endNAC();

    karliPO.createTileLink(posPO, Pattern.DESTROY);
    karliPO.createTileLink(newPosPO, Pattern.CREATE);

    return sokoPO;
  }

  private SokobanPO createPushBoxRule() {
    SokobanPO sokoPO = new SokobanPO();

    KarliPO karliPO = sokoPO.createKarliPO().withPatternObjectName("karli");

    TilePO karliPosPO = karliPO.createTilePO();

    BoxPO boxPO = sokoPO.createBoxesPO();

    TilePO boxPosPO = boxPO.createTilePO();

    karliPosPO.createNeighborsLink(boxPosPO);

    TilePO newBoxPosPO = boxPosPO.createNeighborsPO();

    newBoxPosPO.createWallCondition(false);

    newBoxPosPO.createCondition(p -> straight(karliPosPO, boxPosPO, newBoxPosPO), "straight karliPos, boxPos, newBoxPos");

    sokoPO.startNAC();

    BoxPO obstacleBoxPO = sokoPO.createBoxesPO();

    obstacleBoxPO.createTileLink(newBoxPosPO);

    sokoPO.endNAC();

    boxPO.createTileLink(newBoxPosPO, Pattern.CREATE);

    karliPO.createTileLink(boxPosPO, Pattern.CREATE);

    return sokoPO;
  }


  private SokobanPO createAbstractPushBoxRule() {
    SokobanPO sokoPO = new SokobanPO();

    AKarliPO aKarliPO = sokoPO.createAkarliPO().withPatternObjectName("karli");

    TilePO karliPosPO = aKarliPO.createTilesPO();

    BoxPO boxPO = sokoPO.createBoxesPO();

    TilePO boxPosPO = boxPO.createTilePO();

    karliPosPO.createNeighborsLink(boxPosPO);

    TilePO newBoxPosPO = boxPosPO.createNeighborsPO();

    newBoxPosPO.createWallCondition(false);

    newBoxPosPO.createCondition(p -> straight(karliPosPO, boxPosPO, newBoxPosPO), "straight karliPos, boxPos, newBoxPos");

    sokoPO.startNAC();

    BoxPO obstacleBoxPO = sokoPO.createBoxesPO();

    obstacleBoxPO.createTileLink(newBoxPosPO);

    sokoPO.endNAC();

    boxPO.createTileLink(newBoxPosPO, Pattern.CREATE);


    aKarliPO.startSubPattern();

    TilePO abstractPosPO = aKarliPO.createTilesPO();

    aKarliPO.createTilesLink(abstractPosPO, Pattern.DESTROY);

    abstractPosPO.doAllMatches();

    aKarliPO.endSubPattern();


    aKarliPO.createTilesLink(boxPosPO, Pattern.CREATE);


    aKarliPO.startSubPattern();

    TilePO newAbstractPosPO = aKarliPO.createTilesPO();

    TilePO newNeighborPosPO = newAbstractPosPO.createPath(firstTile -> getReachableNeighbors(sokoPO, firstTile), new TilePO());

    aKarliPO.createTilesLink(newNeighborPosPO, Pattern.CREATE);

    newNeighborPosPO.doAllMatches();

    aKarliPO.endSubPattern();


    return sokoPO;
  }


  private TileSet getReachableNeighbors(SokobanPO sokoPO, Object firstTile) {
    TileSet result = new TileSet();
    result.add(firstTile);

    Sokoban soko = sokoPO.getCurrentMatch();

    TileSet todo = new TileSet();
    todo.add(firstTile);

    while (!todo.isEmpty()) {
      Tile current = todo.remove(0);

      // add neighbors that akarli could move to
      for (Tile neighbor : current.getNeighbors()) {
        if (neighbor.isWall() || soko.getBoxes().getTile().contains(neighbor)) {
          continue;
        }

        if (result.contains(neighbor)) {
          continue;
        }

        result.add(neighbor);
        todo.add(neighbor);
      }
    }

    return result;
  }


  private boolean straight(TilePO karliPosPO, TilePO boxPosPO, TilePO newBoxPosPO) {
    int xDiff = karliPosPO.getX() - boxPosPO.getX();
    int yDiff = karliPosPO.getY() - boxPosPO.getY();

    if (xDiff == boxPosPO.getX() - newBoxPosPO.getX()
        && yDiff == boxPosPO.getY() - newBoxPosPO.getY()) {
      return true;
    }
    return false;
  }

  private Sokoban createLevel(String level) {
    Sokoban soko = new Sokoban();
    Maze maze = soko.createMaze();

    Tile tile = null;
    Tile neighbor;
    int y = 0;

    String[] split = level.split("\n");
    maze.withHeight(split.length);
    for (String line : split) {
      maze.withWidth(line.length());
      for (int x = 0; x < line.length(); x++) {
        char c = line.charAt(x);

        if (c == ' ') {
          tile = null;
        } else if (c == 'w') {
          tile = maze.createTiles()
              .withX(x)
              .withY(y)
              .withWall(true);
        } else if (c == '.') {
          tile = maze.createTiles()
              .withX(x)
              .withY(y);
        } else if (c == 'o') {
          tile = maze.createTiles()
              .withX(x)
              .withY(y)
              .withGoal(true);
        } else if (c == 'b') {
          tile = maze.createTiles()
              .withX(x)
              .withY(y);

          soko.createBoxes().withTile(tile);
        } else if (c == 'k') {
          tile = maze.createTiles()
              .withX(x)
              .withY(y);

          soko.createKarli().withTile(tile);
        }

        neighbor = maze.getTile(x - 1, y);
        if (neighbor != null) {
          neighbor.withNeighbors(tile);
        }
        neighbor = maze.getTile(x, y - 1);
        if (neighbor != null) {
          neighbor.withNeighbors(tile);
        }

      }

      y++;
    }

    return soko;
  }
}
