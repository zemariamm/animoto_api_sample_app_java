<html>
  <head>
    <title>SunDawg's Photo Sharing Site (Java)</title>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js" type="text/javascript"></script>
    <script src="<%= request.contextPath %>/js/flowplayer-3.2.4.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="<%= request.contextPath %>/css/app.css">
  </head>
  <body>
    <a href="<%= request.contextPath %>/home">Home</a> | <a href="<%= request.contextPath %>/callback">View Callbacks</a>
    <g:layoutBody/>
  </body>
</html>
