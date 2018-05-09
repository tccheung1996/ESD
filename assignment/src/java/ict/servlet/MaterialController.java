/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Material;
import ict.db.ProjectDB;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "MaterialController", urlPatterns = {"/MaterialController"})
public class MaterialController extends HttpServlet {

    private ProjectDB db;

    @Override
    public void init() {
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        db = new ProjectDB(dbUrl, dbUser, dbPassword);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session.getAttribute("role_id") == null) {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/pages/login.jsp");
            rd.forward(request, response);
        }

        String action = request.getParameter("action");
        if ("list".equalsIgnoreCase(action)) {
            //***************** Role *********************
            //session.setAttribute("role_id", "1");
            ArrayList materials = db.getMaterials();
            request.setAttribute("materials", materials);
            RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/pages/show_material.jsp");
            rd.forward(request, response);
        } else if ("dl".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            System.out.println(id);
            String fileName = db.getPath(Integer.parseInt(id));
            // obtains ServletContext
            ServletContext context = getServletContext();
            // gets MIME type of the file
            String mimeType = context.getMimeType(fileName);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);
            // Find this file id in database to get file name, and file type
            // You must tell the browser the file type you are going to send
            // for example application/pdf, text/plain, text/html, image/jpg
            response.setContentType(mimeType);
            // Assume file name is retrieved from database
            // For example D:\\file\\test.pdf
            File my_file = new File(fileName);
            // Make sure to show the download dialog
            response.setHeader("Content-disposition", "attachment; filename=" + my_file.getName());
            // This should send the file to browser
            OutputStream out = response.getOutputStream();
            FileInputStream in = new FileInputStream(my_file);
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.flush();
        } else if ("del".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            System.out.println(id);
            String fileName = db.getPath(Integer.parseInt(id));
            System.out.println(fileName);
            File f = new File(fileName);
            db.delMaterial(Integer.parseInt(id));
            if (f.exists()) {
                if (f.delete()) {
                    String message = "Successful!";
                    response.sendRedirect("MaterialController?action=list&message=" + URLEncoder.encode(message, "UTF-8"));
                } else {
                    String message = "Error!";
                    response.sendRedirect("MaterialController?action=list&message=" + URLEncoder.encode(message, "UTF-8"));
                }
            } else {
                String message = "No such material!";
                response.sendRedirect("MaterialController?action=list&message=" + URLEncoder.encode(message, "UTF-8"));
            }
        } else if ("upload".equalsIgnoreCase(action)) {
            ArrayList modules = db.getAllModules();
            request.setAttribute("modules", modules);
            RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/pages/upload_material.jsp");
            rd.forward(request, response);
        } else if ("listM".equalsIgnoreCase(action)) {
            ArrayList modules = db.getAllModules();
            request.setAttribute("modules", modules);
            RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/pages/material_module.jsp");
            rd.forward(request, response);
        } else if ("listMM".equalsIgnoreCase(action)) {
            String id = request.getParameter("id");
            ArrayList materials = db.getModMaterials(Integer.parseInt(id));
            request.setAttribute("materials", materials);
            RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/pages/show_material.jsp");
            rd.forward(request, response);
        } else if ("listStuMod".equalsIgnoreCase(action)) {
            //***************** ID *********************
            //session.setAttribute("stu_id", "1");
            int stuID = (int) session.getAttribute("stu_id");
            System.out.println(stuID);
            ArrayList modules = db.getStuMods(stuID);
            request.setAttribute("modules", modules);
            RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/pages/material_module.jsp");
            rd.forward(request, response);
        }else if("edit".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            ArrayList modules = db.getAllModules();
            request.setAttribute("modules", modules);
            Material material = db.getMaterial(Integer.parseInt(id));
            request.setAttribute("material", material);
            RequestDispatcher rd = this.getServletContext()
                    .getRequestDispatcher("/pages/edit_material.jsp");
            rd.forward(request, response);
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
