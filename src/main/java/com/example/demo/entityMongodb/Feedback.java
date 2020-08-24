package com.example.demo.entityMongodb;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "feedback")
public class Feedback {

    @Id
    //private ObjectId id;
    private String id;
    private String subject;
    private String message;
    private String name;
    private String contactNumber;
    private String email;
    private String receivedDate;

                    //CONSTRUCTORS
    /*public Feedback(String subject, String message, String name, String contactNumber, String email, String receivedDate) {
        this.subject = subject;
        this.message = message;
        this.name = name;
        this.contactNumber = contactNumber;
        this.email = email;
        this.receivedDate = receivedDate;
    }*/
    public Feedback() {
    }

                                            //GETTERS AND SETTERS
        //if i was using ObjectId, then i must convert it to String
        //but i use String id as ID, and it's working... so this commented lines stays as example
    //public String getId() { return id.toHexString(); }
    //public void setId(ObjectId id) { this.id = id; }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getReceivedDate() {
        return receivedDate;
    }
    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }


                                //BUILDER PATTERN
    public static class FeedbackBuilder{
         private String id;
         private String subject;
         private String message;
         private String name;
         private String contactNumber;
         private String email;
         private String receivedDate;

                                //constructor
         public FeedbackBuilder(){}

         public static FeedbackBuilder anFeedback(){
             return new FeedbackBuilder();
         }

                                //with for all fields
         public FeedbackBuilder withId(String id){
             this.id = id;
             return this;
         }
         public FeedbackBuilder withSubject(String subject){
             this.subject = subject;
             return this;
         }
         public FeedbackBuilder withMessage(String message){
             this.message = message;
             return this;
         }
         public FeedbackBuilder withName(String name){
             this.name = name;
             return this;
         }
         public FeedbackBuilder withcontactNumber(String contactNumber) {
             this.contactNumber = contactNumber;
             return this;
         }
         public FeedbackBuilder withEmail(String email){
             this.email = email;
             return this;
         }
         public FeedbackBuilder withReceivedDate(String receivedDate){
             this.receivedDate = receivedDate;
             return this;
         }

                                //constructor/method named "build()" of Main Class Feedback
         public Feedback build(){
             Feedback feedback = new Feedback();
                feedback.id = this.id;
                feedback.subject = this.subject;
                feedback.message = this.message;
                feedback.name = this.name;
                feedback.contactNumber = this.contactNumber;
                feedback.email = this.email;
                feedback.receivedDate = this.receivedDate;

             return feedback;
         }
    }


                                //EQUALS AND HASHCODE
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feedback feedback = (Feedback) o;

        if (id != null ? !id.equals(feedback.id) : feedback.id != null) return false;
        if (subject != null ? !subject.equals(feedback.subject) : feedback.subject != null) return false;
        if (message != null ? !message.equals(feedback.message) : feedback.message != null) return false;
        if (name != null ? !name.equals(feedback.name) : feedback.name != null) return false;
        if (contactNumber != null ? !contactNumber.equals(feedback.contactNumber) : feedback.contactNumber != null)
            return false;
        if (email != null ? !email.equals(feedback.email) : feedback.email != null) return false;
        return receivedDate != null ? receivedDate.equals(feedback.receivedDate) : feedback.receivedDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (contactNumber != null ? contactNumber.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (receivedDate != null ? receivedDate.hashCode() : 0);
        return result;
    }
}
