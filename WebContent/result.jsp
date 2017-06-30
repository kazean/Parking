<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.Enumeration"%>

<%-- <%
Enumeration<String> names = request.getAttributeNames();
while(names.hasMoreElements()) {
  String name = names.nextElement();
  System.out.println("in JSP attr[" + name + ", " + request.getAttribute(name));
  if(request.getAttribute(name) instanceof Model) {
    System.out.println("in JSP model[" + name + ", " + request.getAttribute(name));
  }
}%> --%>

${requestScope.msg}