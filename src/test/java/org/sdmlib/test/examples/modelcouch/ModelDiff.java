package org.sdmlib.test.examples.modelcouch;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.LinkedList;

import org.junit.Test;
import org.sdmlib.models.modelsets.SDMSet;
import org.sdmlib.test.examples.couchspace.tasks.util.TaskCreator;

import de.uniks.networkparser.IdMap;
import de.uniks.networkparser.interfaces.SendableEntity;

public class ModelDiff {
	public boolean equals(IdMap idMap, SendableEntity o1, SendableEntity o2) {
		if (o1.getClass().equals(o2.getClass())) {
			return equals(idMap, o1, o2, null);
		}
		return false;
	}

	protected boolean equals(IdMap idMap, SendableEntity o1, SendableEntity o2,
			LinkedList<SendableEntity> alreadyChecked) {
		if (o1.getClass().equals(o2.getClass())) {
			Field[] declaredFields1 = o1.getClass().getDeclaredFields();
			Field[] declaredFields2 = o2.getClass().getDeclaredFields();
			if (declaredFields1.length == declaredFields2.length) {
				for (Field field : declaredFields1) {
					// Check if all Field are the same
					try {
						field.setAccessible(true);
						if (!(field.get(o1) != null && field.get(o2) != null && field.get(o1).equals(field.get(o2))
								|| field.get(o1) == null && field.get(o2) == null)) {
							return false;
						}
					} catch (IllegalArgumentException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}

				// Check the Other classes
				if (alreadyChecked == null) {
					alreadyChecked = new LinkedList<>();
				}
				alreadyChecked.push(o1);
				alreadyChecked.push(o2);

				// get Objects connected with Edges
				for (Field field : declaredFields1) {
					if ((SDMSet.class.isAssignableFrom(field.getClass()))) {
						if (!equals(idMap, o1, o2, alreadyChecked)) {
							return false;
						}
					}
				}
			}
		}
		return false;
	}

	//@Test
	public void diffTest() {
		String sessionid = "diffTest" + System.currentTimeMillis();
		IdMap idMap = TaskCreator.createIdMap(sessionid);

		Task rootTask = new Task();

		idMap.put("rootTask", rootTask);

		DocumentData taskData01 = rootTask.createTaskData();
		DocumentData taskData02 = rootTask.createTaskData();

		Person person01 = rootTask.createPersons();
		Person person02 = rootTask.createPersons();

		assertTrue(equals(idMap, rootTask, rootTask));
	}
}
