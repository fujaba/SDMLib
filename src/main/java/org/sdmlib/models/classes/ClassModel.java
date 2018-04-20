/*
   Copyright (c) 2016 Stefan
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
   
package org.sdmlib.models.classes;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Set;

import org.sdmlib.doc.DocEnvironment;
import org.sdmlib.doc.GraphFactory;
import org.sdmlib.doc.JavascriptAdapter.Javascript;
import org.sdmlib.doc.interfaze.Adapter.GuiAdapter;
import org.sdmlib.models.classes.logic.GenClassModel;
import org.sdmlib.serialization.PropertyChangeInterface;

import de.uniks.networkparser.graph.Clazz;
import de.uniks.networkparser.graph.Feature;
import de.uniks.networkparser.graph.FeatureProperty;
import de.uniks.networkparser.graph.GraphModel;
import de.uniks.networkparser.interfaces.BaseItem;
import de.uniks.networkparser.interfaces.SendableEntity;
import de.uniks.networkparser.list.SimpleSet;
   /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsModel.java' type='text/x-java'>StudyRightWithAssignmentsClassGeneration</a></p>
    * <p>1. generate class University</p>
    * <pre>      	  ClassModel model = new ClassModel(&quot;org.sdmlib.test.examples.studyrightWithAssignments.model&quot;);
    * 
    *       Clazz universityClass = model.createClazz(&quot;University&quot;)
    *             .withAttribute(&quot;name&quot;, DataType.STRING);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep2.png"></img>
    * <p>2. generate class Student</p>
    * <pre>            Clazz studentClass = model.createClazz(&quot;Student&quot;)
    *             .withAttribute(&quot;name&quot;, DataType.STRING)
    *             .withAttribute(&quot;id&quot;, DataType.STRING)
    *             .withAttribute(&quot;assignmentPoints&quot;, DataType.INT)
    *             .withAttribute(&quot;motivation&quot;, DataType.INT) 
    *             .withAttribute(&quot;credits&quot;, DataType.INT);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep5.png"></img>
    * <p>3. add University --> Student association</p>
    * <pre>            universityClass.withBidirectional(studentClass, &quot;students&quot;, Cardinality.MANY, &quot;university&quot;, Cardinality.ONE);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep8.png"></img>
    * <p>4. add University --> Room association</p>
    * <pre>            Clazz roomClass = model.createClazz(&quot;Room&quot;)
    *             .withAttribute(&quot;name&quot;, DataType.STRING)
    *             .withAttribute(&quot;topic&quot;, DataType.STRING)
    *             .withAttribute(&quot;credits&quot;, DataType.INT);
    * 
    *       roomClass.withMethod(&quot;findPath&quot;, DataType.STRING, new Parameter(DataType.INT).with(&quot;motivation&quot;));
    * 
    *       &#x2F;&#x2F;Association universityToRoom = 
    *       universityClass.createBidirectional(roomClass, &quot;rooms&quot;, Cardinality.MANY, &quot;university&quot;, Cardinality.ONE).with(AssociationTypes.AGGREGATION);
    *       
    *       &#x2F;&#x2F; Association doors = 
    *       roomClass.withBidirectional(roomClass, &quot;doors&quot;, Cardinality.MANY, &quot;doors&quot;, Cardinality.MANY);
    * 
    *       &#x2F;&#x2F; Association studentsInRoom = 
    *       studentClass.withBidirectional(roomClass, &quot;in&quot;, Cardinality.ONE, &quot;students&quot;, Cardinality.MANY);
    *       studentClass.withBidirectional(studentClass, &quot;friends&quot;, Cardinality.MANY, &quot;friends&quot;, Cardinality.MANY);
    *       
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep11.png"></img>
    * <p>5. add assignments:</p>
    * <pre>            Clazz assignmentClass = model.createClazz(&quot;Assignment&quot;)
    *                .withAttribute(&quot;content&quot;, DataType.STRING)
    *                .withAttribute(&quot;points&quot;, DataType.INT)
    *                .withBidirectional(roomClass, &quot;room&quot;, Cardinality.ONE, &quot;assignments&quot;, Cardinality.MANY);
    *       
    *       studentClass.withBidirectional(assignmentClass, &quot;done&quot;, Cardinality.MANY, &quot;students&quot;, Cardinality.MANY);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep14.png"></img>
    * <p>6. generate class source files.</p>
    * <pre>            model.generate(&quot;src&#x2F;test&#x2F;java&quot;); &#x2F;&#x2F; usually don&#x27;t specify anything here, then it goes into src
    * </pre>
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
 * @see <a href='../../../../../../../src/main/java/org/sdmlib/models/tables/TableModel.java'>TableModel.java</a>
 * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsModel#testStudyRightWithAssignmentsClassGeneration
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountClassModel#testGroupAccountCodegen
 * @see org.sdmlib.models.tables.TableModel#testTableModel
 * @see org.sdmlib.simple.TestModelCreation#testCreateEntireModel
 * @see org.sdmlib.test.examples.gofpattern.StrategyModel#GofStrategyModel
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011SimpleMigration
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldConstantTransformation2WithReferences
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011SimpleMigrationViaGenericGraphs
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldCountNumberOfNodes
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldConstantTransformation1
 * @see org.sdmlib.test.examples.ludo.LudoModel#testLudoModel
 * @see org.sdmlib.test.examples.modelcouch.ModelCouchTasksModel#couchSpaceTasksModel
 * @see org.sdmlib.test.examples.modelspace.chat.ModelSpaceChatModel#testModelSpaceChatModel
 * @see org.sdmlib.test.examples.patternrewriteops.TrainModel#TrainModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#UniDirectFerryMansProblemModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#SimpleReachabilityGraphModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#FerryMansProblemModel
 */
