package ru.avk.workers;

public class FixedRateEmployee extends Worker{
    private final double salary;
    public FixedRateEmployee(String firstName, String patronymic,
                             String lastName, String specialization,
                             double salary) {
        super(firstName, patronymic, lastName, specialization);
        this.salary = salary;
    }

    @Override
    public double getSalary() {
        return salary;
    }
}
