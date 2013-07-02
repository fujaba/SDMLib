/**
 */
package org.sdmlib.examples.emfstudyright.EmfstudyrightPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UNIVERSITY__NAME = 0;

   /**
    * The feature id for the '<em><b>Students</b></em>' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UNIVERSITY__STUDENTS = 1;

   /**
    * The number of structural features of the '<em>University</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int UNIVERSITY_FEATURE_COUNT = 2;

   /**
    * The meta object id for the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.PersonImpl <em>Person</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.PersonImpl
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.emfstudyrightgenmodelPackageImpl#getPerson()
    * @generated
    */
   int PERSON = 1;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PERSON__NAME = 0;

   /**
    * The number of structural features of the '<em>Person</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int PERSON_FEATURE_COUNT = 1;

   /**
    * The meta object id for the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.StudentImpl <em>Student</em>}' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.StudentImpl
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.emfstudyrightgenmodelPackageImpl#getStudent()
    * @generated
    */
   int STUDENT = 2;

   /**
    * The feature id for the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int STUDENT__NAME = PERSON__NAME;

   /**
    * The feature id for the '<em><b>Stud Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int STUDENT__STUD_ID = PERSON_FEATURE_COUNT + 0;

   /**
    * The feature id for the '<em><b>Uni</b></em>' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int STUDENT__UNI = PERSON_FEATURE_COUNT + 1;

   /**
    * The number of structural features of the '<em>Student</em>' class.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    * @ordered
    */
   int STUDENT_FEATURE_COUNT = PERSON_FEATURE_COUNT + 2;


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
    * Returns the meta object for the attribute '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University#getName()
    * @see #getUniversity()
    * @generated
    */
   EAttribute getUniversity_Name();

   /**
    * Returns the meta object for the reference list '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University#getStudents <em>Students</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference list '<em>Students</em>'.
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University#getStudents()
    * @see #getUniversity()
    * @generated
    */
   EReference getUniversity_Students();

   /**
    * Returns the meta object for class '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Person <em>Person</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for class '<em>Person</em>'.
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Person
    * @generated
    */
   EClass getPerson();

   /**
    * Returns the meta object for the attribute '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Person#getName <em>Name</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Name</em>'.
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Person#getName()
    * @see #getPerson()
    * @generated
    */
   EAttribute getPerson_Name();

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
    * Returns the meta object for the attribute '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student#getStudId <em>Stud Id</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the attribute '<em>Stud Id</em>'.
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student#getStudId()
    * @see #getStudent()
    * @generated
    */
   EAttribute getStudent_StudId();

   /**
    * Returns the meta object for the reference '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student#getUni <em>Uni</em>}'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the meta object for the reference '<em>Uni</em>'.
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student#getUni()
    * @see #getStudent()
    * @generated
    */
   EReference getStudent_Uni();

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
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute UNIVERSITY__NAME = eINSTANCE.getUniversity_Name();

      /**
       * The meta object literal for the '<em><b>Students</b></em>' reference list feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference UNIVERSITY__STUDENTS = eINSTANCE.getUniversity_Students();

      /**
       * The meta object literal for the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.PersonImpl <em>Person</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.PersonImpl
       * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.emfstudyrightgenmodelPackageImpl#getPerson()
       * @generated
       */
      EClass PERSON = eINSTANCE.getPerson();

      /**
       * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute PERSON__NAME = eINSTANCE.getPerson_Name();

      /**
       * The meta object literal for the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.StudentImpl <em>Student</em>}' class.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.StudentImpl
       * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.emfstudyrightgenmodelPackageImpl#getStudent()
       * @generated
       */
      EClass STUDENT = eINSTANCE.getStudent();

      /**
       * The meta object literal for the '<em><b>Stud Id</b></em>' attribute feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EAttribute STUDENT__STUD_ID = eINSTANCE.getStudent_StudId();

      /**
       * The meta object literal for the '<em><b>Uni</b></em>' reference feature.
       * <!-- begin-user-doc -->
       * <!-- end-user-doc -->
       * @generated
       */
      EReference STUDENT__UNI = eINSTANCE.getStudent_Uni();

   }

} //emfstudyrightgenmodelPackage
