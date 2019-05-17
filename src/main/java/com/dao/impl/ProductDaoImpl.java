package com.dao.impl;

import com.dao.ProductDao;
import com.entities.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProductDaoImpl implements ProductDao {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;


    public void saveProductInTable(Long id, String name, String description,
                                   Double price, String pic){

        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "INSERT INTO products (id, name, description, price, pic) VALUES (?,?,?,?,?)";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, description);
            preparedStatement.setDouble(4, price);
            preparedStatement.setString(5, pic);
            preparedStatement.executeUpdate();

        }catch (SQLException err){
            err.printStackTrace();
        }
        finally {
            try{
                if(preparedStatement!=null || con!=null){
                    preparedStatement.close();
                    con.close();
                }
            }
            catch (SQLException er){
                er.printStackTrace();
            }

        }
    }

    public void updateProduct(Long id, String name, String description,
                              Double price, String pic){
        Connection con = null;
        PreparedStatement preparedStatement = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            String sql = "UPDATE products SET name =?, description=?, price=?, pic=? WHERE id=?";
            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, price);
            preparedStatement.setString(4, pic);
            preparedStatement.setLong(5, id);
            preparedStatement.executeUpdate();

        }catch (SQLException err){
            err.printStackTrace();
        }
        finally {
            try{
                if(preparedStatement!=null || con!=null){
                    preparedStatement.close();
                    con.close();
                }
            }
            catch (SQLException er){
                er.printStackTrace();
            }

        }
    }

    public List<Product> getProductsFromTable (String sortBy){
        List<Product> sortedProducts = new LinkedList();
        String sql = null;
        if(sortBy.equals("lowest")){
            sql = "SELECT * FROM products ORDER BY price";
        }
        else if(sortBy.equals("highest")){
            sql = "SELECT * FROM products ORDER BY price DESC";
        }
        else if(sortBy.equals("sortById")){
            sql = "SELECT * FROM products ORDER BY id";
        }
        else {
            sql = String.format("SELECT * FROM products WHERE name='%s'",sortBy);
        }

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, username, password);
            preparedStatement = con.prepareStatement(sql);
            rs = preparedStatement.executeQuery();
            while ( rs.next() )
            {
                Product prod = new Product();
                prod.setId(rs.getLong("id") );
                prod.setName(rs.getString("name") );
                prod.setDescription(rs.getString("description"));
                prod.setPrice(rs.getDouble("price"));
                prod.setPic(rs.getString("pic"));
                sortedProducts.add(prod);
            }
        }catch (SQLException err){
            err.printStackTrace();
        }
        finally {
            try{
                if(preparedStatement!=null || con!=null){
                    preparedStatement.close();
                    rs.close();
                    con.close();
                }
            }
            catch (SQLException er){
                er.printStackTrace();
            }

        }
        return sortedProducts;
    }



}
