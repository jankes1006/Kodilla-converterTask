package com.kodilla.convertertask.controller;

import com.kodilla.convertertask.model.EvaluationSheetStudent;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class SlashConverter implements HttpMessageConverter<Object> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return clazz.getName().equals("com.kodilla.convertertask.model.EvaluationSheetStudent") &&
                mediaType.getSubtype().equals("plain") && mediaType.getType().equals("text");
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.getName().equals("com.kodilla.convertertask.model.EvaluationSheetStudent") &&
                mediaType.getSubtype().equals("plain") && mediaType.getType().equals("text");
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return List.of(MediaType.ALL);
    }

    @Override
    public Object read(Class<?> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        StringBuilder builder = new StringBuilder();

        try (Reader reader = new BufferedReader(
                new InputStreamReader(
                        inputMessage.getBody(),
                        Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;

            while ((c = reader.read()) != -1)
                builder.append((char) c);
        }
        return prepareEvaluationSheetStudent(builder.toString());
    }

    private EvaluationSheetStudent prepareEvaluationSheetStudent(String message) {
        String[] evaluationSheetArray = message.split("/");

        List<String> basicData = List.of(evaluationSheetArray[0].split("&"));
        List<String> lessons = List.of(evaluationSheetArray[1].split("&"));
        List<String> marks = List.of(evaluationSheetArray[2].split("&"));

        return new EvaluationSheetStudent(basicData.get(0), basicData.get(1), prepareEvaluationSheet(lessons, marks));
    }

    private HashMap<String, String> prepareEvaluationSheet(List<String> lessons, List<String> marks) {
        if (lessons.size() != marks.size()) {
            throw new IllegalArgumentException();
        }
        HashMap<String, String> markHashMap = new HashMap<>();
        for (int i = 0; i < lessons.size(); i++) {
            markHashMap.put(lessons.get(i), marks.get(i));
        }
        return markHashMap;
    }

    @Override
    public void write(Object o, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {

    }
}
