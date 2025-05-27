public class Doctor extends Degree implements Publishable {
    private int publicationCount;

    public Doctor(int publicationCount) {
        this.publicationCount = publicationCount;
    }

    @Override
    public int getPublicationCount() {
        return publicationCount;
    }

    @Override
    public String getTitle() {
        return "Dr.";
    }
}