import java.util.Scanner;
public class CollegeManager {

    private String collegeName;
    private Lecturer[] lecturers = new Lecturer[2]; // creates new object
    private int lecturerCount = 0;
    private Department[] departments = new Department[2]; // creates new object
    private int departmentCount = 0;
    private Committee[] committees = new Committee[2]; // creates new object
    private int committeeCount = 0;
    public CollegeManager(String collegeName) {
        this.collegeName = collegeName;
    }
    private void printMenu() {
        String menu =
                "\n____|" + collegeName.toUpperCase() + " COLLEGE MANAGEMENT SYSTEM|____\n" +
                "0 - Exit\n" +
                "1 - Add Lecturer\n" +
                "2 - Create Committee\n" +
                "3 - Add Lecturer to Committee\n" +
                "4 - Update Committee Chairman\n" +
                "5 - Remove Lecturer from Committee\n" +
                "6 - Add Department\n" +
                "7 - Show Average salary (All Lecturers)\n" +
                "8 - Show Average salary (By Department)\n" +
                "9 - Show All Lecturers Full Details\n" +
                "10 - Show All Committees Full Details\n" +
                "11 - Compare two Doctor/Professors by number of articles\n" +
                "12 - Assign Lecturer to Department\n" +
                "13 - Compare Departments";

        System.out.println(menu);
    }
    public void run(Scanner scanner) {
        int optionSelected = -1;

        while (optionSelected != 0) {
            printMenu();
            System.out.println("____________|ENTER A NUMBER|___________ ");

            if (scanner.hasNextInt()) {
                optionSelected = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Skip wrong input
                continue;
            }

            switch (optionSelected) {
                case 0:
                    System.out.println("Exiting...");
                    break;
                case 1:
                    addLecturer(scanner);
                    break;
                case 2:
                    try {
                        createCommittee(scanner);}
                    catch (InvalidChairmanException e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        addLecturerToCommittee(scanner);
                    } catch (CommitteeOperationException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        updateCommitteeChairman(scanner);
                    } catch (InvalidChairmanException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    removeLecturerFromCommittee(scanner);
                    break;
                case 6:
                    addDepartment(scanner);
                    break;
                case 7:
                    showAverageSalary();
                    break;
                case 8:
                    showAverageSalaryDepartment(scanner);
                    break;
                case 9:
                    printLecturers();
                    break;
                case 10:
                    printCommittees();
                    break;
                case 11:
                    comparePublishableLecturers(scanner);
                    break;
                case 12:
                    assignLecturerToDepartment(scanner);
                    break;
                case 13:
                    compareDepartments(scanner);
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number between 0–13.");
            }
        }
    }
    public boolean isExisting(Lecturer[] array, String id) {
        for (int i = 0; i < lecturerCount; i++) {
            if (array[i] != null && array[i].getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
    public boolean isExisting(Department[] array, String name) {
        for (int i = 0; i < departmentCount; i++) {
            if (array[i] != null && array[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    public boolean isExisting(Committee[] array, String name) {
        for (int i = 0; i < committeeCount; i++) {
            if (array[i] != null && array[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
    public void printStrings(String[] array, int count, String label) {
        System.out.println("All " + label + ":");
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (array[i] != null) {
                System.out.println("--> " + array[i]);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No " + label + " have been added yet.");
        }
    }
    public void addLecturer(Scanner scanner) {
        if (lecturerCount == lecturers.length) {
            Lecturer[] newLecturers = new Lecturer[lecturers.length * 2];
            for (int i = 0; i < lecturers.length; i++) {
                newLecturers[i] = lecturers[i];
            }
            lecturers = newLecturers;
        }

        System.out.print("Enter Lecturer full Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Lecturer ID: ");
        String id = scanner.nextLine();

        while (isExisting(lecturers, id)) { //checks if the Lecturer exists
            System.out.println("Lecturer with this ID already exists. Please enter a different ID.");
            id = scanner.nextLine();
        }

        System.out.print("Enter Lecturer Salary(must be positive): ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // clear buffer

        if (salary <= 0) {
            System.out.println("\n! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !");
            System.out.println("Invalid salary. Salary must be positive.");
            System.out.println("! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! ! !");
            return;
        }

        System.out.println("Enter degree (BA/MA/DR/PROF):");
        String degreeInput = scanner.nextLine();
        Degree degree;
        int numberOfArticles = 0;
        switch (degreeInput.toUpperCase()) {
            case "BA":
                degree = new BachelorDegree();
                break;
            case "MA":
                degree = new MasterDegree();
                break;
            case "DR":
                System.out.print("Enter the number of the articales was published(must be grater than 0): ");
                numberOfArticles = readNumberOfArticles(scanner);
                degree = new Doctor(numberOfArticles);
                break;
            case "PROF":
                System.out.print("Enter granting body for professor: ");
                String grantingBody = scanner.nextLine();
                numberOfArticles = readNumberOfArticles(scanner);
                degree = new Professor(numberOfArticles, grantingBody);
                break;
            default:
                System.out.println("Invalid degree. Defaulting to BA.");
                degree = new BachelorDegree();
        }

        Lecturer newLecturer = new Lecturer(name, id, salary, degree);
        lecturers[lecturerCount++] = newLecturer;

        System.out.println("Lecturer added successfully.");
    }
    public void assignLecturerToDepartment(Scanner scanner){
        System.out.println("Enter Lecturer ID");
        String id = scanner.nextLine();
        if (isExisting(lecturers,id)){
            Department currentDepartment = printAllDepartments(scanner);

            Lecturer currentLecturer = findLecturerById(id); //sends the id to a function that returns the lecturer as a object

            if(currentLecturer != null && currentDepartment !=null){
                // very important line of code, here we are assigning (literally) both of them
                currentLecturer.setDepartment(currentDepartment); // Lecturer -> department(attribute)
                currentDepartment.addLecturer(currentLecturer); // Department -> Lecturer(attribute)
                System.out.println("Lecturer was added successfully to "+ currentDepartment.getName() + " department");
            }
        }
        else{System.out.println("Lecturer was not found, try again");}


    }
    public Lecturer findLecturerById(String id){
        for (int i = 0; i<lecturerCount;i++){
            if (lecturers[i].getId().equalsIgnoreCase(id)){
                return lecturers[i];
            }
        }
        return null;
    }
    public Department printAllDepartments(Scanner scanner){
        for (int i = 0; i < departmentCount; i++) {
            System.out.println("\n~~~Choose Option~~~");
            System.out.println((i + 1) + ") " + departments[i].getName());
            System.out.println("~~~~~~~~~~~~~~~~~~~\n");
        }

        System.out.println("Type Option Num");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option < 1 || option > departmentCount) {
            System.out.println("Invalid option.");
            return null;
        }

        return departments[option - 1];
    }
    public void addDepartment(Scanner scanner) {
        if (departmentCount == departments.length) {
            Department[] larger = new Department[departments.length * 2];
            for (int i = 0; i < departments.length; i++) {
                larger[i] = departments[i];
            }
            departments = larger;
        }

        System.out.print("Enter Department name: ");
        String name = scanner.nextLine().trim();

        if (isExisting(departments, name)) {
            System.out.println("A department with this name already exists.");
            return;
        }

        System.out.print("Enter number of students in the department (must be positive): ");
        int numStudents = scanner.nextInt();
        scanner.nextLine();
        if (numStudents <= 0) {
            System.out.println("Number of students must be positive.");
            return;
        }

        Department department = new Department(name, numStudents);
        departments[departmentCount++] = department;
        System.out.println("Department added successfully.");
    }
    public void showAverageSalary() {
        if (lecturerCount == 0) {
            System.out.println("No lecturers available. Cannot calculate average salary.");
            return;
        }
        double totalSalaries = 0;
        for (int i = 0; i < lecturerCount; i++) {
            totalSalaries += lecturers[i].getSalary();
        }

        double averageSalaries = totalSalaries / lecturerCount;
        System.out.println("Average Salary of all lecturers: " + averageSalaries);

    }
    public void showAverageSalaryDepartment(Scanner scanner){
        if (departmentCount == 0) {
            System.out.println("There are no Departments! First add a Department.");
            return;
        }

        Department currentDepartment = printAllDepartments(scanner);
        if (currentDepartment == null) return;

        double totalSalaries = 0;
        int lecturerCount = currentDepartment.getLecturerCount();

        for (int i = 0; i < lecturerCount; i++) {
            totalSalaries += currentDepartment.getLecturers()[i].getSalary();
        }

        double averageSalaries = totalSalaries / lecturerCount;
        System.out.println("Average Salary of lecturers in " + currentDepartment.getName() + " department: " + averageSalaries);
    }
    public void printLecturers() {
        if (lecturerCount == 0) {
            System.out.println("No lecturers found.");
            return;
        }

        System.out.println("========== All Lecturers ==========");
        for (int i = 0; i < lecturerCount; i++) {
            Lecturer l = lecturers[i];

            System.out.println("Name: " + l.getName());
            System.out.println("ID: " + l.getId());
            System.out.println("Degree: " + l.getDegree());
            System.out.println("Salary: " + l.getSalary());

            // Department
            if (l.getDepartment() != null) {
                System.out.println("Department: " + l.getDepartment().getName());
            } else {
                System.out.println("Department: Not assigned");
            }

            // Committees
            if (l.getCommitteeCount() > 0) {
                System.out.print("Committees: ");
                for (int j = 0; j < l.getCommitteeCount(); j++) {
                    System.out.print(l.getCommittees()[j].getName());
                    if (j < l.getCommitteeCount() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            } else {
                System.out.println("Committees: None");
            }

            System.out.println("-----------------------------------");
        }
    }
    public void printCommittees() {
        if (committeeCount == 0) {
            System.out.println("No committees found.");
            return;
        }

        System.out.println("========== All Committees ==========");
        for (int i = 0; i < committeeCount; i++) {
            Committee c = committees[i];

            System.out.println("Committee Name: " + c.getName());

            // chairman
            if (c.getChairman() != null) {
                System.out.println("Chairperson: " + c.getChairman().getName() + " (ID: " + c.getChairman().getId() + ")");
            } else {
                System.out.println("Chairperson: Not assigned");
            }

            // Members
            if (c.getMemberCount() > 0) {
                System.out.println("Members:");
                for (int j = 0; j < c.getMemberCount(); j++) {
                    Lecturer member = c.getMembers()[j];
                    System.out.println("  - " + member.getName() + " (ID: " + member.getId() + ")");
                }
            } else {
                System.out.println("Members: None");
            }

            System.out.println("-----------------------------------");
        }
    }
    public void createCommittee(Scanner sc) throws InvalidChairmanException{
        if (committeeCount == committees.length) {
            Committee[] larger = new Committee[committees.length * 2];
            for (int i = 0; i < committees.length; i++) {
                larger[i] = committees[i];
            }
            committees = larger;
        }
        System.out.print("Enter Committee name: ");
        String name = sc.nextLine().trim();
        if (isExisting(committees,name)){ //checks if there is a committee like this
            System.out.println("A committee with this name already existing.");
            return;
        }
        System.out.println("Enter the ID of the desired chairman");
        String id = sc.nextLine();
        if (isExisting(lecturers, id)){ // checks if there is a lecturer with this id, if true means we are ok if false we cannot continue
            Lecturer currentLecturer = findLecturerById(id);
            if (!(currentLecturer.getDegree() instanceof Publishable)) {
                throw new InvalidChairmanException("Only Dr. or Prof. can be assigned as committee chairman.");
            }
            Committee newCommittee = new Committee(name,currentLecturer);
            //newCommittee.setChairman(currentLecturer);
            committees[committeeCount++] = newCommittee;
            System.out.println("Committee created successfully!\n");
            System.out.println(currentLecturer.getName() + " is now the chairman of the new committee: " + name);
        }
    }
    public void addLecturerToCommittee(Scanner scanner) throws CommitteeOperationException  {
        if (committeeCount == 0) {
            System.out.println("No committees available.");
            return;
        }

        System.out.println("Select a committee:");
        displayCommittees();

        System.out.print("Enter committee number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // clear

        if (choice < 1 || choice > committeeCount) {
            throw new CommitteeOperationException("Invalid committee selection.");
        }
        Committee selectedCommittee = committees[choice - 1];
        System.out.print("Enter lecturer ID to add: ");
        String lecturerId = scanner.nextLine();
        Lecturer lecturer = findLecturerById(lecturerId);
        if (lecturer == null) {
            throw new CommitteeOperationException("Lecturer not found.");
        }
        if (selectedCommittee.hasMember(lecturer)) {
            throw new CommitteeOperationException("Lecturer already in the committee.");
        }

        selectedCommittee.addMember(lecturer);
        lecturer.addCommittee(selectedCommittee);
        System.out.println("Lecturer " + lecturer.getName() + " was successfully added to committee " + selectedCommittee.getName() + ".");
    }
    public void updateCommitteeChairman(Scanner scanner) throws InvalidChairmanException {
        if (committeeCount == 0) {
            System.out.println("No committees available.");
            return;
        }
        System.out.println("Select a committee to update its chairman:");
        displayCommittees();
        System.out.print("Enter committee number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // clear line

        if (choice < 1 || choice > committeeCount) { //checks for out of range nums
            System.out.println("Invalid choice.");
            return;
        }
        Committee selectedCommittee = committees[choice - 1]; //open the selected committee
        System.out.println("Current chairman: " + (selectedCommittee.getChairman().getName()));
        System.out.print("Enter lecturer ID to set as the new chairman: ");
        String lecturerId = scanner.nextLine();
        Lecturer lecturer = findLecturerById(lecturerId);
        if (lecturer == null) {
            System.out.println("Lecturer not found.");
            return;
        }
        if (!(lecturer.getDegree() instanceof Publishable)) {
            throw new InvalidChairmanException("Only Dr. or Prof. can be assigned as committee chairman.");
        }

        if (selectedCommittee.hasMember(lecturer)) {
            selectedCommittee.removeMember(lecturer);
        }
        selectedCommittee.setChairman(lecturer);
        lecturer.removeCommittee(selectedCommittee);
        System.out.println("🎓 " + lecturer.getName() + " has been appointed as the new chairman of the '" +
                selectedCommittee.getName() + "' committee. Long live the new chair! 🪑👑");
    }
    private int findCommitteeByName(String name) {
        for (int i = 0; i < committeeCount; i++) {
            if (committees[i].getName().equalsIgnoreCase(name))
                return i;
        }
        return -1;
        }
    public boolean isChairmanExisting(String id) {
        for (int i = 0; i < committeeCount; i++) {
            Lecturer chairman = committees[i].getChairman();
            if (chairman != null && chairman.getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
    public void displayCommittees() {
        for (int i = 0; i < committeeCount; i++) {
            System.out.printf("%d. %s%n", i + 1, committees[i].getName());
        }
    }
    public void removeLecturerFromCommittee(Scanner scanner) {
        if (committeeCount == 0) {
            System.out.println("No committees available.");
            return;
        }

        System.out.println("Select a committee:");
        displayCommittees();

        System.out.print("Enter committee number: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // לרוקן שורה

        if (choice < 1 || choice > committeeCount) {
            System.out.println("Invalid committee number.");
            return;
        }

        Committee selectedCommittee = committees[choice - 1];

        System.out.print("Enter lecturer ID to remove: ");
        String lecturerId = scanner.nextLine();

        Lecturer lecturer = findLecturerById(lecturerId);
        if (lecturer == null) {
            System.out.println("Lecturer not found.");
            return;
        }

        if (!selectedCommittee.hasMember(lecturer)) {
            System.out.println("Lecturer is not a member of this committee.");
            return;
        }
        if (selectedCommittee.getChairman().getId().equalsIgnoreCase(lecturerId)) {
            System.out.println("Cannot remove the chairman from the committee.");
            return;
        }
        selectedCommittee.removeMember(lecturer);
        lecturer.removeCommittee(selectedCommittee);
        System.out.println("Lecturer " + lecturer.getName() + " was removed from committee " + selectedCommittee.getName());
    }
    private int readNumberOfArticles(Scanner scanner) {
        int numberOfArticles = -1;

        while (numberOfArticles <= 0) {
            System.out.print("Enter number of published articles (must be > 0): ");
            try {
                numberOfArticles = Integer.parseInt(scanner.nextLine());
                if (numberOfArticles <= 0) {
                    System.out.println("Number must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        return numberOfArticles;
    }
    private void comparePublishableLecturers(Scanner scanner) {
        System.out.print("Enter the first ID");
        String id1 = scanner.nextLine();
        Lecturer l1 = findLecturerById(id1);
        System.out.print("Enter the second ID");
        String id2 = scanner.nextLine();
        Lecturer l2 = findLecturerById(id2);
        if (l1 == null || l2 == null){
            System.out.print("One of the lecturer was not found");
            return;
        }
        if (!(l1.getDegree() instanceof Publishable)) {
            System.out.println(l1.getName() + " is not a Doctor or Professor.");
            return;
        }
        if (!(l2.getDegree() instanceof Publishable)) {
            System.out.println(l2.getName() + " is not a Doctor or Professor.");
            return;
        }
        Publishable n1 = (Publishable) l1.getDegree();
        Publishable n2 = (Publishable) l2.getDegree();
        int o1 = n1.getPublicationCount();
        int o2 = n2.getPublicationCount();
        if (o1 > o2) {
            System.out.println(l1.getName() + " has more articles (" + o1 + ") than " + l2.getName() + " (" + o2 + ")");
        } else if (o2 > o1) {
            System.out.println(l2.getName() + " has more articles (" + o2 + ") than " + l1.getName() + " (" + o1 + ")");
        } else {
            System.out.println("Both have the same number of articles (" + o1 + ")");
        }
    }
    private void compareDepartments(Scanner scanner) {
        // This method will compare departments based on:
        // 1. Number of staff members
        // 2. Total number of articles by committee members
    }
    }

    


