/**
 */
package org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage.Student;
import org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage.University;
import org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage.studyrightgenmodelPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Student</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage.impl.StudentImpl#getStudId <em>Stud Id</em>}</li>
 *   <li>{@link org.sdmlib.examples.sdmmodel_emfcode.studyright.StudyrightPackage.impl.StudentImpl#getUni <em>Uni</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StudentImpl extends PersonImpl implements Student
{
   /**
    * The default value of the '{@link #getStudId() <em>Stud Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStudId()
    * @generated
    * @ordered
    */
   protected static final String STUD_ID_EDEFAULT = null;

   /**
    * The cached value of the '{@link #getStudId() <em>Stud Id</em>}' attribute.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getStudId()
    * @generated
    * @ordered
    */
   protected String studId = STUD_ID_EDEFAULT;

   /**
    * The cached value of the '{@link #getUni() <em>Uni</em>}' reference.
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @see #getUni()
    * @generated
    * @ordered
    */
   protected University uni;

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   protected StudentImpl()
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
      return studyrightgenmodelPackage.Literals.STUDENT;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public String getStudId()
   {
      return studId;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setStudId(String newStudId)
   {
      String oldStudId = studId;
      studId = newStudId;
      if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, studyrightgenmodelPackage.STUDENT__STUD_ID, oldStudId, studId));
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public University getUni()
   {
      if (uni != null && uni.eIsProxy())
      {
         InternalEObject oldUni = (InternalEObject)uni;
         uni = (University)eResolveProxy(oldUni);
         if (uni != oldUni)
         {
            if (eNotificationRequired())
               eNotify(new ENotificationImpl(this, Notification.RESOLVE, studyrightgenmodelPackage.STUDENT__UNI, oldUni, uni));
         }
      }
      return uni;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public University basicGetUni()
   {
      return uni;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public NotificationChain basicSetUni(University newUni, NotificationChain msgs)
   {
      University oldUni = uni;
      uni = newUni;
      if (eNotificationRequired())
      {
         ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, studyrightgenmodelPackage.STUDENT__UNI, oldUni, newUni);
         if (msgs == null) msgs = notification; else msgs.add(notification);
      }
      return msgs;
   }

   /**
    * <!-- begin-user-doc -->
    * <!-- end-user-doc -->
    * @generated
    */
   public void setUni(University newUni)
   {
      if (newUni != uni)
      {
         NotificationChain msgs = null;
         if (uni != null)
            msgs = ((InternalEObject)uni).eInverseRemove(this, studyrightgenmodelPackage.UNIVERSITY__STUDENTS, University.class, msgs);
         if (newUni != null)
            msgs = ((InternalEObject)newUni).eInverseAdd(this, studyrightgenmodelPackage.UNIVERSITY__STUDENTS, University.class, msgs);
         msgs = basicSetUni(newUni, msgs);
         if (msgs != null) msgs.dispatch();
      }
      else if (eNotificationRequired())
         eNotify(new ENotificationImpl(this, Notification.SET, studyrightgenmodelPackage.STUDENT__UNI, newUni, newUni));
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
         case studyrightgenmodelPackage.STUDENT__UNI:
            if (uni != null)
               msgs = ((InternalEObject)uni).eInverseRemove(this, studyrightgenmodelPackage.UNIVERSITY__STUDENTS, University.class, msgs);
            return basicSetUni((University)otherEnd, msgs);
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
         case studyrightgenmodelPackage.STUDENT__UNI:
            return basicSetUni(null, msgs);
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
         case studyrightgenmodelPackage.STUDENT__STUD_ID:
            return getStudId();
         case studyrightgenmodelPackage.STUDENT__UNI:
            if (resolve) return getUni();
            return basicGetUni();
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
         case studyrightgenmodelPackage.STUDENT__STUD_ID:
            setStudId((String)newValue);
            return;
         case studyrightgenmodelPackage.STUDENT__UNI:
            setUni((University)newValue);
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
         case studyrightgenmodelPackage.STUDENT__STUD_ID:
            setStudId(STUD_ID_EDEFAULT);
            return;
         case studyrightgenmodelPackage.STUDENT__UNI:
            setUni((University)null);
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
         case studyrightgenmodelPackage.STUDENT__STUD_ID:
            return STUD_ID_EDEFAULT == null ? studId != null : !STUD_ID_EDEFAULT.equals(studId);
         case studyrightgenmodelPackage.STUDENT__UNI:
            return uni != null;
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
      result.append(" (studId: ");
      result.append(studId);
      result.append(')');
      return result.toString();
   }

} //StudentImpl