public class ClassModel extends GraphModel implements PropertyChangeInterface, SendableEntity 
{
	public static final String DEFAULTPACKAGE = "i.love.sdmlib";
	public static final String PROPERTY_CLASSES = "classes";
	private static final String PROPERTY_FEATURE = "feature";
	private Set<FeatureProperty> features = Feature.getAll();
	private GenClassModel generator;

   /**
    * 
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsModel.java' type='text/x-java'>StudyRightWithAssignmentsClassGeneration</a></p>
    * <p>1. generate class University</p>
    * <pre>      	  ClassModel model = new ClassModel(&quot;org.sdmlib.test.examples.studyrightWithAssignments.model&quot;);
    * 
    *       Clazz universityClass = model.createClazz(&quot;University&quot;)
    *             .withAttribute(&quot;name&quot;, DataType.STRING);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep2.png"></img>
    * <p>2. generate class Student</p>
    * <pre>            Clazz studentClass = model.createClazz(&quot;Student&quot;)
    *             .withAttribute(&quot;name&quot;, DataType.STRING)
    *             .withAttribute(&quot;id&quot;, DataType.STRING)
    *             .withAttribute(&quot;assignmentPoints&quot;, DataType.INT)
    *             .withAttribute(&quot;motivation&quot;, DataType.INT) 
    *             .withAttribute(&quot;credits&quot;, DataType.INT);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep5.png"></img>
    * <p>3. add University --> Student association</p>
    * <pre>            universityClass.withBidirectional(studentClass, &quot;students&quot;, Cardinality.MANY, &quot;university&quot;, Cardinality.ONE);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep8.png"></img>
    * <p>4. add University --> Room association</p>
    * <pre>            Clazz roomClass = model.createClazz(&quot;Room&quot;)
    *             .withAttribute(&quot;name&quot;, DataType.STRING)
    *             .withAttribute(&quot;topic&quot;, DataType.STRING)
    *             .withAttribute(&quot;credits&quot;, DataType.INT);
    * 
    *       roomClass.withMethod(&quot;findPath&quot;, DataType.STRING, new Parameter(DataType.INT).with(&quot;motivation&quot;));
    * 
    *       &#x2F;&#x2F;Association universityToRoom = 
    *       universityClass.createBidirectional(roomClass, &quot;rooms&quot;, Cardinality.MANY, &quot;university&quot;, Cardinality.ONE).with(AssociationTypes.AGGREGATION);
    *       
    *       &#x2F;&#x2F; Association doors = 
    *       roomClass.withBidirectional(roomClass, &quot;doors&quot;, Cardinality.MANY, &quot;doors&quot;, Cardinality.MANY);
    * 
    *       &#x2F;&#x2F; Association studentsInRoom = 
    *       studentClass.withBidirectional(roomClass, &quot;in&quot;, Cardinality.ONE, &quot;students&quot;, Cardinality.MANY);
    *       studentClass.withBidirectional(studentClass, &quot;friends&quot;, Cardinality.MANY, &quot;friends&quot;, Cardinality.MANY);
    *       
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep11.png"></img>
    * <p>5. add assignments:</p>
    * <pre>            Clazz assignmentClass = model.createClazz(&quot;Assignment&quot;)
    *                .withAttribute(&quot;content&quot;, DataType.STRING)
    *                .withAttribute(&quot;points&quot;, DataType.INT)
    *                .withBidirectional(roomClass, &quot;room&quot;, Cardinality.ONE, &quot;assignments&quot;, Cardinality.MANY);
    *       
    *       studentClass.withBidirectional(assignmentClass, &quot;done&quot;, Cardinality.MANY, &quot;students&quot;, Cardinality.MANY);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep14.png"></img>
    * <p>6. generate class source files.</p>
    * <pre>            model.generate(&quot;src&#x2F;test&#x2F;java&quot;); &#x2F;&#x2F; usually don&#x27;t specify anything here, then it goes into src
    * </pre>
    * @see <a href='../../../../../../../src/main/java/org/sdmlib/models/tables/TableModel.java'>TableModel.java</a>
 * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsModel#testStudyRightWithAssignmentsClassGeneration
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountClassModel#testGroupAccountCodegen
 * @see org.sdmlib.models.tables.TableModel#testTableModel
 * @see org.sdmlib.simple.TestModelCreation#testCreateEntireModel
 * @see org.sdmlib.test.examples.gofpattern.StrategyModel#GofStrategyModel
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011SimpleMigration
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldConstantTransformation2WithReferences
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011SimpleMigrationViaGenericGraphs
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldCountNumberOfNodes
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldConstantTransformation1
 * @see org.sdmlib.test.examples.ludo.LudoModel#testLudoModel
 * @see org.sdmlib.test.examples.modelcouch.ModelCouchTasksModel#couchSpaceTasksModel
 * @see org.sdmlib.test.examples.modelspace.chat.ModelSpaceChatModel#testModelSpaceChatModel
 * @see org.sdmlib.test.examples.patternrewriteops.TrainModel#TrainModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#UniDirectFerryMansProblemModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#SimpleReachabilityGraphModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#FerryMansProblemModel
 */
   public ClassModel() {
		name = getDefaultPackage();
		setAuthorName(System.getProperty("user.name"));
	}
   
