import java.util.*;
import java.util.stream.Collectors;

class Notebook {
    private String model;
    private int ram; // ОЗУ (в гигабайтах)
    private int storage; // Объем ЖД (в гигабайтах)
    private String os; // Операционная система
    private String color;

    public Notebook(String model, int ram, int storage, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.os = os;
        this.color = color;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Model: " + model + ", RAM: " + ram + "GB, Storage: " + storage + "GB, OS: " + os + ", Color: " + color;
    }
}

public class NotebookStore {
    public static void main(String[] args) {
        List<Notebook> notebooks = new ArrayList<>();
        notebooks.add(new Notebook("Lenovo", 8, 512, "Windows 10", "Black"));
        notebooks.add(new Notebook("HP", 16, 256, "Windows 11", "Silver"));
        notebooks.add(new Notebook("Dell", 8, 1000, "Ubuntu", "Black"));
        notebooks.add(new Notebook("Asus", 12, 512, "Windows 10", "White"));

        Map<Integer, String> criteria = new HashMap<>();
        criteria.put(1, "RAM");
        criteria.put(2, "Storage");
        criteria.put(3, "OS");
        criteria.put(4, "Color");

        Map<String, Object> filter = new HashMap<>();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите критерии фильтрации (1 - RAM, 2 - Storage, 3 - OS, 4 - Color):");
        int criteriaCount = scanner.nextInt();

        for (int i = 0; i < criteriaCount; i++) {
            System.out.println("Введите номер критерия: ");
            int criterion = scanner.nextInt();

            switch (criteria.get(criterion)) {
                case "RAM":
                    System.out.println("Минимальный объем ОЗУ (GB): ");
                    int minRam = scanner.nextInt();
                    filter.put("RAM", minRam);
                    break;
                case "Storage":
                    System.out.println("Минимальный объем ЖД (GB): ");
                    int minStorage = scanner.nextInt();
                    filter.put("Storage", minStorage);
                    break;
                case "OS":
                    System.out.println("Операционная система: ");
                    String os = scanner.next();
                    filter.put("OS", os);
                    break;
                case "Color":
                    System.out.println("Цвет: ");
                    String color = scanner.next();
                    filter.put("Color", color);
                    break;
            }
        }

        List<Notebook> filteredNotebooks = filterNotebooks(notebooks, filter);
        for (Notebook notebook : filteredNotebooks) {
            System.out.println(notebook);
        }
    }

    public static List<Notebook> filterNotebooks(List<Notebook> notebooks, Map<String, Object> filter) {
        return notebooks.stream()
                .filter(notebook -> {
                    if (filter.containsKey("RAM")) {
                        int minRam = (int) filter.get("RAM");
                        if (notebook.getRam() < minRam) return false;
                    }
                    if (filter.containsKey("Storage")) {
                        int minStorage = (int) filter.get("Storage");
                        if (notebook.getStorage() < minStorage) return false;
                    }
                    if (filter.containsKey("OS")) {
                        String os = (String) filter.get("OS");
                        if (!notebook.getOs().equalsIgnoreCase(os)) return false;
                    }
                    if (filter.containsKey("Color")) {
                        String color = (String) filter.get("Color");
                        if (!notebook.getColor().equalsIgnoreCase(color)) return false;
                    }
                    return true;
                })
                .collect(Collectors.toList());
    }
}
