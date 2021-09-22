package Interview;

public class Listitem_subject_name {
    private String subject;
    private String number;
    private String image;

    public Listitem_subject_name(String subject,String number,String image) {
        this.subject = subject;
        this.number = number;
        this.image = image;
    }

    public String getSubject() {
        return subject;
    }

    public  String getNumber(){return  number;}

    public  String getImage(){return  image;}
}
