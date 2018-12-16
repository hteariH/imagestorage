package com.mamoru.imagestorage.dto;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;

@Document(collection = "file")
public class File {

    @Id
    String id;

    String name;

    byte[] file;


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

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public File() {

    }

    public File(String id, String name, byte[] file) {
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
