package org.sdmlib.doc.interfaze.Drawer;

import java.io.File;

public interface GuiFileDrawer
{
   public boolean drawImg(String fileName, String context);
   public GuiFileDrawer withPlugin(File path, String plusinName);
   public String getVersion();
}
