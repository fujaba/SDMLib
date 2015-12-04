/*
   Copyright (c) 2012 Albert zuendorf

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

import org.sdmlib.models.classes.logic.ClassModelAdapter;
import org.sdmlib.models.classes.util.AnnotationSet;
import org.sdmlib.models.classes.util.AttributeSet;
import org.sdmlib.models.classes.util.ClazzSet;
import org.sdmlib.models.classes.util.MethodSet;
import org.sdmlib.models.classes.util.ModifierSet;
import org.sdmlib.models.classes.util.RoleSet;

/**
 * @author Stefan
 *
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/ClassModelTest.java'>ClassModelTest.java</a>
*/
public class Clazz extends SDMLibClass implements AnnotationOwner, Type {
	public static final String PROPERTY_ATTRIBUTES = "attributes";
	public static final String PROPERTY_CLASSMODEL = "classModel";
	public static final String PROPERTY_SUPERCLAZZES = "superClazzes";
	public static final String PROPERTY_KIDCLAZZES = "kidClazzes";

	public static final String PROPERTY_METHODS = "methods";
	public static final String PROPERTY_ROLES = "roles";

	public static final String PROPERTY_INTERFACE = "interface";
	public static final String PROPERTY_EXTERNAL = "external";
	public static final String PROPERTY_MODIFIER = "modifier";
	public static final ClazzSet EMPTY_SET = new ClazzSet().withReadOnly(true);

	private AttributeSet attributes = null;
	private ClassModel classModel = null;
	private ClazzSet superClazzes = null;
	private ClazzSet kidClazzes = null;
	private ModifierSet modifiers = null;
	private ArrayList<String> imports = new ArrayList<String>();

	private MethodSet methods = null;
	private RoleSet roles = null;

	private boolean interfaze = false;
	private boolean external;

	Clazz(String name) 
	{
		setName(name);
	}

	public Clazz withAssoc(Clazz tgtClass, String tgtRoleName, Card tgtCard, String srcRoleName, Card srcCard) {
		Association assoc = new Association().withTarget(tgtClass, tgtRoleName, tgtCard).withSource(this, srcRoleName,
				srcCard);

		if (this.getClassModel() != null && this.getClassModel().getGenerator() != null) {
			this.getClassModel().getGenerator().addToAssociations(assoc);
		}

		return this;
	}

	public Clazz withAssoc(Clazz tgtClass, String tgtRoleName, Card tgtCard) {
		Association assoc = new Association().withTarget(tgtClass, tgtRoleName, tgtCard).withSource(this, null, null);

		if (this.getClassModel() != null && this.getClassModel().getGenerator() != null) {
			this.getClassModel().getGenerator().addToAssociations(assoc);
		}

		return this;
	}

	public String getFullName() {

		if (name.indexOf('.') < 0 && getClassModel() != null && getClassModel().getName() != null) {
			return getClassModel().getName() + "." + name.replace("$", ".");
		}
		return name.replace("$", ".");
	}

	@Override
	public Clazz withName(String name) {
		setName(name);
		return this;
	}

	public Clazz getSuperClass() {
		if (superClazzes == null) {
			return null;
		}
		for (Clazz clazz : superClazzes) {
			if (!clazz.isInterface()) {
				return clazz;
			}
		}
		return null;
	}

	public ClazzSet getInterfaces() {
		ClazzSet interfaces = new ClazzSet();
		if (superClazzes == null) {
			return interfaces;
		}
		for (Clazz clazz : superClazzes) {
			if (clazz.isInterface()) {
				interfaces.add(clazz);
			}
		}
		return interfaces;
	}

	public ClazzSet getSuperClazzes() {
		if (this.superClazzes == null) {
			return Clazz.EMPTY_SET;
		}

		return this.superClazzes;
	}

	public Clazz withSuperClazz(Clazz... value) {
		if (value == null) {
			return this;
		}
		if (this.superClazzes == null) {
			this.superClazzes = new ClazzSet();
		}
		for (Clazz item : value) {
			if (item != null) {
				if (this.superClazzes.add(item)) {
					item.withKidClazzes(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_SUPERCLAZZES, null, item);
				}
			}
		}
		return this;
	}

