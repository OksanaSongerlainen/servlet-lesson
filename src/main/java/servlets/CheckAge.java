package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/CheckAge")

public class CheckAge extends HttpServlet {
    /*Создать сервлет который на вход принимает возраст, а в ответе возвращает
    информацию, совершеннолетний или нет*/
    private static final int MIN_AGE = 18;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head><title>Проверка возраста</title></head>");
        out.println("<body>");
        out.println("<h1>Проверка совершеннолетия</h1>");
        out.println("<form method='POST'>");
        out.println("Введите возраст: <input type='number' name='age'>");
        out.println("<input type='submit' value='Проверить'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<html>");
        out.println("<head><title>Результат</title></head>");
        out.println("<body>");
        out.println("<h1>Результат проверки</h1>");

        String ageParam = req.getParameter("age");

        try {
            if (ageParam == null || ageParam.trim().isEmpty()) {
                out.print("Ошибка: возраст не указан");
                return;
            }

            int age = Integer.parseInt(ageParam);

            if (age < 0) {
                out.print("Ошибка: возраст не может быть отрицательным");
            } else if (age < MIN_AGE) {
                out.print("Несовершеннолетний");
            } else {
                out.print("Совершеннолетний");
            }

        } catch (NumberFormatException e) {
            out.print("Ошибка: введите корректное число");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
