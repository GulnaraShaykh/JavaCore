package streamAPI.task2;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();

        Random random = new Random();

        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(random.nextInt(names.size())),
                    families.get(random.nextInt(families.size())),
                    random.nextInt(100),
                    Sex.values()[random.nextInt(Sex.values().length)],
                    Education.values()[random.nextInt(Education.values().length)])
            );
        }

        // Задание 1: Найти количество несовершеннолетних
        long underageCount = persons.stream()
                .filter(person -> person.getAge() < 18)
                .count();

        System.out.println("Количество несовершеннолетних: " + underageCount);

        // Задание 2: Получить список фамилий призывников
        List<String> conscripts = persons.stream()
                .filter(person -> person.getSex() == Sex.MAN && person.getAge() >= 18 && person.getAge() <= 27)
                .map(Person::getFamily)
                .collect(Collectors.toList());

        System.out.println("Фамилии призывников: " + conscripts);

        // Задание 3: Получить отсортированный по фамилии список потенциально работоспособных людей
        List<Person> potentiallyEmployable = persons.stream()
                .filter(person -> person.getEducation() == Education.HIGHER &&
                        ((person.getSex() == Sex.WOMAN && person.getAge() >= 18 && person.getAge() <= 60) ||
                                (person.getSex() == Sex.MAN && person.getAge() <= 65)))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList());

        System.out.println("Отсортированный список потенциально работоспособных людей: " + potentiallyEmployable);
    }
}