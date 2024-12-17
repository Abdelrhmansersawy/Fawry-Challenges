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

@WebServlet("/searchProduct")
public class SearchProductServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Get search parameter
        String name = request.getParameter("name");

        // Validate input
        if (name == null || name.trim().isEmpty()) {
            out.println("<h1>Error: Invalid Search</h1>");
            out.println("<p>Please provide a valid product name.</p>");
            return;
        }

        try {
            // Search products
            List<Product> results = ProductRepository.findProductsByName(name);

            // Check if results found
            if (results.isEmpty()) {
                out.println("<h1>Search Results</h1>");
                out.println("<p>No products found matching: " + name + "</p>");
                return;
            }

            // Display results
            out.println("<h1>Search Results for: " + name + "</h1>");
            out.println("<table border='1'>");
            out.println("<tr><th>ID</th><th>Name</th><th>Price</th></tr>");

            for (Product product : results) {
                out.println("<tr>");
                out.println("<td>" + product.getId() + "</td>");
                out.println("<td>" + product.getName() + "</td>");
                out.println("<td>$" + product.getPrice() + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");

        } catch (Exception e) {
            out.println("<h1>Error in Search</h1>");
            out.println("<p>An error occurred while searching for products.</p>");
        }


    }
}
