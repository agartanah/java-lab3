package org.example;

import java.awt.*;
import java.util.*;
import java.util.List;

class PrimesGenerator {
    List<Integer> primeNumbers = new LinkedList<>();
    Integer countElements;

    public final ListIterator<Integer> iteratorPrimeNumbers;

    public Integer getCountElements() {
        return countElements;
    }

    public void setCountElements(Integer countElements) {
        this.countElements = countElements;
    }

    PrimesGenerator(Integer N, Integer lowerBound, Integer upperBound) {
        countElements = N;

        for (int index = 0; index < N; ++index) {
            primeNumbers.add(getPrimeNumber(lowerBound, upperBound));
        }

        iteratorPrimeNumbers = primeNumbers.listIterator();
    }

    private Integer getPrimeNumber(Integer lowerBound, Integer upperBound) {
        Random random = new Random();
        int primeNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        boolean isPrime = false;

        while (!isPrime(primeNumber)) {
            primeNumber = random.nextInt(upperBound - lowerBound + 1) + lowerBound;
        }

        return primeNumber;
    }

    private boolean isPrime(int primeNumber) {
        double sqrtNumber = Math.sqrt(primeNumber);

        if (primeNumber == 2) {
            return true;
        }

        if (primeNumber > 1) {
            for (int number = 2; number <= sqrtNumber; ++number) {
                if (primeNumber % number == 0) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
}

class PrimesGeneratorTest {
    PrimesGenerator primesGenerator;
    int countFirstElements;

    PrimesGeneratorTest(PrimesGenerator primesGenerator, int countFirstElements) {
        this.primesGenerator = primesGenerator;

        if (countFirstElements <= primesGenerator.countElements) {
            this.countFirstElements = countFirstElements;
        } else {
            throw new IndexOutOfBoundsException("The entered number is outside the list");
        }
    }

    public void displayNumbers() {
        ListIterator<Integer> iterator = primesGenerator.iteratorPrimeNumbers;

        for (int index = 0; index < countFirstElements; ++index) {
            System.out.println("Direct: " + iterator.next());
        }

        for (int index = 0; index < countFirstElements; ++index) {
            System.out.println("Reverse: " + iterator.previous());
        }
    }
}

class Human implements Comparable<Human> {
    String firstName;
    String lastName;
    Integer age;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    Human(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    @Override
    public int compareTo(Human o) {
        return this.firstName.compareTo(o.firstName);
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + age;
    }
}

class HumanComparatorByLastName implements Comparator<Human> {
    @Override
    public int compare(Human o1, Human o2) {
        return o1.getLastName().compareTo(o2.getLastName());
    }
}

class EnglishString {
    String englishString;
    List<String> words;

    public List<String> getListWords() {
        return words;
    }

    EnglishString(String string) {
        this.englishString = string;
        words = stringToWords();
    }

    List<String> stringToWords() {
        words = new LinkedList<>();
        Collections.addAll(words, englishString.split(" "));


        return words;
    }

    public Map<String, Integer> frequencyWords() {
        Map<String, Integer> wordsMap = new HashMap<>(words.size()); // мапа слов и их количества
        System.out.println(words);

        Set<String> uniqWords = new TreeSet<>(Comparator.comparing(String::toLowerCase)); // уникальные слова без учёта регистра
        words.forEach(string -> uniqWords.add(string.toLowerCase())); // привести уникальные слова к нижнему регистру
        System.out.println(uniqWords);

        List<String> wordsLowerCase = new LinkedList<>();
        words.forEach(string -> wordsLowerCase.add(string.toLowerCase())); // копия списка слов в нижнем регистре
        System.out.println(wordsLowerCase);

        for (String element : uniqWords) {
            wordsMap.put(element, Collections.frequency(wordsLowerCase, element));
            // В мапу добавляется ключ-уникальная-строка из списка уникальных слов
            // и значение-частота по списку слов в нижнем регистре
        }

        return wordsMap;
    }
}

public class Main {
    public static <K, V> Map<V, K> reverseMap(Map<K, V> directMap) {
        Map<V, K> reverseMap = new HashMap<>();

        for (Map.Entry<K, V> entry : directMap.entrySet()) {
            if (!reverseMap.containsKey(entry.getValue())) {
                reverseMap.put(entry.getValue(), entry.getKey());
            }
        }

        return reverseMap;
    }

    public static void main(String[] args) {
        Integer[] arrayNumbers = new Integer[100];
        Random random = new Random();

        for (int index = 0; index < arrayNumbers.length; ++index) {
            arrayNumbers[index] = random.nextInt(arrayNumbers.length + 1);
            System.out.println(arrayNumbers[index]);
        }

        List<Integer> listNumbers = new ArrayList<>(arrayNumbers.length);

        Collections.addAll(listNumbers, arrayNumbers);

        Collections.sort(listNumbers);
        Collections.reverse(listNumbers);

        Collections.shuffle(listNumbers);

        Collections.rotate(listNumbers, 1);

        Set<Integer> uniqElements = new LinkedHashSet<>();
//        Collections.addAll(hashSet, arrayNumbers); // hashSet список уникальный элементов arraynumbers
//        hashSet.iterator().next();

        for (Integer element : listNumbers) {
            if (Collections.frequency(listNumbers, element) == 1) {
                uniqElements.add(element);
            }
        }
        uniqElements.iterator().next();

        Set<Integer> duplicateElements = new LinkedHashSet<>();

        for (Integer element : listNumbers) {
            if (Collections.frequency(listNumbers, element) > 1) {
                duplicateElements.add(element);
            }
        }
        duplicateElements.iterator().next();

        Integer[] arrayFromList = listNumbers.toArray(new Integer[0]);
        Map<Integer, Integer> countNumberList = new HashMap<>(150);

        for (Integer element : arrayFromList) {
            if (!countNumberList.containsKey(element)) {
                countNumberList.put(element, 1);
            } else {
                countNumberList.put(element, countNumberList.get(element) + 1);
            }
        }
        System.out.println(countNumberList);

        PrimesGeneratorTest primesGeneratorTest = new PrimesGeneratorTest(
                new PrimesGenerator(100, 0, 100),
                10
        );

        primesGeneratorTest.displayNumbers();


        List<Human> listHuman = new LinkedList<Human>();
        listHuman.add(new Human("Honey", "Mad", 40));
        listHuman.add(new Human("Joe", "Peach", 100));
        listHuman.add(new Human("Iliyuha", "Maddyson", 43));
        listHuman.add(new Human("Ilya", "Maddyson", 22));
        listHuman.add(new Human("Mad", "Highlights", 230));
        System.out.println(listHuman);

        // Здесь содержимое listHuman распределяется по бакетам Хэш-таблицы
        Set<Human> hashSetHuman = new HashSet<>(listHuman);
        System.out.println(hashSetHuman);

        // Здесь элементы расположены исходя из порядка их добавления в список
        Set<Human> linkedHashSetHuman = new LinkedHashSet<>(listHuman);
        System.out.println(linkedHashSetHuman);

        // Здесь элементы сортируются по Comparable методу compareTo, который я переопределил в классе Human
        Set<Human> treeSetHuman = new TreeSet<>(listHuman);
        System.out.println(treeSetHuman);

        // Здесь в конструктор множества TreeSet я вписал свой класс компаратор,
        // который сравнивает хуманов по фамилиям. Получилось что тут элементы идут по возрастанию фамилий
        Set<Human> treeSetHumanEmpty = new TreeSet<>(new HumanComparatorByLastName());
        treeSetHumanEmpty.addAll(listHuman);
        System.out.println(treeSetHumanEmpty);

        // Так как здесь я создал новый компаратор по возрасту, элементы будут иди в порядке
        // возрастания возраста
        Set<Human> treeSetHumanAge = new TreeSet<>(Comparator.comparing(o -> o.age));
        treeSetHumanAge.addAll(listHuman);
        System.out.println(treeSetHumanAge);

        EnglishString string = new EnglishString("BRUH bruh barah BrUH bRuh getero etero GETERO");
        System.out.println(string.getListWords());
        System.out.println(string.frequencyWords());

        Map<String, Integer> mapa = new HashMap<>();
        mapa.put("bruh1", 3);
        mapa.put("bruh2", 1);
        mapa.put("bruh3", 1);
        mapa.put("bruh3", 3);
        mapa.put("bruh", 4);

        System.out.println(reverseMap(mapa));
//        var a = new LinkedList<String>();
//        a.add("Amy");
//        a.add("Carl");
//        a.add("Erica");
//
//        var b = new LinkedList<String>();
//        b.add("Bob");
//        b.add("Doug");
//        b.add("Frances");
//        b.add("Gloria");
//
//        ListIterator<String> alter = a.listIterator();
//        Iterator<String> bIter = b.iterator();
//
//        while (bIter.hasNext()) {
//            if (alter.hasNext()) alter.next();
//            alter.add(bIter.next());
//        }
//
//        System.out.println(a);
//
//        bIter = b.iterator();
//        while (bIter.hasNext()) {
//            bIter.next(); //пропустить один элемент
//
//            if (bIter.hasNext()) {
//                bIter.next(); //пропустить следующий элемент
//                bIter.remove(); //удалить данный элемент
//            }
//        }
//
//        System.out.println(b);
//
//        a.removeAll(b);
//
//        System.out.println(a);

//        var listik = new LinkedList<String>();
//        Iterator<String> iterator = listik.listIterator();
//
//        var staff = new LinkedList<String>();
//        staff.add ("Ату");
//        staff.add("Bob");
//        staff.add("Carl");
//        ListIterator<String> bIter = staff.listIterator();
//        String str = bIter.next();
//        bIter.add("Juliet");
    }
}