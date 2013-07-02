/**
 */
package org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Person;
import org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student;
import org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University;
import org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelFactory;
import org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class emfstudyrightgenmodelPackageImpl extends EPackageImpl implements emfstudyrightgenmodelPackage
{
   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass universityEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass personEClass = null;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private EClass studentEClass = null;

   /**
    * Creates an instance of the model <b>Package</b>, registered with
    * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
    * package URI value.
    * <p>Note: the correct way to create the package is via the static
    * factory method {@link #init init()}, which also performs
    * initialization of the package, or returns the registered package,
    * if one already exists.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see org.eclipse.emf.ecore.EPackage.Registry
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelPackage#eNS_URI
    * @see #init()
    * @generated
    */
   private emfstudyrightgenmodelPackageImpl()
   {
      super(eNS_URI, emfstudyrightgenmodelFactory.eINSTANCE);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private static boolean isInited = false;

   /**
    * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
    * 
    * <p>This method is used to initialize {@link emfstudyrightgenmodelPackage#eINSTANCE} when that field is accessed.
    * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #eNS_URI
    * @see #createPackageContents()
    * @see #initializePackageContents()
    * @generated
    */
   public static emfstudyrightgenmodelPackage init()
   {
      if (isInited) return (emfstudyrightgenmodelPackage)EPackage.Registry.INSTANCE.getEPackage(emfstudyrightgenmodelPackage.eNS_URI);

      // Obtain or create and register package
      emfstudyrightgenmodelPackageImpl theemfstudyrightgenmodelPackage = (emfstudyrightgenmodelPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof emfstudyrightgenmodelPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new emfstudyrightgenmodelPackageImpl());

      isInited = true;

      // Create package meta-data objects
      theemfstudyrightgenmodelPackage.createPackageContents();

      // Initialize created meta-data
      theemfstudyrightgenmodelPackage.initializePackageContents();

      // Mark meta-data to indicate it can't be changed
      theemfstudyrightgenmodelPackage.freeze();

  
      // Update the registry and return the package
      EPackage.Registry.INSTANCE.put(emfstudyrightgenmodelPackage.eNS_URI, theemfstudyrightgenmodelPackage);
      return theemfstudyrightgenmodelPackage;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getUniversity()
   {
      return universityEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getUniversity_Name()
   {
      return (EAttribute)universityEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getUniversity_Students()
   {
      return (EReference)universityEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getPerson()
   {
      return personEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getPerson_Name()
   {
      return (EAttribute)personEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EClass getStudent()
   {
      return studentEClass;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EAttribute getStudent_StudId()
   {
      return (EAttribute)studentEClass.getEStructuralFeatures().get(0);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EReference getStudent_Uni()
   {
      return (EReference)studentEClass.getEStructuralFeatures().get(1);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public emfstudyrightgenmodelFactory getemfstudyrightgenmodelFactory()
   {
      return (emfstudyrightgenmodelFactory)getEFactoryInstance();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private boolean isCreated = false;

   /**
    * Creates the meta-model objects for the package.  This method is
    * guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void createPackageContents()
   {
      if (isCreated) return;
      isCreated = true;

      // Create classes and their features
      universityEClass = createEClass(UNIVERSITY);
      createEAttribute(universityEClass, UNIVERSITY__NAME);
      createEReference(universityEClass, UNIVERSITY__STUDENTS);

      personEClass = createEClass(PERSON);
      createEAttribute(personEClass, PERSON__NAME);

      studentEClass = createEClass(STUDENT);
      createEAttribute(studentEClass, STUDENT__STUD_ID);
      createEReference(studentEClass, STUDENT__UNI);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   private boolean isInitialized = false;

   /**
    * Complete the initialization of the package and its meta-model.  This
    * method is guarded to have no affect on any invocation but its first.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void initializePackageContents()
   {
      if (isInitialized) return;
      isInitialized = true;

      // Initialize package
      setName(eNAME);
      setNsPrefix(eNS_PREFIX);
      setNsURI(eNS_URI);

      // Add supertypes to classes
      studentEClass.getESuperTypes().add(this.getPerson());

      // Initialize classes and features; add operations and parameters
      initEClass(universityEClass, University.class, "University", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getUniversity_Name(), ecorePackage.getEString(), "name", null, 0, 1, University.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getUniversity_Students(), this.getStudent(), this.getStudent_Uni(), "students", null, 0, -1, University.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(personEClass, Person.class, "Person", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getPerson_Name(), ecorePackage.getEString(), "name", null, 0, 1, Person.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      initEClass(studentEClass, Student.class, "Student", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
      initEAttribute(getStudent_StudId(), ecorePackage.getEString(), "studId", null, 0, 1, Student.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
      initEReference(getStudent_Uni(), this.getUniversity(), this.getUniversity_Students(), "uni", null, 0, 1, Student.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

      // Create resource
      createResource(eNS_URI);
   }

} //emfstudyrightgenmodelPackageImpl