   @Override
	public String getDefaultPackage() {
		return DEFAULTPACKAGE;
	}

   /**
    * Constructor
    * <p>Storyboard <a href='./src/test/java/org/sdmlib/test/examples/studyrightWithAssignments/StudyRightWithAssignmentsModel.java' type='text/x-java'>StudyRightWithAssignmentsClassGeneration</a></p>
    * <p>1. generate class University</p>
    * <pre>      	  ClassModel model = new ClassModel(&quot;org.sdmlib.test.examples.studyrightWithAssignments.model&quot;);
    * 
    *       Clazz universityClass = model.createClazz(&quot;University&quot;)
    *             .withAttribute(&quot;name&quot;, DataType.STRING);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep2.png"></img>
    * <p>2. generate class Student</p>
    * <pre>            Clazz studentClass = model.createClazz(&quot;Student&quot;)
    *             .withAttribute(&quot;name&quot;, DataType.STRING)
    *             .withAttribute(&quot;id&quot;, DataType.STRING)
    *             .withAttribute(&quot;assignmentPoints&quot;, DataType.INT)
    *             .withAttribute(&quot;motivation&quot;, DataType.INT) 
    *             .withAttribute(&quot;credits&quot;, DataType.INT);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep5.png"></img>
    * <p>3. add University --> Student association</p>
    * <pre>            universityClass.withBidirectional(studentClass, &quot;students&quot;, Cardinality.MANY, &quot;university&quot;, Cardinality.ONE);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep8.png"></img>
    * <p>4. add University --> Room association</p>
    * <pre>            Clazz roomClass = model.createClazz(&quot;Room&quot;)
    *             .withAttribute(&quot;name&quot;, DataType.STRING)
    *             .withAttribute(&quot;topic&quot;, DataType.STRING)
    *             .withAttribute(&quot;credits&quot;, DataType.INT);
    * 
    *       roomClass.withMethod(&quot;findPath&quot;, DataType.STRING, new Parameter(DataType.INT).with(&quot;motivation&quot;));
    * 
    *       &#x2F;&#x2F;Association universityToRoom = 
    *       universityClass.createBidirectional(roomClass, &quot;rooms&quot;, Cardinality.MANY, &quot;university&quot;, Cardinality.ONE).with(AssociationTypes.AGGREGATION);
    *       
    *       &#x2F;&#x2F; Association doors = 
    *       roomClass.withBidirectional(roomClass, &quot;doors&quot;, Cardinality.MANY, &quot;doors&quot;, Cardinality.MANY);
    * 
    *       &#x2F;&#x2F; Association studentsInRoom = 
    *       studentClass.withBidirectional(roomClass, &quot;in&quot;, Cardinality.ONE, &quot;students&quot;, Cardinality.MANY);
    *       studentClass.withBidirectional(studentClass, &quot;friends&quot;, Cardinality.MANY, &quot;friends&quot;, Cardinality.MANY);
    *       
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep11.png"></img>
    * <p>5. add assignments:</p>
    * <pre>            Clazz assignmentClass = model.createClazz(&quot;Assignment&quot;)
    *                .withAttribute(&quot;content&quot;, DataType.STRING)
    *                .withAttribute(&quot;points&quot;, DataType.INT)
    *                .withBidirectional(roomClass, &quot;room&quot;, Cardinality.ONE, &quot;assignments&quot;, Cardinality.MANY);
    *       
    *       studentClass.withBidirectional(assignmentClass, &quot;done&quot;, Cardinality.MANY, &quot;students&quot;, Cardinality.MANY);
    * </pre>
    * <img src="doc-files/StudyRightWithAssignmentsClassGenerationStep14.png"></img>
    * <p>6. generate class source files.</p>
    * <pre>            model.generate(&quot;src&#x2F;test&#x2F;java&quot;); &#x2F;&#x2F; usually don&#x27;t specify anything here, then it goes into src
    * </pre>
    * @param packageName PackageName of ClassModel 
    * @see <a href='../../../../../../../src/main/java/org/sdmlib/models/tables/TableModel.java'>TableModel.java</a>
    * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsModel#testStudyRightWithAssignmentsClassGeneration
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountClassModel#testGroupAccountCodegen
 * @see org.sdmlib.models.tables.TableModel#testTableModel
 * @see org.sdmlib.simple.TestModelCreation#testCreateEntireModel
 * @see org.sdmlib.test.examples.gofpattern.StrategyModel#GofStrategyModel
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011SimpleMigration
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldConstantTransformation2WithReferences
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011SimpleMigrationViaGenericGraphs
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldCountNumberOfNodes
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldConstantTransformation1
 * @see org.sdmlib.test.examples.ludo.LudoModel#testLudoModel
 * @see org.sdmlib.test.examples.modelcouch.ModelCouchTasksModel#couchSpaceTasksModel
 * @see org.sdmlib.test.examples.modelspace.chat.ModelSpaceChatModel#testModelSpaceChatModel
 * @see org.sdmlib.test.examples.patternrewriteops.TrainModel#TrainModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#UniDirectFerryMansProblemModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#SimpleReachabilityGraphModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#FerryMansProblemModel
 */
   public ClassModel(String packageName)
	   {
		  this();
	      with(packageName);
	   }

