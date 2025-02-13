package prv.felix.javacourses.exporting;

import prv.felix.javacourses.entities.JavaCourse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class ExportCsv {

    public void exportJavaCoursesToCsv(List<JavaCourse> courses, Path path) throws IOException {
        List<String> lines = courses.stream()
                .map(this::javaCourseToString)
                .collect(Collectors.toList());
        lines.addFirst("id,name,description,duration,participants,cost,type");

        Files.write(path, lines);
    }

    private String javaCourseToString(JavaCourse javaCourse) {
        return String.join(",",
                javaCourse.getUuid().toString(),
                javaCourse.getCourseName(),
                javaCourse.getDescription(),
                String.valueOf(javaCourse.getDurationInHours()),
                String.valueOf(javaCourse.getMaxParticipants()),
                String.valueOf(javaCourse.getCostInEuros()),
                javaCourse.getCourseTyp().toString()
        );
    }

}