	public ClazzSet getKidClazzes() {
		if (this.kidClazzes == null) {
			return Clazz.EMPTY_SET;
		}

		return this.kidClazzes;
	}

	public ClazzSet getKidClazzesTransitive() {
		ClazzSet result = new ClazzSet().with(this);
		return result.getKidClazzesTransitive();
	}

	public Clazz withKidClazzes(Clazz... value) {
		if (value == null) {
			return this;
		}
		if (this.kidClazzes == null) {
			this.kidClazzes = new ClazzSet();
		}
		for (Clazz item : value) {
			if (item != null) {
				if (this.kidClazzes.add(item)) {
					item.withSuperClazz(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDCLAZZES, null, item);
				}
			}
		}
		return this;
	}

	public ClassModel getClassModel() {
		return this.classModel;
	}

	public boolean setClassModel(ClassModel value) {
		boolean changed = false;

		if (this.classModel != value) {
			ClassModel oldValue = this.classModel;

			if (this.classModel != null) {
				this.classModel = null;
				oldValue.without(this);
			}

			this.classModel = value;

			if (value != null) {
				value.with(this);
			}

			getPropertyChangeSupport().firePropertyChange(PROPERTY_CLASSMODEL, oldValue, value);
			changed = true;
		}

		return changed;
	}

	public Clazz with(ClassModel value) {
		setClassModel(value);
		return this;
	}

	/**
	 * get All Attributes
	 * 
	 * @return all Attributes of a Clazz
	 *         /****************************************************************
	 *         ****
	 * 
	 *         <pre>
	 *              one                       many
	 * Clazz ----------------------------------- Attribute
	 *              clazz                   attributes
	 *         </pre>
	 */
	public AttributeSet getAttributes() {
		if (this.attributes == null) {
			return Attribute.EMPTY_SET;
		}

		return this.attributes;
	}

	/**
	 * get All Roles
	 * 
	 * @return all Roles of a Clazz
	 *         /****************************************************************
	 *         ****
	 * 
	 *         <pre>
	 *              one                       many
	 * Clazz ----------------------------------- Role
	 *              clazz                   sourceRoles
	 *         </pre>
	 */
	public RoleSet getRoles() {
		if (this.roles == null) {
			return Role.EMPTY_SET;
		}

		return this.roles;
	}

	public Clazz with(Attribute... value) {
		if (value == null) {
			return this;
		}
		if (this.attributes == null) {
			this.attributes = new AttributeSet();
		}
		// TODO MUST be check if the Attribute (Name exist)
		for (Attribute item : value) {
			if (item != null) {
				if (this.attributes.add(item)) {
					item.with(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRIBUTES, null, item);
				}
			}
		}
		return this;
	}

	/**
	 * get All Methods
	 * 
	 * @return all Methods of a Clazz
	 *         /****************************************************************
	 *         ****
	 * 
	 *         <pre>
	 *              one                       many
	 * Clazz ----------------------------------- Method
	 *              clazz                   methods
	 *         </pre>
	 */
	public MethodSet getMethods() {
		if (this.methods == null) {
			return Method.EMPTY_SET;
		}

		return this.methods;
	}

	public Clazz withMethod(String name) {
		return with(new Method(name));
	}

	public Clazz withMethod(String name, Type returnType, Parameter... parameters) {
		return with(new Method(name, returnType, parameters));
	}

	/**
	 * Add an attribute to a class. On code generation this creates a Java field in the corresponding class and 
	 * a get-method and a set-method and a with-method for this field. 
	 * 
	 * <pre>
    *    ClassModel model = new ClassModel("org.sdmlib.test.examples.groupaccount.model");
    * 
    *    Clazz groupAccountClass = model.createClazz("GroupAccount")
    *       .withAttribute("task", DataType.STRING);
    *                
    *    groupAccountClass.createMethod("getTaskNames")
    *       .with(new Parameter(DataType.DOUBLE))
    *       .with(new Parameter(DataType.STRING))
    *       .withReturnType(DataType.DOUBLE);
    *       
    *    Clazz personClass = model.createClazz("Person")
    *       .withAttribute("name", DataType.STRING)
    *       .withAttribute("balance", DataType.DOUBLE);
    *  
    *    groupAccountClass.withAssoc(personClass, "persons", Card.MANY, "parent", Card.ONE);
    * 
    *    model.generate("src/test/java");
    *    
    *    model.dumpClassDiagram("GroupAccountClassDiag");
    * </pre>
    * 
	 * @param name of the attribute
	 * @param type of the attribute
	 * @return the clazz for fluent style, e.g. add another attribute
	 * 
	 * @see ClassModel#generate()
	 */
	public Clazz withAttribute(String name, Type type) {
		return this.with(new Attribute(name, type));
	}

