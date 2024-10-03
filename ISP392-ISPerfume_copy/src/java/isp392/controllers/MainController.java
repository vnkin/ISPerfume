/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isp392.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ThinhHoang
 */
public class MainController extends HttpServlet {

    private static final String HOME_PAGE = "home.jsp";
    
    private static final String SEARCH_PRODUCT = "";
    private static final String SEARCH_PRODUCT_CONTROLLER = "SearchProduct";
    private static final String SHOW_ALL_PRODUCT_MANAGER = "Search";
    private static final String SHOW_ALL_PRODUCT_MANAGER_CONTROLLER = "ShowAllProductManager";
    private static final String UPDATE_PRODUCT_MANAGER = "Update";
    private static final String UPDATE_PRODUCT_MANAGER_CONTROLLER = "UpdateProductManager";
    private static final String CREATE_PRODUCT_MANAGER = "Create";
    private static final String CREATE_PRODUCT_MANAGER_CONTROLLER = "CreateProductManager";
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = HOME_PAGE;
        try {
            String action = request.getParameter("action");
            if (SEARCH_PRODUCT.equals(action)) {
                url = SEARCH_PRODUCT_CONTROLLER;
            }else if(SHOW_ALL_PRODUCT_MANAGER.equals(action)){
                url = SHOW_ALL_PRODUCT_MANAGER_CONTROLLER;
            }else if(UPDATE_PRODUCT_MANAGER.equals(action)){
                url = UPDATE_PRODUCT_MANAGER_CONTROLLER;
            }else if(CREATE_PRODUCT_MANAGER.equals(action)){
                url = CREATE_PRODUCT_MANAGER_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}