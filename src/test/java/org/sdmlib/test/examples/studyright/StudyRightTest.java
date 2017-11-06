package org.sdmlib.test.examples.studyright;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.sdmlib.test.examples.studyright.model.Student;
import org.sdmlib.test.examples.studyright.model.University;
import org.sdmlib.test.examples.studyright.model.util.StudentPO;
import org.sdmlib.test.examples.studyright.model.util.UniversityPO;

public class StudyRightTest {
    @Test
    public void test(){
	University university = new University();
	Student peter = university.createStudents().withName("Peter");
	
	university.createRooms().withoutStudents(peter);
	
	StudentPO studentPO = new StudentPO(peter);
	UniversityPO uniPO = studentPO.startNAC().hasUni().endNAC();
	
	assertTrue(uniPO.isHasMatch());
	assertFalse(studentPO.isHasMatch());
    }
}
