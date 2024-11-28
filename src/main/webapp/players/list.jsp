<%--
  Created by IntelliJ IDEA.
  User: HuuNghia
  Date: 28-Nov-24
  Time: 18:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Player Information</title>
    <style>
        body { font-family: Arial, sans-serif; }
        h1 { color: #ff9800; }
        table { width: 100%; border-collapse: collapse; margin-top: 20px; }
        table, th, td { border: 1px solid #ddd; }
        th, td { padding: 8px; text-align: left; }
        th { background-color: #f4f4f4; }
        input, select, button { margin: 5px 0; padding: 8px; }
        button { background-color: #ff9800; color: white; border: none; cursor: pointer; }
        button:hover { background-color: #e68a00; }
    </style>
</head>
<body>
<h1>Player Information</h1>

<form method="post" action="/players">
    <label>Player name: <input type="text" name="name" required></label>
    <label>Player age: <input type="number" name="age" required></label>
    <label>Index name:
        <select name="indexId" id="indexId" required>
            <option value="">--Select--</option>
            <c:forEach var="indexer" items="${indexers}">
                <option value="${indexer.indexerId}">
                        ${indexer.name}
                </option>
            </c:forEach>
        </select>
    </label>
    <label>Value:
        <select name="value" id="value" required>
            <option value="">--Select--</option>
        </select>
    </label>
    <button type="submit">Add</button>
</form>

<table>
    <thead>
    <tr>
        <th>Id</th>
        <th>Player name</th>
        <th>Player age</th>
        <th>Index name</th>
        <th>Value</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="player" items="${players}">
        <tr>
            <td>${player.id}</td>
            <td>${player.name}</td>
            <td>${player.age}</td>
            <td>${player.indexName}</td>
            <td>${player.value}</td>
            <td>
                <a href="/players?action=edit&id=${player.id}">✏️</a>
                <a href="#" onclick="deletePlayer(${player.id}, ${player.indexName})">🗑️</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>

<script>
    function deletePlayer(playerId, indexName) {
        fetch(`/players?action=delete&playerId=${playerId}&indexName=${indexName}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.ok) {
                    alert('Player deleted successfully');
                } else {
                    alert('Failed to delete player');
                }
            });
    }

    document.getElementById('indexId').addEventListener('change', function() {
        const indexerId = this.value;
        const valueDropdown = document.getElementById('value');

        valueDropdown.innerHTML = '<option value="">--Select--</option>';

        if (indexerId) {
            fetch('/getIndexValueRange?indexerId=' + indexerId)
                .then(response => response.json())
                .then(data => {
                    const minValue = data.minValue;
                    const maxValue = data.maxValue;

                    for (let i = minValue; i <= maxValue; i++) {
                        const option = document.createElement('option');
                        option.value = i;
                        option.textContent = i;
                        valueDropdown.appendChild(option);
                    }
                })
                .catch(error => {
                    console.error('Error fetching value range:', error);
                });
        }
    });
</script>

</html>

