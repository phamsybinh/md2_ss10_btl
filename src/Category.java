import java.util.Scanner;

public class Category implements IEntity {
    private int id;
    private String name;
    private boolean status;
    private static final Scanner scanner = new Scanner(System.in);

    public Category() {
    }

    public Category(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    private boolean checkCurrentId(int id) {
        for (Category category : CategoryService.getInstance().getListCategory()) {
            return category.getId() == id;
        }
        return false;
    }
    private boolean checkCurrentName(String name){
        for (Category category : CategoryService.getInstance().getListCategory()) {
            return category.getName().equalsIgnoreCase(name);
        }
        return false;
    }
    private void setIdWithValidation(){
        boolean validInput = false;
        do {
            try {
                System.out.println("Nhap vao ma the loai (so nguyen lon hon 0 va duy nhat): ");
                id = Integer.parseInt(scanner.nextLine());
                if (id > 0 && !checkCurrentId(id)) {
                    validInput = true;
                } else {
                    System.out.println("Mã thể loại không hợp lệ. Vui lòng nhập lại.");
                }
            } catch (NumberFormatException exception) {
                System.out.println("Định dạng không hợp lệ. Vui lòng nhập lại.");
            }
        } while (!validInput);
    }
    private void setNameWithValidation(){
        boolean validInput = false;
        do {
            System.out.println("Nhap vao ten the loai: ");
            name = scanner.nextLine();
            if ( (name.length() >= 6) && checkCurrentName(name) && (name.length() <=30)){
                validInput = true;
            } else {
                System.out.println("Ten the loai khong hop le, vui long nhap lai.");
            }
        }while (!validInput);
    }
    private void setStatusWithValidation(){
        boolean validInput = false;
        do {
            try {
            System.out.println("Nhap vao trang thai true/false: ");
            status = Boolean.parseBoolean(scanner.nextLine());
            validInput = true;
            } catch (IllegalArgumentException e){
                System.out.println("Dinh dang khong hop le, vui long nhap lai.");
            }
        } while (!validInput);
    }
    @Override
    public void input() {
        //This is real code for implementation --> DON'T REMOVE
        setIdWithValidation();
        setNameWithValidation();
        setStatusWithValidation();
        System.out.println("---------------------------");
    }
    public void editData() {
        setNameWithValidation();
        setStatusWithValidation();
        System.out.println("---------------------------");
    }

    @Override
    public void output() {
        System.out.printf("Ma the loai: %d\t Ten the loai: %s\t Trang thai: %s\n", id, name, (status ? "Hoat dong" : "Khong hoat dong"));
    }
}
