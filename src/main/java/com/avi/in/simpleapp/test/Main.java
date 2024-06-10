package com.avi.in.simpleapp.test;

import com.resend.Resend;
import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.SendEmailRequest;
import com.resend.services.emails.model.SendEmailResponse;
import org.modelmapper.ModelMapper;

import java.io.IOException;

//
//public class Main {
//
//
//    /*public static void main(String[] args) {
//
//        Resend resend = new Resend("123");
//
////        String fileContent = null;
////        try {
////            fileContent = FileUtils.encodeFileToBase64("invoice.pdf");
////        } catch (IOException e) {
////            throw new RuntimeException(e);
////        }
//
//
////        Attachment att = Attachment.builder()
////                .fileName("invoice.pdf")
////                .content(fileContent)
////                .build();
//
//        SendEmailRequest sendEmailRequest = SendEmailRequest.builder()
//                .from("Acme <onboarding@resend.dev>")
//                .to("demo@gmail.com")
//                //.attachments(att)
//                .html("<strong>It works!</strong")
//                .subject("Hello from Java!")
//                .build();
//
//        SendEmailResponse data = resend.emails().send(sendEmailRequest);
//
//        System.out.println(data.getId());
//
//    }*/
//
//
//}
// https://github.com/resend/resend-java-example?tab=readme-ov-file


class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
// constructor, getters, and setters
}

class UserDTO {
    private String fullName;
    private int userAge;


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }
// constructor, getters, and setters
}

public class Main {
    public static void main(String[] args) {
        User user = new User("John", 30);
        ModelMapper modelMapper = new ModelMapper();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        System.out.println(userDTO.getFullName()); // Output: John
        System.out.println(userDTO.getUserAge()); // Output: 30
    }
}