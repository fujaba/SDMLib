/**
 */
package org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage.studyrightgenmodelPackage
 * @generated
 */
public interface studyrightgenmodelFactory extends EFactory
{
   /**
    * The singleton instance of the factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   studyrightgenmodelFactory eINSTANCE = org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage.impl.studyrightgenmodelFactoryImpl.init();

   /**
    * Returns a new object of class '<em>University</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>University</em>'.
    * @generated
    */
   University createUniversity();

   /**
    * Returns a new object of class '<em>Person</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Person</em>'.
    * @generated
    */
   Person createPerson();

   /**
    * Returns a new object of class '<em>Student</em>'.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return a new object of class '<em>Student</em>'.
    * @generated
    */
   Student createStudent();

   /**
    * Returns the package supported by this factory.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @return the package supported by this factory.
    * @generated
    */
   studyrightgenmodelPackage getstudyrightgenmodelPackage();

} //studyrightgenmodelFactory
