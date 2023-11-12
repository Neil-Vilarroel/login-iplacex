package com.myconstruction.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lógica de inicio de sesión
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validar credenciales y redirigir según el resultado
        if (validarCredenciales(username, password)) {
            // Credenciales válidas, redirigir a la página de inicio
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        } else {
            // Credenciales inválidas, redirigir de vuelta al formulario de inicio de sesión
            response.sendRedirect(request.getContextPath() + "/login.jsp");
        }
    }

    private boolean validarCredenciales(String username, String password) {
        // Lógica para validar las credenciales (puedes implementar esto)
        // Por ahora, simplemente devuelve true para fines de demostración
        return true;
    }
}
