package servlets;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.annotation.WebServlet;

@WebListener
public class StartupListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("Приложение запущено и работает!");
        System.out.println("Время запуска " + new java.util.Date());
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Приложение остановлено!");
    }
}
