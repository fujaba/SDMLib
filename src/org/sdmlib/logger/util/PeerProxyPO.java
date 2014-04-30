package org.sdmlib.logger.util;

import org.sdmlib.logger.PeerProxy;
import org.sdmlib.models.pattern.PatternObject;

public class PeerProxyPO extends PatternObject<PeerProxyPO, PeerProxy>
{

    public PeerProxySet allMatches()
   {
      this.setDoAllMatches(true);
      
      PeerProxySet matches = new PeerProxySet();

      while (this.getPattern().getHasMatch())
      {
         matches.add((PeerProxy) this.getCurrentMatch());
         
         this.getPattern().findMatch();
      }
      
      return matches;
   }
   
}

