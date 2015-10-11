/*
   Copyright (c) 2014 NeTH 
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software 
   and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, 
   sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is 
   furnished to do so, subject to the following conditions: 
   
   The above copyright notice and this permission notice shall be included in all copies or 
   substantial portions of the Software. 
   
   The Software shall be used for Good, not Evil. 
   
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING 
   BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND 
   NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, 
   DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, 
   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package org.sdmlib.models.classes;

import java.util.ArrayList;
import java.util.Arrays;

import org.sdmlib.models.classes.util.ArrayListSet;
import org.sdmlib.models.classes.util.EnumerationSet;
import org.sdmlib.models.classes.util.MethodSet;
   /**
    * 
    * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
*/
   public class Enumeration extends SDMLibClass {

	// ==========================================================================

	@Override
	public void removeYou() {
		super.removeYou();

		setClassModel(null);
		withoutMethods(this.getMethods().toArray(
				new Method[this.getMethods().size()]));
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

	// ==========================================================================

	public static final String PROPERTY_VALUENAMES = "valueNames";

	private ArrayListSet valueNames = new ArrayListSet();

	public ArrayListSet getValueNames() {
		return this.valueNames;
	}

	public void setValueNames(ArrayListSet value) {
		if (this.valueNames != value) {
			ArrayListSet oldValue = this.valueNames;
			this.valueNames = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_VALUENAMES,
					oldValue, value);
		}
	}

	public Enumeration withValueNames(String... values) {
		ArrayList<String> list = new ArrayList<String>(Arrays.asList(values));
		ArrayListSet listSet = new ArrayListSet();
		listSet.add(list);
		setValueNames(listSet);
		return this;
	}

	public Enumeration withValueNames(ArrayListSet value) {
		setValueNames(value);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(" ").append(this.getName());
		return result.substring(1);
	}

	public static final EnumerationSet EMPTY_SET = new EnumerationSet()
			.withReadOnly(true);

	/********************************************************************
	 * <pre>
	 *              many                       one
	 * Enumeration ----------------------------------- ClassModel
	 *              enumerations                   classModel
	 * </pre>
	 */

	public static final String PROPERTY_CLASSMODEL = "classModel";

	private ClassModel classModel = null;

	public ClassModel getClassModel() {
		return this.classModel;
	}

	public boolean setClassModel(ClassModel value) {
		boolean changed = false;

		if (this.classModel != value) {
			ClassModel oldValue = this.classModel;

			if (this.classModel != null) {
				this.classModel = null;
				oldValue.withoutEnumerations(this);
			}

			this.classModel = value;

			if (value != null) {
				value.withEnumerations(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSMODEL,
					oldValue, value);
			changed = true;
		}

		return changed;
	}

	public Enumeration withClassModel(ClassModel value) {
		setClassModel(value);
		return this;
	}

	public ClassModel createClassModel() {
		ClassModel value = new ClassModel();
		withClassModel(value);
		return value;
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * Enumeration ----------------------------------- Method
	 *              enumeration                   methods
	 * </pre>
	 */

	public static final String PROPERTY_METHODS = "methods";

	private MethodSet methods = null;

	public MethodSet getMethods() {
		if (this.methods == null) {
			return Method.EMPTY_SET;
		}

		return this.methods;
	}
	
	public Enumeration withMethod(String name) {
		return with(new Method(name));
	}
	
	public Enumeration withMethod(String name, DataType returnType) {
		return with(new Method(name, returnType, new Parameter[0]));
	}

	public Enumeration withMethod(String name, DataType returnType,
			Parameter... parameters) {
		return with(new Method(name, returnType, parameters));
	}

	private Enumeration with(Method... value) {
		if (value == null) {
			return this;
		}
		for (Method item : value) {
			if (item != null) {
				if (this.methods == null) {
					this.methods = new MethodSet();
				}

				boolean changed = this.methods.add(item);

				if (changed) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_METHODS, null, item);
				}
			}
		}
		return this;
	}

	public Enumeration withMethods(Method... value) {
		return with(value);
	}

	public Enumeration withoutMethods(Method... value) {
		for (Method item : value) {
			if ((this.methods != null) && (item != null)) {
				if (this.methods.remove(item)) {
					getPropertyChangeSupport().firePropertyChange(
							PROPERTY_METHODS, item, null);
				}
			}
		}
		return this;
	}

	public Method createMethods() {
		Method value = new Method();
		withMethods(value);
		return value;
	}

	@Override
	public SDMLibClass withName(String value) {
		setName(value);
		return this;
	}

	// ==========================================================================
	public static final String PROPERTY_EXTERNAL = "external";

	private boolean external;

	public boolean isExternal() {
		return this.external;
	}

	public boolean setExternal(boolean value) {
		if (this.external != value) {
			boolean oldValue = this.external;
			this.external = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_EXTERNAL,
					oldValue, value);
			return true;
		}
		return false;
	}

	public Enumeration withExternal(boolean value) {
		setExternal(value);
		return this;
	}
	
	   public String getFullName()
	   {
	      
	      if(name.indexOf('.') < 0 && getClassModel()!= null && getClassModel().getName() != null)
	      {
	         return getClassModel().getName()  + "." + name.replace("$", ".");
	      }
	      return name.replace("$", ".");
	   }
}
