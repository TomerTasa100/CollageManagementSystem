public class Lecturer {
    private String id;
    private String name;
    private double salary;
    private Department department; //can be null
    private Committee[] committees;
    private int committeeCount;
    private Degree degree;

    public Lecturer(String name, String id, double salary, Degree degree) {
        this.name = name;
        this.id = id;
        this.degree = degree;
        setSalary(salary);
        this.committees = new Committee[2]; // start size
        this.committeeCount = 0;
        this.department = null; // at first he's not at any depart
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary <= 0) {
            System.out.println("Invalid salary. Salary must be positive.");
            this.salary = 0;

        } else {
            this.salary = salary;
        }
    }

    public Degree getDegree() {
        return degree;
    }
    public String getTitle() {
        return degree.getTitle();
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Committee[] getCommittees() {
        return committees;
    }

    public void setCommittees(Committee[] committees) {
        this.committees = committees;
    }

    public int getCommitteeCount() {
        return committeeCount;
    }

    public void setCommitteeCount(int committeeCount) {
        this.committeeCount = committeeCount;
    }

    public void addCommittee(Committee committee) {
        // בודקים אם כבר קיים
        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].getName().equalsIgnoreCase(committee.getName())) {
                return; // כבר נמצא
            }
        }

        // מגדילים אם צריך
        if (committeeCount >= committees.length) {
            Committee[] newCommittees = new Committee[committees.length * 2];
            System.arraycopy(committees, 0, newCommittees, 0, committees.length);
            committees = newCommittees;
        }

        committees[committeeCount++] = committee;
    }
    public void removeCommittee(Committee committee) {
        for (int i = 0; i < committeeCount; i++) {
            if (committees[i] != null && committees[i].equals(committee)) {
                // Shift elements left
                for (int j = i; j < committeeCount - 1; j++) {
                    committees[j] = committees[j + 1];
                }
                committees[--committeeCount] = null;
                return;
            }
        }
    }


}