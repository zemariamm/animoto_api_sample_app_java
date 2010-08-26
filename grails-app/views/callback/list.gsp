<meta name="layout" content="app"></meta>
<h1>Callbacks</h1>
<table border="1">
  <tr>
    <th>Transaction Token</th>
    <th>Message</th>
    <th>Created At</th>
  </tr>
  <% for (callback in callbacks) { %>
    <tr>
      <td><%= callback.transactionToken %></td>
      <td><%= callback.callbackBody %></td>
      <td><%= callback.createdAt %></td>
    </tr>
  <% } %>
</table>
