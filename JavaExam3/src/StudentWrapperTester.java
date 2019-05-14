import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StudentWrapperTester {

	public StudentWrapper.CIICStudent ciic1;
	public StudentWrapper.CIICStudent ciic2;
	public StudentWrapper.CIICStudent ciic3;
	public StudentWrapper.CIICStudent ciic4;

	public StudentWrapper.INSOStudent inso1;
	public StudentWrapper.INSOStudent inso2;
	public StudentWrapper.INSOStudent inso3;
	public StudentWrapper.INSOStudent inso4;

	StudentWrapper.CIICStudent[] emptyCIICList;
	StudentWrapper.CIICStudent[] allCIICList;
	StudentWrapper.CIICStudent[] hugeCIICList;

	StudentWrapper.INSOStudent[] emptyINSOList;
	StudentWrapper.INSOStudent[] allINSOList;
	StudentWrapper.INSOStudent[] hugeINSOList;

	@Before
	public void setUp() {
		ciic1 = new StudentWrapper.CIICStudent("802-14-1234", "Jose", "Santiago", StudentWrapper.Gender.MALE, 3.67, 3.5, 21);
		ciic2 = new StudentWrapper.CIICStudent("802-14-1111", "Pedro", "Martinez", StudentWrapper.Gender.MALE, 2.89, 3.1, 45);
		ciic3 = new StudentWrapper.CIICStudent("802-14-3333", "Juan", "Velez", StudentWrapper.Gender.MALE, 3.5, 4.0, 120);
		ciic4 = new StudentWrapper.CIICStudent("802-14-4444", "Marta", "Ramirez", StudentWrapper.Gender.FEMALE, 4.0, 3.5, 155);
		inso1 = new StudentWrapper.INSOStudent("802-14-1235", "Maria", "Santiago", StudentWrapper.Gender.FEMALE, 3.89, 3.2, 30);
		inso2 = new StudentWrapper.INSOStudent("802-14-2222", "Ana", "Lopez", StudentWrapper.Gender.FEMALE, 3.23, 3.5, 60);
		inso3 = new StudentWrapper.INSOStudent("802-14-1235", "Joe", "Doe", StudentWrapper.Gender.MALE, 3.89, 3.2, 30);
		inso4 = new StudentWrapper.INSOStudent("802-14-2222", "John", "Doe", StudentWrapper.Gender.MALE, 3.23, 3.5, 60);

		emptyCIICList = new StudentWrapper.CIICStudent[] {};
		allCIICList = new StudentWrapper.CIICStudent[] {ciic1, ciic2, ciic3, ciic4};
		hugeCIICList = new StudentWrapper.CIICStudent[10000];
		Arrays.fill(hugeCIICList, ciic1);

		emptyINSOList = new StudentWrapper.INSOStudent[] {};
		allINSOList = new StudentWrapper.INSOStudent[] {inso1, inso2, inso3, inso4};
		hugeINSOList = new StudentWrapper.INSOStudent[100000];
		Arrays.fill(hugeINSOList, inso2);

	}

	@Test
	public void testStudentAbstract() {
		assertTrue("CIIC Student should be instance of the AbstractStudent Class", ciic1 instanceof StudentWrapper.AbstractStudent);
		assertTrue("INSO Student should be instance of the AbstractStudent Class", inso1 instanceof StudentWrapper.AbstractStudent);

		StudentWrapper.AbstractStudent absCIIC1 = (StudentWrapper.AbstractStudent) ciic1;
		StudentWrapper.AbstractStudent absINSO1 = (StudentWrapper.AbstractStudent) inso1;

		boolean hasMethods = true;
		try {
			Class c = Class.forName("StudentWrapper");
			Class cl[] = c.getDeclaredClasses();
			for(Class cls : cl) {
				if(cls.toString().contains("AbstractStudent")) {
					Method[] methods = cls.getDeclaredMethods();
					assertEquals("Should only contain 12 methods", 12, methods.length);
					for(Method method: methods) {
						if(method.toString().contains("getIdNumber")) {
							assertEquals("Didn't recieve a string", "802-14-1234", (String) method.invoke(absCIIC1));
						}
						if(method.toString().contains("getIncomeTaxRate")) {
							assertTrue("Didn't recieve a double", method.invoke(absCIIC1) instanceof Double);
						}
						if(method.toString().contains("getFirstName")) {
							assertEquals("Didn't recieve a string", "Maria", (String) method.invoke(absINSO1));
						}
						if(method.toString().contains("getLastName")) {
							assertEquals("Didn't recieve a string", "Santiago", (String) method.invoke(absINSO1));
						}		
						if(method.toString().contains("getGender")) {
							assertEquals("Didn't recieve a Gender", StudentWrapper.Gender.MALE, (StudentWrapper.Gender) method.invoke(absCIIC1));
						}
						if(method.toString().contains("getGPA")) {
							assertEquals("Didn't recieve a Number", 3.67, (Double) method.invoke(absCIIC1), 1E-10);
						}
						if(method.toString().contains("setIdNumber")) {
							method.invoke(absCIIC1, "1111");
							assertEquals("The id wasn't updated", "1111", ciic1.getIdNumber());
							method.invoke(absCIIC1, "802-14-1234");
						}
						if(method.toString().contains("setFirstName")) {
							method.invoke(absINSO1, "Al");
							assertEquals("The name wasn't updated", "Al", inso1.getFirstName());
							method.invoke(absINSO1, "Maria");
						}
						if(method.toString().contains("setLastName")) {
							method.invoke(absCIIC1, "Jones");
							assertEquals("The name wasn't updated", "Jones", ciic1.getLastName());
							method.invoke(absCIIC1, "Santiago");
						}
						if(method.toString().contains("setGender")) {
							method.invoke(absINSO1, StudentWrapper.Gender.MALE);
							assertEquals("The gender wasn't updated", StudentWrapper.Gender.MALE, inso1.getGender());
							method.invoke(absINSO1, StudentWrapper.Gender.FEMALE);
						}
						if(method.toString().contains("getGPA")) {
							method.invoke(absINSO1, 0);
							assertEquals("The gender wasn't updated", 0, inso1.getGPA(), 1E-10);
							method.invoke(absINSO1, 3.89);
						}
						if(method.toString().contains("getSpecialtyGPA")) {
							if(!method.toString().contains("abstract")) {
								hasMethods = false;
								break;
							}
						}
						if(method.getName().contains("getSpecialtyGPA")) {
							if(!method.toString().contains("abstract")) {
								hasMethods = false;
								break;	    					}
						}
						else {
							hasMethods = false;
							break;
						}
					}
				}
			}

		} catch (Exception e) {
			fail(e.toString());
		}
		assertFalse("These abstract class has missing methods", hasMethods);

	}

	@Test
	public void testRefactoring() {
		boolean hasMethods = false;
		try {
			Class c = Class.forName("StudentWrapper");
			Class cl[] = c.getDeclaredClasses();
			for(Class cls : cl) {
				if(cls.toString().contains("CIIC") || cls.toString().contains("INSO")) {
					Method[] methods = cls.getDeclaredMethods();
					for(Method method: methods) {
						if(method.getName().contains("getIdNumber") ||
								method.getName().contains("getFirstName") ||
								method.getName().contains("getLastName") ||
								method.getName().contains("getGender") ||
								method.getName().contains("getGPA") ||
								method.getName().contains("setIdNumber") ||
								method.getName().contains("setFirstName") ||
								method.getName().contains("setLastName") ||
								method.getName().contains("setGender")) {
							hasMethods = true;
							break;
						}
					}
				}
			}

		} catch (Exception e) {
			fail(e.toString());
		}
		assertFalse("The subclases still contain methods that should be in the abstract class", hasMethods);
	}

	@Test
	public void testGetSpecialtyGPA() {
		assertEquals("Wrong specialty GPA for CIIC student", ciic1.getCIICGPA(), ciic1.getSpecialtyGPA(),1e-5);
		assertEquals("Wrong specialty GPA for INSO student", inso1.getINSOGPA(), inso1.getSpecialtyGPA(),1e-5);
	}

	@Test
	public void testCloneableInterface() {

		boolean interfaceFound = false;
		try {
			Class cls = StudentWrapper.AbstractStudent.class;
			Class[] inters = cls.getInterfaces();
			for(Class inter: inters) {
				if(inter.getName().contains("Cloneable")) {
					interfaceFound = true;
				}
			}
		} catch (Exception e) {
			fail(e.toString());
		}
		assertTrue("Cloneable interface not implemented by abstract class", interfaceFound);

		StudentWrapper.AbstractStudent ciic1Copy = (StudentWrapper.AbstractStudent) ciic1.clone();
		StudentWrapper.AbstractStudent ciic2Copy = (StudentWrapper.AbstractStudent) ciic2.clone();

		assertTrue("The copy generated must be an instance of the CIICStudent Class", ciic1Copy instanceof StudentWrapper.CIICStudent);
		assertTrue("The copy generated must be an instance of the CIICStudent Class", ciic2Copy instanceof StudentWrapper.CIICStudent);

		assertFalse("The copy should not share the same instance", ciic1 == ciic1Copy);
		assertFalse("The copy should not share the same instance", ciic2 == ciic2Copy);

		assertFalse("The copy should not share the same position instance", ciic1.getIdNumber() == ((StudentWrapper.CIICStudent)ciic1Copy).getIdNumber());
		assertFalse("The copy should not share the same position instance", ciic2.getIdNumber() == ((StudentWrapper.CIICStudent)ciic2Copy).getIdNumber());

		assertFalse("The copy should not share the same position instance", ciic1.getLastName() == ((StudentWrapper.CIICStudent)ciic1Copy).getLastName());
		assertFalse("The copy should not share the same position instance", ciic2.getFirstName() == ((StudentWrapper.CIICStudent)ciic2Copy).getFirstName());

		assertEquals("These copies should be identical", ciic1.getIdNumber(), ((StudentWrapper.CIICStudent)ciic1Copy).getIdNumber());
		assertEquals("These copies should be identical", ciic2.getIdNumber(), ((StudentWrapper.CIICStudent)ciic2Copy).getIdNumber());
	}

	@Test 
	public void testDefineTaxPayerInterface() {

		boolean interfaceFound = false;
		Class taxPayerInterface=null;
		try {
			Class cls = StudentWrapper.class;
			Class[] inters = cls.getClasses();
			for(Class inter: inters) {
				if(inter.getName().contains("IncomeTaxPayer")) {
					interfaceFound = true;
					taxPayerInterface = inter;
				}
			}
		} catch (Exception e) {
			fail(e.toString());
		}
		assertTrue("IncomeTaxPayer interface not implemented by abstract class", interfaceFound);
		Method[] methods = taxPayerInterface.getMethods();
		assertEquals("TaxPayerInterface should have only one method", 1, methods.length);
		assertEquals(methods[0].getName(), "getIncomeTaxRate");

	}

	@Test 
	public void testImplementTaxPayerInterface() {

		assertTrue("CIICStudent should be instance of IncomeTaxPayer", ciic1 instanceof StudentWrapper.IncomeTaxPayer);
		assertTrue("INSOStudent should be instance of IncomeTaxPayer", inso1 instanceof StudentWrapper.IncomeTaxPayer);

		assertEquals("INSOStudent should pay 5% tax rate", 0.05, inso1.getIncomeTaxRate(), 1e-5);
		assertEquals("The TaxPayer interface does not contain getTaxRate", 0.05,(ciic1).getIncomeTaxRate(), 1e-5);

	}

	@Rule
	public final ExpectedException exception = ExpectedException.none();


	@Test
	public void testFindINSOStudent() {
		assertEquals("Top student of empty list should be null", false, inso1.findINSOStudent(emptyINSOList, 0));
		assertTrue("Existing student not found in list", inso4.findINSOStudent(allINSOList, 0));
	    exception.expect(StackOverflowError.class);
		assertEquals("Top student in list should be Juan", ciic1, inso1.findINSOStudent(hugeINSOList, 0));
	}

	@Test
	public void testFindTopCIICStudent() {
		assertEquals("Top student of empty list should be null", null, StudentWrapper.CIICStudent.getTopCIICStudent(0,emptyCIICList));
		assertEquals("Top student in list should be Juan", ciic3, StudentWrapper.CIICStudent.getTopCIICStudent(0,allCIICList));
	    exception.expect(StackOverflowError.class);
		assertEquals("Top student in list should be Juan", ciic1, StudentWrapper.CIICStudent.getTopCIICStudent(0,hugeCIICList));
	}

}