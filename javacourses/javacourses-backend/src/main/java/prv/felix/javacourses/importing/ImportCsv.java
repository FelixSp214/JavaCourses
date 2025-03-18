package prv.felix.javacourses.importing;

import prv.felix.javacourses.entities.JavaCourse;
import prv.felix.javacourses.enums.CourseType;
import prv.felix.javacourses.enums.DBState;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ImportCsv {

    public List<JavaCourse> importCoursesFromCsv(String filePath) throws IOException {
        Path path = Path.of(filePath);
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            return lines.skip(1).map(this::parseJavaCourse)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new IOException("Error while import From Csv", e);
        }
    }

    private JavaCourse parseJavaCourse(String line) {
        String[] parts = line.split(",");
        return new JavaCourse(UUID.fromString(parts[0]), parts[1], parts[2],
                Integer.parseInt(parts[3]), Integer.parseInt(parts[4]),
                Double.parseDouble(parts[5]), CourseType.valueOf(parts[6]), DBState.valueOf(parts[7]));
    }
}