<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*, calc.*" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<jsp:useBean id="c" class="calc.CalculatorBean" scope="page" />
<jsp:setProperty name="c" property="*" />

<%
	CalcDAO clacDAO = new CalcDAO();
	clacDAO.addNumber(c);
	List numbersList = clacDAO.listNumbers();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>계산 결과-useBean</h2>
	<table align="center" width="100%">
			<tr align="center" bgcolor="#99ccff">
				<td width"7%">숫자1</td>
				<td width"7%">연산</td>
				<td width"7%">숫자2</td>
				<td width"7%">=</td>
				<td width"7%">정답</td>
			</tr>
			<%
			if(numbersList.size()==0) {
			%>
			<tr>
			<td colspan="5">
			 <p align="center"><b><span style="font-size:9pt;">
			 등록된 숫자가 없습니다.</span></b></p>
			</td>
			</tr> 
			<%
			}else{
				for(int i=0; i<numbersList.size(); i++) {
					CalculatorBean bean = (CalculatorBean) numbersList.get(i);
			%>
			<tr align="center">
				<td><%=bean.getN1() %></td>
				<td><%=bean.getOp() %></td>
				<td><%=bean.getN2() %></td>
				<td><%="=" %></td>
				<td><%=bean.getResult() %></td>
			</tr>
			<%
				}
				}
			%>
			<tr height="1" bgcolor="#99ccff">
				<td colspan="5"></td>
			</tr>
	</table>
</body>
</html>