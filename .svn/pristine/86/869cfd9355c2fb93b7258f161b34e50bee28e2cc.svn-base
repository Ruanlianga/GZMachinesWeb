<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fns" uri="/WEB-INF/tlds/fns.tld" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html >
<html lang="en">
<head>
<%@include file="../baseset.jsp" %>
<%@include file="../systemset.jsp" %>
    <meta charset="UTF-8">
    <title>附件列表</title>
</head>
<body>
    <h1>附件列表</h1>
    <table id="fileTable" border="1" style="width: 96%">
        <thead>
        <tr>
            <th class='center'>序号</th>
            <th class='center'>文件名</th>
            <th class='center'>创建时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="detail" varStatus="index">
            <tr class='pa'>
                <th class='center'>${index.index+1}</th>
                <th class='center'>
                    <a href=" ${bonuspath}${detail.fileUrl}" target="_blank">${detail.fileName}</a>
                </th>
                <th class='center'>${detail.createTime}</th>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</body>

</html>