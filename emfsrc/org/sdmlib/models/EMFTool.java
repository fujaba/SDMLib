package org.sdmlib.models;

import java.util.LinkedHashSet;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.sdmlib.codegen.CGUtil;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.Role.R;
import org.sdmlib.utils.StrUtil;

public class EMFTool
{
   public EPackage classModelToEPackage(ClassModel model)
   {
      // create the corresponding EMF ecore structures
      EcoreFactory ecoreFactory = EcoreFactory.eINSTANCE;
      EcorePackage ecorePackage = EcorePackage.eINSTANCE;
      
      // first we need a package
      EPackage ePackage = ecoreFactory.createEPackage();
      ePackage.setName(StrUtil.upFirstChar(CGUtil.shortClassName(model.getPackageName()))+"Package");
      ePackage.setNsPrefix(CGUtil.shortClassName(model.getPackageName()));
      ePackage.setNsURI("http:///" + model.getPackageName() + ".ecore");
      
      
      for (Clazz c : model.getClasses())
      {
         EClass ec = ecoreFactory.createEClass();
         ec.setName(CGUtil.shortClassName(c.getName()));
         
         ePackage.getEClassifiers().add(ec);
         
         // transfer attributes
         for (Attribute attr : c.getAttributes())
         {
            EAttribute eattr = ecoreFactory.createEAttribute();
            eattr.setName(attr.getName());
            EClassifier estring = ecorePackage.getEClassifier("E" + attr.getType());
            eattr.setEType(estring);
            ec.getEStructuralFeatures().add(eattr);
         }
         
      }
      
      // transfer super class
      for (Clazz c : model.getClasses())
      {
         if (c.getSuperClass() != null)
         {
            EClass kidEClass = (EClass) ePackage.getEClassifier(CGUtil.shortClassName(c.getName()));
            EClass superEClass = (EClass) ePackage.getEClassifier(CGUtil.shortClassName(c.getSuperClass().getName()));
            
            kidEClass.getESuperTypes().add(superEClass);
         }
      }
      
      // transfer assocs
      for (Association assoc : model.getClasses().getSourceRoles().getAssoc())
      {
         Role srcRole = assoc.getSource();
         Role tgtRole = assoc.getTarget();
            
         EReference fwdRef = ecoreFactory.createEReference();
         fwdRef.setName(tgtRole.getName());
         EClass tgtEClass = (EClass) ePackage.getEClassifier(CGUtil.shortClassName(tgtRole.getClazz().getName()));
         fwdRef.setEType(tgtEClass);
         if (R.MANY.toString().equals(tgtRole.getCard()))
         {
            fwdRef.setUpperBound(-1);
         }

         EReference bwdRef = ecoreFactory.createEReference();
         bwdRef.setName(srcRole.getName());
         EClass srcEClass = (EClass) ePackage.getEClassifier(CGUtil.shortClassName(srcRole.getClazz().getName()));
         bwdRef.setEType(srcEClass);
         if (R.MANY.toString().equals(srcRole.getCard()))
         {
            bwdRef.setUpperBound(-1);
         }

         fwdRef.setEOpposite(bwdRef);
         bwdRef.setEOpposite(fwdRef);

         srcEClass.getEStructuralFeatures().add(fwdRef);

         tgtEClass.getEStructuralFeatures().add(bwdRef);
            
      }
      
      return ePackage;
   }
   
   
   public ClassModel genModelToClassModel(String genModelFileName)
   {
      GenModelPackage.eINSTANCE.eClass();
      
      ResourceSet resSet = new ResourceSetImpl();
      
      URI genModelURI = URI.createFileURI(genModelFileName);
      
      resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("genmodel", new XMLResourceFactoryImpl());
      
      Resource genModelRes = resSet.getResource(genModelURI, true);
      
      GenModel genModel = (GenModel) genModelRes.getContents().get(0);
      
      String packageName = genModel.getModelName();
      
      String ecoreFileName = genModel.getForeignModel().get(0);
      
      URI ecoreModelURI = URI.createFileURI(ecoreFileName);
      
      resSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("ecore", new XMLResourceFactoryImpl());
      
      Resource ecoreModelRes = resSet.getResource(ecoreModelURI, true);
      
      EPackage epackage = (EPackage) ecoreModelRes.getContents().get(0);
      
      ClassModel model = new ClassModel(packageName);
      
      
      LinkedHashSet<EReference> refs = new LinkedHashSet<EReference>();
      
      // add classes
      for (EClassifier eClassifier : epackage.getEClassifiers())
      {
         if (eClassifier instanceof EClass)
         {
            EClass eclass = (EClass) eClassifier;
            
            Clazz clazz = model.createClazz(eclass.getName());
            
            for (EAttribute eattr : eclass.getEAttributes())
            {
               clazz.withAttribute(eattr.getName(), eattr.getEType().getName().substring(1));
            }
            
            for (EReference eref : eclass.getEReferences())
            {
               if ( ! refs.contains(eref))
               {
                  if (eref.getEOpposite() == null)
                  {
                     clazz.withAttribute(eref.getName(), eref.getEReferenceType().getName());
                  }
                  else if ( ! refs.contains(eref.getEOpposite()))
                  {
                     refs.add(eref);
                  }
               }
            }
         }
      }
      
      // inheritance
      for (EClassifier eClassifier : epackage.getEClassifiers())
      {
         if (eClassifier instanceof EClass)
         {
            EClass eclass = (EClass) eClassifier;
            
            if ( ! eclass.getESuperTypes().isEmpty())
            {
               Clazz kidClazz = model.getClazz(eclass.getName());
               Clazz superClazz = model.getClazz(eclass.getESuperTypes().get(0).getName());
               
               kidClazz.withSuperClass(superClazz);
            }
            
         }
      }
      
      // assocs
      for (EReference eref : refs)
      {
         String tgtClassName = eref.getEReferenceType().getName();
         String tgtRoleName = eref.getName();
         R tgtCard = R.ONE;
         if (eref.getUpperBound() != 1)
         {
            tgtCard = R.MANY;
         }

         eref = eref.getEOpposite();
         String srcClassName = eref.getEReferenceType().getName();
         String srcRoleName = eref.getName();
         R srcCard = R.ONE;
         if (eref.getUpperBound() != 1)
         {
            srcCard = R.MANY;
         }
         
         Clazz tgtClazz = model.getClazz(tgtClassName);
         Clazz srcClazz = model.getClazz(srcClassName);
         
         srcClazz.withAssoc(tgtClazz, tgtRoleName, tgtCard, srcRoleName, srcCard);
      }
      
      return model;
   }
}
