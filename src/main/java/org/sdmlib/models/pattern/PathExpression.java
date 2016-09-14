package org.sdmlib.models.pattern;

import java.util.Collection;

@FunctionalInterface
public interface PathExpression
{
   public Collection<?> getNeighbors(Object src);
}