	public Clazz withAttribute(String name, DataType type, String initialization) {
		this.with(new Attribute(name, type).withInitialization(initialization));
		return this;
	}

	// ==========================================================================
	@Override
	public void removeYou() {
		super.removeYou();
		setClassModel(null);
		without(this.getAttributes().toArray(new Attribute[this.getAttributes().size()]));
		without(this.getMethods().toArray(new Method[this.getMethods().size()]));
		without(this.getRoles().toArray(new Role[this.getRoles().size()]));
		withoutKidClazz(this.getKidClazzes().toArray(new Clazz[this.getKidClazzes().size()]));
		withoutSuperClazz(this.getSuperClazzes().toArray(new Clazz[this.getSuperClazzes().size()]));
		withoutKidClazzes(this.getKidClazzes().toArray(new Clazz[this.getKidClazzes().size()]));
		withoutSuperClazzes(this.getSuperClazzes().toArray(new Clazz[this.getSuperClazzes().size()]));
		withoutAttributes(this.getAttributes().toArray(new Attribute[this.getAttributes().size()]));
		withoutMethods(this.getMethods().toArray(new Method[this.getMethods().size()]));
		withoutRoles(this.getRoles().toArray(new Role[this.getRoles().size()]));
		withoutAnnotation(this.getAnnotations().toArray(new Annotation[this.getAnnotations().size()]));
		getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
	}

	public boolean isWithNoObjects() {
		return this.hasModifier(Modifier.ABSTRACT) || this.isInterface();
	}

	// ==========================================================================
	public boolean isInterface() {
		return this.interfaze;
	}

