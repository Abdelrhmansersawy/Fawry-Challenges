package servlets;

import product.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response type to HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            // Retrieve and validate product name parameter
            String productName = request.getParameter("name");
            if (productName == null || productName.trim().isEmpty()) {
                out.println("<h1>Error: Product name cannot be empty.</h1>");
                return;
            }

            productName = productName.trim();

            // Check if product exists
            if (ProductRepository.isExistProductByName(productName)) {
                // Delete product logic (assuming a delete method exists)
                ProductRepository.deleteProductByName(productName);
                out.println("<h1>Product '" + productName + "' successfully deleted.</h1>");
            } else {
                out.println("<h1>Error: Product '" + productName + "' not found.</h1>");
            }
        } catch (Exception e) {
            // Handle unexpected exceptions
            out.println("<h1>Internal Server Error: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        } finally {
            // Close the writer
            out.close();
        }
    }
}
