package org.sdmlib.models.classes.logic;

import org.sdmlib.models.classes.Annotation;
import org.sdmlib.models.classes.Association;
import org.sdmlib.models.classes.Attribute;
import org.sdmlib.models.classes.ClassModel;
import org.sdmlib.models.classes.Clazz;
import org.sdmlib.models.classes.Enumeration;
import org.sdmlib.models.classes.Method;
import org.sdmlib.models.classes.Role;
import org.sdmlib.models.classes.logic.GenClassModel.DIFF;
import org.sdmlib.models.objects.GenericObject;

public interface ClassModelAdapter {
	public boolean generate(String rootDir);

	public Clazz getOrCreateClazz(String className);

	public boolean removeFromAssociations(Association association);

	public boolean addToAssociations(Association association);

	public void setModel(ClassModel object);
	
	public void insertModelCreationCodeHere(String rootDir, String className, String newMethod);

	public Clazz findClass(String partnerClassName);
	
	public void removeAllGeneratedCode(String rootDir, String srcDir, String helpersDir);

	public DIFF getShowDiff();

	public void removeAllCodeForClass(String srcDir, String helpersDir, Clazz clazz);
	
	public void turnRemoveCallToComment(String rootDir);
	
	public void removeFromModelAndCode(Clazz model, String rootDir);
	
	public ClassModel learnFromGenericObjects(String packageName, GenericObject root);
	
	public void updateFromCode(String includePathes, String packages);
	
	public void insertModelCreationCodeHere(String rootDir);
	
	public ClassModelAdapter withShowDiff(DIFF showDiff);
	
	// MUST BE FIXED
	public GenEnumeration getOrCreate(Enumeration enumeration);

	public GenClass getOrCreate(Clazz clazz);
	
	public GenClass getClazz(String name);

	public GenMethod getOrCreate(Method method);

	public GenAttribute getOrCreate(Attribute attribute);

	public GenAnnotation getOrCreate(Annotation annotation);

	public GenAssociation getOrCreate(Association association);

	public GenRole getOrCreate(Role role);
}
