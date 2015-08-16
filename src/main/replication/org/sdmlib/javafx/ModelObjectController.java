package org.sdmlib.javafx;

import org.sdmlib.serialization.PropertyChangeInterface;

import javafx.scene.Node;

public abstract class ModelObjectController
{

   public abstract Node getNode();
   
   public abstract Node init(PropertyChangeInterface listElem);

}
