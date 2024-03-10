import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookService {
    private static BookService instance ;
    private static ArrayList<Book> listBook;
    private BookService(){
        listBook = new ArrayList<Book>();
    }

    public static BookService getInstance() {
        if(instance == null) {
            instance = new BookService();
        }

        return instance;
    }
    public ArrayList<Book> getListBook() {
        return  listBook;
    }

    public void addDataForBook(Scanner scanner) {
//        List<Book> listBook = new ArrayList<>();
        do {
            System.out.println("===== QUẢN LÝ SÁCH =====\n" +
                    "1. Thêm mới sách\n" +
                    "2. Cập nhật thông tin sách\n" +
                    "3. Xóa sách\n" +
                    "4. Tìm kiếm sách\n" +
                    "5. Hiển thị danh sách sách theo nhóm thể loại\n" +
                    "6. Quay lại");
            System.out.println("Nhap vao tu 1-6 de chon phuong thuc");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("Them moi sach");
                    System.out.println("Ban dinh them bao nhieu cuon sach");
                    int n = Integer.parseInt(scanner.nextLine());
                    for (int i = 0; i < n; i++) {
                        System.out.println("Sach thu " + (i+1));
                        Book book = new Book();
                        book.input();
                        listBook.add(book);
                    }
                    break;
                case 2:
                    System.out.println("Cập nhật thông tin sách");
                    System.out.println("Nhap ma sach ma ban muon cap nhat");
                    String idForEdit = scanner.nextLine();
                    Book book = getBookById(listBook,idForEdit);
                    if (book==null){
                        System.out.println("Khong tim thay cuon sach co ma sach "+idForEdit);
                    } else {
                        book.output();
                        System.out.println("Ban co thuc su muon thay doi thong tin cuon sach khong? (c/k)");
                        String line = scanner.nextLine();
                        if (line.equalsIgnoreCase("c")){
                            book.editBook();
                            System.out.println("Ban da thay doi thanh cong thong tin sach");
                        } else {
                            System.out.println("Ban da huy thanh cong");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Xoa sach");
                    System.out.println("Nhap vao ma sach ban muon xoa");
                    String idBookForDelete = scanner.nextLine();
                    Book book1 = getBookById(listBook,idBookForDelete);
                    if (book1 == null){
                        System.out.println("Khong ton tai sach co ma sach la: "+idBookForDelete);
                    } else {
                        book1.output();
                        System.out.println("Ban co thuc su muon xoa khong? (c/k)");
                        String line = scanner.nextLine();
                        if (line.equalsIgnoreCase("c")){
                            listBook.remove(book1);
                            System.out.println("Ban da xoa thanh cong");
                        } else {
                            System.out.println("Ban da huy xoa");
                        }
                    }
                    break;
                case 4:
                    System.out.println("Tim kiem sach");
                    System.out.println("Nhap vao ten sach ban muon tim kiem");
                    String nameForSearch = scanner.nextLine();
                    Book book2 = getBookByName(listBook,nameForSearch);
                    if (book2 == null){
                        System.out.println("Khong tim thay sach ma ban can");
                    } else {
                        System.out.println("Danh sach tim kiem");
                        getBookByName(listBook,nameForSearch);
                    }
                    break;
                case 5:
                    System.out.println("Hiển thị danh sách sách theo nhóm thể loại");
                    for (Category category : CategoryService.getInstance().getListCategory()) {
                        System.out.println(category.getName());
                        Book book3 = getBookByIdCategory(listBook,category.getId());
                        if (book3 != null){
                            getBookByIdCategory(listBook,category.getId());
                            break;
                        }
                    }
                    break;
                case 6:
                    Library.mainService();
                    break;
                default:
                    System.out.println("Noi dung ban nhap sai dinh dang vui long nhap lai");
            }
        } while (true);
    }

    private static Book getBookByName(List<Book> listBook, String prompt){
        for (Book book : listBook){
            if (book.getTitle().toLowerCase().contains(prompt.toLowerCase())||book.getAuthor().toLowerCase().contains(prompt.toLowerCase())||book.getPublisher().toLowerCase().contains(prompt.toLowerCase())){
                System.out.println(book.getTitle());
            }
        }
        return null;
    }
    private static Book getBookById(List<Book> listBook, String id) {
        for (Book book : listBook) {
            if (book.getId().equalsIgnoreCase(id)) {
                return book;
            }
        }
        return null;
    }
    private static Book getBookByIdCategory(List<Book> listBook, int id) {
        for (Book book : listBook) {
            if (book.getCategoryId() == id) {
                System.out.println("\tSach "+book.getTitle());
            }
        }
        return null;
    }
}
