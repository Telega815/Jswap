<%--
  Created by IntelliJ IDEA.
  User: Telega
  Date: 20.02.2018
  Time: 8:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>UserPage</title>
</head>
<body>
    <h1>HI ${user.username}! GO FUCK YOURSELF!</h1>

    <%--<form method="post" action="${user.username}/upload" enctype="multipart/form-data">--%>
        <%--File to upload: <input type="file" name="file" multiple >--%>
        <%--<input type="submit" value="Upload">--%>
    <%--</form>--%>

    <form:form method="post" enctype="multipart/form-data" action="${user.username}/upload" modelAttribute="uploadedFile">

        <table>
            <tr>
                <td>Upload File:</td>
                <td><input type="file" name="files" /></td>
                <td style="color: red; font-style: italic;">
                    <form:errors path="files"/>
                </td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Upload"></td>
                <td></td>
            </tr>
        </table>
    </form:form>
    <a href="${flowExecutionUrl}/${user.username}/viewFiles">viewFiles</a>


</body>
</html>
