package ru.avk.workers;

public abstract class Worker implements Comparable<Worker>{
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

    @Override
    public int compareTo(Worker worker) {
        return lastName.compareTo(worker.lastName);
    }

    public String toString() {
        return String
                .format("Имя: %s\nОтчество: %s\nФамилия: %s\nДолжность: %s\nЗарплата: %.2f\n",
                firstName, patronymic, lastName, specialization, getSalary());
    }
}
