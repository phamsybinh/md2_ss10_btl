import java.time.LocalDate;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Book implements IEntity {
    private String id;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String description;
    private int categoryId;

    public Book(String id, String title, String author, String publisher, int year, String description, int categoryId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.description = description;
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public Book() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void editBook() {
        setNameWithValidation();
        setAuthorWithValidation();
        setPublisherWithValidation();
        setPublishingYearWithValidation();
        setDescriptionWithValidation();
        setCategoryIdWithValidation();
//        System.out.println("Sach ban vua nhap: " + toString());
        System.out.println("--------------------------");
    }

    private void showCurrentCategoryList() {
//        System.out.println("showCurrentCategoryList - running");
        for (Category category : CategoryService.getInstance().getListCategory()) {
            System.out.println(category.getId() + " " + category.getName());
        }
    }

    private void showCategoryInfoOfCurrentBook() {
        for (Category category : CategoryService.getInstance().getListCategory()) {
            if (this.categoryId == category.getId()) {
                System.out.println("Category: id- " + category.getId());
                System.out.println("Category: name- " + category.getName());
            }
        }
    }

    private boolean checkCurrentId(String id) {
        for (Book book : BookService.getInstance().getListBook()) {
            return book.getId().equalsIgnoreCase(id);
        }
        return false;
    }

    private boolean checkCurrentTitle(String title) {
        for (Book book : BookService.getInstance().getListBook()) {
            return book.getTitle().equalsIgnoreCase(title);
        }
        return false;
    }

    private boolean checkCurrentCategoryId(int id) {
        for (Category category : CategoryService.getInstance().getListCategory()) {
            return category.getId() == id;
        }
        return false;
    }

    private void setIdWithValidation() {
        boolean validInput = false;
        do {
            System.out.println("Nhap vao ma sach: ");
            id = scanner.nextLine();
            boolean result = Pattern.matches("(B.{3})", id);
            if (!checkCurrentId(id) && result) {
                validInput = true;
            } else {
                System.out.println("Nhap sai dinh dang, vui long nhap lai.");
            }
        } while (!validInput);
    }

    private void setNameWithValidation() {
        boolean validInput = false;
        do {
            System.out.println("Nhap vao tieu de sach: ");
            title = scanner.nextLine();
            boolean result = Pattern.matches("(.{6,50})", title);
            if (result && !checkCurrentTitle(title)) {
                validInput = true;
            } else {
                System.out.println("Tieu de sach khong hop le, vui long nhap lai");
            }

        } while (!validInput);
    }

    private void setAuthorWithValidation() {
        boolean validInput = false;
        do {
            System.out.println("Nhap vao ten tac gia: ");
            author = scanner.nextLine();
            boolean result = Pattern.matches("(.{1,})", author);
            if (result) {
                validInput = true;
            } else {
                System.out.println("Khong bo trong ten tac gia, vui long nhap lai");
            }
        } while (!validInput);
    }

    private void setPublisherWithValidation() {
        boolean validInput = false;
        do {
            System.out.println("Nhap vao nha xuat ban: ");
            publisher = scanner.nextLine();
            boolean result = Pattern.matches("(.{1,})", publisher);
            if (result) {
                validInput = true;
            } else {
                System.out.println("Khong de trong nha xuat ban, vui long nhap lai.");
            }
        } while (!validInput);
    }

    private void setPublishingYearWithValidation() {
        boolean validInput = false;
        do {
            try {
                System.out.println("Nhap vao nam xuat ban: ");
                year = Integer.parseInt(scanner.nextLine());
                LocalDate currentDate = LocalDate.now();
                int currentYear = currentDate.getYear();
                if (year >= 1970 && year <= currentYear) {
                    validInput = true;
                } else {
                    System.out.println("Sach co nam xuat ban tu 1970-nay, vui long nhap lai");
                }
            } catch (NumberFormatException e) {
                System.out.println("Nam xuat ban khong hop le, vui long nhap lai");
            }

        } while (!validInput);
    }

    private void setDescriptionWithValidation() {
        boolean validInput = false;
        do {
            System.out.println("Mo ta sach: ");
            description = scanner.nextLine();
            boolean result = Pattern.matches("(.{1,})", description);
            if (result) {
                validInput = true;
            } else {
                System.out.println("Khong bo trong ten tac gia, vui long nhap lai");
            }
        } while (!validInput);
    }

    private void setCategoryIdWithValidation() {
        boolean validInput = false;
        do {
            try {
                showCurrentCategoryList();
                System.out.println("Chon the loai sach");
                categoryId = Integer.parseInt(scanner.nextLine());
                if (checkCurrentCategoryId(categoryId)) {
                    validInput = true;
                } else {
                    System.out.println("Ma the loai ma ban chon khong ton tai, vui long nhap lai");
                }

            } catch (Exception e) {
                System.out.println("Sai dinh dang, vui long nhap lai.");
            }
        } while (!validInput);
    }

    @Override
    public void input() {
        System.out.println("Nhap vao thong tin sach");
        setIdWithValidation();
        setNameWithValidation();
        setAuthorWithValidation();
        setPublisherWithValidation();
        setPublishingYearWithValidation();
        setDescriptionWithValidation();
        setCategoryIdWithValidation();
        System.out.println("Sach ban vua nhap: " + toString());
        System.out.println("--------------------------");
//        System.out.println("The loai la: \n");
//        showCategoryInfoOfCurrentBook();

    }

    @Override
    public void output() {
        System.out.printf("id= %s\ttitle= %s\tauthor= %s\tpublisher= %s\tyear= %d\tdescription= %s\tcategoryId= %d\n", id, title, author, publisher, year, description, categoryId);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }

}