   /**
    * 
    * @see <a href='../../../../../../../src/main/java/org/sdmlib/models/tables/TableModel.java'>TableModel.java</a>
 * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsModel#testStudyRightWithAssignmentsClassGeneration
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountClassModel#testGroupAccountCodegen
 * @see org.sdmlib.models.tables.TableModel#testTableModel
 * @see org.sdmlib.simple.TestModelCreation#testCreateEntireModel
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldConstantTransformation2WithReferences
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldCountNumberOfNodes
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldConstantTransformation1
 * @see org.sdmlib.test.examples.modelspace.chat.ModelSpaceChatModel#testModelSpaceChatModel
 * @see org.sdmlib.test.examples.patternrewriteops.TrainModel#TrainModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#UniDirectFerryMansProblemModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#SimpleReachabilityGraphModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#FerryMansProblemModel
 */
   public ClassModel generate() 
   {
      getGenerator().generate();
      return this;
	}

   /**
    * 
    * @see <a href='../../../../../../../src/main/java/org/sdmlib/models/tables/TableModel.java'>TableModel.java</a>
 * @see org.sdmlib.test.examples.studyrightWithAssignments.StudyRightWithAssignmentsModel#testStudyRightWithAssignmentsClassGeneration
 * @see org.sdmlib.test.examples.groupaccount.GroupAccountClassModel#testGroupAccountCodegen
 * @see org.sdmlib.models.tables.TableModel#testTableModel
 * @see org.sdmlib.simple.TestModelCreation#testCreateEntireModel
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldConstantTransformation2WithReferences
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldCountNumberOfNodes
 * @see org.sdmlib.test.examples.helloworld.HelloWorldTTC2011#testTTC2011HelloWorldConstantTransformation1
 * @see org.sdmlib.test.examples.modelspace.chat.ModelSpaceChatModel#testModelSpaceChatModel
 * @see org.sdmlib.test.examples.patternrewriteops.TrainModel#TrainModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#UniDirectFerryMansProblemModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#SimpleReachabilityGraphModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#FerryMansProblemModel
 */
   public ClassModel generate(String rootDir) {
		getGenerator().generate(rootDir);
		return this;
	}

