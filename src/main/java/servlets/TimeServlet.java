package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class TimeServlet extends HttpServlet {
    /*Задача 1:
    Создать приложение, которое при переходе на следующие урлы будет выдавать
    результат:
            /minsk - время в Минске
            /washington - время в Вашингтоне
            /beijing - время в Пекине
    При решении использовать оба варианта настройки (xml и аннотации)*/
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String path = req.getServletPath();

        if (path.equals("/minsk")) {
            String time = getTime("Europe/Minsk");
            out.print("Время в Минске: " + time);
        }
        else if (path.equals("/washington")) {
            String time = getTime("America/New_York");
            out.print("Время в Вашингтоне: " + time);
        }
           else {
            out.print("Доступные города:<br>");
            out.print("<a href='/minsk'>Минск</a><br>");
            out.print("<a href='/washington'>Вашингтон</a><br>");
            out.print("<a href='/beijing'>Пекин</a>");
        }
    }

    private String getTime(String zone) {
        return ZonedDateTime.now(ZoneId.of(zone))
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}