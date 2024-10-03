/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isp392.controllers;

import isp392.product.ProductDAO;
import isp392.product.ProductDTO;
import isp392.product.ViewProductDTO;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ThinhHoang
 */
public class BrandFilterController extends HttpServlet {

    private static final String ERROR="shopping.jsp";
    private static final String SUCCESS="shopping.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {          
            int brandID = Integer.parseInt(request.getParameter("brandID"));
            int categoryID = Integer.parseInt(request.getParameter("Category"));
            
            HttpSession session = request.getSession();
            Map<String, Integer> ids = new HashMap<>();
            ids.put("brandID", brandID);
            ids.put("categoryID", categoryID);
            session.setAttribute("CURRENT_IDS", ids); //Store brandID & categoryID into attribute for DescendingProductByPrice Controller
            
            ProductDAO productDAO = new ProductDAO();
            List<ViewProductDTO> listProduct = productDAO.filterProductByBrand(brandID, categoryID);
            request.setAttribute("LIST_PRODUCT", listProduct);
            session.setAttribute("LIST_PRODUCT_REFERENCE", listProduct);
            url = SUCCESS;
        } catch (Exception e) {
            log("Error at BrandFilterController: " + e.toString());
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
