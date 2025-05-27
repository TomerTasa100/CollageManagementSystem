public class Committee {
    private String name;
    private Lecturer[] members;
    private int memberCount;
    private Lecturer chairman;

    public Committee(String name, Lecturer chairman) {
        this.name = name;
        this.chairman = chairman;
        this.members = new Lecturer[2];
        this.memberCount = 0;
        //addMember(chairman); // chairman must be committee member
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lecturer[] getMembers() {
        return members;
    }

    public void setMembers(Lecturer[] members) {
        this.members = members;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public Lecturer getChairman() {
        return chairman;
    }

    public void setChairman(Lecturer chairman) {
        this.chairman = chairman;
    }

    public boolean hasMember(Lecturer lecturer) {
        for (int i = 0; i < memberCount; i++) {
            if (members[i] != null && members[i].getId().equalsIgnoreCase(lecturer.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean addMember(Lecturer lecturer) {
        if (hasMember(lecturer)) {
            return false; // המרצה כבר נמצא
        }

        if (memberCount >= members.length) {
            // מגדילים את המערך (כפול 2)
            Lecturer[] newMembers = new Lecturer[members.length * 2];
            System.arraycopy(members, 0, newMembers, 0, members.length);
            members = newMembers;
        }

        members[memberCount++] = lecturer;
        return true;
    }
    //removing member from the committee+ updatding the counter of the member (-1).
    public void removeMember(Lecturer lecturer) {

        if (lecturer.getId().equalsIgnoreCase(chairman.getId())) {
            chairman = null;
            System.out.println("The chairman has been removed from the committee.");
            return;
        }
        for (int i = 0; i < memberCount; i++) {
            if (members[i] != null && members[i].getId().equalsIgnoreCase(lecturer.getId())) {
                // הזזה שמאלה
                for (int j = i; j < memberCount - 1; j++) {
                    members[j] = members[j + 1];
                }
                members[--memberCount] = null; // מנקה את האחרון
                return;
            }
        }
        System.out.println("The lecturer is not part of the committee.");
    }
}