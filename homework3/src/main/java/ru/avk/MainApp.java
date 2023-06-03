package ru.avk;

import ru.avk.workers.ArrayWorkers;
import ru.avk.workers.FixedRateEmployee;
import ru.avk.workers.TimeBasedEmployees;
import ru.avk.workers.Worker;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        Worker worker = new FixedRateEmployee("Сергей", "Иванович",
                "Петренко", "разнорабочий", 20000);
        Worker worker1 = new TimeBasedEmployees("Степан", "Петрович",
                "Иванов", "электрик", 200);
        Worker worker2 = new FixedRateEmployee("Иван", "Семенович",
                "Абрамов", "инженер", 50000);
        Worker worker3 = new TimeBasedEmployees("Петр", "Сергеевич",
                "Яшин", "программист", 1000);
        Worker[] workers = new Worker[] {worker, worker1, worker2, worker3};
        ArrayWorkers aw = new ArrayWorkers(workers);

        for (Worker w : workers) {
            System.out.println(w);
        }
        System.out.println("*******************************");
        Arrays.sort(workers);
        for (Worker w : aw) {
            System.out.println(w);
        }
    }
}
