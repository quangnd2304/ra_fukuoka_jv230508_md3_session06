package ra.bussiness;

import ra.entity.Product;
import ra.ultil.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProductBussiness {
    public static List<Product> getAllProduct(){
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        List<Product> listProduct = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_all_product()}");
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Duyệt các bản ghi trong rs và đẩy ra listProduct
            listProduct = new ArrayList<>();
            while (rs.next()){
                Product product = new Product();
                product.setProductId(rs.getInt("productid"));
                product.setProductName(rs.getString("productname"));
                product.setPrice(rs.getFloat("price"));
                product.setProductStatus(rs.getBoolean("productstatus"));
                listProduct.add(product);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return listProduct;
    }

    public static boolean createProduct(Product product){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call create_product(?,?,?)}");
            //set dữ liệu cho các tham số vào của procedure
            callSt.setString(1,product.getProductName());
            callSt.setFloat(2,product.getPrice());
            callSt.setBoolean(3,product.isProductStatus());
            //Đăng ký kiểu dữ liệu cho tham số trả ra
            //Thực hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public static boolean updateProduct(Product product){
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call update_product(?,?,?,?)}");
            //set dữ liệu cho các tham số vào của procedure
            callSt.setInt(1,product.getProductId());
            callSt.setString(2,product.getProductName());
            callSt.setFloat(3,product.getPrice());
            callSt.setBoolean(4,product.isProductStatus());
            //Đăng ký kiểu dữ liệu cho tham số trả ra
            //Thực hiện gọi procedure
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public static Product getProductById(int productId){
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        Product product = null;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_product_by_id(?)}");
            //set giá trị tham số vào
            callSt.setInt(1,productId);
            //Thực hiện gọi procedure
            ResultSet rs = callSt.executeQuery();
            //Lấy dữ liệu rs đẩy vào đối tượng product trả về
            while (rs.next()){
                product = new Product();
                product.setProductId(rs.getInt("productid"));
                product.setProductName(rs.getString("productname"));
                product.setPrice(rs.getFloat("price"));
                product.setProductStatus(rs.getBoolean("productstatus"));
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return product;
    }

    public static boolean deleteProduct(int productId){
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delete_product(?)}");
            //set giá trị tham số vào
            callSt.setInt(1,productId);
            callSt.executeUpdate();
            result = true;
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return result;
    }

    public static int getProductsByPrice(float fromPrice,float toPrice){
        //1. Tạo đối tượng Connection
        //2. Tạo đối tượng CallableStatement
        //3. Gọi procedure
        //4. Xử lý dữ liệu và trả về listProduct
       int cntProduct = 0;
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call get_cntProduct(?,?,?)}");
            //set giá trị cho các tham số vào
            callSt.setFloat(1,fromPrice);
            callSt.setFloat(2,toPrice);
            //Đăng ký tham số trả ra
            callSt.registerOutParameter(3, Types.INTEGER);
            //Thực hiện gọi procedure
            callSt.execute();
            //lấy giá trị trả ra
            cntProduct = callSt.getInt(3);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            ConnectionDB.closeConnection(conn,callSt);
        }
        return cntProduct;
    }
}
