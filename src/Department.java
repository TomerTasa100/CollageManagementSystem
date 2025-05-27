/**
 * Represents an academic department in the college.
 * Implements Comparable to allow comparison between departments based on staff count.
 */
public class Department implements Comparable<Department> {
    private String name;
    private int studentCount;
    private Lecturer[] lecturers;
    private int lecturerCount;

    /**
     * Constructs a new Department with the specified name and student count.
     * @param name The name of the department
     * @param studentCount The number of students in the department
     */
    public Department(String name, int studentCount) {
        this.name = name;
        setStudentCount(studentCount);
        this.lecturers = new Lecturer[2];
        this.lecturerCount = 0;
    }

    /**
     * Gets the name of the department.
     * @return The department name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the department.
     * @param name The new department name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the total number of students in the department.
     * @return The number of students
     */
    public int getStudentCount() {
        return studentCount;
    }

    /**
     * Sets the number of students in the department.
     * Validates that the count is not negative.
     * @param studentCount The new number of students
     */
    public void setStudentCount(int studentCount) {
        if (studentCount < 0) {
            System.out.println("Enter a valid number of students!, must be greater than 0.");
        } else {
            this.studentCount = studentCount;
        }
    }

    /**
     * Gets the array of lecturers in the department.
     * @return Array of lecturers
     */
    public Lecturer[] getLecturers() {
        return lecturers;
    }

    /**
     * Sets the array of lecturers for the department.
     * @param lecturers The new array of lecturers
     */
    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    /**
     * Gets the current count of lecturers in the department.
     * @return The number of lecturers
     */
    public int getLecturerCount() {
        return lecturerCount;
    }

    /**
     * Sets the count of lecturers in the department.
     * @param lecturerCount The new count of lecturers
     */
    public void setLecturerCount(int lecturerCount) {
        this.lecturerCount = lecturerCount;
    }

    /**
     * Adds a new lecturer to the department.
     * Automatically resizes the lecturers array if needed.
     * @param lecturer The lecturer to add
     */
    public void addLecturer(Lecturer lecturer) {
        if (lecturerCount == lecturers.length) {
            Lecturer[] bigger = new Lecturer[lecturers.length * 2];
            for (int i = 0; i < lecturers.length; i++) {
                bigger[i] = lecturers[i];
            }
            lecturers = bigger;
        }
        lecturers[lecturerCount++] = lecturer;
    }

    /**
     * Compares this department with another based on number of lecturers.
     * Implements the Comparable interface.
     * @param o The other department to compare with
     * @return Positive if this department has more lecturers, negative if fewer, 0 if equal
     */
    @Override
    public int compareTo(Department o) {
        return this.lecturerCount - o.lecturerCount;
    }

    /**
     * Compares this department with another based on total published articles.
     * @param other The other department to compare with
     * @return Positive if this department has more articles, negative if fewer, 0 if equal
     */
    public int compareByArticles(Department other) {
        return this.getTotalArticles() - other.getTotalArticles();
    }
    
    /**
     * Calculates the total number of published articles by all lecturers in the department.
     * Only counts articles from lecturers who implement the Publishable interface (Dr. and Prof.).
     * @return The total number of articles
     */
    public int getTotalArticles() {
        int totalArticles = 0;
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i] != null && lecturers[i].getDegree() instanceof Publishable) {
                Publishable pub = (Publishable) lecturers[i].getDegree();
                totalArticles += pub.getPublicationCount();
            }
        }
        return totalArticles;
    }
}