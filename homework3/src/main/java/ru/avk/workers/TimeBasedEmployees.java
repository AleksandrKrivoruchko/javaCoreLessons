package ru.avk.workers;

public class TimeBasedEmployees extends Worker{
    private final double hourlyRate;
    public TimeBasedEmployees(String firstName, String patronymic,
                              String lastName, String specialization,
                              double hourlyRate) {
        super(firstName, patronymic, lastName, specialization);
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double getSalary() {
        double HOURS_COUNT = 8.0;
        double DAYS_COUNT = 20.8;
        return DAYS_COUNT * HOURS_COUNT * hourlyRate;
    }
}
