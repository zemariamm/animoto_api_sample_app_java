<meta name="layout" content="app"></meta>
<h1>Your Animoto Video</h1>
<a href="<%= URLEncoder.encode(videoUrl) %>" style="display:block;height:480px;width:800px;" id="player"></a>
<script language="JavaScript">
  flowplayer("player", "<%= request.contextPath %>/swf/flowplayer-3.2.4.swf");
</script>
