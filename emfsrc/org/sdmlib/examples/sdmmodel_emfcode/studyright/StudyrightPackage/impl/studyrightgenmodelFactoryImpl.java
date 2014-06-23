/**
 */
package org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class studyrightgenmodelFactoryImpl extends EFactoryImpl implements studyrightgenmodelFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static studyrightgenmodelFactory init()
   {
      try
      {
         studyrightgenmodelFactory thestudyrightgenmodelFactory = (studyrightgenmodelFactory)EPackage.Registry.INSTANCE.getEFactory(studyrightgenmodelPackage.eNS_URI);
         if (thestudyrightgenmodelFactory != null)
         {
            return thestudyrightgenmodelFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new studyrightgenmodelFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public studyrightgenmodelFactoryImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EObject create(EClass eClass)
   {
      switch (eClass.getClassifierID())
      {
         case studyrightgenmodelPackage.UNIVERSITY: return createUniversity();
         case studyrightgenmodelPackage.PERSON: return createPerson();
         case studyrightgenmodelPackage.STUDENT: return createStudent();
         default:
            throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
      }
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public University createUniversity()
   {
      UniversityImpl university = new UniversityImpl();
      return university;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Person createPerson()
   {
      PersonImpl person = new PersonImpl();
      return person;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Student createStudent()
   {
      StudentImpl student = new StudentImpl();
      return student;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public studyrightgenmodelPackage getstudyrightgenmodelPackage()
   {
      return (studyrightgenmodelPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   public static studyrightgenmodelPackage getPackage()
   {
      return studyrightgenmodelPackage.eINSTANCE;
   }

} //studyrightgenmodelFactoryImpl
