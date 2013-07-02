/**
 */
package org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class emfstudyrightgenmodelFactoryImpl extends EFactoryImpl implements emfstudyrightgenmodelFactory
{
   /**
    * Creates the default factory implementation.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public static emfstudyrightgenmodelFactory init()
   {
      try
      {
         emfstudyrightgenmodelFactory theemfstudyrightgenmodelFactory = (emfstudyrightgenmodelFactory)EPackage.Registry.INSTANCE.getEFactory(emfstudyrightgenmodelPackage.eNS_URI);
         if (theemfstudyrightgenmodelFactory != null)
         {
            return theemfstudyrightgenmodelFactory;
         }
      }
      catch (Exception exception)
      {
         EcorePlugin.INSTANCE.log(exception);
      }
      return new emfstudyrightgenmodelFactoryImpl();
   }

   /**
    * Creates an instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public emfstudyrightgenmodelFactoryImpl()
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
         case emfstudyrightgenmodelPackage.UNIVERSITY: return createUniversity();
         case emfstudyrightgenmodelPackage.PERSON: return createPerson();
         case emfstudyrightgenmodelPackage.STUDENT: return createStudent();
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
   public emfstudyrightgenmodelPackage getemfstudyrightgenmodelPackage()
   {
      return (emfstudyrightgenmodelPackage)getEPackage();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @deprecated
    * @generated
    */
   public static emfstudyrightgenmodelPackage getPackage()
   {
      return emfstudyrightgenmodelPackage.eINSTANCE;
   }

} //emfstudyrightgenmodelFactoryImpl
