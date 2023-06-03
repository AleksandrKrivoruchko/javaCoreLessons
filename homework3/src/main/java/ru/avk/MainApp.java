package ru.avk;

import ru.avk.workers.FixedRateEmployee;
import ru.avk.workers.TimeBasedEmployees;
import ru.avk.workers.Worker;

public class MainApp {
    public static void main(String[] args) {
        Worker worker = new FixedRateEmployee("Сергей", "Иванович",
                "Петренко", "разнорабочий", 20000);
        Worker worker1 = new TimeBasedEmployees("Степан", "Петрович",
                "Иванов", "электрик", 200);
        System.out.println(worker.getSalary());
        System.out.println(worker1.getSalary());
    }
}
