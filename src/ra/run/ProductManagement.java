package ra.run;

import ra.bussiness.ProductBussiness;
import ra.entity.Product;

import java.util.List;
import java.util.Scanner;

public class ProductManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("************MENU************");
            System.out.println("1. Hiển thị thông tin sản phẩm");
            System.out.println("2. Thêm mới sản phẩm");
            System.out.println("3. Cập nhật sản phẩm");
            System.out.println("4. Xóa sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm theo mã sản phảm");
            System.out.println("6. Tìm kiếm sản phẩm trong khoảng giá");
            System.out.println("7. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    ProductManagement.displayListProduct();
                    break;
                case 2:
                    ProductManagement.createProduct(scanner);
                    break;
                case 3:
                    ProductManagement.updateProduct(scanner);
                    break;
                case 4:
                    ProductManagement.deleteProduct(scanner);
                    break;
                case 5:
                    break;
                case 6:
                    ProductManagement.getCntProductByPrice(scanner);
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng nhập từ 1-7");
            }
        }while(true);
    }

    public static void displayListProduct(){
        List<Product> listProduct = ProductBussiness.getAllProduct();
        System.out.println("THÔNG TIN CÁC SẢN PHẨM:");
        listProduct.stream().forEach(product->product.displayData());
    }

    public static void createProduct(Scanner scanner){
        //Nhập dữ liệu cho 1 sản phẩm để thêm mới
        Product proNew = new Product();
        proNew.inputData(scanner);
        //Gọi createProduct của ProductBussiness để thực hiện thêm dữ liệu vào db
        boolean result = ProductBussiness.createProduct(proNew);
        if (result){
            System.out.println("Thêm mới thành công");
        }else{
            System.err.println("Có lỗi xảy ra trong quá trình thực hiện, vui lòng thực hiện lai");
        }
    }

    public static void updateProduct(Scanner scanner){
        System.out.println("Nhập mã sản phẩm cần cập nhật:");
        int productIdUpdate = Integer.parseInt(scanner.nextLine());
        //Kiểm tra mã sản phẩm này có tồn tại hay không
        Product productUpdate = ProductBussiness.getProductById(productIdUpdate);
        if (productUpdate!=null){
            //Mã sản phẩm có tồn tại trong CSDL
            System.out.println("Nhập vào tên sản phẩm cần cập nhật:");
            productUpdate.setProductName(scanner.nextLine());
            System.out.println("Nhập vào giá sản phẩm:");
            productUpdate.setPrice(Float.parseFloat(scanner.nextLine()));
            System.out.println("Nhập vào trạng thái sản phẩm:");
            productUpdate.setProductStatus(Boolean.parseBoolean(scanner.nextLine()));
            //Thực hiện cập nhật
            boolean result = ProductBussiness.updateProduct(productUpdate);
            if (result){
                System.out.println("Cập nhật thành công");
            }else{
                System.err.println("Có lỗi xảy ra trong quá trình thực hiện, vui lòng thực hiện lai");
            }
        }else{
            //Mã sản phẩm không tồn tại trong CSDL
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    public static void deleteProduct(Scanner scanner){
        System.out.println("Nhập vào mã sản phẩm cần xóa:");
        int productIdDelete = Integer.parseInt(scanner.nextLine());
        //Kiểm tra productIdDelete có tồn tại trong DB
        Product productDelete = ProductBussiness.getProductById(productIdDelete);
        if (productDelete!=null){
            boolean result = ProductBussiness.deleteProduct(productIdDelete);
            if (result){
                System.out.println("Xóa thành công");
            }else{
                System.err.println("Có lỗi xảy ra trong quá trình thực hiện, vui lòng thực hiện lai");
            }
        }else{
            System.err.println("Mã sản phẩm không tồn tại");
        }
    }

    public static void getCntProductByPrice(Scanner scanner){
        System.out.println("Nhập vào giá sản phẩm từ:");
        float fromPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập vào giá sản phẩm đến:");
        float toPrice = Float.parseFloat(scanner.nextLine());
        int cntProduct = ProductBussiness.getProductsByPrice(fromPrice,toPrice);
        System.out.printf("Số lượng sản phẩm có giá từ %f đến %f là: %d\n",fromPrice,toPrice,cntProduct);
    }
}
