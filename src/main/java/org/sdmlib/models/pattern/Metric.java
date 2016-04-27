package org.sdmlib.models.pattern;

@FunctionalInterface
public interface Metric
{
   public double compute(Object graphRoot);
}
