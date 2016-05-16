/*
   Copyright (c) 2012 zuendorf 

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

package org.sdmlib.models.pattern;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;

import org.sdmlib.CGUtil;
import org.sdmlib.models.SDMLibIdMap;
import org.sdmlib.models.pattern.util.AttributeConstraintSet;
import org.sdmlib.models.pattern.util.CardinalityConstraintSet;
import org.sdmlib.models.pattern.util.MatchOtherThanSet;
import org.sdmlib.models.pattern.util.PatternLinkSet;
import org.sdmlib.serialization.EntityFactory;
import org.sdmlib.storyboards.Kanban;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.Condition;
import de.uniks.networkparser.interfaces.SendableEntityCreator;
import org.sdmlib.models.pattern.CardinalityConstraint;
import org.sdmlib.models.pattern.MatchOtherThan;

/**
 * 
 * @see <a href='../../../../../../../src/test/java/org/sdmlib/test/examples/SDMLib/PatternModelCodeGen.java'>PatternModelCodeGen.java</a>
 */
public class PatternObject<POC, MC> extends PatternElement<POC>
{
   public static final String PROPERTY_ATTRCONSTRAINTS = "attrConstraints";
   public static final String PROPERTY_CANDIDATES = "candidates";
   public static final String PROPERTY_CARDCONSTRAINTS = "cardConstraints";
   public static final String PROPERTY_CURRENTMATCH = "currentMatch";
   public static final String PROPERTY_DESTROYELEM = "destroyElem";
   public static final String PROPERTY_EXCLUDERS = "excluders";
   public static final String PROPERTY_INCOMING = "incoming";
   public static final String PROPERTY_MATCHOTHERTHAN = "matchOtherThan";
   public static final String PROPERTY_OUTGOING = "outgoing";

   private PatternLinkSet outgoing = null;
   private MatchOtherThanSet matchOtherThan = null;
   private PatternLinkSet incoming = null;
   private MatchOtherThanSet excluders = null;
   private DestroyObjectElem destroyElem = null;
   private Object currentMatch;
   private CardinalityConstraintSet cardConstraints = null;
   private Object candidates;

   private AttributeConstraintSet attrConstraints = null;

   protected void newInstance(IdMap map){
      Pattern<Object> pattern = new Pattern<Object>(new SDMLibIdMap("p"));
      pattern.addToElements(this);
   }

   protected void newInstance(IdMap map, Object[] hostGraphObject){
      Pattern<Object> pattern = new Pattern<Object>(new SDMLibIdMap("p"));
      pattern.addToElements(this);
      if(hostGraphObject.length>1){
         this.withCandidates(Arrays.asList(hostGraphObject));
      } else {
         this.withCurrentMatch(hostGraphObject[0]);
         this.withModifier(Pattern.BOUND);
      }
      pattern.findMatch();
   }


   public <POSC extends PatternObject> POSC instanceOf(POSC subclassPO)
   {
      // add a pattern link that checks the type of the source object and the
      // target object passed as subclassPO
      this.hasLink("instanceof", subclassPO);
      return subclassPO;
   }

   public boolean rebind(MC o)
   {
      return this.getPattern().rebind(this, o);
   }

   public POC nextMatch()
   {
      this.getPattern().findNextMatch();

      return (POC) this;
   }

   private boolean matchAsSet = false;

   private Iterator<Object> candidatesIterator;

   public POC matchAsSet()
   {
      this.matchAsSet = true;

      // reconstruct set of matches from candidates and current target
      this.setCurrentMatch(this.getCandidates());
      this.setCandidates(null);

      return (POC) this;
   }

