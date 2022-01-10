package domain;

import json.*;

import java.util.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    List<Tuple<String, Integer>> exams;
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        List<Tuple<String, Integer>> examGrades = new ArrayList<>(Arrays.asList(exams));
        this.exams = examGrades;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonObject student = new JsonObject();
        Json[] examArray = new Json[exams.size()];
        for (int i = 0; i < exams.size(); i++){
            Tuple<String, Integer> e = exams.get(i);
            JsonObject exam = new JsonObject(new JsonPair("course", new JsonString(e.key)),
                    new JsonPair("mark", new JsonNumber(e.value)));

            boolean pass;
            if (e.value >= 3){
                pass = true;
            } else {
                pass = false;
            }

            exam.add(new JsonPair("passed", new JsonBoolean(pass)));
            examArray[i] = exam;
        }
        student.add(new JsonPair("name", new JsonString(this.name)));
        student.add(new JsonPair("surname", new JsonString(this.surname)));
        student.add(new JsonPair("year", new JsonNumber(this.year)));
        student.add(new JsonPair("exams", new JsonArray(examArray)));
        return student;
    }
}