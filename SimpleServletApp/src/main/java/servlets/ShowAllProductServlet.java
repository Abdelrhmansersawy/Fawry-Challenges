package servlets;

import product.Product;
import product.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/showAllProduct")
public class ShowAllProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set content type to HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Retrieve all products
            List<Product> allProducts = ProductRepository.getAllProducts();

            // Page Title
            out.println("<h1>All Products</h1>");

            // Check if product list is empty
            if (allProducts.isEmpty()) {
                out.println("<p>No products are available in the repository.</p>");
                return;
            }

            // Display products in an HTML table
            out.println("<table border='1' style='width:80%; margin:auto; border-collapse: collapse;'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>Name</th>");
            out.println("<th>Price</th>");
            out.println("</tr>");

            for (Product product : allProducts) {
                out.println("<tr>");
                out.println("<td>" + product.getId() + "</td>");
                out.println("<td>" + product.getName() + "</td>");
                out.println("<td>$" + product.getPrice() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        } catch (Exception e) {
            // Handle exceptions
            out.println("<h1>Error</h1>");
            out.println("<p>An error occurred while retrieving all products.</p>");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }
}
