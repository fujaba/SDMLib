package org.sdmlib.models.objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.sdmlib.StrUtil;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.logic.ClassModelAdapter;
import org.sdmlib.models.classes.logic.GenClassModel;
import org.sdmlib.storyboards.StoryboardImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XML2GenericUtil
{
   public static void xml2GenericGraph(StoryboardImpl story, String... caseNames)
   {
      ClassModelAdapter learnedModel = new ClassModel().getGenerator();

      for (String caseName : caseNames)
      {
         String XMLText = XML2GenericUtil.readFile("ttc2014-fixml-master/test_cases/" + caseName + ".xml");

         story.addPreformatted(XMLText);

         DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
         try
         {
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File("ttc2014-fixml-master/test_cases/" + caseName + ".xml"));

            GenericGraph graphRoot = XML2GenericUtil.xml2GenericGraph(doc);

            story.addGenericObjectDiag(graphRoot);

            learnedModel.learnFromGenericObjects("de.kassel.ttc2014.fixml.caseUnified", graphRoot.getObjects().first());
         }
         catch (Exception e)
         {
            System.err.println("Error in XML case " + caseName + "\nMessage: " + e.getMessage());
            e.printStackTrace();
         }
      }

      learnedModel.generate("src");

      //      story.addClassDiagram(learnedModel);
   }


   public static GenericGraph xml2GenericGraph(Document doc)
   {
      return xml2GenericGraph(doc, false);
   }
   public static GenericGraph xml2GenericGraph(Document doc, boolean docRootIsObject)
   {
      GenericGraph graph = new GenericGraph();

      Node node = doc.getFirstChild();

      if (docRootIsObject)
      {
         Node entity = node;
         GenericObject genericObject = graph.createObjects()
               .withType(StrUtil.upFirstChar(toValidJavaId(entity.getNodeName())));

         // handle attributes
         for (int j = 0; j < entity.getAttributes().getLength(); j++)
         {
            Node attr = entity.getAttributes().item(j);
            String key = attr.getNodeName();
            String value = attr.getNodeValue();

            genericObject.createAttrs()
            .withName(toValidJavaId(StrUtil.downFirstChar(key)))
            .withValue(value);
         }

         visitXMLEntities(entity, graph, genericObject);

      }
      else
      {
         XML2GenericUtil.visitXMLEntities(node, graph, null);
      }

      return graph;
   }

   public static void visitXMLEntities(Node xmlRoot, GenericGraph graph, GenericObject parent)
   {
      for (int i = 0; i < xmlRoot.getChildNodes().getLength(); i++)
      {
         Node entity = xmlRoot.getChildNodes().item(i);

         if (entity.getNodeType() == Node.TEXT_NODE)
         {
            continue;
         }

         // handle attributes encoded between tags
         if (entity.getChildNodes().getLength() == 1
               && entity.getAttributes().getLength() == 0)
         {
            Node singleChild = entity.getFirstChild();

            if (singleChild.getNodeType() == Node.TEXT_NODE)
            {
               parent.createAttrs()
               .withName(toValidJavaId(StrUtil.downFirstChar(entity.getNodeName())))
               .withValue(singleChild.getNodeValue());

               continue;
            }
         }

         GenericObject genericObject = graph.createObjects()
               .withType(StrUtil.upFirstChar(toValidJavaId(entity.getNodeName())));

         if (parent != null)
         {
            // create link to new kid
            parent.createOutgoingLinks()
            .withSrcLabel(toValidJavaId(parent.getType().toLowerCase()))
            .withTgtLabel(toValidJavaId(entity.getNodeName().toLowerCase()))
            .withTgt(genericObject);
         }

         // handle attributes
         for (int j = 0; j < entity.getAttributes().getLength(); j++)
         {
            Node attr = entity.getAttributes().item(j);
            String key = attr.getNodeName();
            String value = attr.getNodeValue();

            genericObject.createAttrs()
            .withName(toValidJavaId(StrUtil.downFirstChar(key)))
            .withValue(value);
         }

         visitXMLEntities(entity, graph, genericObject);

      }
   }

   public static final String javaKeyWords = " abstract assert boolean break byte case catch char class const continue default do double else enum extends final finally float for if goto implements import instanceof int interface long native new package private protected public return short static strictfp super switch synchronized this throw throws transient try void volatile while ";

   private static String toValidJavaId(String tag)
   {
      if (javaKeyWords.indexOf(" " + tag + " ") >= 0)
      {
         tag = "_" + tag;
      }

      return tag;
   }

   public static String readFile(String fileName)
   {
      try
      {
         BufferedReader br = new BufferedReader(new FileReader(fileName));
         StringBuilder sb = new StringBuilder();
         String line = br.readLine();

         while (line != null)
         {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = br.readLine();
         }
         br.close();
         return sb.toString();
      }
      catch (Exception e)
      {
         e.printStackTrace();
      }
      return null;
   }

}
