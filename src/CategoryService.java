import java.util.*;

public class CategoryService {
    private static CategoryService instance;
    private static ArrayList<Category> listCategory;


    private CategoryService() {
        listCategory = new ArrayList<>();
    }

    public static CategoryService getInstance() {
        if( instance == null) {
            instance = new CategoryService();
        }
        return instance;
    }

    public void addDataForCategory(Scanner scanner) {
        //List<Category> listCategory = new ArrayList<>();
        Category category = new Category();
        do {
            System.out.println("===== QUẢN LÝ THỂ LOẠI =====\n" +
                    "1. Thêm mới thể loại\n" +
                    "2. Hiển thị danh sách theo tên A – Z\n" +
                    "3. Thống kê thể loại và số sách có trong mỗi thể loại\n" +
                    "4. Cập nhật thể loại\n" +
                    "5. Xóa thể loại\n" +
                    "6. Quay lại\n");
            System.out.println("Nhap vao 1-6 de chon phuong thuc");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    //test code - remove later
                    addDefaultCategoryForTest();

//                    System.out.println("Them moi the loai");
//                    System.out.println("Ban muon them bao nhieu the loai");
//                    int n = Integer.parseInt(scanner.nextLine());
//                    for (int i = 0; i < n; i++) {
//                        System.out.println("The loại " + (i + 1));
//                        Category category1 = new Category();
//                        category1.input();
//                        listCategory.add(category1);
//                    }
                    break;
                case 2:
                    System.out.println("Hiển thị danh sách theo tên A – Z");
                    listCategory.sort(Comparator.comparing(Category::getName));
                    for (Category category1 : listCategory) {
                        category1.output();
                    }
                    break;
                case 3:
                    System.out.println("Thống kê thể loại và số sách có trong mỗi thể loại");
                    for (Category category1 : listCategory) {
                        int i=0;
                        for (Book book : BookService.getInstance().getListBook()) {
                            if (book.getCategoryId()==category1.getId()){
                                i++;
                            }
                        }
                        System.out.printf("Ma the loai: %d\tTen the loai: %s\tSo sach: %d\n",category1.getId(),category1.getName(),i);
                    }
                    break;
                case 4:
                    System.out.println("Cập nhật thể loại");
                    System.out.println("Nhap vao ma the loai muon thay doi");
                    int editDataById = Integer.parseInt(scanner.nextLine());
                    Category category1 = getCategoryById(listCategory,editDataById);
                        if (category1!=null){
                            category1.output();
                            System.out.println("Ban co chac chan muon sua khong? (c/k)");
                            String line = scanner.nextLine();
                            if (line.equalsIgnoreCase("c")){
                                category1.editData();
                                System.out.println("Ban da cap nhat thanh cong ");
                            } else {
                                System.out.println("Huy thanh cong");
                            }
                        } else {
                            System.out.println("Khong tim thay category co ma so: "+editDataById);
                        }
                    break;
                case 5:
                    System.out.println("Xoa the loai");
                    System.out.println("Nhap vao ma the loai ban muon xoa");
                    int idCategoryForDelete = Integer.parseInt(scanner.nextLine());
                    Category category2 = getCategoryById(listCategory,idCategoryForDelete);
                    if (category2 == null){
                        System.out.println("Khong ton tai the loai co ma la: "+idCategoryForDelete);
                    } else {
                        category2.output();
                        System.out.println("Ban co thuc su muon xoa khong? (c/k)");
                        String line = scanner.nextLine();
                        if (line.equalsIgnoreCase("c")){
                            Book book = getBookByCategoryId(idCategoryForDelete);
                            if (book==null){
                            listCategory.remove(category2);
                            System.out.println("Ban da xoa thanh cong");
                            } else {
                                System.out.println("The loai dang co sach");
                            }
                        } else {
                            System.out.println("Ban da huy xoa");
                        }
                    }
                    break;
                case 6:
                    Library.mainService();
                    break;
                default:
                    System.out.println("Nhap sai dinh dang vui long nhap lai");
                    break;
            }

        } while (true);

    }

    private void addDefaultCategoryForTest() {
        System.out.println("addDefaultCategoryForTest - running");
        for(int i = 0; i < 5 ; i++) {
            Category category = new Category(i, "TheLoai" + i, true);
            listCategory.add(category);
        }
    }
    public ArrayList<Category> getListCategory() {
        return  listCategory;
    }
    private static Book getBookByCategoryId( int id){
        int i=0;
        for (Book book : BookService.getInstance().getListBook()) {
            if (book.getCategoryId()==id){
                i++;
                return book;
            }
        }
        return  null;
    }


    private static Category getCategoryById(List<Category> listCategory, int id){
        for (Category category : listCategory) {
            if (category.getId()==id){
                return category;
            }
        }
        return null;
    }

}
