import java.util.*;

public class Notebook {
    private String model;
    private int ram;
    private int storage;
    private String operatingSystem;
    private String color;

    public Notebook(String model, int ram, int storage, String operatingSystem, String color) {
        this.model = model;
        this.ram = ram;
        this.storage = storage;
        this.operatingSystem = operatingSystem;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getStorage() {
        return storage;
    }

    public String getOperatingSystem() {
        return operatingSystem;
    }

    public String getColor() {
        return color;
    }

    public static void main(String[] args) {
        Set<Notebook> notebooks = new HashSet<>();
        notebooks.add(new Notebook("Notebook 1", 8, 256, "Windows", "Black"));
        notebooks.add(new Notebook("Notebook 2", 16, 512, "macOS", "Silver"));
        notebooks.add(new Notebook("Notebook 3", 8, 512, "Windows", "Silver"));
        notebooks.add(new Notebook("Notebook 4", 16, 1024, "Windows", "Black"));
        notebooks.add(new Notebook("Notebook 5", 16, 512, "macOS", "Black"));

        Scanner scanner = new Scanner(System.in);

        System.out.println("Приветствие!");
        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int criterion = scanner.nextInt();
        scanner.nextLine();

        Map<Integer, Object> filters = new HashMap<>();

        switch (criterion) {
            case 1:
                System.out.println("Введите минимальное значение ОЗУ:");
                int minRam = scanner.nextInt();
                filters.put(1, minRam);
                break;
            case 2:
                System.out.println("Введите минимальный объем ЖД:");
                int minStorage = scanner.nextInt();
                filters.put(2, minStorage);
                break;
            case 3:
                System.out.println("Введите требуемую операционную систему:");
                String os = scanner.nextLine();
                filters.put(3, os);
                break;
            case 4:
                System.out.println("Введите требуемый цвет:");
                String color = scanner.nextLine();
                filters.put(4, color);
                break;
            default:
                System.out.println("Некорректный выбор критерия.");
                return;
        }

        List<Notebook> filteredNotebooks = filterNotebooks(notebooks, filters);

        if (filteredNotebooks.isEmpty()) {
            System.out.println("Нет ноутбуков, удовлетворяющих выбранным критериям.");
        } else {
            System.out.println("Подходящие ноутбуки:");
            for (Notebook notebook : filteredNotebooks) {
                System.out.println(notebook.getModel());
            }
        }
    }

    private static List<Notebook> filterNotebooks(Set<Notebook> notebooks, Map<Integer, Object> filters) {
        List<Notebook> filteredNotebooks = new ArrayList<>();

        for (Notebook notebook : notebooks) {
            boolean passFilters = true;

            for (Map.Entry<Integer, Object> entry : filters.entrySet()) {
                int criterion = entry.getKey();
                Object value = entry.getValue();

                switch (criterion) {
                    case 1:
                        if (notebook.getRam() < (int) value) {
                            passFilters = false;
                        }
                        break;
                    case 2:
                        if (notebook.getStorage() < (int) value) {
                            passFilters = false;
                        }
                        break;
                    case 3:
                        if (!notebook.getOperatingSystem().equalsIgnoreCase((String) value)) {
                            passFilters = false;
                        }
                        break;
                    case 4:
                        if (!notebook.getColor().equalsIgnoreCase((String) value)) {
                            passFilters = false;
                        }
                        break;
                }

                if (!passFilters) {
                    break;
                }
            }

            if (passFilters) {
                filteredNotebooks.add(notebook);
            }
        }

        return filteredNotebooks;
    }
}