	public GenClassModel getGenerator() {
		if (generator == null) {
			this.setGenerator(new GenClassModel());
		}
		return generator;
	}

	protected void setGenerator(GenClassModel value) {
		if (this.generator != value) {
			GenClassModel oldValue = this.generator;
			if (this.generator != null) {
				this.generator = null;
				oldValue.setModel(null);
			}
			this.generator = value;
			if (value != null) {
				value.setModel(this);
			}
		}
	}

	public String dumpClassDiagram(String diagName) {
		GuiAdapter graphViz = GraphFactory.getAdapter();

		return graphViz.dumpClassDiagram(diagName, this);
	}

	private String dumpClassDiagram(String diagName, String outputType) {
		GuiAdapter graphViz = GraphFactory.getAdapter(outputType);
		return graphViz.dumpClassDiagram(diagName, this);
	}

	public void removeAllGeneratedCode() {
	   File srcDir = new File("src/main/java");

      if (srcDir.exists()) {
         // getGenerator().removeAllGeneratedCode("src/main/java", "src/main/java", "src/main/java");
      }
      else
      {
         getGenerator().removeAllGeneratedCode("src", "src", "src");
      }
	}

	public void removeAllGeneratedCode(String rootDir) {
		getGenerator().removeAllGeneratedCode(rootDir, rootDir, rootDir);
	}

	protected final PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	@Override
	public PropertyChangeSupport getPropertyChangeSupport() {
		return listeners;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(" ").append(this.getId());
		result.append(" ").append(this.getName());
      return result.substring(1);
	}
	
	@Override
	public ClassModel with(String name) {
		super.with(name);
		return this;
	}

	public ClassModel without(Clazz... values) {
		super.without(values);
		return this;
	}
	

	public ClassModel withFeature(Feature... value) {
		if (value == null) {
			return this;
		}
		for (Feature item : value) {
			if (item != null) {
				if (this.features.add(item.create())) {
					getPropertyChangeSupport().firePropertyChange(PROPERTY_FEATURE, null, item);
				}
			}
		}
		return this;
	}

	public ClassModel withoutFeature(Feature... value) {
		if (value == null) {
			return this;
		}
		for (Feature item : value) {
			if (item != null) {
				if (this.features.remove(item)) {
					getPropertyChangeSupport().firePropertyChange(PROPERTY_FEATURE, item, null);
				} else { 
					// Search for name
					for(Iterator<FeatureProperty> i = features.iterator();i.hasNext();) {
						FeatureProperty prop = i.next();
						if(prop.getName().toString().equals(item.toString())) {
							this.features.remove(prop);
							break;
						}
					}
				}
			}
		}
		return this;
	}

