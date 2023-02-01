package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.OrderDAO;
import member.OrderVO;

public class OrderSearchAction implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      ActionForward forward = new ActionForward();
      forward.setPath("order-list.jsp");
      forward.setRedirect(false);
      
      System.out.println("로그: 날짜 조회 성공");
      OrderVO ovo=new OrderVO();
      OrderDAO odao=new OrderDAO();
      
      ArrayList<OrderVO> searchResult = new ArrayList<OrderVO>();
      
      ovo.setoStatus(4);
      
      if(request.getSession().getAttribute("mNum") != null) {
          ovo.setmNum((int)(request.getSession().getAttribute("mNum")));
       }
      
      System.out.println("로그 : "+request.getParameter("searchCal"));
      if(request.getParameter("searchCal") != null) {
         ovo.setSearchCal(Integer.parseInt(request.getParameter("searchCal")));
      }
      
      searchResult=  odao.selectAll(ovo);
      
      request.setAttribute("orders", searchResult);
      return forward;
   }

}