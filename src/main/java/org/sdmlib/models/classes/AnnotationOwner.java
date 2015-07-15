package org.sdmlib.models.classes;

import org.sdmlib.models.classes.util.AnnotationSet;

public interface AnnotationOwner {
	   public AnnotationSet getAnnotations();
	   public AnnotationOwner withAnnotation(Annotation... value);
	   public AnnotationOwner withoutAnnotation(Annotation... value);
	   public Annotation createAnnotations();
	   public String getName();
}
