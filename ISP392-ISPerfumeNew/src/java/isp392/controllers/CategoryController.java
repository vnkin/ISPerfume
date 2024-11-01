/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package isp392.controllers;

import isp392.product.ProductDAO;
import isp392.product.ViewProductDTO;
import java.io.IOException;
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
public class CategoryController extends HttpServlet {

    private static final String ERROR ="home.jsp";
    private static final String SUCCESS ="shopping.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url=ERROR;
        try {
            int categoryID = Integer.parseInt(request.getParameter("Category"));
            
            HttpSession session = request.getSession();
            Map<String, Integer> ids = (Map<String, Integer>) session.getAttribute("CURRENT_IDS"); 
            ids.put("categoryID", categoryID); //store current categoryID and send to Brand & Size filter controller
            
            Map<String, Integer> sizeIDS = (Map<String, Integer>) session.getAttribute("SIZE_IDS");
            if (sizeIDS != null && !sizeIDS.isEmpty()) {
                session.removeAttribute("SIZE_IDS");
            }
            
            Map<String, Integer> brandIDS = (Map<String, Integer>) session.getAttribute("CURRENT_BRANDID");
            if (brandIDS != null && !brandIDS.isEmpty()) {
                session.removeAttribute("CURRENT_BRANDID");
            }
            
            Object searchContent = session.getAttribute("CURRENT_SEARCH");
            if (searchContent != null) {
                session.removeAttribute("CURRENT_SEARCH");
            }
            
            ProductDAO productDAO = new ProductDAO();           
            List<ViewProductDTO> listProduct = productDAO.getListProductByCategory(categoryID);
                       
            session.setAttribute("LIST_PRODUCT", listProduct);
            session.removeAttribute("BRAND_INFOR");
            url = SUCCESS;
                
        } catch (Exception e) {
            log("Error at CategoryController: " + e.toString());
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
