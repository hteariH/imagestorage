package com.mamoru.imagestorage.dto;

//import org.springframework.data.mongodb.core.mapping.Document;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Arrays;

//@Document(collection = "file")
@Entity(name = "file")
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String name;

    @Column(columnDefinition = "LONGBLOB")
    byte[] file;

    String contentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public File() {

    }

    public File(Long id, String name, byte[] file) {
        this.id = id;
        this.name = name;
        this.file = file;
    }

    @Override
    public String toString() {
        return "File{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", file=" + Arrays.toString(file) +
                '}';
    }
}
