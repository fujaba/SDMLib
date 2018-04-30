package org.sdmlib.models;

import java.util.LinkedHashMap;
import java.util.StringTokenizer;

/**
 *
 * <p>Storyboard <a href='.././src/test/java/org/sdmlib/test/doc/TestJavaDocStories.java'>GenJavaDocStory</a></p>
 * <p>Yamler reads simple key value pairs in YAML syntax.</p>
 * <p>Example:</p>
 * <pre>            String yaml = &quot;&quot; +
 *               &quot;msgType: newPlayer\n&quot; +
 *               &quot;login: albert\n&quot; +
 *               &quot;colors: blue red \n&quot;;
 *
 *       Yamler yamler = new Yamler();
 *       LinkedHashMap&lt;String, String&gt; map = yamler.decode(yaml);
 * </pre>
 * <pre>{msgType=newPlayer, login=albert, colors=blue red}</pre>
 */
public class Yamler
{
   private String yaml;
   private StringTokenizer tokenizer;
   private String lookAheadToken;
   private String currentToken;
   private int currentPos;
   private int lookAheadPos;


   public Yamler(String yaml)
   {
      this.yaml = yaml;

      tokenizer = new StringTokenizer(yaml);
      lookAheadToken = "";
      nextToken();
      nextToken();
   }


   public Yamler()
   {
      // empty
   }

   /**
    *
    * Storyboard {@link org.sdmlib.models.classes.ClassModel}
    *
    * <p>Storyboard <a href='https://github.com/fujaba/SDMLib/blob/master/src/test/java/org/sdmlib/test/examples/groupaccount/GroupAccountTests.java'>PlainYaml</a></p>
    * <p>Start: plain yaml to be decoded to map</p>
    * <pre>joining: abu
    * lastChanges: 2018-03-17T14:48:00.000.abu 2018-03-17T14:38:00.000.bob 2018-03-17T14:18:00.000.xia</pre>
    * <pre>{joining=abu, lastChanges=2018-03-17T14:48:00.000.abu 2018-03-17T14:38:00.000.bob 2018-03-17T14:18:00.000.xia}</pre>
    * <p>Check: value for joining abu actual abu</p>
    * <p><a name = 'step_1'>Step 1: Alternatively, use special object type map</a></p>
    * <pre>- m: .Map
    *   joining: abu
    *   lastChanges: 2018-03-17T14:48:00.000.abu 2018-03-17T14:38:00.000.bob 2018-03-17T14:18:00.000.xia</pre>
    * <pre>{joining=abu, lastChanges=2018-03-17T14:48:00.000.abu 2018-03-17T14:38:00.000.bob 2018-03-17T14:18:00.000.xia}</pre>
    * <p>Check: value for joining abu actual abu</p>
    *
    *
    * @see <a href='file://YamlFileMap.java'>YamlFileMap.java</a>
    * @param yaml yaml text
    * @return map with key value pairs
    */
   public LinkedHashMap<String,String> decode(String yaml)
   {
      this.yaml = yaml;
      tokenizer = new StringTokenizer(yaml);
      lookAheadToken = "";
      nextToken();
      nextToken();

      LinkedHashMap<String, String> result = new LinkedHashMap<>();

      while (currentToken.endsWith(":"))
      {
         String attrName = stripColon(currentToken);

         nextToken();

         String value = "";
         int valueStart = currentPos;

         // many values
         while ( ! currentToken.equals("")
                 && ! currentToken.endsWith(":"))
         {
            value = yaml.substring(valueStart, currentPos + currentToken.length());

            nextToken();
         }

         result.put(attrName, value);
      }

      return result;

   }

   public String nextToken()
   {
      currentToken = lookAheadToken;
      currentPos = lookAheadPos;

      if (tokenizer.hasMoreTokens())
      {

         lookAheadToken = tokenizer.nextToken();
         lookAheadPos = yaml.indexOf(lookAheadToken, lookAheadPos + currentToken.length());
         // lookAheadPos = scanner.match().start();
      }
      else
      {
         lookAheadToken = "";
      }



      if (lookAheadToken.startsWith("\""))
      {
         // get up to end of string
         int stringStartPos = lookAheadPos + 1;
         String subToken = lookAheadToken;
         //MatchResult match = scanner.match();
         int subTokenEnd = lookAheadPos + subToken.length();
         while ( subTokenEnd < stringStartPos+1 || ( ! subToken.endsWith("\"") || subToken.endsWith("\\\""))
                 && tokenizer.hasMoreTokens())
         {
            subToken = tokenizer.nextToken();
            subTokenEnd = yaml.indexOf(subToken, subTokenEnd) + subToken.length();
         }

         lookAheadToken = yaml.substring(stringStartPos, subTokenEnd - 1);

         lookAheadToken = deEncapsulate(lookAheadToken);
      }

      return currentToken;
   }

   public String stripColon(String key)
   {
      String id = key;

      if (key.endsWith(":"))
      {
         id = key.substring(0, key.length() - 1);
      }
      else
      {
         printError("key does not end with ':' " + key);
      }

      return id;
   }


   public String getCurrentToken()
   {
      return currentToken;
   }

   String encapsulate(String value)
   {
      if (value.matches("[a-zA-Z0-9_\\.]+"))
      {
         return value;
      }
      value = value.replaceAll("\"", "\\\\\"");
      return "\"" + value + "\"";
   }

   String deEncapsulate(String value)
   {
      value = value.replaceAll("\\\\\"", "\"");
      return value;
   }

   void printError(String msg)
   {
      int startPos = currentPos;

      if (startPos >=  10)
      {
         startPos -= 10;
      }
      else
      {
         startPos = 0;
      }

      int endPos = currentPos + 20;

      if (endPos >=  yaml.length())
      {
         endPos = yaml.length();
      }

      System.err.println(yaml.substring(startPos, currentPos) + "<--" + msg + "-->" + yaml.substring(currentPos, endPos));
   }

   public String getLookAheadToken()
   {
      return lookAheadToken;
   }

   public int getCurrentPos()
   {
      return currentPos;
   }

   public int getLookAheadPos()
   {
      return lookAheadPos;
   }
}
