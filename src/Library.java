import java.util.Scanner;

public class Library {
    public static void main(String[] args) {
        //program service run here
        mainService();

    }

    public static void mainService() {
        //init all environment's element here
        CategoryService categoryService = CategoryService.getInstance();
        BookService bookService = BookService.getInstance();

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("===== QUẢN LÝ THƯ VIỆN =====\n" +
                    "1. Quản lý Thể loại\n" +
                    "2. Quản lý Sách\n" +
                    "3. Thoát\n");
            System.out.println("Nhap vao 1-3 de chon phuong thuc");
            int n = Integer.parseInt(scanner.nextLine());
            switch (n){
                case 1:
                    categoryService.addDataForCategory(scanner);
                    break;
                case 2:
                    bookService.addDataForBook(scanner);
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Nhap sai dinh dang vui long nhap lai");
            }
        } while (true);
    }
}
