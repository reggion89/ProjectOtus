package data;

public enum Courses {
    WEBQA("avtomatizaciya-web-testirovaniya", "Python QA Engineer", "Автоматизация тестирования на Python", "Длительность обучения:\n"+"5 месяцев ,  4 ак. часа в нед.", "Формат:\n"+"Online");


    private String path;
    private String title;
    private String description;
    private String duration;
    private String format;

    Courses(String path, String header, String description, String duration, String format) {
        this.path = path;
        this.title = header;
        this.description = description;
        this.duration = duration;
        this.format = format;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDuration() {
        return duration;
    }

    public String getFormat() {
        return format;
    }
}
