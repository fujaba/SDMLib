/**
 */
package org.sdmlib.examples.emfstudyright.EmfstudyrightPackage;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Student</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student#getStudId <em>Stud Id</em>}</li>
 *   <li>{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student#getUni <em>Uni</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelPackage#getStudent()
 * @model
 * @generated
 */
public interface Student extends Person
{
   /**
    * Returns the value of the '<em><b>Stud Id</b></em>' attribute.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Stud Id</em>' attribute isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Stud Id</em>' attribute.
    * @see #setStudId(String)
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelPackage#getStudent_StudId()
    * @model
    * @generated
    */
   String getStudId();

   /**
    * Sets the value of the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student#getStudId <em>Stud Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Stud Id</em>' attribute.
    * @see #getStudId()
    * @generated
    */
   void setStudId(String value);

   
   String getName();
   
   void setName(String n);
   
   /**
    * Returns the value of the '<em><b>Uni</b></em>' reference.
    * It is bidirectional and its opposite is '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University#getStudents <em>Students</em>}'.
    * <!-- begin-user-doc -->
    * <p>
    * If the meaning of the '<em>Uni</em>' reference isn't clear,
    * there really should be more of a description here...
    * </p>
    * <!-- end-user-doc -->
    * @return the value of the '<em>Uni</em>' reference.
    * @see #setUni(University)
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelPackage#getStudent_Uni()
    * @see org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University#getStudents
    * @model opposite="students"
    * @generated
    */
   University getUni();

   /**
    * Sets the value of the '{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student#getUni <em>Uni</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @param value the new value of the '<em>Uni</em>' reference.
    * @see #getUni()
    * @generated
    */
   void setUni(University value);

} // Student