   @Override
   public boolean findNextMatch()
   {
      if (Pattern.CREATE.equals(getModifier()))
      {
         if (!this.getPattern().getHasMatch())
         {
            return false;
         }

         if (this.getHasMatch())
         {
            // backward execution, backtrack and prepare for forward execution
            this.setHasMatch(false);
            return false;
         }
         else
         {
            // forward execution: create hostgraph object and bind it
            String className = this.getClass().getName();
            className = className.replace(".util.", ".");
            className = className.substring(0, className.length() - 2);
            SendableEntityCreator creatorClass = this.getPattern().getIdMap().getCreator(className, true);
            if (creatorClass == null)
            {
               className = CGUtil.packageName(className) + ".impl." + CGUtil.shortClassName(className) + "Impl";
               creatorClass = this.getPattern().getIdMap().getCreator(className, true);
            }
            Object sendableInstance = creatorClass.getSendableInstance(false);
            this.setCurrentMatch(sendableInstance);
            this.setHasMatch(true);

            if (this.getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
            {
               String shortClassName = CGUtil.shortClassName(className);

               this.getTopPattern().addLogMsg(
                  "" + getLHSPatternObjectName() + " = new " + shortClassName + "(); // "
                        + getPattern().getIdMap().getId(sendableInstance));
            }

            return true;
         }
      }
      else if (Pattern.BOUND.equals(getModifier()))
      {
         if (!this.getPattern().getHasMatch())
         {
            return false;
         }

         if (this.getHasMatch())
         {
            // backward execution, backtrack and prepare for forward execution
            this.setHasMatch(false);
            return false;
         }
         else
         {
            this.setHasMatch(getCurrentMatch() != null);

            return this.getHasMatch();
         }
      }

      if (!this.getPattern().getHasMatch())
      {
         return false;
      }

      if (this.getCandidates() == null
            || this.getCandidates() instanceof Collection && ((Collection<?>) this.getCandidates()).isEmpty())
      {
         this.setHasMatch(false);
         return false;
      }

      boolean resultStat = false;
      if (this.getCandidates() instanceof Collection && !matchAsSet)
      {
         if (this.candidatesIterator == null)
         {
            this.candidatesIterator = ((Collection<Object>) this.getCandidates()).iterator();
         }

         if (this.candidatesIterator.hasNext())
         {
            Object obj = this.candidatesIterator.next();

            this.setCurrentMatch(obj);

            this.setHasMatch(true);

            resultStat = true;

            if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
            {
               String tgtVar = getLHSPatternObjectName();
               getTopPattern().addLogMsg(
                  tgtVar + " = " + getPatternObjectName() + "Candidates.removeFirst(); // "
                        + getTopPattern().getIdMap().getId(obj) + " " + obj + " <- "
                        + valueSetString(this.getCandidates()));
            }
         }
         else
         {
            // out of candidates, resut match
            this.setCurrentMatch(null);
            this.setHasMatch(false);
            resultStat = false;
         }
      }
      else
      {
         this.setCurrentMatch(this.getCandidates());
         this.setCandidates(null);
         this.setHasMatch(true);

         resultStat = true;
      }

      if (Pattern.DESTROY.equals(getModifier()) && this.getCurrentMatch() != null)
      {
         Object currentMatch = this.getCurrentMatch();

         EntityFactory creatorClass = (EntityFactory) this.getPattern().getIdMap().getCreatorClass(currentMatch);

         creatorClass.removeObject(currentMatch);
      }

      return resultStat;
   }

   public String getLHSPatternObjectName()
   {
      String lhsName = getPatternObjectName();
      if (getPattern() != null)
      {
         LinkedHashSet<String> variablesAlreadyInTrace = getTopPattern().getVariablesAlreadyInTrace();
         if (!variablesAlreadyInTrace.contains(lhsName))
         {
            variablesAlreadyInTrace.add(lhsName);
            lhsName = this.getCurrentMatch().getClass().getSimpleName() + " " + lhsName;
         }
      }

      return lhsName;
   }

   @Override
   public void resetSearch()
   {
      if (!Pattern.BOUND.equals(getModifier()))
      {
         this.setCandidates(null);
         this.setCurrentMatch(null);
      }
      this.candidatesIterator = null;
      this.setHasMatch(false);
   }

   public POC startCreate()
   {
      this.getPattern().startCreate();

      return (POC) this;
   }

   public POC endCreate()
   {
      this.getPattern().endCreate();

      return (POC) this;
   }

   public POC startDestroy()
   {
      this.getPattern().startDestroy();

      return (POC) this;
   }

   public POC endDestroy()
   {
      this.getPattern().endCreate();

      return (POC) this;
   }

   public POC startNAC()
   {
      NegativeApplicationCondition nac = new NegativeApplicationCondition();

      this.getPattern().addToElements(nac);

      if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
      {
         nac.setPatternObjectName("n" + getTopPattern().getPatternObjectCount());

      }

      return (POC) this;
   }

   public POC endNAC()
   {
      Pattern directPattern = this.getPattern();

      while (directPattern.getCurrentSubPattern() != null)
      {
         directPattern = directPattern.getCurrentSubPattern();
      }

      if (directPattern instanceof NegativeApplicationCondition)
      {
         directPattern = directPattern.getPattern();
      }

      directPattern.setCurrentSubPattern(null);

      directPattern.findMatch();

      return (POC) this;
   }

   public POC startSubPattern()
   {
      OptionalSubPattern optionalSubPattern = new OptionalSubPattern();

      this.getPattern().addToElements(optionalSubPattern);

      if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
      {
         optionalSubPattern.setPatternObjectName("o" + getTopPattern().getPatternObjectCount());

         getTopPattern().addLogMsg("// start subpattern " + optionalSubPattern.getPatternObjectName());
      }

      return (POC) this;
   }

   public POC endSubPattern()
   {
      Pattern directPattern = this.getPattern();

      while (directPattern.getCurrentSubPattern() != null)
      {
         directPattern = directPattern.getCurrentSubPattern();
      }

      if (directPattern instanceof OptionalSubPattern)
      {
         directPattern = directPattern.getPattern();
      }

      directPattern.setCurrentSubPattern(null);

      return (POC) this;
   }

   public POC doAllMatches()
   {
      this.getPattern().setDoAllMatches(true);
      this.setDoAllMatches(true);

      while (this.getPattern().getHasMatch())
      {
         if (getTopPattern().getDebugMode() >= Kanban.DEBUG_ON)
         {
            getTopPattern().addLogMsg("// " + getPattern().getPatternObjectName() + " allMatches?");
         }

         this.getPattern().findMatch();
      }

      return (POC) this;
   }

   public POC destroy()
   {
      DestroyObjectElem destroyObjectElem = (DestroyObjectElem) new DestroyObjectElem()
            .withPatternObject(this)
            .withPattern(this.getPattern());

      this.getPattern()
      .findMatch();

      return (POC) this;
   }


   // ==========================================================================

   public void removeYou()
   {
      super.removeYou();

      removeAllFromIncoming();
      removeAllFromOutgoing();
      removeAllFromAttrConstraints();
      setDestroyElem(null);
      setPattern(null);
      removeAllFromCardConstraints();
      removeAllFromMatchOtherThan();
      removeAllFromExcluders();
      withoutAttrConstraints(this.getAttrConstraints().toArray(new AttributeConstraint[this.getAttrConstraints().size()]));
      withoutCardConstraints(this.getCardConstraints().toArray(new CardinalityConstraint[this.getCardConstraints().size()]));
      withoutMatchOtherThan(this.getMatchOtherThan().toArray(new MatchOtherThan[this.getMatchOtherThan().size()]));
      withoutExcluders(this.getExcluders().toArray(new MatchOtherThan[this.getExcluders().size()]));
      getPropertyChangeSupport().firePropertyChange("REMOVE_YOU", this, null);
   }

   // ==========================================================================
   public MC getCurrentMatch()
   {
      return (MC) this.currentMatch;
   }

   public void setCurrentMatch(Object value)
   {
      if (this.currentMatch != value)
      {
         Object oldValue = this.currentMatch;
         this.currentMatch = value;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CURRENTMATCH, oldValue, value);
      }
   }

