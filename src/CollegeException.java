// Base exception class for all project-specific exceptions
public class CollegeException extends Exception {
  public CollegeException(String message) {
    super(message);
  }
}
// Exception thrown when trying to add an invalid lecturer (e.g., empty ID)
class InvalidLecturerException extends CollegeException {
  public InvalidLecturerException(String message) {
    super(message);
  }
}

// Exception thrown when a lecturer with the same ID already exists
class LecturerAlreadyExistsException extends CollegeException {
  public LecturerAlreadyExistsException(String message) {
    super(message);
  }
}

// Exception thrown when assigning a chairman who is not a Dr. or Prof.
class InvalidChairmanException extends CollegeException {
  public InvalidChairmanException(String message) {
    super(message);
  }
}

// Exception thrown when a lecturer is already a member of a committee
class LecturerAlreadyInCommitteeException extends CollegeException {
  public LecturerAlreadyInCommitteeException(String message) {
    super(message);
  }
}

// Exception thrown when a lecturer is not found by ID, needs to be added to the method findlecturerbyid
class LecturerNotFoundException extends CollegeException {
  public LecturerNotFoundException(String message) {
    super(message);
  }
}

class CommitteeOperationException extends CollegeException {
  public CommitteeOperationException(String message) {
    super(message);
  }
}