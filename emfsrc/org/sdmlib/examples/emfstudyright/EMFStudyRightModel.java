package org.sdmlib.examples.emfstudyright;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;

import javax.swing.text.html.HTML;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.swt.widgets.Monitor;
import org.junit.Test;
import org.sdmlib.codegen.CGUtil;
import org.sdmlib.models.EMFTool;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.scenarios.Scenario;
import org.sdmlib.utils.StrUtil;

public class EMFStudyRightModel
{
   @Test
   public void EMFStudyRightClasses()
   {
      Scenario scenario = new Scenario("emfsrc");

      scenario.add("We build some example classes and then turn them into an Ecore model ", Scenario.MODELING, "zuendorf", "08.02.2013 14:53:42", 8, 0);

      ClassModel model = new ClassModel("org.sdmlib.examples.emfstudyright");

      Clazz uniClazz = model.createClazz("University", "name", R.STRING);
      
      Clazz persClazz = model.createClazz("Person", "name", R.STRING);
      
      Clazz studClazz = uniClazz.createClassAndAssoc("Student", "students", R.MANY, "uni", R.ONE)
            .withAttributes("studId", R.STRING)
            .withSuperClass(persClazz);
      
      scenario.addImage(model.dumpClassDiag("examples", "EMFStudyRightClassDiag"));
      
      EMFTool emfTool = new EMFTool();
      
      // turn into EPackage
      EPackage ePackage = emfTool.classModelToEPackage(model);
            
      // write to file 
      ResourceSet resourceSet = new ResourceSetImpl();
      resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new XMLResourceFactoryImpl());
      
      Resource resource = resourceSet.createResource(URI.createURI("./EMFStudyRight.ecore"));
      
      resource.getContents().add(ePackage);
      
      
      try
      {
         resource.save(null);
         
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         resource.save(out, null);
         
         String xmlText = "<textarea rows=\"20\" cols=\"120\" style='border:none'>" + out.toString() +  "</textarea>";
         
         scenario.add(xmlText);
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      // try to build the genmodel
      URI genModelURI = URI.createFileURI("./EMFStudyRight.genmodel");
      
      resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("genmodel", new XMLResourceFactoryImpl());
      
      Resource genModelResource = resourceSet.createResource(genModelURI);
      
      GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
      
      genModelResource.getContents().add(genModel);
      
      resourceSet.getResources().add(genModelResource);
      
      genModel.setModelDirectory("./SDMLib/emfsrc");
      genModel.getForeignModel().add("./EMFStudyRight.ecore");
      genModel.initialize(Collections.singleton(ePackage));
      
      GenPackage genPackage = genModel.getGenPackages().get(0);
      
      genModel.setModelName(genModelURI.trimFileExtension().lastSegment());

      genPackage.setPrefix(CGUtil.shortClassName(model.getPackageName()) + "genmodel");
      genPackage.setBasePackage(model.getPackageName());

      try
      {
         genModelResource.save(Collections.EMPTY_MAP);
      }
      catch (IOException e)
      {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      IStatus status = genModel.validate();
      if (!status.isOK())
      {
        System.out.println(status);
      }
      
      
      
      // now generate Java
      // org.eclipse.xsd
      //      JETEmitter e;
      //      CodeGenEcorePlugin codeGenPlugin = CodeGenEcorePlugin.INSTANCE;
      //      
      //      genModel.setCanGenerate(true);
      //      
      //      org.eclipse.emf.codegen.ecore.generator.Generator gen = new org.eclipse.emf.codegen.ecore.generator.Generator();
      //      
      //      gen.setInput(genModel);
      //      
      //      boolean flag = gen.canGenerate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE);
      //      
      //      if (!flag)
      //      {
      //         System.out.println("Cannot generate");
      //      }
      //      
      //      BasicMonitor basicMonitor = new BasicMonitor();
      //      gen.generate(genModel, GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, basicMonitor);
      
      
      scenario.dumpHTML();
   }
   
   @Test
   public void testLoadEcoreCreateClassModel()
   {
      Scenario scenario = new Scenario("emfsrc");

      scenario.add("Reading ecore file and generating sdmlib classModel ", Scenario.MODELING, "zuendorf", "28.06.2013 12:56:42", 1, 24);

      
      EMFTool emfTool = new EMFTool();
      
      ClassModel model = emfTool.genModelToClassModel("./EMFStudyRight.genmodel");
      
      scenario.addImage(model.dumpClassDiag("StudyRighLoadedFromECore"));
      
      scenario.dumpHTML();
   }
}
