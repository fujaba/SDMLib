/*
 * Copyright (c) 2017 zuendorf
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 * 
 * The Software shall be used for Good, not Evil.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.UBankSet;
import org.sdmlib.test.examples.reachabilitygraphs.unidirferrymansproblem.util.URiverSet;
import de.uniks.networkparser.interfaces.SendableEntity;

/**
 * 
 * @see <a href=
 *      '../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphExampleModels.java'>ReachabilityGraphExampleModels.java</a>
 * @see <a href=
 *      '../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphExampleModels#UniDirectFerryMansProblemModel
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#UniDirFerrymansProblemRules
 * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemLazyBackup
 */
public class URiver implements SendableEntity {


  // ==========================================================================

  protected PropertyChangeSupport listeners = null;

  public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue) {
    if (listeners != null) {
      listeners.firePropertyChange(propertyName, oldValue, newValue);
      return true;
    }
    return false;
  }

  public boolean addPropertyChangeListener(PropertyChangeListener listener) {
    if (listeners == null) {
      listeners = new PropertyChangeSupport(this);
    }
    listeners.addPropertyChangeListener(listener);
    return true;
  }

  public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    if (listeners == null) {
      listeners = new PropertyChangeSupport(this);
    }
    listeners.addPropertyChangeListener(propertyName, listener);
    return true;
  }

  public boolean removePropertyChangeListener(PropertyChangeListener listener) {
    if (listeners == null) {
      listeners.removePropertyChangeListener(listener);
    }
    listeners.removePropertyChangeListener(listener);
    return true;
  }

  public boolean removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
    if (listeners != null) {
      listeners.removePropertyChangeListener(propertyName, listener);
    }
    return true;
  }


  // ==========================================================================


  public void removeYou() {
    for (UBank obj : new UBankSet(this.getBanks())) {
      obj.removeYou();
    }
    if (getBoat() != null) {
      getBoat().removeYou();
    }
    firePropertyChange("REMOVE_YOU", this, null);
  }


  /********************************************************************
   * <pre>
   *              one                       many
   * URiver ----------------------------------- UBank
   *              uriver                   banks
   * </pre>
   */

  public static final String PROPERTY_BANKS = "banks";

  private UBankSet banks = null;

  public UBankSet getBanks() {
    if (this.banks == null) {
      return UBankSet.EMPTY_SET;
    }

    return this.banks;
  }

  public URiver withBanks(UBank... value) {
    if (value == null) {
      return this;
    }
    for (UBank item : value) {
      if (item != null) {
        if (this.banks == null) {
          this.banks = new UBankSet();
        }

        boolean changed = this.banks.add(item);

        if (changed) {
          firePropertyChange(PROPERTY_BANKS, null, item);
        }
      }
    }
    return this;
  }

  public URiver withoutBanks(UBank... value) {
    for (UBank item : value) {
      if ((this.banks != null) && (item != null)) {
        if (this.banks.remove(item)) {
          firePropertyChange(PROPERTY_BANKS, item, null);
        }
      }
    }
    return this;
  }

  /**
   * 
   * @see <a href=
   *      '../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
   * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#UniDirFerrymansProblemRules
   * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemLazyBackup
   */
  public UBank createBanks() {
    UBank value = new UBank();
    withBanks(value);
    return value;
  }


  /********************************************************************
   * <pre>
   *              one                       one
   * URiver ----------------------------------- UBoat
   *              uriver                   boat
   * </pre>
   */

  public static final String PROPERTY_BOAT = "boat";

  private UBoat boat = null;

  public UBoat getBoat() {
    return this.boat;
  }

  public boolean setBoat(UBoat value) {
    boolean changed = false;

    if (this.boat != value) {
      UBoat oldValue = this.boat;

      this.boat = value;

      firePropertyChange(PROPERTY_BOAT, oldValue, value);
      changed = true;
    }

    return changed;
  }

  public URiver withBoat(UBoat value) {
    setBoat(value);
    return this;
  }

  /**
   * 
   * @see <a href=
   *      '../../../../../../../../../src/test/java/org/sdmlib/test/examples/reachabilitygraphs/ReachabilityGraphFerrymansProblemExample.java'>ReachabilityGraphFerrymansProblemExample.java</a>
   * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#UniDirFerrymansProblemRules
   * @see org.sdmlib.test.examples.reachabilitygraphs.ReachabilityGraphFerrymansProblemExample#FerrymansProblemLazyBackup
   */
  public UBoat createBoat() {
    UBoat value = new UBoat();
    withBoat(value);
    return value;
  }

  @Override
  public String toString() {
    StringBuilder buf = new StringBuilder();


    buf.append("left: ");
    UBankSet banks = this.getBanks().filter(b -> ((UBank) b).getName().equals("left"));

    buf.append(banks.getCargos().getName());

    buf.append("\nboat: at: ");

    URiverSet thisSet = new URiverSet().with(this);

    buf.append(thisSet.getBoat().getBank().getName());

    buf.append(" with: ");

    buf.append(thisSet.getBoat().getCargo().getName());

    buf.append("\nright: ");

    banks = this.getBanks().filter(b -> ((UBank) b).getName().equals("right"));

    buf.append(banks.getCargos().getName());

    buf.append("\n");

    return buf.toString();
  }
}
