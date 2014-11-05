package org.sdmlib.doc;

/*
   Copyright (c) 2014 zuendorf

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


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import de.uniks.networkparser.graph.GraphList;

public class DocEnvironment {
	public void copyJS(String target){
		new File(target).mkdirs();
		new File(target+"/includes").mkdirs();
		copyDocFile(target, "d3.v3.min.js");
		copyDocFile(target, "nv.d3.css");
		copyDocFile(target, "nv.d3.min.js");
		copyDocFile(target, "dagre.min.js");
		copyDocFile(target, "drawer.js");
		copyDocFile(target, "graph.js");
		copyDocFile(target, "diagramstyle.css");
	}

	private void copyDocFile(String targetFolder, String file) {
		File target = new File(targetFolder + "/includes/" + file);

		InputStream is = GraphList.class.getResourceAsStream(file);

		if (is != null) {
			final int BUFF_SIZE = 5 * 1024; // 5KB
			final byte[] buffer = new byte[BUFF_SIZE];

			try {
				if (!target.exists()) {
					target.createNewFile();
				}
				FileOutputStream out = new FileOutputStream(target);

				while (true) {
					int count = is.read(buffer);
					if (count == -1)
						break;
					out.write(buffer, 0, count);
				}
				out.close();
				is.close();
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}
}