	public boolean setInterface(boolean value) {
		if (this.interfaze != value) {
			boolean oldValue = this.interfaze;
			this.interfaze = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_INTERFACE, oldValue, value);
			return true;
		}
		return false;
	}

	public Attribute getOrCreateAttribute(String attrName, DataType attrType) {
		for (Attribute attrDecl : getAttributes()) {
			if (attrDecl.getName().equals(attrName)) {
				return attrDecl;
			}
		}
		return new Attribute(attrName, attrType).with(this);
	}

	// ==========================================================================
	public boolean isExternal() {
		return this.external;
	}

	public boolean setExternal(boolean value) {
		if (this.external != value) {
			boolean oldValue = this.external;
			this.external = value;
			getPropertyChangeSupport().firePropertyChange(PROPERTY_EXTERNAL, oldValue, value);
			return true;
		}
		return false;
	}

	public Clazz withExternal(boolean value) {
		setExternal(value);
		return this;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();

		result.append(" ").append(this.getName());
		return result.substring(1);
	}

	public Clazz withInterface(boolean value) {
		setInterface(value);
		return this;
	}

	// ==========================================================================

	public Clazz without(Attribute... value) {
		if (this.attributes == null || value == null) {
			return this;
		}
		for (Attribute item : value) {
			if (item != null) {
				if (this.attributes.remove(item)) {
					item.setClazz(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRIBUTES, item, null);
				}
			}
		}
		return this;
	}

	public Clazz with(Method... value) {
		if (value == null) {
			return this;
		}
		if (this.methods == null) {
			this.methods = new MethodSet();
		}
		for (Method item : value) {
			if (item != null) {
				if (this.methods.add(item)) {
					item.with(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_METHODS, null, item);
				}
			}
		}
		return this;
	}

	public Clazz without(Method... value) {
		if (this.methods == null || value == null) {
			return this;
		}
		for (Method item : value) {
			if (item != null) {
				if (this.methods.remove(item)) {
					item.setClazz(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_METHODS, item, null);
				}
			}
		}
		return this;
	}

	public Clazz with(Role... value) {
		if (value == null) {
			return this;
		}
		for (Role item : value) {
			if (item != null) {
				if (this.roles == null) {
					this.roles = new RoleSet();
				}
				if (this.roles.add(item)) {
					item.with(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_ROLES, null, value);
				}
			}
		}
		return this;
	}

	public Clazz without(Role... value) {
		if (this.roles == null || value == null) {
			return this;
		}
		for (Role item : value) {
			if (item != null) {
				if (this.roles.remove(item)) {
					item.setClazz(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_ROLES, item, null);
				}
			}
		}
		return this;
	}

	public ClazzSet getSuperClassTransitive() {
		ClazzSet result = new ClazzSet().with(this);
		return result.getSuperClassTransitive();
	}

	public ClazzSet getInterfacesTransitive() {
		ClazzSet result = new ClazzSet().with(this);
		return result.getInterfacesTransitive();
	}

	public Method createMethod(String name, Parameter... parameters) {
		return new Method().withName(name).with(parameters).with(this);
	}

	public Method createMethod(String name, DataType returnValue, Parameter... parameters) {
		return new Method().withName(name).with(parameters).withReturnType(returnValue).with(this);
	}

	public Attribute createAttribute(String name, DataType type) {
		Attribute attribute = new Attribute(name, type);
		with(attribute);
		return attribute;
	}

	Method createMethod() {
		Method value = new Method();
		with(value);
		return value;
	}

	public Clazz withoutKidClazz(Clazz... value) {
		if (this.kidClazzes == null || value == null) {
			return this;
		}
		for (Clazz item : value) {
			if (item != null) {
				if (this.kidClazzes.remove(item)) {
					item.withoutSuperClazz(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDCLAZZES, item, null);
				}
			}
		}
		return this;
	}

	public Clazz createKidClazz(String name) {
		Clazz value = new Clazz(name);
		withKidClazzes(value);
		return value;
	}

	Clazz createKidClazz() {
		Clazz value = new Clazz(null);
		withKidClazzes(value);
		return value;
	}

	public ClazzSet getSuperClazzesTransitive() {
		ClazzSet result = new ClazzSet().with(this);
		return result.getSuperClazzesTransitive();
	}

	public Clazz withoutSuperClazz(Clazz... value) {
		if (this.superClazzes == null || value == null) {
			return this;
		}
		for (Clazz item : value) {
			if (item != null) {
				if (this.superClazzes.remove(item)) {
					item.withoutKidClazz(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_SUPERCLAZZES, item, null);
				}
			}
		}
		return this;
	}

	Clazz createSuperClazz() {
		Clazz value = new Clazz(null);
		withSuperClazz(value);
		return value;
	}

	public Clazz withClassModel(ClassModel value) {
		setClassModel(value);
		return this;
	}

	ClassModel createClassModel() {
		ClassModel value = new ClassModel();
		withClassModel(value);
		return value;
	}

	boolean addToKidClazzes(Clazz value) {
		boolean changed = false;

		if (value != null) {
			if (this.kidClazzes == null) {
				this.kidClazzes = new ClazzSet();
			}

			changed = this.kidClazzes.add(value);

			if (changed) {
				value.withSuperClazzes(this);
				getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDCLAZZES, null, value);
			}
		}

		return changed;
	}

	Clazz withoutKidClazzes(Clazz... value) {
		for (Clazz item : value) {
			if ((this.kidClazzes != null) && (item != null)) {
				if (this.kidClazzes.remove(item)) {
					item.withoutSuperClazzes(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_KIDCLAZZES, item, null);
				}
			}
		}
		return this;
	}

	Clazz createKidClazzes() {
		Clazz value = new Clazz(null);
		withKidClazzes(value);
		return value;
	}

	Clazz withSuperClazzes(Clazz... value) {
		return withSuperClazz(value);
	}

	Clazz withoutSuperClazzes(Clazz... value) {
		return withoutSuperClazz(value);
	}

	Clazz createSuperClazzes() {
		Clazz value = new Clazz(null);
		withSuperClazzes(value);
		return value;
	}

	Clazz withAttributes(Attribute... value) {
		return with(value);
	}

	Clazz withoutAttributes(Attribute... value) {
		return without(value);
	}

	Attribute createAttributes() {
		Attribute value = new Attribute(null, null);
		withAttributes(value);
		return value;
	}

	Clazz withMethods(Method... value) {
		return with(value);
	}

	Clazz withoutMethods(Method... value) {
		return without(value);
	}

	Method createMethods() {
		Method value = new Method();
		withMethods(value);
		return value;
	}

	Clazz withRoles(Role... value) {
		return with(value);
	}

	Clazz withoutRoles(Role... value) {
		return without(value);
	}

	Role createRoles() {
		Role value = new Role();
		withRoles(value);
		return value;
	}

	// ==========================================================================

	public Clazz withImport(String value) {
		this.imports.add(value);
		return this;
	}

	public ArrayList<String> getImports() {
		return imports;
	}

	public boolean isEnumeration() {
		for (Enumeration enumeration : getClassModel().getEnumerations()) {
			String enumName = enumeration.getFullName();
			String clazzName = this.getFullName();
			if (enumName.equals(clazzName))
				return true;
		}
		return false;
	}

	public boolean hasFeature(Feature feature) {
		return getClassModel().hasFeature(feature, this);
	}

	/********************************************************************
	 * <pre>
	 *              one                       many
	 * Clazz ----------------------------------- Annotation
	 *              clazz                   annotations
	 * </pre>
	 */

	public static final String PROPERTY_ANNOTATIONS = "annotations";

	private AnnotationSet annotations = null;

	public AnnotationSet getAnnotations() {
		if (this.annotations == null) {
			return AnnotationSet.EMPTY_SET;
		}

		return this.annotations;
	}

	public Clazz withAnnotation(Annotation... value) {
		if (value == null) {
			return this;
		}
		for (Annotation item : value) {
			if (item != null) {
				if (this.annotations == null) {
					this.annotations = new AnnotationSet();
				}

				boolean changed = this.annotations.add(item);

				if (changed) {
					item.withOwner(this);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_ANNOTATIONS, null, item);
				}
			}
		}
		return this;
	}

	public Clazz withoutAnnotation(Annotation... value) {
		for (Annotation item : value) {
			if ((this.annotations != null) && (item != null)) {
				if (this.annotations.remove(item)) {
					item.setOwner(null);
					getPropertyChangeSupport().firePropertyChange(PROPERTY_ANNOTATIONS, item, null);
				}
			}
		}
		return this;
	}

	public Annotation createAnnotations() {
		Annotation value = new Annotation();
		withAnnotation(value);
		return value;
	}

	public Clazz withModifier(Modifier... value) {
		if (value == null) {
			return this;
		}
		if (this.modifiers == null) {
			this.modifiers = new ModifierSet();
		}
		for (Modifier item : value) {
			if (item != null) {
				if (this.modifiers.add(item)) {
					getPropertyChangeSupport().firePropertyChange(PROPERTY_SUPERCLAZZES, null, item);
				}
			}
		}
		return this;
	}
	
	public boolean hasModifier(Modifier value) {
		if (value == null) {
			return true;
		}
		if (this.modifiers == null) {
			return false;
		}
		for (Modifier item : modifiers) {
			if(value.getValue().equals(item.getValue())) {
				return true;
			}
		}
		return false;
	}

	public ModifierSet getModifiers() {
		return modifiers;
	}

	/**
	 * Removes this class from the current model and deletes
	 * the generated code from the model and util classes.<br> 
	 * This includes the set, creator and pattern object classes, that are associated with this class.
	 * 
	 * 
	 * @param rootDir root directory, where the code of the class is located
	 */
	public void removeFromModelAndCode(String rootDir) {
		ClassModelAdapter genModel = this.getClassModel().getGenerator();
		genModel.removeFromModelAndCode(this, rootDir);
		this.removeYou();
	}
}
