public class Professor extends Degree implements Publishable {
    private int publicationCount;
    private String grantingBody;

    public Professor(int publicationCount, String grantingBody) {
        this.publicationCount = publicationCount;
        this.grantingBody = grantingBody;
    }

    @Override
    public int getPublicationCount() {
        return publicationCount;
    }

    @Override
    public String getTitle() {
        return "Prof.";
    }

    public String getGrantingBody() {
        return grantingBody;
    }
}
