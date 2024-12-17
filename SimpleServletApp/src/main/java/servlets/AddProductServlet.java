package servlets;

import product.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String pricePram = request.getParameter("price");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Validate name
        if (name == null || name.length() < 2) {
            out.println("<h1>Error: Invalid Product Name</h1>");
            out.println("<p>Product name must be at least 2 characters long.</p>");
            return;
        }

        try {
            double price = Double.parseDouble(pricePram);
            if (price < 0) {
                out.println("<h1>Error: Invalid Price</h1>");
                out.println("<p>Price cannot be negative.</p>");
                return;
            }
            ProductRepository.addProduct(name.trim(), price);
            out.println("<h1>Successfully created product</h1>");
            out.println("<p>Product: " + name + " (Price: $" + price + ")</p>");
            out.println("<a href='addProduct.html'>Add Another Product</a>");
        } catch (NumberFormatException | NullPointerException e) {
            out.println("<h1>Error: Invalid input</h1>");
            out.println("<p>Please provide a valid price.</p>");
        }

    }
}
