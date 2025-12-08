package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/load-book")
@MultipartConfig(
fileSizeThreshold = 1024 * 1024,    // 1 MB
maxFileSize = 1024 * 1024 * 10,     // 10 MB
maxRequestSize = 1024 * 1024 * 15   // 15 MB
)



public class BookUploadServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploaded_books";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h2>Загрузите книгу</h2>");
        out.println("<form method='POST' action='/load-book' enctype='multipart/form-data'>");
        out.println("<input type='file' name='bookFile'><br>");
        out.println("<input type='submit' value='Загрузить'>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Part filePart = req.getPart("bookFile");

        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        String fileName = getFileName(filePart);
        filePart.write(uploadPath + File.separator + fileName);

        resp.getWriter().println("Файл '" + fileName + "' успешно загружен!");
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length() - 1);
            }
        }
        return "unknown";
    }


}
