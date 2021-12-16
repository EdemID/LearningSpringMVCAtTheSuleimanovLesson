<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <title>Books Page</title>

    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #fff;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>

<body>

<a href="index.jsp">Back to main menu</a>

<br/>
<br/>

<h1>Books List</h1>

<form action="booksSearch" method="POST">
    <label>
        <spring:message text="Find books"/>
        <textarea name="titleBook"></textarea>
    </label>
    <button type="submit">Find books</button>
</form >
<br/>
<br/>

<c:if test="${!empty booksSearch}">
    <table class="tg">
        <tr>
            <th width="80">ID</th>
            <th width="120">Name</th>
            <th width="120">Author</th>
            <th width="120">Price</th>
            <th width="60">Edit</th>
            <th width="60">Delete</th>
        </tr>
        <c:forEach items="${booksSearch}" var="bookSearch">
        <tr>
            <td>${bookSearch.id}</td>
            <td><a href="<c:url value='/bookData/${bookSearch.id}' />" target="_blank">${bookSearch.bookTitle}</a></td>
            <td>${bookSearch.bookAuthor}</td>
            <td>${bookSearch.price/100}${bookSearch.price%100}</td>
            <td><a href="<c:url value='/edit/${bookSearch.id}' />">Edit</a></td>
            <td><a href="<c:url value='/remove/${bookSearch.id}' />">Delete</a></td>
        </tr>
        </c:forEach>
    </table>
</c:if>