   public PatternObject withCurrentMatch(Object value)
   {
      setCurrentMatch(value);
      return this;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * PatternObject ----------------------------------- PatternLink
    *              tgt                   incoming
    * </pre>
    * @return The PatternObject
    */
   public PatternLinkSet getIncoming()
   {
      if (this.incoming == null)
      {
         return PatternLink.EMPTY_SET;
      }

      return this.incoming;
   }

   public boolean addToIncoming(PatternLink value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.incoming == null)
         {
            this.incoming = new PatternLinkSet();
         }

         changed = this.incoming.add(value);

         if (changed)
         {
            value.withTgt(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INCOMING, null, value);
         }
      }

      return changed;
   }

   public boolean removeFromIncoming(PatternLink value)
   {
      boolean changed = false;

      if ((this.incoming != null) && (value != null))
      {
         changed = this.incoming.remove(value);

         if (changed)
         {
            value.setTgt(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_INCOMING, value, null);
         }
      }

      return changed;
   }

   public PatternObject withIncoming(PatternLink value)
   {
      addToIncoming(value);
      return this;
   }

   public PatternObject withoutIncoming(PatternLink value)
   {
      removeFromIncoming(value);
      return this;
   }

   public void removeAllFromIncoming()
   {
      LinkedHashSet<PatternLink> tmpSet = new LinkedHashSet<PatternLink>(this.getIncoming());

      for (PatternLink value : tmpSet)
      {
         this.removeFromIncoming(value);
      }
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * PatternObject ----------------------------------- PatternLink
    *              src                   outgoing
    * </pre>
    * @return The PatternLinkSet
    */
   public PatternLinkSet getOutgoing()
   {
      if (this.outgoing == null)
      {
         return PatternLink.EMPTY_SET;
      }

      return this.outgoing;
   }

   public boolean addToOutgoing(PatternLink value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.outgoing == null)
         {
            this.outgoing = new PatternLinkSet();
         }

         changed = this.outgoing.add(value);

         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTGOING, null, value);
         }
      }

      return changed;
   }

   public boolean removeFromOutgoing(PatternLink value)
   {
      boolean changed = false;

      if ((this.outgoing != null) && (value != null))
      {
         changed = this.outgoing.remove(value);

         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_OUTGOING, value, null);
         }
      }

      return changed;
   }

   public PatternObject withOutgoing(PatternLink value)
   {
      addToOutgoing(value);
      return this;
   }

   public PatternObject withoutOutgoing(PatternLink value)
   {
      removeFromOutgoing(value);
      return this;
   }

   public void removeAllFromOutgoing()
   {
      // LinkedHashSet<PatternLink> tmpSet = new
      // LinkedHashSet<PatternLink>(this.getOutgoing());
      //
      // for (PatternLink value : tmpSet)
      // {
      // this.removeFromOutgoing(value);
      // }
   }

   // ==========================================================================
   public Object getCandidates()
   {
      return this.candidates;
   }

   public void setCandidates(Object value)
   {
      if (this.candidates != value)
      {
         Object oldValue = this.candidates;
         this.candidates = value;
         this.candidatesIterator = null;
         getPropertyChangeSupport().firePropertyChange(PROPERTY_CANDIDATES, oldValue, value);
      }
   }

   public PatternObject withCandidates(Object value)
   {
      setCandidates(value);
      return this;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * PatternObject ----------------------------------- AttributeConstraint
    *              src                   attrConstraints
    * </pre>
    * @return The AttributeConstraintSet
    */
   public AttributeConstraintSet getAttrConstraints()
   {
      if (this.attrConstraints == null)
      {
         return AttributeConstraint.EMPTY_SET;
      }

      return this.attrConstraints;
   }

   public boolean addToAttrConstraints(AttributeConstraint value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.attrConstraints == null)
         {
            this.attrConstraints = new AttributeConstraintSet();
         }

         changed = this.attrConstraints.add(value);

         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRCONSTRAINTS, null, value);
         }
      }

      return changed;
   }

   public boolean removeFromAttrConstraints(AttributeConstraint value)
   {
      boolean changed = false;

      if ((this.attrConstraints != null) && (value != null))
      {
         changed = this.attrConstraints.remove(value);

         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_ATTRCONSTRAINTS, value, null);
         }
      }

      return changed;
   }

   public PatternObject withAttrConstraints(AttributeConstraint value)
   {
      addToAttrConstraints(value);
      return this;
   }

   public PatternObject withoutAttrConstraints(AttributeConstraint value)
   {
      removeFromAttrConstraints(value);
      return this;
   }

   public void removeAllFromAttrConstraints()
   {
      LinkedHashSet<AttributeConstraint> tmpSet = new LinkedHashSet<AttributeConstraint>(this.getAttrConstraints());

      for (AttributeConstraint value : tmpSet)
      {
         this.removeFromAttrConstraints(value);
      }
   }

   public void hasLink(String roleName, PatternObject result)
   {
      if (Pattern.CREATE.equals(this.getPattern().getModifier()))
      {
         this.getPattern().addToElements(result);

         this.getPattern().findMatch();

         LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
               .withTgt(result).withTgtRoleName(roleName)
               .withSrc(this)
               .withModifier(this.getPattern().getModifier());

         this.getPattern().addToElements(patternLink);

         patternLink.getPattern().findMatch();
      }
      else
      {
         PatternLink patternLink = new PatternLink()
               .withTgt(result).withTgtRoleName(roleName)
               .withSrc(this);
         patternLink.setModifier(this.getPattern().getModifier());

         this.getPattern().addToElements(patternLink);

         this.getPattern().addToElements(result);

         result.getPattern()
         .findMatch();
      }
   }

   protected void filterAttr() {
      if(!this.getPattern().findMatch()) 
      {
         setCurrentMatch(null);
      }
   }

   public POC has(Condition<Object> condition, String text)
   {
      GenericConstraint genericConstraint = (GenericConstraint) new GenericConstraint()
            .withText(text)
            .withSrc(this)
            .withCondition(condition)
            .withModifier(this.getPattern().getModifier())
            .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return (POC) this;
   }

   /**
    * Depricated. Use filter() instead.
    * @param condition The condition used for filtering
    * @return this
    */
   @Deprecated
   public POC has(Condition<Object> condition)
   {
      GenericConstraint genericConstraint = (GenericConstraint) new GenericConstraint()
            .withCondition(condition)
            .withSrc(this)
            .withModifier(this.getPattern().getModifier())
            .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return (POC) this;
   }

   public POC filter(Condition<MC> condition)
   {
      GenericConstraint genericConstraint = (GenericConstraint) new GenericConstraint()
            .withCondition((Condition<Object>) condition)
            .withSrc(this)
            .withModifier(this.getPattern().getModifier())
            .withPattern(this.getPattern());

      this.getPattern().findMatch();

      return (POC) this;
   }
   
   
   public <TPOC extends PatternObject> TPOC filterPath(PathExpression expression, TPOC targetPatternObject)
   {
      PatternPath patternPath = new PatternPath();
      patternPath.setPathExpr(expression);
      patternPath.withSrc(this).withPattern(this.getPattern());
      patternPath.withTgt(targetPatternObject);
      
      targetPatternObject.withPattern(this.getPattern());
      
      this.getPattern().findMatch();
      
      return targetPatternObject;
   }
   
   /********************************************************************
    * <pre>
    *              one                       one
    * PatternObject ----------------------------------- DestroyObjectElem
    *              patternObject                   destroyElem
    * </pre>
    * @return The DestroyObjectElem
    */
   public DestroyObjectElem getDestroyElem()
   {
      return this.destroyElem;
   }

   public boolean setDestroyElem(DestroyObjectElem value)
   {
      boolean changed = false;

      if (this.destroyElem != value)
      {
         DestroyObjectElem oldValue = this.destroyElem;

         if (this.destroyElem != null)
         {
            this.destroyElem = null;
            oldValue.setPatternObject(null);
         }

         this.destroyElem = value;

         if (value != null)
         {
            value.withPatternObject(this);
         }

         getPropertyChangeSupport().firePropertyChange(PROPERTY_DESTROYELEM, oldValue, value);
         changed = true;
      }

      return changed;
   }

   public PatternObject withDestroyElem(DestroyObjectElem value)
   {
      setDestroyElem(value);
      return this;
   }

   DestroyObjectElem createDestroyElem()
   {
      DestroyObjectElem value = new DestroyObjectElem();
      withDestroyElem(value);
      return value;
   }

   public String toString()
   {
      StringBuilder s = new StringBuilder();

      s.append(" ").append(CGUtil.shortClassName(this.getClass().getName()));
      s.append(" ").append(this.getModifier());
      s.append(" ").append(this.getPatternObjectName());
      return s.substring(1);
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * PatternObject ----------------------------------- CardinalityConstraint
    *              src                   cardConstraints
    * </pre>
    * @return The CardinalityConstraintSet
    */
   public CardinalityConstraintSet getCardConstraints()
   {
      if (this.cardConstraints == null)
      {
         return CardinalityConstraint.EMPTY_SET;
      }

      return this.cardConstraints;
   }

   public boolean addToCardConstraints(CardinalityConstraint value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.cardConstraints == null)
         {
            this.cardConstraints = new CardinalityConstraintSet();
         }

         changed = this.cardConstraints.add(value);

         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CARDCONSTRAINTS, null, value);
         }
      }

      return changed;
   }

   public boolean removeFromCardConstraints(CardinalityConstraint value)
   {
      boolean changed = false;

      if ((this.cardConstraints != null) && (value != null))
      {
         changed = this.cardConstraints.remove(value);

         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_CARDCONSTRAINTS, value, null);
         }
      }

      return changed;
   }

   public PatternObject withCardConstraints(CardinalityConstraint value)
   {
      addToCardConstraints(value);
      return this;
   }

   public PatternObject withoutCardConstraints(CardinalityConstraint value)
   {
      removeFromCardConstraints(value);
      return this;
   }

   public void removeAllFromCardConstraints()
   {
      LinkedHashSet<CardinalityConstraint> tmpSet = new LinkedHashSet<CardinalityConstraint>(this.getCardConstraints());

      for (CardinalityConstraint value : tmpSet)
      {
         this.removeFromCardConstraints(value);
      }
   }

   CardinalityConstraint createCardConstraints()
   {
      CardinalityConstraint value = new CardinalityConstraint();
      withCardConstraints(value);
      return value;
   }

   public POC hasLinkConstraint(PatternObject tgt, String roleName)
   {
      if (tgt == null)
      {
         this.startNAC();

         PatternObject result = new PatternObject();

         result.setModifier(this.getPattern().getModifier());

         this.hasLink(roleName, result);

         this.endNAC();
      }
      else
      {
         LinkConstraint patternLink = (LinkConstraint) new LinkConstraint()
               .withTgt(tgt).withTgtRoleName(roleName)
               .withSrc(this)
               .withModifier(this.getPattern().getModifier());

         this.getPattern().addToElements(patternLink);

         this.getPattern().findMatch();
      }

      return (POC) this;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * PatternObject ----------------------------------- MatchOtherThan
    *              src                   matchOtherThan
    * </pre>
    * @return The MatchOtherThanSet
    */
   public MatchOtherThanSet getMatchOtherThan()
   {
      if (this.matchOtherThan == null)
      {
         return MatchOtherThan.EMPTY_SET;
      }

      return this.matchOtherThan;
   }

   public boolean addToMatchOtherThan(MatchOtherThan value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.matchOtherThan == null)
         {
            this.matchOtherThan = new MatchOtherThanSet();
         }

         changed = this.matchOtherThan.add(value);

         if (changed)
         {
            value.withSrc(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_MATCHOTHERTHAN, null, value);
         }
      }

      return changed;
   }

   public boolean removeFromMatchOtherThan(MatchOtherThan value)
   {
      boolean changed = false;

      if ((this.matchOtherThan != null) && (value != null))
      {
         changed = this.matchOtherThan.remove(value);

         if (changed)
         {
            value.setSrc(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_MATCHOTHERTHAN, value, null);
         }
      }

      return changed;
   }

   public PatternObject withMatchOtherThan(MatchOtherThan value)
   {
      addToMatchOtherThan(value);
      return this;
   }

   public PatternObject withoutMatchOtherThan(MatchOtherThan value)
   {
      removeFromMatchOtherThan(value);
      return this;
   }

   public void removeAllFromMatchOtherThan()
   {
      LinkedHashSet<MatchOtherThan> tmpSet = new LinkedHashSet<MatchOtherThan>(this.getMatchOtherThan());

      for (MatchOtherThan value : tmpSet)
      {
         this.removeFromMatchOtherThan(value);
      }
   }

   MatchOtherThan createMatchOtherThan()
   {
      MatchOtherThan value = new MatchOtherThan();
      withMatchOtherThan(value);
      return value;
   }

   public POC hasMatchOtherThan(PatternObject forbidden)
   {
      MatchOtherThan otherThan = createMatchOtherThan()
            .withForbidden(forbidden)
            .withPattern(getPattern());

      getPattern().findMatch();

      return (POC) this;
   }

   /********************************************************************
    * <pre>
    *              one                       many
    * PatternObject ----------------------------------- MatchOtherThan
    *              forbidden                   excluders
    * </pre>
    * @return The MatchOtherThanSet
    */
   public MatchOtherThanSet getExcluders()
   {
      if (this.excluders == null)
      {
         return MatchOtherThan.EMPTY_SET;
      }

      return this.excluders;
   }

   public boolean addToExcluders(MatchOtherThan value)
   {
      boolean changed = false;

      if (value != null)
      {
         if (this.excluders == null)
         {
            this.excluders = new MatchOtherThanSet();
         }

         changed = this.excluders.add(value);

         if (changed)
         {
            value.withForbidden(this);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_EXCLUDERS, null, value);
         }
      }

      return changed;
   }

   public boolean removeFromExcluders(MatchOtherThan value)
   {
      boolean changed = false;

      if ((this.excluders != null) && (value != null))
      {
         changed = this.excluders.remove(value);

         if (changed)
         {
            value.setForbidden(null);
            getPropertyChangeSupport().firePropertyChange(PROPERTY_EXCLUDERS, value, null);
         }
      }

      return changed;
   }

   public PatternObject withExcluders(MatchOtherThan value)
   {
      addToExcluders(value);
      return this;
   }

   public PatternObject withoutExcluders(MatchOtherThan value)
   {
      removeFromExcluders(value);
      return this;
   }

   public void removeAllFromExcluders()
   {
      LinkedHashSet<MatchOtherThan> tmpSet = new LinkedHashSet<MatchOtherThan>(this.getExcluders());

      for (MatchOtherThan value : tmpSet)
      {
         this.removeFromExcluders(value);
      }
   }

   MatchOtherThan createExcluders()
   {
      MatchOtherThan value = new MatchOtherThan();
      withExcluders(value);
      return value;
   }

   public PatternObject withAttrConstraints(AttributeConstraint... value)
   {
      if(value==null)
      {
         return this;
      }
      for (AttributeConstraint item : value)
      {
         addToAttrConstraints(item);
      }
      return this;
   }

   public PatternObject withoutAttrConstraints(AttributeConstraint... value)
   {
      for (AttributeConstraint item : value)
      {
         removeFromAttrConstraints(item);
      }
      return this;
   }

   public AttributeConstraint createAttrConstraintsNew()
   {
      AttributeConstraint value = new AttributeConstraint();
      withAttrConstraints(value);
      return value;
   }

   public PatternObject withCardConstraints(CardinalityConstraint... value)
   {
      if(value==null)
      {
         return this;
      }
      for (CardinalityConstraint item : value)
      {
         addToCardConstraints(item);
      }
      return this;
   }

   public PatternObject<?,?> withoutCardConstraints(CardinalityConstraint... value)
   {
      for (CardinalityConstraint item : value)
      {
         removeFromCardConstraints(item);
      }
      return this;
   }

   public PatternObject withMatchOtherThan(MatchOtherThan... value)
   {
      for (MatchOtherThan item : value)
      {
         addToMatchOtherThan(item);
      }
      return this;
   }

   public PatternObject withoutMatchOtherThan(MatchOtherThan... value)
   {
      for (MatchOtherThan item : value)
      {
         removeFromMatchOtherThan(item);
      }
      return this;
   }

   public PatternObject withExcluders(MatchOtherThan... value)
   {
      if(value==null){
         return this;
      }
      for (MatchOtherThan item : value)
      {
         addToExcluders(item);
      }
      return this;
   }

   public PatternObject withoutExcluders(MatchOtherThan... value)
   {
      for (MatchOtherThan item : value)
      {
         removeFromExcluders(item);
      }
      return this;
   }

   public Object createAttrConstraints()
   {
      AttributeConstraint value = new AttributeConstraint();
      withAttrConstraints(value);
      return value;
   }
}
