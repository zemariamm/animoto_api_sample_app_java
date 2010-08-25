<meta name="layout" content="app"></meta>
<script type="text/javascript">
  $(document).ready( function() {
    document.getElementById('configuration').submit();
  });
</script>
<h1>SunDawg's Animoto Widget</h1>
<form id="configuration" style="display:none" method="POST" target="widget" action="<%= host %>/workflow">
  <% for (key in params.keySet()) { %>
    <% if (!['workspace', 'action', 'controller'].contains(key)) {%>
      <input type="hidden" name="<%= key %>" value="<%= params.get(key) %>"/>
    <% } %>
  <% } %>
</form>
<iframe id="widget" name="widget" width="100%" height="100%" scrolling="no" frameborder="0"/>
