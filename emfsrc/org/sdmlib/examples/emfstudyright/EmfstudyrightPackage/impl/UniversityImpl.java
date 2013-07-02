/**
 */
package org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.Student;
import org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.University;
import org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.emfstudyrightgenmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>University</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.UniversityImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.sdmlib.examples.emfstudyright.EmfstudyrightPackage.impl.UniversityImpl#getStudents <em>Students</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UniversityImpl extends EObjectImpl implements University
{
   /**
    * The default value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected static final String NAME_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getName()
    * @generated
    * @ordered
    */
   protected String name = NAME_EDEFAULT;

   /**
    * The cached value of the '{@link #getStudents() <em>Students</em>}' reference list.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStudents()
    * @generated
    * @ordered
    */
   protected EList students;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected UniversityImpl()
   {
      super();
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected EClass eStaticClass()
   {
      return emfstudyrightgenmodelPackage.Literals.UNIVERSITY;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getName()
   {
      return name;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setName(String newName)
   {
      String oldName = name;
      name = newName;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, emfstudyrightgenmodelPackage.UNIVERSITY__NAME, oldName, name));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public EList getStudents()
   {
      if (students == null)
      {
         students = new EObjectWithInverseResolvingEList(Student.class, this, emfstudyrightgenmodelPackage.UNIVERSITY__STUDENTS, emfstudyrightgenmodelPackage.STUDENT__UNI);
      }
      return students;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case emfstudyrightgenmodelPackage.UNIVERSITY__STUDENTS:
            return ((InternalEList)getStudents()).basicAdd(otherEnd, msgs);
      }
      return super.eInverseAdd(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
   {
      switch (featureID)
      {
         case emfstudyrightgenmodelPackage.UNIVERSITY__STUDENTS:
            return ((InternalEList)getStudents()).basicRemove(otherEnd, msgs);
      }
      return super.eInverseRemove(otherEnd, featureID, msgs);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public Object eGet(int featureID, boolean resolve, boolean coreType)
   {
      switch (featureID)
      {
         case emfstudyrightgenmodelPackage.UNIVERSITY__NAME:
            return getName();
         case emfstudyrightgenmodelPackage.UNIVERSITY__STUDENTS:
            return getStudents();
      }
      return super.eGet(featureID, resolve, coreType);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void eSet(int featureID, Object newValue)
   {
      switch (featureID)
      {
         case emfstudyrightgenmodelPackage.UNIVERSITY__NAME:
            setName((String)newValue);
            return;
         case emfstudyrightgenmodelPackage.UNIVERSITY__STUDENTS:
            getStudents().clear();
            getStudents().addAll((Collection)newValue);
            return;
      }
      super.eSet(featureID, newValue);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void eUnset(int featureID)
   {
      switch (featureID)
      {
         case emfstudyrightgenmodelPackage.UNIVERSITY__NAME:
            setName(NAME_EDEFAULT);
            return;
         case emfstudyrightgenmodelPackage.UNIVERSITY__STUDENTS:
            getStudents().clear();
            return;
      }
      super.eUnset(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public boolean eIsSet(int featureID)
   {
      switch (featureID)
      {
         case emfstudyrightgenmodelPackage.UNIVERSITY__NAME:
            return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
         case emfstudyrightgenmodelPackage.UNIVERSITY__STUDENTS:
            return students != null && !students.isEmpty();
      }
      return super.eIsSet(featureID);
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String toString()
   {
      if (eIsProxy()) return super.toString();

      StringBuffer result = new StringBuffer(super.toString());
      result.append(" (name: ");
      result.append(name);
      result.append(')');
      return result.toString();
   }

} //UniversityImpl
