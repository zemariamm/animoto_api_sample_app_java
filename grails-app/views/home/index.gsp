<meta name="layout" content="app"></meta>
<h1>Photo Album - SunDawg's Little Puppies</h1>

<% for (image in images) { %>
  <img height="100px" src="<%= image %>"/>
<% } %>

<form method="GET" action="<%= request.contextPath %>/widget">
  <% count = 1 %>
  <% for (image in images) { %>
    <input type="hidden" name="workspace.defaultVisuals[<%= count %>].url" value="<%= image %>"/>
    <% count += 1 %>
  <% } %> 
  <input type="submit" value="Make Animoto Video"/>
</form>
