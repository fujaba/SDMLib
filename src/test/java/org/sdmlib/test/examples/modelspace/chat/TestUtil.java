package org.sdmlib.test.examples.modelspace.chat;

import java.util.Map;

public class TestUtil {
	public static boolean isTravis() {
		boolean result=false;
		Map<String, String> env = System.getenv();
        for (String envName : env.keySet()) {
        	if("build".equalsIgnoreCase(envName)) {
        		result = "travisci".equalsIgnoreCase(env.get(envName));
        	}
//            System.out.format("%s=%s%n",
//                              envName,
//                              env.get(envName));
        }
        return result;
	}
}
