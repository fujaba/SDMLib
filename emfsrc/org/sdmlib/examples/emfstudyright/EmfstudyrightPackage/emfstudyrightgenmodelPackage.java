/**
 */
package org.sdmlib.examples.emfstudyright.EmfstudyrightPackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelFactory
 * @model kind="package"
 * @generated
 */
public interface emfstudyrightgenmodelPackage extends EPackage
{
   /**
    * The package name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNAME = "EmfstudyrightPackage";

   /**
    * The package namespace URI.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_URI = "http:///org.sdmlib.examples.emfstudyright.ecore";

   /**
    * The package namespace name.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   String eNS_PREFIX = "emfstudyright";

   /**
    * The singleton instance of the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   emfstudyrightgenmodelPackage eINSTANCE = org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.emfstudyrightgenmodelPackageImpl.init();

   /**
    * The meta object id for the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.UniversityImpl <em>University</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.UniversityImpl
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.emfstudyrightgenmodelPackageImpl#getUniversity()
    * @generated
    */
   int UNIVERSITY = 0;

   /**
    * The number of structural features of the '<em>University</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UNIVERSITY_FEATURE_COUNT = 0;

   /**
    * The meta object id for the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.StudentImpl <em>Student</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.StudentImpl
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.emfstudyrightgenmodelPackageImpl#getStudent()
    * @generated
    */
   int STUDENT = 1;

   /**
    * The number of structural features of the '<em>Student</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int STUDENT_FEATURE_COUNT = 0;


   /**
    * Returns the meta object for class '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University <em>University</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>University</em>'.
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University
    * @generated
    */
   EClass getUniversity();

   /**
    * Returns the meta object for class '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student <em>Student</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Student</em>'.
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student
    * @generated
    */
   EClass getStudent();

   /**
    * Returns the factory that creates the instances of the model.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the factory that creates the instances of the model.
    * @generated
    */
   emfstudyrightgenmodelFactory getemfstudyrightgenmodelFactory();

   /**
    * <!-- begin-user-doc -->
    * Defines literals for the meta objects that represent
    * <ul>
    *   <li>each class,</li>
    *   <li>each feature of each class,</li>
    *   <li>each enum,</li>
    *   <li>and each data type</li>
    * </ul>
    * <!-- end-user-doc -->
    * @generated
    */
   interface Literals
   {
      /**
       * The meta object literal for the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.UniversityImpl <em>University</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.UniversityImpl
       * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.emfstudyrightgenmodelPackageImpl#getUniversity()
       * @generated
       */
      EClass UNIVERSITY = eINSTANCE.getUniversity();

      /**
       * The meta object literal for the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.StudentImpl <em>Student</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.StudentImpl
       * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.emfstudyrightgenmodelPackageImpl#getStudent()
       * @generated
       */
      EClass STUDENT = eINSTANCE.getStudent();

   }

} //emfstudyrightgenmodelPackage
