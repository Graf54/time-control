<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List processes</title>
</head>
<body>
<h1>Add new process</h1>
<table border="2" width="70%" cellpadding="2">
    <tr>
        <th>Name</th>
        <th>TimeLimit</th>
        <th>Add</th>
    </tr>
    <form:form method="post" action="add">
        <tr>
            <td><input type="text" width="100%" name="name" value="${proc.name}"/></td>
            <td><input type="number" width="100%" name="timeLimit" value="${proc.timeLimit}"/></td>
            <td><input type="submit" width="100%" value="Add"/></td>
        </tr>
    </form:form>
</table>

<h1>Employees List</h1>
<table border="2" width="70%" cellpadding="2">
    <tr>
        <th>Id</th>
        <th>Added</th>
        <th>Name</th>
        <th>TimeLimit</th>
        <th>TimeUse</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach var="proc" items="${list}">
        <c:choose>
            <c:when test="${proc.editCode==1}">
                <form:form method="post" action="update/${proc.id}">
                    <tr>
                        <td>${proc.id}</td>
                        <td><joda:format value="${proc.added}" style="SM"/></td>
                        <c:choose>
                            <c:when test="${proc.dbHasName==1}">
                                <td><p style="color:rgba(255,0,0,0.5);">Введенное имя уже есть</p><input type="text" name="name" value="${proc.name}"/></td>
                            </c:when>
                            <c:otherwise>
                                <td><input type="text" name="name" value="${proc.name}"/></td>
                            </c:otherwise>
                        </c:choose>
                        <td><input type="number" name="timeLimit" value="${proc.timeLimit}"/></td>
                        <td>${proc.timeUse}</td>
                        <td><input type="submit" value="save"/></td>
                        <td><a href="cancel">Cancel</a></td>
                    </tr>
                </form:form>
            </c:when>
            <c:otherwise>
                <tr>
                    <td>${proc.id}</td>
                    <td><joda:format value="${proc.added}" style="SM"/></td>
                    <td>${proc.name}</td>
                    <td>${proc.timeLimit}</td>
                    <td>${proc.timeUse}</td>
                    <td><a href="edit/${proc.id}">Edit</a></td>
                    <td><a href="delete/${proc.id}">Delete</a></td>
                </tr>
            </c:otherwise>
        </c:choose>

    </c:forEach>
</table>
</body>
</html>
