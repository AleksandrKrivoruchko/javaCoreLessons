package ru.avk.workers;

public abstract class Worker {
    protected String firstName;
    protected  String lastName;
    protected String patronymic;
    protected String specialization;

    public Worker(String firstName, String patronymic,
                  String lastName, String specialization) {
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.lastName = lastName;
        this.specialization = specialization;
    }

    public abstract double getSalary();
}
