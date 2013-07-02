/**
 */
package org.sdmlib.examples.emfstudyright.EmfstudyrightPackage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>University</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University#getName <em>Name</em>}</li>
 *   <li>{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University#getStudents <em>Students</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelPackage#getUniversity()
 * @model
 * @generated
 */
public interface University extends EObject
{
   /**
    * Returns the value of the '<em><b>Name</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Name</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Name</em>' attribute.
    * @see #setName(String)
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelPackage#getUniversity_Name()
    * @model
    * @generated
    */
   String getName();

   /**
    * Sets the value of the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University#getName <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Name</em>' attribute.
    * @see #getName()
    * @generated
    */
   void setName(String value);

   /**
    * Returns the value of the '<em><b>Students</b></em>' reference list.
    * The list contents are of type {@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student}.
    * It is bidirectional and its opposite is '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student#getUni <em>Uni</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Students</em>' reference list isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Students</em>' reference list.
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelPackage#getUniversity_Students()
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student#getUni
    * @model type="org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student" opposite="uni"
    * @generated
    */
   EList getStudents();

} // University
