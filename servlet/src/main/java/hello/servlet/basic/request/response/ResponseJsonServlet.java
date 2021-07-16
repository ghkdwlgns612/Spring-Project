package hello.servlet.basic.request.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest requset, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");

        HelloDate helloDate = new HelloDate();
        helloDate.setAge(20);
        helloDate.setUsername("kim");

        //{"username":"kim", "age" : 20}
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(helloDate);
        response.getWriter().write(result);
    }
}
