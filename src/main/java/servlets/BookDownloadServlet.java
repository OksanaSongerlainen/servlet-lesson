package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/book")
public class BookDownloadServlet extends HttpServlet {
    private static final String BOOK_FILENAME = "sample-book.pdf";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String appPath = getServletContext().getRealPath("");
        String bookPath = appPath + "WEB-INF" + File.separator + "books" + File.separator + BOOK_FILENAME;

        File file = new File(bookPath);


        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Книга не найдена на сервере.");
            System.err.println("Файл не найден по пути: " + bookPath);
            return;
        }
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition",
                "attachment; filename=\"" + file.getName() + "\"");
        response.setContentLength((int) file.length());
        try(InputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream()){
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
           }
}