
public class StudentWrapper {

	public enum Gender {
		MALE,
		FEMALE
	}

	public enum Merit {
		LAUDE,
		CUM_LAUDE,
		MAGNA_CUM_LAUDE
	}

	/**
	 * Exercise #3
	 * An interface that represents an Income Tax Payer.
	 * Contains the method getIncomeTaxRate.
	 *
	 */
	public interface IncomeTaxPayer {
		// YOUR CODE HERE
		public double getIncomeTaxRate();
	}

	/**
	 * Exercise #1
	 * 
	 * An abstract class that holds the properties and implements the methods that are common 
	 * to students of both specialties CIIC and INSO. All the moved properties must remain 
	 * PRIVATE and the methods PUBLIC.
	 * 
	 * The abstract class should supply an appropriate constructor to be used by 
	 * subclass constructors to initialize the private properties in the abstract class.
	 * Any variables that do not have the same definition should not be moved from their
	 * respective classes (Like INSOGpa).
	 * Any methods that have are the same, but have different implementations or use different
	 * variables can be moved to the abstract class, but only its declaration, not the implementation.
	 * 
	 */
	public static abstract class AbstractStudent implements Cloneable, IncomeTaxPayer {
		// YOUR CODE HERE
		private String idNumber;
		private String firstName;
		private String lastName;
		private Gender gender;
		private double GPA;
		
		public AbstractStudent(String idNumber, String firstName, String lastName, Gender gender, double GPA) {
			this.idNumber = idNumber;
			this.firstName = firstName;
			this.lastName = lastName;
			this.gender = gender;
			this.GPA = GPA;
		}
		
		public String getIdNumber() {
			return idNumber;
		}
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Gender getGender() {
			return gender;
		}
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		public double getGPA() {
			return GPA;
		}
		public void setGPA(double GPA) {
			this.GPA = GPA;
		}
		public double getIncomeTaxRate() {
			return 0.05;
		}
		public abstract double getSpecialtyGPA();
		
	}

	/**
	 * Exercise #2 (Part A)
	 * Refactor this class to remove any property or method that was 
	 * moved to the AbstractStudent class.  You should modify the
	 * constructor to call the super constructor appropriately.
	 * 
	 * A class that represents a CIIC Student
	 *
	 */
	public static class CIICStudent extends AbstractStudent {
		
		private String idNumber;
		private String firstName;
		private String lastName;
		private Gender gender;
		private double GPA;
		private double ciicGPA;
		private int    ciicCredits;

		public CIICStudent(String idNumber, String firstName, String lastName, Gender gender, double GPA, double ciicGPA, int ciicCredits) {
			super(idNumber, firstName, lastName, gender, GPA);
			this.idNumber = idNumber;
			this.firstName = firstName;
			this.lastName = lastName;
			this.gender = gender;
			this.GPA = GPA;
			this.ciicGPA = ciicGPA;
			this.ciicCredits = ciicCredits;
		}

		public String getIdNumber() {
			return idNumber;
		}
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Gender getGender() {
			return gender;
		}
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		public double getGPA() {
			return GPA;
		}
		public void setGPA(double GPA) {
			this.GPA = GPA;
		}

		public double getCIICGPA() {
			return ciicGPA;
		}

		public int getCIICCredits() {
			return ciicCredits;
		}

		public double getSpecialtyGPA() {
			return ciicGPA;
		}

		/**
		 * Exercise #6
		 * Returns a deep clone of the target object. A deep clone is a copy of
		 * the object replacing any references to internal objects with references
		 * to deep clones of these internal objects.
		 * Implements Cloneable interface
		 */
		@Override
		public Object clone() {
			
			
			
			return null; // Dummy return
		}

		public static CIICStudent maxGPA(CIICStudent s1, CIICStudent s2) {
			return (s1.getCIICGPA()>s2.getCIICGPA() ? s1 : s2);
		}

		/**
		 * Exercise #8
		 * A method that returns the first student with the highest CIIC GPA
		 * The method MUST BE RECURSIVE.
		 * @param start
		 * @param students
		 * @return
		 */
		
		public static CIICStudent getTopCIICStudent(int start, CIICStudent[] students) {
			// YOUR CODE HERE
			return getTopCIICStudentHelper(start, students[start], students);
		}
		
		public static CIICStudent getTopCIICStudentHelper(int start, CIICStudent highest, CIICStudent[] students) {
			
			if(students.length == 0) {
				return null;
			}
			
			if(start <= students.length) {
				return highest;
			}
			
			if(highest.getCIICGPA() < students[start + 1].getCIICGPA()) {
				highest = students[start + 1];
			}
			
			return getTopCIICStudentHelper(start + 1, highest, students);
		}
		
		public String toString() {
			return "Student: " + this.getIdNumber();
		}

		
		/**
		 * Exercise #4
		 * A method that returns the students tax rate
		 * Hint: Student have 5% tax rate.
		 */
	
		public double getIncomeTaxRate() {
			return 0.05; // Dummy return
		}

	}

	/**
	 * Exercise #2 (Part B)
	 * Refactor this class to remove any property or method that was 
	 * moved to the AbstractStudent class.  You should modify the
	 * constructor to call the super constructor appropriately.
	 * 
	 * A class that represents a INSO Student
	 *
	 */
	public static class INSOStudent extends AbstractStudent {

		private String idNumber;
		private String firstName;
		private String lastName;
		private Gender gender;
		private double GPA;
		private double insoGPA;
		private int    insoCredits;

		public INSOStudent(String idNumber, String firstName, String lastName, Gender gender, double GPA, double insoGPA, int insoCredits) {
			super(idNumber, firstName, lastName, gender, GPA);
			this.idNumber = idNumber;
			this.firstName = firstName;
			this.lastName = lastName;
			this.gender = gender;
			this.GPA = GPA;
			this.insoGPA = insoGPA;
			this.insoCredits = insoCredits;
		}

		public String getIdNumber() {
			return idNumber;
		}
		public void setIdNumber(String idNumber) {
			this.idNumber = idNumber;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public Gender getGender() {
			return gender;
		}
		public void setGender(Gender gender) {
			this.gender = gender;
		}
		public double getGPA() {
			return GPA;
		}
		public void setGPA(double GPA) {
			this.GPA = GPA;
		}

		public double getINSOGPA() {
			return insoGPA;
		}

		public int getINSOCredits() {
			return insoCredits;
		}

		@Override
		public Object clone() {
			// Do not implement
			return null;
		}

		/**
		 * Exercise #4
		 * A method that returns the students tax rate
		 * Hint: Student have 5% tax rate.
		 */
	
		public double getIncomeTaxRate() {
			return 0.05;
		}
		
		/**
		 * Exercise #7
		 * A method that returns the position of the target INSOStudent in the given list.
		 * The method MUST BE RECURSIVE
		 * @param list
		 * @param start
		 * @return
		 */
		public boolean findINSOStudent(INSOStudent[] list, int start) {
			// YOUR CODE HERE
			if(start > list.length - 1) {
				return false;
			}
			
			if(list[start].equals(this)) {
				return true;
			}
			
			return findINSOStudent(list, start + 1); // Dummy return
		}

		/**
		 * Exercise #5
		 * A method that returns the student specialty GPA
		 * @return
		 */
		public double getSpecialtyGPA() {
			// YOUR CODE HERE
			return this.getINSOGPA(); // Dummy return
			
		}
	}
}