	public ClassModel withFeatures(Set<FeatureProperty> value) {
		if (value == null) {
			this.features.clear();
			return this;
		}
		for (FeatureProperty item : value) {
			if (item != null) {
				if (this.features.add(item)) {
					getPropertyChangeSupport().firePropertyChange(PROPERTY_FEATURE, null, item);
				}
			}
		}
		return this;
	}

	public boolean hasFeature(Feature value) {
		for(Iterator<FeatureProperty> i = features.iterator();i.hasNext();) {
			FeatureProperty item = i.next();
			if(item.equals(value)) {
				return true;
			}
		}
		return false;
	}

	public boolean hasFeature(Feature feature, Clazz value) {
		FeatureProperty property = getFeature(feature);
		if(property != null) {
			return property.match(value);
		}
		return false;
	}

	/**
	 * dump classdiagram
	 * 
	 * @param diagramName
	 *            Diagrammname
	 */
	public boolean dumpHTML(String diagramName) {
		dumpHTML(diagramName, "doc", Javascript.NAME);
		return true;
	}

	/**
	 * dump classdiagram
	 * 
	 * @param diagramName
	 *            Diagrammname
	 * @param folder
	 *            target folder
	 * @param outputType
	 *            GuiAdapter name (Javascript.NAME or GraphViz.NAME)
	 */

	public void dumpHTML(String diagramName, String folder, String outputType) {
		new File(folder).mkdirs();

		String dumpClassDiagram = dumpClassDiagram(diagramName, outputType);

		// build index
		String htmlTemplate = "<html>\n" + "<head>\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"includes/diagramstyle.css\">\n"
				+ "<script src=\"includes/dagre.min.js\"></script>\n" + "<script src=\"includes/graph.js\"></script>\n"
				+ "<script src=\"includes/drawer.js\"></script>\n" + "</head>\n" + "<body>\n" + "bodytext\n"
				+ "</body>\n" + "</html>\n";

		htmlTemplate = htmlTemplate.replaceFirst("bodytext", dumpClassDiagram);

		File file = new File(folder + "/" + diagramName + ".html");
		try {
			PrintStream out = new PrintStream(file);
			out.println(htmlTemplate);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		new DocEnvironment().copyJS(folder);
	}
	public Clazz getClazz(String name)
	{
		if (name == null) {
			return null;
		}
		for (Clazz c : getClazzes()) {
			if (c.getName().equals(name) ) {
				return c;
			}else if(name.indexOf(".")>0) {
				if(c.getName().equals(name.substring(name.lastIndexOf(".")+1))) {
					return c;
				}
			}else if(c.getName().endsWith("." + name)){
				return c;
			}
		}
		return null;
	}

	public SimpleSet<Clazz> getEnumerations() {
		SimpleSet<Clazz> clazzes = getClazzes();
		SimpleSet<Clazz> collection = new SimpleSet<Clazz>();
		for (Clazz child : clazzes) {
			if (child.getType()==Clazz.TYPE_ENUMERATION)  {
				collection.add(child);
			}
		}
		return collection;
	}
	
	public boolean removePropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().removePropertyChangeListener(listener);
		return true;
	}

	public boolean removePropertyChangeListener(String property,
			PropertyChangeListener listener) {
		if (listeners != null) {
			listeners.removePropertyChangeListener(property, listener);
		}
		return true;
	}
	
	@Override
	public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(propertyName, listener);
		return true;
	}

	@Override
	public boolean addPropertyChangeListener(PropertyChangeListener listener) {
		getPropertyChangeSupport().addPropertyChangeListener(listener);
		return true;
	}

   
   //==========================================================================
   
   public void removeYou()
   {
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

	public FeatureProperty getFeature(Feature value) {
		for(Iterator<FeatureProperty> i = features.iterator();i.hasNext();) {
			FeatureProperty item = i.next();
			if(item.equals(value)) {
				return item;
			}
		}
		return null;
	}

	@Override
	public boolean add(Object... values) {
		return false;
	}

	@Override
	public BaseItem getNewList(boolean keyValue) {
		return new ClassModel();
	}
}
